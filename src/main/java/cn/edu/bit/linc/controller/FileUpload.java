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

	// �����ļ��ϴ�һ  
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)  
    public ModelAndView fileUpload(  
            @RequestParam("fileUpload") CommonsMultipartFile file) {  
        // ��ȡ�ļ�����  
        System.out.println(file.getContentType());  
        // ��ȡ�ļ���С  
        System.out.println(file.getSize());  
        // ��ȡ�ļ�����  
        System.out.println(file.getOriginalFilename());  
  
        // �ж��ļ��Ƿ����  
        if (!file.isEmpty()) {  
            String path = null;
			path = "D:/" + file.getOriginalFilename();
			//String path=getSession().getServletContext().getRealPath("upload/img/" + file.getOriginalFilename());
			
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
    
    // �����ļ��ϴ���  
    @RequestMapping(value = "fileUpload2", method = RequestMethod.POST)  
    public String fileUpload2(HttpServletRequest request)  
            throws IllegalStateException, IOException {  
        // �������·���  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
                request.getSession().getServletContext());  
        
        System.out.println(request.getParameter("text"));
  
        // ���form�Ƿ���enctype="multipart/form-data"  
        if (multipartResolver.isMultipart(request)) {  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
  
            Iterator<String> iter = multiRequest.getFileNames();  
            while (iter.hasNext()) {  
  
                // ��CommonsMultipartFile�̳ж���,ӵ������ķ���.  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if (file != null) {  
                    String fileName = file.getOriginalFilename();  
                    String path = request.getSession().getServletContext().getRealPath("/resourses/imgUpload/") + fileName;  
                    System.out.println(path);
  
                    File localFile = new File(path);  
                    file.transferTo(localFile);  
                }  
  
            }  
        }  
        return "success";  
    }  
}
