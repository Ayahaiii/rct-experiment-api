package com.monetware.rctexperiment.business.pojo.vo.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description: ExperimentalRegisteVO
 * @author: 彭于晏
 * @create: 2020-10-21 20:09
 **/
@Data
public class ExperimentalRegisteVO {
    /**
     * 用户实验id
     */
    private Integer id;

    /**
     * 注册题目
     */
    private String registerTitle;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 注册号码
     */
    private String registerNum	;
    /**
     * 注册状态 1已完成 2未完成
     */
    private Integer registerStatus;
    /**
     * 注册结果 1为通过 2为未通过
     */
    private Integer registerResult;

}
