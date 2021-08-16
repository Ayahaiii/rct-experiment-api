package com.monetware.rctexperiment.business.pojo.vo.task;

import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import lombok.Data;

/**
 * @description: RctUserTaskVo
 * @author: 彭于晏
 * @create: 2020-10-15 13:31
 **/
@Data
public class RctUserTaskVO extends RctUserTask {
    /**
     * 步骤
     */
    private Integer stepNum;

}
