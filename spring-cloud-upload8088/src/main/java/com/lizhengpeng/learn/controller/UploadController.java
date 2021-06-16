package com.lizhengpeng.learn.controller;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;

/**
 * 测试文件上传使用的控制器
 * @author lzp
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/showUpload", method = RequestMethod.GET)
    public ModelAndView showUploadPage(ModelAndView modelAndView){
        modelAndView.setViewName("uploadPage");
        return modelAndView;
    }

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile multipartFile){
        try{
            if(multipartFile != null && !multipartFile.isEmpty()){
                System.out.println("文件名称为  "+multipartFile.getOriginalFilename());
                int hasRead = -1;
                byte[] swap = new byte[2049];
                int index = 0;
                BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
                while((hasRead  = bufferedInputStream.read(swap)) != -1){
                    System.out.println((++index)+"次读取文件数据,临时长度为"+hasRead);
                }
                bufferedInputStream.close();
            }
            return "文件上传成功";
        }catch (Exception e) {
            return "文件上传失败";
        }
    }

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(){
        return "hello,this is upload Server";
    }

}
