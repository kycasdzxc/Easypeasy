<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
        #main{position: absolute; top: 120px;}

        .outer1{
            padding:50px;
            width:1000px;
            margin:auto;
            float:left;
            box-sizing: border-box;
        }
      tbody>tr:hover{background:rgb(233, 233, 233); cursor:pointer;}
        	
	</style>
</head>
<body>
    <jsp:include page="../common/header.jsp"/>

	<div id="main">

		<jsp:include page="appMenubar.jsp" />
		<div class="outer1">
			<p style="font-size:25px;"><b>참조대기문서</b></p>
			<br><br>
			<div class="list-1">
				<select name="period" id="">
					<option value="">전체기간</option>
					<option value="">1개월</option>
					<option value="">6개월</option>
					<option value="">1년</option>
				</select>
				<select name="option" id="">
					<option value="">기안자</option>
					<option value="">기안부서</option>
					<option value="">결재양식</option>
				</select>
				<input type="search"> <button>검색</button>

			</div>

			<br><br>

			<div class="list-2">
				<table style="text-align:center" class="table" id="result-tb">
					<thead>
						<tr>
							<th>
								<input type="checkbox" name="" id="chk-total">
							</th>
							<th>기안일</th>
							<th>결재양식</th>
							<th>제목</th>
							<th>첨부파일</th>
							<th>기안자</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty list}">
								<tr>
									<td colspan='6'>
										참조대기중인 문서가 없습니다.
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="a" items="${list}" >
									<tr>
										<td>
											<input type="checkbox" name="chk" id="">
											<input type="hidden" id="num" value="${a.appNo}">
										</td>
										<td>${a.enrollDate}</td>
										<td>${a.formName}</td>
										<c:choose>
											<c:when test="${a.formCode == 3 || a.formCode == 4 }">
												<td>${a.formName}</td>
											</c:when>
											<c:otherwise>
												<td>${a.title}</td>
											</c:otherwise>
										</c:choose>
										<td>
											<c:if test="${a.attachCount > 0}">
												<span><img src="resources/common_images/attachment.png" width="10px;"></span>
											</c:if>
										</td>
										<td>${a.empName}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>                    

					</tbody>
				</table>
				<br><br>
				<div align="center">
					<ul id="paging">
						<c:choose>
							<c:when test="${pi.currentPage eq 1 }">
								<li class="page-item disabled"><a class="page-link" href="#">&lt;</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a class="page-link" href="refWlist.ap?cpage=${pi.currentPage -1 }">&lt;</a>  </li> 
							</c:otherwise>
						</c:choose>
						
						<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
							<li class=""><a class="page-link" href="refWlist.ap?cpage=${p }">${p }</a></li>
						</c:forEach>
						
						<c:choose>
							<c:when test="${pi.currentPage eq pi.maxPage }">
								<li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="refWlist.ap?cpage=${pi.currentPage + 1 }">&gt;</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<script>
				$(function(){
					
					// 체크박스 전체선택 or 해제하는 function
					$("#chk-total").on("click", function(){
						if($("#chk-total").is(":checked")){
							$("#result-tb tbody input[name='chk']").prop("checked", true);
						}else{
							$("#result-tb tbody input[name='chk']").prop("checked", false);
						}
					});
					
					$("#result-tb tbody input[name='chk']").on("click", function(){
						var total = $("#result-tb tbody input[name='chk']").length;
						var checked = $("#result-tb tbody input[name='chk:checked']").length;
						
						if(total != checked){
							$("#result-tb thead input[name='chk']").prop("checked", false);
						}else{
							$("#result-tb thead input[name='chk']").prop("checked", true);
						}
					});
					
					// 상세페이지로 이동하는 function
					if(${not empty list}){
						$("table tbody").on("click", "tr", function(){
							location.href = 'detailRec.ap?no=' + $(this).children().eq(0).find("#num").val()+"&form="+ $(this).children().eq(2).text()+"&st=참조대기"; 
						}); 
					}
				});
				</script>           
				<br clear="both"><br>



			</div>
		</div>
    </div>
</body>
</html>