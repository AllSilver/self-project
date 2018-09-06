package org.zerock.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface FileCommand {

	//public void insertBoard(Map<String, Object> map, HttpServletRequest request, Model model) throws Exception;


	public void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	
}
