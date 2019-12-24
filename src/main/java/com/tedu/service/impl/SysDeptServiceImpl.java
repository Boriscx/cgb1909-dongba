package com.tedu.service.impl;

import com.tedu.aspect.annotation.RequestLog;
import com.tedu.dao.SysDeptDao;
import com.tedu.entity.SysDept;
import com.tedu.pojo.JsonResult;
import com.tedu.service.SysDeptService;
import com.tedu.util.Assert;
import com.tedu.util.ShiroUtil;
import org.springframework.stereotype.Service;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    private SysDeptDao sysDeptDao;


    public SysDeptServiceImpl(SysDeptDao sysDeptDao) {
        this.sysDeptDao = sysDeptDao;
    }

    @RequestLog("查找所有部门")
    @Override
    public JsonResult findObjects() {
        return new JsonResult<>(sysDeptDao.findObjects());
    }

    @Override
    public JsonResult findNoteObjects() {
        return new JsonResult<>(sysDeptDao.findNoteObjects());
    }

    @RequestLog("添加部门")
    @Override
    public JsonResult saveObject(SysDept sysDept) {
        if (Assert.isNull(sysDept)) return JsonResult.error("部门不能为空");
        sysDept.setCreatedUser(ShiroUtil.getUsername());
        return sysDeptDao.insertObject(sysDept) < 1 ? JsonResult.error("更新失败,记可能不存在了") : JsonResult.success("添加部门成功");
    }


    @RequestLog("删除部门")
    @Override
    public JsonResult deleteById(Integer id) {
        if (id == null || id < 1) return JsonResult.error("ID值非法");
        if (sysDeptDao.deleteById(id) < 1) return JsonResult.error("删除失败,部门可能不存在了");
        return JsonResult.success("删除部门成功");
    }

    @RequestLog("修改部门信息")
    @Override
    public JsonResult updateObject(SysDept sysDept) {
        if (Assert.isNull(sysDept)) return JsonResult.error("部门信息不能为空");
        if (sysDept.getId() == null || sysDept.getId() < 1) return JsonResult.error("id值非法");
        sysDept.setModifiedUser(ShiroUtil.getUsername());
        if (sysDeptDao.updateObject(sysDept) < 1) return JsonResult.error("修改失败,部门可能不存在");
        return JsonResult.success("修改部门信息成功");
    }
}
