package com.tedu.service;

import com.tedu.entity.SysMenu;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.MenuNote;
import com.tedu.pojo.UserMenuVo;

import java.util.List;

public interface SysMenuService {
    JsonResult findMenusByUserId(Integer userId);

    List<SysMenu> findObjects();

    List<MenuNote> findNoteObjects();

    JsonResult updateObject(SysMenu sysMenu);

    void saveObject(SysMenu sysMenu);

    void deleteById(Integer id);


}
