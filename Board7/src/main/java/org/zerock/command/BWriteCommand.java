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
		// (2) 출력 형태를 지정하기 위해 Formatter를 얻는다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		// (3) 출력형태에 맞는 문자열을 얻는다.
		String datetime = sdf.format(cal.getTime());
		
		System.out.println("datetime : " + datetime);
		//실제 파일을 업로드하기 위한 파일 객체 생성  
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
