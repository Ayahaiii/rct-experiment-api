package com.monetware.rctexperiment.business.pojo.vo.common;

import lombok.Data;

/**
 * @description: PageVo
 * @author: 彭于晏
 * @create: 2020-10-19 13:34
 **/
@Data
public class PageVO {
    /**
     * 当前页
     */
    private Integer pageNum=1;
    /**
     * 每页条数
     */
    private Integer pageSize=10;
}
