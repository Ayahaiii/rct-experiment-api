package com.monetware.rctexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/16 11:56 上午
 * @description 批量修改用户实验样本传入VO
 */
@Data
public class UpdateUserTaskSampleListVO {

    /**
     * 用户实验id
     */
    private Integer userTaskId;

    /**
     * 实验id
     */
    private Integer taskId;

}
