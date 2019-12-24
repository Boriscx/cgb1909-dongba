package com.tedu.dao;

import com.tedu.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleMenuDao {

    /**
     * 根据字段查找存在数量
     * @param columnName 字段名称
     * @param columnValue 字段值
     * @return 字段值和条件下数据库包含数据的条数
     *
     */
    int isExistByColumn(@Param("columnName") String columnName, @Param("columnValue") Object columnValue);

    /**
     * 根据字段删除
     * @param columnName 字段名称
     * @param columnValues 字段值
     * @return 删除条数
     */
    int deleteByColumns(@Param("columnName") String columnName, @Param("columnValues") Object... columnValues);

    @Delete("delete from sys_role_menus where role_id=#{roleId}")
    int deleteByRoleId(Integer roleId);

    /**
     * 添加角色菜单信息
     * @param sysRoleMenu 角色菜单信息
     * @return 存入结果
     */
    int insertObject(SysRoleMenu sysRoleMenu);

    /**
     * 根据角色id和菜单id数组(一个或多个)进行添加
     * @param roleId 角色id
     * @param menuIds 菜单id数组(一个或多个)
     * @return 添加条数
     */
    int insertObjectByRoleIdAndMenuIds(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

}
