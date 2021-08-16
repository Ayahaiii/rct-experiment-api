package com.monetware.rctexperiment.business.service.rtc;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monetware.rctexperiment.business.dao.RctTaskMapper;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctTaskDTO;
import com.monetware.rctexperiment.business.pojo.dto.usertask.RctTaskDetailDTO;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.business.pojo.vo.task.RctTaskVO;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.base.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

//import static com.monetware.rctexperiment.business.pojo.constant.RctTaskContant.*;

/**
 * @description: RctTaskService
 * @author: 彭于晏
 * @create: 2020-10-15 10:46
 **/
@Service
public class RctTaskService {
    @Autowired
    private RctTaskMapper rctTaskMapper;

    /**
     * 获取实验
     * @param rctTaskVo
     * @return
     */
    public PageList<RctTaskDTO>getListRctTaskList(RctTaskVO rctTaskVo){
        Page page = PageHelper.startPage(rctTaskVo.getPageNum(), rctTaskVo.getPageSize());
        Example example=new Example(RctTask.class);
        //判断排序规制
        if(rctTaskVo.getCreateTimeAsc()==0){
            example.orderBy("createTime").desc();
        }else if(rctTaskVo.getCreateTimeAsc()==1){
            example.orderBy("createTime").asc();
        }
        if(rctTaskVo.getViewNumAsc()==0){
            example.orderBy("viewNum").desc();
        }else if(rctTaskVo.getViewNumAsc()==1){
            example.orderBy("viewNum").asc();
        }
        rctTaskMapper.selectByExample(example);
        PageList<RctTaskDTO> pageList=new PageList<>(page);
        return pageList;
    }

    /**
     * 查询单个实验得详细信息
     * @param rctTaskVo
     * @return
     */
    public RctTaskDetailDTO getRctTask(RctTaskVO rctTaskVo){
        RctTask rctTask=new RctTask();
        rctTask.setId(rctTaskVo.getRctTaskId());
        RctTask rctTask1 = rctTaskMapper.selectOne(rctTask);
        RctTaskDetailDTO rctTaskDTO = new RctTaskDetailDTO();
        BeanUtils.copyProperties(rctTask1,rctTaskDTO);
        return rctTaskDTO;
    }


//    /**
//     * 获取分组结果通知播放视频地址
//     * @return
//     */
//    public ResultData groupResultNoticeFlash(RctTaskVo rctTaskVo){
//        Integer flash = rctTaskVo.getGroupResultNoticeFlash();
//        RctTask rctTask=new RctTask();
//        rctTask.setId(rctTaskVo.getRctTaskId());
//        RctTask rctTask1 = rctTaskMapper.selectOne(rctTask);
//        String url="";
//        switch (flash){
//            case GROUPRESULTONE:
//                url= rctTask1.getGroupResultNotice1Flash();
//                break;
//            case GROUPRESULTTWO:
//                url= rctTask1.getGroupResultNotice2Flash();
//                break;
//            case GROUPRESULTTHREE:
//                url= rctTask1.getGroupResultNotice3Flash();
//                break;
//            default:
//                    break;
//        }
//        return new ResultData(0,"获取成功",url);
//    }


}
