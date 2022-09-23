## 프로젝트 라따뚜이 : 레시피 & 맛집 프로젝트 


## ERD : <br>

![image](https://user-images.githubusercontent.com/77670592/191951966-2ee1fcef-277e-48cc-8583-d985cfe3eee1.png)  

![image](https://user-images.githubusercontent.com/77670592/191952066-6fbbd086-9b66-42f9-a141-b760753e63f1.png)


### 주요 기능 소개
회원가입 전에는 전체적인 내용은 확인 가능 하지만 작성은 회원으로 로그인하여 작성되게 제어하고 있습니다 
레시피 맛집 쇼핑 내역을 보여주며 작성시 로그인 하라고 경고창 발생되거나 작성 버튼이 안보입니다.

1. 회원정보 가입
- 회원가입
- 로그인
- ( 아이디 찾기 메일로 인증코드 전송 되어서 전송된 정보를 확인 하실수 있습니다.)
- ( 비밀번호 찾기 메일로 인증코드 전송 되어서 전송된 정보를 확인 하실수 있습니다.)

<br>

2. 로그인후 사용
- my page -> 회원 정보 수정은 여기서 수정가능하고 비밀번호가 동일해야 비밀번호 일치하다고 멘트가 보이면서 수정한 정보가 반영 됩니다.
주소검색은 다음 주소 를 사용하였고 api ,닉네임 정보를 수정가능하지만 중복된 닉네임은 입력이 안되게 제어하였습니다.
- 회원 탈퇴를 누르면 해당 id에 작성 된 모든 리뷰와 레시피 등록 내역이 삭제됩니다.

<br>

3. 레시피 등록
- 회원으로 로그인해야 레시피 작성 아이콘이 보입니다
- 레시피 등록시 이미지와 글 제목 요리소개 카테고리별 선택을 후 추가 재료와 요리순서를 등록합니다 
- 추가 정보를 넣고 싶으면 추가 버튼을 클릭하여 작성 하시면 되고 요리순서에는 이미지와 글 내용이 함께 작성되도록 제어 하였습니다.
- 해시태그는 글 작성시 작성하면 등록이 가능합니다
- 요리 등록을 하게 되면 레시피 리스트에서 내가 작성한 레시피 목록이 확인 가능합니다.
- 이미지를 클릭하면 내가 작성한 레시피가 수정 가능 하고 추가로 댓글을 작성할 수 있는 공간을 만들어서 
내가 작성한 레시피 목록 말고도 상대방이 작성한 레시피에 대한 나의 생각을 작성할수 있게 작업하였습니다.

[![레시피 글쓰기 수정 삭제 + 쇼핑몰 목록 디테일](https://img.youtube.com/vi/7wkH7P1ZbGk/0.jpg)](https://www.youtube.com/watch?v=7wkH7P1ZbGk?t=0s)

<br>

4. 맛집
- 맛집 정보는 공공 데이터에서 부산에 있는 맛집 정보 데이터를 파싱하여 각 테마별 맛집 리스트 정보를 가져오게 하였습니다.
- 맛집 이미지를 클릭하면 대표 이미지와 맛집에 관한 정보와 함께 해당위치에 대한 정보를 지도로 보여주게 됩니다.
- 리뷰쓰기에서 별점을 부여한 후 그 맛집 점수를 합산하여 상단에 맛집점수가 부여됩니다.
- 그리고 검색어를 등록하여 (Ex 국밥 검색시) 맛집 인기 순위와 함께 해당 지역의 맛집 지도 정보를 한눈에 볼 수 있습니다

<br>

5. 쇼핑
- 쇼핑몰은 밀키트, 식재료, 주방용품,에 따라서 물건을 구매할수 있도록 제어 하였고 상품 등록은 관리자 통해서만 상품을 등록하게 하였습니다.
- 쇼핑에서 구매신청한 내역인 장바구니는 상단에 장바구니 버튼을 클릭하면 장바구니에 등록된 상품목록을 확인이 가능한데
회원으로 가입된 회원들에게만 장바구니 정보를 확인 하실 수 있도록 하였습니다.
- 본인이 원하는 상품을 등록하면 장바구니에 상품을 담아서 구매하기를눌러야 구매 상세 페이지로 이동이 됩니다.
- 이동된 상품 정보 페이지에서 상품을 구매하고 상품 주문한 내역은 마이페이지에서 확인이 가능합니다.

<br>

6. 마이페이지 추가
- 지금까지 작성된 레시피와 내가 작성한 맛집 리뷰 쇼핑몰 주문목록을 추가로 확인 가능함.
- 관리자로 로그인 하게 되면 추가 내용인 쇼핑몰 상품등록, 관리자 상품 목록 리스트, 회원목록, 사용자 주문 목록과 함께 주문한 내역이
소비자에게 배송중인지를 입력하여 소비자가 주문한 상품의 주문상태를 확인하실 수가 있습니다.

<br>


7. 기타 추가 기능
- 기타 추가 기능으로는 관리자에게 하고 싶은말을 메일로 전달할 수 있도록 하였는데 관리자에게 불만사항이나 하고 싶은 말이 있다면
메일 주소와 내용을 작성하여 메일을 보내면 보낸 메일이 관리자로 등록된 메일로 가도록 만들었습니다.
(=> 유진씨에게 등록된 메일 주소를 받아야 합니다 구글 메일 주소와 비
번이 필요합니다. )
- 그외 고객 행복 센터를 만들어 소비자가 많이 물어볼 수 있는 정보를 Q&A로 만들어서 문의사항을 정리하였습니다
