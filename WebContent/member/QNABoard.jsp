<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<meta name="description" content="">
<meta name="keywords" content="">


<title>라따뚜이</title>
<link rel="shortcut icon"
	href="//www.cjcookit.com/cjkit/web/images/favicon.ico">

<link rel="stylesheet" type="text/css"
	href="//www.cjcookit.com/cjkit/web/css/common.css?20200924090027">
<link rel="stylesheet" type="text/css"
	href="//www.cjcookit.com/cjkit/web/css/chatbot.css?20200924090027">

<link rel="stylesheet" type="text/css"
	href="//www.cjcookit.com/cjkit/web/css/cs.css?20200924090027">
<link rel="stylesheet" type="text/css"
	href="//www.cjcookit.com/cjkit/web/css/my.css?20200924090027">

<script type="text/javascript"
	src="//www.cjcookit.com/cjkit/web/js/libs/jquery-1.11.2.min.js"></script>

<script>
	var cjConf = {
		serverImg : "//www.cjcookit.com/cjkit/web/images",
		loginYn : "Y"
	}
</script>

<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/jquery/jquery.form.js"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/crew/crew.js?20200924090027"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/crew/form/form.js?20200924090027"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/cj.js?20200924090027"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/oauth/kakao.min.js"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/oauth/naveridlogin_js_sdk_2.0.0.js"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/netfunnel.js?20200924090027"
	charset="utf-8"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/om_pc_skin.js?20200924090027"
	charset="utf-8"></script>
<script type="text/javascript"
	src="//www.cjcookit.com/pc/js/webtoolkit.base64.js"></script>

<meta name="google-site-verification"
	content="0GuzYD2oaBVARveIoOEQEEx10478h5Vnn5i5YNAS6zI" />

<script language="javascript">
	function loginPopup() {
		window
				.open(
						"./MemberLogin.me",
						"a",
						"width=483, height=422, left=900, right=0, top=500 resizable = no, scrollbars = no");
	}
	function SendMailPopup() {
		window
				.open(
						"./SendMail.me",
						"b",
						"width=600, height=450, left=900, right=0, top=500 resizable = no, scrollbars = no");
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


		<%
			String id = (String) session.getAttribute("f_mem_id");
		%>
		<!-- header 시작  -->
		<jsp:include page="../inc/header.jsp" />
		<!-- header 끝  -->
		<section id="container">
			<div id="container_title" class="hide" tabindex="-1">본문 시작</div>

			<!-- content -->


			<!-- content -->
			<div id="content" class="cs_content">
				<div class="cs_wrap">
					<h1 class="title">Q&A게시판</h1>

					<div id="tabView" class="ui_tab_panel" style="display: block;">
						<div class="faq_view">
							<h2 class="hide"></h2>
							<p class="result_txt" id="cntKeyWord"></p>
							<div class="list_acco ui_accordion" data-accord-group="faq_list"
								data-single-open="false" data-open-index="-1"
								ui-modules="Accordian">
								<ul class="faqListArea">
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "회원 아이디,비밀번호를 분실한 경우 어떻게 찾을 수 있나요?" <span
														class="cate"> <span class="hide">해당 질문 분류</span>
														"회원"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">로그인 페이지에서 아이디/비밀번호 찾기를 통해 확인 가능합니다.</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "포인트는 어떤 기준으로 지급되나요?" <span class="cate">
														<span class="hide">해당 질문 분류</span> "회원"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">실 결제 금액에서 0.2% 적립됩니다.</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "자택이 아닌 다른 곳으로도 배송이 가능한가요.(ex.회사,상가,학교
													등)" <span class="cate"> <span class="hide">해당
															질문 분류</span> "주문"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">
												새벽에 배송되어 새벽 출입이 제한되는 회사,관공서,기숙사,병원 등은 배송이 불가합니다.<br>
												되도록 자택으로 주문해주시길 바랍니다.
											</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "배송을 못 받았는데 [배송완료]로 확인되요." <span
														class="cate"> <span class="hide">해당 질문 분류</span>
														"주문"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">고객센터(1111-2222)로 연락주시길 바랍니다.</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "토요일과 일요일 그리고 공휴일에도 배송이 되나요?" <span
														class="cate"> <span class="hide">해당 질문 분류</span>
														"주문"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">주 5일(화/수/목/금/토)새벽 배송되며, 일부는 공휴일에도 배송될 수
												있습니다.</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "회원가입에 나이 제한이 있나요?" <span class="cate">
														<span class="hide">해당 질문 분류</span> "회원"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">전자상거래법에 의거하여 만14세 이상의 고객님만 서비스 이용이 가능합니다.
											</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "회원가입하면 어떤 혜택이 있나요?" <span class="cate">
														<span class="hide">해당 질문 분류</span> "회원"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">
												다양한 할인 쿠폰을 제공해드리고 있습니다.<br>
												<br> -첫 구매 무료배송 쿠폰<br> -첫 구매 감사 5,000원 할인쿠폰 <br>
											</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "주문한 상품의 일부만 취소할 수 있나요?" <span
														class="cate"> <span class="hide">해당 질문 분류</span>
														"취소/반품"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">부분취소는 주문마감전까지만 주문상세 페이지에서 [주문취소] 버튼을 눌러,
												취소를 원하는 상품만 선택하여 취소할 수 있습니다.</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "반품 신청은 어떻게 하나요?" <span class="cate">
														<span class="hide">해당 질문 분류</span> "취소/반품"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">
												반품은 제품의 하자 이외의 고객님의 단순 변심으로 인한 반품 신청은 불가합니다. <br> 배송된
												택배 또는 제품에 하자가 있는 경우,<br> 발견 즉시 사진을 찍어 고객행복센터로 사진을 첨부하여
												문의해 주시기 바랍니다. <br> 하자 사항에 대해 자세한 사진을 첨부해 주시면 보다 신속한 처리가
												가능합니다. <br>
											</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "주문한 메뉴를 변경할 수 있나요?" <span class="cate">
														<span class="hide">해당 질문 분류</span> "취소/반품"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">
												주문후에는 메뉴 변경이 불가합니다.<br> 주문 취소 후, 다시 주문해 주시기 바랍니다.
											</p>
										</div>
									</li>
									<li class="active">
										<div class="head btn-open">
											<a href="#qna answer248" class="link_cnt ui_accord_toggle"
												data-open-text="내용 더 보기 " data-close-text="내용닫기"
												data-seq="248" onclick="javascript:viewFaq(this,'248','Y');">
												<div class="tit">
													<strong>Q</strong> "반품했는데 환불은 언제되나요?" <span class="cate">
														<span class="hide">해당 질문 분류</span> "취소/반품"
													</span>
												</div> <span class="hide ui_accord_text">내용 더 보기</span>
											</a>
										</div>
										<div class="desc ui_accord_content" id="qna_answer248"
											style="display: none;">
											<strong>A</strong>
											<p class="cont">
												반품된 상품이 물류센터로 입고되어 검수가 완료되면 '반품완료' 처리됩니다.<br> 환불은 반품완료
												후 처리되며, 결제수단에 따라 소요 기간이 상이할 수 있습니다.<br>
											</p>
										</div>
									</li>
								</ul>
							</div>
						</div>


						<div class="pagination"></div>
					</div>

				</div>

				<!-- //content -->

				<style>
.highlight {
	color: #008A00;
	background-color: white;
	font-weight: bold;
}
</style>
				<script type="text/javascript"
					src="//www.cjcookit.com/pc/js/jquery/jquery.highlight.js"></script>
				<script type="text/javascript">
					// XSS방지
					var entityMap = {
						'&' : '&amp;',
						'<': '&lt;',
						'>' : '&gt;',
						'"' : '&quot;',
						"'" : '&#39;',
						'/' : '&#x2F;',
						'`' : '&#x60;',
						'=' : '&#x3D;'
					};
					function escapeHtml(string) {
						return String(string).replace(/[&<>"'`=\/]/g,
								function(s) {
									return entityMap[s];
								});
					}
					/* function goFaqListPage(obj, page, cslCtgrId, keyWord, mode, faqContSeq){
						$(window).scrollTop($(".faq_conts .f_type .tit").offset().top);
						loadFaqList(obj, page, cslCtgrId, keyWord, mode, faqContSeq);
					} */

					/* function loadFaqList(obj, page, cslCtgrId, keyWord, mode, faqContSeq) { // FAQ 목록 구성
						keyWord = ($("#fn_txt_srch").val() != "" ? $("#fn_txt_srch").val() : keyWord);
						// 전송
						$.ajax({
							async		: true,
							url			: '/QNABoard/QNABoardMain.html',
							dataType	: 'json',
							type		: 'POST',
							data		: { page : page, cslCtgrId : cslCtgrId, keyWord : keyWord, mode : mode},
							success		: function(data, textStatus, jqXHR){
								// 실패 메세지 처리
								if(!crew.ajaxValidate(data)){ return; }
								$("#cntKeyWord").addClass("hide");
								$("ul.faqListArea").empty().append(data.html);
								if(keyWord != ""){
									$("#cntKeyWord").empty().append("<em>"+ escapeHtml(keyWord) +"</em> 검색결과 총 "+data.totCnt+"개");
									$("#cntKeyWord").removeClass("hide");
									setTimeout(function(){
						        		$(".ui_accord_toggle").find(".tit").highlight();
						        		$(".ui_accord_toggle").find(".tit").trigger("search.highlight", keyWord);        			
						    		}, 100);
								}
								if(data.totCnt > 0 )
									$("div.pagination").empty().html(data.paging); // 페이지네이션 세팅	
								else 
									$("div.pagination").empty();
							},
							error		: function(jqXHR, textStatus, errorThrown){
								console.log(jqXHR.status);
							}
						});
					} */
					function viewFaq(obj, seq, hitUpdateYn) { // FAQ 상세보기 데이터 조회
						var area = $(obj).closest("li");
						// 전송
						$.ajax({
							async : true,
							url : '/pc/cs/faqView.json',
							dataType : 'json',
							type : 'POST',
							data : {
								faqContSeq : seq,
								hitUpdateYn : hitUpdateYn
							},
							success : function(data, textStatus, jqXHR) {
								// 실패 메세지 처리				
								if (!crew.ajaxValidate(data)) {
									return;
								}
								$(area).find("div.ui_accord_content p.cont")
										.empty().append(data.data.faqTxt);
							},
							error : function(jqXHR, textStatus, errorThrown) {
								console.log(jqXHR.status);
							}
						});
					}

					/* function goSearch() { // 검색
						$("input[name=keyWord]").val($.trim($("input[name=keyWord]").val()));
						if ($("input[name=keyWord]").val() == "") {
							$("input[name=keyWord]").focus();
							alert("검색어를(을) 입력해 주세요.");
						 	return false;
						} else {
							$("form[name=searchForm]").attr("onsubmit", true);
							$("form[name=searchForm]").submit();
						}
					} */

					/* function onkeyDown(){
						if(event.keyCode == 13) goSearch();
					} */

					/* function goPage(ntcDivCd, ntcSeq) {
						var url = "";
						if (ntcDivCd == "FO") {
							url = "/pc/cs/noticeView?ntcSeq=" + ntcSeq;
						} else {
							url = "/pc/cs/winnerView?ntcSeq=" + ntcSeq;
						}
						location.href = url;
					} */

					/* $(document).ready(function(){
						처음 목록 구성
						var cslCtgrId = "";
						loadFaqList(null, 1, cslCtgrId, "", "", "0");
						
					}); */
				</script>

				<!-- //content -->
		</section>


		<!-- 챗봇 레이어 팝업 -->
		<!-- <article class="lay_chatbot_wrap_ok">
    <div class="lay_chatbot_frame">
        <section class="chatbot_conts">
          <iframe src="about:blank" id="iframeChatbot" allow="geolocation"></iframe>
        </section>
        <div class="close">
            <button type="button" class="ui_closeConfirm"><span class="hide">닫기</span></button>
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
 -->
		<!-- 챗봇 레이어 팝업 -->


		<!-- Footer -->
		<jsp:include page="../inc/footer.jsp"/>	
		<!-- //Footer -->



		<div class="fix_contsbtm">
			<!-- 스크립트요청사항 : 바닥컨텐츠가 .btm 클래스 추가해주세요 -->
			<div class="fx_button chatbot ui_chatBot show">
<!-- 				<button type="button" class="chatbot"
					onclick="javascript:cj.chatbot.open('//cookit.oli-va.com');">
					<span class="hide">챗봇으로 문의하기</span>
				</button> -->
			</div>
			<div class="fx_button top ui_gotoTop show">
				<a href="#wrap" role="button"> <span class="hide">페이지
						상단으로</span>
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
		src="//www.cjcookit.com/cjkit/web/js/vcui.js?20200924090027"></script>
	<script type="text/javascript"
		src="//www.cjcookit.com/cjkit/web/js/olivekitchen.site.js?20200924090027"></script>

	<script type="text/javascript">
		//vcui.require.config( {baseUrl:'http://publish.cjcookit.com/cjkit/mobile/js'} );
		vcui.require.config({
			baseUrl : '//www.cjcookit.com/cjkit/web/js'
		});
		//olivekitchen._.addon.carousel = false;
	</script>

	<script type="text/javascript"
		src="//www.cjcookit.com/pc/js/jquery/jquery.lazy.min.js"></script>



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


	<script type="text/javascript"
		src="//www.cjcookit.com/pc/js/makePCookie.js"></script>




	<!-- 쿡킷 GA 스크립트 -->
	<!-- Global site tag (gtag.js) - Google Analytics -->


	<script async
		src="//www.googletagmanager.com/gtag/js?id=UA-142134398-1"></script>
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
		src="//fin.rainbownine.net/js/adn_shortcut_remarketing_1.0.1.js"></script>

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
		src="//fin.rainbownine.net/js/adn_tags_2.1.3.js"></script>

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
				'https://connect.facebook.net/en_US/fbevents.js');
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
			n.src = "https://cdn.megadata.co.kr/dist/prod/enp_tracker_self_hosted.min.js";
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


	<script type="text/javascript" src="//wcs.naver.net/wcslog.js">
		
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
	<script async src="//www.googletagmanager.com/gtag/js?id=AW-738287366"></script>
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
				wp_hcuid : "3695a98827666df4b06217e2dae929f6", /*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입.
				 *주의 : 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
				ti : "49612", /*광고주 코드 */
				ty : "Home", /*트래킹태그 타입 */
				device : "web" /*디바이스 종류  (web 또는  mobile)*/

			};
		}));
	</script>
	<script type="text/javascript" async
		src="//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js"></script>

	<!-- // WIDERPLANET  SCRIPT END 2020.7.7 -->










</body>
</html>