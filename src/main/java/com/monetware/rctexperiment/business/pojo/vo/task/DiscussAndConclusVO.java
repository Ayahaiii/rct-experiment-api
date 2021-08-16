package com.monetware.rctexperiment.business.pojo.vo.task;

import lombok.Data;

/**
 * @description: DiscussAndConclusVO
 * @author: 彭于晏
 * @create: 2020-10-21 20:13
 **/
@Data
public class DiscussAndConclusVO {

    private Integer id;

    /**
     * 讨论
     */
    private String discuss;
    /**
     * 结论
     */
    private String  conclusion;
}
