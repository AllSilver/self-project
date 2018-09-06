<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
    // 1: 기존의 세션 데이터를 모두 삭제
    session.invalidate();
     // 2: 로그인 페이지로 이동시킴.
    response.sendRedirect("main");
%>

</body>
</html>