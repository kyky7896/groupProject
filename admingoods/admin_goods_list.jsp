<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이 </title>
</head>
<body>

	<%
		// request 영역에서 꺼내기 
		List adminGoodsList =(List)request.getAttribute("adminGoodsList");
	%>
	
<!--▶▶메뉴바 형태로 만들었으면 좋겠는데  -->
	<form action="" method="get">
		<select name="item">
			<option value="meal">밀키트</option>
			<option value="veg">식재료</option>
			<option value="tool">주방용품</option>
		</select>
	</form>
	<!-- <div class="tab_wrap">
	<form action="" method="get">
				<ul class="tabs" name="item">
					<li class="tabSelect tabSelectTheme" value="meal">
					<a href="#">
						<span>밀키트</span></a>
					</li>
					<li class="tabSelect tabSelectTheme" value="veg">
					<a href="#">
						<span>식재료</span></a>
					</li>	
					<li class="tabSelect tabSelectTheme" value="veg">
					<a href="#">
						<span>주방용품</span></a>
					</li>			
				</ul>
				</form>
			</div> -->
	
	<table border="1">
	<%
		// 상품 총 개수
			int size = adminGoodsList.size();
		// 상품 열 개수(지정) - 4개
			int col = 4;
		// 상품 행 개수
			int row = (size/col) + (size%col>0? 1:0);
		// 출력 카운트
			int num = 0;
		
		for(int a=0;a<row;a++){
	%>
		<tr>
	<%
		for(int b=0;b<col;b++){
			GoodsBean gb = (GoodsBean)adminGoodsList.get(num);
			System.out.println("@@@@@@@@"+num);
	%>
		<td>
			<img src="./upload/<%=gb.getF_gs_img().split(",")[0] %>" width="300" height="450"><br>
			<a href=""><%=gb.getF_gs_name() %></a><br>
			<%=gb.getF_gs_price() %>원<br>	
			<a href="./AdminGoodsModify.ag?num=<%=gb.getF_gs_num()%>">수정</a>
			
			/
			<a href="./AdminGoodsDelete.ag?num=<%=gb.getF_gs_num()%>">삭제</a>
		</td>

			
	<%
			num++;
			if(size<=num) break;
	
			}
	%>
		</tr>
		
	<%
		}
	%>
	</table>
	
	
	
<!-- 	<form action="" method="get">
		<select name="item">
			<option value="hit">인기상품순</option>
			<option value="hit">신상품순</option>
			<option value="hit">낮은 가격순</option>
			<option value="hit">높은 가격순</option>
		</select>
		<input type="submit" value="검색">
	</form> -->

</body>
</html>