package org.zerock.command;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.zerock.dao.BDao;
import org.zerock.dao.MDao;
import org.zerock.dto.BDto;
import org.zerock.dto.MDto;

@Service
public class MLoginCommand implements MCommand {
	
	private String userId;
	

	@Override
	public void execute(Model model) {
		MDao mdao = new MDao();
		ArrayList<MDto> MDtos = mdao.Login;
		model.addAttribute("Login", MDtos);
	
	}
	

	@Override
	public MDto execute6(String userId, String userPw) {
		MDao dao = new MDao();
		String struserId = ""+userId; 
		String struserPw = ""+userPw; 
		MDto dto = dao.Login(struserId,struserPw);		
		
		return dto;
	}


	@Override
	public void execute2(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		MDao dao = new MDao();
		dao.joining(userId, userPw, userName, userEmail);
		
	}


	@Override
	public boolean execute3(Model model) {
		MDao dao = new MDao();
		String userId2 = ""+userId; 
		boolean dto = dao.joinOk(userId2);		
		
		return dto;
	}


	@Override
	public MDto loginSession(MDto mdto) {
		// TODO Auto-generated method stub
		MDao mdao = new MDao();
		return mdao.loginSession(mdto);
	}
	
	
}
