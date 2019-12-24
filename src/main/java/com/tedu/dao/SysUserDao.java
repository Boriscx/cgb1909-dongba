package com.tedu.dao;

import com.tedu.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserDao {

    @Select("SELECT id, username, email, mobile, valid, createdtime, modifiedtime FROM sys_users WHERE username like CONCAT('%',#{username},'%')")
    List<SysUser> findPageObjects(String username);

    @Select("SELECT * FROM sys_users WHERE ${columnName}=#{columnValue}")
    SysUser findObjectByColumn(String columnName, Object columnValue);

    List<String> findPermissionsByUserId(Integer userId);

    int insertObject(SysUser sysUser);

    int updateObject(SysUser sysUser);

    @Delete("DELETE FROM sys_users where id=#{id}")
    int deleteObject(Integer id);

    @Update("update sys_users set valid=#{id} where id = #{valid}")
    int updateValid(@Param("id") Integer id, @Param("valid") Integer valid);


}
