package com.monetware.rctexperiment.business.pojo.dto.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/19 1:40 下午
 * @description 结果数据分析信息返回DTO
 */
@Data
public class ResultDataAnalysisDTO {

    /**
     * 结果
     */
    private Integer finishStepNum;
    /**
     * 实验组 基线数据
     * 样本数量 平均值 标准差
     */
    private Integer pcitT0Num;
    private Double pcitT0Mean;
    private Double pcitT0SD;

    /**
     * 实验组 第一次随访
     * 样本数量 平均值 标准差
     */
    private Integer pcitT1Num;
    private Double pcitT1Mean;
    private Double pcitT1SD;

    /**
     * 实验组 第二次随访
     * 样本数量 平均值 标准差
     */
    private Integer pcitT2Num;
    private Double pcitT2Mean;
    private Double pcitT2SD;

    /**
     * 实验组 T0T1变化
     * T值 Cohen's d值
     */
    private Double pcitT0T1T;
    private Double pcitT0T1Cohensd;

    /**
     * 实验组 T0T2变化
     * T值 Cohen's d值
     */
    private Double pcitT0T2T;
    private Double pcitT0T2Cohensd;

    /**
     * 对照组 基线数据
     * 样本数量 平均值 标准差
     */
    private Integer tauT0Num;
    private Double tauT0Mean;
    private Double tauT0SD;

    /**
     * 对照组 第一次随访
     * 样本数量 平均值 标准差
     */
    private Integer tauT1Num;
    private Double tauT1Mean;
    private Double tauT1SD;

    /**
     * 对照组 第二次随访
     * 样本数量 平均值 标准差
     */
    private Integer tauT2Num;
    private Double tauT2Mean;
    private Double tauT2SD;

    /**
     * 对照组 T0T1变化
     * T值 Cohen's d值
     */
    private Double tauT0T1T;
    private Double tauT0T1Cohensd;

    /**
     * 对照组 T0T2变化
     * T值 Cohen's d值
     */
    private Double tauT0T2T;
    private Double tauT0T2Cohensd;

}
