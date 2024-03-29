package com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.impl;


import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.CodeSandbox;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeRequest;
import com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @ClassName ThirdPartyCodeSandbox
 * @Author sheldon
 * @Date 2024/3/4 16:02
 * @Version 1.0
 * @Description 第三方代码沙箱
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
