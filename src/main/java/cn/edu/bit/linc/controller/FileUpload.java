package cn.edu.bit.linc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUpload {

	// 处理文件上传一  
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)  
    public ModelAndView fileUpload(  
            @RequestParam("fileUpload") CommonsMultipartFile file) {  
        // 获取文件类型  
        System.out.println(file.getContentType());  
        // 获取文件大小  
        System.out.println(file.getSize());  
        // 获取文件名称  
        System.out.println(file.getOriginalFilename());  
  
        // 判断文件是否存在  
        if (!file.isEmpty()) {  
            String path = null;
			path = "D:/" + file.getOriginalFilename();
			
            File localFile = new File(path);  
            try {  
                file.transferTo(localFile);  
            } catch (IllegalStateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return new ModelAndView("success"); 
    }
    
    // 处理文件上传二  
    @RequestMapping(value = "fileUpload2", method = RequestMethod.POST)  
    public String fileUpload2(HttpServletRequest request)  
            throws IllegalStateException, IOException {  
        // 设置上下方文  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
                request.getSession().getServletContext());  
  
        // 检查form是否有enctype="multipart/form-data"  
        if (multipartResolver.isMultipart(request)) {  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
  
            Iterator<String> iter = multiRequest.getFileNames();  
            while (iter.hasNext()) {  
  
                // 由CommonsMultipartFile继承而来,拥有上面的方法.  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if (file != null) {  
                    String fileName = "demoUpload_" + file.getOriginalFilename();  
                    String path = "D:/" + fileName;  
  
                    File localFile = new File(path);  
                    file.transferTo(localFile);  
                }  
  
            }  
        }  
        return "success";  
    }  
}
