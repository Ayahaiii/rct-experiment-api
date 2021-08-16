package com.monetware.rctexperiment.business.pojo.vo.task;

import lombok.Data;

/**
 * @description: PurposeAndTools
 * @author: 彭于晏
 * @create: 2020-10-21 20:00
 **/
@Data
public class PurposeAndToolsVO {
    /**
     * 用户实验id
     */
    private Integer id;

    /**
     * 测量工具
     */
    private String  measureTool;
    /**
     * 测量维度
     */
    private String measureDimension;
    /**
     * task_title		实验标题
     */
    private String taskTitle;
    /**
     * task_purpose	目的描述
     */
    private String taskPurpose;
}