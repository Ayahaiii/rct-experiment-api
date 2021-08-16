package com.monetware.rctexperiment.business.service.rtc;

import com.monetware.rctexperiment.business.dao.RctTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskMapper;
import com.monetware.rctexperiment.business.pojo.dto.usertask.DiscussAndConclusDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctUserTaskDTO;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import com.monetware.rctexperiment.business.pojo.vo.common.CommonVO;
import com.monetware.rctexperiment.business.pojo.vo.task.*;
import com.monetware.rctexperiment.business.service.manage.UserTaskManageService;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import com.monetware.rctexperiment.system.base.ResultData;
import com.monetware.threadlocal.ThreadLocalManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import static com.monetware.rctexperiment.business.pojo.constant.RctTaskConstant.*;


/**
 * @description: RctTaskUserService
 * @author: 彭于晏
 * @create: 2020-10-15 13:37
 **/
@Service
public class RctUserTaskService {
    @Autowired
    private RctUserTaskMapper rctUserTaskMapper;
    @Autowired
    private RctTaskMapper taskMapper;
    @Autowired
    private UserTaskManageService userTaskManageService;
//==========================================hs begin=============================================

    /**
     *再用户开始实验的时候首先判断用户是否又未完成的实验
     * @param rctUserTaskVo
     * @return
     */
    public RctUserTaskDTO ifBegin(IfBeginVO rctUserTaskVo){
        //首先通过实验id和用户id去实验用户表中去查询 用户未完成的实验
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setUserId(ThreadLocalManager.getUserId());
        rctUserTask.setTaskId(rctUserTaskVo.getTaskId());
        rctUserTask.setStatus(2);
        List<RctUserTask> rctUserTask1 = rctUserTaskMapper.select(rctUserTask);
        if(rctUserTask1!=null&&rctUserTask1.size()>0){
            RctUserTask rctUserTask2 = rctUserTask1.get(0);
            RctUserTaskDTO rctUserTaskDTO=new RctUserTaskDTO();
            BeanUtils.copyProperties(rctUserTask2,rctUserTaskDTO);
            //保存当前用户实验的ID
            return rctUserTaskDTO;
//            return new ResultData<>(0, "未完成界面跳转到详细页面", rctUserTaskDTO);
        }
        return null;
//        return new ResultData<>(1,"当前实验未开始");
    }
    /**
     * 开始实验
     * @return
     */
    public Integer beginRctTask(BeginRctTaskVO rctUserTaskVo){
        //这个i代表获取数据库自动递增的实验用户id用于返回给前端
        Integer i=1;
        Example example=new Example(RctUserTask.class);
        example.orderBy("id").desc();
        //第一次开始实验添加成功之后获取用户实验表中的一些相关信息。
        List<RctUserTask> rctUserTasks1 = rctUserTaskMapper.selectByExample(example);
        if(rctUserTasks1!=null&&rctUserTasks1.size()!=0){
            i=rctUserTasks1.get(0).getId()+1;
        }
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(i);
        rctUserTask.setUserId(rctUserTaskVo.getUserId());
        rctUserTask.setTaskId(rctUserTaskVo.getTaskId());
        rctUserTask.setFinishStepNum(STEP1);
        rctUserTask.setStatus(2);
        rctUserTask.setBeginTime(new Date());
        rctUserTaskMapper.insertSelective(rctUserTask);

        //实验人数加1
        RctTask rctTask=new RctTask();
        rctTask.setId(rctUserTaskVo.getTaskId());
        RctTask rctTask1 = taskMapper.selectOne(rctTask);
        Integer viewNum = rctTask1.getViewNum();
        if(viewNum==null){
            viewNum=1;
        }else{
            viewNum++;
        }
        rctTask.setViewNum(viewNum);
        taskMapper.updateByPrimaryKeySelective(rctTask);
       return i;
    }

    /**
     *插入实验目的与工具
     * @param rctUserTaskVo
     * @return
     */
    public Integer purposeAndTools(PurposeAndToolsVO rctUserTaskVo){
        CommonIdParam commonVO=new CommonIdParam();
        commonVO.setId(rctUserTaskVo.getId());
        userTaskManageService.nextStep(commonVO);
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(rctUserTaskVo.getId());
        rctUserTask.setMeasureTool(rctUserTaskVo.getMeasureTool());
        rctUserTask.setMeasureDimension(rctUserTaskVo.getMeasureDimension());
        rctUserTask.setTaskTitle(rctUserTaskVo.getTaskTitle());
        rctUserTask.setTaskPurpose(rctUserTaskVo.getTaskPurpose());
//        rctUserTask.setFinishStepNum(STEP2);
        return rctUserTaskMapper.updateByPrimaryKeySelective(rctUserTask);
    }

    /**
     * 实验注册
     * @return
     */
    public Integer ExperimentalRegiste(ExperimentalRegisteVO rctUserTaskVo){
        CommonIdParam commonVO=new CommonIdParam();
        commonVO.setId(rctUserTaskVo.getId());
        userTaskManageService.nextStep(commonVO);
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setRegisterTitle(rctUserTaskVo.getRegisterTitle());
        rctUserTask.setRegisterTime(rctUserTaskVo.getRegisterTime());
        rctUserTask.setRegisterNum(rctUserTaskVo.getRegisterNum());
        rctUserTask.setRegisterStatus(rctUserTaskVo.getRegisterStatus());
        rctUserTask.setRegisterResult(rctUserTaskVo.getRegisterResult());
//        rctUserTask.setFinishStepNum(STEP3);
        rctUserTask.setId(rctUserTaskVo.getId());
       return rctUserTaskMapper.updateByPrimaryKeySelective(rctUserTask);

    }

    /**
     * 获取实验注册号
     * @return
     */
    public String getNewRegisterNum(){
        //i代表实验注册号
        String i="ChiCTR2000030001";
        Example example=new Example(RctUserTask.class);
        example.orderBy("registerNum").desc();
        List<RctUserTask> rctUserTasks = rctUserTaskMapper.selectByExample(example);
        if(rctUserTasks!=null&&rctUserTasks.size()>0){
            String registerNum=rctUserTasks.get(0).getRegisterNum();
            if(registerNum!=null){
                String r = "ChiCTR";
                String r1=registerNum.substring(registerNum.lastIndexOf("R")+1,registerNum.length());
                Integer integer = Integer.valueOf(r1);
                i=r+(integer+1);
            }

        }
        return i;
    }

    /**
     * 添加讨论和 结论
     * @param rctUserTaskVo
     * @return
     */
    public Integer discussAndConclus(DiscussAndConclusVO rctUserTaskVo){
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(rctUserTaskVo.getId());
        rctUserTask.setDiscuss(rctUserTaskVo.getDiscuss());
        rctUserTask.setConclusion(rctUserTaskVo.getConclusion());
       return rctUserTaskMapper.updateByPrimaryKeySelective(rctUserTask);

    }

    /**
     * 获取当前步骤
     * @param rctUserTaskVo
     * @return
     */
    public Integer getCurrentStep(RctUserTaskVO rctUserTaskVo){
        RctUserTask rctUserTask=new RctUserTask();
        //这判断用户是否在开始实验直接跳转其他步骤
        rctUserTask.setId(rctUserTaskVo.getId());
        RctUserTask rctUserTask1 = rctUserTaskMapper.selectOne(rctUserTask);
        return rctUserTask1.getFinishStepNum();
    }

    /**
     * 最后一部保存流程图
     * @param rctUserTaskVo
     * @return
     */
    public Integer finallStep(CommonVO rctUserTaskVo){
        RctUserTask rctUserTask=new RctUserTask();
        //这判断用户是否在开始实验直接跳转其他步骤
        rctUserTask.setId(rctUserTaskVo.getId());
        rctUserTask.setStatus(1);
        rctUserTask.setEndTime(new Date());
        return rctUserTaskMapper.updateByPrimaryKeySelective(rctUserTask);

    }

    /**
     * 获取当前用户实验详情
     * @param rctUserTaskVo
     * @return
     */
    public RctUserTaskDTO getUserTask(CommonVO rctUserTaskVo){
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(rctUserTaskVo.getId());
        RctUserTask rctUserTask1 = rctUserTaskMapper.selectOne(rctUserTask);
        RctUserTaskDTO rctUserTaskDTO=new RctUserTaskDTO();
        BeanUtils.copyProperties(rctUserTask1,rctUserTaskDTO);
        return rctUserTaskDTO;
    }

    /**
     * 删除用户实验
     * @param rctUserTaskVo
     * @return
     */
    public Integer delUserTask(CommonVO rctUserTaskVo){
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(rctUserTaskVo.getId());
        rctUserTask.setStatus(3);
        rctUserTaskMapper.updateByPrimaryKeySelective(rctUserTask);
        return 0;
    }

    /**
     * 获取word地址
     * @param rctUserTaskVo
     * @return
     */
    public String getWordUrl(CommonVO rctUserTaskVo) {
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(rctUserTaskVo.getId());
        RctUserTask rctUserTask1 = rctUserTaskMapper.selectOne(rctUserTask);
        String url=rctUserTask1.getResultFileUrl();
        return url;
    }

    public DiscussAndConclusDTO getDiscussAndConclus(CommonVO commonVO){
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(commonVO.getId());
        RctUserTask rctUserTask1 = rctUserTaskMapper.selectOne(rctUserTask);
        DiscussAndConclusDTO discussAndConclusDTO = new DiscussAndConclusDTO();
        BeanUtils.copyProperties(rctUserTask1,discussAndConclusDTO);
        return discussAndConclusDTO;
    }
//=================================================hs end===========================================
}
