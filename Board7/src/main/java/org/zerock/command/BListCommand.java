package org.zerock.command;

import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dao.BDao;
import org.zerock.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		ArrayList<BDto> BDtos = dao.list(0);
		model.addAttribute("list", BDtos);
		
		
	}

	private Object listPage(int page) {
		
		if (page <= 0) {
		    page = 1;
		  }
		  page = (page - 1) * 10;
		  return listPage(page);
		  
		  
		
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
