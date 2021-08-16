package com.monetware.rctexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 7:53 下午
 * @description 添加用户实验样本传入VO
 */
@Data
public class AddUserTaskSampleVO {

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
     * 性别：
     * 1.男
     * 2.女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 家庭状况：
     * 1.正常
     * 2.单亲
     * 3.孤儿
     */
    private Integer familyStatus;

    /**
     * 地址
     */
    private String address;

}
