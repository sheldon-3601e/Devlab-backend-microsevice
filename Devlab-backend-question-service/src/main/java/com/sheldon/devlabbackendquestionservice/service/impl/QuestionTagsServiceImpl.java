package com.sheldon.devlabbackendquestionservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheldon.devlabbackendquestionservice.mapper.QuestionTagsMapper;
import com.sheldon.devlabbackendquestionservice.service.QuestionTagsService;
import devlabbackendmodel.entity.QuestionTags;
import org.springframework.stereotype.Service;

/**
* @author sheldon
* @description 针对表【question_tags(标签)】的数据库操作Service实现
* @createDate 2024-03-04 00:53:26
*/
@Service
public class QuestionTagsServiceImpl extends ServiceImpl<QuestionTagsMapper, QuestionTags>
    implements QuestionTagsService {

}




