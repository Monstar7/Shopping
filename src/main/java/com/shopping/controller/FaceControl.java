package com.shopping.controller;

import com.shopping.service.FaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String face(){
        String pngUrl = "";
        String s = faceImpl.GetImageStr("D:\\111.png");
        List<String> strings = faceImpl.faceIdentify(s);
        return "";
    }


}
