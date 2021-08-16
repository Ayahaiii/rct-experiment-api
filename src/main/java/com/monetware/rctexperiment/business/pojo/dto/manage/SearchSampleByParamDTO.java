package com.monetware.rctexperiment.business.pojo.dto.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 2:53 下午
 * @description 条件查询样本信息返回DTO
 */
@Data
public class SearchSampleByParamDTO {

    /**
     * id
     */
    private Integer id;

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
