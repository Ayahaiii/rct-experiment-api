package com.monetware.rctexperiment.business.controller.task;

import com.monetware.rctexperiment.business.pojo.dto.usertask.DiscussAndConclusDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctUserTaskDTO;
import com.monetware.rctexperiment.business.pojo.po.user.RctUser;
import com.monetware.rctexperiment.business.pojo.vo.common.CommonVO;
import com.monetware.rctexperiment.business.pojo.vo.task.*;
import com.monetware.rctexperiment.business.service.rtc.RctUserTaskService;
import com.monetware.rctexperiment.business.service.user.UserService;
import com.monetware.rctexperiment.system.base.ResultData;
import com.monetware.threadlocal.ThreadLocalManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * @description: RctUserTaskController
 * @author: 彭于晏
 * @create: 2020-10-15 15:33
 **/
@Api("用户实验相关API")
@RestController
@RequestMapping("/userTask")
public class RctUserTaskController {
    @Autowired
    RctUserTaskService rctUserTaskService;
    @Autowired
    UserService userService;
//=================================================hs begin=========================================

    /**
     * 是否有未完成的实验
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/ifBegin")
    @ApiOperation("是否有未完成的实验")
    public ResultData<RctUserTaskDTO> ifBegin(@RequestBody IfBeginVO rctUserTaskVo){
        RctUserTaskDTO rctUserTaskDTO = rctUserTaskService.ifBegin(rctUserTaskVo);
        if(rctUserTaskDTO!=null){
            return new ResultData<>(0,"实验已开始",rctUserTaskDTO);
        }
        return new ResultData<>(1,"实验未开始");
    }


    /**
     * 开始实验
     * @return
     */
    @PostMapping("/beginRctTask")
    @ApiOperation("开始实验")
    public ResultData<Integer> beginRctTask(@RequestBody BeginRctTaskVO rctUserTaskVo){
        RctUser rctUser = userService.commonGetUser();
        rctUserTaskVo.setUserId(rctUser.getId());
        return new ResultData<>(0,"调用成功",rctUserTaskService.beginRctTask(rctUserTaskVo));
    }


    /**
     *插入实验目的与工具
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/purposeAndTools")
    @ApiOperation("插入实验目的与工具")
    public ResultData<Integer> purposeAndTools(@RequestBody PurposeAndToolsVO rctUserTaskVo, HttpSession session){

        return new ResultData(0,"插入实验目的与工具",rctUserTaskService.purposeAndTools(rctUserTaskVo));
    }


    /**
     * 实验注册
     * @return
     */
    @PostMapping("/ExperimentalRegiste")
    @ApiOperation("实验注册")
    public ResultData<Integer> ExperimentalRegiste(@RequestBody ExperimentalRegisteVO rctUserTaskVo){

        return new ResultData(0,"实验注册成功",rctUserTaskService.ExperimentalRegiste(rctUserTaskVo));
    }


    /**
     * 获取实验注册号
     * @return
     */
    @PostMapping("/getNewRegisterNum")
    @ApiOperation("获取实验注册号")
    public ResultData<String> getNewRegisterNum(){

       return new ResultData(0,"取实验注册号",rctUserTaskService.getNewRegisterNum());
    }


    /**
     * 添加讨论和 结论
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/discussAndConclus")
    @ApiOperation("添加讨论和 结论")
    public ResultData<Integer> discussAndConclus(@RequestBody DiscussAndConclusVO rctUserTaskVo){

        return new ResultData(0,"添加讨论和 结论",rctUserTaskService.discussAndConclus(rctUserTaskVo));
    }


    /**
     * 获取当前步骤
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/getCurrentStep")
    @ApiOperation("获取当前步骤")
    public ResultData<Integer> getCurrentStep(@RequestBody RctUserTaskVO rctUserTaskVo){

        return new ResultData<>(0,"调用成功",rctUserTaskService.getCurrentStep(rctUserTaskVo));
    }


    /**
     * 项目结束
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/finallTask")
    @ApiOperation("项目结束")
    public ResultData<Integer> finallTask(@RequestBody CommonVO rctUserTaskVo){

        rctUserTaskService.finallStep(rctUserTaskVo);
        return new ResultData(0,"rct实验完成");
    }


    /**
     * 获取用户项目详细信息
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/getUserTask")
    @ApiOperation("通过用户实验表id获取相对应的实验")
    public ResultData getUserTask(@RequestBody CommonVO rctUserTaskVo){

       return new ResultData<>(0,"调用成功",rctUserTaskService.getUserTask(rctUserTaskVo));
    }


    /**
     * 删除实验
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/delUserTask")
    @ApiOperation("删除实验")
    public ResultData<Integer> delUserTask(@RequestBody CommonVO rctUserTaskVo){
         rctUserTaskService.delUserTask(rctUserTaskVo);
        return new ResultData(0,"成功");
    }

    /**
     * 获取wordUrl
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/getWordUrl")
    @ApiOperation("获取wordUrl")
    public ResultData<String> getWordUrl(@RequestBody CommonVO rctUserTaskVo){
//        rctUserTaskService.getWordUrl(rctUserTaskVo);
        return new ResultData(0,"成功",rctUserTaskService.getWordUrl(rctUserTaskVo));
    }

    /**
     * 获取讨论和结论
     * @param commonVO
     * @return
     */
    @PostMapping("/getDiscussAndConclus")
    @ApiOperation("获取讨论和结论")
    public ResultData<DiscussAndConclusDTO> getDiscussAndConclus(@RequestBody CommonVO commonVO){
        DiscussAndConclusDTO discussAndConclus = rctUserTaskService.getDiscussAndConclus(commonVO);
        return new ResultData<>(0,"成功",discussAndConclus);
    }

//===================================================hs end========================================
}
