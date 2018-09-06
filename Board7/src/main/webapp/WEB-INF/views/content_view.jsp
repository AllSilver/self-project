<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-li.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-theme.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap-theme.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/js/bootstrap.js" />" rel="stylesheet">
<link href="<c:url value="/js/bootstrap.min.js" />" rel="stylesheet">
<link href="<c:url value="/js/npm.js" />" rel="stylesheet">
 <script>
 
function loginout(){
		 alert("로그아웃 되었습니다.") 
		 location.href="logout";  
}  

function onDownload(){
	
	 location.href="/download";  
}  
</script>

<title>Home</title>
<script src="//cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>
</head>
<body>
<iframe id="ifrm_filedown"  style="position:absolute; z-index:1;visibility : hidden;"></iframe>

	<div class="container">
		<div class="jumbotron">

			<nav class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="">JSP 게시판 웹 사이트</a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="">메인</a></li>
						<li class="active"><a href="list">게시판</a></li>
					</ul>
				
					<ul class="nav navbar-nav   navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">마이페이지<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li class="active"><a href="">- 내 정보</a></li>
								<li class="active"><a href="#" onclick="loginout()">- 로그아웃</a></li>
							</ul></li>
					</ul>

				</div>
			</nav>
			<div class="container"></div>
			<div class="row"></div>
			<table class="table table-striped">
			
				<form commandName="BDto" action="" method="post" >
					<input type="hidden" name="bId" value="${content_view.bId}"/>
					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center;">글보기</th>
						</tr>
						</thead>
						<tbody>
					<tr>
						<td align="left">번호 : ${content_view.bId}</td>
					</tr>
					<tr>
						<td align="left">조회수 : ${content_view.bHit}</td>
					</tr>
						<tr>
						<td align="left">작성자 : ${content_view.bName}</td>
						</tr>
						<tr>
						<td align="left">제목 : ${content_view.bTitle}</td>
						</tr>
						<tr>
						<td>${content_view.bContent}</td>
						</tr>
						<tr>
						<td>첨부 파일 :<a href="file.pdf" download="${content_view.filename}">${content_view.filename}</a></td>
					<%-- 	<td>첨부 파일 :<a href="/download?filename=${content_view.filename}">${content_view.filename}</a></td>  --%> 
					<%-- 	 <td>첨부 파일 :<a href="#" onclick="onDownload()">${content_view.filename}</a></td>  --%>
						<tr>
				</tbody>
			</table>
			<tr>
			<td colspan="2" align="right">
				 <a href="list" >목록보기</a> &nbsp;&nbsp; 
				 
				 <c:if test="${MDto.userId eq content_view.bName}"> 
				<a href="delete?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp; 
				<a href="change_content?bId=${content_view.bId}" >수정하기</a>&nbsp;&nbsp; </td>
		    	</c:if>
		    	
				<a href="replyForm?bId=${content_view.bId}&bTitle=${content_view.bTitle}">답변</a>&nbsp;&nbsp; 
				</tr>
	
			</form>
			<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
			<script src="js/bootstrap.js"></script>
		</div>
	</div>
</body>
</html>