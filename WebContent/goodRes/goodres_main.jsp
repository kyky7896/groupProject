<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.goodres.db.GoodResDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.goodres.db.GoodResBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/goodResCSS/goodress_main.css">
</head>
<body>

	<jsp:include page="../inc/header.jsp"/>
	
	<%
		
		GoodResBoardBean grbb = new GoodResBoardBean();
		
		List<GoodResBoardBean> goodResList = new ArrayList<GoodResBoardBean>();
		List<GoodResBoardBean> goodResTypeList = new ArrayList<GoodResBoardBean>();
		List<GoodResBoardBean> starAvgList = new ArrayList<GoodResBoardBean>();
		List<GoodResBoardBean> foodTypeList = new ArrayList<GoodResBoardBean>();
	
		String[] gu1 = {"강서구","금정구","기장군","남구","동구","동래구",
						"부산진구","북구","사상구","사하구","서구","수영구",
						"연제구","영도구","중구","해운대구"};
		
		HashMap<String, List<GoodResBoardBean>> totalList = new HashMap<>();
		HashMap<String, List<GoodResBoardBean>> totalTypeList = new HashMap<>();
		HashMap<String, List<GoodResBoardBean>> foodTypeMap = new HashMap<>();
		
		totalList = (HashMap) request.getAttribute("totalList");
		totalTypeList = (HashMap) request.getAttribute("totalTypeList");
		foodTypeMap = (HashMap) request.getAttribute("foodTypeMap");
		
		//System.out.println(foodTypeMap);
		
		starAvgList = (List) request.getAttribute("starAvgList");
		
		String[] foodTypeKeys = foodTypeMap.keySet().toArray(new String[foodTypeMap.size()]);
		
		//System.out.println("foodTypeKeys>>>>> " + foodTypeKeys.length);
		
		/* 
        for (String key : foodTypeKeys) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + key);
        }
         */
		int idx = 0;
		String b = "";
	%>
	
 	<div class="div_gr_main">
		<div class="div_search">
			<form action="./GoodResSearchAction.gr" method="post">			
				<table class="div_search_table">
					<tr>
						<td><input class="div_search_input01" type="text" name="gr_input_keyword_search"></td>
						<td><input class="div_search_input02" type="submit" name="gr_btn_search" value="검색"></td>					
					</tr>
				</table>
			</form>
		</div>
		<!-- 지역별======================================================================= -->
		<div class="div_gr_total_list">
			<div class="div_gr_list">
				<div class="div_gr_list_span">부산 지역(구) 맛집 리스트</div>
				<table class="div_gr_list_table">		
					<tr>										
						<%					
							int[] save = new int[3];		
							
							for(int i=0; i<save.length; i++) {						
								save[i] = (int)(Math.random()*gu1.length); // gu1.length == 16
								
								for(int j=0; j<i; j++) {
									if(save[i] == save[j]) {
										i--;
										break;
									}
								}						
							}
							
							for(int i=0;i<3;i++) {
								goodResList = totalList.get(gu1[save[i]]);
								grbb = goodResList.get(0);
						%>
						<td>
						<!-- 이미지 출력 -->
						<a href="./GoodResList.gr?gu=<%=grbb.getF_gr_gu() %>">
						<img src="<%=grbb.getF_gr_img() %>">
						</a>
						</td>				
	 					<%
	 						}
						%>
					</tr>
					<tr>
						<%
							for(int i=0; i<3; i++) {
								goodResList = totalList.get(gu1[save[i]]);
								grbb = goodResList.get(0);
						%>
						<td class="gr_gu_name"><%=grbb.getF_gr_gu() %> 맛집</td>
						<%
							}
						%>
					</tr>
				</table>			
				<hr>
			</div>
			
			<!-- 평점별======================================================================= -->
			<div class="div_gr_list2">
				<span class="div_gr_list_span2">평점이 높은 인기 식당</span>
				<table class="div_gr_list_table2">
					<tr>
					<%
	 					for(int i=0;i<3;i++) {
								grbb = starAvgList.get(i);						
					%>
						<td>
						<!-- 이미지 출력 -->
						<a href="./GoodResContentAction.gr?num=<%=grbb.getF_gr_num() %>">
						<img src="<%=grbb.getF_gr_img() %>">
						</a>
						</td>				
	 				<%
	 					}
					%>
					</tr>
					<tr>
					<%
						for(int i=0; i<3; i++) {
							grbb = starAvgList.get(i);
					%>
						<td class="gr_gu_name"><%=grbb.getF_gr_name() %></td>
					<%
						}
					%>
					</tr>
				</table>
				<hr>
			</div>
			
			<!-- 메뉴별======================================================================= -->
			<div class="div_gr_list3">
				<span class="div_gr_list_span3">메뉴별 인기 맛집</span>
				<table class="div_gr_list_table3">
					<tr>										
						<%					
							int[] save2 = new int[3];		
							
							for(int i=0; i<save2.length; i++) {						
								save2[i] = (int)(Math.random()*foodTypeKeys.length); // foodTypeKeys.length == 17
								
								for(int j=0; j<i; j++) {
									if(save2[i] == save2[j]) {
										i--;
										break;
									}
								}						
							}
							
							for(int i=0;i<3;i++) {
								foodTypeList = foodTypeMap.get(foodTypeKeys[save2[i]]);								
								grbb = foodTypeList.get(0);
						%>
						<td>
						<!-- 이미지 출력 -->
						<a href="./GoodResList.gr?foodtype=<%=grbb.getF_gr_foodtype() %>">
						<img src="<%=grbb.getF_gr_img() %>">
						</a>
						</td>				
	 					<%
	 						}
						%>
					</tr>
					<tr>
						<%
							for(int i=0; i<3; i++) {
								foodTypeList = foodTypeMap.get(foodTypeKeys[save2[i]]);
								grbb = foodTypeList.get(0);
						%>
						<td class="gr_gu_name"><%=grbb.getF_gr_foodtype() %> 맛집</td>
						<%
							}
						%>				
					</tr>		
				</table>
				<hr>
			</div>
		</div>
	</div>

	<jsp:include page="../inc/footer.jsp"/>
</body>
</html>