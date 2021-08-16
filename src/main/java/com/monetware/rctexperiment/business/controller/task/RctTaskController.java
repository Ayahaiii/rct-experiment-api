package com.monetware.rctexperiment.business.controller.task;

import com.github.pagehelper.PageInfo;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctTaskDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctTaskDetailDTO;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.business.pojo.vo.task.RctTaskVO;
import com.monetware.rctexperiment.business.service.rtc.RctTaskService;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: RctTask
 * @author: 彭于晏
 * @create: 2020-10-15 15:28
 **/
@Api("实验相关API")
@RestController
@RequestMapping("/task")
public class RctTaskController {
    @Autowired
    RctTaskService rctTaskService;
//====================================================hs begin======================================
    /**
     * 获取实验
     * @param rctTaskVo
     * @return
     */
    @PostMapping("getListRctTask")
    @ApiOperation("获取实验")
    public ResultData<PageList<RctTaskDTO>> getListRctTask(@RequestBody RctTaskVO rctTaskVo){
        return new ResultData<>(0,"调用成功",rctTaskService.getListRctTaskList(rctTaskVo));
    }
    /**
     * 查询单个实验得详细信息
     * @param rctTaskVo
     * @return
     */
    @PostMapping("/getRctTask")
    @ApiOperation("查询单个实验得详细信息")
    public ResultData<RctTaskDetailDTO> getRctTask(@RequestBody RctTaskVO rctTaskVo){
        return new ResultData<>(0,"调用成功",rctTaskService.getRctTask(rctTaskVo));
    }

//    /**
//     * 获取分组结果通知播放视频地址
//     * @return
//     */
//    @PostMapping("/groupResultNoticeFlash")
//    @ApiOperation("获取分组结果通知播放视频地址")
//    public ResultData groupResultNoticeFlash(@RequestBody RctTaskVo rctTaskVo){
//        return rctTaskService.groupResultNoticeFlash(rctTaskVo);
//    }
//=========================================hs end===================================================
}
