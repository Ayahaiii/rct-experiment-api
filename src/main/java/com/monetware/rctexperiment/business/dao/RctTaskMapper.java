package com.monetware.rctexperiment.business.dao;

import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: 彭于晏
 * @create: 2020-10-14 16:32
 **/
@Mapper
@Repository
public interface RctTaskMapper extends MyMapper<RctTask> {
}
