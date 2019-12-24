package com.tedu.dao;

import com.github.pagehelper.PageHelper;
import com.tedu.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysLogDao {

    @Select("SELECT * FROM sys_logs")
    List<SysLog> findObjects(String username);

    List<SysLog> findPageObjects(String username);

    int deleteByIds(@Param("ids") Integer... ids);

    int insertObject(SysLog sysLog);
}
