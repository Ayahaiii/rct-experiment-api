package com.monetware.rctexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 3:52 下午
 * @description 修改用户实验传入VO
 */
@Data
public class UpdateUserTaskVO {

    /**
     * 用户实验id
     */
    private Integer id;

    /**
     * 实验id
     */
    private Integer taskId;

    /**
     * 选择标准
     */
    private String selectionCriteria;

    /**
     * 分组结果：
     * 1.召集所有样本
     * 2.发送不透光
     * 3.治疗师写信通知样本
     */
    private Integer groupResult;

}
