package com.tedu.service;

import com.tedu.entity.SysDept;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.MenuNote;

import java.util.List;

public interface SysDeptService {

    JsonResult findObjects();

    JsonResult findNoteObjects();

    JsonResult saveObject(SysDept sysDept);

    JsonResult deleteById(Integer id);

    JsonResult updateObject(SysDept sysDept);
}
