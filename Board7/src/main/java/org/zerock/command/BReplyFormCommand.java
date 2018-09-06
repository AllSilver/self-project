package org.zerock.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dao.BDao;
import org.zerock.dto.BDto;

public class BReplyFormCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
	}
	@Override
	public BDto execute4(int bId) {
		BDao dao = new BDao();
		String strbId2 = ""+bId; 
		BDto dto = dao.replyForm(strbId2);		
		return dto;
	}
	@Override
	public BDto execute2(int bId) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public BDto execute3(int bHit) {
		// TODO Auto-generated method stub
				return null;
	}
	@Override
	public BDto execute5(int bId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void execute(Model model, MultipartFile file) {
		// TODO Auto-generated method stub
		
	}


}
