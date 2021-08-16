package com.monetware.rctexperiment.business.pojo.vo.manage;

import com.monetware.rctexperiment.system.base.PageParam;
import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 6:39 下午
 * @description 条件查询实验样本传入VO
 */
@Data
public class SearchUserTaskSampleByParamVO extends PageParam {

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

    /**
     * 是否同意：
     * 1.是
     * 2.否
     */
    private Integer ifAgree;

    /**
     * 测量得分
     */
    private Integer measurementScore;

    /**
     * 项目分组：
     * 1.PCIT
     * 2.TAU
     */
    private Integer groupType;

    /**
     * 初次随访分数
     * 0.失访
     */
    private Integer firstAccessScore;

    /**
     * 二次随访分数
     * 0.失访
     */
    private Integer secondAccessScore;

}
