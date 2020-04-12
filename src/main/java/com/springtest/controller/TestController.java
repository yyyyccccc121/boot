package com.springtest.controller;

import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Controller
public class TestController {

    @RequestMapping("websocket/manage/excelImport.do")
    @ResponseBody
    public String excelImport(@RequestParam("file")MultipartFile excelFile, HttpServletRequest request, String extra) throws IOException {
        System.out.println("1===="+excelFile.getOriginalFilename());
        System.out.println("2===="+request.getSession().getServletContext().getRealPath(""));
        System.out.println("extra===="+extra);
        String theFileName=null;
        if (excelFile != null){
            String filename=excelFile.getOriginalFilename();
            //IE浏览器获取的文件名会自带盘符,这里需要截取一下
            int unixSep = filename.lastIndexOf('/');
            int winSep = filename.lastIndexOf('\\');
            int pos = (winSep > unixSep ? winSep : unixSep);
            if (pos != -1)  {
                filename = filename.substring(pos + 1);
            }

            theFileName = filename;
            String a="F:/XXXXX";
            System.out.println("a="+a);
            SaveFileFromInputStream(excelFile.getInputStream(),a,filename);//保存到服务器的路径
        }

        return theFileName;
    }

    /**
     * 将MultipartFile转化为file并保存到服务器上的某地
     */
    public void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException
    {
        FileOutputStream fs=new FileOutputStream( path + "/"+ savefile);
        System.out.println("------------"+path + "/"+ savefile);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread=stream.read(buffer))!=-1)
        {
            bytesum+=byteread;
            fs.write(buffer,0,byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    @RequestMapping(value = "util/downfile")
//    @ResponseBody
    public void downFile(HttpServletRequest request, HttpServletResponse response, String fileNname) throws IOException {

        // 根据文件名称|文件路径获取 上下文的路径地址
        String realPath = "F:/XXXXX/"+fileNname;

        // 获取文件的长度
        File file = new File(realPath);
        long fileLength = file.length();
        // 获取文件名称
        String name = file.getName();
        System.out.println("name="+name);
        // 设置响应类型
        response.setHeader("Content-Type", "application/octet-stream");
        // 设置下载的类型的长度
        response.setHeader("Content-Length", String.valueOf(fileLength));
        // 设置以下载方式使用
        response.setHeader("Content-Disposition","attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(name, "UTF-8"));
        // 获取下载流对象
        ServletOutputStream os = response.getOutputStream();
        // 缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(os);
        // 下载文件的缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        // 定义的缓冲区
        byte buffer[] =new byte[1024];
        // 定义读取的长度
        int len=0;
        // 循环读取
        while((len=bis.read(buffer))!=-1) {
            // 写入到响应的流中
            bos.write(buffer, 0, len);
        }
        bis.close();
        bos.close();
        os.close();
    }

    @RequestMapping(value = "staff/test/qq")
    @ResponseBody
    public JSONObject test11(String base1){
        System.out.println("---------");
        System.out.println(base1);
        System.out.println("---------");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("theBase",base1);
        return jsonObject;
//        Object object = jsonObject.get("theDate");
//        System.out.println(object);
//        Map<String,Base64> map = new HashedMap();
//        map.put("theDate",theDate);
//        return map;
    }

}
