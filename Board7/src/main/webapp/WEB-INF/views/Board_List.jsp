<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
 
<style type="text/css">
       #layer1{
             position:absolute;
             padding:5px;
             filter:alpha(opacity=50);
             width:250px; height:auto;
             background-color:white;
             border:2px #000000 dotted;
             visibility:hidden; 
       }
       
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
body {
    font-family: 'Nanum Gothic', sans-serif;
}
 
</style>
<script language="javascript" type="text/javascript" src="js/createXMLHttpRequest.js"></script>
<script type="text/javascript">
console.log("${page.pagePerBlock}" + ":" + "${page.nowBlock}" + ":" + "${page.nowPage}")
    function check() {
        if (document.search.keyWord.value == "") {
            alert("검색어를 입력하세요.");
            document.search.keyWord.focus();
            return;
        }
        document.search.submit();
    }
 
    function list() {
        document.list.action = "boardList.action";
        document.list.submit();
    }
 
    function read(value) {
        document.read.action = "boardView.action";
        document.read.seq.value = value; // 해당 게시글 번호
        document.read.keyField.value = document.search.keyField.value;
        document.read.keyWord.value = document.search.keyWord.value;
        document.read.submit();
    }
 
    function pagemove(i) {
        var nowPage = document.pagemove.nowPage.value;
        document.pagemove.nowPage.value = Number(nowPage) + Number(i);
 
        document.pagemove.submit();
    }
 
    function blockmovef() {
        document.blockmovef.submit();
    }
 
    function blockMoveb() {
        console.log("##" + document.blockmoveb.nowBlock.value);
        document.blockmoveb.submit();
    }
    ///////////////////////////////////////////////////////////////////////////////////    
    var xmlHttp;
 
    var xmlDoc;
 
    var message;
 
    function contentprev(seq) {
 
        var url = "boardAjax.action?seq=" + seq; //미리보기 서블릿 호출
 
        xmlHttp = createXMLHttpRequest();
 
        xmlHttp.onreadystatechange = handleStateChange;
 
        xmlHttp.open("get", url, true);
 
        xmlHttp.send(null);
 
    }
 
    function handleStateChange() {
 
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                xmlDoc = xmlHttp.responseText;
                document.getElementById("layer1").innerHTML = xmlDoc;
            }
 
        }
 
    }
 
    function showlayer(id) {
        if (document.all[id]) {
            document.all[id].style.visibility = "visible";
        } else if (document.layers[id]) {
            document.layers[id].style.visibility = "visible";
        }
 
    }
 
    function hidelayer(id) {
        if (document.all[id])
            document.all[id].style.visibility = "hidden";
 
        else if (document.layers[id])
            document.layers[id].style.visibility = "hidden";
 
    }
 
    function movetip() {
        layer1.style.marginTop = event.y + document.body.scrollTop + 10 +"px";
        
        layer1.style.marginLeft = event.x + document.body.scrollLeft + 10 +"px";
    }
 
    document.onmousemove = movetip;
</script>
</head>
 
<body>
<div id="layer1">게시물 본문 미리 보기</div>
    <marquee behavior="alternate" scrolldelay="100" direction="right">
        지훈이의 게시판입니다.</marquee>
    <form action="boardList.action" name="search" method="post">
        <table border=0 width=800 cellpadding=5 cellspacing=0>
 
        <tr>
            <td align=left> ▶전체게시물수 : ${page.totalRecord }개 ▶현재 페이지 ( <font
                color=red> ${page.nowPage+1 } / ${page.totalPage} 페이지</font>)
            </td>
            <td align=right valign=top>
            <select name="keyField" size="1">
                <option value="name" <c:if test="${'name'==keyField }"> selected</c:if>> 이름 </option>
                <option value="title" <c:if test="${'title'==keyField }"> selected</c:if>> 제목 </option>
                <option value="content" <c:if test="${'content'==keyField }"> selected</c:if>> 내용 </option>
            </select><input type="text" size="16" name="keyWord" value="${keyWord }">
            <input type="button" value="검색" onClick="check()"><input type="hidden" name="page" value="0"></td>
        </tr>                
        </table>
    </form>    
 
    <table class="bbs" width="800" height="200" border="2" bgcolor="D8D8D8">
        <colgroup>
            <col width="50" />
            <col width="450" />
            <col width="100" />
            <col width="100" />
        </colgroup>
        <thead>
            <tr>
                <th>번 호</th>
                <th>제 목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조 회</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:choose>
                    <c:when test="${empty list }">
                    등록된 글이 없습니다.
                    </c:when>
                    <c:otherwise>
                        <c:set var="doneLoop" value="false" />
                        <c:forEach begin="${page.beginPerPage }" end="${page.beginPerPage + page.numPerPage -1}" var="i">
                                <c:if test="${not doneLoop }">
                                    <tr>
                                        <td align=center>${list[i].seq }</td>
                                        <td>
                                            <!-- depth --> <c:if test="${list[i].depth != 0 }">
                                                <c:forEach begin="0" end="${list[i].depth}">
                                                   
                                                </c:forEach>
                                            </c:if><a href="javascript:read(${list[i].seq })" onmouseover="contentprev('${list[i].seq}');showlayer('layer1');" 
                                                            onmouseout="hidelayer('layer1');">${list[i].title }</a>
                                        </td>
                                        <td align="center"><a href="mailto:${list[i].email}">${list[i].name }</a>
                                        <td align=center>${list[i].regdate }</td>
                                        <td align=center>${list[i].count }</td>
                                    </tr>
                                    <c:if test="${i+1 == page.totalRecord} }">
                                        <c:set var="doneLoop" value="true" />
                                    </c:if>
                                </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
        </tbody>
        <tfoot>
 
        </tfoot>
    </table>
        <table border=0 width=800 cellpadding=5 cellspacing=0>
                        <tr>
                <td align="left"><c:if test="${page.totalRecord !=0}">Page→ 
                
                <c:if test="${page.nowBlock >0 }">
                    <a href="javascript:blockMoveb()"><font color="red"> 이전 ${page.pagePerBlock }개</font></a>
                </c:if>
                
                
               <%--  <c:set var="doneLoop2" value="false" />
                <c:forEach begin="0" end="${page.pagePerBlock-1 }" var="i">
                    <c:if test="${not doneLoop2 }">
                    <a href="javascript:pagemove(${i })"> ${(page.nowBlock*page.pagePerBlock)+i+1}</a>
                    <c:if test="${(page.pagePerBlock*page.nowBlock+i+1) == page.totalPage }">
                    <c:set var="doneLoop2" value="true" />  
                    </c:if>
                    </c:if>
                </c:forEach> --%>
                
                
                <c:if test="${page.totalBlock > page.nowBlock+1 }">
                    <a href="javascript:blockmovef()"><font color="red"> 다음 ${page.pagePerBlock }개</font></a>
                </c:if>
                </c:if>
                </td> 
                    <td align=right>    <a href="boardWrite.action">[글쓰기]</a> <a href ="javascript:list()">[목록으로]</a>
                </td>
            </tr>
        </table>
    
    
    <!-- 히든 정의 -->
    <form name="read" method="post">
        <input type="hidden" name="seq"/><input type="hidden" name="keyField"/><input type="hidden" name="keyWord"/>
    </form>
 
        <form name="pagemove" method="POST" action="boardList.action" >
            <input type="hidden" name="nowBlock" value="${page.nowBlock}" /> 
            <input type="hidden" name="nowPage" value="${page.nowBlock*page.pagePerBlock}" /> 
            <input type="hidden"name="keyField" value="${keyField }" /> 
            <input type="hidden"name="keyWord" value="${keyWord }" />
        </form>
    
    <form name="blockmovef" method="get" action="boardList.action">
        <input type="hidden" name="nowBlock" value="${page.nowBlock+1 }" /> 
        <input type="hidden" name="nowPage" value="${(page.nowBlock+1)*page.pagePerBlock}" />
        <input type="hidden" name="keyField" value="${keyField }" />
        <input type="hidden" name="keyWord" value="${keyWord }" />
    </form>
    
    <form name="blockmoveb" method="POST" action="boardList.action">
        <input type="hidden" name="nowBlock" value="${page.nowBlock-1 }" /> 
        <input type="hidden" name="nowPage" value="${(page.nowBlock-1)*page.pagePerBlock}" />
        <input type="hidden" name="keyField" value="${keyField }" />
        <input type="hidden" name="keyWord" value="${keyWord }" />
    </form>
    
    <form name="list" method="GET">
        <input type="hidden" name="reload" value="true">
    </form>
 
</body>
</html>


