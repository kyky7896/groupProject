<%@page import="com.member.db.MemberBean"%>
<%@page import="com.recipe.board.db.RecipeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
<!DOCTYPE html>
<html lang="ko">

<!-- Mirrored from www.cjcookit.com/pc/menu/menuList by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 23 Mar 2021 09:53:58 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=UTF-8" /><!-- /Added by HTTrack -->
	<head>
		<meta charset="utf-8">
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>COOKIT 메뉴</title>
		<link rel="shortcut icon" href="https://www.cjcookit.com/cjkit/web/images/favicon.ico">
		<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/common.css?20200924090025">
	    <link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/chatbot.css?20200924090025">
		<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/pd.css?20200924090025">
		<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/op.css?20200924090025">
		<link rel="stylesheet" type="text/css" href="./cjkit/web/css/recipeCSS/menuList.css">
	</head>
	<body>
				<div id="skipnavi">
					<ul>
						<li><a href="#container_title">본문바로가기</a></li>
					</ul>
				</div>
		
			<div id="wrap">
				<div class="preloader" style="display:none;">
					<div class="loader">
						<span class="loading"></span>
					</div>
				</div>
			
				
		<!-- header -->
		 <jsp:include page="./inc/header.jsp"/>
		<!-- //header -->
		
			<%
			//request영역에서 꺼내기
			List recipeList = (List)request.getAttribute("recipeList");
			
			String f_mem_id = (String)session.getAttribute("f_mem_id");
			String f_mem_nick = (String)session.getAttribute("f_mem_nick");
			
			%>
			
				
				<section id="container" >
					<div id="container_title" class="hide" tabindex="-1">본문 시작</div>
		
		
		<!-- content -->
		<div id="content" class="pd_wrap">
			<div class="olki_list_wrap">
				<div class="top_sec">
				<h2> COOKIT 레시피</h2>
				<!-- 팀프로젝트 코드 들어가는 부분 본문 -->
				<div class="cate_wrap">
					<div class="tab_wrap">
						<ul class="tabs">
							<li class="tabSelect tabSelectDate on">
							<a href="./RecipeList.re?">
								<span>전체보기</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=1">
								<span>국·탕</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=2">
								<span>볶음·반찬</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=3">
								<span>분식</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=4">
								<span>간식·디저트</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=5">
								<span>술이한잔</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=6">
								<span>캠핑·여행</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=7">
								<span>고기류</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=8">
								<span>해물류</span></a>
							</li>
							<li class="tabSelect tabSelectTheme">
							<a href="./RecipeList.re?item=9">
								<span>헬씨·다이어트</span></a>
							</li>
						</ul>
					</div>
				<!-------------- 글쓰기 --------------->
				<% if(f_mem_id != null){%> 
						<div class="writebutton">
							<button onclick="location.href='./RecipeWrite.re'">
							<img title="글쓰기" src="./cjkit/web/images/recipeIcon/writeicon.png" width="50px;" height="50px;"></button>
						</div>
				<%} %>
				<!-------------- 글쓰기 ------------>
				<%
				String item = request.getParameter("item"); 
				if("null".equals(item)){
					item = "all";
				}
				%>	
						<!--- 상품 정렬 최신순/조회순 --->
						
				<div>
					<form action="./RecipeList.re?item=<%=item %>" name="form" method="get" style="float:right;">
					<input type="hidden" name="item" value="<%=item %>">
						<select name="type" class="sele" onchange="sel(); ">	
							<option value="newest" 
							<% 
							String type = request.getParameter("type");
								if(type != null){
									if(type.equals("newest")){
							%>
							selected
							<%} 
								}%>
							>최신순</option>
							
							<option value="hit"
							<%
								if(type != null){
									if(type.equals("hit")){
							%>
							selected
							<%
							}  }
							%>
							>인기순</option>
						</select>
					</form>
				</div>
				<script type="text/javascript">
						function sel(){
							document.form.submit();
						}
				</script>
			    </div>
				</div>
			<%
						//상품의 총 개수
						int size = 0;
						try{
							size = recipeList.size();
						}catch(Exception e){
							
						}
						/* int size = recipeList.size(); */
					    // 상품의 열의 개수 8개
					    int col = 4;
					    // 상품의 행의 개수
					    int row = (size / col) + (size % col>0? 1:0);
					    // 출력카운트
					    int num = 0;
						
						%>

				<div class="olki_list prodListArea">
					<ul>
							<%
						      for(int a=0;a<row;a++){
						    	  %>
							<!-- 이미지케이스 -->
						    	  <% for(int b=0;b<col;b++){
						    		  RecipeBean rb = (RecipeBean) recipeList.get(num);
						    	  %>
						<li class="item" style="height: 541px;">
							<div class="pro_module">
								<div class="img_wrap">
									<a href="./RecipeDetail.re?f_rcp_num=<%=rb.getF_rcp_num()%>">
										<img src="./recipe_upload/<%=rb.getF_rcp_th_img()%>">
								
									</a>
								</div>
							<div class="txt_wrap">
								<a href="" class="conts">
								<p class="etc_info">
		                    	<span class="txt">
		                    		<%-- <a href="./RecipeList.re?q=<%=rb.getF_rcp_hashtag() %>"> --%>#<%=rb.getF_rcp_hashtag() %><!-- </a> -->
		                    	</span>
		            			</p>
							<div class="tit_info">
								<span class="tit mt_elps"><%=rb.getF_rcp_title() %></span>
							</div>
							<div>
								<span class="nick_info"><%=rb.getF_rcp_nick() %></span>
							</div>
							<div>
								<span class="view_count">조회수 <%=rb.getF_rcp_count() %></span>
							</div>
								</a>
							</div>
							</div>
							<%
				    		num++;
				    		if(size<=num) break;
				    	  	}
				    	 	%>
						</li>
						<%
						}
						%>
							<!-- //이미지케이스 -->
										
					</ul>
				</div>
				
				
				
				<!-- /////////////////////////////////////////////////////////////////////////////////// -->
				<!-- 팀프로젝트 코드 들어가는 부분 본문  끝-->
				
		      
			</div>
		</div>
				</section>
			
			    
		
		
		
		
		
		
		<!-- 챗봇 레이어 팝업 -->
		<article class="lay_chatbot_wrap_ok">
		    <div class="lay_chatbot_frame">
		        <section class="chatbot_conts">
		          <iframe src="about:blank" id="iframeChatbot" allow="geolocation"></iframe>
		        </section>
		        <div class="close">
		            <button type="button" class="btn_chatbot_close"><span class="hide">닫기</span></button>
		        </div>
		    </div>
		    <!-- 레이어 팝업 -->
		    <article class="lay_wrap_chatbot modal_chatbot" style="display:none;">
		        <h1 class="hide"><span class="hide">앱업데이트안내(필수)</span></h1>
		        <section class="lay_conts">
		            <!-- 190419_구조 수정 -->
		            <div class="laypop_cont">
		                <p>챗봇 창을 닫으시면, <br>기존 채팅 내용이 초기화됩니다.<br>챗봇 창을 닫으시겠습니까?</p>
		            </div>
		            <!-- //190419_구조 수정 -->
		            <div class="btn_wrap laypop">
		                <button type="button" class="btn_lay_chatbot ui_closeCancel">취소</button>
		                <button type="button" class="btn_lay_chatbot green ui_closeConfirm">확인</button>
		            </div>
		        </section>
		    </article>
		    <div class="dimmed" style="display:none;"></div>
		</article>
		
		
		
		
		
		
		<!-- Footer -->
		<jsp:include page="./inc/footer.jsp"/>
		<!-- //Footer -->
		
		
			
		
		
		
		
		</body>
		
		<!-- Mirrored from www.cjcookit.com/pc/menu/menuList by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 23 Mar 2021 09:54:00 GMT -->
		</html>