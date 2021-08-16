package com.monetware.rctexperiment.business.service.rtc;

import cn.afterturn.easypoi.word.entity.WordImageEntity;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.monetware.rctexperiment.business.dao.RctUserTaskMapper;
import com.monetware.rctexperiment.business.dao.RctUserTaskSampleMapper;
import com.monetware.rctexperiment.business.pojo.dto.manage.ResultDataAnalysisDTO;
import com.monetware.rctexperiment.business.pojo.po.sample.RctUserTaskSample;
import com.monetware.rctexperiment.business.pojo.po.task.RctUserTask;
import com.monetware.rctexperiment.business.pojo.po.user.RctUser;
import com.monetware.rctexperiment.business.pojo.vo.manage.SearchUserTaskSampleByParamVO;
import com.monetware.rctexperiment.business.pojo.vo.task.BaseToImgVO;
import com.monetware.rctexperiment.business.service.manage.UserTaskSampleManageService;
import com.monetware.rctexperiment.business.service.user.UserService;
import com.monetware.rctexperiment.system.base.ResultData;
import com.monetware.rctexperiment.system.config.Config;
import com.monetware.rctexperiment.system.util.upload.FileUploadUtils;
import com.monetware.rctexperiment.system.util.upload.GetFileNameUtils;
import com.monetware.rctexperiment.system.util.word.WordExport;
import com.monetware.rctexperiment.system.util.word.WordExportUtils;
import com.monetware.threadlocal.ThreadLocalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.monetware.rctexperiment.business.pojo.constant.RctTaskConstant.*;

/**
 * @description: RtcBash64ToImgService
 * @author: 彭于晏
 * @create: 2020-10-16 13:03
 **/
@Service
public class EnclosureService {
    @Autowired
    private Config config;
    @Autowired
    private RctUserTaskMapper userTaskMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RctUserTaskSampleMapper rctUserTaskSamplMapper;
    @Autowired
    private UserTaskSampleManageService userTaskSampleManageService;
    /**
     * 保存流程图
     * @param baseToImgVo
     * @return
     */
    public Integer bash64ToImg(@RequestBody BaseToImgVO baseToImgVo){
        //获取图片存储位置
        String path=config.getUploadImgs();
        //获取图片下载位置
        String imgUrl=config.getUploadImgUrl();
        //获取文件名称
        String fileName= GetFileNameUtils.getImgName();
        //获取图片新地址
        String bashPath=path + File.separator + ThreadLocalManager.getUserId()+File.separator+fileName;
        //bash64ToImg
        String s = FileUploadUtils.bash64ToImg(baseToImgVo.getImgUrl(), bashPath,path);
        //判断是否成功
        if("ok".equals(s)){
            //修改用户实验表中的图片地址
            Integer userTaskId = baseToImgVo.getUserTaskId();
            RctUserTask rctUserTask=new RctUserTask();
            rctUserTask.setId(userTaskId);
            rctUserTask.setFlowsheetUrl(imgUrl+fileName);
            rctUserTask.setFlowsheetRealUrl(bashPath);
            rctUserTask.setFinishStepNum(STEP10);
           return userTaskMapper.updateByPrimaryKeySelective(rctUserTask);

        }
        return 0;
    }


    /**
     * 导出word文档到本地
     * @return
     */
    public Integer exportWord(@RequestBody BaseToImgVO baseToImgVo){
        //获取用户实验id
        Integer userTaskId = baseToImgVo.getUserTaskId();
        //查询当前用户实验
        RctUser rctUser = userService.commonGetUser();
        RctUserTask rctUserTask=new RctUserTask();
        rctUserTask.setId(userTaskId);
        RctUserTask userTask = userTaskMapper.selectOne(rctUserTask);
        //获取上传地址
        String path=config.getUploadWord();
        //或用word新文件名
        String wordName = GetFileNameUtils.getWordName();
        //获取模板地址
        String templatePath=config.getTemplatePath();
        //图片路径，请注意你是linux还是windows

        Map<String, Object> datas = new HashMap<String, Object>() ;
        datas.put("auth",rctUser.getUsername());
        //注册编号
        datas.put("registerNum",userTask.getRegisterNum());
        //实验目的
        datas.put("task_purpose",userTask.getTaskPurpose());
        //实验讨论
        datas.put("discuss",userTask.getDiscuss());
        //实验结论
        datas.put("conclusion",userTask.getConclusion());
        //导入流程图到word
        PictureRenderData pictureRenderData= new PictureRenderData(300, 300, userTask.getFlowsheetRealUrl());
        datas.put("image",pictureRenderData);
        //导入表格到 word
        List<RenderData> renderDataList= new ArrayList<RenderData>();
        renderDataList.add(new TextRenderData("d0d0d0", "样本编号"));
        renderDataList.add(new TextRenderData("d0d0d0", "年龄"));
        renderDataList.add(new TextRenderData("d0d0d0", "性别"));
        renderDataList.add(new TextRenderData("d0d0d0", "初始测量分数"));
        renderDataList.add(new TextRenderData("d0d0d0", "第6个月随访数据"));
        renderDataList.add(new TextRenderData("d0d0d0", "第18个月随访数据"));
        //PCIT表格中所需要的数据
        RctUserTaskSample rctUserTaskSample=new RctUserTaskSample();
        rctUserTaskSample.setUserTaskId(userTaskId);
        rctUserTaskSample.setGroupType(1);
        List<RctUserTaskSample> select = rctUserTaskSamplMapper.select(rctUserTaskSample);
        TableRenderData table =new TableRenderData(renderDataList,tableStringList(select),"no datas", 9000);
        //导入PCIT表格
        datas.put("PCIT",table);
        //导入TAU表格
        rctUserTaskSample.setGroupType(2);
        List<RctUserTaskSample> select1 = rctUserTaskSamplMapper.select(rctUserTaskSample);
        TableRenderData table2 =new TableRenderData(renderDataList,tableStringList(select1),"no datas", 9000);
        datas.put("TAU",table2);
        //导出表格
        datas.put("measure_tool",userTask.getMeasureTool()+"   "+userTask.getMeasureDimension());
        //userTaskSampleManageService
        SearchUserTaskSampleByParamVO param =new SearchUserTaskSampleByParamVO();
        param.setUserTaskId(userTaskId);
        ResultDataAnalysisDTO analysis = userTaskSampleManageService.generateResultDataAnalysis(param);
        for (int i=1;i<=6;i++){
           if(i==1){
               datas.put("N"+i,analysis.getPcitT0Num());
               datas.put("Mean"+i,analysis.getPcitT0Mean());
               datas.put("SD"+i,analysis.getPcitT0SD());
           }
            if(i==2){
                datas.put("N"+i,analysis.getPcitT1Num());
                datas.put("Mean"+i,analysis.getPcitT1Mean());
                datas.put("SD"+i,analysis.getPcitT1SD());
            }
            if(i==3){
                datas.put("N"+i,analysis.getPcitT2Num());
                datas.put("Mean"+i,analysis.getPcitT2Mean());
                datas.put("SD"+i,analysis.getPcitT2SD());
            }
            if(i==4){
                datas.put("N"+i,analysis.getTauT0Num());
                datas.put("Mean"+i,analysis.getTauT0Mean());
                datas.put("SD"+i,analysis.getTauT0SD());
            }
            if(i==5){
                datas.put("N"+i,analysis.getTauT1Num());
                datas.put("Mean"+i,analysis.getTauT1Mean());
                datas.put("SD"+i,analysis.getTauT1SD());
            }
            if(i==6){
                datas.put("N"+i,analysis.getTauT2Num());
                datas.put("Mean"+i,analysis.getTauT2Mean());
                datas.put("SD"+i,analysis.getTauT2SD());
            }
        }
        datas.put("T1",analysis.getPcitT0T1T());
        datas.put("C1",analysis.getPcitT0T1Cohensd());
        datas.put("T2",analysis.getPcitT0T2T());
        datas.put("C2",analysis.getPcitT0T2Cohensd());

        datas.put("T3",analysis.getTauT0T1T());
        datas.put("C3",analysis.getTauT0T1Cohensd());
        datas.put("T4",analysis.getTauT0T2T());
        datas.put("C4",analysis.getTauT0T2Cohensd());
        //开始导出word
        XWPFTemplate template = XWPFTemplate.compile(templatePath)
                .render(datas);
        FileOutputStream out;
        try {
            File file = new File(path+File.separator+ThreadLocalManager.getUserId());
            if (!file.exists()){
                file.mkdirs();
            }
            out = new FileOutputStream(path+File.separator+ThreadLocalManager.getUserId()+File.separator+wordName);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String wordUrl=config.getUploadWordUrl()+ThreadLocalManager.getUserId()+"/"+wordName;
        rctUserTask.setResultFileUrl(wordUrl);
        rctUserTask.setFinishStepNum(STEP12);
//        rctUserTask.setStatus(1);
        rctUserTask.setEndTime(new Date());
        return userTaskMapper.updateByPrimaryKeySelective(rctUserTask);
//        RctUserTask userTask1 = userTaskMapper.selectOne(rctUserTask);
    }


//================================================common=================================================

    /**
     * 通用的表格赋值
     * @param select
     * @return
     */
    private List<Object> tableStringList(List<RctUserTaskSample> select){
        List<Object>list= new ArrayList<Object>();
        for (RctUserTaskSample userTaskSample : select) {
            StringBuffer stringBuffer=new StringBuffer();
            //样本id
            stringBuffer.append(userTaskSample.getSampleId()+";");
            //年龄
            stringBuffer.append(userTaskSample.getAge()+";");
            //性别
            if(userTaskSample.getSex()==1){
                stringBuffer.append("男;");
            }else{
                stringBuffer.append("女;");
            }

            //初始测量分数
            stringBuffer.append(ifShiFang(userTaskSample.getMeasurementScore())+";");
            //第一次
            stringBuffer.append(ifShiFang(userTaskSample.getFirstAccessScore())+";");
            //第二次
            stringBuffer.append(ifShiFang(userTaskSample.getSecondAccessScore()));
            list.add(stringBuffer.toString());
        }
        if(list.size()==0){
            list.add("暂无数据"+";");
        }
        return list;
    }

    public String  ifShiFang(Integer core){
        String measurementString=core+"";
        if(core==null){
            measurementString=" ";
        }else if(core==0){
            measurementString="失访";
        }
        return measurementString;
    }
}
