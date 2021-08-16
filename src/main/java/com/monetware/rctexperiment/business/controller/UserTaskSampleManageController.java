package com.monetware.rctexperiment.business.controller;

import com.monetware.rctexperiment.business.pojo.dto.manage.FlowChartDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.ResultDataAnalysisDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.SearchGroupResultDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.SearchUserTaskSampleByParamDTO;
import com.monetware.rctexperiment.business.pojo.vo.manage.AddUserTaskSampleVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.SearchUserTaskSampleByParamVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.UpdateUserTaskSampleListVO;
import com.monetware.rctexperiment.business.service.manage.UserTaskSampleManageService;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import com.monetware.rctexperiment.system.base.PageList;
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
 * @create 2020/10/15 7:08 下午
 * @description RCT用户实验样本管理相关api
 */
@Api("RCT用户实验样本管理相关api")
@RestController
@RequestMapping("/rct")
public class UserTaskSampleManageController {

    @Autowired
    UserTaskSampleManageService userTaskSampleManageService;

    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 条件查询用户实验样本
     * @Date 11:35 上午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<com.monetware.rctexperiment.system.base.PageList<com.monetware.rctexperiment.business.pojo.dto.manage.SearchUserTaskSampleByParamDTO>>
     **/
    @ApiOperation(value = "条件查询用户实验样本")
    @PostMapping("v1/searchUserTaskSampleByParam")
    public ResultData<PageList<SearchUserTaskSampleByParamDTO>> searchUserTaskSampleByParam(@RequestBody SearchUserTaskSampleByParamVO param) {
        PageList<SearchUserTaskSampleByParamDTO> result = userTaskSampleManageService.searchUserTaskSampleByParam(param);
        return new ResultData<>(0, "调用成功", result);
    }

    /**
     * @Author budi
     * @Description 添加用户实验样本
     * @Date 11:43 上午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "添加用户实验样本")
    @PostMapping("v1/addUserTaskSample")
    public ResultData<Integer> addUserTaskSample(@RequestBody AddUserTaskSampleVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.addUserTaskSample(param));
    }
    
    /**
     * @Author budi
     * @Description 删除用户实验样本
     * @Date 3:55 下午 2020/10/22
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "删除用户实验样本")
    @PostMapping("v1/deleteUserTaskSample")
    public ResultData<Integer> deleteUserTaskSample(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.deleteUserTaskSample(param));
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（签署知情同意书）
     * @Date 1:51 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "批量修改用户实验样本（签署知情同意书）")
    @PostMapping("v1/updateUserTaskSampleListIfAgree")
    public ResultData<String> updateUserTaskSampleListIfAgree(@RequestBody UpdateUserTaskSampleListVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.updateUserTaskSampleListIfAgree(param));
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（测量基线数据）
     * @Date 2:28 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "批量修改用户实验样本（测量基线数据）")
    @PostMapping("v1/updateUserTaskSampleListMeasurementScore")
    public ResultData<String> updateUserTaskSampleListMeasurementScore(@RequestBody UpdateUserTaskSampleListVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.updateUserTaskSampleListMeasurementScore(param));
    }
    
    /**
     * @Author budi
     * @Description 批量修改用户实验样本（简单随机化分组）
     * @Date 2:52 下午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "批量修改用户实验样本（简单随机化分组）")
    @PostMapping("v1/updateUserTaskSampleListGroupType")
    public ResultData<String> updateUserTaskSampleListGroupType(@RequestBody UpdateUserTaskSampleListVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.updateUserTaskSampleListGroupType(param));
    }

    /**
     * @Author budi
     * @Description 查询分组结果
     * @Date 1:33 下午 2020/10/23
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<com.monetware.rctexperiment.business.pojo.dto.manage.SearchGroupResultDTO>
     **/
    @ApiOperation(value = "查询分组结果")
    @PostMapping("v1/searchGroupResult")
    public ResultData<SearchGroupResultDTO> searchGroupResult(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.searchGroupResult(param));
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（第一次随访）
     * @Date 11:01 上午 2020/10/19
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "批量修改用户实验样本（第一次随访）")
    @PostMapping("v1/updateUserTaskSampleListFirstAccessScore")
    public ResultData<String> updateUserTaskSampleListFirstAccessScore(@RequestBody UpdateUserTaskSampleListVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.updateUserTaskSampleListFirstAccessScore(param));
    }
    
    /**
     * @Author budi
     * @Description 批量修改用户实验样本（第二次随访）
     * @Date 11:03 上午 2020/10/19
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<java.lang.String>
     **/
    @ApiOperation(value = "批量修改用户实验样本（第二次随访）")
    @PostMapping("v1/updateUserTaskSampleListSecondAccessScore")
    public ResultData<String> updateUserTaskSampleListSecondAccessScore(@RequestBody UpdateUserTaskSampleListVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.updateUserTaskSampleListSecondAccessScore(param));
    }

    /**
     * @Author budi
     * @Description 生成流程图
     * @Date 11:47 上午 2020/10/19
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<com.monetware.rctexperiment.business.pojo.dto.manage.GenerateFlowChartDTO>
     **/
    @ApiOperation(value = "生成流程图")
    @PostMapping("v1/generateFlowChart")
    public ResultData<FlowChartDTO> generateFlowChart(@RequestBody SearchUserTaskSampleByParamVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.generateFlowChart(param));
    }
    
    /**
     * @Author budi
     * @Description 生成结果数据分析
     * @Date 3:38 下午 2020/10/19
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<com.monetware.rctexperiment.business.pojo.dto.manage.ResultDataAnalysisDTO>
     **/
    @ApiOperation(value = "生成结果数据分析")
    @PostMapping("v1/generateResultDataAnalysis")
    public ResultData<ResultDataAnalysisDTO> generateResultDataAnalysis(@RequestBody SearchUserTaskSampleByParamVO param) {
        return new ResultData<>(0, "调用成功", userTaskSampleManageService.generateResultDataAnalysis(param));
    }
    
    // =========================== budi End ===========================

}
