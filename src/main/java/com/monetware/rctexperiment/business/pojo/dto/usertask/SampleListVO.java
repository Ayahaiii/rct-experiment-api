package com.monetware.rctexperiment.business.pojo.dto.usertask;

import lombok.Data;

import java.util.List;

/**
 * @description: SampleList
 * @author: 彭于晏
 * @create: 2020-10-22 20:09
 **/
@Data
public class SampleListVO {
    private List<Integer> listSample;
    private Integer userTaskId;
}
