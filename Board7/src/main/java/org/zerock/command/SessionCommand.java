package org.zerock.command;

import org.zerock.dao.MDao;
import org.zerock.dto.MDto;

public class SessionCommand implements SCommand {

	@Override
	public MDto execute(String userName) {
		MDao dao = new MDao();
		String strName = ""+userName; 
		MDto dto = dao.write(strName);		
		return dto;
	}


}
