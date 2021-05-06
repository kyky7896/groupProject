<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>

<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
<link rel="stylesheet" type="text/css" href="../cjkit/mobile/css/swiper.css">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/memberCSS/find.css">
<style type="text/css">
	body, html {
		width: 100%;
		height: 100%;
	}
		
	td {		
		font-size: 30px;		
		height: 50px;
	}
	
	label {
		width: 200px;
		height: 50px;
	}
	
	.div01 {
		height: 100%;
		margin-top: 20%;
		margin-left: 20%;
		margin-right: 20%;	
		text-align: center;		
	}
	
	.lab01 {
		margin-left: 36px;
	}
	
	.inp01 {
		width: 300px;
		height: 50px;
	}

</style>

</head>
<body>
	<%
		String findId =  (String) request.getAttribute("findId");
	%>
	
	<jsp:include page="../inc/header.jsp"/>
	
	<div class="div01">
		<table class="tab01">
			<tr>
				<td><label class="lab01">ID : </label></td>
				<td><label class="lab02"><%=findId %></label></td>
			</tr>
			<tr>
				<td colspan="2"><input class="inp01" type="button" value="확인" onclick="location.href='./Main.me'"/></td>
			</tr>
		</table>		
	</div>
	
	<jsp:include page="../inc/footer.jsp"/>
</body>
</html>