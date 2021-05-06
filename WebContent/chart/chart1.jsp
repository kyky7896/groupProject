
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
	String sql = "SELECT COUNT(case when f_rcp_cate='1' then 1 END) AS soup, COUNT(case when f_rcp_cate='2' then 1 END) AS fry, COUNT(case when f_rcp_cate='3' then 1 END) AS school_food, COUNT(case when f_rcp_cate='4' then 1 END) AS dsrt, COUNT(case when f_rcp_cate='5' then 1 END) AS snacks, COUNT(case when f_rcp_cate='6' then 1 END) AS travel, COUNT(case when f_rcp_cate='7' then 1 END) AS meat, COUNT(case when f_rcp_cate='8' then 1 END) AS seafood, COUNT(case when f_rcp_cate='9' then 1 END) AS diet  from f_rcp;";
	PreparedStatement pstmt = con.prepareStatement(sql);
	// 4. sql 실행
	ResultSet rs = pstmt.executeQuery();

	// ▲ 두개의 값만 가져와서 비교
	
	ChartBean cb = new ChartBean();
	if(rs.next()){

		cb.setSoup(rs.getInt("soup"));
		cb.setFry(rs.getInt("fry"));
		cb.setSchool_food(rs.getInt("school_food"));
		cb.setDsrt(rs.getInt("dsrt"));
		cb.setSnacks(rs.getInt("snacks"));
		cb.setTravel(rs.getInt("travel"));
		cb.setMeat(rs.getInt("meat"));
		cb.setSeafood(rs.getInt("seafood"));
		cb.setDiet(rs.getInt("diet"));
		System.out.println("총 리스트 " + rs);
	}
%>
  
<div align="left">
	<h1 align="center">레시피 등록 현황</h1>
   <div id="columnchart_values" style="width: 1200px; height: 800px;"></div>
</div>
 



<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["list", "count", { role: "style" } ],
        ['국/탕',     <%=cb.getSoup()%> , "color: #ff8080"],
        ['볶음/반찬',     <%=cb.getFry()%>, "color: #ccebff"],
        ['분식',  <%=cb.getSchool_food()%>, "color: #b3ffe0"],
        ['간식/디저트', <%=cb.getDsrt()%>, "color: #59b300"],
        ['술이한잔',    <%=cb.getSnacks()%>, "color: #ffb3ff"],
        ['캠핑/여행',    <%=cb.getTravel()%>, "color: #c2c2d6"],
        ['고기류',    <%=cb.getMeat()%>, "color: #006600"],
        ['해물',    <%=cb.getSeafood()%>, "color: #660066"],
        ['다이어트',    <%=cb.getDiet()%>, "color: #66ffff"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "레시피 등록 현황",
        width: 1200,
        height: 800,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
  }
  </script>
  
  
  
    
   
</body>
</html>