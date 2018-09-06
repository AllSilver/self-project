<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
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
		
<title>login page</title>
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
								<a class="navbar-brand" href="main">JSP 게시판 웹 사이트</a>
							</div>
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li><a href="main">메인</a></li>
									<li><a href="#" onclick="listlogin();">게시판</a></li>
									</ul>
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
							<div class="jumbotron" style="padding-top: 20px;" >
										
								<form action="loginOk" method="post" name="userInfo";>
									<h3 style="text-align: center;">로그인 화면</h3>
									<div class="form-group">
										<input type="text" class="form-control" placeholer="아이디" name="userId"  id="userId" maxlength="20" >
									</div>
									<div class="form-group">
										<input type="password" class="form-control" placeholer="비밀번호" name="userPw"  id="userPw"  maxlength="20">
									</div>
									<input type="submit" class="btn btn-primary form-control" value="로그인" >
								</form>
						</div>
					</div>
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="js/bootstrap.js"></script>
        </div>
    </div>
</body>
</html>