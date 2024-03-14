package com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.impl;


import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.CodeSandbox;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeRequest;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeResponse;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.JudgeInfo;
import devlabbackendmodel.enums.JudgeInfoMessageEnum;
import devlabbackendmodel.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @ClassName ExampleCodeSandbox
 * @Author sheldon
 * @Date 2024/3/4 16:00
 * @Version 1.0
 * @Description 示例代码沙箱（跑通业务流程）
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试代码沙箱信息");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.Accepted.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
