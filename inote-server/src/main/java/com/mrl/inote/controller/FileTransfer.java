package com.mrl.inote.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mrl.inote.common.util.UuId;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileTransfer {

    public static Map<String, String> multipleFileReceive(HttpServletRequest request, String basePath) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory dff = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            dff.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //2、创建一个文件上传解析器
            ServletFileUpload sfu = new ServletFileUpload(dff);
            //监听文件上传进度
            sfu.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
//                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //解决上传文件名的中文乱码
            sfu.setHeaderEncoding("UTF-8");
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是10MB
            sfu.setFileSizeMax(1024 * 1024 * 10);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为30MB
            sfu.setSizeMax(1024 * 1024 * 30);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> items = sfu.parseRequest(request);
            for (FileItem item : items) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    map.put("baseInfo", value);
                }
            }
            for (FileItem item : items) {
                //如果fileitem中封装的是普通输入项的数据
                if (!item.isFormField()) {
                    //如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    // 获取上传字段
                    // 更改文件名为唯一的
                    String filename = item.getName();
                    if (filename == null || filename.trim().equals("")) continue;
                    //得到上传文件的扩展名
                    //String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);//.substring(0,filename.lastIndexOf("."));
                    String fileExtName = FilenameUtils.getExtension(filename);
                    if(StringUtils.isBlank(fileExtName)) continue;
                    if ("/uploads/uploadImg".equals(basePath)) {
//						long curTime=System.currentTimeMillis();
//						filename = curTime+"_"+map.get("baseInfo") + "." + FilenameUtils.getExtension(filename);
                            filename = map.get("baseInfo") + "." + fileExtName;
                    } else {
                        filename = UuId.generateGUID() + "." + fileExtName;
                    }
                    //优化文件命名结构
//					String storeDirectory = request.getSession().getServletContext().getRealPath(basePath);
//					String subStoreDirectory = genericPath(filename,storeDirectory);
//					item.write(new File(storeDirectory + subStoreDirectory, filename));
//					String filePath = subStoreDirectory + "/" + filename;
//					map.put("fileInfo", filePath);
                    //不优化文件命名结构
                    String storeDirectory = request.getSession().getServletContext().getRealPath(basePath);
                    initDir(storeDirectory, filename);
                    item.write(new File(storeDirectory, filename));
                    String filePath = storeDirectory + "/" + filename;
                    map.put("fileInfo", filePath);
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    // 计算文件的存放目录
    private static String genericPath(String filename, String storeDirectory) {
        int hashCode = filename.hashCode();
        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode & 0xf0) >> 4;

        String dir = "/" + dir1 + "/" + dir2;

        File file = new File(storeDirectory, dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param filePath 文件夹路径
     *                 true 文件不存在，false 文件存在不做任何操作
     */
    public static void initDir(String filePath, String fileName) {
        //如果文件夹不存在则创建
        File file = new File(filePath+"/"+fileName);
        if (file.isFile()) {
            System.out.println("//存在");
            file.delete();
        } else {
            System.out.println("//不存在");
        }
    }
}
