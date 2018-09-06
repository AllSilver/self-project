package org.zerock.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.zerock.dto.BDto;
import org.zerock.dto.MDto;

public interface MCommand {
	
	void execute(Model model);
	
	void execute2(Model model);
	
	boolean execute3(Model model);
	
	public MDto loginSession(MDto mdto);

	
	MDto execute6(String userId, String userPw);

	
}
