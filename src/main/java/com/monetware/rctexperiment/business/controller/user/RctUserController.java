package com.monetware.rctexperiment.business.controller.user;

import com.monetware.rctexperiment.business.pojo.dto.usertask.UserTaskDTO;
import com.monetware.rctexperiment.business.pojo.vo.common.PageVO;
import com.monetware.rctexperiment.business.pojo.vo.user.RctUserVo;
import com.monetware.rctexperiment.business.service.user.UserService;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: RctUserController
 * @author: 彭于晏
 * @create: 2020-10-15 15:24
 **/
@Api("用户相关API")
@RestController
@RequestMapping("/user")
public class RctUserController {
    @Autowired
    UserService userService;
    @PostMapping("v1/login")
    @ApiOperation("用户登录")
    public ResultData<Integer> login(@RequestBody RctUserVo rctUserVo){
         userService.login(rctUserVo);
        return new ResultData(0,"登录成功");
    }

    /**
     * 查询用户做过的实验表
     * @param pageVo
     * @return
     */
    @PostMapping("v1/getUserTaskList")
    @ApiOperation("查询用户做过的实验表")
    public ResultData<PageList<UserTaskDTO>> getUserTaskManage(@RequestBody PageVO pageVo){

        return new ResultData<>(0,"调用成功",userService.getUserTaskManage(pageVo));
    }


    /**
     * 判断用户是否为登录状态
     * @return
     */
    @PostMapping("/v1/userInfo")
    @ApiOperation("判断用户是否为登录状态")
    public ResultData<Integer> userInfo(){
         userService.userInfo();
         return new ResultData <>(0,"调用成功");
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/v1/signOut")
    @ApiOperation("登出")
    public ResultData<Integer> signOut(){
        userService.signOut();
        return new ResultData(0,"成功");
    }
}
