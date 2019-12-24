package com.tedu.dao;

import com.tedu.entity.SysDept;
import com.tedu.pojo.MenuNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysDeptDao {

    List<SysDept> findObjects();

    List<MenuNote> findNoteObjects();

    int insertObject(SysDept sysDept);

    int deleteById(Integer id);

    int updateObject(SysDept sysDept);

}
