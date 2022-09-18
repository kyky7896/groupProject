<%@page import="java.util.ArrayList"%>
<%@page import="com.goodres.db.GoodResBoardBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/goodResCSS/goodres_search.css">
</head>
<body>
	<jsp:include page="../inc/header.jsp"/>
	
	<%		
		List keywordList = (List) request.getAttribute("keywordList");
		System.out.println(keywordList);
		
		String keyword = (String) request.getAttribute("keyword");
		System.out.println(keyword);
		
		String[] latlng = {};
		
	%>
	<div class="div_search_all">
		<div class="div_search">
			<form action="./GoodResSearchAction.gr" method="post">
				<table class="div_search_table">
					<tr>
						<td><input class="div_search_input01" type="text" name="gr_input_keyword_search"></td>
						<td><input class="div_search_input02" type="submit" name="gr_btn_search" value="찾기"></td>			
					</tr>
				</table>
			</form>
		</div>
		<h1 class="h1_search_title"><%=keyword %> 맛집 인기 검색순위</h1>
		<section class="sec_result">			
			<div class="div_result">						
				<table class="tab_search" id="tab_search">
				<%
					// 상품의 총 개수
					int size = keywordList.size();
					// 상품의 열의 개수 8개
					int col = 2;
					// 상품의 행의 개수
					int row = (size / col) + (size % col>0? 1:0);
					// 출력카운트
					int num = 0;
					
					for(int a=0; a<row; a++) {
				%>		
					<tr class="tr_search" id="tr_search">
				<% 
						for(int b=0; b<col; b++) {
							GoodResBoardBean grbb = (GoodResBoardBean) keywordList.get(num);
				%>
						
							<td class="td_search" id="td_search">
								<table>
									<tr><td><a href="./GoodResContentAction.gr?num=<%=grbb.getF_gr_num() %>"><img src="<%= grbb.getF_gr_thumb() %>"></a></td></tr>
									<tr><td><a href="./GoodResContentAction.gr?num='<%=grbb.getF_gr_num() %>'"><%= grbb.getF_gr_name() %></a></td></tr>
									<tr><td><%= grbb.getF_gr_gu() %> - <%= grbb.getF_gr_menu() %></td></tr>
								</table>
								<input type="hidden" id="lat" name="lat" value="<%=grbb.getF_gr_lati() %>">
								<input type="hidden" id="lng" name="lng" value="<%=grbb.getF_gr_long() %>">
								<input type="hidden" id="gr_name" name="gr_name" value="<%=grbb.getF_gr_name() %>">							
							</td>						
				<%										
							num++;
							if(size <= num) break;
						}
				%>		
					</tr>
				<%	
					}
				%>
				</table>
			</div>
		</section>	
		
		<section class="sec_rigth">
			<div id="map"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29e9b6b552e8b0d78bef9a137a6809e2"></script>	
			<script type="text/javascript">			
				var mapContainer = document.getElementById('map'), // 이미지 지도를 표시할 div
					mapOption = { 
						center: new kakao.maps.LatLng(35.179744238649576, 129.0750680030133), // 이미지 지도의 중심좌표 
						level: 9, // 이미지 지도의 확대 레벨									
					};
				
				var map = new kakao.maps.Map(mapContainer, mapOption);
				
				var positions = [];
				
  				for(var i=0; i<td_search.length; i++) {				 
					positions.push({
						"title": document.getElementsByName('gr_name')[i].value,		
						"latlng": new kakao.maps.LatLng(document.getElementsByName('lat')[i].value, document.getElementsByName('lng')[i].value)
					});						  
				}
  				
 				var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
			    
				for (var i = 0; i < positions.length; i ++) {				    
				    // 마커 이미지의 이미지 크기 입니다
				    var imageSize = new kakao.maps.Size(24, 35); 
				    
				    // 마커 이미지를 생성합니다    
				    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
				    
				    // 마커를 생성합니다
				    var marker = new kakao.maps.Marker({
				        map: map, // 마커를 표시할 지도
				        position: positions[i].latlng, // 마커를 표시할 위치
				        title : positions[i].title,  // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				        image : markerImage // 마커 이미지 
				    });
				}	
					
				function setZoomable(zoomable) {
				    // 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
				    map.setZoomable(zoomable);    
				}
			</script>
		</section>
	</div>
	
	<jsp:include page="../inc/footer.jsp"/><!-- clear: both; 추가 -->

	<div class="fix_contsbtm">	
		<!-- 스크립트요청사항 : 바닥컨텐츠가 .btm 클래스 추가해주세요 -->
		<div class="fx_button chatbot ui_chatBot show">
			<button type="button" class="chatbot"
				onclick="javascript:cj.chatbot.open('http://cookit.oli-va.com/');">
				<span class="hide">챗봇으로 문의하기</span>
			</button>
		</div>
		<div class="fx_button top ui_gotoTop show">
			<a href="#wrap" role="button" onclick="window.scrollTo(0,0);"> <span class="hide">페이지 상단으로</span>
			</a>			
		</div>
	</div>
	
</body>
</html>