<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<script language="javascript">
	function loginPopup() {
		window.open("./MemberLogin.me", "a","width=483, height=422, left=900, right=0, top=500 resizable = no, scrollbars = no");
	}
	function SendMailPopup(){
		window.open("./SendMail.me","b","width=450, height=500, left=900, right=0, top=500 resizable = no, scrollbars = no");
	}
	function BasketListPopup(){
		window.open("./BasketList.ba","c","width=700, height=450, left=900, right=0, top=500 resizable = no, scrollbars = no");
	}
	function MapPopup(){
		window.open("./map.jsp","d","width=500, height=400, left=900, right=0, top=300 resizable = no, scrollbars = no");
	}
</script>

<%	
	String id = (String) session.getAttribute("f_mem_id");
%>

<header id="head">
	<div class="gnb_wrap">
		<div class="gnb_util">
			<div class="util_wrap">
				<ul>
				<%
					if(id == null) {
				%>
					<li onclick="location.href='./MemberJoin.me'"><a
						href="#">회원가입</a></li>
					<li onclick="loginPopup();"><a
						href="#">로그인</a></li>
				<%
					} else {
				%>
					<li><label><b style="color:#ccff66;"><%=id %>님</b></label></li>
					<li onclick="location.href='./MemberLogout.me'"><a
						href="#">로그아웃</a></li>
<!-- 					<li onclick="location.href='./MemberMyPageAction.me'"><a
						href="#">마이페이지</a></li> -->			
				<%
					}
				%>				
					<li onclick="location.href='./QNABoard.me'"><a>고객행복센터</a></li>
					 <!-- <li onclick="MapPopup();"><a>실시간 교통정보</a></li> -->
				<%
					if(id == null) {
				%>
					<!-- <li><a
						href="#"
						target="_blank">카카오톡가입</a></li> -->
				<%
					}
				%>
				</ul>
			</div>
		</div>
		<div class="gnb_menu">
			<!-- <h1 class="logo"> </h1> --><a href="./Main.me">
			<img src="./cjkit/web/images/cook_img/logoC.png" class="logo">
				<span class="hide">라따뚜이</span></a>

			<ul class="list_gmenu">


				<li><a href="./MemberIntroduce.me" target="_self">소개</a></li>


				<li><a href="./RecipeList.re" target="_self">레시피</a></li>
				
				<li><a href="./GoodResMainAction.gr" target="_self">맛집</a></li>


				<li><a href="./GoodsList.go">쇼핑</a></li>

				<%
					if(id != null) {
				%>
				<li><a href="./MemberMyPageAction.me">MY PAGE</a></li>
				<%
					}
				%>

			</ul>

			<div class="my_gmenu">
				<ul>
					<!--                        <li class="g_my"><a href="/pc/my/myMain"><span class="hide">마이 쿡킷 페이지로 이동</span></a></li> 0701 숨김처리 -->
					<li class="g_od"><a
						onclick="BasketListPopup();">
							<span class="txt">장바구니</span> <span class="hide">장바구니 페이지로
								이동</span> <!--   <span class="num" id="headerCartCount">
                                   <span class="hide">장바구니에 담기 상품 갯수</span>
                                   0
                               </span> --> <!-- 장바구니 담는 갯수는 구현이 안되어서 주석처리함. -->
					</a></li>
					<li class="g_mu"><a
						onclick="SendMailPopup();"><span
							class="txt">관리자에게..</span><span class="hide">관리자에게 메일보내기</span></a></li>
				</ul>
			</div>
		</div>
	</div>
</header>