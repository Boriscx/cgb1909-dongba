package com.tedu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tedu.config.PageProperties;
import com.tedu.dao.SysUserDao;
import com.tedu.entity.SysUser;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;
import com.tedu.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao;

    private PageProperties pageProperties;

    public SysUserServiceImpl(SysUserDao sysUserDao, PageProperties pageProperties) {
        this.pageProperties = pageProperties;
        this.sysUserDao = sysUserDao;
    }

    @Override
    public JsonResult findPageObjects(String username, Integer pageCurrent, Integer pageSize) {
        if (pageSize == null || pageSize < 3) {
            pageSize = pageProperties.getPageSize();
        }
        Page<Object> objects = PageHelper.startPage(pageCurrent, pageSize).doSelectPage(() -> sysUserDao.findPageObjects(username));
        if (objects.getTotal() < 1) return JsonResult.error("没有数据");
        return new JsonResult<>(new PageObject<>(objects));
    }

    @Override
    public JsonResult findObjects(String username) {
        return null;
    }

    @Override
    public JsonResult deleteObjectById(Integer id) {
        if (sysUserDao.deleteObject(id)<1) return JsonResult.error("删除失败");
        return JsonResult.success("删除成功"+id);
    }

    @Override
    public JsonResult saveObject(SysUser sysUser) {
        return null;
    }

    @Override
    public JsonResult updateObject(SysUser sysUser) {
        return null;
    }
}
