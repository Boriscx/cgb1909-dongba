package com.tedu.service;

import com.tedu.entity.SysRole;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;

import java.util.List;

public interface SysRoleService {

    /**
     * 查询所有角色信息
     * @return 所有角色信息
     */
    JsonResult findObjects();

    /**
     * 按角色名模糊查找角色分页
     * @param name 角色名
     * @param pageCurrent 页码值(必须大于0)
     * @param pageSize 每页数据条数 (如果值为null或者小于3,则获取配置文件PageProperties中的pageSize值(默认为10))
     * @return 数据
     */
    JsonResult findPageObjects(String name, Integer pageCurrent, Integer pageSize);

    /**
     * 按id查找角色
     * @param id 指定的id
     * @return 角色信息
     */
    JsonResult findObjectById(Integer id);

    /**
     * 添加角色
     * @param sysRole 角色信息
     */
    JsonResult saveObject(SysRole sysRole);

    /**
     * 通过id值删除角色
     * @param id id值
     */
    JsonResult deleteById(Integer id);

    /**
     * 修改角色信息
     * @param sysRole 待更新的信息
     */
    JsonResult updateObject(SysRole sysRole);

}
