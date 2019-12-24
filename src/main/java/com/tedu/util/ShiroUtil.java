package com.tedu.util;

import com.tedu.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * 自定义shiro工具
 */
public class ShiroUtil {

    // 获取用户身份信息的id
    public static Integer getUserId() {
        return getUser().getId();
    }

    // 获取身份信息中的用户名
    public static String getUsername() {
        return getUser().getUsername();
    }

    // 获取身份信息
    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }
}
