package com.tedu.dao;

import com.tedu.entity.SysMenu;
import com.tedu.pojo.MenuNote;
import com.tedu.pojo.UserMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SysMenuDao {

    List<SysMenu> findObjects();

    List<UserMenuVo> findMenusByUserId(Integer userId);

    List<MenuNote> findNoteObjects();

    int updateObject(SysMenu sysMenu);

    int insertObject(SysMenu sysMenu);

    int deleteById(Integer id);
}
