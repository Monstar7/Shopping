package com.shopping.controller;

import java.net.URLEncoder;
import com.shopping.service.FaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Name;
import java.util.List;
import java.util.Map;

/**
 *  该类用于人脸识别测试controller
 */
@Controller
public class FaceControl {

    @Autowired
    private FaceImpl faceImpl;

    @RequestMapping(value = "/face",method = RequestMethod.POST)
    @ResponseBody
    public String face(String image){
        System.out.println(image);
//        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
//        String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
//        String pictore = image;
//        String path = "";
//        String s = faceImpl.GetImageStr(webappRoot+"static/userImg/"+path);
        List<String> strings = faceImpl.faceIdentify(image);
        String s = strings.get(0);
        /*s="开心";

        try {
            s = URLEncoder.encode(s,"utf-8");
            s = new String(s.getBytes("GBK"),"UTF-8");
        }catch (Exception e){

        }*/
        return s;
    }


}
