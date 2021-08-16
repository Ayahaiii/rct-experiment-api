package com.monetware.rctexperiment.system.util.upload;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: GetFileNameUtils
 * @author: 彭于晏
 * @create: 2020-10-16 11:54
 **/

public class GetFileNameUtils {

    /**
     * 获取图片名字
     * @return
     */
    public static String getImgName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-ddhhmmss");
        String format = simpleDateFormat.format(date);
        return format+".jpg";
    }

    /**
     * 获取word名称
     * @return
     */
    public static String getWordName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-ddhhmmss");
        String format = simpleDateFormat.format(date);
        return format+".docx";
    }
}
