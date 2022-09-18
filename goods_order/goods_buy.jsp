<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="com.basket.db.BasketBean"%>
<%@page import="com.member.db.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="com.goods.action.GoodsListAction"%>
<%@page import="java.util.List"%>
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
	
		<!-- 팀프로젝트와 관련 있는  코드입니다. ....(시작) -->
<script src="./js/shoppingJS/buy.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="./jquery-3.5.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	function setDisplay() {
		if ($('input:radio[id=no_payer]').is(':checked')) {
			$('#divId').hide();
		} else {
			$('#divId').show();
		}
	}
</script>

<link rel="stylesheet" type="text/css" href="./cjkit/web/css/shopCSS/goods_buy.css">
<!-- 팀프로젝트와 관련 있는  코드입니다.  ....(끝) -->


<script type="text/javascript">
	function checkIt(){
		var doc = document.fr;

		if(doc.f_or_rec_name.value == "") {
			alert("받는 사람의 성함을 입력하세요!");
			doc.f_or_rec_name.focus();
			return false;
		}
		
		if(doc.f_or_rec_phone.value == "") {
			alert("받는 사람의 연락처를 입력하세요!");
			doc.f_or_rec_phone.focus();
			return false;
		}
		
		
		if(doc.f_or_rec_postcode.value == "") {
			alert("받는 사람의 주소를 입력하세요!");
			doc.f_or_rec_postcode.focus();
			return false;
		}
		
		if(doc.f_or_trade_type[0].checked == false 
				&& doc.f_or_trade_type[1].checked == false 
				&& doc.f_or_trade_type[2].checked == false
				&& doc.f_or_trade_type[3].checked == false){
			
			alert('결제 정보를 선택해 주세요');
			doc.f_or_trade_type[0].focus();
			return false;
		} 

	}
</script>
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
					<h1 class="title" align="center">상품 주문 정보 입니다.</h1><br>
						
					<!--(팀프로젝트 쇼핑몰 리스트) 여기서부터 본문 시작입니다. -->
						<div class="goods_buy" >
						<h2> 주문 상세 내역 </h2><br>
						
						<%
						/* 	
							정보 저장
							request.setAttribute("basketList", basketList);
							request.setAttribute("goodsList", goodsList);
							request.setAttribute("memberInfo", memberInfo); 
						*/
							ArrayList basketList = (ArrayList) request.getAttribute("basketList");
							ArrayList goodsList = (ArrayList) request.getAttribute("goodsList");
							MemberBean memberInfo = (MemberBean) request.getAttribute("memberInfo");
						
						%>
						
						  <table border="" style="text-align: center;">
						  <tr style="background-color: #b3d9ff;">
							<td>이미지</td>
							<td>상품명</td>
							<td>구매수량</td>
							<td>가격</td>
						  </tr>
						  
						  <%
						  	int totalSum = 0;
						  	
						  	for(int i=0; i<basketList.size(); i++){
						  		BasketBean bk = (BasketBean) basketList.get(i);
						  		GoodsBean gb = (GoodsBean) goodsList.get(i);
						  		
						  		totalSum += (gb.getF_gs_price() * bk.getF_bas_amount());
						  %>
						    <tr>
						      <td>
						      	<img src="./upload/<%=gb.getF_gs_img().split(",")[0] %>"
						      		width="120" height="120">
						      </td>
						      <td><%=gb.getF_gs_name() %></td>
						      <td><%=bk.getF_bas_amount() %></td>
						      <td><%=gb.getF_gs_price() %></td>
						    </tr>
						    <%
						  	}
						    %>
						  </table>
						  	<br><h3><b> 총 가격 : <%=totalSum %> </b></h3><br>
						  
						  <hr><hr>
						  
						<form action="./OrderAdd.or" method="post" name="fr" onsubmit="return checkIt()">
						  <table border="1">
						    <tr>
						      <br><td colspan="2"><h2>주문자 정보</h2></td>
						    </tr>  
						    <tr>
						      <td>이름</td>
						      <td><%=memberInfo.getF_mem_name() %></td>
						    </tr>  
						    <tr>
						      <td>전화번호</td>
						      <td><%=memberInfo.getF_mem_phone() %></td>
						    </tr> 
						    <tr>
						      <td>주문자 우편번호</td>
						      <td><%=memberInfo.getF_mem_postcode() %></td>
						    </tr> 
						    <tr>
						      <td>주문자 주소</td>
						      <td><%=memberInfo.getF_mem_address() %></td>
						    </tr> 
						    <tr>
						      <td>주문자 상세주소1</td>
						      <td><%=memberInfo.getF_mem_detailAddress() %></td>
						    </tr> 
						    <tr>
						      <td>주문자 상세주소2</td>
						      <td><%=memberInfo.getF_mem_extraAddress() %></td>
						    </tr> 
						    <tr>
						      <td>이메일</td>
						      <td><%=memberInfo.getF_mem_email() %></td>
						    </tr>    
						    
						    <tr>
						      <td colspan="2"><h2>배송지 정보</h2></td>
						    </tr>  
						   
						    <tr>
						      <td>받는 사람</td>
						      <td><input type="text" name="f_or_rec_name" value="<%=memberInfo.getF_mem_name()%>"></td>
						    </tr>  
						    <tr>
						      <td>전화번호(휴대폰)</td>
						      <td><input type="text" class="f_or_rec" name="f_or_rec_phone"></td>
						    </tr>  
						    <tr>
						      <td>우편번호</td>
							  <td><input type="text" class="f_or_rec_postcode" id="f_or_rec_postcode" name="f_or_rec_postcode"  placeholder="우편번호" readonly="readonly">&nbsp;&nbsp;&nbsp;<input class="div01_inp_postcode" type="button" value="주소검색" onclick="daumPostcode()"></td>
	
						    </tr>  
						    <tr>
						      <td>배송지 주소</td>
						      <td><input type="text" class="f_or_rec" id="f_or_rec_address" name="f_or_rec_address"></td>
						    </tr>  
						    <tr>
						      <td>배송지 상세 주소</td>
						      <td><input type="text" class="f_or_rec" id="f_or_rec_detailAddress" name="f_or_rec_detailAddress"></td>
						    </tr>  
						    <tr>
						      <td>배송지 추가 주소</td>
						      <td><input type="text" class="f_or_rec" id="f_or_rec_extraAddress" name="f_or_rec_extraAddress"></td>
						    </tr>  
						    <tr>
						      <td>배송 메세지 작성</td>
						      <td><input type="text" class="f_or_rec" name="f_or_memo"></td>
						    </tr>  
						    <tr>
						      <td colspan="2"><h2>결제 정보</h2></td>
						    </tr>  
						    <tr>
						      <td colspan="2">
						        <input type="radio" name="f_or_trade_type" id="payer" onchange="setDisplay()" value="무통장 입금"> 무통장 입금 
						        <input type="radio" name="f_or_trade_type" id="no_payer" onchange="setDisplay()" value="신용카드"> 신용카드 
						        <input type="radio" name="f_or_trade_type" id="no_payer" onchange="setDisplay()" value="체크카드"> 체크카드
						        <input type="radio" name="f_or_trade_type" id="no_payer" onchange="setDisplay()" value="페이"> 페이      
						      
						    	<div id="divId" style="display:none;"> 
								  	<label> 입금자명(무통장 입금)</label>
								  	<b><input type="text" name="f_or_trade_payer"
						      	   value="<%=memberInfo.getF_mem_name() %>"></b>
								</div>      
						      </td>
						    </tr>
						
						    <tr>  
						      <td colspan="2">
						       &nbsp;&nbsp;&nbsp; <input type="submit" style="width: 100px; height: 45px; font-size: 18px; background-color: #e6e6ff;" value="주문하기">      
						        &nbsp;&nbsp;<input type="reset" style="width: 100px; height: 45px; font-size: 18px;" value="취소">      
						      </td>
						    </tr>  
						    
						  </table>
						</form> 
						


</div>

					<!--(팀프로젝트 쇼핑몰 리스트) 여기서부터 본문 끝입니다. -->
					
				</div>
			</div>
		</section>
      </div>
			<!-- //content -->

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
<article class="lay_chatbot_wrap_ok">
    <div class="lay_chatbot_frame">
        <section class="chatbot_conts">
          <iframe src="about:blank" id="iframeChatbot" allow="geolocation"></iframe>
        </section>
        <div class="close">
            <button type="button" class="btn_chatbot_close"><span class="hide">닫기</span></button>
        </div>
    </div>
    <!-- 레이어 팝업 -->
    <article class="lay_wrap_chatbot modal_chatbot" style="display:none;">
        <h1 class="hide"><span class="hide">앱업데이트안내(필수)</span></h1>
        <section class="lay_conts">
            <!-- 190419_구조 수정 -->
            <div class="laypop_cont">
                <p>챗봇 창을 닫으시면, <br>기존 채팅 내용이 초기화됩니다.<br>챗봇 창을 닫으시겠습니까?</p>
            </div>
            <!-- //190419_구조 수정 -->
            <div class="btn_wrap laypop">
                <button type="button" class="btn_lay_chatbot ui_closeCancel">취소</button>
                <button type="button" class="btn_lay_chatbot green ui_closeConfirm">확인</button>
            </div>
        </section>
    </article>
    <div class="dimmed" style="display:none;"></div>
</article>
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

	<div class="fix_contsbtm">
		<!-- 스크립트요청사항 : 바닥컨텐츠가 .btm 클래스 추가해주세요 -->
		<div class="fx_button chatbot ui_chatBot show">
<!-- 			<button type="button" class="chatbot"
				onclick="javascript:cj.chatbot.open('http://cookit.oli-va.com/');">
				<span class="hide">챗봇으로 문의하기</span>
			</button> -->
		</div>
		<div class="fx_button top ui_gotoTop show">
			<a href="#wrap" role="button"> <span class="hide">페이지 상단으로</span>
			</a>
		</div>
	</div>

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
<!--팀프로젝트 관련 자바스크립트 제어코드 구문 입니다.   -->
<script src="./js/shoppingJS/admin_goods.js" ></script>

<!--팀프로젝트 관련 자바스크립트 제어코드 구문 입니다.   -->
</body>


</html>