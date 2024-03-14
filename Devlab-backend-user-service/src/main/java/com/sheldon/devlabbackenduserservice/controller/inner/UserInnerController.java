package com.sheldon.devlabbackenduserservice.controller.inner;

import com.sheldon.devlabbackendserviceclient.service.UserFeignClient;
import com.sheldon.devlabbackenduserservice.service.UserService;
import devlabbackendmodel.entity.User;
import devlabbackendmodel.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author sheldon
 * @version 1.0
 * @className UserInnerController
 * @date 2024/3/11 18:26
 * @description 用于内部调用的接口
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;

    /**
     * 根据用户 id 获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") Long userId) {
        return userService.getById(userId);
    }


    /**
     * 根据用户 id 列表获取用户信息
     *
     * @param idList
     * @return
     */
    @Override
    @GetMapping("/get/ids")
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }


    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    @Override
    @PostMapping("/get/userVO")
    public UserVO getUserVO(@RequestBody User user) {
        return userService.getUserVO(user);
    }


}
