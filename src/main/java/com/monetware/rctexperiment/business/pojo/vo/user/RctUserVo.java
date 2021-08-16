package com.monetware.rctexperiment.business.pojo.vo.user;

import lombok.Data;

/**
 * @description: RctUserVo
 * @author: 彭于晏
 * @create: 2020-10-15 14:34
 **/
@Data
public class RctUserVo {
    /**
     * 用户id
     */
    private Integer ringId;
    /**
     * 用户名
     */
    private String userName;
}
