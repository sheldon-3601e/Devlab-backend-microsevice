package com.sheldon.devlabbackendserviceclient.service;


import devlabbackendmodel.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName JudgeService
 * @Author sheldon
 * @Date 2024/3/4 17:00
 * @Version 1.0
 * @Description 判题服务接口
 */
@FeignClient(name = "devlab-backend-judge-service", path = "/api/judge/inner")
public interface JudgeFeignClient {

    /**
     * 执行判题
     * @param questionSubmitId
     * @return
     */
    @PostMapping("/do")
    QuestionSubmit doJudge(@RequestParam("questionSubmitId") Long questionSubmitId);

}
