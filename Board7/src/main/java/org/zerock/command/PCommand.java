package org.zerock.command;

import java.util.List;

import org.zerock.dto.BDto;

import spring.page.test.Criteria;

public interface PCommand {

	 public List<BDto> listPage(int page) throws Exception;
	 
	 public List<BDto> listCriteria(Criteria cri) throws Exception;
	 
	 public List<BDto> boardList(String searchType, String keyword);
	 
	
}
