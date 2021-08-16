package com.monetware.rctexperiment.business.controller;

import com.monetware.rctexperiment.business.pojo.dto.manage.SearchSampleByParamDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.SampleListVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.SearchSampleByParamVO;
import com.monetware.rctexperiment.business.service.manage.SampleManageService;
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
 * @create 2020/10/15 2:43 下午
 * @description RCT样本管理相关api
 */
@Api("RCT样本管理相关api")
@RestController
@RequestMapping("/rct")
public class SampleManageController {

    @Autowired
    SampleManageService sampleManageService;

    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 条件查询样本
     * @Date 11:36 上午 2020/10/16
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.ResultData<com.monetware.rctexperiment.system.base.PageList<com.monetware.rctexperiment.business.pojo.dto.manage.SearchSampleByParamDTO>>
     **/
    @ApiOperation(value = "条件查询样本")
    @PostMapping("v1/searchSampleByParam")
    public ResultData<PageList<SearchSampleByParamDTO>> searchSampleByParam(@RequestBody SearchSampleByParamVO param) {
        PageList<SearchSampleByParamDTO> result = sampleManageService.searchSampleByParam(param);
        return new ResultData<>(0, "调用成功", result);
    }


    // =========================== budi End ===========================

}
