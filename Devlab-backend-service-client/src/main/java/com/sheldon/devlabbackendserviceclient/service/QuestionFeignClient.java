package com.sheldon.devlabbackendserviceclient.service;



import devlabbackendmodel.entity.Question;
import devlabbackendmodel.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sheldon
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2024-03-02 15:25:26
 */
@FeignClient(name = "devlab-backend-question-service", path = "/api/question/inner")
public interface QuestionFeignClient {

    /**
     * 根据题目 id 获取题目信息
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);

    /**
     * 根据题目提交 id 获取题目提交信息
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId);

    /**
     * 更新题目提交信息
     * @param questionSubmitUpdate
     * @return
     */
    @PostMapping("/submit/update/id")
    boolean updateQuestionSubmitById(@RequestBody  QuestionSubmit questionSubmitUpdate);

}
