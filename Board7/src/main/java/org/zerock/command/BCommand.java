package org.zerock.command;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dto.BDto;

public interface BCommand {
		
		void execute(Model model);
		BDto execute2(int bId);
		BDto execute3(int bHit);
		BDto execute4(int bId);
		BDto execute5(int bId);
		void execute(Model model, MultipartFile file);
		

		
	}

