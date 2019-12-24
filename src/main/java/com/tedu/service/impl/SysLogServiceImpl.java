package com.tedu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tedu.aspect.annotation.RequestLog;
import com.tedu.config.PageProperties;
import com.tedu.dao.SysLogDao;
import com.tedu.entity.SysLog;
import com.tedu.exception.ServiceException;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;
import com.tedu.service.SysLogService;
import com.tedu.util.Assert;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    private SysLogDao sysLogDao;

    private PageProperties pageProperties;

    public SysLogServiceImpl(SysLogDao sysLogDao, PageProperties pageProperties) {
        this.sysLogDao = sysLogDao;
        this.pageProperties = pageProperties;
    }

    @Override
    public JsonResult findObjects(String username) {
        return new JsonResult<>(sysLogDao.findObjects(username));
    }

    @Override
    public JsonResult findPageObjects(String username, Integer currentPage, Integer pageSize) {
        if (currentPage == null) throw new ServiceException("页码不能为空");
        if (pageSize == null || pageSize < 3) pageSize = pageProperties.getPageSize();
        Page<SysLog> objects = PageHelper.startPage(currentPage, pageSize).doSelectPage(() ->
                sysLogDao.findPageObjects(username));
        // Assert.isNull(objects.getTotal(), "没有数据");
        if (objects.getTotal()<1) return JsonResult.error("没有数据");
        return new JsonResult<>(new PageObject<>(objects));
    }

    @RequestLog("删除日志")
    @Override
    public JsonResult deleteByIds(Integer... ids) {
        Assert.isValid(ids == null || ids.length == 0, "请选择要删除的数据");
        int row = sysLogDao.deleteByIds(ids);
        if (row<1)
            return JsonResult.error("删除失败");
        return JsonResult.success("删除成功");
    }


    @Async
    @Override
    public void saveObject(SysLog sysLog) {
        sysLogDao.insertObject(sysLog);
    }
}
