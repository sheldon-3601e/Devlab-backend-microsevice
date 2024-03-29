package com.sheldon.devlabbackenduserservice.controller;


import com.sheldon.devlabbackenduserservice.service.TagService;
import com.sheldon.devlabbackendcommon.common.BaseResponse;
import com.sheldon.devlabbackendcommon.common.ErrorCode;
import com.sheldon.devlabbackendcommon.common.ResultUtils;
import com.sheldon.devlabbackendcommon.exception.BusinessException;
import com.sheldon.devlabbackendcommon.exception.ThrowUtils;
import devlabbackendmodel.dto.tag.TagQueryRequest;
import devlabbackendmodel.entity.Tag;
import devlabbackendmodel.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 *
 * @author <a href="https://github.com/sheldon-3601e">sheldon</a>
 * @from <a href="https://github.com/sheldon-3601e">sheldon</a>
 */
@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 获取所有标签列表
     *
     * @param request
     * @return
     */
    @PostMapping("/list/vo")
    public BaseResponse<List<TagVO>> listTagVO(
            HttpServletRequest request) {
        List<Tag> tagList = tagService.list();
        List<TagVO> tagVOList = tagService.getTagVO(tagList);
        return ResultUtils.success(tagVOList);
    }

    /**
     * 获取查询次数最多的标签
     *
     * @param tagQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/top/vo")
    public BaseResponse<List<TagVO>> listTagVOByTop(@RequestBody TagQueryRequest tagQueryRequest,
                                                    HttpServletRequest request) {
        if (tagQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer number = tagQueryRequest.getNumber();
        // 限制爬虫
        ThrowUtils.throwIf(number > 20, ErrorCode.PARAMS_ERROR);
        List<TagVO> tagVO = tagService.listTopTagVO(number);
        return ResultUtils.success(tagVO);
    }
}
