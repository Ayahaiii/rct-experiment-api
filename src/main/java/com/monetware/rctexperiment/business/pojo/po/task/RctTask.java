package com.monetware.rctexperiment.business.pojo.po.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description: RctTask 实验表
 * @author: 彭于晏
 * @create: 2020-10-14 15:31
 **/
@Data
@Table(name="rct_task")
public class RctTask {

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
    /**
     * screen_flash	实验选点动画
     */
   private String screenFlash;
    /**
     * consent_form_flash	签署同意书动画
     */
   private String consentFormFlash;
    /**
     * metrical_data_flash	测量数据动画
     */
   private String metricalDataFlash;
    /**
     * random_group_flash	随机分组动画
     */
   private String randomGroupFlash;
    /**
     * group_result_notice1_flash	分组结果通知动画1
     */
   private String groupResultNotice1Flash;
    /**
     *  group_result_notice2_flash	分组结果通知动画2
     */
   private String groupResultNotice2Flash;
    /**
     *  group_result_notice3_flash	分组结果通知动画3
     */
   private String groupResultNotice3Flash;
    /**
     * first_access_flash	第一次访问动画
     */
   private String firstAccessFlash;
    /**
     * second_access_flash	第二次访问动画
     */
   private String secondAccessFlash;
    /**
     * create_time		创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
