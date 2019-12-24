package com.tedu.service.impl;

import com.tedu.dao.SysUserRoleDao;
import com.tedu.pojo.JsonResult;
import com.tedu.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private SysUserRoleDao sysUserRoleDao;

    public SysUserRoleServiceImpl(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
    }

    @Override
    public JsonResult isExistByColumn(String columnName, Integer columnValue) {
        if (columnValue<1) return JsonResult.error("值必须大于1");
        int row = sysUserRoleDao.isExistByColumn(columnName, columnValue);
        if (row>0)
            return new JsonResult<>(row);
        else
            return JsonResult.error("没有数据");
    }

    @Override
    public JsonResult deleteByColumn(String columnName, Integer id) {
        return null;
    }

    @Override
    public JsonResult saveObjectByUserIdAndRoleIds(Integer userId, List<Integer> roleIds) {
        return null;
    }
}
