<%@page import="com.recipe.board.db.RecipeBean"%>
<%@page import="com.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
	

	
	<title>레시피 등록 - 집밥을 특별하게, 쿡킷</title>
	<link rel="shortcut icon" href="https://www.cjcookit.com/cjkit/web/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
	<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/recipeCSS/recipeWrite.css">


<script type="text/javascript" src="./js/jquery-3.5.1.min.js" charset="utf-8"></script>
<script type="text/javascript">
	
	/* 유효성 체크 */
	
	function checkForm() {
		
		if(document.RecipeForm.f_rcp_title.value==""){
			alert(" 제목을 입력하세요.");
			document.RecipeForm.f_rcp_title.focus();
			return false;
		}
		
	}
	
	/* 유효성 체크 */

		         
</script>
<!-- header -->
 <jsp:include page="../inc/header.jsp"/>
<!-- header -->
	<% RecipeBean rb=(RecipeBean)request.getAttribute("rb"); 
	
		// 본문 내용 줄 바꿈 표시 
		String f_rcp_content = rb.getF_rcp_content();
		if(f_rcp_content != null) f_rcp_content = f_rcp_content.replace("\r\n","<br>");
	
		String f_rcp_ord_text = rb.getF_rcp_ord();
		if(f_rcp_ord_text != null){
			f_rcp_ord_text=f_rcp_ord_text.replace("\r\n", "<br>");
		}
		
		
	%>


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
	

	

	<form action="./RecipeUpdateAction.re" method="post" enctype="multipart/form-data" name="RecipeForm" onsubmit="return checkForm();">
	 <input type="hidden" name="f_rcp_num" value="<%=rb.getF_rcp_num()%>"> 
	  <% 
	  if(!"".equals(rb.getF_rcp_ord()) && !"".equals(rb.getF_rcp_ord_img()) && 
			!rb.getF_rcp_ord().equals("null") && !rb.getF_rcp_ord_img().equals("null")){
	  System.out.println("f_rcp_num:!!!!!!");
	 			for(int i=0;i<rb.getF_rcp_ord_img().split(",").length;i++){ %>
	 <!-- 이전 이미지 -->
     <input type="hidden" name="file<%=i%>" value="<%=rb.getF_rcp_ord_img().split(",")[i] %>" >

     <%	} %>
	 <% for(int i=0;i<rb.getF_rcp_ord().split(",").length;i++){ %>
     <!-- 이전 텍스트 -->
     <input type="hidden" name="order_text<%=i %>" value="<%=rb.getF_rcp_ord().split(",")[i] %>">
	    
	<% }
				}%>
	
	<input type="hidden" name="f_rcp_th_img" value="<%=rb.getF_rcp_th_img()%>">
	<!-- ------------------본문시작 ------------------------ -->
 	<div class="container"> 
		<div class="col-sm-12">
			<div class="form-group">
					<h1>레시피 수정하기</h1>
			</div>
		</div>
		

		
		
		<div class="col-sm-12 ">
			<div class="form-group title01">
				<!-- 레시피 썸네일 -->
				<div class="form-group t_img" >
					<img id="thumb_img" name="f_rcp_th_img" src="./recipe_upload/<%=rb.getF_rcp_th_img() %>"><br>
					<input type="file" id="input_thum_img" name="input_thum_img" >
				</div>
				
				<!-- 레시피 제목 -->
				<div class="a01">
				<label for="r_1"><h2>레시피 제목</h2></label><br>
				<input type="text" name="f_rcp_title" placeholder="예)소고기 미역국 끓이기" value="<%=rb.getF_rcp_title() %>"
				style="width:400px;"><br>
				</div>
				
				<!-- 요리 소개 -->
				<div class="a02">
				<label for="r_2"><h2>요리 소개</h2></label><br>
				<textarea name="f_rcp_content" placeholder="예) 이 레시피의 탄생 배경을 적어주세요" 
				style="width:400px; height: 180px"><%=rb.getF_rcp_content() %></textarea><br>
				</div>
				
				<!-- 카테고리 -->
				<div class="a03">
				<label for="r_3"><h2>카테고리</h2></label><br>
				<select name="category" id="category">
				<option value="1" 
					<% if(rb.getF_rcp_cate().equals("1")){ %>
					selected
					<% } %>
					>국/탕</option>
				<option value="2"
					<% if(rb.getF_rcp_cate().equals("2")){ %>
					selected
					<% } %>
				>볶음/반찬</option>
				<option value="3"
					<% if(rb.getF_rcp_cate().equals("3")){ %>
					selected
					<% } %>
				>분식</option>
				<option value="4"
				<% if(rb.getF_rcp_cate().equals("4")){ %>
					selected
					<% } %>
				>간식/디저트</option>
				<option value="5"
				<% if(rb.getF_rcp_cate().equals("5")){ %>
					selected
					<% } %>
				>술이한잔</option>
				<option value="6"
				<% if(rb.getF_rcp_cate().equals("6")){ %>
					selected
					<% } %>
				>캠핑/여행</option>
				<option value="7"
				<% if(rb.getF_rcp_cate().equals("7")){ %>
					selected
					<% } %>
				>고기류</option>
				<option value="8"
				<% if(rb.getF_rcp_cate().equals("8")){ %>
					selected
					<% } %>
				>해물류</option>
				<option value="9"
				<% if(rb.getF_rcp_cate().equals("9")){ %>
					selected
					<% } %>
				>헬씨/다이어트</option>
				</select>
				</div>
			</div>
		</div>
	
	
	
		<div class="col-sm-12 size">
			<div class="form-group ingredi">
				<!-- 재료 -->
				<label for="r_4"><h2>재료</h2></label>
				<br>
				<span class="warning"> 3개까지만 추가 가능합니다. </span><br>
					<%
					if(!rb.getF_rcp_ingre().equals(",") ){
							
						System.out.println("rcp_ingre:"+rb.getF_rcp_ingre());
							
						int size = rb.getF_rcp_ingre().split(",").length;
						for(int i=0;i<size/2;i++){
							
		 					
					%>
					   <input type="text" name="f_rcp_ingre<%=i %>"  id="R_ingre" value="<%=rb.getF_rcp_ingre().split(",")[2*i] %>"> 
						
						
					
						<input type="text" name="f_rcp_ingre<%=i %>"  id="R_ingre" value="<%=rb.getF_rcp_ingre().split(",")[2*i+1] %>"><br>
					<%
								}
							
							
					}%>
									
					
					<!-- <div id="addedIngre"></div><br> -->
					
					<!-- <div class="button-group">
						<input type="button" onclick="addIngre()" value="추가" class="btn-primary"> 
						<input type="button" onclick="delIngre()" value="삭제" class="btn-primary"> 
					</div> -->
					
			</div>
			<div class="form-group01 order">
				<!-- 요리순서 -->
				<label for="" id=""><h2>요리순서</h2></label>
				<br>
				<span class="warning"> 3개까지만 추가 가능합니다. </span><br>
				<% if(!"".equals(rb.getF_rcp_ord()) && !"".equals(rb.getF_rcp_ord_img()) && 
						!rb.getF_rcp_ord().equals("null") && !rb.getF_rcp_ord_img().equals("null")){
		  					 int size = 0;
		    				 try{   
		        			 size = rb.getF_rcp_ord().split(",").length;
		     					 }catch(Exception e){
		     						 } %>
     					 	
					<div class="vertical">
					<textarea name="f_rcp_ord_text" style="height: 150px; width: 300px;" id="f_rcp_ord_text"
			 		placeholder="예)소고기는 기름기를 떼어내고 적당한 크기로 썰어주세요"><%=rb.getF_rcp_ord().split(",")[0] %></textarea><br>
			 		
				<%	System.out.println("getF_rcp_ord[0]"+rb.getF_rcp_ord().split(",")[0]);
					
					/* int size1 = rb.getF_rcp_ord().split(",").length; */
							for(int i=1;i<size;i++){
				%>
					 <textarea name="ord_text<%=i %>" style="height: 150px; width: 300px;" id="ord_text"><%=rb.getF_rcp_ord().split(",")[i] %></textarea><br>
				<% 
					System.out.println("getF_rcp_ord[i]"+rb.getF_rcp_ord().split(",")[i]);
					} 
				
						%>
					</div>
					
					
					<!-- 요리 이미지 -->
					<div class="vertical_01">
					<%	int sizee = rb.getF_rcp_ord_img().split(",").length;
							for(int i=0;i<sizee;i++){ %>
					<img id="f_rcp_ord_imgfile<%=i %>" class="f_rcp_ord_img" src="./recipe_upload/<%=rb.getF_rcp_ord_img().split(",")[i] %>"><br>
					<input type="file" id="ord_img_upload<%=i %>" name="ord_img_upload<%=i%>" onchange="fileUpload(this)"><br>
					<!-- <input type="button" class="OrdRemove" value="삭제"></button><br> -->
					
				<% System.out.println("getF_rcp_ord_img[i]"+rb.getF_rcp_ord_img().split(",")[i]);
						}
					
					}%>	
					<br>
					
					
					 </div>
					 
			 		<!--  <input type="button" class="AddCuisine" value="추가" class="btn-primary">
			 		<div id="addedCuisine"></div><br>   -->
			 		
				<!-- 해시태그 -->
				<br>
				<div id="hash01">
				<label id=""><h2>해시태그</h2></label><br>
				<input type="text" name="f_rcp_hashtag" id="hashtag" value="<%=rb.getF_rcp_hashtag()%>"><br>	
				<!-- 요리등록 & 삭제 -->
				<input type="submit" value="수정완료" class="btn-primary regi">	
				</div>
			</div>
			
			<!-- 요리완성사진 -->
			<!-- <div id="" class="form-group" >
			<label for="" id=""><h2>요리 완성사진</h2></label><br>
				<input type="file" name="f_rcp_ord_img0" id="f_rcp_imgs_upload" multiple="multiple"> <br>
					<div id="imagePreview">
					<div class="f_rcp_imgs"></div>
					</div>
			</div> -->
		
			<div id="" class="form-group " >
				
				
			</div>
			
		</div>
	
	</div>
 </div> 
	
</form>
	
	<!-- footer -->
	 	  <jsp:include page="../inc/footer.jsp" />   
	<!-- footer -->


<script type="text/javascript" src="./js/jquery-3.5.1.min.js" charset="utf-8"></script>
<script type="text/javascript">

/* 대표 이미지 미리보기 시작 */
	var sel_file;
	
	$(document).ready(function(){
		
		$("#input_thum_img").on("change", handleImgFileSelect)
		});
	
	function handleImgFileSelect(e) {
	  var files = e.target.files;
	  var fileArr = Array.prototype.slice.call(files);
	  console.log(e.target.result);
	  console.log(fileArr);
	  fileArr.forEach(function(f){
	    	if(!f.type.match("image.*")){
	        	alert("이미지 확장자만 업로드 가능합니다.");
	            return;
			}
	    	
	    sel_file=f;
	    	
	    var reader = new FileReader();

	    reader.onload = function(e){
	        	 $("#thumb_img").attr("src",e.target.result);
	        	 
	            }
	            reader.readAsDataURL(f);
	        });
	} /* 대표 이미지 미리보기 끝 */

	
	
	// 요리 순서 미리보기 이미지 함수 
	function fileUpload(fis){ 
		
		//alert(fis.files);
		var str = fis.id; // 클릭한 특정 파일 태그의 id
		var cutStr = str.slice(-1); // 그 id에서 숫자만 골라내기
		//alert(cutStr);
		
		/* $('#f_rcp_ord_imgfile'+cutStr).attr('src','./recipe_upload/'+str); */
		console.log(fis.files[0]);
		console.log(fis.files);
		 if (fis.files && fis.files[0]) {
			 
				var reader = new FileReader();
				
				reader.onload = function(e) {
					$('#f_rcp_ord_imgfile'+cutStr).attr('src', e.target.result); 
				}
				
				reader.readAsDataURL(fis.files[0]);
			}
		
	}
	// 요리 순서 미리보기 이미지 함수 끝
	
	
	


</script>
	
</body>
		

		
	
</html>