package com.monetware.rctexperiment.business.pojo.vo.task;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @description: BashToImgVo
 * @author: 彭于晏
 * @create: 2020-10-16 10:32
 **/
@Data
@ApiModel("图片参数")
public class BaseToImgVO{
    private Integer userTaskId;
    private String imgUrl;
}
