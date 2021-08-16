package com.monetware.rctexperiment.business.controller.enclosure;


import com.monetware.rctexperiment.business.pojo.vo.task.BaseToImgVO;
import com.monetware.rctexperiment.business.service.rtc.EnclosureService;
import com.monetware.rctexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.File;


/**
 * @description: EnclosureController  附件
 * @author: 彭于晏
 * @create: 2020-10-16 10:34
 **/
@RestController
@RequestMapping("/enclosure")
@Api(value = "附件相关的APIword、流程图")
public class EnclosureController {
    @Autowired
    private EnclosureService enclosureService;
//====================================hs begin=========================================
    /**
     * 保存流程图 把bash64转换为图片
     * @param baseToImgVo
     * @return
     */
    @PostMapping("/bash64ToImg")
    @ApiOperation("保存流程图")
    public ResultData<Integer> bash64ToImg(@RequestBody @ApiParam(name="用户对象",value="传入json格式") BaseToImgVO baseToImgVo){
       enclosureService.bash64ToImg(baseToImgVo);
       return new ResultData<>(0,"调用成功");
    }


    /**
     * 保存word
     * @param baseToImgVo
     * @return
     */
    @PostMapping("/exportWord")
    @ApiOperation("保存word")
    public ResultData<Integer> exportWord(@RequestBody BaseToImgVO baseToImgVo){
         enclosureService.exportWord(baseToImgVo);
        return new ResultData<>(0,"调用成功");
    }
//========================================hs end=====================================
}
