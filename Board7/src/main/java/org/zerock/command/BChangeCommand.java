package org.zerock.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dao.BDao;
import org.zerock.dto.BDto;

public class BChangeCommand implements BCommand {

	@Override
	public void execute(Model model) {
	
	}

	@Override
	public BDto execute2(int bId) {
		BDao dao = new BDao();
		String strbId2 = ""+bId; 
		BDto dto = dao.content_view(strbId2);		
		return dto;
	}

	@Override
	public BDto execute3(int bHit) {
		BDao dao = new BDao();
		String Hit = ""+bHit; 
		BDto dto = dao.content_view(Hit);		
		return dto;
	}

	@Override
	public BDto execute4(int bId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BDto execute5(int bId) {
		BDao dao = new BDao();
		String strbId2 = ""+bId; 
		BDto dto = dao.change_content(strbId2);		
		return dto;
	}

	@Override
	public void execute(Model model, MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

}

