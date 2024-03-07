package com.sheldon.devlabbackenduserservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheldon.devlabbackenduserservice.mapper.UserTeamMapper;
import com.sheldon.devlabbackenduserservice.service.UserTeamService;
import devlabbackendmodel.entity.UserTeam;
import org.springframework.stereotype.Service;

/**
* @author sheldon
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-02-20 16:26:45
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




