package com.monetware.rctexperiment.business.pojo.po.sample;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: RctUserTaskSample 用户实验样本表
 * @author: 彭于晏
 * @create: 2020-10-14 16:02
 **/
@Data
@Table(name="rct_user_task_sample")
public class RctUserTaskSample {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 用户实验id
     */
    private Integer userTaskId;
    /**
     * 样本id
     */
    private Integer sampleId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex	;
    /**
     * 年龄
     */
    private Integer age	;
    /**
     * 家庭状况（1:正常，2:单亲，3:孤儿）
     */
    private Integer familyStatus;
    /**
     * 地址
     */
    private String address;
    /**
     * 是否同意（1:是，2:否）
     */
    private Integer ifAgree;
    /**
     * 测量得分
     */
    private Integer measurementScore;
    /**
     * 项目分组（1:PCIT,2:TAU）
     */
    private Integer groupType;
    /**
     * 初次随访分数（0:失访）
     */
    private Integer firstAccessScore;
    /**
     * 第二次次随访分数（0:失访）
     */
    private Integer secondAccessScore;
}
