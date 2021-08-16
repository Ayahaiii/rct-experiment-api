package com.monetware.rctexperiment.business.pojo.constant;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/19 10:34 上午
 * @description 二次随访分数相关常量
 */
public class SecondAccessScoreConstant {
    
    /**
     * 失访得分
     */
    public static final Integer SECOND_ACCESS_SCORE_NULL = 0;

    /**
     * 失访概率
     */
    public static final Double SECOND_ACCESS_SCORE_NULL_POSSIBILITY = 0.1;

    /**
     * ECBI问题频率得分最小值
     */
    public static final Integer SECOND_ACCESS_SCORE_FREQUENCY_MIN = 100;

    /**
     * ECBI问题频率得分最大值
     */
    public static final Integer SECOND_ACCESS_SCORE_FREQUENCY_MAX = 160;

    /**
     * ECBI问题强度得分最小值
     */
    public static final Integer SECOND_ACCESS_SCORE_INTENSITY_MIN = 40;

    /**
     * ECBI问题强度得分最大值
     */
    public static final Integer SECOND_ACCESS_SCORE_INTENSITY_MAX = 75;

}
