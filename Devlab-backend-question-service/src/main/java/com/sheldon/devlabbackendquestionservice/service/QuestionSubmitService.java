package com.sheldon.devlabbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import devlabbackendmodel.dto.questionSubmit.QuestionSubmitAddRequest;
import devlabbackendmodel.dto.questionSubmit.QuestionSubmitQueryRequest;
import devlabbackendmodel.entity.QuestionSubmit;
import devlabbackendmodel.entity.User;
import devlabbackendmodel.vo.QuestionSubmitVO;

/**
 * @author sheldon
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-03-02 15:25:26
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     * @param questionSubmitSubmitSubmitSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitSubmitSubmitSubmitQueryRequest);

    /**
     * 获取题目提交封装
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目提交封装
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
