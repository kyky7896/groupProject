<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/reWriteCSS/gr_reWriteCSS.css">
<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">

</head>
<body>
	<jsp:include page="../inc/header.jsp"/>
	
	<div class="div_rv_main">
		<h1 class="h1_rv_title">리뷰작성</h1>	
		<br>
		<div class="div_rv_tab">
			<form name="rvForm" action="./ReviewAdd.rv" enctype="multipart/form-data" method="post" onsubmit="return check()">
				<input type="hidden" name="rv_gr_num" value="<%=request.getParameter("gr_num") %>">
				<table border="1">
					<tr>
						<td>아이디</td>
						<td><input type="text" value="<%=session.getAttribute("f_mem_id") %>" readonly="readonly"></td>
					</tr>
					<tr>
						<td>별점</td>
						<td><span class="star-input"> <span class="input">
									<input type="radio" name="star-input" value="0.5" id="p1">
									<label for="p1">0.5</label> <input type="radio" name="star-input"
									value="1" id="p2"> <label for="p2">1</label> <input
									type="radio" name="star-input" value="1.5" id="p3"> <label
									for="p3">1.5</label> <input type="radio" name="star-input"
									value="2" id="p4"> <label for="p4">2</label> <input
									type="radio" name="star-input" value="2.5" id="p5"> <label
									for="p5">2.5</label> <input type="radio" name="star-input"
									value="3" id="p6"> <label for="p6">3</label> <input
									type="radio" name="star-input" value="3.5" id="p7"> <label
									for="p7">3.5</label> <input type="radio" name="star-input"
									value="4" id="p8"> <label for="p8">4</label> <input
									type="radio" name="star-input" value="4.5" id="p9"> <label
									for="p9">4.5</label> <input type="radio" name="star-input"
									value="5" id="p10"> <label for="p10">5</label>
							</span>
						</span> <input type="button" onclick="ratingCheck()" value="check">
							<input type="hidden" name="rv_rating" id="rv_rating"> <input type="hidden" id="nullCheck" value="checkOff"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea cols="30" rows="5" name="rv_content"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" id="addFiles" value="사진 추가"></td>
						<td id="fileTd">
							<div id="fileDiv0">
								<input type="file" id="input_imgs0" name="files0"> <span id="imgs0"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="리뷰 등록"></td>
					</tr>
				</table>
			</form>			
		</div>
		<br>		
		<input class="btn_cancel" type="button" name="btn_cancel" value="취소하기" onclick="location.href='./GoodResContentAction.gr?gr_num=<%=request.getParameter("rv_gr_num") %>'">
	</div>
	
	<jsp:include page="../inc/footer.jsp"/>
	
	<script type="text/javascript">
		function check() {
		  if(document.getElementById("nullCheck").value=="checkOff"){
		    alert("별점을 부여하고 체크 버튼을 확인해주세요");
		    return false;
		  }
		}
		
		function ratingCheck() {
		  var obj_length = document.getElementsByName("star-input").length;
		
		  for (var i=0; i<obj_length; i++) {
		    if (document.getElementsByName("star-input")[i].checked == true) {
		      alert(document.getElementsByName("star-input")[i].value);
		      document.getElementById('rv_rating').value
		        = document.getElementsByName("star-input")[i].value;
		      document.getElementById("nullCheck").value = "CheckOn";
		    }
		  }
		}
	</script>
	<script src="./cjkit/web/js/jquery/jquery-3.5.1.js"></script>
	<script type="text/javascript">
		$(function(){
		  $("#input_imgs0").on('change', handleImgsFilesSelect);
		});
		
		function handleImgsFilesSelect(e){
		  var files = e.target.files;
		  var filesArr = Array.prototype.slice.call(files);
		  
		  filesArr.forEach(function(f){
		    if(!f.type.match('image.*')){
		      alert("확장자는 이미지 확장자만 가능합니다.");
		      return;
		    }
		    
		    var sel_file = f;
		    
		    var reader = new FileReader();
		    reader.onload = function(e){
		      var html = 
		        "<img src=\""+e.target.result+"\">";
		      $("#imgs0").empty();
		      $("#imgs0").append(html);
		      console.log(html);
		    } 
		    
		    reader.readAsDataURL(f);
		  });
		} // 기본 file tag 미리보기
		
		var index = 1;
		var input_imgs;
		var imgs;
		var delSwitch = true;
		$(function(){
		  $("#addFiles").on("click", function addFiles(){
		    
		    input_imgs = "#input_imgs"+index;
		    imgs = "#imgs"+index;
			  
		    if(delSwitch){
		      var delButton =
		        "<input type=\"button\" id=\"dels0\" value=\"삭제하기\">";
		      $("#fileDiv0").append(delButton);
		      delSwitch = false; // file del button 추가
		      
		      $("#dels0").on("click", function(){
		        $("#fileDiv0").remove();
		      }); // file del 기능
		    } // 기본 file tag의 del
		    
		    var divHtml = 
		      "<div id=\"fileDiv"+index+"\">"+
		      "<input type=\"file\" id=\"input_imgs"+index+"\" name=\"files"+index+"\">"+
		      "<span id=\"imgs"+index+"\"></span>"+
		      "<input type=\"button\" id=\"dels"+index+"\""+
		      " onclick=\"divDel("+index+")\" value=\"삭제하기\">"+
		      "</div>";
		    $("#fileTd").append(divHtml); // file div 추가
		    
		    var sel_file;
		    
		    $(input_imgs).on('change', handleImgsFilesSelect);
		    
		    function handleImgsFilesSelect(e){
		      var files = e.target.files;
		      var filesArr = Array.prototype.slice.call(files);
		      
		      filesArr.forEach(function(f){
		        if(!f.type.match('image.*')){
		          alert("확장자는 이미지 확장자만 가능합니다.");
		          return;
		        }
		        
		        var sel_file = f;
		        
		        var reader = new FileReader();
		        reader.onload = function(e){
		          var html = 
		            "<img src=\""+e.target.result+"\">";
		          $(imgs).empty();
		          $(imgs).append(html);
		        } 
		        
		        reader.readAsDataURL(f);
		      });
		    } // file tag 미리보기
		    
		    index++;
		  });
		});
		
		function divDel(index){
		  var divDel = "fileDiv"+index;
		  var fileDiv = document.getElementById(divDel);
		  fileDiv.remove();
		}; // 추가된 file div를 삭제	
	</script>

</body>
</html>