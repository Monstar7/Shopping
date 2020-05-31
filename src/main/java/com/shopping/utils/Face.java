package com.shopping.utils;


import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Face {

    public static List<String> faceIdentify(String imageUrl){
        ArrayList<String> z_results = new ArrayList<>();
        // 初始化一个AipFace
        AipFace client = new AipFace("20161333", "kkCmUGQEPuwDN7nmn6GNurh5", "QBH6BktInj4vvOsjXVhBHa8pkx2YEQCg");

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //   client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //   client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "emotion");
        //最大处理人个数
        options.put("max_face_num", "3");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        // 人脸检测
        JSONObject res = client.detect(imageUrl, "BASE64", options);

        Map<String, Object> results = res.toMap();
        Map<String, Object> result = (Map<String, Object>) results.get("result");
        ArrayList<Map> face_list = (ArrayList) result.get("face_list");
        for(Map li:face_list){
            Map emotion = (Map) li.get("emotion");
            String type = (String) emotion.get("type");
            z_results.add(type);
        }
        System.out.println(z_results);
        return z_results;
    }

    // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String GetImageStr(String imgFilePath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

}
