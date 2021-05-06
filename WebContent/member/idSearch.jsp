<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<style type="text/css">
h1 {
	margin-left: 13%;
}

.div01 {
	margin-left: 25%;
	margin-top: 20%
}

.tab01 td{	
	height: 30px;
}

.inp01 {
	width: 232px;
}
</style>
<script type="text/javascript">
	function valueCheck() {
		if(!document.form1.username.value) {
			alert("이름을 입력하세요");
			return false;
		}
		
		if(!document.form1.email.value) {
			alert("이메일을 입력하세요");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="div01">
		<form action="./MemberLoginInfoSearch.me" name="form1" method="post" onsubmit="return valueCheck()">
			<h1>아이디찾기</h1>
			<table class="tab01">
				<tr>
					<td>이름</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email"></td>
				</tr>				
			</table>
			<input class="inp01" type="submit" value="인증코드" >
		</form>
	</div>	
</body>
</html>