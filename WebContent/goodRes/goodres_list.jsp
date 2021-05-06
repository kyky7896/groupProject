<%@page import="java.util.List"%>
<%@page import="com.goodres.db.GoodResBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/goodResCSS/goodress_main.css">

<style>
body, html {
}

h1 {
	margin-top: 20px;
	margin-bottom: 3px;
	height: 50px;
}

table{
	vertical-align: middle;
	margin: auto;
	width: 1000px;
 	border-collapse: separate;
 	border-spacing: 0 20px;  
 	
}

table tr{
	margin-bottom: 200px;
}

table img{

}

#test{
	text-align: center;
}

#list_img{
}

#list{
	clear: both;
	font-size: 1.5em;
	line-height: 1.5em;

}

#resList{
	border-bottom: 1px solid black;
}

</style>

</head>


<body>
	<jsp:include page="../inc/header.jsp"/>
	
	<%
	//	List<GoodResBoardBean> GoodResList = grdao.getGoodResList();
		
	//	request.setAttribute("GoodResList", GoodResList);
	
		List<GoodResBoardBean> guList = (List<GoodResBoardBean>)request.getAttribute("guList");
		List<GoodResBoardBean> typeList = (List<GoodResBoardBean>)request.getAttribute("typeList");
		
		String gu = (String) request.getParameter("gu");
		String foodType = (String) request.getParameter("foodtype");
		
	%>
	<div id="test">
		<%
			if (gu != null) {
		%>
		<h1>
			<b><%=gu%> 맛집 리스트 </b>
		</h1>
		<%
			}
		%>
		<table>
			<tr>
				<div>
					<%
						for (int i = 0; i < guList.size(); i++) {
							// 배열 -> MemberBean -> 출력
							GoodResBoardBean grb = (GoodResBoardBean) guList.get(i);
					%>
					<td><a
						href="./GoodResContentAction.gr?num=<%=grb.getF_gr_num()%>">
							<img src="<%=grb.getF_gr_thumb()%>" id="list_img">
					</a><br></td>
					<td id="resList">
						<div id="list">
							<%=i + 1%>.
							<%=grb.getF_gr_name()%>
							<br> 주소 :
							<%=grb.getF_gr_addr()%><br> 별점 평균 :
							<%=String.format("%.1f", grb.getF_gr_staravg())%><br>
							리뷰수: <%=grb.getF_gr_rv_cnt() %><br>
							조회수: <%=grb.getF_gr_cnt() %><br>
						</div>
				</div>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<div id="test">
		<%
			if (foodType != null) {
		%>
		<h1>
			<b><%=foodType%> 맛집 리스트 </b>
		</h1>
		<%
			}
		%>
		<table>
			<tr>
				<div>
					<%
						for (int i = 0; i < typeList.size(); i++) {
							// 배열 -> MemberBean -> 출력
							GoodResBoardBean grb = (GoodResBoardBean) typeList.get(i);
					%>
					<td><a
						href="./GoodResContentAction.gr?num=<%=grb.getF_gr_num()%>">
							<img src="<%=grb.getF_gr_thumb()%>" id="list_img">
					</a><br></td>
					<td id="resList">
						<div id="list">
							<%=i + 1%>.
							<%=grb.getF_gr_name()%>
							<br> 주소 :
							<%=grb.getF_gr_addr()%><br> 별점 평균 :
							<%=String.format("%.1f", grb.getF_gr_staravg())%><br>
							리뷰수: <%=grb.getF_gr_rv_cnt() %><br>
							조회수: <%=grb.getF_gr_cnt() %><br>
						</div>
				</div>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

	<jsp:include page="../inc/footer.jsp"/>

</body>
</html>