package com.monetware.rctexperiment.business.pojo.constant;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/16 2:33 下午
 * @description 用户实验样本分组相关常量
 */
public class GroupTypeConstant {

    /**
     * 项目分组：
     * 1.PCIT
     * 2.TAU
     */
    public static final Integer PCIT = 1;
    public static final Integer TAU = 2;

    /**
     * 简单随机化分组概率
     * 随机分为两组：实验组PCIT、对照组TAU
     */
    public static final Double SIMPLE_RANDOM_POSSIBILITY = 0.5;

}
