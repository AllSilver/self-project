package org.zerock.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.command.BCommand;
import org.zerock.command.BContentCommand;
import org.zerock.command.MCommand;
import org.zerock.command.MLoginCommand;
import org.zerock.dao.BDao;
import org.zerock.dao.MDao;
import org.zerock.dto.BDto;
import org.zerock.dto.MDto;

import com.mysql.jdbc.log.Log;

@Controller
public class MemberController  {
	
	private MDao mdao = new MDao();
	
    @RequestMapping(value = "/joining",method=RequestMethod.GET)
    public String serverSide(@ModelAttribute MDto mdto,HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	System.out.println("�̻���� �Ѥ�;;;");	
    	
    	return "joining";
    }
   
    
    @RequestMapping(value = "/joining",method=RequestMethod.POST)
    public String outSider(@ModelAttribute @Validated MDto mdto,BindingResult bindingResult,
    		HttpServletRequest request, HttpServletResponse response)throws Exception {
    	
    	String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName= request.getParameter("userName");
		String userEmail = request.getParameter("userEmail"); 
		
		System.out.println("���� ���� : " + bindingResult.hasErrors());
		System.out.println(bindingResult.getFieldError());
		System.out.println("�������̵�");
		MDto Login = mdao.Login(userId, userPw);
		System.out.println(Login.getUserId()+" : login Userid");

					if(!bindingResult.hasErrors())
					{	
						System.out.println( " userId : " + userId );
						if(Login.getUserId() != null )
						{
							if(Login.getUserId().equals(userId))
							{
									System.out.println("���� ó������");
									response.setContentType("text/html; charset=UTF-8");
									PrintWriter out = response.getWriter();
									out.println("<script>alert('�����ϴ� ���̵��Դϴ� .');</script>");
									out.flush();
									
									return "/joining";	
							}
							else{	}
						}
						else
								{		mdao.joining(userId, userPw, userName, userEmail);
										response.setContentType("text/html; charset=UTF-8");
										PrintWriter out = response.getWriter();
										out.println("<script>alert('���ԿϷ� �Ǿ����ϴ�.')a href='loginPage';</script>");
										out.flush();
										return "loginPage";
									}  
							} 
	    System.out.println("������ �Է���");
	    return "joining";
    }
		
    

}

	
	

