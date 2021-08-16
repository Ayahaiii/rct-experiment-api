package com.monetware.rctexperiment.business.pojo.dto.usertask;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description: UserTaskDto
 * @author: 彭于晏
 * @create: 2020-10-20 16:19
 **/
@Data
public class UserTaskDTO {
    /**
     * 实验名称
     */
    private String name;
    /**
     * 实验标题
     */
    private String coverImg;
    /**
     * 用户实验id
     */
    private Integer userTaskId;
    /**
     * 实验id
     */
    private Integer taskId;
    /**
     * '注册题目',
     */
    private String registerTitle;
    /**
     * :注册码,
     */
    private String registerNum;
    /**
     * :注册状态,
     */
    private Integer registerStatus;
    /**
     * :注册结果,
     */
    private Integer registerResult;
    /**
     * :实验步骤
     */
    private Integer finishStepNum;
    /**
     * 1已完成2未完成
     */
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
