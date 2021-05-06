<%@page import="com.recipe.board.db.RecipeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<!-- Mirrored from www.cjcookit.com/pc/event/eventMain by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 23 Mar 2021 09:53:50 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=UTF-8" /><!-- /Added by HTTrack -->
<head>
<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(시작) -->
	<meta charset="utf-8">
	<meta name="description" content="">
	
	<meta name="keywords" content="">
	
	<title>라따뚜이</title>
	<link rel="shortcut icon" href="https://www.cjcookit.com/cjkit/web/images/favicon.ico">

    <link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/common.css?20200924090025">
    <link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/chatbot.css?20200924090025">
	<!-- uri array -->
	
	<!-- 업무 단위로 c:when 문 추가하여 사용하세요. -->
	
			<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/ev.css?20200924090025">
			<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/stamp.css?20200924090025"> <!-- 20200130_추가 -->
			<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/my.css?20200924090025">
		


    <script type="text/javascript" src="../../cjkit/web/js/libs/jquery-1.11.2.min.js"></script>

	<script>
		var cjConf = {
			serverImg : "//www.cjcookit.com/cjkit/web/images",
			loginYn : "N"
		}
	</script>

	<script type="text/javascript" src="../js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="../js/crew/crew17e0.js?20200924090026"></script>
	<script type="text/javascript" src="../js/crew/form/form17e0.js?20200924090026"></script>
	<script type="text/javascript" src="../js/cj17e0.js?20200924090026"></script>
	<script type="text/javascript" src="../js/oauth/kakao.min.js"></script>
	<script type="text/javascript" src="../js/oauth/naveridlogin_js_sdk_2.0.0.js"></script>
	<script type="text/javascript" src="https://www.cjcookit.com/pc/js/netfunnel.js?20200924090026" charset="utf-8"></script>
	<script type="text/javascript" src="https://www.cjcookit.com/pc/js/om_pc_skin.js?20200924090026" charset="utf-8"></script>
	<script type="text/javascript" src="../js/webtoolkit.base64.js"></script>

		<script type="text/javascript" src="https://nsso.cjone.com/findCookieSecured.jsp?cjssoq=fOYjm2%2FlJbWuG7If5njx%2FuF8RRF9w4%2BkbMBBqJUhojnBiyY0JLSJBB0HC0q3lSH8O2PzVFZdZMVBknu0V9WGuVBoR2p5QmJxdHlSbjlwR0JOWWNiWlFJcU1hMDVPNWhhMldQdnZHc2k4Z1hkRGlGSkE4YXU1bkw0RFFtYklSYTVzdUNkNldCaTZSVT0%3D"></script>
		<script>
		//$(document).ready(function(evt){
			if((typeof _cjssoEncData) != "undefined" && _cjssoEncData !=""){
				var redirectUrl = location.pathname + location.search;
				var data = {};
				data.redirectUrl		= redirectUrl;
				data.mode			= "SSO_AUTH";
				data.cjssoq			= _cjssoEncData;

				// 전송
				$.ajax({
					async		: true,
					url			: '/pc/auth/login.json',
					dataType	: 'json',
					type		: 'POST',
					data		: data,
					success		: function(data, textStatus, jqXHR){
						// 로그인 처리 결과 체크
						cj.loginResultCheck(data);
					},
					error	   : function(jqXHR, textStatus, errorThrown){
					  //console.log(jqXHR.status);
					}
				});
			}
		//});
		</script>	
	<meta name="google-site-verification" content="0GuzYD2oaBVARveIoOEQEEx10478h5Vnn5i5YNAS6zI" />	 
		<script type="text/javascript">
			var _isFlagContinue= false;
			function fn_evntAgreePop(evntId){
				var params = {evntId: evntId};
				if(_isFlagContinue) return false;
				
				if (cjConf.loginYn != 'Y') {
					alert('로그인 후 참여 하시기 바랍니다.');
					cj.login();
					return;
				}
				
				_isFlagContinue = true;
				
				// 테스트 구문 퍼블 후 적용.
				$.ajax({
					async: true,
					url: "/pc/event/eventAgree.json",
					dataType: "json",
					type: 'POST',
					data: params,
					success: function(data, textStatus, jqXHR) {
						if (!crew.ajaxValidate(data)) {
							_isFlagContinue = false;
							return;
						}
						if((data.html).length == 0){
							alert("동의항목이 없습니다.");
							_isFlagContinue = false;
							return false;
						}else{
							$("#lay_wrap").remove();
							$(".modal-container").append(data.html);
							vcui.require( ['ui/modal'], function() {
				            	$("#lay_wrap").vcModal();
				        	});
							_isFlagContinue = false;
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						//console.log(jqXHR.status);
						_isFlagContinue = false;
					}
				});
			}
			
		</script>
	<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(끝) -->
	<!-- 팀프로젝트와 관련된 CSS 코드 링크를 여기서 부터 넣어주시면 됩니다. -->
	
	<script src="./js/jquery-3.5.1.min.js"></script>
	<link href="./css/recipeDetail.css" rel="stylesheet"> 
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css?20200924090025">
	<link rel="stylesheet" type="text/css" href="https://www.cjcookit.com/cjkit/web/css/main.css?20200924090025">
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/goodResCSS/goodress_main.css">
	<link rel="stylesheet" type="text/css" href="./cjkit/web/css/recipeCSS/recipeDetail.css">
	
	<style type="text/css">
	
	.container {margin: auto !important;}
	.cont_ingre2-center {/* border: solid red; */
	width: 800px;
	margin: auto;
	padding: 2em 1em;}
	.btn_detail {text-align: center !important;
	margin-left: 0 !important;
	}
	</style>
	
	<!-- 팀프로젝트와 관련된 CSS 코드 링크를 여기서 부터 넣어주시면 됩니다.(끝) -->
</head>
<body>
		<div id="skipnavi">
			<ul>
				<li><a href="#container_title">본문바로가기</a></li>
			</ul>
		</div>

	<div id="wrap">
		<div class="preloader" style="display: none;">
			<div class="loader">
				<span class="loading"></span>
			</div>
		</div>

		<!-- header -->
		<jsp:include page="../inc/header.jsp" />
		<!-- //header -->

		<section id="container">

			<!-- content -->
			<div id="content" class="ev_content">

				<div class="ev_wrap">
					<h1 class="title"></h1>

					<!--(팀프로젝트 쇼핑몰 리스트) 여기서부터 본문 시작입니다. -->
<%	
		String id = (String) session.getAttribute("f_mem_id");
	%>

	<%
		// 로그인 제어X (모든 회원 다 볼 수 있음)
		String f_mem_id = (String)session.getAttribute("f_mem_id");
		System.out.println(f_mem_id);
		// 닉네임 값 꺼내기
		String f_mem_nick = (String)session.getAttribute("f_mem_nick");
		System.out.println(f_mem_nick);
		
		// session영역에서 상세 레시피 데이터 꺼내기(빈)
		RecipeBean rb = (RecipeBean)session.getAttribute("recipeDetail");
		System.out.println("상세 레시피>>"+rb);
		
		// 본문 내용 줄 바꿈 표시 
		String f_rcp_content = rb.getF_rcp_content();
		if(f_rcp_content != null) f_rcp_content = f_rcp_content.replace("\r\n","<br>");
	%>
	
<!-- 본문 전체 영역 -->
<div class="container">

<!-- ★★★----------------------------------------- 메인 이미지 (시작 ) --------------------------------------------★★★ -->
	<div class="view2_pic">
		<!-- 메인 이미지 -->
		<div class="centeredcrop">
			<img src="./recipe_upload/<%=rb.getF_rcp_th_img() %>">
		</div>
	</div>
<!-- ★★★---------------------------------------- 제목 & 요리설명 (시작) --------------------------------------------★★★ -->
	
	<div class="view2_summary">		
		<!-- 제목 -->
		<h1><%=rb.getF_rcp_title() %></h1>
		<!-- 요리설명 -->
		<div class="summary">"<%=f_rcp_content %>"</div>			
		<!-- 해시태그 -->	
		<div class="view_step">
		<%
			if(!rb.getF_rcp_hashtag().equals("")){ // 해시태그 데이터 없는 경우 이 부분 안 보임
		%>
			<div class="view_tag">	
				<a href="./RecipeList.re?q=<%=rb.getF_rcp_hashtag() %>">#<%=rb.getF_rcp_hashtag() %></a>
		<% }%>
			</div>
		</div> 
	</div>
	<%
	
	%>
<!-- ★★★----------------------------------------- 재료 설명 (시작)-------------------------------------------------★★★ -->
<div class="cont_ingre2-center">
<div class="cont_ingre2">
		<!-- '재료' 제목 -->
		<div class="best_tit">
			<b>재료</b>
			<span>Ingredients</span>
		</div>
		<!-- 재료, 양념 리스트 -->
		<div class="ready_ingre3" id="divConfirmedMaterialArea">
			<ul class="ingre_list_ul">
			<%
			if(!rb.getF_rcp_ingre().equals(",")){
					
				System.out.println("뭐냐넌>>"+rb.getF_rcp_ingre());
					
				int size = rb.getF_rcp_ingre().split(",").length;
				for(int i=0;i<size;i++){
 					if(!rb.getF_rcp_ingre().equals("") && i%2 == 0){ 
			%>
			   <li class="ingre_list_li"><%=rb.getF_rcp_ingre().split(",")[i] %> &nbsp;
				
			<%			}else{
			%>	
				<span class="amount"><%=rb.getF_rcp_ingre().split(",")[i] %></span></li>
			<%
			}
					}
				} %>
			</ul>
		</div>
</div>
<!-- ★★★----------------------------------- 조리순서 (시작) --------------------------------------★★★ -->
 
	<div class="view_step">
		<div class="best_tit">
			<b>조리순서</b>
			<span>Steps</span>
		</div>
		<!-- 순서 -->
<%
	if(!rb.getF_rcp_ord().equals("") && !rb.getF_rcp_ord_img().equals("")){ 
		System.out.println("????"+rb.getF_rcp_ord());
		System.out.println("????"+rb.getF_rcp_ord_img());

	int size = rb.getF_rcp_ord().split(",").length;
	for(int i=0;i<size;i++){
%>
		<div class="stepDiv">
		<!-- 글 -->
	<%if(!rb.getF_rcp_ord().equals("")){ %>
<%-- 		<div class="step_text_<%=i %>"> --%>
		 	<div class="step_text">
				<%=rb.getF_rcp_ord().split(",")[i] %>
			</div>
	<%} %>
		<!-- 그림 -->
	<%if(!rb.getF_rcp_ord_img().equals("") || !rb.getF_rcp_ord_img().split(",")[i].equals("")){ %>
			<div class="step_img">
				<img src="./recipe_upload/<%=rb.getF_rcp_ord_img().split(",")[i] %>" width="300" height="169">
			</div>	
	<%} %>
	   </div>
<%
	}  } 
%>	

	<%
		 if(f_mem_id != null && f_mem_id.equals(rb.getF_rcp_id())){	 
	%>
	<div class="btn_detail">
		<input type="button" value="수정하기" onclick="location.href='./RecipeUpdate.re?f_rcp_num=<%=rb.getF_rcp_num()%>'">
		<input type="button" value="삭제하기" onclick="location.href='./RecipeDelete.re?f_rcp_num=<%=rb.getF_rcp_num()%>'">
	</div>
		<!-- ----------------------------------- 조리순서 (끝)-------------------------------------- -->
	 <%} %> 

	</div>
<!-- ★★★----------------------------------- 댓글 (시작)-------------------------------------- -->
	<div class="view_reply">
		<div class="view_title">
			댓글 
			<span id="recipeCommentListCount">댓글 갯수(넣어야 함)</span>
		</div>
		
		<!-- 댓글 목록 -->
		<div id="recipeCommentList_1">
			<!-- 댓글 수 만큼 반복되는 부분 ,ajax에 작성  -->
			<!-- <div class="media reply_list"> -->
			<div class="commentList">
			</div>
			<!-- 댓글 수 만큼 반복되는 부분 -->
		</div>
	</div>
		
		<!-- 댓글 작성 -->
		<div class="reply_write">
			<form name="commentInsertForm">
			<!-- 아이디/닉네임/레시피번호/댓글내용 -->
		<%
			if(f_mem_nick != null){
		%>
<!-- 			<span><input type="text" name="f_rcpc_content"></span> -->
			<input type="hidden" name="f_rcpc_rcp_num" value="<%=rb.getF_rcp_num() %>">
			<input type="hidden" name="f_rcpc_mem_id" value="<%=f_mem_id %>">
			<input type="hidden" name="f_rcpc_mem_nick" value="<%=f_mem_nick %>">
			<span><%=f_mem_nick %></span>
			<div class="input-group" style="width: 680px; ">
				<textarea name="f_rcpc_content" class="form-control" placeholder="댓글을 남겨주세요!" style="height: 100px; width: 100%; resize: none;">
				</textarea>
				<span class="input-group-btn"><button class="btn btn-default" type="button" name="commentInsertBtn1" style="height: 100px; width: 100px;">등록
				</button></span>
			</div>
			</form>
		<% 
			}else{
		%>
			<div class="input-group" style="width: 680px; ">
				<textarea name="f_rcpc_content" class="form-control" placeholder="로그인" onclick="loginArt();" style="height: 100px; width: 100%; resize: none;">
				</textarea>
				<span class="input-group-btn"><button class="btn btn-default" type="button" name="commentInsertBtn1" style="height: 100px; width: 100px;">등록
				</button></span>
			</div>
			<!-- <span><input type="text" name="f_rcpc_content" onclick="loginPopup(); " placeholder="로그인"></span>
			<span><button type="button" >댓글</button></span> -->
		<%
			}
		%>
		</div>		
</div>		
<!-- ----------------------------script------------------------------------------ -->

<script type="text/javascript">

// 로그인X - 댓글 창 클릭
/* function loginPopup() {
    window.open("./MemberLogin.me", "a","width=483, height=422, left=900, right=0, top=500 resizable = no, scrollbars = no");
 }//loginPopup() */
 
// 로그인X - 댓글 창 클릭
function loginArt(){
	 alert('로그인해 주세요!');
 }

//게시글 번호 
	var f_rcp_num = <%=rb.getF_rcp_num() %>; 
	console.log('게시글 번호>>'+f_rcp_num);


//페이지 로딩 시 댓글 목록 출력
	$(document).ready(function(){
		
		commentList();
		countComment();
		
	});//ready()

	
//댓글 등록 버튼 클릭시 
	$('[name=commentInsertBtn1]').click(function(){ 
	    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
	    commentInsert(insertData); //Insert 함수호출(아래)
	});
	
	
//댓글 등록
function commentInsert(insertData){
	
	console.log(insertData);
	
	$.ajax({
		url : './CommentAdd.co',
	    type : 'post',
	    data : insertData,
	    success : function(data){
	    	
	    	console.log(data);
	    	
	        if(data == 1) {
	        	alert('댓글 업로드 완료');
	            $('[name=f_rcpc_content]').val('');//댓글 작성한 곳의 내용 비우기
	            commentList(); //댓글 작성 후 댓글 목록 reload
	            countComment(insertData);
	        }
	    }
	});
	
}//commentInsert(insertData)

	

//댓글 목록 
function commentList(){
		
	
	    $.getJSON('./CommentList.co?f_rcpc_rcp_num='+<%=rb.getF_rcp_num()%>,function(data){
	    	
			var a =''; 
	    	
			$.each(data, function(key,v){ 
				
				console.log('data:'+data);
	    		
				console.log(v.f_rcpc_no);
				console.log(v.f_rcpc_rcp_num);
				

			
		// 원 댓글(f_rcpc_lev == 0) 리스트만 출력, 대댓글 출력X
		if(v.f_rcpc_lev == 0){
			
			a += '<div class="media reply_list"><b class="info_name_m">'+v.f_rcpc_mem_nick +'</b>'+v.f_rcpc_date;
			/* a += '<div class="media reply_list'+v.f_rcpc_no+'">'+'<b class="info_name_m">'+v.f_rcpc_mem_nick +'</b>'+v.f_rcpc_date; */
		if(v.f_rcpc_mem_nick != "null" ){ 
			  if(v.f_rcpc_mem_nick == "<%=f_mem_nick%>" ){ 
			a += '<a class="com" onclick="commentUpdate('+v.f_rcpc_no+',\''+v.f_rcpc_content+'\');"> 수정 </a>';
            a += '<a class="com" onclick="commentDelete('+v.f_rcpc_no+');"> 삭제 </a>';
			  }  }  
		 if(v.f_rcpc_mem_nick != "null" ){ 
			  if(v.f_rcpc_mem_nick != "<%=f_mem_nick%>" ){ 
            a += '<a class="com" onclick="commentReply('+v.f_rcpc_no+','+v.f_rcpc_rcp_num+','+v.f_rcpc_ref+','+v.f_rcpc_lev+','+v.f_rcpc_seq+');"> 답글 </a></div>'; 
			} }
            a += '<div class="commentContent'+v.f_rcpc_no+'"> '+v.f_rcpc_content+'</div>';
			a += '<div class="insertRe'+v.f_rcpc_no+'"></div>'; // 답글 작성 form 붙는 곳
		}
		if(v.f_rcpc_lev > 0 && v.f_rcpc_seq == 1){
            a += '<div class="reCommentList'+v.f_rcpc_ref+'" id="reListView"><a onclick="showReList('+v.f_rcpc_ref+');">답글 더보기</a></div>';/* 답글 내용이 있는 경우에만 보이도록  */
            a += '<div class="list'+v.f_rcpc_ref+'"></div></div>'
		}
			
        	 $(".commentList").html(a);
    		}); 
    	
    	 }); 
}//commentList()
    

    
    
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(f_rcpc_no, f_rcpc_content){
	    var a ='';
	    
	    a += '<div class="input-group">';
	    a += '<input type="text" class="form-control" name="content_'+f_rcpc_no+'" value="'+f_rcpc_content+'"/>';
	    a += '<button class="btn btn-default" type="button" onclick="commentUpdateProc('+f_rcpc_no+');">수정</button>';
	    a += '<button class="btn btn-default" type="button" onclick="commentList();">취소</button>';
	    a += '</div>';
    $('.commentContent'+f_rcpc_no).html(a);
	    
}//commentUpdate()        


//댓글 수정
function commentUpdateProc(f_rcpc_no){
	 var updateContent = $('[name=content_'+f_rcpc_no+']').val(); // 수정된 특정 댓글의 값을 가져옴 
	    
	    $.ajax({
	        url : './CommentUpdate.co',
	        type : 'post',
	        data : {'f_rcpc_content' : updateContent, 'f_rcpc_no' : f_rcpc_no},
	        success : function(data){
	            if(data == 1) commentList(f_rcpc_no); //댓글 수정후 목록 출력 
	        }
	    });
}//commentUpdateProc()

//댓글 삭제 
function commentDelete(f_rcpc_no){
		
	   $.ajax({
	       url : './CommentDelete.co',
	       type : 'post',
	       data : {'f_rcpc_no' : f_rcpc_no},
	       success : function(data){
	           commentList(f_rcpc_no); //댓글 삭제후 목록 출력 
	       }
	   });
}//commentDelete(f_rcpc_no)

//댓글 갯수 가져오기 
function countComment(f_rcpc_no){
	
	$.getJSON('./CountComment.co?f_rcpc_num='+<%=rb.getF_rcp_num() %>,function(data){
				
		/* alert(data); */
			
		if(data >0 ){
			console.log('댓글 1개 이상 있는 경우');
			$('#recipeCommentListCount').html(data+'개');
		}else{
			$('#recipeCommentListCount').html(0+'개');
		}
	
		});//getJSON();
	
}//countComment()


//답글 쓰는 Form
function commentReply(f_rcpc_no,f_rcpc_rcp_num,f_rcpc_ref,f_rcpc_lev,f_rcpc_seq){
		
	var a ='';
	    
	    a += '<div class="input-group2">';
	    a += '<form name="reCommentInsertForm">';
	    a += '<input type="text" class="form-control2" name="f_rcpc_content" value="답글 추가..."/>';
	    a += '<input type="hidden" name="f_rcpc_mem_nick" value="${f_mem_nick}">';
	    a += '<input type="hidden" name="f_rcpc_ref" value="'+f_rcpc_ref+'"/>';
	    a += '<input type="hidden" name="f_rcpc_lev" value="'+f_rcpc_lev+'"/>';
	    a += '<input type="hidden" name="f_rcpc_seq" value="'+f_rcpc_seq+'"/>';
	    a += '<input type="hidden" name="f_rcpc_no" value="'+f_rcpc_no+'"/>';
	    a += '<input type="hidden" name="f_rcpc_mem_id" value="${f_mem_id}"/>';/* 세션영역에 저장되어 있는 id */
	    a += '<input type="hidden" name="f_rcpc_rcp_num" value="'+f_rcpc_rcp_num+'"/>';
	/*         a += '<input type="button" name="reCommentBtn" value="답글">'; */
	    a += '<button class="btn btn-default" type="button" onclick="AddRecomment('+f_rcpc_no+');">답글</button>';
	    a += '<button class="btn btn-default" type="button" onclick="commentList();">취소</button>';
	    a += '</form>';
	    a += '</div>';
	    
	    $('.insertRe'+f_rcpc_no).html(a);
}//commentReply(f_rcpc_no,f_rcpc_rcp_num,f_rcpc_ref,f_rcpc_lev,f_rcpc_seq)


//답글 등록하기 
function AddRecomment(f_rcpc_no){

    var reInsertData = $('[name=reCommentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴

    
    // 해당 게시글의 답글 데이터를 보내야 한다.
    $.ajax({
        url : './AddReComment.co',
        type : 'post',
        data : reInsertData,
        success : function(data){
        	alert('답글 업로드 완료!');
            /* if(data == 1) commentList(f_rcpc_no);  *///댓글 수정후 목록 출력
            commentList();
            /* $('[name=f_rcpc_content]').val(''); *///댓글 작성한 곳의 내용 비우기
            //답글 리스트 reload 
        }
    });
}//AddRecomment(f_rcpc_no)



//답글 보기 클릭시 해당되는 답글 리스트 뽑기 (같은 그룹에 있는 댓글만 가져오여 함)
function showReList(f_rcpc_ref){
	
	 $.getJSON('./CommentReList.co?f_rcpc_ref='+f_rcpc_ref,function(data){
/*         $.getJSON('./CommentList.co?num=${goods.num }',function(data)*/
	 
 		 alert('같은 그룹내 대댓글 가져옴!'); 

 		var a =''; 
     	
 		$.each(data, function(key,v){ 
     		
 		     			
 			a += '<div class="reCommentTit'+v.f_rcpc_ref+'">'+'&emsp;<b class="info_name_m">'+v.f_rcpc_mem_nick +'</b>'+v.f_rcpc_date;
 			a += '<div class="commentContent2">'+v.f_rcpc_content+'</div>';
 			
 			
         	$('.list'+f_rcpc_ref).html(a);
         	 
     			}); 
			$(".reCommentList"+f_rcpc_ref).html('답글 숨기기');  
			/*   $(this).off(event);   */			
     		}); 

  	   $(".reCommentList"+f_rcpc_ref ).click(function(){
		
		 $('.list'+f_rcpc_ref).html('');
		 $(".reCommentList"+f_rcpc_ref).html('답글 더보기');
		 $(this).off(event); 
  	});   
}//showReList(f_rcpc_ref)
</script>
</div>


					<!--(팀프로젝트 쇼핑몰 리스트) 여기서부터 본문 끝입니다. -->

				</div>
			</div>
			<!-- //content -->
		</section>
	</div>
<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(끝) -->
	<script type="text/javascript">
		$(function() {
			$("#sorting_ev").change(function() {
				loadWinnerList(1);
			});

			var mode = "";
			if (mode != "") {
				getListData(mode);
			} else {
				loadEventLi
				st();
			}
		});

		// 탭 이동
		function getListData(type) {
			if (type == "EVENT") {
				if (!$(".tabs > li:first").hasClass("on")) {
					$(".tabs > li:first").addClass("on");
					$(".tabs > li:last").removeClass("on");
					$(".tabs > li:first").find("span").append(
							'<em class="hide">선택됨</em>');
					$(".tabs > li:last").find("span > em").remove();
					$(".event_list").show(); // 이벤트 목록 보이기 처리
					$(".img_left_wrap").show(); // 이벤트 목록 슬라이드 보이기 처리
					$(".event_winner").hide(); // 당첨자 발표 목록 숨김 처리
				}
				loadEventList(); // 이벤트 목록 조회
			} else {
				if (!$(".tabs > li:last").hasClass("on")) {
					$(".tabs > li:last").addClass("on");
					$(".tabs > li:first").removeClass("on");
					$(".tabs > li:last").find("span").append(
							'<em class="hide">선택됨</em>');
					$(".tabs > li:first").find("span > em").remove();
					$(".event_list").hide(); // 이벤트 목록 숨김 처리
					$(".img_left_wrap").hide(); // 이벤트 목록 슬라이드 숨김 처리
					$(".event_winner").show(); // 당첨자 발표 목록 보이기 처리			
				}
				loadWinnerList(); // 당첨자 발표 목록 조회
			}
		}

		// 이벤트 목록 데이터를 구성함.
		var isBlock = false;
		function loadEventList(page) {
			// block 처리
			if (isBlock) {
				return;
			}
			isBlock = true;

			if (cj.isEmpty(page)) {
				page = 1;
			} else {
				$(window).scrollTop($("#eventListArea").offset().top - 50);
			}

			// 전송
			$.ajax({
				async : true,
				url : '/pc/event/eventList.json',
				dataType : 'json',
				type : 'POST',
				data : {
					page : page
				},
				success : function(data, textStatus, jqXHR) {
					if (!crew.ajaxValidate(data)) {
						return;
					}

					// html 적용

					$("#eventListArea").html(data.html);

					$("#eventPaging").html(data.paging);

					// block 해제
					isBlock = false;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					isBlock = false;
					//console.log(jqXHR.status);
				}
			});
		}

		// 당첨자 목록 데이터를 구성함.
		var isBlock2 = false;
		function loadWinnerList(page) {
			// block 처리
			if (isBlock) {
				return;
			}
			isBlock2 = true;

			// 내가 참여한 이벤트 여부 확인
			var mbrSrcYn = "";
			if ($("#sorting_ev").prop("checked")) {
				var isLogin = false;
				if (!isLogin) {
					if (confirm("로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?")) {
						cj.login();
					} else {
						$("#sorting_ev").prop("checked", false);
						return;
					}
				} else {
					mbrSrcYn = "Y";
				}
			} else {
				mbrSrcYn = "";
			}

			if (cj.isEmpty(page)) {
				page = 1;
			} else {
				$(window).scrollTop(
						$("#winnerListArea > div:first").offset().top);
			}

			// 전송
			$.ajax({
				async : true,
				url : '/pc/event/winnerList.json',
				dataType : 'json',
				type : 'POST',
				data : {
					page : page,
					mbrSrcYn : mbrSrcYn
				},
				success : function(data, textStatus, jqXHR) {
					if (!crew.ajaxValidate(data)) {
						return;
					}

					// html 적용
					$("#winnerListArea").find(".tb_row").remove();
					$("#winnerListArea").find(".no_data").remove();

					$("#winnerListArea > div:first").after(data.html);

					$("#winnerPaging").html(data.paging);

					// block 해제
					isBlock2 = false;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					isBlock2 = false;
					//console.log(jqXHR.status);
				}
			});
		}
	</script>

<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(끝) -->
	<!-- //content -->
	</section>

	<!-- 챗봇 레이어 팝업 -->
<!-- <article class="lay_chatbot_wrap_ok">
    <div class="lay_chatbot_frame">
        <section class="chatbot_conts">
          <iframe src="about:blank" id="iframeChatbot" allow="geolocation"></iframe>
        </section>
        <div class="close">
            <button type="button" class="btn_chatbot_close"><span class="hide">닫기</span></button>
        </div>
    </div>
    레이어 팝업
    <article class="lay_wrap_chatbot modal_chatbot" style="display:none;">
        <h1 class="hide"><span class="hide">앱업데이트안내(필수)</span></h1>
        <section class="lay_conts">
            190419_구조 수정
            <div class="laypop_cont">
                <p>챗봇 창을 닫으시면, <br>기존 채팅 내용이 초기화됩니다.<br>챗봇 창을 닫으시겠습니까?</p>
            </div>
            //190419_구조 수정
            <div class="btn_wrap laypop">
                <button type="button" class="btn_lay_chatbot ui_closeCancel">취소</button>
                <button type="button" class="btn_lay_chatbot green ui_closeConfirm">확인</button>
            </div>
        </section>
    </article>
    <div class="dimmed" style="display:none;"></div>
</article> -->
<script type="text/javascript">
    /*!
        Chatbot Layer, open/close 
    */
   $(document).ready(function(){
        var $floatingWrap = $('.fix_contsbtm');
        var $chatbotWrap = $('.lay_chatbot_wrap_ok');
        var $chatbotFrame = $chatbotWrap.find('.lay_chatbot_frame');
        
        $floatingWrap.on( 'click', 'button.chatbot', function() {
        	console.log("1");
            openChatbot( true ); 
        }); 

        $chatbotWrap.on( 'click', 'button.btn_chatbot_close', function() {
            openModalAlert( true ); 
        }); 

        $chatbotWrap.on( 'click', 'button.ui_closeCancel', function() {
            openModalAlert( false ); 
        }); 

        $chatbotWrap.on( 'click', 'button.ui_closeConfirm', function() {
            openModalAlert( false ); 
            openChatbot( false ); 
        }); 

        function openModalAlert( open ) { 
            var state = open ? 'show' : 'hide'; 
            $chatbotWrap.find('.dimmed')[state](); 
            $chatbotWrap.find('.modal_chatbot')[state](); 
        } 

        function openChatbot( open ) { 
            if( open ) {
                $chatbotWrap.show(); 
                $chatbotFrame.animate( {top:0}, 460, 'easeOutCubic' ); 

            } else {
                $chatbotFrame.animate( {top:640}, 400, 'easeInCubic', function() {
                    $chatbotWrap.hide(); 
                });
            }
        }
    });
</script>
<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(끝) -->

<!-- Footer -->
<jsp:include page="../inc/footer.jsp"/>
<!-- //Footer -->

<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(시작) -->

	<!-- <div class="fix_contsbtm">
		스크립트요청사항 : 바닥컨텐츠가 .btm 클래스 추가해주세요
		<div class="fx_button chatbot ui_chatBot show">
			<button type="button" class="chatbot"
				onclick="javascript:cj.chatbot.open('http://cookit.oli-va.com/');">
				<span class="hide">챗봇으로 문의하기</span>
			</button>
		</div>
		<div class="fx_button top ui_gotoTop show">
			<a href="#wrap" role="button"> <span class="hide">페이지 상단으로</span>
			</a>
		</div>
	</div> -->

	<script>
		function toggleUiFamilyArea(obj) {
			var _this = $(obj);
			_this.closest('.ui_familySite').toggleClass('on');
			_this.closest('.ui_familySite').find('.ly_manage').toggleClass(
					'show');
		}
	</script>

	</div>

	<div class="modal-container"></div>


	<script>
		$(document).ready(function(evt) {
			//카카오 JavaScript 키 설정
			Kakao.init('44db3bbd3ed2c23cfd950c7ac71aee25');
		});
	</script>
	<script type="text/javascript"
		src="../../cjkit/web/js/vcui17e0.js?20200924090026"></script>
	<script type="text/javascript"
		src="../../cjkit/web/js/olivekitchen.site17e0.js?20200924090026"></script>

	<script type="text/javascript">
		//vcui.require.config( {baseUrl:'http://publish.cjcookit.com/cjkit/mobile/js'} );
		vcui.require.config({
			baseUrl : '//www.cjcookit.com/cjkit/web/js'
		});
		//olivekitchen._.addon.carousel = false;
	</script>

	<script type="text/javascript" src="../js/jquery/jquery.lazy.min.js"></script>



	<script type="text/javascript">
		function setIframe(target, link, description) {
			var demo_Iframe = document.createElement("iframe");
			demo_Iframe.src = "/pc/footer/cookitInsta?mid1=" + target;
			demo_Iframe.id = "demo_Iframe";
			demo_Iframe.width = "0";
			demo_Iframe.height = "0";
			document.getElementsByTagName('body')[0].appendChild(demo_Iframe);
			if (!cj.isEmpty(link))
				cj.go.outLinkOpen(link, 'Y');

		}
	</script>

	<script type="text/javascript">
		var params = {};//파라미터 없음
		$.ajax({
			url : '/pc/myFpn/fpnViewMenu',
			dataType : 'json',
			type : 'POST',
			data : params,
			success : function(data, textStatus, jqXHR) {
				if (data.fpnData != null) {
					$('#fpnMenuView').show();
				} else {
					$('#fpnMenuView').hide();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
			}
		});
	</script>


	<script type="text/javascript" src="../js/makePCookie.js"></script>

	<!-- 쿡킷 GA 스크립트 -->
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="http://www.googletagmanager.com/gtag/js?id=UA-142134398-1"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());
		gtag('config', 'UA-142134398-1');
	</script>

	<!-- e:쿡킷 GA 스크립트 -->


	<!-- ADN M바로가기 리마케팅 start -->

	<script type="text/javascript">
		var adn_quicklink_param = adn_quicklink_param || [];
		adn_quicklink_param.push([ {
			ui : '102326',
			ci : '1023260001',
			gi : '28018'
		} ]);
	</script>
	<script type="text/javascript"
		src="../../../fin.rainbownine.net/js/adn_shortcut_remarketing_1.0.1.js"></script>

	<!-- ADN M바로가기 리마케팅 설치 end -->

	<!-- ADN Tracker[공통] start -->


	<script type="text/javascript">
		var adn_param = adn_param || [];
		adn_param.push([ {
			ui : '101740',
			ut : 'Home'
		} ]);
	</script>
	<script type="text/javascript" defer=""
		src="../../../fin.rainbownine.net/js/adn_tags_2.1.3.js"></script>

	<!-- ADN Tracker[공통] end -->


	<!-- FACEBOOK WEB SDK 실적 공통 적용  -->


	<script>
		!function(f, b, e, v, n, t, s) {
			if (f.fbq)
				return;
			n = f.fbq = function() {
				n.callMethod ? n.callMethod.apply(n, arguments) : n.queue
						.push(arguments)
			};
			if (!f._fbq)
				f._fbq = n;
			n.push = n;
			n.loaded = !0;
			n.version = '2.0';
			n.queue = [];
			t = b.createElement(e);
			t.async = !0;
			t.src = v;
			s = b.getElementsByTagName(e)[0];
			s.parentNode.insertBefore(t, s)
		}(window, document, 'script',
				'../../../connect.facebook.net/en_US/fbevents.js');
		fbq('init', '429975664402063');
		fbq('track', 'PageView');
	</script>


	<!-- 모비온 Enliple Tracker Start -->


	<script type="text/javascript">
		(function(a, g, e, n, t) {
			a.enp = a.enp || function() {
				(a.enp.q = a.enp.q || []).push(arguments)
			};
			n = g.createElement(e);
			n.defer = !0;
			n.src = "../../../cdn.megadata.co.kr/dist/prod/enp_tracker_self_hosted.min.js";
			t = g.getElementsByTagName(e)[0];
			t.parentNode.insertBefore(n, t)
		})(window, document, "script");
		enp('create', 'common', 'cjcookit', {
			device : 'B'
		});
		enp('send', 'common', 'cjcookit');
	</script>
	<!-- 모비온 Enliple Tracker End -->


	<!-- 네이버 모바일 - 공통 적용 스크립트 , 모든 페이지에 노출되도록 설치. 단 전환페이지 설정값보다 항상 하단에 위치해야함 -->


	<script type="text/javascript" src="../../../wcs.naver.net/wcslog.js">
		
	</script>
	<script type="text/javascript">
		if (!wcs_add)
			var wcs_add = {};
		wcs_add["wa"] = "s_15f84e946d7d";
		if (!_nasa)
			var _nasa = {};
		wcs.inflow();
		wcs_do(_nasa);
	</script>

	<!-- Global site tag (gtag.js) - Google Ads: 738287366 -->
	<script async
		src="http://www.googletagmanager.com/gtag/js?id=AW-738287366"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());
		gtag('config', 'AW-738287366');
	</script>

	<!-- WIDERPLANET  SCRIPT START 2020.7.7 -->


	<div id="wp_tg_cts" style="display: none;"></div>
	<script type="text/javascript">
		var wptg_tagscript_vars = wptg_tagscript_vars || [];
		wptg_tagscript_vars.push((function() {
			return {
				wp_hcuid : "", /*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입.
				 *주의 : 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
				ti : "49612", /*광고주 코드 */
				ty : "Home", /*트래킹태그 타입 */
				device : "web" /*디바이스 종류  (web 또는  mobile)*/

			};
		}));
	</script>
	<script type="text/javascript" async
		src="../../../cdn-aitg.widerplanet.com/js/wp_astg_4.0.js"></script>

	<!-- // WIDERPLANET  SCRIPT END 2020.7.7 -->

	<!-- 팀프로젝트와 관련 없는  코드입니다. 건드리지 말것....(끝) -->
	<!-- 팀프로젝트와 관련된 JS파일 넣는 경로 입니다. (시작)  -->
	
	
	
	
	
	
	
	
	<!-- 팀프로젝트와 관련된 JS파일 넣는 경로 입니다. (끝)  -->

</body>


</html>