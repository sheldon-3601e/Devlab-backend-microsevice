package com.sheldon.devlabbackendquestionservice.controller.inner;

import com.sheldon.devlabbackendquestionservice.service.QuestionService;
import com.sheldon.devlabbackendquestionservice.service.QuestionSubmitService;
import com.sheldon.devlabbackendserviceclient.service.QuestionFeignClient;
import devlabbackendmodel.entity.Question;
import devlabbackendmodel.entity.QuestionSubmit;
import org.springframework.web.bind.annotation.*;

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
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    /**
     * 根据题目 id 获取题目信息
     * @param questionId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") Long questionId) {
        return questionService.getById(questionId);
    }

    /**
     * 根据题目提交 id 获取题目提交信息
     * @param questionSubmitId
     * @return
     */
    @Override
    @GetMapping("/submit/get/id")
    public   QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    };

    /**
     * 更新题目提交信息
     * @param questionSubmitUpdate
     * @return
     */
    @Override
    @PostMapping("/submit/update/id")
    public  boolean updateQuestionSubmitById(@RequestBody  QuestionSubmit questionSubmitUpdate){
        return questionSubmitService.updateById(questionSubmitUpdate);
    };




}
