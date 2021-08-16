package com.monetware.rctexperiment.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monetware.rctexperiment.business.dao.RctTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskSampleMapper;
import com.monetware.rctexperiment.business.pojo.constant.*;
import com.monetware.rctexperiment.business.pojo.dto.manage.*;
import com.monetware.rctexperiment.business.pojo.po.sample.RctUserTaskSample;
import com.monetware.rctexperiment.business.pojo.po.task.RctTask;
import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import com.monetware.rctexperiment.business.pojo.vo.manage.AddUserTaskSampleVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.SearchUserTaskSampleByParamVO;
import com.monetware.rctexperiment.business.pojo.vo.manage.UpdateUserTaskSampleListVO;
import com.monetware.rctexperiment.business.pojo.vo.task.RctUserTaskVO;
import com.monetware.rctexperiment.business.service.rtc.RctUserTaskService;
import com.monetware.rctexperiment.system.base.CommonIdParam;
import com.monetware.rctexperiment.system.base.ErrorCode;
import com.monetware.rctexperiment.system.base.PageList;
import com.monetware.rctexperiment.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/15 6:45 下午
 * @description RCT后台实验样本管理相关方法
 */
@Slf4j
@Service
public class UserTaskSampleManageService {

    @Autowired
    RctUserTaskSampleMapper rctUserTaskSampleMapper;

    @Autowired
    RctTaskMapper rctTaskMapper;

    @Autowired
    RctUserTaskMapper rctUserTaskMapper;

    @Autowired
    RctUserTaskService rctUserTaskService;
    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 条件查询用户实验样本信息
     * @Date 7:56 下午 2020/10/15
     * @Param [param]
     * @return com.monetware.rctexperiment.system.base.PageList<com.monetware.rctexperiment.business.pojo.dto.manage.SearchUserTaskSampleByParamDTO>
     **/
    public PageList<SearchUserTaskSampleByParamDTO> searchUserTaskSampleByParam(SearchUserTaskSampleByParamVO param) {
        Page page = new PageHelper().startPage(param.getPageNum(), param.getPageSize());
        // 条件查询样本信息
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        sample.setSampleId(param.getSampleId());
        sample.setName(param.getName());
        sample.setAge(param.getAge());
        sample.setSex(param.getSex());
        sample.setFamilyStatus(param.getFamilyStatus());
        sample.setAddress(param.getAddress());
        sample.setIfAgree(param.getIfAgree());
        sample.setMeasurementScore(param.getMeasurementScore());
        sample.setGroupType(param.getGroupType());
        sample.setFirstAccessScore(param.getFirstAccessScore());
        sample.setSecondAccessScore(param.getSecondAccessScore());
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        // 重组返回信息
        List<SearchUserTaskSampleByParamDTO> result = new ArrayList<>();

        for (RctUserTaskSample item : rctUserTaskSampleList) {
            SearchUserTaskSampleByParamDTO dto = new SearchUserTaskSampleByParamDTO();
            BeanUtils.copyProperties(item, dto);
            result.add(dto);
        }
        return new PageList<>(page, result);
    }

    /**
     * @Author budi
     * @Description 添加用户实验样本
     * @Date 10:18 上午 2020/10/16
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer addUserTaskSample(AddUserTaskSampleVO param) {
        // 判断当前用户实验样本是否已存在
        RctUserTaskSample rctUserTaskSample = new RctUserTaskSample();
        rctUserTaskSample.setUserTaskId(param.getUserTaskId());
        rctUserTaskSample.setSampleId(param.getSampleId());
        rctUserTaskSample.setName(param.getName());
        rctUserTaskSample.setSex(param.getSex());
        rctUserTaskSample.setAge(param.getAge());
        rctUserTaskSample.setFamilyStatus(param.getFamilyStatus());
        rctUserTaskSample.setAddress(param.getAddress());
        rctUserTaskSample = rctUserTaskSampleMapper.selectOne(rctUserTaskSample);
        if (rctUserTaskSample != null) {
            throw new ServiceException(ErrorCode.USER_TASK_SAMPLE_EXIT);
        }
        // 增加用户实验样本
        rctUserTaskSample = new RctUserTaskSample();
        BeanUtils.copyProperties(param, rctUserTaskSample);
        return rctUserTaskSampleMapper.insertSelective(rctUserTaskSample);
    }
    
    /**
     * @Author budi
     * @Description 删除用户实验样本
     * @Date 1:07 下午 2020/10/23
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer deleteUserTaskSample(CommonIdParam param) {
        return rctUserTaskSampleMapper.deleteByPrimaryKey(param.getId());
    }


    /**
     * @Author budi
     * @Description 批量修改用户实验样本（签署知情同意书）
     * @Date 1:32 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String 签署同意书动画URL
     **/
    public String updateUserTaskSampleListIfAgree(UpdateUserTaskSampleListVO param) {
        // 通过传入的用户实验id找到所有的用户实验样本
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        // 随机设置ifAgree，即每个用户实验样本是否同意签署知情同意书，同意的用户控制在80%。
        Random rd = new Random();
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 随机选择一部分用户同意签署知情同意书(AGREE_POSSIBLE = 0.8)
            if (rd.nextDouble() < IfAgreeConstant.AGREE_POSSIBILITY) {
                item.setIfAgree(IfAgreeConstant.IFAGREE_YES);
            } else {
                item.setIfAgree(IfAgreeConstant.IFAGREE_NO);
            }
            rctUserTaskSampleMapper.updateByPrimaryKey(item);
        }
        // 获取签署同意书动画URL
        RctTask task = new RctTask();
        task.setId(param.getTaskId());
        task = rctTaskMapper.selectOne(task);
        return task.getConsentFormFlash();
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（测量基线数据）
     * @Date 2:18 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String 测量数据动画URL
     **/
    public String updateUserTaskSampleListMeasurementScore(UpdateUserTaskSampleListVO param) {
        // 通过传入的用户实验id找到所有签署用户知情同意书的用户实验样本
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        sample.setIfAgree(IfAgreeConstant.IFAGREE_YES);
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        // 根据用户实验选择的测量维度设置测量分数范围
        RctUserTask userTask = new RctUserTask();
        userTask.setId(param.getUserTaskId());
        userTask = rctUserTaskMapper.selectOne(userTask);
        int measurementScoreMin, measurementScoreMax;
        if (userTask.getMeasureDimension() == "ECBI-intensity问题频率得分（母亲视角）" || userTask.getMeasureDimension() == "ECBI-intensity问题频率得分（父亲视角）") {
            measurementScoreMin = MeasurementScoreConstant.MEASUREMENT_SCORE_FREQUENCY_MIN;
            measurementScoreMax = MeasurementScoreConstant.MEASUREMENT_SCORE_FREQUENCY_MAX;
        } else {
            measurementScoreMin = MeasurementScoreConstant.MEASUREMENT_SCORE_INTENSITY_MIN;
            measurementScoreMax = MeasurementScoreConstant.MEASUREMENT_SCORE_INTENSITY_MAX;
        }
        // 随机设置measurementScore，即测量得分，范围控制在
        // MEASUREMENT_SCORE_MIN 到 MEASUREMENT_SCORE_MAX 之间，取整
        Random rd = new Random();
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            item.setMeasurementScore(rd.nextInt(measurementScoreMax-measurementScoreMin)+measurementScoreMin);
            rctUserTaskSampleMapper.updateByPrimaryKey(item);
        }
        // 获取测量数据动画URL
        RctTask task = new RctTask();
        task.setId(param.getTaskId());
        task = rctTaskMapper.selectOne(task);
        return task.getMetricalDataFlash();
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（简单随机化分组）
     * @Date 2:52 下午 2020/10/16
     * @Param [param]
     * @return java.lang.String 随机分组动画URL
     **/
    public String updateUserTaskSampleListGroupType(UpdateUserTaskSampleListVO param) {
        // 通过传入的用户实验id找到所有签署用户知情同意书的用户实验样本
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        sample.setIfAgree(IfAgreeConstant.IFAGREE_YES);
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        // 将用户实验样本随机分为两组：实验组PCIT、对照组TAU
        Random rd = new Random();
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 用户实验样本被分入实验组PCIT或对照组TAU的概率相等（SIMPLE_RANDOM_POSSIBILITY = 50%）
            if (rd.nextDouble() > GroupTypeConstant.SIMPLE_RANDOM_POSSIBILITY) {
                item.setGroupType(GroupTypeConstant.PCIT);
            } else {
                item.setGroupType(GroupTypeConstant.TAU);
            }
            rctUserTaskSampleMapper.updateByPrimaryKey(item);
        }
        // 保证每组至少有2个样本
        int groupNumLimit = 4;
        // 打乱样本
        Collections.shuffle(rctUserTaskSampleList);
        List<RctUserTaskSample> randomList = rctUserTaskSampleList.subList(0, groupNumLimit);
        for (RctUserTaskSample item : randomList) {
            if (groupNumLimit > 0) {
                if (groupNumLimit > 2) {
                    // 实验组
                    item.setGroupType(GroupTypeConstant.PCIT);
                    groupNumLimit--;
                } else {
                    // 对照组
                    item.setGroupType(GroupTypeConstant.TAU);
                    groupNumLimit--;
                }
                rctUserTaskSampleMapper.updateByPrimaryKey(item);
            }
        }
        // 获取随机分组动画URL
        RctTask task = new RctTask();
        task.setId(param.getTaskId());
        task = rctTaskMapper.selectOne(task);
        return task.getRandomGroupFlash();
    }

    /**
     * @Author budi
     * @Description 查询分组结果
     * @Date 1:31 下午 2020/10/23
     * @Param [param]
     * @return com.monetware.rctexperiment.business.pojo.dto.manage.SearchGroupResultDTO
     **/
    public SearchGroupResultDTO searchGroupResult(CommonIdParam param) {
        RctUserTaskVO rctUserTaskVO=new RctUserTaskVO();
        rctUserTaskVO.setId(param.getId());
        Integer currentStep = rctUserTaskService.getCurrentStep(rctUserTaskVO);
        // 查询实验组样本信息
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getId());
        sample.setGroupType(GroupTypeConstant.PCIT);
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        SearchGroupResultDTO searchGroupResultDTO = new SearchGroupResultDTO();
        List<SearchUserTaskSampleByParamDTO> pcitList = new ArrayList<>();
        // 重组返回信息
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            SearchUserTaskSampleByParamDTO dto = new SearchUserTaskSampleByParamDTO();
            BeanUtils.copyProperties(item, dto);
            pcitList.add(dto);
        }
        // 查询对照组样本信息
        sample.setGroupType(GroupTypeConstant.TAU);
        rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        List<SearchUserTaskSampleByParamDTO> tauList = new ArrayList<>();
        // 重组返回信息
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            SearchUserTaskSampleByParamDTO dto = new SearchUserTaskSampleByParamDTO();
            BeanUtils.copyProperties(item, dto);
            tauList.add(dto);
        }
        // 将查询结果添加到返回DTO
        searchGroupResultDTO.setPcitList(pcitList);
        searchGroupResultDTO.setTauList(tauList);
        searchGroupResultDTO.setFinishStepNum(currentStep);

        return searchGroupResultDTO;
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（第一次随访）
     * @Date 10:35 上午 2020/10/19
     * @Param [param]
     * @return java.lang.String
     **/
    @Transactional(rollbackFor = Exception.class)
    public String updateUserTaskSampleListFirstAccessScore(UpdateUserTaskSampleListVO param) {
        // 通过传入的用户实验id找到所有签署用户知情同意书的用户实验样本
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        sample.setIfAgree(IfAgreeConstant.IFAGREE_YES);
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        // 根据用户实验选择的测量维度设置测量分数范围
        RctUserTask userTask = new RctUserTask();
        userTask.setId(param.getUserTaskId());
        userTask = rctUserTaskMapper.selectOne(userTask);
        int firstAccessScoreMin, firstAccessScoreMax;
        if (userTask.getMeasureDimension() == "ECBI-intensity问题频率得分（母亲视角）" || userTask.getMeasureDimension() == "ECBI-intensity问题频率得分（父亲视角）") {
            firstAccessScoreMin = FirstAccessScoreConstant.FIRST_ACCESS_SCORE_FREQUENCY_MIN;
            firstAccessScoreMax = FirstAccessScoreConstant.FIRST_ACCESS_SCORE_FREQUENCY_MAX;
        } else {
            firstAccessScoreMin = FirstAccessScoreConstant.FIRST_ACCESS_SCORE_INTENSITY_MIN;
            firstAccessScoreMax = FirstAccessScoreConstant.FIRST_ACCESS_SCORE_INTENSITY_MAX;
        }
        // 随机选择一部分样本失访，并对于未失访的用户实验样本
        // 随机设置firstAccessScore，即测量得分，范围控制在
        // First_Access_Score_MIN 到 First_Access_Score_MAX 之间，取整
        Random rd = new Random();
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 随机选择一部分用户失访(FIRST_ACCESS_SCORE_NULL_POSSIBILITY = 0.1)
            if (rd.nextDouble() < FirstAccessScoreConstant.FIRST_ACCESS_SCORE_NULL_POSSIBILITY) {
                item.setFirstAccessScore(FirstAccessScoreConstant.FIRST_ACCESS_SCORE_NULL);
            } else {
                item.setFirstAccessScore(rd.nextInt(firstAccessScoreMax - firstAccessScoreMin) + firstAccessScoreMin);
            }
            rctUserTaskSampleMapper.updateByPrimaryKey(item);
        }
        // 获取第一次访问动画URL
        RctTask task = new RctTask();
        task.setId(param.getTaskId());
        task = rctTaskMapper.selectOne(task);
        return task.getFirstAccessFlash();
    }

    /**
     * @Author budi
     * @Description 批量修改用户实验样本（第二次随访）
     * @Date 10:58 上午 2020/10/19
     * @Param [param]
     * @return java.lang.String
     **/
    public String updateUserTaskSampleListSecondAccessScore(UpdateUserTaskSampleListVO param) {
        // 通过传入的用户实验id找到所有签署用户知情同意书的用户实验样本
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        sample.setIfAgree(IfAgreeConstant.IFAGREE_YES);
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);
        // 根据用户实验选择的测量维度设置测量分数范围
        RctUserTask userTask = new RctUserTask();
        userTask.setId(param.getUserTaskId());
        userTask = rctUserTaskMapper.selectOne(userTask);
        int secondAccessScoreMin, secondAccessScoreMax;
        if (userTask.getMeasureDimension() == "ECBI-intensity问题频率得分（母亲视角）" || userTask.getMeasureDimension() == "ECBI-intensity问题频率得分（父亲视角）") {
            secondAccessScoreMin = SecondAccessScoreConstant.SECOND_ACCESS_SCORE_FREQUENCY_MIN;
            secondAccessScoreMax = SecondAccessScoreConstant.SECOND_ACCESS_SCORE_FREQUENCY_MAX;
        } else {
            secondAccessScoreMin = SecondAccessScoreConstant.SECOND_ACCESS_SCORE_INTENSITY_MIN;
            secondAccessScoreMax = SecondAccessScoreConstant.SECOND_ACCESS_SCORE_INTENSITY_MAX;
        }
        // 随机选择一部分样本失访，并对于未失访的用户实验样本
        // 随机设置secondAccessScore，即测量得分，范围控制在
        // Second_Access_Score_MIN 到 Second_Access_Score_MAX 之间，取整
        Random rd = new Random();
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次不失访，才可进行第二次随访
            if (item.getFirstAccessScore() != 0) {
                // 随机选择一部分用户失访(SECOND_ACCESS_SCORE_NULL_POSSIBILITY = 0.1)
                if (rd.nextDouble() < SecondAccessScoreConstant.SECOND_ACCESS_SCORE_NULL_POSSIBILITY) {
                    item.setSecondAccessScore(SecondAccessScoreConstant.SECOND_ACCESS_SCORE_NULL);
                } else {
                    item.setSecondAccessScore(rd.nextInt(secondAccessScoreMax - secondAccessScoreMin) + secondAccessScoreMin);
                }
                rctUserTaskSampleMapper.updateByPrimaryKey(item);
            }
        }
        // 获取第二次访问动画URL
        RctTask task = new RctTask();
        task.setId(param.getTaskId());
        task = rctTaskMapper.selectOne(task);
        return task.getSecondAccessFlash();
    }

    /**
     * @Author budi
     * @Description 生成流程图
     * @Date 11:45 上午 2020/10/19
     * @Param [param]
     * @return com.monetware.rctexperiment.business.pojo.dto.manage.GenerateFlowChartDTO
     **/
    public FlowChartDTO generateFlowChart(SearchUserTaskSampleByParamVO param) {
        RctUserTaskVO rctUserTaskVO=new RctUserTaskVO();
        rctUserTaskVO.setId(param.getUserTaskId());
        Integer currentStep = rctUserTaskService.getCurrentStep(rctUserTaskVO);
        FlowChartDTO dto = new FlowChartDTO();
        dto.setFinishStepNum(currentStep);
        // 通过传入的用户实验id计算所有纳入的用户实验样本数目
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        dto.setIncludeNum(rctUserTaskSampleMapper.selectCount(sample));
        // 计算签署知情同意书的用户实验样本数目
        sample.setIfAgree(IfAgreeConstant.IFAGREE_YES);
        dto.setAgreeNum(rctUserTaskSampleMapper.selectCount(sample));
        // 计算PCIT组的实验样本数目
        sample.setGroupType(GroupTypeConstant.PCIT);
        dto.setPcitNum(rctUserTaskSampleMapper.selectCount(sample));
        // 计算TAU组的实验样本数目
        sample.setGroupType(GroupTypeConstant.TAU);
        dto.setTauNum(rctUserTaskSampleMapper.selectCount(sample));
        // 计算PCIT组第一次随访的样本数量
        sample.setGroupType(GroupTypeConstant.PCIT);
        sample.setFirstAccessScore(FirstAccessScoreConstant.FIRST_ACCESS_SCORE_NULL);
        dto.setPcitFirstAccessNum(dto.getPcitNum()-rctUserTaskSampleMapper.selectCount(sample));
        // 计算TAU组第一次随访的样本数量
        sample.setGroupType(GroupTypeConstant.TAU);
        sample.setFirstAccessScore(FirstAccessScoreConstant.FIRST_ACCESS_SCORE_NULL);
        dto.setTauFirstAccessNum(dto.getTauNum()-rctUserTaskSampleMapper.selectCount(sample));
        // 计算PCIT组第二次随访的样本数量
        sample.setGroupType(GroupTypeConstant.PCIT);
        sample.setFirstAccessScore(null);
        sample.setSecondAccessScore(SecondAccessScoreConstant.SECOND_ACCESS_SCORE_NULL);
        dto.setPcitSecondAccessNum(dto.getPcitFirstAccessNum()-rctUserTaskSampleMapper.selectCount(sample));
        // 计算TAU组第二次随访的样本数量
        sample.setGroupType(GroupTypeConstant.TAU);
        sample.setFirstAccessScore(null);
        sample.setSecondAccessScore(SecondAccessScoreConstant.SECOND_ACCESS_SCORE_NULL);
        dto.setTauSecondAccessNum(dto.getTauFirstAccessNum()-rctUserTaskSampleMapper.selectCount(sample));
        // 返回生成流程图dto
        return dto;
    }

    /**
     * @Author budi
     * @Description 生成结果数据分析
     * @Date 3:35 下午 2020/10/19
     * @Param [param]
     * @return com.monetware.rctexperiment.business.pojo.dto.manage.ResultDataAnalysisDTO
     **/
    public ResultDataAnalysisDTO generateResultDataAnalysis(SearchUserTaskSampleByParamVO param) {
        DecimalFormat df=new DecimalFormat("0.00");
        ResultDataAnalysisDTO dto = new ResultDataAnalysisDTO();
        // 定义计算数据
        int num = 0;
        double score, mean, sd;
        double sum = 0.0;
        // 通过传入的用户实验id找到PCIT实验组数据
        RctUserTaskSample sample = new RctUserTaskSample();
        sample.setUserTaskId(param.getUserTaskId());
        sample.setIfAgree(IfAgreeConstant.IFAGREE_YES);
        sample.setGroupType(GroupTypeConstant.PCIT);
        List<RctUserTaskSample> rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);

        // 计算PCIT实验组基线数据结果分析
        // 遍历PCIT用户实验数据，计算样本数、测量得分总和
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            num++;
            sum += item.getMeasurementScore().doubleValue();
        }
        // 计算平均值
        mean = sum / num;
        // 遍历PCIT用户实验数据，计算标准差
        sum = 0.0;
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            score = item.getMeasurementScore();
            sum += Math.pow((score - mean), 2);
        }
        sd = Math.sqrt(sum / num);
        // 将计算结果添加至返回dto
        dto.setPcitT0Num(num);
        dto.setPcitT0Mean(Double.valueOf(df.format(mean)));
        dto.setPcitT0SD(Double.valueOf(df.format(sd)));



        // 计算PCIT实验组第一次随访结果分析
        // 重置计算数据
        num = 0;
        sum = 0.0;
        // 遍历PCIT第一次随访结果数据，计算样本数、测量得分总和
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次随访不失访
            if (item.getFirstAccessScore() != 0) {
                 num++;
                sum += item.getFirstAccessScore().doubleValue();
            }
        }
        // 计算平均值
        mean = sum / num;
        // 遍历PCIT第一次随访数据，计算标准差
        sum = 0.0;
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次随访不失访
            if (item.getFirstAccessScore() != 0) {
                score = item.getFirstAccessScore();
                sum += Math.pow((score - mean), 2);
            }
        }
        sd = Math.sqrt(sum / num);
        // 将计算结果添加至dto
        dto.setPcitT1Num(num);
        dto.setPcitT1Mean(Double.valueOf(df.format(mean)));
        dto.setPcitT1SD(Double.valueOf(df.format(sd)));



        // 计算PCIT实验组第二次随访结果分析
        // 重置计算数据
        num = 0;
        sum = 0.0;
        // 遍历PCIT第二次随访结果数据，计算样本数、测量得分总和
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次、第二次随访均不失访
            if (item.getFirstAccessScore()!=null && item.getSecondAccessScore()!=null&& item.getFirstAccessScore() != 0 && item.getSecondAccessScore() != 0) {
                num++;
                sum += item.getSecondAccessScore().doubleValue();
            }
        }
        // 计算平均值
        mean = sum / num;
        // 遍历PCIT第二次随访数据，计算标准差
        sum = 0.0;
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次、第二次随访均不失访
            if (item.getFirstAccessScore()!=null && item.getSecondAccessScore()!=null&&item.getFirstAccessScore() != 0 && item.getSecondAccessScore() != 0) {
                score = item.getSecondAccessScore();
                sum += Math.pow((score - mean), 2);
            }
        }
        sd = Math.sqrt(sum / num);
        // 将计算结果添加至dto
        dto.setPcitT2Num(num);
        dto.setPcitT2Mean(Double.valueOf(df.format(mean)));
        dto.setPcitT2SD(Double.valueOf(df.format(sd)));



        // 通过传入的用户实验id找到TAU对照组数据
        sample.setGroupType(GroupTypeConstant.TAU);
        rctUserTaskSampleList = rctUserTaskSampleMapper.select(sample);

        // 计算TAU对照组基线数据结果分析
        // 重置计算数据
        num = 0;
        sum = 0.0;
        // 遍历TAU用户实验数据，计算样本数、测量得分总和
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            num++;
            sum += item.getMeasurementScore().doubleValue();
        }
        // 计算平均值
        mean = sum / num;
        // 遍历TAU用户实验数据，计算标准差
        sum = 0.0;
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            score = item.getMeasurementScore();
            sum += Math.pow((score - mean), 2);
        }
        sd = Math.sqrt(sum / num);
        // 将计算结果添加至dto
        dto.setTauT0Num(num);
        dto.setTauT0Mean(Double.valueOf(df.format(mean)));
        dto.setTauT0SD(Double.valueOf(df.format(sd)));


        // 计算TAU对照组第一次随访结果分析
        // 重置计算数据
        num = 0;
        sum = 0.0;
        // 遍历TAU第一次随访结果数据，计算样本数、测量得分总和
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次随访不失访
            if (item.getFirstAccessScore()!=null && item.getFirstAccessScore() != 0) {
                num++;
                sum += item.getFirstAccessScore().doubleValue();
            }
        }
        // 计算平均值
        mean = sum / num;
        // 遍历TAU第一次随访数据，计算标准差
        sum = 0.0;
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次随访不失访
            if (item.getFirstAccessScore()!=null && item.getFirstAccessScore() != 0) {
                score = item.getFirstAccessScore();
                sum += Math.pow((score - mean), 2);
            }
        }
        sd = Math.sqrt(sum / num);
        // 将计算结果添加至dto
        dto.setTauT1Num(num);
        dto.setTauT1Mean(Double.valueOf(df.format(mean)));
        dto.setTauT1SD(Double.valueOf(df.format(sd)));



        // 计算TAU对照组第二次随访结果分析
        // 重置计算数据
        num = 0;
        sum = 0.0;
        // 遍历TAU第二次随访结果数据，计算样本数、测量得分总和
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次、第二次随访均不失访
            if (item.getFirstAccessScore()!=null && item.getSecondAccessScore()!=null&&item.getFirstAccessScore() != 0 && item.getSecondAccessScore() != 0) {
                num++;
                sum += item.getSecondAccessScore().doubleValue();
            }
        }
        // 计算平均值
        mean = sum / num;
        // 遍历TAU第二次随访数据，计算标准差
        sum = 0.0;
        for (RctUserTaskSample item : rctUserTaskSampleList) {
            // 第一次、第二次随访均不失访
            if (item.getFirstAccessScore()!=null && item.getSecondAccessScore()!=null&&item.getFirstAccessScore() != 0 && item.getSecondAccessScore() != 0) {
                score = item.getSecondAccessScore();
                sum += Math.pow((score - mean), 2);
            }
        }
        sd = Math.sqrt(sum / num);
        // 将计算结果添加至dto
        dto.setTauT2Num(num);
        dto.setTauT2Mean(Double.valueOf(df.format(mean)));
        dto.setTauT2SD(Double.valueOf(df.format(sd)));



        // T0-T1变化 T1-T2变化
        // 实验组
        int pcitT0Num = dto.getPcitT0Num();
        double pcitT0Mean = dto.getPcitT0Mean();
        double pcitT1Mean = dto.getPcitT1Mean();
        double pcitT2Mean = dto.getPcitT2Mean();
        double pcitT0SD = dto.getPcitT0SD();
        double pcitT1SD = dto.getPcitT1SD();
        double pcitT2SD = dto.getPcitT2SD();

        // Cohen's d值
        // T0-T1 T1-T2
        double pcitT0T1Cohensd = (pcitT0Mean - pcitT1Mean) / (Math.sqrt((Math.pow(pcitT0SD, 2) + Math.pow(pcitT1SD, 2)) / 2));
        double pcitT0T2Cohensd = (pcitT0Mean - pcitT2Mean) / (Math.sqrt((Math.pow(pcitT0SD, 2) + Math.pow(pcitT2SD, 2)) / 2));
        //将计算结果添加至dto
        dto.setPcitT0T1Cohensd(Double.valueOf(df.format(pcitT0T1Cohensd)));
        dto.setPcitT0T2Cohensd(Double.valueOf(df.format(pcitT0T2Cohensd)));

        // T值
        // T0-T1 T1-T2
        double pcitT0T1T = (pcitT1Mean - pcitT0Mean) / (pcitT0SD / Math.sqrt(pcitT0Num - 1));
        double pcitT0T2T = (pcitT2Mean - pcitT0Mean) / (pcitT0SD / Math.sqrt(pcitT0Num - 1));


        dto.setPcitT0T1T(Double.valueOf(df.format(pcitT0T1T)));
        dto.setPcitT0T2T(Double.valueOf(df.format(pcitT0T2T)));
        // T0-T1变化 T1-T2变化
        // 对照组
        int tauT0Num = dto.getTauT0Num();
        double tauT0Mean = dto.getTauT0Mean();
        double tauT1Mean = dto.getTauT1Mean();
        double tauT2Mean = dto.getTauT2Mean();
        double tauT0SD = dto.getTauT0SD();
        double tauT1SD = dto.getTauT1SD();
        double tauT2SD = dto.getTauT2SD();

        // Cohen's d值
        // T0-T1 T1-T2
        double tauT0T1Cohensd = (tauT0Mean - tauT1Mean) / (Math.sqrt((Math.pow(tauT0SD, 2) + Math.pow(tauT1SD, 2)) / 2));
        double tauT0T2Cohensd = (tauT0Mean - tauT2Mean) / (Math.sqrt((Math.pow(tauT0SD, 2) + Math.pow(tauT2SD, 2)) / 2));
        //将计算结果添加至dto
        dto.setTauT0T1Cohensd(Double.valueOf(df.format(tauT0T1Cohensd)));
        dto.setTauT0T2Cohensd(Double.valueOf(df.format(tauT0T2Cohensd)));

        // T值
        // T0-T1 T1-T2
        double tauT0T1T = (tauT1Mean - tauT0Mean) / (tauT0SD / Math.sqrt(tauT0Num - 1));
        double tauT0T2T = (tauT2Mean - tauT0Mean) / (tauT0SD / Math.sqrt(tauT0Num - 1));
        //将计算结果添加至dto
        dto.setTauT0T1T(Double.valueOf(df.format(tauT0T1T)));
        dto.setTauT0T2T(Double.valueOf(df.format(tauT0T2T)));
        RctUserTaskVO rctUserTaskVO=new RctUserTaskVO();
        rctUserTaskVO.setId(param.getUserTaskId());
        Integer currentStep = rctUserTaskService.getCurrentStep(rctUserTaskVO);
        dto.setFinishStepNum(currentStep);
        return dto;
    }

    // =========================== budi End ===========================

}
