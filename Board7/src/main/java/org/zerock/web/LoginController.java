package org.zerock.web;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.zerock.dao.MDao;
import org.zerock.dto.MDto;

@Controller
public class LoginController  {
	
	private MDao mdao = new MDao();
	
	
	@RequestMapping(value="/loginPage")
	public String login(){
		return"loginPage";
	}
	
	
	@RequestMapping(value="/loginOk",method=RequestMethod.POST)
	public void gologinOk(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, MDto dto) 
			throws Exception {		
		
		
 		String Id = request.getParameter("userId");
		String pass = request.getParameter("userPw");
		MDto Login = mdao.Login(Id, pass);
	
		System.out.println("userId :" +Id);
		System.out.println("userPw :" +pass);
		
		if (Id != null && Id.equals("")|| pass != null && pass.equals("")) {
			
			System.out.println("Id.equals :" +Id);
			System.out.println("pass.equals :" +pass);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디와 패스워드를 입력해주세요.');history.go(-1);</script>");
			out.flush();
			
		
		} else { 
			
			if ( session.getAttribute("login") != null ){
				// 기존에 login이란 세션 값이 존재한다면
				session.removeAttribute("login"); // 기존값을 제거해 준다.
			}
			if(Login.getUserId() != null) {
				if(Login.getUserId().equals(Id)) {
						if(Login.getUserPw().equals(pass)) {
								session.setAttribute("login", Login); // 세션에 login인이란 이름으로 MDto 객체를 저장해 놈		
								response.sendRedirect("list");						
						}else {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							out.println("<script>alert('비밀번호가 일치 하지 않습니다.');history.go(-1);</script>");
							out.flush();
						}
					} else {					
				}
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('아이디를 한번 더 확인해주세요.');history.go(-1);</script>");
				out.flush();
			}
		}
		
	}
	
	 @RequestMapping(value="/logout")
	    public String logout(HttpSession session) {
	        session.invalidate(); // 세션 전체를 날려버림
//	      session.removeAttribute("login"); // 하나씩 하려면 이렇게 해도 됨.
	        return "redirect:/main"; 
	    }
	

}

	
	


