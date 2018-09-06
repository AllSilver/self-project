package org.zerock.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dao.BDao;
import org.zerock.dto.BDto;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String bId = request.getParameter("bId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao bdao = new BDao();
		bdao.modify(bId,bTitle,bContent);
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
	public BDto execute4(int bId) {
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