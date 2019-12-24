package com.tedu.service;

import com.tedu.entity.SysLog;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;

import java.util.List;

public interface SysLogService {

     JsonResult findObjects(String username);

     JsonResult findPageObjects(String username, Integer currentPage, Integer pageSize);

     JsonResult deleteByIds(Integer... ids);

     void saveObject(SysLog sysLog);
}
