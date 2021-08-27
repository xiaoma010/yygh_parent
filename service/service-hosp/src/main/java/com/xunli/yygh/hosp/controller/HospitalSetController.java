package com.xunli.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunli.hosp.yygh.model.hosp.HospitalSet;
import com.xunli.hosp.yygh.vo.hosp.HospitalSetQueryVo;
import com.xunli.yygh.common.result.Result;
import com.xunli.yygh.common.utils.MD5;
import com.xunli.yygh.hosp.service.HospitalSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/** @Author：XiaoMa @Date：2021/8/26 14:16 @Description：Controller */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

  @Autowired private HospitalSetService hospitalSetService;

  /**
   * @Description: 查询医院设置表所有信息 @Author: XiaoMa @Param: @Return:
   * com.xunli.yygh.common.result.Result @Date: 2021/8/27 10:26
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
   * @description: 逻辑删除医院设置信息 @Author: XiaoMa @Param: id @Return:
   *     com.xunli.yygh.common.result.Result @Date: 2021/8/26 17:28
   */
  @ApiOperation(value = "逻辑删除医院设置信息")
  @DeleteMapping("{id}")
  public Result removeHospSet(@ApiParam(name = "id", value = "医院的id") @PathVariable Long id) {
    boolean flag = hospitalSetService.removeById(id);
    if (flag) {
      return Result.ok();
    } else {
      return Result.fail();
    }
  }

  /**
   * @Description: 条件分页查询 @Author: XiaoMa @Param: current
   *
   * @param: limit
   * @param: hospitalSetQueryVo @Return: com.xunli.yygh.common.result.Result @Date: 2021/8/27 11:28
   */
  @ApiOperation(value = "条件分页查询")
  @PostMapping("findPageHospSet/{current}/{limit}")
  public Result findPageHospSet(
      @PathVariable Long current,
      @PathVariable Long limit,
      @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
    // 创建page对象，传递当前页，每页记录数
    Page<HospitalSet> page = new Page<>(current, limit);
    // 构建条件
    QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();
    // 医院名称
    String hosname = hospitalSetQueryVo.getHosname();
    // 医院编号
    String hoscode = hospitalSetQueryVo.getHoscode();
    if (!StringUtils.isEmpty(hosname)) {
      hospitalSetQueryWrapper.like("hosname", hospitalSetQueryVo.getHosname());
    }
    if (!StringUtils.isEmpty(hoscode)) {
      hospitalSetQueryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
    }
    Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, hospitalSetQueryWrapper);
    return Result.ok(hospitalSetPage);
  }

  /**
   * @Description: 添加医院设置 @Author: XiaoMa @Param: hospitalSet @Return:
   * com.xunli.yygh.common.result.Result @Date: 2021/8/27 11:28
   */
  @PostMapping("saveHospitalSet")
  public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
    // 设置状态 1 使用 0 不能使用
    hospitalSet.setStatus(1);
    // 签名秘钥
    Random random = new Random();
    hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
    // 调用service
    boolean save = hospitalSetService.save(hospitalSet);
    if (save) {
      return Result.ok();
    } else {
      return Result.fail();
    }
  }
}
