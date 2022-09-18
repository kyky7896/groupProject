/**
 *  쇼핑몰 추가 js관련 페이지 입니다. - 백성진 - 
 */
// 쇼핑몰 주소검색 시작 

/* ----------------------------------(★★)추가추가추가추가------------------------------------------------------ */
// 상품 등록시 빈칸이 없도록 제어하는 제어문이 있습니다. 
	function formCheck(){
		
		
		/* 유효성 검사 */
		
		var fr = document.fr;
		
		
		// 카테고리
		if(fr.f_gs_cate[0].checked == false && fr.f_gs_cate[1].checked == false && fr.f_gs_cate[2].checked == false){
			
			alert('카테고리를 설정해 주세요');
			fr.f_gs_cate[0].focus();
			return false;
		} 
		
		
		// 상품이름
 		if(fr.f_gs_name.value == '' ){
			alert('상품 이름을 입력해 주세요');
			fr.f_gs_name.focus();
			return false;
		}
		
		
		// 가격 & 숫자만 입력되도록 제어
		if(fr.f_gs_price.value == '' ){
			alert('가격을 입력해 주세요');
			fr.f_gs_price.focus();
			return false;
		}else{

			var num_check1 = /^[0-9]*$/;
			
			if(!num_check1.test(fr.f_gs_price.value)){ // test() : 입력한 값이 정규식과 일치하면 true반환
				
				alert("숫자만 입력할 수 있습니다.");
				
				fr.f_gs_price.value = "" // 빈 칸으로 만들기
				fr.f_gs_price.focus();
				return false;
			
			}
		}
		
			
		// 재고 수량 & 숫자만 입력되도록 제어
		if(fr.f_gs_amount.value == '' ){
			alert('판매할 수량을 입력해 주세요');
			fr.f_gs_amount.focus();
			return false;
			
		}else{

			var num_check2 = /^[0-9]*$/;
			
			if(!num_check2.test(fr.f_gs_amount.value)){

				alert ( "숫자만 입력할 수 있습니다." );
				fr.f_gs_amount.value = ""
				fr.f_gs_amount.focus();
				return false;

			}
		}
		
		
		// 상세 내용
		if(fr.f_gs_msg.value == '' ){
			alert('상세내용을 작성해 주세요');
			fr.f_gs_msg.focus();
			return false;
		}
		
		// 메인 이미지 등록 제어 (이미지까지 제어해야 할 필요 있을까?)
		if(fr.file1.value == ''){
			alert('이미지를 등록해 주세요');
			fr.file1.focus();
			return false;
		}
		
	}

/* ----------------------------------(★★)추가추가추가추가------------------------------------------------------ */
/* ---------------------------------(★★★시작) 추가추가추가추가------------------------------------------------------ */

	// 상품정보 수정에 관한 제어문 입니다. -백성진 - 
	function ckForm(){
		
		/* 유효성 검사 */
		
		var fr = document.fr;

	// 상품이름
	if(fr.f_gs_name.value == '' ){
		alert('상품 이름을 입력해 주세요');
		fr.f_gs_name.focus();
		return false;
	}
		
	
	// 가격 & 숫자만 입력되도록 제어
	if(fr.f_gs_price.value == '' ){
		alert('가격을 입력해 주세요');
		fr.f_gs_price.focus();
		return false;
	}else{

		var num_check1 = /^[0-9]*$/;
		
		if(!num_check1.test(fr.f_gs_price.value)){ // test() : 입력한 값이 정규식과 일치하면 true반환
			
			alert("숫자만 입력할 수 있습니다.");
			
			fr.f_gs_price.value = "" // 빈 칸으로 만들기
			fr.f_gs_price.focus();
			return false;
		
		}
	}
	
		
	// 재고 수량 & 숫자만 입력되도록 제어
	if(fr.f_gs_amount.value == '' ){
		alert('판매할 수량을 입력해 주세요');
		fr.f_gs_amount.focus();
		return false;
		
	}else{

		var num_check2 = /^[0-9]*$/;
		
		if(!num_check2.test(fr.f_gs_amount.value)){

			alert ( "숫자만 입력할 수 있습니다." );
			fr.f_gs_amount.value = ""
			fr.f_gs_amount.focus();
			return false;
		}
	}
	
	
	// 상세 내용
	if(fr.f_gs_msg.value == '' ){
		alert('상세내용을 작성해 주세요');
		fr.f_gs_msg.focus();
		return false;
	}
	
	// 메인 이미지 등록 제어 (이미지까지 제어해야 할 필요 있을까?)
	if(fr.file1.value == ''){
		alert('이미지를 등록해 주세요');
		fr.file1.focus();
		return false;
	}
		
	}

/* ----------------------------------(★★★끝)추가추가추가추가------------------------------------------------------ */