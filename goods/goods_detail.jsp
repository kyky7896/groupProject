<%@page import="com.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�������</title>
<!-- (�ڡڡڽ���) �߰� (�̹��� �����̵� -css �κ� ) 
		�̹��� �����̵� ��� ������ �ʿ��� css�ڵ��Դϴ�.
		css ������ ���� �� ���� .. �ϴ� ��� �κ��� �ٿ��� �����ϴ�!
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
	margin-left: 20px;/* ���� */
} 
</style>
<!-- (�ڡڡڳ�) �߰� (�̹��� �����̵� -css )-->

<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css">

<title>Insert title here</title>
<script type="text/javascript">
    function isBasket(){
    	//alert(" ��ٱ��� ó�� ");
    	
    	// ���� ����,ũ��,���� �� ���ý� �̵� X
    	if(document.gfr.amount.value == ""){
    		alert(" ���ż����� �Է��ϼ���.");
    		document.gfr.amount.focus();
    		return;
    	}
    	
		var check = confirm("��ٱ��Ͽ� �����Ͻðڽ��ϱ�?");
    	
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
	<br><h1 align="center"> ��ǰ�� ������  </h1><br>
	<form action="" method="post" name="gfr">
	<input type="hidden" name="gs_num" value="<%=gb.getF_gs_num()%>">
	<table border="1">
		<tr>
			<td rowspan="7" style="width: 400px;">
			
<!-- (�ڡڡڽ���) �߰� (���� �̹��� ���̴� ĭ �ȿ�,���ε��� ��� �̹����� �����̵� ���·� ���������� �����Ͽ����ϴ�~)-->
<div class="slideshow-container">
    
   <%
   		// upload������ �̹����� ������ ���� �ȶ���Ф�! ������ �ְ� �׽�Ʈ�� �ּ���~		
   
		// String[] �迭 �ȿ� �ִ� ���� ���� ���ϱ�
		// ������ ���� ���ؾ� �ϴ� ���� : null�� ���� 
		// (gb.getF_gs_img().split(",").length��ŭ �ݺ��ǵ��� �ϴϱ� null���� ������ �̹����� �� ���� �ϴµ� ���� �̹���ĭ�� ��
		// �� 4���� ĭ�� �ִ� String �迭(�̹��� ���ε�� 4�� �̹����� ���ε� �ǵ��� �س���)�� 3���� ������ ���ε��� ��� ������ ĭ�� null���� �����
		// gb.getF_gs_img().split(",")[i] != null �� ��쿡�� �̹��� �����̵尡 ���������� �����Ϸ��� ������ ����x
		// console�� ���ϱ� null ���ڰ��� �����淡 equals("null") ����ϴ� �����.
		// �ݺ����� ���� ����ϸ� ���� �ʴٰ� ��...�Ф� Ȥ�� �� ���� ����� �˰� ��Ŵٸ� �ڵ� �������ּ���^_^b
   		
		int size = gb.getF_gs_img().split(",").length;//split���� ��, String�迭�� ����Ǵ� �̹��� ���� ����(null ���� ����)
   		int count = 0; // split���� ��, String �迭���� �̹��� ���� ����(null ���� ����)
   		for(int i=0;i<size;i++){
   			if(!gb.getF_gs_img().split(",")[i].equalsIgnoreCase("null")){
   				
	   			//System.out.println(gb.getF_gs_img().split(",")[i]); //���ε� �Ǿ��ִ� �̹��� �̸�
   				count += 1;
   			}
   		}
   			//System.out.println("count:"+count);
   			
   		for(int i=0;i<count;i++){ // �̹��� ������ŭ �̹��� �����̵� �ݺ�
   			
   	%>
   
   		<div class="mySlides fade">
   			
  			<div class="numbertext"><%=i+1 %> / <%=count %></div>
  				<img src="./upload/<%=gb.getF_gs_img().split(",")[i] %>" 
  					style="width: 400px; height: 400px;">
		    </div>	
   		
   	<%
   		}
	%>
 
		<!-- ���� �̹����� �̵��ϴ� > , < ��ư -->
		<a class="prev" onclick="plusSlides(-1) ">&#10094;</a>
		<a class="next" onclick="plusSlides(1) ">&#10095;</a>
</div>
<br>
	
<!-- (�ڡڡڳ�) �߰� (���� �̹��� ���̴� ĭ �ȿ�,���ε��� ��� �̹����� �����̵� ���·� ���������� �����Ͽ����ϴ�~) -->
			
		</tr>
		<tr>
			<td>��ǰ�̸� : <b><%=gb.getF_gs_name() %></b></td>
		</tr>
		<tr>
			<td>�ǸŰ��� : <%=gb.getF_gs_price() %> �� </td>
		</tr>
		<tr>
			<td>���ż��� : <input type="text" name="amount" placeholder="������ �Է����ּ���"></td>
		</tr>
		<tr>
			<td>�������� : <%=gb.getF_gs_amount() %> </td>	
		</tr>	
		<tr>
			<%
			  String msg = gb.getF_gs_msg();
	
		      if(msg!=null){
		          msg = msg.replace("\r\n", "<br>");
		      }
			%>
			<td>
				��ǰ���� :<br><br>
				<%=msg %>
			</td>
		</tr>
		<tr>
			<td><a href="javascript:isBasket()">&nbsp;&nbsp;&nbsp;[��ٱ��� ���]</a>
			<!-- <a href="javascript:isBuy()">[�����ϱ�]</a></td> ���� ������.. �ּ�ó����.-->
		</tr>
	
	</table>
	</form>

	
<!-- (�ڡڡڽ���) �߰� (�̹��� �����̵�-�ڹٽ�ũ��Ʈ) �� �ٵ� ������ �±�(/body) �ٷ� ���� �ٿ��ּ���!! �ȱ׷� �۵��� �� �ȵǿ�Ф� -->
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
<!-- (�ڡڡڳ�) �߰� (�̹��� �����̵�-�ڹٽ�ũ��Ʈ) -->

</body>
</html>