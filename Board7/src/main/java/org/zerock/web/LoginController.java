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
			out.println("<script>alert('���̵�� �н����带 �Է����ּ���.');history.go(-1);</script>");
			out.flush();
			
		
		} else { 
			
			if ( session.getAttribute("login") != null ){
				// ������ login�̶� ���� ���� �����Ѵٸ�
				session.removeAttribute("login"); // �������� ������ �ش�.
			}
			if(Login.getUserId() != null) {
				if(Login.getUserId().equals(Id)) {
						if(Login.getUserPw().equals(pass)) {
								session.setAttribute("login", Login); // ���ǿ� login���̶� �̸����� MDto ��ü�� ������ ��		
								response.sendRedirect("list");						
						}else {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							out.println("<script>alert('��й�ȣ�� ��ġ ���� �ʽ��ϴ�.');history.go(-1);</script>");
							out.flush();
						}
					} else {					
				}
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('���̵� �ѹ� �� Ȯ�����ּ���.');history.go(-1);</script>");
				out.flush();
			}
		}
		
	}
	
	 @RequestMapping(value="/logout")
	    public String logout(HttpSession session) {
	        session.invalidate(); // ���� ��ü�� ��������
//	      session.removeAttribute("login"); // �ϳ��� �Ϸ��� �̷��� �ص� ��.
	        return "redirect:/main"; 
	    }
	

}

	
	


