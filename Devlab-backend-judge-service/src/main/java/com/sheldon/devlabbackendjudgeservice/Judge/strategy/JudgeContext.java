package com.sheldon.devlabbackendjudgeservice.Judge.strategy;


import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.JudgeInfo;
import devlabbackendmodel.dto.questionSubmit.JudgeConfig;
import devlabbackendmodel.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @ClassName JudgeContext
 * @Author sheldon
 * @Date 2024/3/4 18:22
 * @Version 1.0
 * @Description 策略模式上下文
 */
@Data
public class JudgeContext {

    private QuestionSubmit questionSubmit;

    private List<String> outputList;

    private List<String> responseOutputList;

    private JudgeConfig judgeConfig;

    private JudgeInfo judgeInfo;

}
