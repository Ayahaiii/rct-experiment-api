package com.monetware.rctexperiment.business.controller;

import com.monetware.rctexperiment.business.dao.RctUserTaskMapper;
import com.monetware.rctexperiment.business.pojo.dto.manage.EnterSampleSelectionDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.GroupResultDTO;
import com.monetware.rctexperiment.business.pojo.vo.common.CommonVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.UpdateUserTaskVO;
import com.monetware.rctexperiment.business.service.manage.UserTaskManageService;
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
 * @create 2020/10/15 4:10 下午
 * @description RCT用户实验管理相关api
 */
@Api("RCT用户实验管理相关api")
@RestController
@RequestMapping("/rct")
public class UserTaskManageController {

    @Autowired
    UserTaskManageService userTaskManageService;

    // =========================== budi Begin ===========================
    
    /**
     * @Author budi
     * @Description 进入样本选择页面
     * @Date 2:22 下午 2020/10/23
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<com.monetware.rctexperiment.business.pojo.dto.manage.EnterSampleSelectionDTO>
     **/
    @ApiOperation(value = "进入样本选择页面")
    @PostMapping("v1/enterSampleSelection")
    public ResultData<EnterSampleSelectionDTO> enterSampleSelection(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.enterSampleSelection(param));
    }

    /**
     * @Author budi
     * @Description 修改用户实验
     * @Date 11:37 上午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "修改用户实验")
    @PostMapping("v1/updateUserTask")
    public ResultData<String> updateUserTask(@RequestBody UpdateUserTaskVO param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.updateUserTask(param));
    }

    /***
     * 样本筛选下一步
     * @param param
     * @return
     */
    @ApiOperation(value = "样本筛选下一步")
    @PostMapping("v1/sampleNext")
    public ResultData<Integer> sampleNext(@RequestBody UpdateUserTaskVO param){
        return new ResultData<>(0,"调用成功",userTaskManageService.sampleNext(param));
    }
    /**
     * @Author budi
     * @Description 下一步
     * @Date 1:49 下午 2020/10/22
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "下一步")
    @PostMapping("v1/nextStep")
    public ResultData<Integer> nextStep(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.nextStep(param));
    }
    @ApiOperation(value = "后去分组结果")
    @PostMapping("v1/groupResult")
    public ResultData<GroupResultDTO> groupResult(@RequestBody CommonVO commonVO){
        return new ResultData<>(0, "调用成功", userTaskManageService.groupResult(commonVO));
    }
    // =========================== budi End ===========================

}
