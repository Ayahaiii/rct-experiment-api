package com.monetware.rctexperiment.business.pojo.dto.usertask;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @description: RctTaskDetailDTO
 * @author: 彭于晏
 * @create: 2020-10-22 17:41
 **/
@Data
public class RctTaskDetailDTO {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 实验名称
     */
    private String name;
    /**
     *  cover_img实验封面
     */
    private String coverImg;
    /**
     * view_num		实验人数
     */
    private Integer viewNum;
    /**
     * index_flash		首页动画
     */
    private String indexFlash;
    /**
     *     digest		实验简介
     */
    private String digest;
}
