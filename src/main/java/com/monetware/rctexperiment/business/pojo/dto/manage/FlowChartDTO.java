package com.monetware.rctexperiment.business.pojo.dto.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/19 11:09 上午
 * @description 实验流程图信息返回DTO
 */
@Data
public class FlowChartDTO {
    /**
     * 完成步骤序号
     */
    private  Integer finishStepNum;
    /**
     * 纳入/排除出后纳入样本数量
     */
    private Integer includeNum;

    /**
     * 签署知情同意书样本数量
     */
    private Integer agreeNum;

    /**
     * PCIT组样本数量
     */
    private Integer pcitNum;

    /**
     * PCIT组第一次随访样本数量
     */
    private Integer pcitFirstAccessNum;

    /**
     * PCIT组第二次随访样本数量
     */
    private Integer pcitSecondAccessNum;

    /**
     * TAU组样本数量
     */
    private Integer tauNum;

    /**
     * TAU组第一次随访样本数量
     */
    private Integer tauFirstAccessNum;

    /**
     * TAU组第二次随访样本数量
     */
    private Integer tauSecondAccessNum;

}
