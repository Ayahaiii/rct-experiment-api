package com.monetware.rctexperiment.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monetware.rctexperiment.business.dao.RctSampleMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskSampleMapper;
import com.monetware.rctexperiment.business.pojo.dto.manage.SearchSampleByParamDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.SampleListVO;
import com.monetware.rctexperiment.business.pojo.po.sample.RctSample;
import com.monetware.rctexperiment.business.pojo.po.sample.RctUserTaskSample;
import com.monetware.rctexperiment.business.pojo.vo.manage.SearchSampleByParamVO;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.base.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 2:46 下午
 * @description RCT后台样本管理相关方法
 */
@Slf4j
@Service
public class SampleManageService {

    @Autowired
    RctSampleMapper rctSampleMapper;
    @Autowired
    RctUserTaskSampleMapper rctUserTaskSampleMapper;
    @Autowired
    UserTaskManageService userTaskManageService;
    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 条件查询样本信息
     * @Date 3:28 下午 2020/10/15
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.PageList<com.monetware.rctexperiment.business.pojo.dto.manage.SearchSampleByParamDTO>
     **/
    public PageList<SearchSampleByParamDTO> searchSampleByParam(SearchSampleByParamVO param) {
        // 条件查询样本信息
        RctUserTaskSample rctUserTaskSample=new RctUserTaskSample();
        rctUserTaskSample.setUserTaskId(param.getUsetTaskId());
        List<RctUserTaskSample> select = rctUserTaskSampleMapper.select(rctUserTaskSample);
        //获取当前样本存在的样本
        List<Integer>list=new ArrayList<>();
        for (RctUserTaskSample userTaskSample : select) {
            if(userTaskSample.getSampleId()==null){
                continue;
            }
            list.add(userTaskSample.getSampleId());
        }
        //创建条件查询
        Page page = new PageHelper().startPage(param.getPageNum(), param.getPageSize());
        Example example=new Example(RctSample.class);
        Example.Criteria criteria = example.createCriteria();
        if(list!=null&&list.size()>0){
            criteria.andNotIn("id",list);
        }
        if(param.getAge()!=null){
            criteria.andEqualTo("age",param.getAge());
        }
        if(param.getSex()!=null){
            criteria.andEqualTo("sex",param.getSex());
        }
        if(param.getFamilyStatus()!=null){
            criteria.andEqualTo("familyStatus",param.getFamilyStatus());
        }
        if(param.getAddress()!=null){
            criteria.andEqualTo("address",param.getAddress());
        }
//        RctSample sample = new RctSample();
//        sample.setAge(param.getAge());
//        sample.setSex(param.getSex());
//        sample.setFamilyStatus(param.getFamilyStatus());
//        sample.setAddress(param.getAddress());
        List<RctSample> rctSampleList = rctSampleMapper.selectByExample(example);
        // 重组返回信息
        List<SearchSampleByParamDTO> result = new ArrayList<>();
        for (RctSample item : rctSampleList) {
            SearchSampleByParamDTO dto = new SearchSampleByParamDTO();
            BeanUtils.copyProperties(item, dto);
            result.add(dto);
        }
        return new PageList<>(page, result);
    }

    // =========================== budi End ===========================

}
