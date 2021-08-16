package com.monetware.rctexperiment.business.pojo.dto.manage;

import lombok.Data;

import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/23 1:11 下午
 * @description 分组结果查询返回DTO
 */
@Data
public class SearchGroupResultDTO {

    /**
     * 完成步骤序号
     */
    private  Integer finishStepNum;

    /**
     * PCIT实验组实验样本列表
     */
    private List<SearchUserTaskSampleByParamDTO> pcitList;

    /**
     * TAU对照组实验样本列表
     */
    private List<SearchUserTaskSampleByParamDTO> tauList;

}
