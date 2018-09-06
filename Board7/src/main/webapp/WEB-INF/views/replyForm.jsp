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
</script>
<title>Home</title>
<script src="//cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>
</head>
<body>

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
					<a class="navbar-brand" href="main">JSP 게시판 웹 사이트</a>
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
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<form commandName="BDto" action="replyOk" method="post">
					<input type="hidden" name="bId" value="${replyForm.bId}" />
					<input type="hidden" name="bGroup" value="${replyForm.bGroup}" />
					<input type="hidden" name="bStep" value="${replyForm.bStep}" />
					<input type="hidden" name="bIndent" value="${replyForm.bIndent}" />
					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center;">답변하기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right">번호 : ${replyForm.bId}</td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글 제목" name="bTitle" value="       L  reply :  ${replyForm.bTitle}"
								readonly="readonly" maxlength="50"></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="작성자" name="bName" maxlength="60"></td>
						</tr>
							<tr>					
							<td>	
							<!-- <script src="http://cdn.ckeditor.com/4.7.0/standard-all/ckeditor.js"></script> -->
							<textarea id="editor1" name="bContent"  row="10" cols="80"></textarea> 
							
							<script>
							
									CKEDITOR.replace( 'editor1', {
									    filebrowserImageUploadUrl :'/community/imageUpload.do'
									    } );
									
									 CKEDITOR.on('dialogDefinition', function( ev ){
								            var dialogName = ev.data.name;
								            var dialogDefinition = ev.data.definition;
								          
								            switch (dialogName) {
								                case 'image': //Image Properties dialog
								                    //dialogDefinition.removeContents('info');
								                    dialogDefinition.removeContents('Link');
								                    dialogDefinition.removeContents('advanced');
								                    break;
								            }
								        });
									
									</script>
									</td> 
					    </td>
					    	</tr>
					</tbody>
			</table>
			<tr>
				<td colspan="2" align="right">
				<input type="submit" class="btn btn-primary pull-right" value="답변전송">
					&nbsp;&nbsp; <a href="list">목록보기</a> &nbsp;&nbsp;</td>
			</tr>
			</form>
			<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
			<script src="js/bootstrap.js"></script>
		</div>
	</div>
</body>
</html>