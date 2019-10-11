package com.mrl.inote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController {

    public final static String UPLOADIMG_SAVE_PATH = "uploadImg";
    public final static String UPLOADFILE_SAVE_PATH = "uploadFile";


    @RequestMapping("/knowledge/img")
    public void uploadKnowledgeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String,String> userInfo = FileTransfer.multipleFileReceive(request,"/uploads/uploadImg");

        String result = "";
        if(userInfo.containsKey("baseInfo")&&userInfo.containsKey("fileInfo")){
            result="succeed";
        }
        out.write(result);
        out.flush();
        out.close();
    }

}
