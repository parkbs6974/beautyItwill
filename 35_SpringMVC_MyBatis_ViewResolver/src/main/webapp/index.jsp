<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시작페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	// Ajax 요청처리에 의해 데이터 가져와서 화면 표시
	function getJsonBoardListData() {
		alert("getJsonBoardListData() 실행~~");
		
		$.ajax("getJsonBoardList.do", {
			type: "get",
			dataType: "json", //응답받을 데이터 타입
			success: function(respData){
				alert("성공~~~");
				console.log(respData);
				
				let dispHtml = "<ul>";
				$.each(respData, function(index, board){
					dispHtml += "<li>";
					dispHtml += this.seq + ", ";
					dispHtml += this.title + ", ";
					dispHtml += this["writer"] + ", ";
					dispHtml += board.content + ", ";
					dispHtml += board.regdate + ", ";
					dispHtml += board["cnt"] + ", ";
					dispHtml += "</li>";
				});
				
				dispHtml += "</ul>";
				$("#dispData").html(dispHtml);
			},
			error: function(){
				alert("실패~~~");
			}
		});
	}
</script>
</head>
<body>

<div id="container">
	<h1>게시판 프로그램</h1>
	<hr>
	<p>
		<a href="user/login.do?id=test&password=test">로그인 페이지로 이동</a>
	</p>
	<p><a href="javascript:getJsonBoardListData()">JSON 데이터 가져오기(BoardList)</a></p>
	<p><a href="javascript:getJsonBoardData()">JSON 데이터 가져오기(Board)</a></p>
	
	<hr>
	<div id="dispData">
		<ul>
			<li>데이터 가져와서 출력하기</li>
		</ul>
	</div>
</div>

<script>
	function getJsonBoardData() {
		alert("getJsonBoardData() 실행~~");
		let vo = { seq: 1};
		console.log(vo);
		console.log(JSON.stringify(vo));
		
		$.ajax("getJsonBoard.do", {
			type: "post",
			data: JSON.stringify(vo), // 서버쪽 전달 데이터(JSON 문자열)
			contentType: "application/json", //서버로 전송하는 컨텐츠 유형(JSON형식)
			dataType: "json", //서버로 부터 응답받을 데이터 형식
			success: function(respData){
				alert("성공~~");
				console.log(respData);
				
				let dispHtml = "<ul>";
				dispHtml += "<li>";
				dispHtml += respData.seq + ", ";
				dispHtml += respData.title + ", ";
				dispHtml += respData["writer"] + ", ";
				dispHtml += respData.content + ", ";
				dispHtml += respData.regdate + ", ";
				dispHtml += respData["cnt"] + ", ";
				dispHtml += "</li>";
				dispHtml += "</ul>";
				
				$("#dispData").html(dispHtml);
			},
			error: function(){
				alert("실패~~");
			}
		});
	}
</script>
</body>
</html>






