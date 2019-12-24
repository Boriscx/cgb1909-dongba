package com.tedu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tedu.aspect.annotation.RequestLog;
import com.tedu.config.PageProperties;
import com.tedu.dao.SysRoleDao;
import com.tedu.dao.SysRoleMenuDao;
import com.tedu.entity.SysRole;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;
import com.tedu.service.SysRoleService;
import com.tedu.service.SysUserRoleService;
import com.tedu.util.Assert;
import com.tedu.util.ShiroUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private SysRoleDao sysRoleDao;


    private PageProperties pageProperties;

    private SysRoleMenuDao sysRoleMenuDao;

    private SysUserRoleService sysUserRoleService;

    public SysRoleServiceImpl(SysRoleDao sysRoleDao, PageProperties pageProperties, SysRoleMenuDao sysRoleMenuDao, SysUserRoleService sysUserRoleService) {
        this.sysRoleDao = sysRoleDao;
        this.pageProperties = pageProperties;
        this.sysRoleMenuDao = sysRoleMenuDao;
        this.sysUserRoleService = sysUserRoleService;
    }

    @RequestLog("查询所有角色")
    @Override
    public JsonResult findObjects() {
        List<SysRole> objects = sysRoleDao.findObjects();
        if (objects.isEmpty()) return JsonResult.error("没有记录");
        return new JsonResult<>(objects);
    }

    @RequestLog("分页查询角色")
    @Override
    public JsonResult findPageObjects(String name, Integer pageCurrent, Integer pageSize) {
        if (pageCurrent == null || pageCurrent < 1) return JsonResult.error("页码值不能小于1");
        if (pageSize == null || pageSize < 3) {
            pageSize = pageProperties.getPageSize();
        }
        Page<SysRole> pageObject = PageHelper.startPage(pageCurrent, pageSize).doSelectPage(() ->
                sysRoleDao.findPageObjects(name));
        if (pageObject.getResult().isEmpty()) return JsonResult.error("没有数据");
        return new JsonResult<PageObject>(new PageObject<SysRole>(pageObject));
    }

    @RequestLog("通过ID查找角色")
    @Override
    public JsonResult findObjectById(Integer id) {
        if (Assert.isNull(id)) return JsonResult.error("id值非法");
        SysRole objectById = sysRoleDao.findObjectById(id);
        if (Assert.isNull(objectById)) return JsonResult.error("角色不存在");
        return new JsonResult<>(objectById);
    }

    @RequestLog("添加角色")
    @Transactional
    @Override
    public JsonResult saveObject(SysRole sysRole) {
        // 检验参数
        if (Assert.isNull(sysRole) || sysRole.getName() == null || sysRole.getName().trim().isEmpty())
            return JsonResult.error("角色信息不能为空");
        //业务实现
        sysRole.setCreatedUser(ShiroUtil.getUsername());
        sysRoleDao.insertObject(sysRole);
        if (sysRole.getMenuIds().size() > 0) {
            sysRoleMenuDao.insertObjectByRoleIdAndMenuIds(sysRole.getId(), sysRole.getMenuIds());
        }
        return JsonResult.success("添加角色成功");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult deleteById(Integer integer) {
        if (Assert.isNull(integer)) return JsonResult.error("ip值无效");
        // 检查角色有没有正在使用
        if (sysUserRoleService.isExistByColumn("role_id", integer).getData() != null)
            return JsonResult.error("有用户已使用此角色,不能删除");
        sysRoleMenuDao.deleteByColumns("role_id", integer);
        int row = sysRoleDao.deleteById(integer);
        if (Assert.isNull(row)) return JsonResult.error("删除失败");
        return JsonResult.success("删除成功!");
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public JsonResult updateObject(SysRole sysRole) {
        if (sysRole.getId() == null || sysRole.getId() < 1) return JsonResult.error("角色不存在");
        sysRole.setModifiedUser(ShiroUtil.getUsername());
        int row = sysRoleDao.updateObject(sysRole);
        sysRoleMenuDao.deleteByRoleId(sysRole.getId());
        if (sysRole.getMenuIds() != null && sysRole.getMenuIds().size() > 0)
            sysRoleMenuDao.insertObjectByRoleIdAndMenuIds(sysRole.getId(), sysRole.getMenuIds());
        if (row < 1)
            return JsonResult.error("更新失败,请检查数据后重试");
        else
            return JsonResult.success("修改角色信息成功!");
    }

}
