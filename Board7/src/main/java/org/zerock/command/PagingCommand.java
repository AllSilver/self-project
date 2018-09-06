package org.zerock.command;

import java.util.List;

import org.zerock.dao.BDao;
import org.zerock.dao.MDao;
import org.zerock.dto.BDto;

import spring.page.test.Criteria;

public class PagingCommand implements PCommand{

	@Override
	public List<BDto> listPage(int page) throws Exception {
		if (page <= 0) {
		    page = 1;
		  }
		  page = (page - 1) * 10;
		  return listPage(page);
	}

	@Override
	public List<BDto> listCriteria(Criteria cri) throws Exception {
		Criteria criteria = new Criteria();
		BDao dao = new BDao();
		return ((PCommand) dao).listCriteria(cri);
	}

	@Override
	public List<BDto> boardList(String searchType, String keyword) {
		 System.out.println("BoardService.BoardList( ) Á¢¼ÓµÊ");
	     return BDao.boardList(searchType, keyword);
	}


}
