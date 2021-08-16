package com.monetware.rctexperiment.business.controller;

import com.monetware.rctexperiment.business.service.manage.TaskManageService;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import com.monetware.rctexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/16 4:39 下午
 * @description RCT实验管理相关api
 */
@Api("RCT实验管理相关api")
@RestController
@RequestMapping("/rct")
public class TaskManageController {

    @Autowired
    TaskManageService taskManageService;

    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 获取实验选点动画URL
     * @Date 4:43 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取实验选点动画URL")
    @PostMapping("v1/getScreenFlash")
    public ResultData<String> getScreenFlash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getScreenFlash(param));
    }

    /**
     * @Author budi
     * @Description 获取签署同意书动画URL
     * @Date 4:51 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取签署同意书动画URL")
    @PostMapping("v1/getConsentFormFlash")
    public ResultData<String> getConsentFormFlash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getConsentFormFlash(param));
    }
    
    /**
     * @Author budi
     * @Description 获取测量数据动画URL
     * @Date 4:53 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取测量数据动画URL")
    @PostMapping("v1/getMetricalDataFlash")
    public ResultData<String> getMetricalDataFlash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getMetricalDataFlash(param));
    }
    
    /**
     * @Author budi
     * @Description 获取随机分组动画URL
     * @Date 4:54 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取随机分组动画URL")
    @PostMapping("v1/getRandomGroupFlash")
    public ResultData<String> getRandomGroupFlash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getRandomGroupFlash(param));
    }

    /**
     * @Author budi
     * @Description 获取分组结果通知动画1URL
     * @Date 4:56 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取分组结果通知动画1URL")
    @PostMapping("v1/getGroupResultNotice1Flash")
    public ResultData<String> getGroupResultNotice1Flash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getGroupResultNotice1Flash(param));
    }

    /**
     * @Author budi
     * @Description 获取分组结果通知动画2URL
     * @Date 4:56 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取分组结果通知动画2URL")
    @PostMapping("v1/getGroupResultNotice2Flash")
    public ResultData<String> getGroupResultNotice2Flash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getGroupResultNotice2Flash(param));
    }

    /**
     * @Author budi
     * @Description 获取分组结果通知动画3URL
     * @Date 4:56 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取分组结果通知动画3URL")
    @PostMapping("v1/getGroupResultNotice3Flash")
    public ResultData<String> getGroupResultNotice3Flash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getGroupResultNotice3Flash(param));
    }

    /**
     * @Author budi
     * @Description 获取第一次随访动画URL
     * @Date 4:59 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取第一次随访动画URL")
    @PostMapping("v1/getFirstAccessFlash")
    public ResultData<String> getFirstAccessFlash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getFirstAccessFlash(param));
    }

    /**
     * @Author budi
     * @Description 获取第二次随访动画URL
     * @Date 5:00 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "获取第二次随访动画URL")
    @PostMapping("v1/getSecondAccessFlash")
    public ResultData<String> getSecondAccessFlash(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", taskManageService.getSecondAccessFlash(param));
    }

    // =========================== budi End ===========================

}
