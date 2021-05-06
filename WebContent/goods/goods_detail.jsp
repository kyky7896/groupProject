<%@page import="com.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>라따뚜이</title>
<!-- (★★★시작) 추가 (이미지 슬라이드 -css 부분 ) 
		이미지 슬라이드 기능 구현에 필요한 css코드입니다.
		css 파일을 따로 안 만들어서 .. 일단 헤드 부분이 붙여서 보냅니다!
-->

<style>
* {box-sizing: border-box}
body {font-family: Verdana, sans-serif; margin:0}
.mySlides {display: none}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;
}

/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  padding: 16px;
  margin-top: -22px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .prev, .next,.text {font-size: 11px}
}

 table {
	width: 700px;
	height: 500px;
	margin-left: 20px;/* 삭제 */
} 
</style>
<!-- (★★★끝) 추가 (이미지 슬라이드 -css )-->

<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css">

<title>Insert title here</title>
<script type="text/javascript">
    function isBasket(){
    	//alert(" 장바구니 처리 ");
    	
    	// 구매 수량,크기,색상 미 선택시 이동 X
    	if(document.gfr.amount.value == ""){
    		alert(" 구매수량을 입력하세요.");
    		document.gfr.amount.focus();
    		return;
    	}
    	
		var check = confirm("장바구니에 저장하시겠습니까?");
    	
    	if(check){
    		document.gfr.action = "./BasketAdd.ba";
    		document.gfr.submit();	    		
    	}else{
    		return;
    	}
    	
    	
    }
    
</script>
</head>
<body>

<%
	GoodsBean gb=(GoodsBean)request.getAttribute("goods");
%>
	<br><h1 align="center"> 상품상세 페이지  </h1><br>
	<form action="" method="post" name="gfr">
	<input type="hidden" name="gs_num" value="<%=gb.getF_gs_num()%>">
	<table border="1">
		<tr>
			<td rowspan="7" style="width: 400px;">
			
<!-- (★★★시작) 추가 (메인 이미지 보이는 칸 안에,업로드한 모든 이미지를 슬라이드 형태로 보여지도록 변경하였습니다~)-->
<div class="slideshow-container">
    
   <%
   		// upload폴더에 이미지가 없으면 사진 안떠용ㅠㅠ! 사진을 넣고 테스트해 주세요~		
   
		// String[] 배열 안에 있는 사진 갯수 구하기
		// 갯수를 따로 구해야 하는 이유 : null값 제어 
		// (gb.getF_gs_img().split(",").length만큼 반복되도록 하니까 null값인 사진은 이미지가 안 떠야 하는데 공백 이미지칸만 뜸
		// 총 4개의 칸이 있는 String 배열(이미지 업로드시 4개 이미지만 업로드 되도록 해놨음)에 3개의 사진만 업로드한 경우 마지막 칸은 null값이 저장됨
		// gb.getF_gs_img().split(",")[i] != null 인 경우에만 이미지 슬라이드가 보여지도록 제어하려고 했으나 제어x
		// console에 찍어보니까 null 문자값이 나오길래 equals("null") 사용하니 제어됨.
		// 반복문을 많이 사용하면 좋지 않다고 들어서...ㅠㅠ 혹시 더 좋은 방법을 알고 계신다면 코드 수정해주세요^_^b
   		
		int size = gb.getF_gs_img().split(",").length;//split했을 때, String배열에 저장되는 이미지 사진 갯수(null 갯수 포함)
   		int count = 0; // split했을 때, String 배열에서 이미지 사진 갯수(null 갯수 제외)
   		for(int i=0;i<size;i++){
   			if(!gb.getF_gs_img().split(",")[i].equalsIgnoreCase("null")){
   				
	   			//System.out.println(gb.getF_gs_img().split(",")[i]); //업로드 되어있는 이미지 이름
   				count += 1;
   			}
   		}
   			//System.out.println("count:"+count);
   			
   		for(int i=0;i<count;i++){ // 이미지 갯수만큼 이미지 슬라이드 반복
   			
   	%>
   
   		<div class="mySlides fade">
   			
  			<div class="numbertext"><%=i+1 %> / <%=count %></div>
  				<img src="./upload/<%=gb.getF_gs_img().split(",")[i] %>" 
  					style="width: 400px; height: 400px;">
		    </div>	
   		
   	<%
   		}
	%>
 
		<!-- 다음 이미지로 이동하는 > , < 버튼 -->
		<a class="prev" onclick="plusSlides(-1) ">&#10094;</a>
		<a class="next" onclick="plusSlides(1) ">&#10095;</a>
</div>
<br>
	
<!-- (★★★끝) 추가 (메인 이미지 보이는 칸 안에,업로드한 모든 이미지를 슬라이드 형태로 보여지도록 변경하였습니다~) -->
			
		</tr>
		<tr>
			<td>상품이름 : <b><%=gb.getF_gs_name() %></b></td>
		</tr>
		<tr>
			<td>판매가격 : <%=gb.getF_gs_price() %> 원 </td>
		</tr>
		<tr>
			<td>구매수량 : <input type="text" name="amount" placeholder="수량을 입력해주세요"></td>
		</tr>
		<tr>
			<td>남은수량 : <%=gb.getF_gs_amount() %> </td>	
		</tr>	
		<tr>
			<%
			  String msg = gb.getF_gs_msg();
	
		      if(msg!=null){
		          msg = msg.replace("\r\n", "<br>");
		      }
			%>
			<td>
				상품내용 :<br><br>
				<%=msg %>
			</td>
		</tr>
		<tr>
			<td><a href="javascript:isBasket()">&nbsp;&nbsp;&nbsp;[장바구니 담기]</a>
			<!-- <a href="javascript:isBuy()">[구매하기]</a></td> 현재 사용안함.. 주석처리함.-->
		</tr>
	
	</table>
	</form>

	
<!-- (★★★시작) 추가 (이미지 슬라이드-자바스크립트) 꼭 바디 닫히는 태그(/body) 바로 위에 붙여주세요!! 안그럼 작동이 잘 안되요ㅠㅠ -->
<script type="text/javascript">

	var slideIndex = 1;
	showSlides(slideIndex);
	
	// Next/previous controls
	function plusSlides(n) {
	  showSlides(slideIndex += n);
	}
	
	// Thumbnail image controls
	function currentSlide(n) {
	  showSlides(slideIndex = n);
	}
	
	function showSlides(n) {
		
	  var i=0;
	  var slides = document.getElementsByClassName("mySlides");
	  var dots = document.getElementsByClassName("dot");
	  /* alert(slides.length); */
	  if (n > slides.length) {slideIndex = 1}
	  if (n < 1) {slideIndex = slides.length}
	  for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none";
	  }
	  for (i = 0; i < dots.length; i++) {
	      dots[i].className = dots[i].className.replace(" active", "");
	  }
	  
	  slides[slideIndex-1].style.display = "block";
	  dots[slideIndex-1].className += " active";
	}

</script>
<!-- (★★★끝) 추가 (이미지 슬라이드-자바스크립트) -->

</body>
</html>