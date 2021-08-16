package com.monetware.rctexperiment.business.pojo.po.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description: RctUserTask 用户实验表
 * @author: 彭于晏
 * @create: 2020-10-14 15:41
 **/
@Data
@Table(name="rct_user_task")
public class RctUserTask {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 实验id
     */
    private Integer taskId;
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
    /**
     * 选择标准
     */
    private String selectionCriteria;
    /**
     * 分组结果（1:召集所有样本，2发送不透光:，3:治疗师写信通知样本）
     */
    private Integer groupResult;
    /**
     * 讨论
     */
    private String discuss;
    /**
     * 结论
     */
    private String  conclusion;
    /**
     * 成果文件地址
     */
    private String resultFileUrl;
    /**
     * 完成步骤序号
     */
    private  Integer finishStepNum;
    /**
     *流程图
     */
    private String flowsheetUrl;

    /**
     * flowsheet_real_url
     */
    private String flowsheetRealUrl;
    /**
     * 状态 1为已完成2为未完成3为删除状态
     */
    private Integer status;
    /**
     * 结束时间
     */
    private Date beginTime;
    /**
     * 开始时间
     */
    private Date endTime;
}
