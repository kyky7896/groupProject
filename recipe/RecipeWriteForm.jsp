<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
	
		
		
	<meta name="description" content="">
	<meta name="keywords" content="">

	
	<title>레시피 등록 - 집밥을 특별하게, 쿡킷</title>
	<link rel="shortcut icon" href="https://www.cjcookit.com/cjkit/web/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
	<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/recipeCSS/recipeWrite.css">
	
<script type="text/javascript" src="./js/jquery-3.5.1.min.js" charset="utf-8"></script>
<script type="text/javascript">

	
	
	
	
	/* 요리 순서 이미지 생성 */
	 
	var sel_file1;
	function handleImgFileSelect1(e) {
		  var files = e.target.files;
		  var fileArr = Array.prototype.slice.call(files);
		  //이 코드가 존재하는 함수의 매개변수로 넘어온 값들을 array로 변환하겠다는 것
		  //  https://tbang.tistory.com/132
		  //console.log(fileArr);
		  fileArr.forEach(function(f){
		    	if(!f.type.match("image.*")){
		        	alert("이미지 확장자만 업로드 가능합니다.");
		            return;
				}
		    	
		    	
		    	sel_file1=f;
		    	
		    	 var reader = new FileReader();
		         reader.onload = function(e){
		        	 //파일을 읽기에 성공하면 e 변수로 접근
			         //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
			         //alert(e.target.result);
		        	 
		        	 $("#f_rcp_ord_img").attr("src",e.target.result);
		        	 //src에 e.target.result
		        	// html태그 중 id 가 empImg 인 태그에 src 속성 값을 읽은 파일로 변경 ( src = "파일에서 읽은 값 즉 파일")
		            }
		            reader.readAsDataURL(f);
		        });
	}    
	/* 요리 이미지 생성 */
	
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
	

	

	<form action="./RecipeWriteAction.re" method="post" enctype="multipart/form-data" name="RecipeForm" onsubmit="return checkForm();">
	<!-- ------------------본문시작 ------------------------ -->
 	<div class="container"> 
		<div class="col-sm-12">
			<div class="form-group">
					<h1>레시피 등록</h1>
			</div>
		</div>
		
		
		<div class="col-sm-12 ">
			<div class="form-group title01">
				<!-- 레시피 썸네일 -->
				<div class="form-group t_img" >
				<img id="thumb_img" name="f_rcp_th_img"><br>
				<input type="file" id="input_thum_img" name="input_thum_img">
				</div>
				
				<!-- 레시피 제목 -->
				<div class="a01">
				<label for="r_1"><h2>레시피 제목</h2></label><br>
				<input type="text" name="f_rcp_title" placeholder="예)소고기 미역국 끓이기"
				style="width:400px;"><br>
				</div>
				
				<!-- 요리 소개 -->
				<div class="a02">
				<label for="r_2"><h2>요리 소개</h2></label><br>
				<textarea name="f_rcp_content" placeholder="예) 이 레시피의 탄생 배경을 적어주세요" 
				style="width:400px; height: 180px"></textarea><br>
				</div>
				
				<!-- 카테고리 -->
				<div class="a03">
				<label for="r_3"><h2>카테고리</h2></label><br>
				<select name="category" id="category">
				<option value="1">국/탕</option>
				<option value="2">볶음/반찬</option>
				<option value="3">분식</option>
				<option value="4">간식/디저트</option>
				<option value="5">술이한잔</option>
				<option value="6">캠핑/여행</option>
				<option value="7">고기류</option>
				<option value="8">해물류</option>
				<option value="9">헬씨/다이어트</option>
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
						
				 	<input type="text" name="f_rcp_ingre0"  placeholder="돼지고기" id="R_ingre">
					<input type="text" name="f_rcp_ingre0"  placeholder="300g" id="R_ingre"> 
					<div id="addedIngre"></div><br>
					
					<div class="button-group">
						<input type="button" onclick="addIngre()" value="추가" class="btn-primary"> 
						<input type="button" onclick="delIngre()" value="삭제" class="btn-primary"> 
					</div>
						
			</div>
			<div class="form-group01 order">
				<!-- 요리순서 -->
				<label for="" id=""><h2>요리순서</h2></label>
				<br>
				<span class="warning"> 3개까지만 추가 가능합니다. </span><br>
					<textarea name="f_rcp_ord_text" style="height: 150px; width: 300px;"
			 		placeholder="예)소고기는 기름기를 떼어내고 적당한 크기로 썰어주세요"></textarea>
					<img id="f_rcp_ord_img" class="f_rcp_ord_img"><br>
					<input type="file" id="ord_img_upload" name="ord_img_upload">
			
			 		<input type="button" class="AddCuisine btn-primary" value="추가" >
			 		<div id="addedCuisine"></div>
			 		
			 		<!-- 해시태그 -->
			 		<div class="tag">
						<p id="hashtag"><h2>해시태그</h2></p><br>
						<input type="text" name="f_rcp_hashtag" id="hashtag"><br>	
						<!-- 요리등록 & 삭제 -->
						<input type="submit" value="요리등록" class="btn-primary regi" >	
						<input type="reset" value="모두삭제" class="btn-primary regi">
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
		
			<div class="form-group" >
				
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

	/* 대표 이미지 생성 */
		var sel_file;
	
	$(document).ready(function(){
		//대표 이미지 
		$("#input_thum_img").on("change", handleImgFileSelect);
		
		
	});
	
	function handleImgFileSelect(e) {
		  var files = e.target.files;
		  var fileArr = Array.prototype.slice.call(files);
		  
		  fileArr.forEach(function(f){
		    	if(!f.type.match("image.*")){
		        	alert("이미지 확장자만 업로드 가능합니다.");
		            return;
				}
		    	
		    	sel_file=f;
		    	
		    	 var reader = new FileReader();
		    	 
		    	 //alert(sel_file);
		         reader.onload = function(e){
		        	 $("#thumb_img").attr("src",e.target.result);
		        	 
		            }
		            reader.readAsDataURL(f);
		        });
	} 
	/* 대표 이미지 생성 */
	

	/* 재료 추가  */
	
  var count = 1;
 
  function addIngre(){
  var addedIngre = document.getElementById("addedIngre");
 
  var str = "";
 
  str+="<br><input type='text' name='f_rcp_ingre"+count+"' class='form-control form-control-sm' id='R_ingre'>";
  str+="<input type='text' name='f_rcp_ingre"+count+"' class='form-control form-control-sm' id='R_ingre'>";
 
  // 추가할 폼(에 들어갈 HTML)
 
 if(count<=3){
	  var addedDiv = document.createElement("div"); // 폼 생성
	  addedDiv.id = "added_"+count; // 폼 Div에 ID 부여 (삭제를 위해)
	  addedDiv.innerHTML = str; // 폼 Div안에 HTML삽입
	  addedIngre.appendChild(addedDiv); // 삽입할 DIV에 생성한 폼 삽입
	 
	  count++;
	 console.log("count:"+count);
	 }else{
		 alert("3개까지 추가 가능합니다");
	 }
  }
  
 
	

  function delIngre(){
	 
	  var addedIngre = document.getElementById("addedIngre");
	 
	 
	  if(count >=1){ // 현재 생성된 폼이 하나 이상이면
	 
	  var addedDiv = document.getElementById("added_"+(--count));
	  // 마지막으로 생성된 폼의 ID를 통해 Div객체를 가져옴
	  console.log("count :"+count)
	  
	  addedIngre.removeChild(addedDiv); // 폼 삭제
	 
	  }else{ // 마지막 폼만 남아있다면
	 
	  document.addedIngre.reset(); // 폼 내용 삭제
	 
	  }
	  
 
  } 
  

	/* 재료 추가 끝 */
	
	
			
	// 이미지 순서(0) 들어가는 코드
	$("#ord_img_upload").on("change", handleImgFileSelect1);
	
	/* 요리 순서 이미지(0) 생성 */
	 var sel_file1;
	function handleImgFileSelect1(e) {
		  var files = e.target.files;
		  var fileArr = Array.prototype.slice.call(files);
		  //이 코드가 존재하는 함수의 매개변수로 넘어온 값들을 array로 변환하겠다는 것
		  //  https://tbang.tistory.com/132
		  //console.log(fileArr);
		  fileArr.forEach(function(f){
		    	if(!f.type.match("image.*")){
		        	alert("이미지 확장자만 업로드 가능합니다.");
		            return;
				}
		    	
		    	
		    	sel_file1=f;
		    	
		    	 var reader = new FileReader();
		         reader.onload = function(e){
		        	 //파일을 읽기에 성공하면 e 변수로 접근
			         //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
			         //alert(e.target.result);
		        	 
		        	 $("#f_rcp_ord_img").attr("src",e.target.result);
		        	 //src에 e.target.result
		        	// html태그 중 id 가 empImg 인 태그에 src 속성 값을 읽은 파일로 변경 ( src = "파일에서 읽은 값 즉 파일")
		            }
		            reader.readAsDataURL(f);
		        });
	}

	/* 요리 순서 추가 생성 */
	
	var CuiNum=1;
	$('.AddCuisine').on("click",function(){
		if($("textarea[name^='ord_text']").length<=2){
			 $('#addedCuisine').append(
					 '<div id="ac'+CuiNum+'">'
					 +'<textarea name="ord_text'+CuiNum+'" style="height: 150px; width: 300px;"></textarea>'
					 +'<img src="" id="f_rcp_ord_imgfile'+CuiNum+'" name="f_rcp_ord_imgfile'+CuiNum+'" class="f_rcp_ord_imgfile'+CuiNum+'">'
					 +'<input type="button" class="OrdRemove'+CuiNum+' btn-primary" value="삭제"></button>'
				 	 +'<input type="file" id="ord_img_upload'+CuiNum+'" name="ord_img_upload'+CuiNum+'" class="ord_img_upload'+CuiNum+'">'
					 +'</div>'
				
			 ); //input 추가
			 
			 
		 }else{
			 alert("3개까지만 추가 가능합니다.")
		 }
		 
		var OrdRemove=".OrdRemove"+CuiNum;
		var ac="#ac"+CuiNum;
		
		
		 $(OrdRemove).on('click',function(){
		 console.log("ac:"+ac)
			
			 $(ac).remove(); 
		});
		 
		
		CuiNum++;
		console.log("CuiNum:"+CuiNum); 
				

				
			//이미지 순서(1~3) 들어가는 코드
			var ImgStep=CuiNum-1;
			console.log("ImgStep :"+ImgStep)
			var AddOrdUpload="#ord_img_upload"+(ImgStep);
			var AddOrdImg="#f_rcp_ord_imgfile"+(ImgStep);
			console.log("AddOrdUpload :"+AddOrdUpload)
			
				$(AddOrdUpload).on("change", handleImgFileSelect3);
		
			
			


	/* 요리 순서 이미지(1~3) 생성 */
	var sel_file2;
	function handleImgFileSelect3(e) {
		
		  var files = e.target.files;
		  var fileArr = Array.prototype.slice.call(files);

		  fileArr.forEach(function(f){
		    	if(!f.type.match("image.*")){
		        	alert("이미지 확장자만 업로드 가능합니다.");
		            return;
				}
		    	
		    	
		    	sel_file2=f;
		    	
		    	 var reader = new FileReader();
		         reader.onload = function(e){

		        	 
		        	 $(AddOrdImg).attr("src",e.target.result);
		        
		            }
		            reader.readAsDataURL(f);
		        });
	}	
			
	}); 
		
	/* 요리 순서 이미지(1~3) 미리보기 생성 끝 */
	
	
	


</script>
	
</body>
		

		
	
</html>