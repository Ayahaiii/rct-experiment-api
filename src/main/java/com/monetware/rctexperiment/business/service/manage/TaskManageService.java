package com.monetware.rctexperiment.business.service.manage;

import com.monetware.rctexperiment.business.dao.RctTaskMapper;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/16 4:36 下午
 * @description RCT后台实验管理相关方法
 */
@Slf4j
@Service
public class TaskManageService {

    @Autowired
    RctTaskMapper rctTaskMapper;

    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 获取实验选点动画URL
     * @Date 4:38 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String 实验选点动画URL
     **/
    public String getScreenFlash(CommonIdParam param) {
        // 获取实验选点动画URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getScreenFlash();
    }

    /**
     * @Author budi
     * @Description 获取签署同意书动画URL
     * @Date 4:44 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getConsentFormFlash(CommonIdParam param) {
        // 获取签署同意书动画URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getConsentFormFlash();
    }

    /**
     * @Author budi
     * @Description 获取测量数据动画URL
     * @Date 4:47 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getMetricalDataFlash(CommonIdParam param) {
        // 获取测量数据动画URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getMetricalDataFlash();
    }

    /**
     * @Author budi
     * @Description 获取随机分组动画URL
     * @Date 4:47 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getRandomGroupFlash(CommonIdParam param) {
        // 获取随机分组动画URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getRandomGroupFlash();
    }

    /**
     * @Author budi
     * @Description 获取分组结果通知动画1URL
     * @Date 4:49 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getGroupResultNotice1Flash(CommonIdParam param) {
        // 获取分组结果通知动画1URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getGroupResultNotice1Flash();
    }

    /**
     * @Author budi
     * @Description 获取分组结果通知动画2URL
     * @Date 4:49 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getGroupResultNotice2Flash(CommonIdParam param) {
        // 获取分组结果通知动画2URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getGroupResultNotice2Flash();
    }

    /**
     * @Author budi
     * @Description 获取分组结果通知动画3URL
     * @Date 4:49 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getGroupResultNotice3Flash(CommonIdParam param) {
        // 获取分组结果通知动画3URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getGroupResultNotice3Flash();
    }

    /**
     * @Author budi
     * @Description 获取第一次随访动画URL
     * @Date 4:47 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getFirstAccessFlash(CommonIdParam param) {
        // 获取第一次随访动画URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getFirstAccessFlash();
    }

    /**
     * @Author budi
     * @Description 获取第二次随访动画URL
     * @Date 4:47 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String
     **/
    public String getSecondAccessFlash(CommonIdParam param) {
        // 获取第二次随访动画URL
        RctTask task = new RctTask();
        task.setId(param.getId());
        task = rctTaskMapper.selectOne(task);
        return task.getSecondAccessFlash();
    }

    // =========================== budi End ===========================

}
