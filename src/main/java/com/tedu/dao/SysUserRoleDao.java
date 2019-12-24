package com.tedu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserRoleDao {

    /**
     * 根据字段查找存在数量
     * @param columnName 字段名称
     * @param columnValue 字段值
     * @return 字段值和条件下数据库包含数据的条数
     *
     */
    @Select("SELECT COUNT(*) FROM sys_user_roles WHERE ${columnName}=#{columnValue}")
    int isExistByColumn(@Param("columnName") String columnName, @Param("columnValue") Object columnValue);


}
