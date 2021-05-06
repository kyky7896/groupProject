/******************************************************************************
	작성자 : 김기석
	작성일 : 2019.01.03
	기능 : CJ 공통 스크립트 처리
	참고사항 :
******************************************************************************/
var cj = {
	// cj 공통 환경 변수들...
	config : {
		url : {
			main		: "/pc/main",
			login		: "/pc/auth/login",
			loginMobile	: "/pc/auth/formLoginMobile.json",
			loginSns	: "/pc/auth/formLoginSns",
			logout		: "/pc/auth/logout",
			order		: "/pc/order/order"
		}
	},
	
	// 중복클릭 방지 변수
	isBlock : {},

	// 뒤로가기 대응 히스토리 라이브러리
	// 뒤로가기 대응 히스토리 라이브러리
	hist : {
		// 뒤로가기 관련 설정 정보들...
		config : {
			skipValue : []
		},


		// 현재 요청 경로 정보를 반환함.
		getUrl : function(){
			var url = [];
			url.push("//");
			url.push(location.host);
			url.push(location.pathname);
			return url.join("");
		},

		// 검색조건을 기반으로 쿼리스트링 구성
		getSearch : function(search){
			var rv = [];

			for(var key in search){
				var val = search[key] + "";

				if(val != "" && !cj.hist.isSkipValue(val) && key != "scrollTop"){
					rv.push(key + "=" + val);
				}
			}

			// scrollTop 정보 처리
			rv.push("scrollTop=" + $(window).scrollTop());


			return rv.join("&");
		},

		// 스크롤 이동 처리
		moveScrollTopCnt : 0,
		moveScrollTop : function(top){
			cj.hist.moveScrollTopCnt++;

		    $(window).scrollTop(top);

		    //console.log("moveScrollTop");
		    //console.log($(window).scrollTop() + "," + chkScrollTop);

		    // 화면상의 이슈로 절대 해당 위치로 이동하지 못하는 경우 재귀 종료 처리
		    if(cj.hist.moveScrollTopCnt > 100){
		    	cj.hist.moveScrollTopCnt = 0;
		    	return;
		    }


		    if($(window).scrollTop() != top){
		    	$(window).scrollTop($(window).scrollTop() - 1);    // window scroll 이벤트가 초기에 잡히지 않는 현상이 발생해서 스크롤을 강제도 이동시킴.
		        setTimeout("cj.hist.moveScrollTop(" + top + ")", 80);
		    }
		},

		// 쿼리스트링에서 스킵할 문자열인지를 판단함.
		isSkipValue : function(val){
			for(var i=0, d ; d = cj.hist.config.skipValue[i] ; i++){
				if(val == d){ return true; }
			}

			return false;
		},


		// 쿼리스트링을 기반으로 검색 오브젝트를 생성해서 반환함.
		getSearchObj : function(){
			var rv = {};

			var arr = location.search.substring(1).split("&");
			for(var i=0, d ; d = arr[i] ; i++){
				var a = d.split("=");
				rv[a[0]] = a[1];
			}

			return rv;
		},

		// 히스토리 생성
		createHistory : function(search){
			var url = cj.hist.getUrl() + "?" + cj.hist.getSearch(search);
			search.scrollTop = $(window).scrollTop();
			history.pushState(search, url, url);
		},

		// 히스토리 교체 - scrollTop 정보를 설정하기 위함임.
		replaceHistory : function(search){
			var url = cj.hist.getUrl() + "?" + (search ? cj.hist.getSearch(search) : cj.hist.getSearch(cj.hist.getSearchObj()));
			history.replaceState(null, url, url);
		},

		// 히스토리 라이브러리 초기화. onpopstate이벤트 핸들러 처리
		init : function(fn, opt){
			// 옵션 설정
			if(opt){
				cj.hist.config = $.extend(true, cj.hist.config, opt)
			}

			// 이벤트 핸들 설정
			if(typeof(fn) == "function"){
				window.onpopstate = function(event){
					var search = event.state;

					if(search == null){
						search = cj.hist.getSearchObj();
					}

					fn(search);
				}
			}
		},

		// 파라미터에서 scrollTop값이 존재하면 해당 위치로 화면을 이동 시킴. -> $(document).ready에서 이벤트 적용해야 함.
		initScrollTop : function(){
			var search = cj.hist.getSearchObj();
			if(search.scrollTop){ cj.hist.moveScrollTop(search.scrollTop); }
		}
	},


	// 경로 이동 처리
	go : {
		// 메인페이지로 이동
		main : function(){
			location.href = cj.config.url.main;
		},

		// 검색 결과페이지 이동
		search : function(keyword){
			alert(keyword + " 검색 결과 이동 필요함");
		},

		// 일반링크 처리 - 뒤로가기 대응으로 현재 위치 정보를 저장하고 이동함.
		link : function(url){
			cj.hist.replaceHistory();
			location.href = url;
		},

		// 일반 window.open 처리
		open : function(url){
			window.open(url);
		},

		// 메뉴
		menu : function(){
			location.href = "../menu/menuList.html";
		},
		
		//외부 링크 타겟 설정
		outLinkOpen : function (url, blankYn) {
			if (cj.isEmpty(url)) { return false; }
			
			if (blankYn == "Y") {
				window.open(url, "_blank");
			} else {
				window.open(url);
			}
		}
	},

	// 로그인 이동 처리
	// nonMemberLoginYn : 로그인 페이지 호출 시 비회원 주문조회를 먼저 보여줄지 여부
	// wid1 : 와이즈로그 메인 파라미터
	login : function(redirectUrl, isConfirm, nonMemberLoginYn, wid1){
		// 확인 메세지 구성
		if(isConfirm){
			var isLoginProcess = false;
			if(confirm("로그인이 필요한 서비스입니다.\n로그인하시겠습니까?")){ isLoginProcess = true; }
			if(!isLoginProcess){ return; }
		}

		redirectUrl = redirectUrl || (location.pathname + location.search);

		location.href = cj.config.url.login + "?redirectUrl=" + encodeURIComponent(redirectUrl) + (nonMemberLoginYn?"&nonMemberLoginYn="+nonMemberLoginYn:'') + (wid1?'&wid1='+wid1:'');
	},

	// 휴대폰 간편 로그인 인터페이스 처리
	loginMobile : function(redirectUrl){
		// 기존 모달 닫기
		$(".ui_modal_close").trigger('click');

		// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
		$("#loginMobileModal").remove();

		redirectUrl = redirectUrl || (location.pathname + location.search);

		// 전송
		$.ajax({
			async		: true,
			url			: cj.config.url.loginMobile,
			dataType	: 'json',
			type		: 'POST',
			data		: { redirectUrl : redirectUrl },
			success		: function(data, textStatus, jqXHR){
				if(!crew.ajaxValidate(data)){ return; }

				$(".modal-container").append(data.html);
				vcui.require( ['ui/modal'], function() {
	            	$("#loginMobileModal").vcModal();
	        	});
			},
			error	   : function(jqXHR, textStatus, errorThrown){
			  //console.log(jqXHR.status);
			}
		});
	},

	// SNS 간편 로그인 CJ ONE 연결 인터페이스 처리
	loginSns : function(snsCd, accessToken, redirectUrl){
		redirectUrl = redirectUrl || (location.pathname + location.search);
		location.href = cj.config.url.loginSns+"?snsCd="+snsCd+"&accessToken="+encodeURIComponent(accessToken)+"&redirectUrl="+encodeURIComponent(redirectUrl);
	},

	// 마케팅 수신 동의 모달 인터페이스 처리
	/*
	mrktAgreeModal : function(){
		// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
		$("#mrktAgreeModal").remove();

		// 전송
		$.ajax({
			async		: true,
			url			: '/pc/auth/mrktAgreeModal.json',
			dataType	: 'json',
			type		: 'POST',
			data		: { },
			success		: function(data, textStatus, jqXHR){
				if(!crew.ajaxValidate(data)){ return; }

				$(".modal-container").append(data.html);
				vcui.require( ['ui/modal'], function() {
	            	$("#mrktAgreeModal").vcModal();
	        	});
			},
			error	   : function(jqXHR, textStatus, errorThrown){
			  //console.log(jqXHR.status);
			}
		});
	},
	*/

	// 로그인 요청후 처리 결과 공통 처리
	loginResultCheck : function(data, disabledItgAgrYn){
		//console.log(data);

		// 통합약관 미동의 회원 처리(마이그래이션 회원)
		// - 통합약관 미동의 상태의 참여사 휴면회원(resultCode:640)은 더마켓으로 이동하여 휴면 해제 하기 때문에 통합약관동의를 진행하지 않음
		// TODO 6/24 통합약관 동의 체크 주석처리 해야함
		if(disabledItgAgrYn != "Y" && data.itgPrvAgrYn == "N" && (data.resultCode == 1 || data.resultCode == 820)){
			// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
			$("#agreeModal").remove();
			// 전송
			$.ajax({
				async		: true,
				url			: '/pc/auth/agreeModal.json',
				dataType	: 'json',
				type		: 'POST',
				data		: { redirectUrl : encodeURIComponent(data.redirectUrl) },
				success		: function(modalData, textStatus, jqXHR){
					if(!crew.ajaxValidate(modalData)){ return; }

					$(".modal-container").append(modalData.html);
					vcui.require( ['ui/modal'], function() {
		            	$("#agreeModal").vcModal();
		        	});
					setLoginResultData(data);
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
			return;
		}

		// 기타 결과코드에 대한 처리
		if(data.resultCode != 1){
			if(data.resultCode == 500){			// 자동입력방지문자 체크 결과 처리
				$(".captchaAnswerValidate").html(data.message);
				$(".captchaAnswerValidate").show();
			}else if(data.resultCode == 510){	// CJ SSO 로그인 실패 처리
				// SSO 로그인은 메시지 처리나 페이지를 이동하지 않음
			}else if(data.resultCode == 520){	// 14세 미만 로그인 실패 처리
				alert(data.message);
			}else if(data.resultCode == 530){	// CJ World 로그인 실패 처리
				alert(data.message);
				cj.go.main();
			}else if(data.resultCode == 540){	// BO 로그인 실패 처리
				alert(data.message);
				cj.go.main();
			}else if(data.resultCode == 550){	// CJWORLD SSO 로그인 실패 처리
				alert(data.message);
				cj.go.main();
			}else if(data.resultCode == 600){	// 휴면회원(CJ 제일제당) 체크 결과 처리
				// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
				$("#dormancyInfoMarket").remove();
				// 전송
				$.ajax({
					async		: true,
					url			: '/pc/auth/dormancyInfoMarket.json',
					dataType	: 'json',
					type		: 'POST',
					data		: { redirectUrl : encodeURIComponent(data.redirectUrl) },
					success		: function(modalData, textStatus, jqXHR){
						if(!crew.ajaxValidate(modalData)){ return; }

						$(".modal-container").append(modalData.html);
						vcui.require( ['ui/modal'], function() {
			            	$("#dormancyInfoMarket").vcModal();
			        	});
					},
					error	   : function(jqXHR, textStatus, errorThrown){
					  //console.log(jqXHR.status);
					}
				});
			}else if(data.resultCode == 610){	// 휴면회원(CJ ONE) 체크 결과 처리
				// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
				$("#dormancyInfoOne").remove();
				// 전송
				$.ajax({
					async		: true,
					url			: '/pc/auth/dormancyInfoOne.json',
					dataType	: 'json',
					type		: 'POST',
					data		: { redirectUrl : encodeURIComponent(data.redirectUrl) },
					success		: function(modalData, textStatus, jqXHR){
						if(!crew.ajaxValidate(modalData)){ return; }

						$(".modal-container").append(modalData.html);
						vcui.require( ['ui/modal'], function() {
			            	$("#dormancyInfoOne").vcModal();
			        	});
					},
					error	   : function(jqXHR, textStatus, errorThrown){
					  //console.log(jqXHR.status);
					}
				});
			}else if(data.resultCode == 620){	// 잠금회원(CJ ONE) 체크 결과 처리
				// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
				$("#lockMemberModal").remove();
				// 전송
				$.ajax({
					async		: true,
					url			: '/pc/auth/lockMemberModal.json',
					dataType	: 'json',
					type		: 'POST',
					data		: { linkUrl : encodeURIComponent(data.linkUrl) },
					success		: function(modalData, textStatus, jqXHR){
						if(!crew.ajaxValidate(modalData)){ return; }

						$(".modal-container").append(modalData.html);
						vcui.require( ['ui/modal'], function() {
			            	$("#lockMemberModal").vcModal();
			        	});
					},
					error	   : function(jqXHR, textStatus, errorThrown){
					  //console.log(jqXHR.status);
					}
				});
				/*
				if(confirm(data.message)){
					alert("CJ ONE사이트로 이동합니다.\nCJ ONE ID로 로그인 후 이용해주세요");
					if(cjConf.appYn == "Y"){
						CJONS_PLUGIN.exec("OK_WEB.showWebPopUp", null, null, {
				            'url' : data.linkUrl
				        });
					}else{
						location.href = data.linkUrl;
					}
				}
				*/
			}else if(data.resultCode == 630){	// CJ ONE PM일시 ALERT 진행.
				alert("CJ ONE 사이트의 서비스 점검으로 휴면 해제가 불가능합니다.\nCJ ONE의 서비스 점검이 끝난 후 다시 시도해 주시기 바랍니다.");
				return;
			
			}else if(data.resultCode == 640){	// 통합약관 미동의 상태의 휴면회원(CJ 제일제당) 체크 결과 처리
				// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
				$("#noSvcDormancyModal").remove();
				// 전송
				$.ajax({
					async		: true,
					url			: '/pc/auth/noSvcDormancyModal.json',
					dataType	: 'json',
					type		: 'POST',
					data		: { },
					success		: function(modalData, textStatus, jqXHR){
						if(!crew.ajaxValidate(modalData)){ return; }

						$(".modal-container").append(modalData.html);
						vcui.require( ['ui/modal'], function() {
			            	$("#noSvcDormancyModal").vcModal();
			        	});
					},
					error	   : function(jqXHR, textStatus, errorThrown){
					  //console.log(jqXHR.status);
					}
				});
			}else if(data.resultCode == 800){	// 약관동의 필요회원 결과 처리
				location.href = "https://www.cjcookit.com/pc/auth/formAgree";
			}else if(data.resultCode == 810){	// SSO 로그인 약관동의 필요회원 결과 처리
				if(confirm(data.message)){
					location.href = "https://www.cjcookit.com/pc/auth/formAgree?ssoYn=Y";	//ssoYn이 Y이면 약관동의 취소 시 SSO 재시도를 하지 않도록 쿠키 저장
				}else{
					// 약관동의 취소 시 SSO를 다시 재시도를 하지않도록 쿠키 저장
					document.cookie = "ssoDisabledCookieKey=Y;path=/;SameSite=None; Secure";
				}
			}else if(data.resultCode == 820){	// 쿡킷(6130) 통합약관 미동의 회원 결과 처리 (마이그래이션 회원)
				if(data.mode == "SSO_AUTH"){
					// SSO를 통해 로그인한 경우 통합약관동의 취소 시 SSO를 다시 재시도를 하지않도록 쿠키 저장
					document.cookie = "ssoDisabledCookieKey=Y;path=/;SameSite=None; Secure";
				}
			}else if(data.resultCode == 900){	// SNS로그인 CJ ONE 연동 필요회원 결과 처리
				cj.loginSns(data.snsCd, data.accessToken, data.redirectUrl);
			}else{
				alert(data.message);
			}

			return;
		}

		// 로그인 성공후 추가 처리용 결과코드 획득
		var addResultCode = data.addResultCode;

		// 이동 처리
		if(addResultCode == 1){		// 정상로그인
			location.href = data.redirectUrl == "" ? cj.config.url.main : data.redirectUrl;
		}else if(addResultCode == 700){	//정기 비밀번호 변경
			// 기존에 생성된 모달은 삭제 처리함. - 데이터를 새로 갱신하므로 항상 새로 그린다.
			$("#formChangePwd").remove();
			// 전송
			$.ajax({
				async		: true,
				url			: '/pc/auth/formChangePwd.json',
				dataType	: 'json',
				type		: 'POST',
				data		: { redirectUrl : encodeURIComponent(data.redirectUrl) },
				success		: function(modalData, textStatus, jqXHR){
					if(!crew.ajaxValidate(modalData)){ return; }

					$(".modal-container").append(modalData.html);
					vcui.require( ['ui/modal'], function() {
		            	$("#formChangePwd").vcModal();
		        	});
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		}else if(addResultCode == 710){	//임시 비밀번호 변경
			alert(data.message);
			location.href = data.redirectUrl == "" ? cj.config.url.main : data.redirectUrl;
		}else if(addResultCode == 910){	//SNS 연동 결과 처리
			alert(data.message);
			location.href = data.redirectUrl == "" ? cj.config.url.main : data.redirectUrl;
		}else if(addResultCode == 630){	//휴면회원(CJ 제일제당) 해제 결과 처리
			alert(data.message);
			location.href = data.redirectUrl == "" ? cj.config.url.main : data.redirectUrl;
		}
		/* 기획 삭제됨
		else if(addResultCode == 720){	//마케팅 수신 미동의 회원
			vcui.require( ['ui/modal'], function() {
				cj.mrktAgreeModal();
        	});
		}
		*/
	},

	// 로그아웃 처리
	// openYn : 새창으로 열기 여부
	logout : function(redirectUrl){
		redirectUrl = redirectUrl == null ? "" : redirectUrl;

		// SSO 로그아웃 처리
		var $iframe = $("<iframe id='ssoLogoutIframe'></iframe>");
		$iframe.attr("width", "0");
		$iframe.attr("height", "0");
		$iframe.attr("scrolling", "no");
		$iframe.attr("src", cj.config.url.logout+"?redirectUrl="+encodeURIComponent(redirectUrl));
		$(".modal-container").append($iframe);

		alert("로그아웃 되었습니다.");
	},

	ssoLogoutAfterLoading : function(redirectUrl){
		location.href = redirectUrl == "" ? cj.config.url.main : redirectUrl;
	},

	// form 관련 기능들...
	form : {
		//숫자만 입력 : 숫자 이외 ('' 포함) => 0
		onlynum : function(event){
			var val		= $(this).val();
			var result	= "";

			for(var i=0, s ; s = val.substring(i, i+1) ; i++){
				if(!isNaN(s)){ result += s; }
			}

			$(this).val(parseInt(result || 0));
		},

		//숫자만 입력 (반환 값 숫자아님) :  숫자만 반환
		onlynum2 : function(event){
			var val		= $(this).val();
			var result	= [];

			for(var i=0, s ; s = val.substring(i, i+1) ; i++){
				if(!isNaN(s)){ result.push(s); }
			}

			$(this).val(result.join(''));
		},

		// input file의 파일크기정보를 반환함.
		filesize : function(obj){
			var browser=navigator.appName;

			if (browser=="Microsoft Internet Explorer"){
				var fso = new ActiveXObject("Scripting.FileSystemObject");
				return fso.getFile(obj.value).size;
			}else{
				return obj.files[0].size;
			}
		}
	},




	// 유효성 체크 기능들...
	validate : {
		// 패스워드 유효성 검증. 숫자+영문+특수문자 조합. 8~12자리
		// notInclude 값은 선택임. 포함되면 안되는 문자열(아이디)을 배열로 전달하면 됨.
		password : function(pwd, notInclude){
			// 입력값 검증
			if(typeof(pwd) != "string"){ return false; }


			/*
			// 숫자+영문+특수문자 조합
			var num = pwd.search(/[0-9]/g);
			var eng = pwd.search(/[a-z]/gi);
			var spe = pwd.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

			if(num == -1 || eng == -1 || spe == -1){ return 10; }

			*/

			// 8~12자리 - javascript에서는 정규표현식 길이 체크가 다르다.
			var len = pwd.length;
			if(len < 8 || len > 12){ return 20; }

			// 숫자+영문+특수문자+8~12글자
			if(pwd.search(/(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[`~!@@#$%^&*|₩₩₩'₩";:₩/?]).{1,}/) == -1){
				return 10;
			}


			// 연속문자 + 반목문자 체크(4글자)
			var alpaBig		= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			var alpaSmall	= "abcdefghijklmnopqrstuvwxyz";
			var numStr		= "0123456789";

			for(var i=0 ; i < pwd.length ; i++){
				var chkStr = pwd.substring(i, i+4);
				if(chkStr.length != 4){ break; }

				// 연속문자
				if(alpaBig.indexOf(chkStr) != -1 || alpaSmall.indexOf(chkStr) != -1 || numStr.indexOf(chkStr) != -1){
					return 30;
				}

				// 반복문자 - 비교대상반복문자를 구성해서 비교함.
				var repeatStr = [];
				for(var j=0 ; j < chkStr.length ; j++){
					repeatStr.push(chkStr.substring(0, 1));
				}
				if(repeatStr.join("") == chkStr){ return 40; }
			}



			// 패스워드 포함 불가 단어 - 아이디등.
			if(typeof(notInclude) == "object"){
				for(var i=0 ; i < notInclude.length ; i++){
					if(notInclude[i].length == 0){ continue; }
					//if(pwd.indexOf(notInclude[i]) != -1){ return 50; }

					//아이디와 4자리 이상 동일한 문자 또는 숫자 체크
					var a = notInclude[i];
					for(var j =0; j<=(a.length-4); j++){
						var b= a.substring(j, (j+4));
						if(pwd.indexOf(b) != -1){ return 50; }
					}
				}
			}

			return 0;
		}
	},

	// 배송지 관련 기능들...
	dlvd : {
		memberDlvdList : [],
		memberDlvdCallback : function(data){},
		callbackYn : "N",
		checkDawnYn : "Y",

		// 배송지 관리 인터페이스 활성화
		open : function(callback, checkDawnYn){

			if(typeof(callback) == "function"){
				cj.dlvd.memberDlvdCallback = callback;
				cj.dlvd.callbackYn = "Y";
			}else{
				cj.dlvd.memberDlvdCallback = function(data){};
				cj.dlvd.callbackYn = "N";
			}

			cj.dlvd.checkDawnYn = cj.isEmpty(checkDawnYn) ? false : checkDawnYn;
			vcui.util.openPopup('/pc/myOrder/dlvdInfo', 500, 762, {target : 'dlvdInfo', scrollbars: 'yes'});
		},

		// 배송지 목록 반환
		loadDlvdList : function (page) {
			if (cj.isEmpty(page)) { return false; }
			cj.dlvd.memberDlvdList = [];

			// 데이터 요청
			$.ajax({
				async		: true,
				url			: "/pc/myOrder/dlvdInfoList.json",
				dataType	: "json",
				type		: 'POST',
				data		: {mode : "change", page : page},
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					// 선택 처리를 위해서 목록 데이터 저장
					if (data.totalCount > 0) { cj.dlvd.memberDlvdList = cj.dlvd.memberDlvdList.concat(data.list); }

					$("#memberDlvdPopup").find(".dlvdInfoListArea").html(data.html);
					$("#memberDlvdPopup").find("#pageArea").html(data.paging);

					if ($("#memberDlvdPopup").find("#pageArea span.page_num").length > 0) {
						$("#memberDlvdPopup").find("#pageArea").show();
					} else {
						$("#memberDlvdPopup").find("#pageArea").hide();
					}

				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		},

		// 배송지 수정 인터페이스 구성
		memberDlvdModify : function(seq){
			if (cj.isEmpty(seq)) { return false; }

			vcui.util.openPopup('/pc/myOrder/dlvdModifyForm?mbrDlvdSeq=' + seq, 500, 626, {target : 'dlvdModifyForm', scrollbars: 'yes'});
		},

		// 배송지 등록 인터페이스 구성
		memberDlvdCreate : function(regYn, callback, type) {
			var url = "/pc/myOrder/dlvdModifyForm";

			if (regYn == "N") {
				url = "/pc/myOrder/dlvdNoneMemberModifyForm";
				if(typeof(callback) == "function"){
					cj.dlvd.memberDlvdCallback = callback;
				}else{
					cj.dlvd.memberDlvdCallback = function(data){};
				}
			}
			if(cj.isEmpty(type)){
				vcui.util.openPopup(url + '?mode=create&keyWord='+regYn, 500, 626, {target : 'dlvdCreateForm', scrollbars: 'yes'});
			}else{
				vcui.util.openPopup(url + '?mode=' + type + '&keyWord='+regYn, 500, 626, {target : 'dlvdCreateForm', scrollbars: 'yes'});
			}
		},

		// 배송지 정보 반환
		getMemberDlvd : function (seq) {
			for(var i=0, d ; d = cj.dlvd.memberDlvdList[i] ; i++) {
				if(seq == d.mbrDlvdSeq){
					return d;
				}
			}
			return null;
		},

		// 항목 선택 처리
		memberDlvdSelect : function(seq) {
			if (cj.isEmpty(seq)) { return false; }

			$(".preloader").show();
			var o = this.getMemberDlvd(seq);
			if (o == null) {
				return false;
			} else {

				if(cj.isEmpty(o.dlvNtcCd)){
					o.dlvNtcCd = "9";
					o.dlvNtcCdNm = "미전송";
				}
				
				if (cj.isEmpty(o.dlvdZipCd) || cj.isEmpty(o.dlvdAdr)) {
					//skip
					cj.dlvd.closeAndCallback(o);
				}else{
					//새벽배송 체크
					setTimeout(function(){	// ajax async false인 경우 화면이 잠기면서 preloader가 정상 표시 되지않아 지연처리
						$.ajax({
					        url: '/pc/order/getArpYn.json',
					        method: "post",
					        data: {recvZipCode : o.dlvdZipCd, recvAddr : o.dlvdAdr},
					        async: false,
					        dataType : 'json',
					        success : function(data, textStatus, jqXHR) {
					        	//console.log("## data.isDawnAble : "+data.isDawnAble);
					        	if(!data.isDawnAble){
					        		alert("새벽배송이 불가능합니다. 다른 배송지를 선택해주세요.");
					        		return false;
					        	}
					        	
					        },
					        error : function(jqXHR, textStatus, errorThrown) {
					            alert('고객님 죄송합니다.\n오류가 발생하였습니다.\n고객센터(1668-1953) 혹은 1:1 게시판에 문의주시기 바랍니다.');
					            return false;
					        },
					        complete : function() {
					        	//$(opener.document).find(".preloader").hide();
					        	$(".preloader").hide();
					        	cj.dlvd.closeAndCallback(o);
					        }
					    });
					}, 1);
				}
			}
		},

		// 배송지 등록 처리
		memberDlvdSave : function(odrrMbrYn){
			// block 처리
			if (cj.isBlock['memberDlvdSave']) { return; }
			
			var typeTxt = "추가";
			if(!cj.isEmpty(odrrMbrYn)){
				typeTxt = '수정';
			}
			var regYn = $("#regYn").val();
			var basicDlvdYn = $("#memberDlvdModifyPopup").find("#basicYn").prop("checked") ? "Y" : "N"; // 기본배송지 등록 여부
			$("#memberDlvdModifyPopup").find("input[name=basicDlvdYn]").val(basicDlvdYn);

			$("#memberDlvdModifyPopup").find("textarea").each(function(i){
				$(this).val($.trim($(this).val()));

				$(this).siblings("div").find("span.ui_inputLimitCount").html($(this).val().length);
			})

			$("#memberDlvdModifyPopup").find("input").each(function(i){
				$(this).val($.trim($(this).val()));
			})

			var form = $("form[name=memberDlvdForm]");
			if(!form.validateForm()){ return; }
			var o = form.serializeObject();
			if(!this.validate(o)) { return; };

			if (!confirm("배송지를 " + typeTxt + "하시겠습니까?")) {
				return false;
			} else {
				if (regYn == "N") {
					cj.dlvd.closeAndCallback(o);
				} else {					
					cj.isBlock['memberDlvdSave'] = true;
					
					// 전송
					$.ajax({
						async		: true,
						url			: '/pc/myOrder/dlvdSave.json',
						dataType	: 'json',
						type		: 'POST',
						data		: o,
						success		: function(data, textStatus, jqXHR){
							if(!crew.ajaxValidate(data)){ return; }

							alert("배송지가 " + typeTxt + "되었습니다.");

							if (cj.dlvd.callbackYn == "Y") {
								if (opener) { opener.cj.dlvd.loadDlvdList(1); }
							} else {
								opener.window.location.reload();
							}

							window.close();

						},
						error	   : function(jqXHR, textStatus, errorThrown){
							//console.log(jqXHR.status);
						},
						complete : function() {
							cj.isBlock['memberDlvdSave'] = false;
				        }
					});
				}

			}
		},

		// 배송지 수정 처리
		memberDlvdUpdate : function(){
			// block 처리
			if (cj.isBlock['memberDlvdUpdate']) { return; }
			
			var basicDlvdYn = $("#memberDlvdModifyPopup").find("#basicYn").prop("checked") ? "Y" : "N"; // 기본배송지 등록 여부
			$("#memberDlvdModifyPopup").find("input[name=basicDlvdYn]").val(basicDlvdYn);

			$("#memberDlvdModifyPopup").find("textarea").each(function(i){
				$(this).val($.trim($(this).val()));

				$(this).siblings("div").find("span.ui_inputLimitCount").html($(this).val().length);
			})

			$("#memberDlvdModifyPopup").find("input").each(function(i){
				$(this).val($.trim($(this).val()));
			})

			var form = $("form[name=memberDlvdForm]");
			if(!form.validateForm()){ return; }
			var data = form.serializeObject();
			if(!this.validate(data)) { return; };
			
			cj.isBlock['memberDlvdUpdate'] = true;
			
			// 전송
			$.ajax({
				async		: true,
				url			: '/pc/myOrder/dlvdUpdate.json',
				dataType	: 'json',
				type		: 'POST',
				data		: data,
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					alert("배송지 정보가 수정되었습니다.");

					if (cj.dlvd.callbackYn == "Y") {
						if (opener) { opener.cj.dlvd.loadDlvdList(1); }
					} else {
						opener.window.location.reload();
					}

					window.close();

				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				},
				complete : function() {
					cj.isBlock['memberDlvdUpdate'] = false;
		        }
			});

		},

		// 배송지 삭제 처리
		memberDlvdRemove : function(seq){
			// block 처리
			if (cj.isBlock['memberDlvdRemove']) { return; }
			
			if (cj.isEmpty(seq)) { return false; }

			if (!confirm("해당 배송지를 삭제하시겠습니까?")) {
				return false;
			} else {				
				cj.isBlock['memberDlvdRemove'] = true;
				
				// 전송
				$.ajax({
					async		: true,
					url			: '/pc/myOrder/dlvdRemove.json',
					dataType	: 'json',
					type		: 'POST',
					data		: { mbrDlvdSeq : seq },
					success		: function(data, textStatus, jqXHR){
						if(!crew.ajaxValidate(data)){ return; }

						alert("배송지가 삭제되었습니다.");

						window.location.reload();
					},
					error	   : function(jqXHR, textStatus, errorThrown){
						//console.log(jqXHR.status);
					},
					complete : function() {
						cj.isBlock['memberDlvdRemove'] = false;
			        }
				});

			}
		},

		// 기본 배송지 설정
		basicDlvdUpdate : function (mbrDlvdSeq) {			
			// block 처리
			if (cj.isBlock['basicDlvdUpdate']) { return; }
			
			if (cj.isEmpty(mbrDlvdSeq)) { return false; }
			
			cj.isBlock['basicDlvdUpdate'] = true;
			
			$.ajax({
				async		: true,
				url			: '/pc/myOrder/basicDlvdUpdate.json',
				dataType	: 'json',
				type		: 'POST',
				data		: {mbrDlvdSeq : mbrDlvdSeq, basicDlvdYn : 'Y'},
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					alert("기본배송지가 변경되었습니다.");
					window.location.reload();
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				},
				complete : function() {
					cj.isBlock['basicDlvdUpdate'] = false;
		        }
			})
		},

		changeDoorEnt : function(obj) {
			var x = cj.isEmpty($(obj).val()) ? '99' : $(obj).val();

			var $origin = $("form[name=memberDlvdForm]").find(".pubDoorEntWayInfo");

			if ($origin.length > 0) {
				var $clone = $origin.clone();
				$("#wayInfoArea").append($clone);
				$("form[name=memberDlvdForm]").find(".pubDoorEntWayInfo").remove();
			}

			var $new = $("#wayInfoArea .pubDoorEntWayInfo[data-id=" + x + "]").clone();

			// textarea의 placeholder와 val이 같으면 val 제거 (IE에서의 문제)
			var $newTextarea = $new.find("textarea");
			if($newTextarea.attr("placeholder") == $newTextarea.val()){
				if(x == 2 || x == 9) {
					$new.find("textarea").val("");
				}
			}

			$("span.pubDoorEntWaySpan").after($new);
			vcui.form.InputLimitCount( $('#question_cont2, #question_cont6'));

			$('#question_cont2, #question_cont6').each(function(){
				var len = $(this).val().length;
				var id = $(this).data("count-target");
				$(id).find("span.ui_inputLimitCount").html(len);
			})

			$("#wayInfoArea .pubDoorEntWayInfo[data-id=" + x + "]").remove();

			if(x == "2") {
				$(".alam_info").show();
			} else {
				$(".alam_info").hide();
			}

			return true;
		},

		// 화면 close, callback
		closeAndCallback : function(o) {

			if (cj.dlvd.checkDawnYn == "Y") { // 새벽배송 체크 여부
				cj.addr.data.zipCd = o.dlvdZipCd;
				cj.addr.data.mnAdr = o.dlvdAdr;
				cj.addr.checkDawnable();

				var isAble = cj.addr.data.isDawnAble;
				if (!isAble) {
					alert("새벽배송이 불가능합니다. 다른 배송지를 선택해주세요.");
					return false;
				}

				$.extend(o, {"isDawnAble" : isAble});
			}

			for(var key in o){
				if(o[key] == null){ o[key] = ""; }
			}

			if (opener) { opener.cj.dlvd.memberDlvdCallback(o); }

			window.close();
		},

		// 우편번호 검색 팝업 콜백 함수
		zipSearchCallBack : function(data) {
			var gubun = " ";
			var adrLast = data.mnAdr.substring(data.mnAdr.length - 1);
			var adrDtlFirst = cj.isEmpty(data.dtlAdr) ? "" : data.dtlAdr.substring(0, 1);

			if ((adrLast).match(/[0-9]/) && (adrDtlFirst).match(/[0-9]/)) {
				gubun = ", ";
			}

			var fullAdr = data.mnAdr + (cj.isEmpty(data.dtlAdr) ? "" : gubun + data.dtlAdr);

			$("#fullAdr").val(fullAdr);
			$("input[name=dlvdZipCd]").val(data.zipCd);
			$("input[name=dlvdAdr]").val(data.mnAdr);
			$("input[name=dlvdDtlAdr]").val(data.dtlAdr);
			$("#dawnYn").val(data.isDawnAble ? "Y" : "N");

			if (data.isDawnAble) {
				$("#memberDlvdModifyPopup .dawnArea").show();
			} else {
				$("#memberDlvdModifyPopup .dawnArea").hide();
			}

		},

		// 공통 validate에서 처리하지 않는 validate 처리
		validate : function(o) {
			var msg = "";

			// '연락처2'가 하나라도 적혀있는 경우
			if (!cj.isEmpty(o.dlvdTlno1) || !cj.isEmpty(o.dlvdTlno2) || !cj.isEmpty(o.dlvdTlno3)) {

				if (cj.isEmpty(o.dlvdTlno1)) {
					alert("전화번호를 선택해 주세요.");

					$("select[name=dlvdTlno1]").focus();
					return false;
				} else if (cj.isEmpty(o.dlvdTlno2)) {
					alert("전화번호를 입력해 주세요.");

					$("input[name=dlvdTlno2]").focus();
					return false;
				} else if (cj.isEmpty(o.dlvdTlno3)) {
					alert("전화번호를 입력해 주세요.");

					$("input[name=dlvdTlno3]").focus();
					return false;
				}
			}

			// 새벽 배송 여부
			var dawnYn =  $("#dawnYn").val();
			if (dawnYn == "Y") {
				if (cj.isEmpty(o.pubDoorEntWayCd)) {
					alert("수령방법을 입력해주세요.");

					$("select[name=pubDoorEntWayCd]").focus();
					return false;
				} else {

					if (o.pubDoorEntWayCd == "2") {
						if (cj.isEmpty($("#question_cont2").val())) {
							alert("공동현관 비밀번호를 입력해주세요.");

							$("#question_cont2").focus();
							return false;
						}
					} else if (o.pubDoorEntWayCd == "9") {
						if (cj.isEmpty($("#question_cont6").val())) {
							alert("기타사항을 입력해주세요.");

							$("#question_cont6").focus();
							return false;
						}
					}

				}

			} else {

				alert("새벽배송이 불가능합니다. 다른 배송지를 선택해주세요.");
				return false;
			}

			return true;
		},

		page : {
			goPage : function(page) {
				cj.dlvd.loadDlvdList(page);
			}
		}
	},

	// 위시(찜하기) 관련 기능들
	wishAddClick : false,
	wish : {
		// 찜하기 처리
		add : function(prdCd, obj, callback, url){
			
			if(cj.wishAddClick){return;}
			cj.wishAddClick = true;
			
			$.ajax({
				async		: true,
				url			: (url == "" ? '/pc/comm/wish.json' : url),
				dataType	: 'json',
				type		: 'POST',
				data		: { prdCd : prdCd },
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					// 찜추가/취소 메세지 처리
					if(data.info.subResultCode == "1") {
						cj.com.toast('쇼핑찜 등록!', 'insertwish');
					} else if(data.info.subResultCode == "2") {
						cj.com.toast('쇼핑찜 취소!', 'removewish');
					}

					// 버튼 활성화 및 비활성화
					if(data.info.subResultCode == "1") {
						$(obj).addClass("on");
					} else if(data.info.subResultCode == "2") {
						$(obj).removeClass("on");
					}



					// 콜백 처리 진행
					if(typeof(callback) == "function"){ callback(data); }
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				},
				complete : function() {
		        	setTimeout(function(){ cj.wishAddClick = false; }, 1000);
		        }
			});
		}
	},

	// 장바구니 관련 기능들...
	cartAddClick : false,
	cart : {
		lastPrdCd : "",

		// 상품번호 기준으로 장바구니 담기 처리
		add : function(){
			var prdCd			= arguments[0];
			var ctgrId			= arguments[1] || "";
			var plnId			= arguments[2] || "";
			var giftSetEvntId	= arguments[3] || "";
			var slpkPrdCd		= arguments[4] || "";
			var prdQtyList		= arguments[5] || "";
			var cartPrdPtnCd	= arguments[6] || "";
			var dlvDt			= arguments[7] || "";

			cj.cart.add_({ prdCd : prdCd, ctgrId : ctgrId, plnId : plnId, giftSetEvntId : giftSetEvntId, slpkPrdCd : slpkPrdCd, prdQtyList : prdQtyList, cartPrdPtnCd : cartPrdPtnCd, dlvDt : dlvDt });
		},

		// 상품번호 기준으로 장바구니 담기 처리 후 콜백 호출
		addPrdList : function(){
			var prdCd			= arguments[0];
			var ctgrId			= arguments[1] || "";
			var plnId			= arguments[2] || "";
			var giftSetEvntId	= arguments[3] || "";
			var slpkPrdCd		= arguments[4] || "";
			var prdQtyList		= arguments[5] || "";
			var cartPrdPtnCd	= arguments[6] || "";
			var dlvDt			= arguments[7] || "";
			var callback		= arguments[8] || "";

			cj.cart.add_({ prdCd : prdCd, ctgrId : ctgrId, plnId : plnId, giftSetEvntId : giftSetEvntId, slpkPrdCd : slpkPrdCd, prdQtyList : prdQtyList, cartPrdPtnCd : cartPrdPtnCd, dlvDt : dlvDt }, callback);
		},

		// 장바구니에 상품을 담고 콜백 호출
		addBack : function(prdCd, callback){
			cj.cart.add_({ prdCd : prdCd }, callback);
		},

		// 출고일 지정 장바구니 담기 처리
		addDlvDt : function(prdCd, dlvDt, plnId){
			cj.cart.add_({ prdCd : prdCd, dlvDt : dlvDt, plnId : plnId });
		},

		// 동일 상품 복수 출고일 장바구니 담기 처리
		addMultiDlvDt : function(cartList, callback) {
			cj.cart.add_(cartList[0], callback, cartList);
		},

		// 장바구니 담기 공통 처리
		add_ : function(info, callback, cartListData){
			
			if(cj.cartAddClick){return;}
			cj.cartAddClick = true;
			
			var defaults = {
				prdCd			: "",
				ctgrId			: "",
				plnId			: "",
				giftSetEvntId	: "",
				slpkPrdCd		: "",
				prdQtyList		: "",
				cartPrdPtnCd	: "",
				dlvDt			: ""
			}
			var data = $.extend(true, defaults, info);
			data.cartListData = JSON.stringify(cartListData);
			cj.cart.lastPrdCd = data.prdCd;
			console.log("cart Data = > " + data)
			$.ajax({
				async		: true,
				url			: '/pc/comm/cart.json',
				dataType	: 'json',
				type		: 'POST',
				data		: data,
				success		: function(data, textStatus, jqXHR){
					// 재고 없음인 경우에는 재입고 알림으로 전환해야 함.
					if(data.resultCode == 710){
						if(confirm(data.message)){
							if(cjConf.loginYn == "Y"){
								cj.ntcReq.restock(cj.cart.lastPrdCd);
							}else{
								if(confirm("로그인이 필요한 서비스입니다.\n로그인하시겠습니까?")){
									cj.login();
								}
							}
						}


						return;
					}


					// 장바구니 일반 담기 로직 처리
					if(!crew.ajaxValidate(data)){ return; }

					//console.log("headerCartCount : "+data.headerCartCount);
					$("#headerCartCount").text(data.headerCartCount);

					//console.log(data);

					var info = data.info;

					if(typeof(callback) == "function"){
						callback(data);
					}else{

						var msg = "배송일 " + data.info.dlvDtMM + "/" + data.info.dlvDtDD;
						
						if(info.subResultCode == 100){
							cj.com.toast(msg, "cart");
						}else if(info.subResultCode == 200){
							//msg = "한번 더 담으셨어요.<br>장바구니 수량이 " + info.prdQty + "개가 되었습니다.";
							cj.com.toast(msg, "cart");
						}else if(info.subResultCode == 300){	// 복수종류의 상품이 장바구니에 등록되는 경우
							cj.com.toast(msg, "cart");
						}else{
							cj.com.toast(msg, "cart");
						}
						
						/*if(info.subResultCode == 100){
							alert("상품이 장바구니에 담겼습니다.");
						}else if(info.subResultCode == 200){
							alert("한번 더 담으셨어요.\r\n장바구니 수량이 " + info.prdQty + "개가 되었습니다." ");
						}else if(info.subResultCode == 300){	// 복수종류의 상품이 장바구니에 등록되는 경우
							alert("상품이 장바구니에 담겼습니다.");
						}else{
							alert("상품이 장바구니에 담겼습니다.");
						}					*/
					}
					
					// Facebook 픽셀 장바구니 이벤트
					if (typeof fbq != 'undefined' && fbq != null) {
					    fbq('track', 'AddToCart', {
					    	content_type: 'product',
					    	content_ids: [cj.cart.lastPrdCd],
					    	contents: [{'id': cj.cart.lastPrdCd, 'quantity': data.info.prdQty}]
						});
					}
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				},
				complete : function() {
		        	setTimeout(function(){ cj.cartAddClick = false; }, 1000);
		        }
			});
		},

		// 상품번호 기준으로 바로구매 담기 처리
		buy : function(){
			var prdCd			= arguments[0];
			var ctgrId			= arguments[1] || "";
			var plnId			= arguments[2] || "";
			var giftSetEvntId	= arguments[3] || "";
			var slpkPrdCd		= arguments[4] || "";
			var prdQtyList		= arguments[5] || "";
			var cartPrdPtnCd	= arguments[6] || "";
			var dlvDt			= arguments[7] || "";
			var giftOdrYn		= arguments[8] || "";

			cj.cart.buy_({ prdCd : prdCd, ctgrId : ctgrId, plnId : plnId, giftSetEvntId : giftSetEvntId, slpkPrdCd : slpkPrdCd, prdQtyList : prdQtyList, cartPrdPtnCd : cartPrdPtnCd, dlvDt : dlvDt, giftOdrYn : giftOdrYn });
		},

		// 동일 상품 복수 출고일 장바구니 담기 처리
		buyMultiDlvDt : function(cartList, callback) {
			cj.cart.buy_(cartList[0], callback, cartList);
		},

		// 바로구매 공통 처리
		buy_ : function(info, callback, cartListData){
			var defaults = {
				prdCd			: "",
				ctgrId			: "",
				plnId			: "",
				giftSetEvntId	: "",
				slpkPrdCd		: "",
				prdQtyList		: "",
				cartPrdPtnCd	: "",
				dlvDt			: ""
			}
			var data = $.extend(true, defaults, info);
			data.cartListData = JSON.stringify(cartListData);

			$.ajax({
				async		: true,
				url			: '/pc/comm/buy.json',
				dataType	: 'json',
				type		: 'POST',
				data		: data,
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					var orderUrl = cj.config.url.order + "?cartPrdPtnCd=" + info.cartPrdPtnCd;

					if (info.giftOdrYn) {
						orderUrl += "&giftOdrYn=" + info.giftOdrYn;
					}
					location.href = orderUrl;
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		}
	},


	/*
     * SNS 공유
     * 파라미터 : text, url, description, image (필수 x)
     * 파라미터 값이 없으면 meta태그에서 사용
     */
    share : {
        info : {},

        setInfo : function(target, object) {
        	this.info = $.extend(true, target, object);
        },

        getInfo : function () {
            return this.info;
        },

        getInfoValue : function(key) {
            return key =! "" ? this.info[key] : "";
        },

        setOption : function(method, option) { // 옵션 세팅
            this.info = {};

    		// 기본값 셋팅
    		var defaults = {
    			text		: $("meta[property='og:title']" ).length == 0 ? document.title : $("meta[property='og:title']" ).attr("content"),
    			url			: $("meta[property='og:url']" ).length == 0 ? location.href : $("meta[property='og:url']").attr("content"),
    			description	: $("meta[property='og:description']" ).length == 0 ? document.title : $("meta[property='og:description']" ).attr("content"),
    			image		: $("meta[property='og:image']" ).length == 0 ? "/" : $("meta[property='og:image']" ).attr("content")
    		}
    		this.setInfo(defaults, option);

            if (method == "kakaoShare") {
                var pcUrl = (this.getInfoValue("url")).replace("/mo/", "/pc/");
                var moUrl = (this.getInfoValue("url")).replace("/pc/", "/mo/");

                var data = {
                        objectType: 'feed',
                        content: {
                          title: this.getInfoValue("text"),
                          description: this.getInfoValue("description"),
                          imageUrl: this.getInfoValue("image"),
                          link: {
                            mobileWebUrl: moUrl,
                            webUrl: pcUrl
                          }
                        },
                        buttons: [{
                          title: '웹으로 보기',
                          link: {
                            mobileWebUrl: moUrl,
                            webUrl: pcUrl
                          }
                        }]
                    };

                this.setInfo({},data);

            } else if (method == "kakaoStoryShare"){
                var data = {
                        url : this.getInfoValue("url"),
                        text : this.getInfoValue("text")
                    };

                this.setInfo({},data);
            }
        },

        kakaoShare : function(option) { // 카카오 공유
            this.setOption("kakaoShare", option);

            Kakao.Link.sendDefault(this.getInfo());
        },

        facebookShare : function(option) { // 페이스북 공유
            this.setOption("facebookShare", option);

            window.open("http://www.facebook.com/sharer/sharer.php?u=" + encodeURIComponent(this.getInfoValue("url")),  "_blank");
        },

        twitterShare : function(option) { // 트위터 공유
            this.setOption("twitterShare", option);

            window.open("https://twitter.com/intent/tweet?text=" + encodeURIComponent(this.getInfoValue("text")) +"&url=" + encodeURIComponent(this.getInfoValue("url")), "_blank");
        },

        kakaoStoryShare : function(option) { // 카카오 스토리 공유
            this.setOption("kakaoStoryShare", option);

            Kakao.Story.share(this.getInfo());
        },

        urlCopy : function(option) { // URL 복사 .. 아이폰 처리 필요
            this.setOption("urlCopy", option);

            cj.copyText(this.getInfoValue("url"));
            alert("복사되었습니다.");
        },

        snsOpenModal : function () { // sns 공유 레이어 호출
        	var text        = arguments[0];
			var url         = arguments[1];
			var image       = arguments[2];
			var description = arguments[3];

			// 공유하기 호출
			$.ajax({
		        async       : true,
		        url         : "/pc/comm/snsShare.json",
		        dataType    : "json",
		        type        : 'POST',
		        success     : function(data, textStatus, jqXHR){
		            if(!crew.ajaxValidate(data)){ return; }

		            $("#modal-mini-share").remove();
		            $("#modal-wrap").append(data.html);

		            $("#modal-wrap > #modal-mini-share").find("a").each(function(){
						if (text != "") { $(this).data("text", text) }
						if (url != "") { $(this).data("url", url ) }
						if (image != "") { $(this).data("image", image) }
						if (description != "") { $(this).data("description", description) }
					})
					$(".modal-container").append(data.html);
					vcui.require( ['ui/modal'], function() {
		            	$("#snsGR_layerPopup").vcModal();
		        	});
		        },
		        error      : function(jqXHR, textStatus, errorThrown){
		          //console.log(jqXHR.status);
		        }
		    });
        }
    },

    // 상품평 관련 기능
    prodReview : {
    	info : {},
    	prodReviewCallback : function(data){},

    	// 정보 반환
    	getInfoValue : function(key) {
    		return key =! "" ? this.info[key] : "";
    	},

		// 상품평 모아보기 팝업
		open : function(seq , check){
			if (cj.isEmpty(seq)) { return false; }

			cj.prodReview.paramSeq = seq;
			
			var type = "";
			if(check=="02"){
				type="Y";
			}else{
				type="N";
			}

			cj.prodReview.type = type;
			
			vcui.util.openPopup( '/pc/popup/myAct/mbrProdReview?paramSeq='+seq+'&type='+type, 848, 820, {name:'mbrProdReview', align:null, scrollbars:'yes'} );
		},
		// 상품평 모아보기 데이터
		loadList : function(page) {
			if (cj.isEmpty(page)) { return false; }

			if (page == 1) { $(".mbrProdReviewArea").empty(); }

			var seq = opener.cj.prodReview.paramSeq;
			var type = opener.cj.prodReview.type;
			if (cj.isEmpty(seq)) { return false; }

			$.ajax({
				async		: true,
				url			: '/pc/myAct/mbrProdReviewList.json',
				dataType	: "json",
				type		: 'POST',
				data		: { paramSeq : seq, type : type, page : page},
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)) { return; }
					$(".mbrProdReviewArea").html(data.html);
					$('#reviewPagingArea').html(data.paging);
					$.each($("img[name^=imgResizingCookitSub]"), function(index, item){
						var imgObject = {};
						imgObject.url = $(this).attr("url");
						imgObject.w = 0;
						imgObject.h = 0;
						imgObject.obj = this;
						image.init(imgObject);
					});
				},
				error: function(e) {
//    				console.log(jqXHR.status);
				}
			});
		},

		// 모아보기에서 마크업 처리
    	link : function(obj) {
    		$(obj).closest("div.review_module").toggleClass("open");
    		$(obj).find('div.txt_wrap div.txt_cont p.txt').toggleClass("mt_elps");
    	},

    	// 상품평 저장
    	save : function() {
    		var f = $("form[name=reviewForm");
    		var o = f.serializeObject();

    		if (!this.validate(f)) {
    			return false;
    		} else {
    			// 데이터 요청
    			$.ajax({
    				async		: true,
    				url			: "/pc/myAct/createProdReview.json",
    				dataType	: "json",
    				type		: 'POST',
    				data		: o,
    				success		: function(data, textStatus, jqXHR){
    					if (data.resultCode == "140") { // 금지어 존재
    						$("#vanWordReview").remove();
    						$(".modal-container").append(data.html);
    			            $('[data-js=modal]').modal();
    			            $('[data-js=modal]').trigger('open', $('#vanWordReview'));
    			            $("textarea[name=prdRvwTxt]").val("");
    						return false;
    					}

    					if(!crew.ajaxValidate(data)){ return; }

    					alert("등록되었습니다.");
    					$('[data-js=modal]').trigger('close', $('#formProdReviewModal'));

    					cj.prodReview.prodReviewCallback();
    				},
    				error	   : function(jqXHR, textStatus, errorThrown){
    					//console.log(jqXHR.status);
    				}
    			});
    		}
    	},

    	// 상품평 validate
    	validate : function(f) {
    		var chkCnt = f.find("input[type=radio]:checked").length;

    		if (this.getInfoValue("questCnt") != chkCnt) {
    			alert("상품평 항목을 모두 선택해 주세요.");
    			return false;
    		}

    		if ($("textarea[name='prdRvwTxt']").length > 0) {
    			var len = $("textarea[name='prdRvwTxt']").val().length;

    			if (len < 20) {
    				$("textarea[name='prdRvwTxt']").focus();
    				alert("상품평 내용은 최소20자 이상 입력해주세요.");
    				return false;
    			}

    			if (len > 200) {
    				$("textarea[name='prdRvwTxt']").focus();
    				alert("상품평 내용은 최대200자 이하 입력해주세요.");
    				return false;
    			}
    		}

    		return true;
    	},

		// 리뷰 상세보기
    	// wid1 : 와이즈로그 메인 파라미터
		openDetail : function(prdRvwSeq, rvwDivCd, wid1) {
			if (cj.isEmpty(prdRvwSeq) || cj.isEmpty(rvwDivCd)) { return false;}

			vcui.util.openPopup( '/pc/popup/myAct/mbrProdReviewDetail?prdRvwSeq='+prdRvwSeq+'&rvwDivCd='+rvwDivCd + (wid1?'&wid1='+wid1:''), 848, 820, {name:'mbrProdReviewDetail', align:null, scrollbars:'yes'} );
		},

		// 리뷰 작성 유의사항 레이어팝업
		openReviewGuide : function() {

			if ( $("#reviewGuide").length > 0) {  $("#reviewGuide").remove(); }
			// 데이터 요청
			$.ajax({
				async		: true,
				url			: "/pc/myAct/reviewGuide.json",
				dataType	: "json",
				type		: 'POST',
				data		: {},
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }
					$(".modal-container").append(data.html);

					vcui.require( ['ui/modal'], function() {
						$("#reviewGuide").vcModal();
					});
				},
				error		: function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		}
	},


    // 알림요청 처리 관련 기능
    ntcReq : {

    	_this : '',

    	data : {
    		dlvReqDt : ''
    	},

    	// 팝업에서 알림신청 등록할경우에만 실행되는 콜백함수
    	requestCallback : function() {},

    	// 팝업에서 알림신청 취소할경우에만 실행되는 콜백함수
    	cancelCallback : function() {},

    	// 할인 알림 신청
    	sale : function(prdCd){
    		this.ntcReq_(prdCd, "01");
    	},
    	// 입고 알림 신청
    	restock : function(prdCd, requestCallback, cancelCallback, obj, dlvReqDt){
    		this._this = $(obj);

    		// 날짜데이터 받아와야함(해당 날짜 체크박스 체크 목적)
    		if(!cj.isEmpty(dlvReqDt)) {
    			this.data.dlvReqDt = dlvReqDt;
    		}

    		if(typeof requestCallback == "function") {
    			this.requestCallback = requestCallback;
    		}

    		if(typeof cancelCallback == "function") {
    			this.cancelCallback = cancelCallback;
    		}

    		this.ntcReq_(prdCd, "02");
    	},

    	// 재판매알림(03임)
    	resale : function(prdCd, requestCallback, cancelCallback, obj){
    		this._this = $(obj);

    		if(typeof requestCallback == "function") {
    			this.requestCallback = requestCallback;
    		}

    		if(typeof cancelCallback == "function") {
    			this.cancelCallback = cancelCallback;
    		}

    		// 버튼 활성화 여부에따라 신청팝업 및 팝업 없이 취소처리.
    		var isOn = this._this.hasClass('on');
    		if(isOn) {
    			this.resaleCancel(prdCd);
    		} else {
    			this.ntcReq_(prdCd, "03");
    		}
    	},


    	ntcReq_ : function(prdCd, prdNtcDivCd){
    		var url = '';
    		var modalId = "";
    		if(prdNtcDivCd == "02") {
    			// 재입고알림신청 url
    			url = "/pc/myAct/prdNtcForm.json";
    			modalId = "#rePut_layerPopup";
    		} else if(prdNtcDivCd == "03") {
    			// 재판매알림신청 url
    			url = "/pc/myAct/resaleNtcForm.json";
    			modalId = "#reSale_layerPopup";
    		} else {
    			alert("잘못된 알림신청 코드");
    			return;
    		}

    		// 알림신청 팝업
    		$.ajax({
    			method: 'GET',
    			url: url,
    			data: { prdCd : prdCd, prdNtcDivCd : prdNtcDivCd },
    			success: function(data) {
    				if(!crew.ajaxValidate(data)) { return; }

    				// 영역에 데이터가 존재하면 영역 초기화
    				if($(modalId).length != 0) {
    					$(modalId).remove();
    				}

    				$('.modal-container').append(data.html);

    				vcui.require( ['ui/modal'], function() {
    	            	$(modalId).vcModal().on('modalhidden', function(e) {
    	            		// 웹 접근성 적용 처리
    	                    cj.ntcReq._this.focus();
    	                    $(modalId).off('modalhidden');
    	                });
    	        	});
    			},
    			error	   : function(jqXHR, textStatus, errorThrown){
//    				console.log(e);
    			}
    		});
    	},

    	// 재판매 알림신청 취소처리
    	resaleCancel : function (prdCd) {
    		var resReqBlock = false;
    		if(resReqBlock) { return; }
    		resReqBlock  = true;

    		$.ajax({
    			type : "POST",
    			url : "/pc/myAct/mergeProductNotice.json",
    			data : {prdCd : prdCd, prdNtcDivCd : '03', mktWayCd : 'SK'},
    			dataType : "json",
    			success : function(data) {
    				if(!crew.ajaxValidate(data)) { return; }

    				if(data.info.mergeResultCode == 2) {
    					alert("재판매 알림 신청이 취소되었습니다.");
    					cj.ntcReq.cancelCallback(cj.ntcReq._this);

    					if(cj.ntcReq._this.hasClass('btn_resel')) {
    						cj.ntcReq._this.removeClass('on');
    						cj.ntcReq._this.find('span').text('재판매 알림 신청');
    					}

    					$('.btn_sale_alarm').each(function(i, e) {
    						var $saleAlarm = $(e);
    						if(!cj.isEmpty($saleAlarm.data('prd_cd'))) {
    							if($saleAlarm.data('prd_cd') == prdCd){
    								$saleAlarm.removeClass('on');
    								$saleAlarm.find('span').text('재판매 알림 신청');
    							}
    						} else {
								$saleAlarm.removeClass('on');
								$saleAlarm.find('span').text('재판매 알림 신청');
    						}
    					});
    				}

    				resReqBlock = false;
    			},
    			error : function(jqXHR, textStatus, errorThrown) {
//    				console.log(errorThrown);
    			}
    		});
    	}

    },


    // 대량견적 장바구니 추가 및 삭제 기능
    massCart : {
    	add : function(prdCd, reqPrdQty, callback) {
    		this.massCartReq_(prdCd, reqPrdQty, 'add', callback);
    	},
    	remove : function(prdCd, callback) {
    		this.massCartReq_(prdCd, 0, 'remove', callback);
    	},
    	massCartReq_ : function(prdCd, reqPrdQty, mode, callback) {

    		$.ajax({
    			method: 'POST',
    			url: '/pc/myAct/massQuotaPrdToggleCart.json',
    			data: { prdCd : prdCd, mode : mode, reqPrdQty : reqPrdQty },
    			dataType: "json",
    			success: function(data) {
    				if(!crew.ajaxValidate(data)) { return; }

    				if(typeof(callback) == "function"){
    					callback(data);
    				}
    			},
    			error: function(e) {
//    				console.log(e);
    			}
    		});
    	}

    },

	addComma : function(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	},
	removeComma : function(str) {
	    str = String(str);
	    return str.replace(/[^\d]+/g, '');
	},
	isEmpty: function (value) {
	    return (value == null || value == undefined || value == "");
	},

	footer : { // 푸터 이용약관 modal
		open : function(name) {
			if (cj.isEmpty(name)) { return false; }

			$.ajax({
				async		: true,
				url			: "/pc/footer/" + name + ".json",
				dataType	: "json",
				type		: 'POST',
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }
					$("#" + name).remove();

					$(".modal-container").append(data.html);
					$('[data-js=modal]').modal();
					$('[data-js=modal]').trigger('open', $('#' + name));
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		},
		close : function (name) {
			if (name == "") { return false; }

			$('[data-js=modal]').trigger('close', $('#' + name));
		}
	},

	// 공통 기능
	com : {
		// 토스트 메세지 처리
		toast : function(msg, type, timeout){
			var type = type || "text";
			timeout = cj.nvl(timeout, 1000);
			$('.toastMsgAreaWish').remove();
			$('.toastMsgAreaText').remove();
			$('.toastMsgAreaCart').remove();
			// 텍스트형
			if(type == "text"){

				var area = $('.toastMsgAreaText');

				if(area.length == 0){
					var html = [];

					html.push("<article id='lay_wrap' class='lay_wrap toasts_message toastMsgAreaText' style='display:none;'>");
					html.push("    <h1 class='hide'><span class='hide'>Toasts message</span></h1>");
					html.push("    <section class='lay_conts hcont'>");
					html.push("        <p class='msg'>" + msg + "</p>");
					html.push("    </section>");
					html.push("</article>");

					$("body").append(html.join(""));

					area = $('.toastMsgAreaText');
				}

				area.find(".msg").html(msg);
				area.vcModal();

				setTimeout(function(){ area.vcModal('instance').close(); }, timeout);

			}

			// 장바구니형
			if(type == "cart"){
				var area = $('.toastMsgAreaCart');

				if(area.length == 0){
					var html = [];
					html.push("<article id='lay_wrap' class='lay_wrap alerting_message add_cart toastMsgAreaCart' style='display:none;'>");
					html.push("   <h1 class='hide'><span class='hide'>장바구니 담기</span></h1>");
					html.push("   <section class='lay_conts'>");
					html.push("      <p class='msg'>" + msg + "</p>");
					html.push("   </section>");
					html.push("</article>");
					$("body").append(html.join(""));

					area = $('.toastMsgAreaCart');
				}

				area.find(".msg").html(msg);
				//area.vcModal( {dimStyle:{opacity:0}} );
				//setTimeout(function(){ area.vcModal('instance').close(); }, 1000);
				area.show();
				setTimeout(function(){ area.hide(); }, 1000);
			}

			// 쇼핑찜등록형
			if(type == "insertwish"){
				var area = $('.toastMsgAreaWish');
				if(area.length == 0){
					var html = [];
					html.push("<article id='lay_wrap' class='lay_wrap alerting_message add_interest toastMsgAreaWish' style='display:none;'>");
					html.push("	<h1 class='hide'><span class='hide'>찜하기</span></h1>");
					html.push("	<section class='lay_conts'>");
					html.push("		<p class='msg'>쇼핑찜 등록!</p>");
					html.push("	</section>");
					html.push("</article>");

					$("body").append(html.join(""));

					area = $('.toastMsgAreaWish');
				}

				area.find(".msg").html(msg);
				//area.vcModal( {dimStyle:{opacity:0}} );
				//setTimeout(function(){ area.vcModal('instance').close(); }, 1000);
				area.show();
				setTimeout(function(){ area.hide(); }, 1000);
			}

			// 쇼핑찜취소형
			if(type == "removewish"){
				var area = $('.toastMsgAreaWish');
				if(area.length == 0){
					var html = [];
					html.push("<article id='lay_wrap' class='lay_wrap alerting_message remove_interest toastMsgAreaWish' style='display:none;'>");
					html.push("	<h1 class='hide'><span class='hide'>찜하기</span></h1>");
					html.push("	<section class='lay_conts'>");
					html.push("		<p class='msg'>쇼핑찜 취소!</p>");
					html.push("	</section>");
					html.push("</article>");

					$("body").append(html.join(""));

					area = $('.toastMsgAreaWish');
				}

				area.find(".msg").html(msg);
				//area.vcModal( {dimStyle:{opacity:0}} );
				//setTimeout(function(){ area.vcModal('instance').close(); }, 1000);
				area.show();
				setTimeout(function(){ area.hide(); }, 1000);
			}

			// 좋아요
			if(type == "addlike"){
				var area = $('.toastMsgAreaLike');
				if(area.length == 0){
					var html = [];
					html.push("<article id='lay_wrap' class='lay_wrap alerting_message add_like toastMsgAreaLike' style='display:none;'>");
					html.push("	<h1 class='hide'><span class='hide'>좋아요</span></h1>");
					html.push("	<section class='lay_conts'>");
					html.push("		<p class='msg'>좋아요 완료!</p>");
					html.push("	</section>");
					html.push("</article>");

					$("body").append(html.join(""));

					area = $('.toastMsgAreaLike');
				}

				area.find(".msg").html(msg);
				area.show();
				setTimeout(function(){ area.hide(); }, 1000);
			}

			// 좋아요 취소
			if(type == "removelike"){
				var area = $('.toastMsgAreaRemoveLike');
				if(area.length == 0){
					var html = [];
					html.push("<article id='lay_wrap' class='lay_wrap alerting_message remove_like toastMsgAreaRemoveLike' style='display:none;'>");
					html.push("	<h1 class='hide'><span class='hide'>좋아요</span></h1>");
					html.push("	<section class='lay_conts'>");
					html.push("		<p class='msg'>좋아요 취소!</p>");
					html.push("	</section>");
					html.push("</article>");

					$("body").append(html.join(""));

					area = $('.toastMsgAreaLike');
				}

				area.find(".msg").html(msg);
				area.show();
				setTimeout(function(){ area.hide(); }, 1000);
			}

		},
		// no image 처리
		noImg : function(obj, type, src){
			var src = src || "/cjkit/web/images/common/no_img_268x320.png";
			if(type == 'P') {
				// 메인 빌보드 영역 600*600 (1:1)
				src = "/cjkit/web/images/common/no_img_600x600.png";
			}
			if(type == 'U') {
				src = "/cjkit/web/images/common/no_img_780x480.png";
			}

			// 반복호출 방지
			obj.onerror = null;

			// no image 처리
			obj.src = src;
		},
		
		lazyLoad : function (target) {
			var target = target || "*[data-src]";
	        $(target).Lazy({
	            beforeLoad: function beforeLoad(element) {},
	            afterLoad: function afterLoad(element) {},
	            onError: function onError(element) {
	                element.attr({
	                	src: "/cjkit/web/images/common/no_img_268x320.png",
	                    alt: "이미지가 없습니다."
	                });
	            },
	            onFinishedAll: function onFinishedAll() {},
	            effect: "fadeIn"
	        });
	    }
	},

	// 앱관련 기능
	app : {
		// 앱설정 호출
		showSetting : function(name) {
			CJONS_PLUGIN.exec("OK_COMMON.showSettingPopUp", null, null, null);
		},

		appSnsShare : function (link) {
			CJONS_PLUGIN.exec("OK_SNS_SHARE.defaultSharing", function(data){
				// 처리 내용 무.
			}, null, {"snsLink" : link});

		}
	},

	// 텍스트 복사
	copyText : function(text) {
		var isIos = navigator.userAgent.match(/ipad|iphone/i);
		if(isIos){
			var t = document.createElement("textarea");
	        document.body.appendChild(t);
	        t.value = text;
			range = document.createRange();
            range.selectNodeContents(t);
            selection = window.getSelection();
            selection.removeAllRanges();
            selection.addRange(range);
            t.setSelectionRange(0, 999999);
            document.execCommand("copy");
  		  	document.body.removeChild(t);
		}else{
			$("#ShareUrl").val(text);
			$("#ShareUrl").select();
			document.execCommand("copy");
		}


	},

	// 우편번호 찾기
	addr : {
		// 우편번호 검색조건
		addrSearchCondi : {
			page		: 1,
			keyword		: '',
			siNm		: '',
			guNm		: '',
			checkDawn	: true,
			pageSize	: 5
		},

		// 주소데이터
		data : {
			zipCd : '',
			mnAdr : '',
			dtlAdr : '',
			isDawnAble : false,
			needDtlAdr : true,
		},

		// 콜백 지정
		callback : function(data) {},

		// 우편번호 찾기 팝업창 열기
		// wid1 : 와이즈로그 메인 파라미터
		open : function(callback, checkDawn, checkOnly, wid1){

			// 새벽배송여부 체크 필요시
			if(cj.isEmpty(checkDawn)) {
				checkDawn = false;
			}

			this.addrSearchCondi.checkDawn = checkDawn;

			if(cj.isEmpty(checkOnly)) {
				checkOnly = false;
			}

			// 선택처리시의 콜백 지정
			if(typeof(callback) == "function"){
				this.callback = callback;
			}else{
				this.callback = function(data){};
			}

			vcui.util.openPopup( '/pc/comm/addrSearchPop?checkDawn=' + checkDawn + '&checkOnly=' + checkOnly + (wid1?'&wid1='+wid1:''), 500, 600, {scrollbars:'yes'});
		},

		// 지번 주소, 도로명 주소 같이 가져옴(처음 검색시)
		search : function(keyword, page, drawCallback) {
			this.addrSearchCondi.keyword = keyword;
			this.addrSearchCondi.page = page;

			if(this.addrSearchCondi.keyword == '') {
				alert('지번주소(동읍면리), 도로명 또는 건물명(아파트)을 입력해주세요.');
				return;
			}

			$.ajax({
				async		: true,
				url			: "/common/address/searchAddress.json",
				dataType	: "json",
				type		: 'POST',
				data		: this.addrSearchCondi,
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					if(typeof drawCallback === 'function') { drawCallback(data) }

					$('#searchDataArea').show();
					$('#addrSearchDtlInput, #addrSearchBtnWrap').hide();
					$('#zip_noti').hide();

				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		},
		// 도로명 주소 데이터만 가져옴
		searchDoro : function(keyword, page, siNm, guNm, drawCallback) {
			this.addrSearchCondi.keyword = keyword;
			this.addrSearchCondi.page = page;
			this.addrSearchCondi.siNm = siNm;
			this.addrSearchCondi.guNm = guNm;

			if(this.addrSearchCondi.keyword == '') {
				alert('지번주소(동읍면리), 도로명 또는 건물명(아파트)을 입력해주세요.');
				return;
			}

			$.ajax({
				async		: true,
				url			: "/common/address/searchAddressRoad.json",
				dataType	: "json",
				type		: 'POST',
				data		: this.addrSearchCondi,
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					if(typeof drawCallback === 'function') { drawCallback(data) }
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		},
		// 지번주소 데이터만 가져옴
		searchJibun : function(keyword, page, drawCallback) {
			this.addrSearchCondi.keyword = keyword;
			this.addrSearchCondi.page = page;

			if(this.addrSearchCondi.keyword == '') {
				alert('지번주소(동읍면리), 도로명 또는 건물명(아파트)을 입력해주세요.');
				return;
			}

			$.ajax({
				async		: true,
				url			: "/common/address/searchAddressJibun.json",
				dataType	: "json",
				type		: 'POST',
				data		: this.addrSearchCondi,
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }

					if(typeof drawCallback === 'function') { drawCallback(data) }
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		},
		// 주소 선택시
		getAddr : function(obj) {
			// this.data.zipCd : 우편번호, this.data.mnAdr : 주소
			this.data.zipCd = $(obj).find('input[name=zipCd]').val();
			this.data.mnAdr = $(obj).find('input[name=mnAdr]').val().trim();

			$('#searchDataArea').hide();
			$('#addrSearchDtlInput, #addrSearchBtnWrap').show();
			$('#addrSearchInputTxt').val(this.data.zipCd + ' ' + this.data.mnAdr);

			// 새벽배송체크가 필요할 경우
			if(cj.addr.addrSearchCondi.checkDawn) {
				this.checkDawnable();
			}

		},
		// 상세주소 입력후 등록 버튼 클릭시
		submitAddr : function() {
			this.data.dtlAdr = $('#dtlAdr').val();

			if(!this.validateAdrData()) {
				return;
			}

			if(typeof opener.cj.addr.callback === 'function') {
				opener.cj.addr.callback(opener.cj.addr.data);
				window.close();
			}
		},
		// 주소데이터 검증
		validateAdrData : function() {
			var adrData = this.data.zipCd + this.data.mnAdr;
			if(cj.isEmpty(adrData)){
				alert('선택 한 주소 정보가 없습니다.');
				return false;
			}

			if(cj.isEmpty(this.data.dtlAdr) && !this.data.needDtlAdr){
				alert('상세 주소를 입력 해 주세요.');
				return false;
			}

			Object.assign(opener.cj.addr.data, this.data);
			return true;
		},
		// 새벽배송 여부체크 (우편번호와 주소로 판단)
		checkDawnable : function() {
			$.ajax({
				url			: "/common/address/chkDawnDeliveryAvailable.json",
				dataType	: "json",
				type		: 'POST',
				async		: false,
				data		: {'zipCd' : this.data.zipCd, 'addr' : this.data.mnAdr},
				success		: function(data, textStatus, jqXHR){
					if(!crew.ajaxValidate(data)){ return; }
					cj.addr.data.isDawnAble = data.isDawnAble;
				},
				error	   : function(jqXHR, textStatus, errorThrown){
				  //console.log(jqXHR.status);
				}
			});
		}

	},

	locationInfo: {
		open : function(){
			//vcui.util.openPopup( '/pc/comm/addrSearchPop', 500, 600, {scrollbars:'yes'});
			vcui.util.openPopup('/pc/myInfo/locationInfoPopup', 500, 626, {scrollbars: 'yes'});
		}
	},
	// 새벽배송 안내 팝업
	dawnDeliverInfo : {
		// 새벽배송 안내
		showDeliveryInfo : function() {
			$.ajax({
				async: true,
				url: '/pc/event/dawnDeliveryInfo.json',
				dataType: 'json',
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					// 로그인 실패 메세지 처리
					if (!crew.ajaxValidate(data)) {
						return;
					}

					if($('#deliveryInfo').length > 0) {
						$("#deliveryInfo").remove();
					}
					$("body").append(data.html);
					$("#deliveryInfo").vcModal();
					$('#sidenav').removeClass('active');
				},
				error: function(jqXHR, textStatus, errorThrown){
					//console.log(jqXHR.status);
				}
			});
		},
		// 새벽배송 안내 닫기
		closeDeliveryInfo : function() {
			$(".deliveryInfo").vcModal('instance').close();
			$("#deliveryInfo").remove();
		}
	},

	// 선물하기
	gift : {
		// 선물하기 상품 상세정보
		showPrdDoc : function(prdCd) {
		    $.ajax({
		        type        : "POST",
		        url         : "/pc/prod/popup/giftProdDetailDoc.json",
		        async       : true,
		        dataType    : "json",
		        data        : {prdCd : prdCd},
		        success     : function(data, textStatus, jqXHR) {
		            if(!crew.ajaxValidate(data)){ return; }

		            $("#giftProdDocPopUp").remove();

		            $(".modal-container").append(data.html);
		            $('[data-js=modal]').modal();
		            $('[data-js=modal]').trigger("open", $("#giftProdDocPopUp"));
		            // $('[data-js=slick]').slick();
		            cjom.youtubeComponent();
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		          //  console.log(jqXHR.status);
		        }
		    });
		}
	},

	nvl : function(args, targs){
		return (cj.isEmpty(args) ? targs : args);
	},

	// 챗봇 관련 기능
	chatbot : {

		staticType : {
			TYPE : "F",
			dStatus : "COMPLETE",
			PATH : ""
		},
		Ajax : {
			Token : function(PARAM, callback) {
				$.ajax({
					type : 'GET',
					url : "/pc/comm/chatbotToken.json",
					data : PARAM,
					dataType : "json",
					success : function(data) {

						callback(data);
					},
					error : function(xhr, status, error) {

					}
				});
			},
			Data : function(url, type, Param, callback) {
				$("#urlDesc").append(url + " >>>> Param=" + JSON.stringify(Param)+ "<br/>");
				$.ajax({
					type : type,
					contentType : 'application/json; charset=UTF-8',
					url : url,
					data : JSON.stringify(Param),
					dataType : "json",
					success : function(data) {

						callback(data);
					},
					error : function(xhr, status, error) {

					}
				});
			},
			ChatBotInit : function(url) {
				switch (cj.chatbot.staticType.TYPE) {
				case "F":
					cj.chatbot.Ajax.ChatBotFrame(url);
					break;
				case "O":
					cj.chatbot.Ajax.ChatBotOpen(url);
					break;
				}
			},
			ChatBotOpen : function(url) {
				window.open(url);
			},
			ChatBotFrame : function(url) {
				//$("#urlDesc").append(url + "<br/>");
				$("#iframeChatbot").attr("src", url);
			}
		},
		ChatBotTokenCallBack : function (data) {
			if (data.status == 200) {
				var url = cj.chatbot.staticType.PATH +"?param="+ data.token
				cj.chatbot.Ajax.ChatBotInit(url);
			} else if (data.status == 401) {
				var url = cj.chatbot.staticType.PATH
				cj.chatbot.Ajax.ChatBotInit(url);
			} else {
				alert(data.status);
			}
		},
		ProductListCallBack : function (data) {
		//	console.log(data);
		},
		typeChange : function (type) {
		//	console.log(type);
			cj.chatbot.staticType.TYPE = type;

		},
		dStatusChange : function (value) {
		//	console.log(value);
			cj.chatbot.staticType.dStatus = value;

		},
		open : function(url) {
			var Param = {};
			Param.type = "OPEN";
			var protocol = "https:";	//document.location.protocol;
			cj.chatbot.staticType.PATH = protocol+url;
			cj.chatbot.Ajax.Token(Param, cj.chatbot.ChatBotTokenCallBack);

		}

	},

	// 레시피, 상품 상세설명 팝업
	prodPopup : {
		// 레시피 팝업
		receipePopup : function(prdCd, matCd){
			this.openPopup(prdCd, matCd, "prodRecipe");
		},
		// 상세 설명 팝업
		prodDetailPopup : function(prdCd, matCd){
			this.openPopup(prdCd, matCd, "prodDetail");
		},
		// 상세 설명 PC 팝업
		prodDetailIndexPopup : function(prdCd, matCd ,index){
			this.openPopupDetail(prdCd, matCd, index, "prodDetail");
		},
		openPopup : function(prdCd, matCd, popupId ) {
			if (cj.isEmpty(popupId)) {
				alert("존재하지 않는 팝업ID 입니다.");
				return false;
			}

			var matCd = matCd || "";
			vcui.util.openPopup( '/pc/prod/popup/openPopup?prdCd=' + prdCd  + '&matCd=' + matCd +'&popupId=' + popupId , 848, 820, {name:'prodRecipePopup', align:'center', scrollbars:'yes'} );

		},
		openPopupDetail : function(prdCd, matCd,index, popupId ) {
			if (cj.isEmpty(popupId)) {
				alert("존재하지 않는 팝업ID 입니다.");
				return false;
			}

			var matCd = matCd || "";
			vcui.util.openPopup( '/pc/prod/popup/openPopup?prdCd=' + prdCd  + '&matCd=' + matCd +'&index=' + index +'&popupId=' + popupId , 848, 820, {name:'prodDetailPopup', align:'center', scrollbars:'yes'} );

		}
	}

};

var snsCall = {
	urlCopy : function(e){
		var option = {};
			option.url = $(this).data("url");
			cj.share.urlCopy(option);
	}
	, twitterShare : function(){
		var option = {};
			option.text=$(this).data("text");
			option.url = $(this).data("url");
			cj.share.twitterShare(option);
	}
	, facebookShare : function(){
		var option = {};
			option.url = $(this).data("url");
	        cj.share.facebookShare(option);
	}, kakaoShare : function(){
		var option = {};
			option.text=$(this).data("text");
			option.url = $(this).data("url");
			option.image=$(this).data("image");
			option.description = $(this).data("description");
		 	cj.share.kakaoShare(option);
	}, kakaoStoryShare : function(){
		var option = {};
			option.text=$(this).data("text");
			option.url = $(this).data("url");
			cj.share.kakaoStoryShare(option);
	}
};

//이미지 리사이징을 위한 처리
//작성자 : why
var image = {
	w : 0,
	h : 0,
	init : function(item){ this.convert(item); },
	convert: function(item){
		var url = "";
		var img = new Image();
		img.src = item.url;
		//item.url = item.url + "?CG=5";
		item.url = item.url + (item.url.indexOf("?")==-1?"?":"&") + "CG=5";
		img.onload= function(){
			item.obj.src = (this.width >= this.height ? item.url + "&CS=" + this.height + "x" + this.height
						: item.url + "&CS=" + this.width + "x" + this.width);
			if(item.w > 0){
				$(item.obj).css("width", item.w).css("height", item.h);
			}
		};
	}
};

$(document).ready(function(evt){
	// 숫자 입력 처리
	$(document).on("keyup", ".onlynum2", cj.form.onlynum2);
	$(document).on("keyup", ".onlynum", cj.form.onlynum);

	//Kakao.init('4503d04cbb94c2d4f871af081a5257ae'); // 추후 config값 사용  [테스트용]

	// 공유하기 모달 오픈 ( 파라미터 값 text, url, image, description )
	// ex) data-text="텍스트"
	// df : meta:og 값

	$(".snsShareBtn").click(function(e){
		e.preventDefault();
		var option = {
				text        : $(this).data("text"),
                url         : $(this).data("url"),
                image       : $(this).data("image"),
                description : $(this).data("description")
            };
		cj.share.snsOpenModal(option.text, option.url, option.image, option.description);
	});

	cj.hist.initScrollTop();
});


