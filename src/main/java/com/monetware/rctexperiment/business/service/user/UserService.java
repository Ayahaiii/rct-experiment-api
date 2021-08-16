package com.monetware.rctexperiment.business.service.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monetware.rctexperiment.business.dao.RctTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskMapper;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctUserTaskDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.UserTaskDTO;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import com.monetware.rctexperiment.business.pojo.po.user.RctUser;
import com.monetware.rctexperiment.business.pojo.vo.common.PageVO;
import com.monetware.rctexperiment.business.pojo.vo.task.RctUserTaskVO;
import com.monetware.rctexperiment.business.pojo.vo.user.RctUserVo;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.base.ResultData;
import com.monetware.rctexperiment.system.util.redis.RedisUtil;
import com.monetware.threadlocal.ThreadLocalManager;
import com.monetware.threadlocal.TokenContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: UserService
 * @author: 彭于晏
 * @create: 2020-10-15 14:32
 **/
@Service
public class UserService {
    @Autowired
    private RctUserMapper rctUserMapper;

    @Autowired
    private RctUserTaskMapper rctUserTaskMapper;

    @Autowired
    private RctTaskMapper rctTaskMapper;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 用户登录
     * @return
     */
    public ResultData login(RctUserVo rctUserVo){
        RctUser rctUser=new RctUser();
        rctUser.setId(ThreadLocalManager.getUserId());
        RctUser rctUser1 = rctUserMapper.selectOne(rctUser);
        System.out.println(ThreadLocalManager.getTokenContext().getUserId());
        rctUser.setUsername(ThreadLocalManager.getTokenContext().getName());
        rctUser.setCreateTime(new Date());
        //判断用户是否第一次登录
        if(rctUser1==null){
            int i = rctUserMapper.insertSelective(rctUser);
        }else {
            int i = rctUserMapper.updateByPrimaryKeySelective(rctUser);
        }

        return new ResultData(0,"登录成功");
    }

    /**
     * 获取用户做过的实验
     * @param pageVo
     * @return
     */
    public PageList<UserTaskDTO>  getUserTaskManage(PageVO pageVo){
        Page page = PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        Example example=new Example(RctUserTask.class);
        example.orderBy("id").desc();
        Example.Criteria criteria = example.createCriteria();
        System.out.println(ThreadLocalManager.getUserId());
        criteria.andEqualTo("userId",ThreadLocalManager.getUserId());
        criteria.andNotEqualTo("status",3);
        RctUserTask rctUserTask=new RctUserTask();
//        rctUserTask.setUserId(1);
//        rctUserTask.setUserId(ThreadLocalManager.getUserId());
        List<RctUserTask> select = rctUserTaskMapper.selectByExample(example);
        List<UserTaskDTO>list=new ArrayList<>();
        for (RctUserTask userTask : select) {
            Integer taskId = userTask.getTaskId();
            RctTask rctTask=new RctTask();
            rctTask.setId(taskId);
            RctTask rctTask1 = rctTaskMapper.selectOne(rctTask);
            UserTaskDTO userTaskDto = new UserTaskDTO();
            userTaskDto.setName(rctTask1.getName());
            userTaskDto.setCoverImg(rctTask1.getCoverImg());
            userTaskDto.setUserTaskId(userTask.getId());
            userTaskDto.setTaskId(rctTask1.getId());
            userTaskDto.setRegisterTitle(userTask.getRegisterTitle());
            userTaskDto.setRegisterNum(userTask.getRegisterNum());
            userTaskDto.setRegisterStatus(userTask.getRegisterStatus());
            userTaskDto.setRegisterResult(userTask.getRegisterResult());
            userTaskDto.setFinishStepNum(userTask.getFinishStepNum());
            userTaskDto.setStatus(userTask.getStatus());
            userTaskDto.setBeginTime(userTask.getBeginTime());
            userTaskDto.setEndTime(userTask.getEndTime());
            list.add(userTaskDto);
        }
        PageList <UserTaskDTO> pageList=new PageList<>(page,list);
//        PageInfo rctUserTaskPageInfo = new PageInfo<>(select);
//        rctUserTaskPageInfo.setList(list);
//        return new ResultData<>(0,"查询成功",rctUserTaskPageInfo);
        return pageList;
    }
    /**
     * 获取用户信息
     * @return
     */
    public RctUser commonGetUser(){
        Integer userId = ThreadLocalManager.getUserId();
        RctUser rctUser=new RctUser();
        rctUser.setId(userId);
        RctUser rctUser1 = rctUserMapper.selectOne(rctUser);
        return rctUser1;
    }

    /**
     * 后去用户信息
     * @return
     */
    public void userInfo() {
        TokenContext tokenContext=null;
        tokenContext = ThreadLocalManager.getTokenContext();
        if(tokenContext==null){
            throw new RuntimeException("用户登录失效");
        }

    }

    /**
     * 登出
     */
    public void signOut() {
        //从当前线程中获取用户信息
        TokenContext tokenContext = ThreadLocalManager.getTokenContext();
        //从redis中删除用户信息
        redisUtil.remove(tokenContext.getToken());
    }
}
