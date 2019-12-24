package com.tedu.service;

import com.tedu.entity.SysUser;
import com.tedu.pojo.JsonResult;

public interface SysUserService {

    JsonResult findPageObjects(String username,Integer pageCurrent,Integer pageSize);

    JsonResult findObjects(String username);

    JsonResult deleteObjectById(Integer id);

    JsonResult saveObject(SysUser sysUser);

    JsonResult updateObject(SysUser sysUser);
}
