package com.mrl.inote.controller;

import com.google.gson.JsonObject;
import com.mrl.inote.common.dto.AjaxResult;
import com.mrl.inote.common.util.UuId;
import com.mrl.inote.note.entity.Comment;
import com.mrl.inote.note.entity.Knowledge;
import com.mrl.inote.note.entity.User;
import com.mrl.inote.note.service.CommentService;
import com.mrl.inote.note.service.KnowledgeService;
import com.mrl.inote.note.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inote")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private CommentService commentService;

    /**
     * 获取用户列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/create")
    @ResponseBody
    public AjaxResult createUser(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        AjaxResult ajaxResult = new AjaxResult();
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String msg = null;
        if(StringUtils.isNotBlank(userName)&&StringUtils.isNotBlank(userPassword)){
            if(userService.getUserByName(userName)==null){
                User user = new User();
                user.setId(UuId.getId());
                user.setName(userName);
                user.setPassword(userPassword);
                user.setFansCount(0);
                user.setCreateDate(new Date());
                user.setUpdateDate(new Date());
                userService.save(user);
            }else{
                msg = "该用户名已被占用";
            }
        }else{
            msg = "创建失败";
        }
        ajaxResult.setMsg(msg);
        ajaxResult.setSuccess(StringUtils.isBlank(msg));
        return ajaxResult;
    }

    /**
     * 获取用户列表
     * @param response
     * @return
     */
    @RequestMapping("/user/list")
    @ResponseBody
    public AjaxResult getUserList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        AjaxResult ajaxResult = new AjaxResult();
        List<User> users = this.userService.getUserAll();
        List<String> userNames = null;
        if(users!=null&&users.size()>0){
            userNames = new ArrayList<String>();
            for(User user:users){
                userNames.add(user.getName());
            }
        }
        String usersStr = (userNames!=null&&userNames.size()>0)?GsonUtil.objectToJson(userNames):null;
        ajaxResult.setSuccess(StringUtils.isNotBlank(usersStr));
        ajaxResult.setData(usersStr);
        return ajaxResult;
    }

    /**
     * 获取知识点列表
     * @param request
     * @param response
     */
    @RequestMapping("/knowledge/list")
    @ResponseBody
    public AjaxResult getKnowledgeList(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        AjaxResult ajaxResult = new AjaxResult();
        String userName = request.getParameter("userName");
        List<Knowledge> knowledges = null;
        if(StringUtils.isNoneBlank(userName)){
            User user = userService.getUserByName(userName);
            if(user!=null){
                Knowledge knowledge = new Knowledge();
                knowledge.setUser(user.getId());
                knowledges = knowledgeService.getKnowledgeList(knowledge);
            }
        }
        String knowledgesStr = (knowledges!=null&&knowledges.size()>0)?GsonUtil.objectToJson(knowledges):null;
        ajaxResult.setSuccess(StringUtils.isNotBlank(knowledgesStr));
        ajaxResult.setData(knowledgesStr);
        return ajaxResult;
    }

    /**
     * 上传知识列表
     * @param request
     * @param response
     */
    @RequestMapping(value="/knowledge/upload", method=RequestMethod.POST,consumes="application/json")
    @ResponseBody
    public AjaxResult uploadKnowledge(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        AjaxResult ajaxResult = new AjaxResult();
        String knowledgeStr = request.getParameter("knowledgeStr");
        //String knowledgeStr = getInput(request);
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String deleteFlag = request.getParameter("deleteFlag");
        try {
            userName=URLDecoder.decode(userName, "UTF-8");
            userPassword=URLDecoder.decode(userPassword, "UTF-8");
            knowledgeStr=URLDecoder.decode(knowledgeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Knowledge> knowledges = null;
        String msg = null;
        User user = null;
        if(StringUtils.isNoneBlank(knowledgeStr)&&StringUtils.isNotBlank(userName)
                &&StringUtils.isNotBlank(userPassword)&&StringUtils.isNotBlank(deleteFlag)){
            user = userService.getUserByName(userName);
            if(user!=null&&userPassword.equals(user.getPassword())) {
                knowledges = GsonUtil.jsonsToObjects(knowledgeStr, Knowledge.class);
            }else{
                msg = "账户名或密码错误，请重新输入或创建新账户";
            }
        }
        if(knowledges!=null&&knowledges.size()>0){

            Knowledge knowledge = new Knowledge();
            knowledge.setUser(user.getId());
            List<Knowledge> knowledgeOld = knowledgeService.getKnowledgeList(knowledge);
            if("true".equals(deleteFlag)){
                knowledgeService.deleteAll(knowledgeOld);
                knowledgeOld = knowledgeService.getKnowledgeList(knowledge);
                if(knowledgeOld!=null&&knowledgeOld.size()>0){
                    msg = "上传失败";
                }
            }
            if(StringUtils.isBlank(msg)){
                for(Knowledge k : knowledges){
                    try{
                        k.setUser(user.getId());
                        if(StringUtils.isNotBlank(k.getImgUrl())){
                            k.setImgUrl("/uploads/"+UploadController.UPLOADIMG_SAVE_PATH+"/" + k.getImgUrl());
                        }
                        knowledgeService.save(k);
                    }catch (Exception e){
                        e.printStackTrace();
                        msg = "上传失败";
                    }
                }
            }
        }else{
            if(StringUtils.isBlank(msg)){
                msg = "上传失败";
            }
        }
        ajaxResult.setMsg(msg);
        ajaxResult.setSuccess(StringUtils.isBlank(msg));
        return ajaxResult;
    }

    /**
     * 提交评论
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/comment/submit")
    @ResponseBody
    public AjaxResult submitComment(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        AjaxResult ajaxResult = new AjaxResult();
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String knowledgeId = request.getParameter("knowledgeId");
        String content = request.getParameter("content");
        String msg = null;
        if(StringUtils.isNotBlank(userName)&&StringUtils.isNotBlank(knowledgeId)&&StringUtils.isNotBlank(content)){
            if(content.length()<=256){
                User user = userService.getUserByName(userName);
                if(user!=null&&userPassword.equals(user.getPassword())){
                    Comment comment = new Comment();
                    comment.setId(UuId.getId());
                    comment.setContent(content);
                    comment.setUser(user.getId());
                    comment.setUserName(user.getName());
                    comment.setKnowledge(knowledgeId);
                    Date date = new Date();
                    comment.setCreateDate(date);
                    comment.setUpdateDate(date);
                    commentService.save(comment);
                }else{
                    msg = "账户名或密码错误，请重新输入或创建新账户";
                }
            }else{
                msg = "评论字数过长";
            }
        }else{
            msg = "评论失败";
        }
        ajaxResult.setSuccess(StringUtils.isBlank(msg));
        ajaxResult.setMsg(msg);
        return ajaxResult;
    }

    /**
     * 获取评论列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/comment/list")
    @ResponseBody
    public AjaxResult getCommentList(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        String knowledgeId = request.getParameter("knowledgeId");
        AjaxResult ajaxResult = new AjaxResult();
        List<Comment> comments = null;
        if(StringUtils.isNotBlank(knowledgeId)){
            Comment comment = new Comment();
            comment.setKnowledge(knowledgeId);
            comments = this.commentService.getCommentList(comment);
        }
        String commentStr = (comments!=null&&comments.size()>0)?GsonUtil.objectToJson(comments):null;
        ajaxResult.setSuccess(StringUtils.isNotBlank(commentStr));
        ajaxResult.setData(commentStr);
        return ajaxResult;
    }

    private String getInput(HttpServletRequest request){
        InputStream inputStream;
        String   json="";
        //获得响应流，获得输入对象
        try{
            inputStream = request.getInputStream();
            //建立接收流缓冲，准备处理
            StringBuffer requestBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            //读入流，并转换成字符串
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                requestBuffer.append(readLine).append("\n");
            }
            reader.close();
            json = requestBuffer.toString();
            //logger.error("RequestInfo:" + xmlInfo);
        } catch(Exception e){
            //logger.error("接收同步消息失败"+e);
        }
        return json;
    }

}
