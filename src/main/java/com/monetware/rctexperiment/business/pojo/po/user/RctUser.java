package com.monetware.rctexperiment.business.pojo.po.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description: RctUser
 * @author: 彭于晏
 * @create: 2020-10-14 15:24
 **/
@Data
@Table(name="rct_user")
public class RctUser {
    /**
     * id(锐研用户id)
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * username		用户名
     */
    private String username;
    /**
     * last_login_time	最后一次登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    /**
     * create_time	创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
