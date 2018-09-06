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
 
 
  // mailSending �ڵ�
  @RequestMapping(value = "/mail/mailSending", method=RequestMethod.POST)
  public void mailSending(HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
	System.out.println("---------mailSending------------");
    String setfrom = "whapkj303@gmail.com";         
    String tomail  = request.getParameter("tomail");     // �޴� ��� �̸���
    String title   = request.getParameter("title");      // ����
    String content = request.getParameter("content");    // ����
   
    if(tomail!=("")) {
    	  if(title!= ("")) {
    		  if(content!= ("")) {
    			  
    			  System.out.println("---------finall------------");
    			  try {
    			      MimeMessage message = mailSender.createMimeMessage();
    			      MimeMessageHelper messageHelper 
    			                        = new MimeMessageHelper(message, true, "UTF-8");
    			 
    			      messageHelper.setFrom(setfrom);  // �����»�� �����ϰų� �ϸ� �����۵��� ����
    			      messageHelper.setTo(tomail);     // �޴»�� �̸���
    			      messageHelper.setSubject(title); // ���������� ������ �����ϴ�
    			      messageHelper.setText(content);  // ���� ����
    			    
    			      mailSender.send(message);  
    			   
    			      
    			    } catch(Exception e){
    			      System.out.println(e);
    			    }
    			  
    			  
    	  }else {
    		  System.out.println("---------content------------");
  			
  			response.setCharacterEncoding("UTF-8");
      		response.setContentType("text/html; charset=UTF-8");
      		PrintWriter writer = response.getWriter();
      		writer.println("<script>alert('������ �Է����ּ���.'); location.href='/main';</script>");
    	  }
  }else {
	  System.out.println("---------title------------");
 	 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('������ �Է����ּ���.'); location.href='/main';</script>");
  		} 
    } 
    
    else {
	  System.out.println("---------tomail------------");
	  	
	  	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('�޴� ���@ �̸��� �Է����ּ���.'); location.href='/main';</script>");
}
 
  
    response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert(' ********************** ������ ���� �Ǿ����ϴ� **********************'); location.href='/main';</script>");
    
  }
}

