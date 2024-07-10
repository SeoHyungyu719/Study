<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#boardDiv{margin-left: 150px; margin-right: 150px; margin-top: 150px; min-height:350px; border: 1px solid black; padding: 30px;}
	#boardDiv>table{width: 100%; text-align: center; height: 100%;}
	th{border-bottom: 1px solid black;}
	#writeLabel{float: right;}
	#writeLabel:hover{font-weight: bold; text-decoration: underline;}
	
	.pagingArea *{margin: 0 10px;}
	.pagingArea a{color: black; text-decoration: none;}
	.pagingArea a:hover{font-weight: bold; text-decoration: underline;}
	.disable{color: lightgray;}
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<jsp:include page="../common/loginbar.jsp"/>
	<div id="boardDiv">
		<label id="writeLabel">[ 글쓰기 ]</label> <!-- 로그인을 해야만 쓸 수 있도록 제한 -->
		<br><br><br>
		<table>
			<tr>
				<th width="5%"></th>
				<th width="10%">글번호</th>
				<th width="40%">글제목</th>
				<th width="18%">작성자</th>
				<th width="17%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
			<c:if test="${ empty list }">
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${ !empty list}">
				<c:forEach items="${ list }" var="b">
					<tr>
						<td>${ b.isNotice == 'N' ? '' : '[공지]' }</td>
						<td>${ b.boardNo }</td>
						<td>${ b.title }</td>
						<td>${ b.writer }</td>
						<td>${ b.updateDate }</td>
						<td>${ b.count }</td>
					</tr>
				</c:forEach>
			</c:if>		
		</table>
		<br><br><br>
		<div class="pagingArea" align="center">
			<!-- 이전 -->
			<c:if test="${ pi.currentPage <= 1}">
				<span class="disable">[이전]</span>
			</c:if>
			<c:if test="${ pi.currentPage > 1 }">
				<c:url value="${ loc }" var="blistBack"><!-- loc : 현재 contextPath/url,,  var="자기가 정한겂" -->
					<c:param name="page" value= "${ pi.currentPage-1 }"/>
					<c:if test="${ condition != null }">
						<c:param name="condition" value="${ condition }"/>
						<c:param name="value" value="${ value }"/>
					</c:if>
				</c:url>
				<a href="${ blistBack }">[이전]</a>
			</c:if>
			<!-- 숫자 -->
			<c:forEach begin="${ pi.startPage }" end="${ pi.endPage }" var="p">
				<c:if test="${ p eq pi.currentPage }">
					<font color="red" size="4"><b>[${ p }]</b></font>
				</c:if>
				<c:if test="${ p ne pi.currentPage }">
					<c:url value="${ loc }" var="blistNum">
						<c:param name="page" value="${ p }"/>
						<c:if test="${ condition != null }">
						<c:param name="condition" value="${ condition }"/>
						<c:param name="value" value="${ value }"/>
					</c:if>
					</c:url>
					<a href="${ bListNum }">${ p }</a>
				</c:if>
			</c:forEach>
			<!-- 다음 -->
			<c:if test="${ pi.currentPage >= pi.maxPage  }">
				<span class="disable">[다음]</span>
			</c:if>
			<c:if test="${ pi.currentPage < pi.maxPage }">
				<c:url value="${ loc }" var="blistNext"> 
					<c:param name="page" value="${ pi.currentPage+1 }"/>
					<c:if test="${ condition != null }">
						<c:param name="condition" value="${ condition }"/>
						<c:param name="value" value="${ value }"/>
					</c:if>
				</c:url>
				<a href="${ blistNext }">[다음]</a>
			</c:if>
		</div>
		
	</div>
	
	
		<div id="searchArea" align="center">
			<label>검색 조건</label>
			<select>
				<option value = "-">------------</option>
				<option value = "writer">작성자</option>
				<option value = "title">제목</option>
				<option value = "content">내용</option>
			</select>
			<input type="search">
			<button>검색하기</button>
		</div>
		<br><br><br>
		
		<script>
			window.onload= () =>{
				document.getElementById('searchArea').querySelector('button').addEventListener('click', function(){
					const value = this.previousElementSibling.value;
					const condition = this.previousElementSibling.previousElementSibling.value;
					
					location.href="${contextPath}/search.bo?value=" + value + '&condition=' + condition;
				});
				
				const tds = document.querySelectorAll('td');
				for(const td of tds){
					const parent = td.parentElement;
					td.addEventListener('mouseenter', () => {
						parent.style.background = "darkgray";
						parent.style.cursor = 'pointer';
					});
					td.addEventListener('mouseout', ()=>{
						parent.style.background = 'white';
					});
					td.addEventListener('click', () => {
						const boardNo = parent.children[1].innerText;
						location.href = '${contextPath}/selectBoard.bo?bId=' + boardNo;
					});
				}
				
				document.getElementById('writeLabel').addEventListener('click',() =>{
					if('${loginUser}' == ''){
						// $ {}(el)을 굳이 '' 감싸는 이유는, 리터럴로 만들려고(만들지 않으면 변수로 인지함)
						// el은 값이 없으면 비어있다고 했기 때문에 로그인이 안된 상태로 $ {loginUser}를 호출하면 비어있을 것임
						// 위에 el표현식을 그대로 사용해서 읽어오면 erro
						// el표현식을 읽어오지 못하도록 일부러 $와 {를 띄워서 썻음
						alert('로그인 후 이용하세요');
					}else{
						location.href='${contextPath}/writeBoardPage.do';
						
					}
				})				
				
				
				
				
				
				
			}
		</script>
</body>
</html>