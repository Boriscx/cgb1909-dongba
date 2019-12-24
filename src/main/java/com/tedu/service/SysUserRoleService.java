package com.tedu.service;

import com.tedu.pojo.JsonResult;

import java.util.List;

public interface SysUserRoleService {

    /**
     * 根据字段查找存在数量
     * @param columnName 字段名称
     * @param columnValue 字段值
     * @return 字段值和条件下数据库包含数据的条数
     *
     */
    JsonResult isExistByColumn(String columnName, Integer columnValue);

    JsonResult deleteByColumn(String columnName,Integer id);

    JsonResult saveObjectByUserIdAndRoleIds(Integer userId, List<Integer> roleIds);




}
