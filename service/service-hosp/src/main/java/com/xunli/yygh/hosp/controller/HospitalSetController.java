package com.xunli.yygh.hosp.controller;

import com.xunli.hosp.yygh.model.hosp.HospitalSet;
import com.xunli.yygh.common.result.Result;
import com.xunli.yygh.hosp.service.HospitalSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @Author：XiaoMa
 * @Date：2021/8/26 14:16
 * @Description：Controller
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * @description: 查询医院设置表所有信息
     * @Author: XiaoMa
     * @Param:
     * @Return: com.xunli.yygh.common.result.Result
     * @Date: 2021/8/26 17:28
     */
    @ApiOperation(value = "获取所有医院设置信息")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        System.out.println(list);
        Result<List<HospitalSet>> ok = Result.ok(list);
        return ok;

    }

    /**
     * @description: 逻辑删除医院设置信息
     * @Author: XiaoMa
     * @Param: id
     * @Return: com.xunli.yygh.common.result.Result
     * @Date: 2021/8/26 17:28
     */
    @ApiOperation(value = "逻辑删除医院设置信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(
            @ApiParam(name = "id", value = "医院的id")
            @PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
