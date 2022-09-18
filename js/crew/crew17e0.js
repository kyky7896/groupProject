/******************************************************************************
	작성자 : 김기석
	작성일 : 2012.02.07
	기능 : 공통 스크립트 처리
	참고사항 :
******************************************************************************/
var crew = {
	// 서버측으로부터 전달받은 JSON 오브젝트에 에러메세지가 있는 경우 alert를 발생시키고 false를 리턴 시킴
	ajaxValidate : function(result, isAlert){
		var isAlert = typeof(isAlert) == "boolean" ? isAlert : true;
//		console.debug(result);
		if(typeof(loadingBarStop) == 'function'){ loadingBarStop(); }; 
		
		if(result.resultCode && result.resultCode != 1){
			// 인증 오류인 경우의 처리
			if(result.loginUrl && result.redirectUrl){
				cj.login(location.pathname + location.search, "N", true);
				return false;
			}
			
			
			// message에 정보가 존재하는 경우
			if(result.message){
				if(isAlert){ alert(result.message); }
				return false;
			}
			
			// messageList에 정보가 존재하는 경우
			if(result.messageList){
				if(isAlert){ alert(result.messageList.join("\n")); }
				return false;
			}
			
			// 메세지가 존재하지 않는 경우
			if(isAlert){
				alert("일시적으로 문제가 발생하여 잠시 후 다시 시도해주세요.");
				return false;
			}
			
			
			// 모든 메세지가 없는 경우에 대한 처리
			return false;
		}

		return true;
	}, 
	
	// guid를 생성함.
	guid : function(){
		var ran = function () {
			return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
		};

		var guid = (ran() + ran() + "-" + ran() + "-" + ran() + "-" + ran() + "-" + ran() + ran() + ran()).toUpperCase();
		return guid;
	}, 


	
	// 엔터값 체크후 해당 콜백함수 호출
	checkEnter : function(jqObj, callback){
		jqObj.bind("keydown", function(e){
			if(e.keyCode == "13"){
				callback();
			}
		});
	}, 
	
	
	
	
	//-----------------------------------------------------------//
	
	

	// 스크롤바 하단 체크후 해당 영역의 하단보다 화면이 아래에 위치하면 해당 콜백 함수 호출 처리
	//	- <!doctype html> 이거 반드시 선어되어 있어야 함.
	checkScrollBottom : function(area, callback){
		if($(area).length){
			$(window).scroll(function(){
				var windowBottom 	= $(window).height() + $(window).scrollTop();
				var areaBottom		= $(area).offset().top + $(area).height();
				var option			= $(area).data("option");
				
				// 스크롤바의 하단이 체크영역보다 아래로 내려가는 경우 처리
				if(windowBottom >= areaBottom && option.requestStat != "STOP"){
					callback(area);
			    }
			});
			
			
			// 스크롤바의 최초의 위치가 체크영역보다 아래로 내려가는 경우 1차 요청 처리
			$(document).ready(function(evt){
				var windowBottom 	= $(window).height() + $(window).scrollTop();
				var areaBottom		= $(area).offset().top + $(area).height();
				var option			= $(area).data("option");
				
				// 스크롤바의 하단이 체크영역보다 아래로 내려가는 경우 처리
				if(windowBottom >= areaBottom && option.requestStat != "STOP"){
					callback(area);
			    }
			});
		}
	}, 
	
	

	// 스크롤바 하단에 위치한 경우 해당 경로로 요청을 보냄
	requestScrollBottom : function(area, option){
		// 기본값 셋팅
		var defaults = {
			url			: "", 
			dataType	: "JSON", 
			callback	: function(){},
			requestStat	: "WAIT", 
			param		: { page : 1 }
		}
		
		option = $.extend(true, defaults, option);
		$(area).data("option", option);

		// 추적시작
		crew.checkScrollBottom(area, crew.requestScrollBottomCallBack);
	}, 

	// 스크롤바 하단에 위치한 경우 경로 요청을 중단함.	
	requestScrollBottomStop : function(area, option){
		var option = $(area).data("option");
		option.requestStat = "STOP";
		$(area).data("option", option);
	}, 	
	
	// 스크롤바 하단에 위치한 경우 해당 경로로 요청을 보냄후 콜백 처리
	requestScrollBottomCallBack : function(area){
		var option		= $(area).data("option");
		
		// 중복 요청 방지 처리 및 페이지 처리
		if(option.requestStat == "REQUEST"){ return; }
		if(option.requestStat == "STOP"){ return; } // 더이상 요청할 데이터가 없는경우.
		
		option.requestStat = "REQUEST";
		option.param.page++;
		$(area).data("OPTION", option);
		
		// 요청처리
		$.ajax({
	        url			: option.url,
	        cache		: false,
	        dataType	: option.dataType, 
	        async		: true,
			type		: "POST", 
			data		: option.param,
			beforeSend	: function(){ },
		    complete	: function(){ },
	        success		: function(data, textStatus, jqXHR){
	        	// 요청상태 처리
	    		option.requestStat = "WAIT";
	    		$(area).data("option", option);
	        	
	        	// 콜백처리
	        	option.callback(area, data);
	        },
	        error: function(data, textStatus, jqXHR){
				console.debug("---------------------------------> error!!");
				console.debug(textStatus);
				console.debug(data);

	        	alert("error!!");
			}
        });
	} 
};



// object를 query문자열로 변환하여 반환함.
function objectToQuery(obj, skip){
	var rv	= [];
	var skipList = typeof(skip) == "undefined" ? [] : skip.split(/,/gi);
	
	for(var key in obj){
		// skip여부를 판단함.
		var isAdd = true;
		for(var i=0 ; i < skipList ; i++){
			if(skipList[i] == key){
				isAdd = false;
				break;
			}
		}
		
		if(isAdd){
			rv.push("&");
			rv.push(key);
			rv.push("=");
			rv.push(obj[key]);
		}
	}
	
	return rv.join("").substring(1);
}