package com.monetware.rctexperiment.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monetware.rctexperiment.business.dao.RctSampleMapper;
import com.monetware.rctexperiment.business.dao.RctTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskSampleMapper;
import com.monetware.rctexperiment.business.pojo.constant.RctTaskConstant;
import com.monetware.rctexperiment.business.pojo.dto.manage.EnterSampleSelectionDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.GroupResultDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.SearchSampleByParamDTO;
import com.monetware.rctexperiment.business.pojo.dto.manage.SearchUserTaskSampleByParamDTO;
import com.monetware.rctexperiment.business.pojo.po.sample.RctSample;
import com.monetware.rctexperiment.business.pojo.po.sample.RctUserTaskSample;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import com.monetware.rctexperiment.business.pojo.vo.common.CommonVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.UpdateUserTaskVO;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import com.monetware.rctexperiment.system.base.ErrorCode;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.exception.ServiceException;
import com.monetware.threadlocal.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 4:12 下午
 * @description 后台用户实验管理相关方法
 */
@Slf4j
@Service
public class UserTaskManageService {

    @Autowired
    RctUserTaskMapper rctUserTaskMapper;

    @Autowired
    RctTaskMapper rctTaskMapper;

    @Autowired
    RctSampleMapper rctSampleMapper;

    @Autowired
    RctUserTaskSampleMapper rctUserTaskSampleMapper;

    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 进入样本选择页面
     * @Date 2:17 下午 2020/10/23
     * @Param [param]
     * @return com.monetware.rctexperiment.business.pojo.dto.manage.EnterSampleSelectionDTO
     **/
    public EnterSampleSelectionDTO enterSampleSelection(CommonIdParam param) {
        EnterSampleSelectionDTO enterSampleSelectionDTO = new EnterSampleSelectionDTO();
        RctUserTask rctUserTask = new RctUserTask();
        rctUserTask.setId(param.getId());
        rctUserTask = rctUserTaskMapper.selectOne(rctUserTask);
        // 回显选择标准
        enterSampleSelectionDTO.setSelectionCriteria(rctUserTask.getSelectionCriteria());
        enterSampleSelectionDTO.setFinishStepNum(rctUserTask.getFinishStepNum());
        // 条件查询样本信息
        Page sampleListPage = new PageHelper().startPage(param.getPageNum(), param.getPageSize());
        RctSample rctSample = new RctSample();
        List<RctSample> rctSampleList = rctSampleMapper.select(rctSample);
        // 重组返回信息
        List<SearchSampleByParamDTO> sampleList = new ArrayList<>();
        for (RctSample item : rctSampleList) {
            SearchSampleByParamDTO dto = new SearchSampleByParamDTO();
            BeanUtils.copyProperties(item, dto);
            sampleList.add(dto);
        }
        enterSampleSelectionDTO.setSampleList(new PageList<>(sampleListPage, sampleList));

        // 条件查询用户实验样本信息
        Page userTaskSampleListPage = new PageHelper().startPage(param.getPageNum(), param.getPageSize());
        RctUserTaskSample rctUserTaskSample = new RctUserTaskSample();
        rctUserTaskSample.setUserTaskId(param.getId());
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(rctUserTaskSample);
        // 重组返回信息
        List<SearchUserTaskSampleByParamDTO> userTaskSampleList = new ArrayList<>();
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            SearchUserTaskSampleByParamDTO dto = new SearchUserTaskSampleByParamDTO();
            BeanUtils.copyProperties(item, dto);
            userTaskSampleList.add(dto);
        }
        enterSampleSelectionDTO.setUserTaskSampleList(new PageList<>(userTaskSampleListPage, userTaskSampleList));

        return enterSampleSelectionDTO;
    }

    /**
     * @Author budi
     * @Description 修改用户实验
     * @Date 4:56 下午 2020/10/15
     * @Param [param]
     * @return java.lang.String 分组结果动画URL
     **/
    public String updateUserTask(UpdateUserTaskVO param) {
        // 判断修改后的用户实验是否已经存在
        RctUserTask rctUserTask = new RctUserTask();
//        rctUserTask.setSelectionCriteria(param.getSelectionCriteria());
//        rctUserTask.setGroupResult(param.getGroupResult());
//        rctUserTask = rctUserTaskMapper.selectOne(rctUserTask);
//        if (rctUserTask != null && !rctUserTask.getId().equals(param.getId())) {
//            throw new ServiceException(ErrorCode.USER_TASK_EXIT);
//        }
        // 修改用户实验信息
        rctUserTask = rctUserTaskMapper.selectByPrimaryKey(param.getId());
//        rctUserTask.setSelectionCriteria(param.getSelectionCriteria());
        rctUserTask.setGroupResult(param.getGroupResult());
        rctUserTaskMapper.updateByPrimaryKey(rctUserTask);
        // 获取分组结果动画URL
        RctTask task = new RctTask();
        task.setId(param.getTaskId());
        task = rctTaskMapper.selectOne(task);
        if (param.getGroupResult() == 1) {
            return task.getGroupResultNotice1Flash();
        } else if (param.getGroupResult() == 2) {
            return task.getGroupResultNotice2Flash();
        } else if (param.getGroupResult() == 3) {
            return task.getGroupResultNotice3Flash();
        } else {
            throw new ServiceException(ErrorCode.USER_TASK_GROUP_RESULT_NULL);
        }
    }

    /**
     * @Author budi
     * @Description 下一步
     * @Date 1:47 下午 2020/10/22
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer nextStep(CommonIdParam param) {
        // 根据传入的用户实验id找到用户实验对象
        RctUserTask rctUserTask = new RctUserTask();
        rctUserTask.setId(param.getId());
        rctUserTask = rctUserTaskMapper.selectByPrimaryKey(rctUserTask);
        switch (rctUserTask.getFinishStepNum()) {
            case 1:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP2);
                break;
            case 2:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP3);
                break;
            case 3:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP4);
                break;
            case 4:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP5);
                break;
            case 5:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP6);
                break;
            case 6:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP7);
                break;
            case 7:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP8);
                break;
            case 8:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP9);
                break;
            case 9:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP10);
                break;
            case 10:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP11);
                break;
            case 11:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP12);
                break;
            case 12:
                rctUserTask.setFinishStepNum(RctTaskConstant.STEP13);
                break;
        }
        return rctUserTaskMapper.updateByPrimaryKey(rctUserTask);
    }

    /**
     *样本实验保存
     * @param userTaskVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer sampleNext(UpdateUserTaskVO userTaskVO) {
        CommonIdParam param=new CommonIdParam();
        param.setId(userTaskVO.getId());
        nextStep(param);
        RctUserTask rctUserTask = new RctUserTask();
        rctUserTask.setId(param.getId());
        rctUserTask.setSelectionCriteria(userTaskVO.getSelectionCriteria());
       return rctUserTaskMapper.updateByPrimaryKeySelective(rctUserTask);
    }


    public GroupResultDTO groupResult(CommonVO commonVO){
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(commonVO.getId());
        RctUserTask rctUserTask1 = rctUserTaskMapper.selectByPrimaryKey(rctUserTask);
        GroupResultDTO groupResultDTO=new GroupResultDTO();
        groupResultDTO.setFinishStepNum(rctUserTask1.getFinishStepNum());
        groupResultDTO.setGroupResult(rctUserTask1.getGroupResult());
        return groupResultDTO;
    }
    // =========================== budi End ===========================
    
}
