<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
<!-- Bootstrap -->
<link href='<c:url value="/css/bootstrap.min.css" />' rel="stylesheet">
<link href='<c:url value="/css/kfonts2.css" />' rel="stylesheet">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src='<c:url value="/jquery/jquery-1.11.3.min.js" />'></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src='<c:url value="/js/bootstrap.min.js"  />'></script>
 

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
 <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
 <link href="<c:url value="/css/bootstrap-li.css" />" rel="stylesheet">
 <link href="<c:url value="/css/bootstrap-theme.css" />" rel="stylesheet">
 <link href="<c:url value="/css/bootstrap-theme.min.css" />" rel="stylesheet">
 <link href="<c:url value="/js/bootstrap.js" />" rel="stylesheet">
 <link href="<c:url value="/js/bootstrap.min.js" />" rel="stylesheet">
 <link href="<c:url value="/js/npm.js" />" rel="stylesheet">
 
<script type="text/javascript">
function listlogin(){
    if(confirm("로그인 후 이용해주세요.")){
        location.href = "loginPage";
        return true;
    } else {
        return false;
    }
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
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							</button>
								<a class="navbar-brand" href=""> JSP 게시판 웹 사이트</a>
							</div>
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li class="active"><a href="main">메인</a></li>
									<li><a href="list">게시판</a></li>
									</ul>
										<%-- <%
													if(userID == null) {
											%> --%>
											<ul class="nav navbar-nav   navbar-right">						
									<li class="dropdown">
											<a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-haspopup="true"
												aria-expanded="false">접속하기<span class="caret"></span></a>
											<ul class="dropdown-menu">										
													<li class="active"><a href="loginPage">- 로그인</a></li>
													<li class="active"><a href="joining">- 회원가입</a></li>					
											</ul>
										</li>
								</ul>
								
							</div>
						</nav>
						<div class="container">
							<div class="jumbotron">
								<dive class="container">
									<h1>웹 사이트 소개</h1>
										<p>이곳은 spring 기반의 게시판용 사이트 입니다.</p>
								<!-- 	<p><a class="btn btn-primary btn-pull" href="#" role="button">자세히 알아보기</a></p> -->
							</div>
						</div>
					
					<div class="container">
			  <h4>메일 보내기</h4>
			  <form action="${pageContext.request.contextPath}/mail/mailSending" method="post">
			    <div align="center"><!-- 받는 사람 이메일 -->
			    <input type="text" name="tomail" size="120" style="width:100%" placeholder="받는 사람 @ 이메일" class="form-control" >
			    </div>     
			    <div align="center"><!-- 제목 -->
			      <input type="text" name="title" size="120" style="width:100%" placeholder="제목을 입력해주세요" class="form-control" >
			    </div>
			    <p>
			    <div align="center"><!-- 내용 --> 
			      <textarea name="content" cols="120" rows="12" style="width:100%; resize:none" placeholder="내용#" class="form-control"></textarea>
			    </div>
			    <p>
			    <div align="center">
			      <input type="submit" value="메일 보내기" class="btn btn-warning">
			    </div>
			  </form>
			</div>
					
					
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="js/bootstrap.js"></script>
        </div>
    </div>
</body>
</html>