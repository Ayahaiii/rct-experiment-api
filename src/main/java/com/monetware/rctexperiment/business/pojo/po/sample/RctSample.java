package com.monetware.rctexperiment.business.pojo.po.sample;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: RctSample 样本表
 * @author: 彭于晏
 * @create: 2020-10-14 15:58
 **/
@Data
@Table(name="rct_sample")
public class RctSample {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年龄 1代表男2代表女
     */
    private Integer age;
    /**
     * 家庭状况（1:正常，2:单亲，3:孤儿）
     */
    private Integer familyStatus;
    /**
     * 地址
     */
    private String address;
}
