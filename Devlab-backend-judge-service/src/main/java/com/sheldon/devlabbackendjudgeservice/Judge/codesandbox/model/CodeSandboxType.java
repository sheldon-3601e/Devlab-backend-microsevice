package com.sheldon.devlabbackendjudgeservice.Judge.codesandbox.model;

/**
 * @author sheldon
 * @version 1.0
 * @className CodeSandboxType
 * @date 2024/3/5 1:07
 * @description 代码沙箱类型
 */
public enum CodeSandboxType {
    EXAMPLE,
    REMOTE,
    THIRDPARTY;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
