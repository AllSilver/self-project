<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
 
<%
    String filename = request.getParameter("file.getOriginalFilename()") + ".pdf";
    String file_location = "D:\\uploads\\";
    Calendar cal = Calendar.getInstance();
	// (2) ��� ���¸� �����ϱ� ���� Formatter�� ��´�.
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
	// (3) ������¿� �´� ���ڿ��� ��´�.
	String datetime = sdf.format(cal.getTime());
	
	System.out.println("datetime : " + datetime);
	//���� ������ ���ε��ϱ� ���� ���� ��ü ����  
	
	File f = new File("D:\\uploads\\");	
    File file = null;
    BufferedInputStream fin = null;
    BufferedOutputStream outs = null;
 
    try{
       
        file = new File(file_location, filename);
        response.reset();
 
        response.setHeader("Content-Type","application/pdf");
        response.setHeader("Content-Disposition","attachment;filename="+";");
 
        if(file != null){
            fin = new BufferedInputStream(new FileInputStream(file));
            outs = new BufferedOutputStream(response.getOutputStream());
 
            int read = 0;
 
            while((read = fin.read()) != -1 ){
                outs.write(read);
            }
        }
 
    }catch(Exception e){
        response.setContentType("text/html;charset=euc-kr");
        out.println("<script type='text/javascript'>");
        out.println("alert('���� ���� �� ������ �߻��Ͽ����ϴ�.');");
        out.println("</script>");
    }finally{
 
        if(outs != null) fin.close();
        if(fin != null) outs.close();
 
    }
   
%>