<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<link href="./cjkit/web/css/memberCSS/popupLoginForm.css" rel="stylesheet">
</head>
<body>
	<div class="div_image">
    <img src="./cjkit/web/images/cook_img/pop.png">
      <div class="div01">
         <div class="div02" style="text-align: center;">
         <label><b>회원로그인</b></label></div>	
			
			<div class="div03">	
				<form action="./MemberLoginAction.me" method="post">
					<label class="lab01">
						<table class="tab01">
							<tr>
								<td><input class="tab01_input01" type="text" name="f_mem_id" placeholder="아이디"></td>			
							</tr>
							<tr>
								<td><input class="tab01_input01" type="password" name="f_mem_pw" placeholder="비밀번호"></td>					
							</tr>
							<tr>
								<td><input class="tab01_input02" type="submit" value="로그인"></td>
							</tr>				
						</table>		
					</label>
				</form>
			</div>
			<!-- <div class="div02"><label>간편로그인</label></div>
			 <div class="div04">
				<table class="tab02">
					<tr>
						<td class="tab02_td01">
							<label>소셜계정으로 간편하게 로그인하세요.<br>더 나은 서비스로 나아갑니다 !!</label>					
						</td>
						<td>
							<input class="tab02_td02" type="button" value="카카오 로그인 >">
						</td>				
					</tr>
				</table>		
			</div> -->
			<div class="div05">
				<table class="tab03">
					<tr>
						<td class="tab03_td01"><a class="tab03_a01" onclick="memberJoin()"><label>회원가입</label></a></td>
						<td class="tab03_td04">|</td>
						<td class="tab03_td02"><a class="tab03_a01" onclick="location.href='./MemberIdSearch.me'"><label>아이디 찾기</label></a></td>
						<td class="tab03_td04">|</td>
						<td class="tab03_td03"><a class="tab03_a01" onclick="location.href='./MemberPwSearch.me'"><label>비밀번호 찾기</label></a></td>				
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">	
		function memberJoin() {
			self.close();
			opener.location.href="./MemberJoin.me";			
		}
		function memberIdSearch() {
			self.close();
			winodw.opne("./MemberIdSearch.me", "memberIdSearch","width=300, height=300, left=900, right=0, top=500 resizable = no, scrollbars = no");	
		}
		function memberPwSearch() {
			self.close();
			winodw.opne("./MemberPwSearch.me", "memberIdSearch","width=300, height=300, left=900, right=0, top=500 resizable = no, scrollbars = no");
		}
	</script>	
</body>
</html>