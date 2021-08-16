package com.monetware.rctexperiment.business.pojo.vo.task;

import com.monetware.rctexperiment.system.base.PageParam;
import lombok.Data;

/**
 * @description: RctTaskVo
 * @author: 彭于晏
 * @create: 2020-10-15 10:52
 **/
@Data
public class RctTaskVO extends PageParam {
    /**
     * 实验id
     */
    private Integer rctTaskId;
    /**
     * 是否更具创建时间排序 0.倒叙1.正序
     */
    private Integer createTimeAsc;
    /**
     * 是否根据实验次数排序 0.倒叙1.正序
     */
    private Integer viewNumAsc;
    /**
     * 分组结果通知动漫
     */
    private Integer groupResultNoticeFlash;
}
