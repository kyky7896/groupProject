<%@page import="com.review.db.ReviewBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.goodres.db.GoodResBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/goodResCSS/goodres_content.css">

</head>
<body>
	<%
		GoodResBoardBean grbb = (GoodResBoardBean) request.getAttribute("grbb");
			
		// 세션확인
		HttpSession sessionChk = request.getSession();
		String f_mem_id = (String) sessionChk.getAttribute("f_mem_id");
	%>

	<jsp:include page="../inc/header.jsp"/>
	
	<input type="hidden" id="session_id" value="<%=f_mem_id %>">
	<input type="hidden" id="get_gr_num" value="<%=grbb.getF_gr_num() %>">
	
	<div class="div_content_all">
		<div class="div_title_content">		
			<table class="div_title_table">
				<tr>
					<td style="font-size: 30px;">
						<%=grbb.getF_gr_name() %>
						<label class="lab_staravg" style="font-size: 20px;"><b><%=String.format("%.1f", grbb.getF_gr_staravg()) %></b></label>
					</td>
				</tr>
			</table>			
		</div>
		<br>
		<hr>		
		<section class="sec_content">			
			<div class="div_content">
				<table class="tab_content">
					<tr><td class="tab_content_td01" rowspan="7"><img class="td_img" src="<%= grbb.getF_gr_img() %>"></td></tr>															
					<tr><td>주소</td><td><%=grbb.getF_gr_addr() %></td></tr>										
					<tr><td>전화번호</td><td><%=grbb.getF_gr_tel() %></td></tr>												
					<tr><td>음식종류</td><td><%=grbb.getF_gr_foodtype() %></td></tr>															
					<tr><td>메뉴</td><td><%=grbb.getF_gr_menu() %></td></tr>
					<tr><td>운영시간</td><td><%=grbb.getF_gr_open() %></td></tr>
					<tr><td>홈페이지</td><td><%=grbb.getF_gr_hp() %></td></tr>			
				</table>
				<input type="hidden" id="lat" name="lat" value="<%=grbb.getF_gr_lati() %>">
				<input type="hidden" id="lng" name="lng" value="<%=grbb.getF_gr_long() %>">
				<input type="hidden" id="gr_name" name="gr_name" value="<%=grbb.getF_gr_name() %>">
			</div>
			<div class="map" id="map"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29e9b6b552e8b0d78bef9a137a6809e2"></script>	
			<script type="text/javascript">			
				var mapContainer = document.getElementById('map'), // 이미지 지도를 표시할 div
					mapOption = { 
						center: new kakao.maps.LatLng(document.getElementById('lat').value, document.getElementById('lng').value), // 이미지 지도의 중심좌표 
						level: 6, // 이미지 지도의 확대 레벨									
					};
				
				var map = new kakao.maps.Map(mapContainer, mapOption);
				
				var position = [
					{
						"title": document.getElementById('gr_name').value,		
						"latlng": new kakao.maps.LatLng(document.getElementById('lat').value, document.getElementById('lng').value)
					}
				];	
				
 				var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
								    
			    // 마커 이미지의 이미지 크기 입니다
			    var imageSize = new kakao.maps.Size(24, 35); 
			    
			    // 마커 이미지를 생성합니다    
			    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
			    
			    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: position[0].latlng, // 마커를 표시할 위치
			        title : position[0].title,  // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			        image : markerImage // 마커 이미지 
			    });
					
				function setZoomable(zoomable) {
				    // 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
				    map.setZoomable(zoomable);    
				}
			</script>
			
			<script type="text/javascript">				
				function reviewWrite() {
					var session_id = document.getElementById("session_id").value;
					var get_gr_num = document.getElementById("get_gr_num").value;
					
					if(session_id == "null") {
						alert("로그인해주세요");
						return false;						
					} else {
						location.href="./ReviewWrite.rv?gr_num=" + get_gr_num;						
					} 
				}			
			</script>
		</section>
		<br>
		<hr>		
		<section class="sec_review">
			<button class="btn_review" onclick="return reviewWrite()">리뷰쓰기</button>			
			<%
				int cnt = (Integer) request.getAttribute("cnt");
				int pageSize = (Integer) request.getAttribute("pageSize");
				int currentPage = (Integer) request.getAttribute("currentPage");
				int startRow = (Integer) request.getAttribute("startRow");
				ArrayList reviewList = (ArrayList) request.getAttribute("reviewList");				
				
	 			if (cnt != 0) {
	 				for (int i = 0; i < reviewList.size(); i++) {
	 					ReviewBean rb = (ReviewBean) reviewList.get(i);
	 					
	 		%>
	 		<div class="div_review">	 		
				<table class="tab_review" border="1">
					<tr>
						<th>id</th>
						<td><%=rb.getF_grv_mem_id()%></td>
					</tr>
					<tr>
						<th>star</th>
						<td>
							<div class='star-rating'>						
								<span style="width: calc(<%=rb.getF_grv_star()%>*20%)"></span>
							</div>
						</td>
					</tr>
					<%				
						if(!rb.getF_grv_img().equals("")) {
					%>
					<tr>					
						<th>image</th>
						<td>
						<%
							int size = rb.getF_grv_img().split(",").length;
							int count = 0;
							for (int j = 0; j < size; j++) {
								if (!rb.getF_grv_img().split(",")[j].equalsIgnoreCase("null")) {
									count += 1;
						%>		
									<img id="myImg" src="./upload/<%=rb.getF_grv_img().split(",")[j]%>" width="130" height="150"></div>
																		
									<div id="myModal" class="modal">
										<span class="close">&times;</span>
										<img class="modal-img" id="img01">										
									</div>									
						<%
								}
							}
						%>
						</td>
					</tr>
					<%
						} else {
							
						}
					%>
					
					<tr>
						<th>content</th>
						<%
							String content = rb.getF_grv_content();
									if (content != null) {
										content = content.replace("\r\n", "<br>");
						%>
						<td><%=content%></td>
						<%
							}
						%>
					</tr>
				</table>
        <%
          if(rb.getF_grv_mem_id().equals(f_mem_id)){ 
        %>
          <input type="button" value="삭제하기"
          onclick="location.href='./ReviewDelete.rv?gr_num=<%=rb.getF_grv_gr_num() %>&grv_num=<%=rb.getF_grv_num() %>'">
        <%
        }
        %>
			</div>
			<br>
			<%
					}
				}
			%>
			<br>
			<br>
			
			<%
				if (cnt != 0) {
					int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);
					int pageBlock = 5;
					int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
					int endPage = startPage + pageBlock - 1;
					if (endPage > pageCount) {
						endPage = pageCount;
					}
			%>
			<div class="pagination">
				<div class="pagination_chd">
				<%
					if (startPage > pageBlock) {
				%>
				<a href="./GoodResContentAction.gr?pageNum=<%=startPage - pageBlock%>&num=<%=grbb.getF_gr_num()%>">&laquo;</a>
				<%
					}
						for (int i = startPage; i <= endPage; i++) {
				%>
				<a href="./GoodResContentAction.gr?pageNum=<%=i%>&num=<%=grbb.getF_gr_num()%>"><%=i%></a>
				<%
					}
						if (endPage < pageCount) {
				%>
				<a href="./GoodResContentAction.gr?pageNum=<%=startPage + pageBlock%>&num=<%=grbb.getF_gr_num()%>">&raquo;</a>
				<%
					}
				%>
				</div>
			</div>
			<%
				}
			%>
			
		</section>
	</div>
	
	<jsp:include page="../inc/footer.jsp"/><!-- clear: both; 추가 -->

	<div class="fix_contsbtm">	
		<!-- 스크립트요청사항 : 바닥컨텐츠가 .btm 클래스 추가해주세요 -->
		<div class="fx_button chatbot ui_chatBot show">
<!-- 			<button type="button" class="chatbot"
				onclick="javascript:cj.chatbot.open('http://cookit.oli-va.com/');">
				<span class="hide">챗봇으로 문의하기</span>
			</button> -->
		</div>
		<div class="fx_button top ui_gotoTop show">
			<a href="#wrap" role="button" onclick="window.scrollTo(0,0);"> <span class="hide">페이지 상단으로</span>
			</a>			
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		// modal 스크립트
		var modal = document.getElementById("myModal");
		
		var img = document.getElementById("myImg");
		var modalImg = document.getElementById("img01");
				
		$(document).on("click", "img", function(){
			modal.style.display = "block";
			modalImg.src = this.src;			
		});		
		
		var span = document.getElementsByClassName("close")[0];
		
		span.onclick = function() {
		  modal.style.display = "none";
		}
		// modal 스크립트
	</script>
		
</body>
</html>