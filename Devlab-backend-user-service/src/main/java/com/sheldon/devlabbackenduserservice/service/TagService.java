package com.sheldon.devlabbackenduserservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import devlabbackendmodel.entity.Tag;
import devlabbackendmodel.vo.TagVO;

import java.util.List;

/**
* @author sheldon
* @description 针对表【tag(标签)】的数据库操作Service
* @createDate 2024-02-11 00:26:13
*/
public interface TagService extends IService<Tag> {

    /**
     * 获取标签封装类
     *
     * @param tag
     * @return
     */
    TagVO getTagVO(Tag tag);

    /**
     * 获取标签封装类列表
     *
     * @param tagList
     * @return
     */
    List<TagVO> getTagVO(List<Tag> tagList);

    /**
     * 查询查询次数靠前的标签列表
     *
     * @param number
     * @return
     */
    List<TagVO> listTopTagVO(Integer number);
}
