
<%@page import="com.chart.db.ChartBean"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
</head>
<body>

<%
	// DB연결해서 테이블 가져오기
	// 커넥션 풀 없이 바로 디비연결해서 회원 목록 전부를 가져오기

	String DRIVER = "com.mysql.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/food";
	String DBID = "root";
	String DBPW = "1234";

	// 1. 드라이버 로드
	Class.forName(DRIVER);
	System.out.println("드라이버 로드 성공!");
	// 2. 디비연결
	Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
	System.out.println("디비 연결 성공!");
	// 3. sql 작성 & pstmt 객체 생성
	String sql = "SELECT COUNT(case when f_gs_cate='meal' then 1 END) AS meal, COUNT(case when f_gs_cate='veg' then 1 END) AS veg, COUNT(case when f_gs_cate='tool' then 1 END) AS tool FROM f_goods;";
	PreparedStatement pstmt = con.prepareStatement(sql);
	// 4. sql 실행
	ResultSet rs = pstmt.executeQuery();

	// ▲ 두개의 값만 가져와서 비교

	ChartBean cb = new ChartBean();
	if (rs.next()) {

		cb.setMeal(rs.getInt("meal"));
		cb.setVeg(rs.getInt("veg"));
		cb.setTool(rs.getInt("tool"));
		System.out.println("총 리스트 " + rs);
	}
%>

 <div align="left">
<h1 align="center">쇼핑몰 상품 카테고리별 점유율</h1>
<div id="piechart" style="width: 1200px; height: 800px;"></div>
</div> 

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
    	  ['list', 'Shoppinglist'],
          ['밀키트', <%=cb.getMeal()%>],
          ['식재료', <%=cb.getVeg()%>],
          ['주방용품', <%=cb.getTool()%>]
      ]);

      var options = {
        title: '상품 카테고리',
        pieHole: 0.4,
      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart'));
      chart.draw(data, options);
    }
    </script>
</body>
</html>