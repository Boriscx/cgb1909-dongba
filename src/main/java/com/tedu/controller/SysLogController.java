package com.tedu.controller;

import com.github.pagehelper.Page;
import com.tedu.entity.SysLog;
import com.tedu.pojo.JsonResult;
import com.tedu.pojo.PageObject;
import com.tedu.service.SysLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/log/")
public class SysLogController {

    private SysLogService sysLogService;

    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

//    @RequestMapping("doFindObjects")
//    public Object doFindObjects() {
//        return sysLogService.findObjects(null);
//    }

    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObject(String username, Integer pageCurrent,Integer pageSize) {
        return sysLogService.findPageObjects(username, pageCurrent,pageSize);
    }

    @PostMapping("doDeleteObjects")
    public JsonResult doDeleteByIds(@RequestParam(name = "ids") Integer... ids) {
        return sysLogService.deleteByIds(ids);
//        System.out.println("delete ids " + Arrays.toString(ids));
//        return new JsonResult<>("delete Ok");
    }

}
