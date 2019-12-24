package com.tedu.dao;

import com.tedu.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleDao {

    @Select("select * from sys_roles")
    List<SysRole> findObjects();

    List<SysRole> findPageObjects(String name);

//    @Select("SELECT * FROM sys_roles WHERE id=#{id}")
    SysRole findObjectById(Integer id);

    int insertObject(SysRole sysRole);

    int deleteById(Integer id);

    int updateObject(SysRole sysRole);

}
