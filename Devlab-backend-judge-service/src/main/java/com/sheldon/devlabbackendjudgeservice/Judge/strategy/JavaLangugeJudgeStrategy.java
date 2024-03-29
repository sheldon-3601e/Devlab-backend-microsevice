package com.sheldon.devlabbackendjudgeservice.Judge.strategy;


import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.JudgeInfo;
import devlabbackendmodel.dto.questionSubmit.JudgeConfig;
import devlabbackendmodel.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DefaultJudgeStrategy
 * @Author sheldon
 * @Date 2024/3/4 18:23
 * @Version 1.0
 * @Description Java 策略模式实现
 */
public class JavaLangugeJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        List<String> outputList = judgeContext.getOutputList();
        List<String> responseOutputList = judgeContext.getResponseOutputList();
        JudgeConfig judgeConfig = judgeContext.getJudgeConfig();
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();

        long timeLimit = judgeConfig.getTimeLimit();
        long memoryLimit = judgeConfig.getMemoryLimit();

        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L);
        long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);

        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.Accepted;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);

        if (outputList.size() != responseOutputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        for (int i = 0; i < outputList.size(); i++) {
            if (!outputList.get(i).equals(responseOutputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        if (time > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (memory > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
