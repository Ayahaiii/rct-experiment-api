package com.monetware.rctexperiment.business.pojo.dto.manage;

import lombok.Data;

/**
 * @description: GroupResultDTO
 * @author: 彭于晏
 * @create: 2020-10-26 17:16
 **/
@Data
public class GroupResultDTO {
    /**
     * 结果
     */
    private Integer groupResult;

    /**
     * 完成步骤序号
     */
    private  Integer finishStepNum;
}
