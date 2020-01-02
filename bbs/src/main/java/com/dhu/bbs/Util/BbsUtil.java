package com.dhu.bbs.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

public class BbsUtil {

    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
                  } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    public static String getJSONString(int code, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        for (Map.Entry<String, Object> entry : map.entrySet()){
            json.put(entry.getKey(), entry.getValue());
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public  static String IMAGE_DIR = "c:/upload/";
    public  static String DOMAIN = "127.0.0.1:8080/image";
    public static  String[] IMAGE_FILE_EXT = new String[] {"png", "bmp", "jpg", "gif", "jepg"};



    public  static boolean isFileAllowed(String extName){
        for(String ext: IMAGE_FILE_EXT){
            if(extName.equals(ext) ){
                return true;
            }
        }
        return false;
    }

    public static String saveImage(MultipartFile file) throws IOException {
        int doPos = file.getOriginalFilename().lastIndexOf(".");

        if(doPos < 0){
            return null;
        }
        String fileExt = file.getOriginalFilename().substring(doPos+1).toLowerCase();
        if(!BbsUtil.isFileAllowed(fileExt)){
            return null;
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + "." + fileExt;

        Files.copy(file.getInputStream(), new File(BbsUtil.IMAGE_DIR + fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        return  "/image?name=" + fileName;

    }

    public static String Object2Json(Object objs){

        return JSON.toJSONString(objs);
    }


}
