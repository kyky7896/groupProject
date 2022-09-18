<%@page import="com.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<script type="text/javascript">
function btn_click(str) {
	if(str == "modify") {
		userInfo.action = "./MemberUpdateAction.me"
	} else if(str == "delete") {
		userInfo.action = "./MemberDeleteAction.me"
	}
}
</script>

<link href="./cjkit/web/css/memberCSS/memberUpdateForm.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
<link rel="stylesheet" type="text/css" href="../cjkit/mobile/css/swiper.css">
<script src="./js/memberJS/insertScript.js" ></script>
<script src="./js/memberJS/idCheckScript.js" ></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<%
		String f_mem_id = (String)session.getAttribute("f_mem_id");
		
		if(f_mem_id == null) {
			response.sendRedirect("./Main.me");
		}	
		MemberBean mb = (MemberBean) request.getAttribute("mb");
	%>

	<jsp:include page="../inc/header.jsp"/>
	<div class="div_backImg">
		<!-- <img class="imgBack" src="./cjkit/web/images/cook_img/memberJoin.jpg"> -->
		<div class="insertTitle"><label>회원 정보</label></div>	
		<div class="div01">			
			<form name="userInfo" method="post">
				<table class="tab01">
					<tr>
						<td>아아디</td>
						<td><input type="text" name="f_mem_id" value="<%=mb.getF_mem_id() %>" readonly="readonly"></td>						
						<input type="hidden" name="idDup" value="idUncheck">								
					</tr>
					<tr>
						<td>비밀번호</td>								
						<td><input type="password" name="f_mem_pw" maxlength="10" placeholder="최소 4자 최대 10자 가능."></td>
					</tr>
					<tr>
						<td>비밀번호확인</td>
						<td><input type="password" name="f_mem_pw_chk" maxlength="10" onkeyup="pwCheck()"></td>								
						<td><input class="div01_inp_pwChkRes" type="text" name="f_mem_pw_result" readonly="readonly"></td>
					</tr>
					<tr>
						<td>이름</td>								
						<td><input type="text" name="f_mem_name" value="<%=mb.getF_mem_name() %>"></td>
					</tr>
					<tr>
						<td>휴대폰</td>
						<td><input type="text" name="f_mem_phone" value="<%=mb.getF_mem_phone() %>"></td>				
					</tr>
					<tr>
						<td>우편번호</td>								
						<td><input type="text" name="f_mem_postcode" id="postcode" value="<%=mb.getF_mem_postcode() %>" readonly="readonly"></td>
						<td><input class="div01_inp_postcode" type="button" value="주소검색" onclick="daumPostcode()"></td>
					</tr>
					<tr>
						<td>주소</td>								
						<td><input type="text" name="f_mem_address" id="address" value="<%=mb.getF_mem_address() %>"></td>
					</tr>
					<tr>
						<td>상세주소</td>								
						<td><input type="text" name="f_mem_detailAddress" id="detailAddress" value="<%=mb.getF_mem_detailAddress() %>"></td>
						<td><input type="text" name="f_mem_extraAddress" id="extraAddress" value="<%=mb.getF_mem_extraAddress() %>"></td>
					</tr>
					<tr>
						<td>이메일</td>								
						<td><input type="text" name="f_mem_email" placeholder="이메일"></td>
					</tr>
					<tr>
						<td>닉네임</td>
						<td><input type="text" name="f_mem_nick" placeholder="중복확인을 눌러주세요" readonly="readonly"></td>
						<td><input class="div01_inp_idChk" type="button" value="중복확인" onclick="openNickNameChk()"></td>
						<input type="hidden" name="nickNameDup" value="nickNameUncheck">
					</tr>
				</table>
				<table>
					<tr>						
						<td><input class="inp01" type="submit" value="수정" onclick="btn_click('modify')"></td>
						<td><input class="inp03" type="submit" value="회원탈퇴" onclick="btn_click('delete')"></td>
						<td><input class="inp02" type="button" value="취소" onclick="location.href='./MemberMyPageAction.me'"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../inc/footer.jsp"/>
	
</body>
</html>