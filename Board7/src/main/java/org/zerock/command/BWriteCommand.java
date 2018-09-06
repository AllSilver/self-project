package org.zerock.command;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dao.BDao;
import org.zerock.dto.BDto;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = model.asMap();
		Calendar cal = Calendar.getInstance();
		// (2) ��� ���¸� �����ϱ� ���� Formatter�� ��´�.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		// (3) ������¿� �´� ���ڿ��� ��´�.
		String datetime = sdf.format(cal.getTime());
		
		System.out.println("datetime : " + datetime);
		//���� ������ ���ε��ϱ� ���� ���� ��ü ����  
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String filen = file.getOriginalFilename();
		File f = new File("D:\\uploads\\"+"_"+filen);	
		String filepa = "D:\\uploads\\"+file.getOriginalFilename()+"_"+datetime ;
		int fileSize = (Integer) null ;
		String fileKey = "D:\\uploads\\"+file.getOriginalFilename()+"_"+datetime ;
		
		
		BDao dao = new BDao();
		dao.write_view(bName, bTitle, bContent, filen, filepa, fileSize, fileKey);
		
	
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
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}
	


}
