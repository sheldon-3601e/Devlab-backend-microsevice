package com.sheldon.devlabbackendjudgeservice.controller.inner;

import com.sheldon.devlabbackendjudgeservice.Judge.JudgeService;
import com.sheldon.devlabbackendserviceclient.service.JudgeFeignClient;
import devlabbackendmodel.entity.QuestionSubmit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sheldon
 * @version 1.0
 * @className UserInnerController
 * @date 2024/3/11 18:26
 * @description 用于内部调用的接口
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    /**
     * 执行判题
     *
     * @param questionSubmitId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") Long questionSubmitId) {
        return judgeService.doJudge(questionSubmitId);
    }

    ;

}
