package com.monetware.rctexperiment.business.pojo.dto.manage;

import com.monetware.rctexperiment.system.base.PageList;
import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/23 1:54 下午
 * @description 进入样本选点页面返回DTO
 */
@Data
public class EnterSampleSelectionDTO {
    /**
     * 当前步骤数
     */
    private  Integer finishStepNum;
    /**
     * 选择标准
     */
    private String selectionCriteria;

    /**
     * 未选中样本列表
     */
    private PageList<SearchSampleByParamDTO> sampleList;

    /**
     * 选中实验样本列表
     */
    private PageList<SearchUserTaskSampleByParamDTO> userTaskSampleList;

}
