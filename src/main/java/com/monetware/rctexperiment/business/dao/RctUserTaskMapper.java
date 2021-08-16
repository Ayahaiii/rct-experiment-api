package com.monetware.rctexperiment.business.dao;

import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import com.monetware.rctexperiment.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: 彭于晏
 * @create: 2020-10-14 16:33
 **/
@Mapper
@Repository
public interface RctUserTaskMapper extends MyMapper<RctUserTask> {
}
