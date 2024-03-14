package com.sheldon.devlabbackendjudgeservice.Judge;


import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.JudgeInfo;
import com.sheldon.devlabbackendjudgeservice.Judge.strategy.DefaultJudgeStrategy;
import com.sheldon.devlabbackendjudgeservice.Judge.strategy.JavaLangugeJudgeStrategy;
import com.sheldon.devlabbackendjudgeservice.Judge.strategy.JudgeContext;
import com.sheldon.devlabbackendjudgeservice.Judge.strategy.JudgeStrategy;
import devlabbackendmodel.entity.QuestionSubmit;
import devlabbackendmodel.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Service;

/**
 * @ClassName JudgeManage
 * @Author sheldon
 * @Date 2024/3/4 18:35
 * @Version 1.0
 * @Description 管理判题策略
 */
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();

        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (QuestionSubmitLanguageEnum.JAVA.getValue() .equals(language)) {
            judgeStrategy = new JavaLangugeJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
