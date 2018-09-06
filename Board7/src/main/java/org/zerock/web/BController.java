package org.zerock.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.command.BChangeCommand;
import org.zerock.command.BCommand;
import org.zerock.command.BContentCommand;
import org.zerock.command.BDeleteCommand;
import org.zerock.command.BListCommand;
import org.zerock.command.BModifyCommand;
import org.zerock.command.BReplyFormCommand;
import org.zerock.command.PagingCommand;
import org.zerock.command.SCommand;
import org.zerock.command.SessionCommand;
import org.zerock.dao.BDao;
import org.zerock.dao.MDao;
import org.zerock.dto.BDto;
import org.zerock.dto.MDto;
import org.zerock.dto.PageMaker;



@Controller
public class BController {


	private static final int ArrayList = 0;
	private static final int BDto = 0;
	BContentCommand bcommand= null;
	BListCommand blistcommand = null;
	BCommand command = null;
	SCommand Scommand = null;
	BListCommand listcommand = null;
	
	private BDto bdto = new BDto();
	private BDao bdao = new BDao();
	private String downloadView;
	
	 @RequestMapping(value="/boardList.action")
	    public ModelAndView list(@RequestParam(value ="pagenum", defaultValue ="1") int page, HttpServletRequest request,
	            @RequestParam(required=false) String searchType, @RequestParam(required=false) String keyword, Model model)
	    {
		 	 BDto bdto = new BDto();
			 BDao bdao = new BDao();
		 
		 	 model.addAttribute("request",request);
	         PageMaker pagemaker = new PageMaker();
	       //  ArrayList<BDto> contentnum = bdao.list(page); 
	         List<BDto> contentnum = bdao.boardList(searchType, keyword); 
	         
	         List<BDto> list = BDao.boardList(searchType, keyword);
	         PagingCommand pagingCommand = new PagingCommand();
	         
	         System.out.println("contentnum :" + contentnum);
	         System.out.println("contentnum 2:" + searchType);
	         System.out.println("contentnum 3:" + keyword);
	         
	         int cpagenum = page;
	         System.out.println("cpagenum : " + cpagenum);
	         //content
	         int ccontentnum = Integer.parseInt("10");//int ������ �� �������� � ������ �� ����ȯ
	         System.out.println("ccontentnum : " + ccontentnum);
	        
	     /*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
	         pagemaker.setTotalcount(cpagenum);//��ü �Խñ� ���� �����Ѵ�
	         System.out.println(bdao.TotalCount()+"��ü �Խñ� ������ �?");
	         System.out.println("setTotalcount : " + bdao.list(cpagenum));
	         pagemaker.setPagenum(cpagenum-1);//���� �������� ������ ��ü�� �ٽ� �������ش�//��� ���������� PageMaker�� �����Ѵ�
	         System.out.println("setPagenum : " + (cpagenum-1));
	         pagemaker.setContentnum(ccontentnum);//�� �������� ��� �������� �����Ѵ�
	         System.out.println("setContentnum : " + (ccontentnum));
	         pagemaker.setCurrentblock(cpagenum);//���� ����������� ������� ���� ������ ��ȣ�� ���ؼ� �����Ѵ�
	         System.out.println("setCurrentblock : " + (cpagenum));
	         pagemaker.setLastblock(pagemaker.getTotalcount());//������ ��� ��ȣ�� ��ü �Խñ� ���� ���ؼ� ���Ѵ�
	         System.out.println("setLastblock : " + (pagemaker.getTotalcount()));
	     /*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
	         
	         pagemaker.prevnext(cpagenum);//���� ������ ��ȣ�� ȭ��ǥ ��Ÿ���� �����Ѵ�
	         System.out.println("prevnext : " + (cpagenum));
	         pagemaker.setStartPage(pagemaker.getCurrentblock());//���������� ��ȣ�� ���� ������ ������� ���Ѵ�
	         System.out.println("setStartPage : " + (pagemaker.getCurrentblock()));
	         pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
	       //  System.out.println("setEndPage : " + (pagemaker.getLastblock(),pagemaker.getCurrentblock()));
	         //���� ��� ��ȣ�� ������ ��� ��ȣ�� ������ �����ϰ� ������ ����� ������ ��ȣ�� �����Ѵ�
	         
	       
		
			 ModelAndView mv = new ModelAndView();
	
	         mv.addObject("dao", new PagingCommand());
	         mv.addObject("keyWord", keyword);
	         mv.addObject("searchType", searchType);
			 mv.addObject("page", pagemaker);
			 mv.addObject("list", list);
			 mv.setViewName("list");
	        
	         return mv;
	    }

	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public  ModelAndView list(@RequestParam(value ="pagenum", defaultValue ="1") int page, 
			HttpServletRequest request) throws Exception {
		BDto bdto = new BDto();
		BDao bdao = new BDao();
		//��ܿ� �Ķ���� ���� page => pagenum
		
		
        PageMaker pagemaker = new PageMaker();
       // String contentnum = request.getParameter("list");//�� �������� � ������
        ArrayList<BDto> contentnum = bdao.list(page); 
        System.out.println("contentnum :" + contentnum);
        int cpagenum = page;
        System.out.println("cpagenum : " + cpagenum);
        //content
        int ccontentnum = Integer.parseInt("10");//int ������ �� �������� � ������ �� ����ȯ
        System.out.println("ccontentnum : " + ccontentnum);
       
    /*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
        pagemaker.setTotalcount(cpagenum);//��ü �Խñ� ���� �����Ѵ�
        System.out.println(bdao.TotalCount()+"��ü �Խñ� ������ �?");
        System.out.println("setTotalcount : " + bdao.list(cpagenum));
        pagemaker.setPagenum(cpagenum-1);//���� �������� ������ ��ü�� �ٽ� �������ش�//��� ���������� PageMaker�� �����Ѵ�
        System.out.println("setPagenum : " + (cpagenum-1));
        pagemaker.setContentnum(ccontentnum);//�� �������� ��� �������� �����Ѵ�
        System.out.println("setContentnum : " + (ccontentnum));
        pagemaker.setCurrentblock(cpagenum);//���� ����������� ������� ���� ������ ��ȣ�� ���ؼ� �����Ѵ�
        System.out.println("setCurrentblock : " + (cpagenum));
        pagemaker.setLastblock(pagemaker.getTotalcount());//������ ��� ��ȣ�� ��ü �Խñ� ���� ���ؼ� ���Ѵ�
        System.out.println("setLastblock : " + (pagemaker.getTotalcount()));
    /*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
        
        pagemaker.prevnext(cpagenum);//���� ������ ��ȣ�� ȭ��ǥ ��Ÿ���� �����Ѵ�
        System.out.println("prevnext : " + (cpagenum));
        pagemaker.setStartPage(pagemaker.getCurrentblock());//���������� ��ȣ�� ���� ������ ������� ���Ѵ�
        System.out.println("setStartPage : " + (pagemaker.getCurrentblock()));
        pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
      //  System.out.println("setEndPage : " + (pagemaker.getLastblock(),pagemaker.getCurrentblock()));
        //���� ��� ��ȣ�� ������ ��� ��ȣ�� ������ �����ϰ� ������ ����� ������ ��ȣ�� �����Ѵ�
        
    
        ModelAndView mv = new ModelAndView();
        
		mv.addObject("page", pagemaker);
		mv.addObject("list", contentnum);
		mv.setViewName("list");
		return mv;
	}
	

	@RequestMapping("/write_view")
	public ModelAndView write_view(Model model, HttpSession session) {
		System.out.println("-----------------------write_view() ȣ��------------------------");

		MDto mdto = (MDto) session.getAttribute("login");
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("write_view");
		view.addObject("MDto", mdto);
		return view;
	}
	
	@RequestMapping(value="/writeOk", method=RequestMethod.GET)
	public ModelAndView gowriteOk2(@RequestParam(value="userName") String userName, HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception  {
		
		System.out.println("replyForm userName check : " + userName);
		model.addAttribute("request",request);
		Scommand = new SessionCommand();
		MDto mdto = Scommand.execute(userName);
		
		
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("write_view");    
	    mav.addObject("write_view", mdto);
	    return mav;
	}

	
	
	@RequestMapping(value="/writeOk",method=RequestMethod.POST)
	public ModelAndView gowriteOk(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response ) 
			throws IOException {		
		BDto bdto = new BDto();
		BDao bdao = new BDao();
		
		ModelAndView mav = new ModelAndView();

		Calendar cal = Calendar.getInstance();
		// (2) ��� ���¸� �����ϱ� ���� Formatter�� ��´�.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		// (3) ������¿� �´� ���ڿ��� ��´�.
		String datetime = sdf.format(cal.getTime());
		
		System.out.println("datetime : " + datetime);
		//���� ������ ���ε��ϱ� ���� ���� ��ü ����  
		
		File f = new File("D:\\uploads\\"+"_"+file.getOriginalFilename());	

 		System.out.println("Post method");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent= request.getParameter("bContent");
		String filen = file.getOriginalFilename();
		String filepa =  "D:\\uploads\\"+file.getOriginalFilename()+"_"+datetime;
		byte [] bytes = file.getBytes ();
		int fileSize = bytes.length ;
		String fileKey = "D:\\uploads\\"+file.getOriginalFilename()+"_"+datetime ;
		
		if(f.exists()) {
			f = new File("D:\\uploads\\"+file.getOriginalFilename()+"_"+datetime);
			//�ѹ��� ���ؼ� ������ ������ �����ϸ� �̸��� (1) ,
			//(���߿�)2��°���� �˻��ؼ� �̸��� ���غ���, Ȯ���� �տ� �ٸ� �̸��� �߰��ϵ��� �غ��� 
		}  try {
			file.transferTo(f);
			System.out.println("f :" +f);
			bdto.setFilename(file.getOriginalFilename());
			System.out.println("filename :" +file.getOriginalFilename());
			bdto.setFilepath(filepa);
			System.out.println("filepath2 :" +filepa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (bTitle != null && bTitle.equals("")||bContent != null && bContent.equals("")) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('����� ������ �Է����ּ���.');history.go(-1);</script>");
			out.flush();
		} else { 
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("list");
			bdao.write_view(bName, bTitle, bContent, filen, filepa, fileSize, fileKey);
			response.sendRedirect("list");
		}
		
		mav.setViewName("list");
		return mav;
		
	} 
	

	@RequestMapping(value="/content_view", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView content_view(@RequestParam(value="bId", defaultValue="3") int bId,
		HttpServletRequest request,Model model, HttpSession session) 
		throws Exception {
		System.out.println("---------content_view()------------");
		System.out.println("bid check : " + bId);
		
		MDto mdto = (MDto) session.getAttribute("login");	
		// MDto�� �ҷ� ���°�!!
		model.addAttribute("request",request);
		command = new BContentCommand();
		
		BDto content = command.execute2(bId);
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("content_view");
		view.addObject("MDto", mdto);
		view.addObject("content_view", content);
		
		return view;
	}
	
	@RequestMapping(value="/downloadView", method = RequestMethod.GET)
	    private void downloadView() {
		
	};

	@RequestMapping(value="/download", method = RequestMethod.GET)
	public ModelAndView download() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("download");
		Calendar cal = Calendar.getInstance();
		// (2) ��� ���¸� �����ϱ� ���� Formatter�� ��´�.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		// (3) ������¿� �´� ���ڿ��� ��´�.
		String datetime = sdf.format(cal.getTime());
	
		
		File downloadFile = new File("download");
        mav.addObject("datetime", downloadFile);

        return mav;

    }
	
	@RequestMapping(value="/change_content", method=RequestMethod.GET)
	public ModelAndView chang_content(@RequestParam(value="bId", defaultValue="3") int bId, HttpServletRequest request,Model model) 
			throws Exception {
		
		model.addAttribute("request",request);
		command = new BChangeCommand();
		BDto content = command.execute5(bId);
		
		 ModelAndView view = new ModelAndView();
		 
		 view.setViewName("change_content");
		 view.addObject("change_content", content);
		
		
		return view;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("---------modify() ȣ��------------");
		
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("---------delete() ȣ��------------");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
		
	}
	
	@RequestMapping(value="/replyForm", method=RequestMethod.GET)
	public ModelAndView replyForm(@RequestParam(value="bId", defaultValue="3") int bId,HttpServletRequest request, HttpServletResponse response,Model model) throws Exception  {
		
		System.out.println("replyForm bid check : " + bId);
		model.addAttribute("request",request);
		command = new BReplyFormCommand();
		BDto dto = command.execute4(bId);
		
		
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("replyForm");    
	    mav.addObject("replyForm", dto);
	    return mav;
	}

	
	
	@RequestMapping(value="replyOk" ,method=RequestMethod.POST)
	public void replyOk(HttpServletRequest request, HttpServletResponse response ) throws IOException {		
		System.out.println("--------------------replyOk()-------------------");
		
		String bName = request.getParameter("bName");
		String bTitle= request.getParameter("bTitle");   
		String bContent= request.getParameter("bContent");  
		String bHit= request.getParameter("bHit");  
		String bGroup= request.getParameter("bGroup");
		String bStep= request.getParameter("bStep");
		String bIndent= request.getParameter("bIndent");
		String filename= request.getParameter("filename");
		String filepath= request.getParameter("filepath");
		 
		System.out.println("bName :" +bName);
		System.out.println("bTitle :" +bTitle);
		System.out.println("bContent :" +bContent);
		
		if (bName != null  && bName.equals("")|| bContent != null  && bContent.equals("")) {
			
			System.out.println("bName :" +bName);
			System.out.println("bContent :" +bContent);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�ۼ��ڿ� ������ �Է����ּ���.');history.go(-1);</script>");
			out.flush();
			
		}
	
		else { 
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("list");
		bdao.replyOk(bName, bTitle, bContent, bGroup, bStep, bIndent,filename, filepath);
		response.sendRedirect("list");
		System.out.println("--------------------replyOk finally()-------------------");
		}
		
	}
	
	private ArrayList<BDto> BContentCommand(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@ModelAttribute("BDto")
	public BDto formBacking() {
		return new BDto();
	}

	@RequestMapping("/Board_List")
	public String Board_List(Model model) {
		System.out.println("-----------------------write_view() ȣ��------------------------");

		return "Board_List";
	}
	
	@RequestMapping(value = "/community/imageUpload.do", method = RequestMethod.POST) 
    public void communityImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) { 
  
System.out.println("---------------------- ck ���� �̹��� ���ε�------------------------"); 


        OutputStream out = null; 
        PrintWriter printWriter = null; 
        response.setCharacterEncoding("utf-8"); 
        response.setContentType("text/html;charset=utf-8"); 
  
        try{ 
  
            String fileName = upload.getOriginalFilename(); 
            byte[] bytes = upload.getBytes(); 
            
            String uploadPath ="D:\\temp\\"+fileName; //������ 
            System.out.println("uploadPath:"+uploadPath); 
     
             
            out = new FileOutputStream(new File(uploadPath)); 
            out.write(bytes); 
            String callback = request.getParameter("CKEditorFuncNum"); 
            System.out.println("callback:"+callback); 
             
            printWriter = response.getWriter(); 
           
            String fileUrl = "http://localhost:8083/image2/" + fileName; //url��� 
            System.out.println("fileUrl :" + fileUrl); 
  
            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" 
                    + callback 
                    + ",'" 
                    + fileUrl 
                    + "','�̹����� ���ε� �Ͽ����ϴ�.'" 
                    + ")</script>"); 
            printWriter.flush(); 
  
        }catch(IOException e){ 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (out != null) { 
                    out.close(); 
                } 
                if (printWriter != null) { 
                    printWriter.close(); 
                } 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
  
        return; 
    } 
}



