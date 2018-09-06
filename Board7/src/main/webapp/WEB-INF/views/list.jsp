<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

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

function page(idx){
    var pagenum = idx;
    var contentnum = $("#contentnum option:selected").val();                
    location.href="${pageContext.request.contextPath}/list?pagenum="+pagenum+"&contentnum="+contentnum;    
}
</script>
<title>Home</title>
</head>
<body>


	<div class="container">
		<div class="jumbotron">
			<%--     <h1>Hello world!</h1>
            <p>The time on the server is ${serverTime}.</p> --%>
			<nav class="navbar navbar-default">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
										<a class="navbar-brand" href="">JSP 게시판 웹 사이트    
												<c:if test="$[msg == 'success']">
												<h2>${sesstionScope.userName}님 반갑습니다!!</h2>
												</c:if>
										</a>
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
				<form action="" method="Post">
					<input type="hidden" name="bId" value="${content_view.bId}">
					<input type="hidden" name="bGroup" value="${replyForm.bGroup}" />
					<input type="hidden" name="bStep" value="${replyForm.bStep}" />
					<input type="hidden" name="bIndent" value="${replyForm.bIndent}" />
					<thead>
				</form>	
					
					<form method="GET" action="/boardList.action"> 
					<select id="searchType" name="searchType"> 
					<option value="bTitle" <c:if test ="${'bTitle'==searchType }"></c:if>>제목</option> 
					<option value="bContent" <c:if test="${'bContent'==searchType }"></c:if>>내용</option> 
					<option value="bName"  <c:if test="${'bName'==searchType }"></c:if>>글쓴이</option> 
					</select> 
					<input type="text" style="text-align: left; padding-right:1px;" 
					value="${keyword}" name="keyword"/> 
					<input type="submit" value="검색">
					</form> 
				
	
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">번호</th>

							<th style="background-color: #eeeeee; text-align: center;">글쓴이</th>

							<th style="background-color: #eeeeee; text-align: center;">제목</th>

							<th style="background-color: #eeeeee; text-align: center;">작성일</th>

							<th style="background-color: #eeeeee; text-align: center;">조회수</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="BDto">
							<tr>
								<td>${BDto.bId}</td>
								<td align="left">${BDto.bName}</td>
								<td align="left">
								<c:forEach begin="1" end="${BDto.bIndent}">ㄴ</c:forEach>
								<a href='content_view?bId=${BDto.bId}&bHit=${BDto.bHit}'>${BDto.bTitle}</a>
								</td>
								<td>${BDto.bDate}</td>
								<td><span class="backge be-red">${BDto.bHit}</span></td>
							</tr>
						</c:forEach>
					</tbody>
					 <tfoot >
            <tr>
            

                <td colspan="5">
                    <!-- 왼쪽 화살표 -->
                    <c:if test="${page.prev}">
                        <a style="text-decoration:none;" href="javascript:page(${page.startPage-1});" >&laquo;</a>
                    </c:if>
                    
                    <!-- 페이지 숫자 표시 -->
                    <c:forEach begin="${page.startPage}" end="${page.endPage}" var="idx">
                        <a style="text-decoration: none;" href="javascript:page(${idx});">${idx}</a>
                    </c:forEach>
                    
                    <!-- 오른쪽 화살표 -->
                    <c:if test="${page.next}">
                        <a style="text-decoration:none;" href="javascript:page(${page.endPage+1});" >&raquo;</a>
                        
                    </c:if>
                    
                </td>
            </tr>
        </tfoot>
				</form>
			</table>
		<%-- 	 <c:forEach items="${write_view}" var="MDto">  --%>
			<a href="write_view?userName=${MDto.userName}" class="btn btn-primary pull-right">글작성</a>
			<%--  </c:forEach>  --%>
			<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
			<script src="js/bootstrap.js"></script>
		</div>
	</div>
</body>
</html>