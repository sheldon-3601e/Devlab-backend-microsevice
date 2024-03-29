package com.sheldon.devlabbackendjudgeservice.Judge;


import cn.hutool.json.JSONUtil;
import com.sheldon.devlabbackendcommon.common.ErrorCode;
import com.sheldon.devlabbackendcommon.exception.BusinessException;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.CodeSandbox;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.CodeSandboxFactory;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.CodeSandboxProxy;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeRequest;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeResponse;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.JudgeInfo;
import com.sheldon.devlabbackendjudgeservice.Judge.strategy.JudgeContext;
import com.sheldon.devlabbackendserviceclient.service.QuestionFeignClient;
import devlabbackendmodel.dto.question.JudgeCase;
import devlabbackendmodel.dto.questionSubmit.JudgeConfig;
import devlabbackendmodel.entity.Question;
import devlabbackendmodel.entity.QuestionSubmit;
import devlabbackendmodel.enums.QuestionSubmitStatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName JudgeServiceImpl
 * @Author sheldon
 * @Date 2024/3/4 17:02
 * @Version 1.0
 * @Description 判题服务实现类
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;


    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {

        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目提交记录不存在");
        }
        Integer status = questionSubmit.getStatus();
        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!QuestionSubmitStatusEnum.WAITING.getValue().equals(status)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目信息不存在");
        }
        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行，也能让用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.WORKING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "判题状态更改失败");
        }

        // 4）调用沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);

        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCases = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCases.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        List<String> outputList = judgeCases.stream().map(JudgeCase::getOutput).collect(Collectors.toList());

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);

        List<String> responseOutputList = executeCodeResponse.getOutputList();
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();

        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeContext.setOutputList(outputList);
        judgeContext.setResponseOutputList(responseOutputList);
        judgeContext.setJudgeConfig(judgeConfig);
        judgeContext.setJudgeInfo(judgeInfo);
        JudgeInfo judgeInfoResponse = judgeManager.doJudge(judgeContext);

        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoResponse));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;

    }
}
