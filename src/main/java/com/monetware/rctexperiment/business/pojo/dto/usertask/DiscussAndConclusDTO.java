package com.monetware.rctexperiment.business.pojo.dto.usertask;

import lombok.Data;

/**
 * @description: DiscussAndConclusDTO
 * @author: 彭于晏
 * @create: 2020-10-23 11:22
 **/
@Data
public class DiscussAndConclusDTO {
    /**
     * 讨论
     */
    private String discuss;
    /**
     * 结论
     */
    private String  conclusion;
    /**
     * 完成步骤序号
     */
    private  Integer finishStepNum;
}
