package com.sheldon.devlabbackendjudgeservice.Judge.codesandbox;


import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeRequest;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @ClassName CodeSandbox
 * @Author sheldon
 * @Date 2024/3/4 15:51
 * @Version 1.0
 * @Description 代码沙箱接口定义
 */
public interface CodeSandbox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
