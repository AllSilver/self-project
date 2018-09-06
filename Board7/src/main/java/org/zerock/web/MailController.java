package org.zerock.web;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class MailController {
 
  @Autowired
  private JavaMailSender mailSender;
 
  @RequestMapping(value ="/main")
	public String mainView() {

		return "main";
	}
 
 
  // mailSending 코드
  @RequestMapping(value = "/mail/mailSending", method=RequestMethod.POST)
  public void mailSending(HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
	System.out.println("---------mailSending------------");
    String setfrom = "whapkj303@gmail.com";         
    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
    String title   = request.getParameter("title");      // 제목
    String content = request.getParameter("content");    // 내용
   
    if(tomail!=("")) {
    	  if(title!= ("")) {
    		  if(content!= ("")) {
    			  
    			  System.out.println("---------finall------------");
    			  try {
    			      MimeMessage message = mailSender.createMimeMessage();
    			      MimeMessageHelper messageHelper 
    			                        = new MimeMessageHelper(message, true, "UTF-8");
    			 
    			      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
    			      messageHelper.setTo(tomail);     // 받는사람 이메일
    			      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
    			      messageHelper.setText(content);  // 메일 내용
    			    
    			      mailSender.send(message);  
    			   
    			      
    			    } catch(Exception e){
    			      System.out.println(e);
    			    }
    			  
    			  
    	  }else {
    		  System.out.println("---------content------------");
  			
  			response.setCharacterEncoding("UTF-8");
      		response.setContentType("text/html; charset=UTF-8");
      		PrintWriter writer = response.getWriter();
      		writer.println("<script>alert('내용을 입력해주세요.'); location.href='/main';</script>");
    	  }
  }else {
	  System.out.println("---------title------------");
 	 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('제목을 입력해주세요.'); location.href='/main';</script>");
  		} 
    } 
    
    else {
	  System.out.println("---------tomail------------");
	  	
	  	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('받는 사람@ 이메일 입력해주세요.'); location.href='/main';</script>");
}
 
  
    response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert(' ********************** 메일이 전송 되었습니다 **********************'); location.href='/main';</script>");
    
  }
}

