<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#loginbar{text-align: right; margin: 50px;}
	#loginbar{color: black; text-decoration: none;}
	#loginbar>a:hover{font-weight:bold; text-decoration: underline;}
	
</style>
</head>
<body>
	<c:if test="${ empty loginUser }"> //로그인 유저가 비어있을 때 ()
		<div id="loginbar">
			<a href="${ contextPath }/loginView.me">로그인</a>
		</div>
	</c:if>
	<c:if test="${ !empty loginUser }">
		<div id="loginbar">
			<b>${ loginUser.empName }님, 반갑습니다.</b><br><br>
			<a href="${ contextPath }/editPage.me">내 정보 수정</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${ contextPath }/logout.me">로그아웃</a>
			<br><br>
			<a href="${ contextPath }/list.bo">▶▷▶▷▶게시판</a>			
		
		</div>
	</c:if>
</body>
</html>