package com.monetware.rctexperiment.business.pojo.vo.manage;

import com.monetware.rctexperiment.system.base.PageParam;
import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 2:59 下午
 * @description 条件查询样本传入VO
 */
@Data
public class SearchSampleByParamVO extends PageParam {
    /**
     * 当前用户实验id
     */
    private Integer usetTaskId;
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
