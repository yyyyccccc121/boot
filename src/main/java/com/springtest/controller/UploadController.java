package com.springtest.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {


    public static final String ACCESS_KEY = "Y7QIvvEcbDS5lFFGFmkXHqU4r-hZ3NX5u2C7r-SJ"; // 你的access_key
    public static final String SECRET_KEY = "qq8uW8hunwJp1B3NAZz2qMR1vw63-_VfL0oezeo4"; // 你的secret_key
    public static final String BUCKET_NAME = "q2qyxlu1z.bkt.clouddn.com"; // 你的secret_key


    @RequestMapping(value="staff/pushUserIcon",method= RequestMethod.POST)
    @ResponseBody
    public String pushUserIcon(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = null;
        String userId = request.getParameter("userId");
        try{
            //转型为MultipartHttpRequest(重点的所在)
            MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
            //获得第1张图片（根据前台的name名称得到上传的文件）
            MultipartFile file  =  multipartRequest.getFile("file");

            String url = SaveFileFromInputStream(file.getInputStream(),"F:/",file.getOriginalFilename());
            uploadToQiNiu(url,file.getOriginalFilename());
            downFile(file.getOriginalFilename());

            response.setContentType("text/html;charset=utf8");
            response.getWriter().write("result:" + result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public String SaveFileFromInputStream(InputStream stream, String path, String filename)throws IOException
    {
        FileOutputStream fs = new FileOutputStream( path +"/"+ filename);
        byte[] buffer=new byte[1024*1024];
        int bytesum=0;
        int byteread=0;
        while((byteread=stream.read(buffer))!=-1){
        bytesum+=byteread;
        fs.write(buffer,0,byteread);
           fs.flush();
        }
        fs.close();
        stream.close();
        return path+"/"+filename;
   }

   public void uploadToQiNiu(String url,String filename){

       //构造一个带指定 Region 对象的配置类
       Configuration cfg = new Configuration(Region.region2());
       //...其他参数参考类注释
       UploadManager uploadManager = new UploadManager(cfg);
       //...生成上传凭证，然后准备上传
       String accessKey = ACCESS_KEY;
       String secretKey = SECRET_KEY;
       String bucket = "mystoragespacename";
       //如果是Windows情况下，格式是 D:\\qiniu\\test.png
       String localFilePath = url;
       System.out.println("url="+url);
       //默认不指定key的情况下，以文件内容的hash值作为文件名
       String key = filename;
       Auth auth = Auth.create(accessKey, secretKey);
       String upToken = auth.uploadToken(bucket,key);
       System.out.println("uptoken is:     "+upToken);
       try {
           Response response = uploadManager.put(localFilePath, key, upToken);
           //解析上传成功的结果
           DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
           System.out.println("key="+putRet.key);
           System.out.println("hash="+putRet.hash);
       } catch (QiniuException ex) {
           Response r = ex.response;
           System.err.println(r.toString());
           try {
               System.err.println(r.bodyString());
           } catch (QiniuException ex2) {
               System.out.println("????????????????");
           }
       }

       System.out.println("succeed upload image");
   }

   public void downFile(String fileName){
       String domainOfBucket = BUCKET_NAME;
       String finalUrl = String.format("%s/%s", domainOfBucket, fileName);
       System.out.println(finalUrl);
   }
}