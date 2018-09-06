package spring.page.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileHelper {
	 public static String upload(String uploadPath, MultipartFile multipartFile, HttpServletRequest request) {
	        
		 	String uploadedFileUrl = null;
	        String rootPath = request.getSession().getServletContext().getRealPath("/");
	        String realUploadPath = rootPath + "/resources" + uploadPath;
	        File dir = new File(realUploadPath);
	        if (!dir.exists())
	            dir.mkdirs();
	        File file = new File(dir.getAbsolutePath() + File.separator + multipartFile.hashCode()
	                + multipartFile.getOriginalFilename());
	        try {
	            multipartFile.transferTo(file);
	            String contextPath = request.getContextPath();
	            uploadedFileUrl = contextPath + uploadPath + File.separator + file.getName();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return uploadedFileUrl;
	    }

}


