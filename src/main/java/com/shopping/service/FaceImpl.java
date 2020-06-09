package com.shopping.service;


import com.baidu.aip.face.AipFace;
import com.shopping.dao.ProductDao;
import com.shopping.entity.Product;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaceImpl implements Face{


    @Autowired
    private ProductDao productDao;

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

    @Override
    public List<String> faceRecomment(String s){

        String faceRes = "";
        String faceCode = "";
        List<String> faces = new ArrayList();
        /**
         * 表情对应解析及编码
         * angry:愤怒:1
         * disgust:厌恶:2
         * fear:恐惧:3
         * happy:高兴:4
         * sad:伤心:5
         * surprise:惊讶:6
         * neutral:无表情:7
         * pouty: 撅嘴:8
         * grimace:鬼脸:9
         */
        switch (s){
            case "angry" :
                faceRes = "愤怒";
                faceCode = "1";   //冷饮
                break;
            case "disgust" :
                faceRes = "厌恶";
                faceCode = "2";     //爽口
                break;
            case "fear" :
                faceRes = "恐惧";
                faceCode = "3";    //较甜
                break;
            case "happy" :
                faceRes = "高兴";
                faceCode = "4";   //新上市
                break;
            case "sad" :
                faceRes = "伤心";
                faceCode = "5";   //热饮
                break;
            case "surprise" :
                faceRes = "惊讶";
                faceCode = "6";
                break;
            case "neutral" :
                faceRes = "表情自然";
                faceCode = "7";    //特色
                break;
            case "pouty" :
                faceRes = "撅嘴";
                faceCode = "8";
                break;
            case "grimace" :
                faceRes = "鬼脸";
                faceCode = "9";
                break;
            default:
                faceRes = "表情自然";
                faceCode = "7";
                break;
        }
        faces.add(s);
        faces.add(faceRes);
        faces.add(faceCode);
        return faces;
    }



    public List<Product> getProductsByFace(String faceCode){
        return productDao.getProductsByFace(faceCode);

    }



}
