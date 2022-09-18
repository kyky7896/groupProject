<%@page import="java.util.List"%>
<%@page import="com.member.db.MemberBean"%>
<%@page import="com.recipe.board.db.RecipeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<!-- <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->
<script src="./js/jquery-3.5.1.min.js"></script>
<link href="./cjkit/web/css/recipeCSS/recipeDetail.css" rel="stylesheet">
<!-- <link href="./cjkit/web/css/recipeCSS/recipeWrite.css" rel="stylesheet"> -->
<link href="./cjkit/web/css/recipeCSS/myRecipeList.css" rel="stylesheet" type="text/css">
<!-- 공통필수 -->
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
<!-- <link rel="stylesheet" type="text/css" href="./cjkit/web/css/main2.css"> -->

<!-- 이미지 -->
<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/pd.css?20200924090025">
<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/op.css?20200924090025">
<script type="text/javascript" src="../../cjkit/web/js/libs/jquery-1.11.2.min.js"></script>


</head>
<body>

	<!-- <h1>WebContent/recipe/MyRecipeList.jsp</h1>
	<h2>마이 레시피 리스트 </h2> -->
	
	<jsp:include page="../inc/header.jsp"/>

	<%
		String f_mem_id = (String)session.getAttribute("f_mem_id");
		
		if(f_mem_id == null) {
			response.sendRedirect("./Main.me");
		}	
		MemberBean mb = (MemberBean) request.getAttribute("mb");
		
		List MyRecipeList = (List)request.getAttribute("MyRecipeList");
		
		//상품의 총 개수
		int size= MyRecipeList.size();
	    // 상품의 열의 개수 8개
	    int col = 3;
	    // 상품의 행의 개수
	    int row = (size / col) + (size % col>0? 1:0);
	    // 출력카운트
	    int num = 0;
		
	%>
	<!-- content -->
	<div id="content" class="pd_wrap">
	
    <!-- <h1 class="h4_tit" align="center"><b> 나의 레시피 모아보기 </b></h1> -->
    <h1 align="center"><b> 나의 레시피 모아보기 </b></h1> <!-- 정중앙에 보이게 하기 위해선 이게 낫지 않을까? -->
    <br><br><br>
    
	<div class="olki_list_wrap">
		<div class="top_sec">
		<!-- 팀프로젝트 코드 들어가는 부분 본문 -->
		<div class="cate_wrap">
			<div class="tab_wrap">
				
		<%
		String item = request.getParameter("item"); 
		if(item == null){
			item = "all";
		}
		%>	
	
			<script type="text/javascript">
				function sel(){
					document.form.submit();
				}
			</script>			
			</div>
		</div>
		</div>
		<div class="olki_list prodListArea">
			<ul style="margin='left'">
					<%
				      for(int a=0;a<row;a++){
				    	  %>
					<!-- 이미지케이스 -->
				    	  <% for(int b=0;b<col;b++){
				    		 RecipeBean rb = (RecipeBean) MyRecipeList.get(num);

				    	  %>
				<li class="item">
					<div class="pro_module">
						<div class="img_wrap">
							<a href="./RecipeDetail.re?f_rcp_num=<%=rb.getF_rcp_num()%>">
								<img src="./recipe_upload/<%=rb.getF_rcp_th_img()%>">						
							</a>
						</div>
						<div class="txt_wrap">
							<a href="" class="conts">
								<p class="etc_info">
                    			<span class="txt">#<%=rb.getF_rcp_hashtag() %></span>
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
				<%} %>
					<!-- //이미지케이스 -->
								
			</ul>
		</div>
		
		
		
		<!-- /////////////////////////////////////////////////////////////////////////////////// -->
		<!-- 팀프로젝트 코드 들어가는 부분 본문  끝-->
		
      
	</div>
</div>
<!-- //content -->
	
	

<jsp:include page="../inc/footer.jsp"/>
</body>
</html>