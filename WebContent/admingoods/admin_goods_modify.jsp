<%@page import="com.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>
</head>
<body>
   
   <h1 align="center"> 관리자 상품 수정  </h1>
  
   <%
// (★★시작)추가 : 관리자 id로 로그인 X -> 로그인 페이지로 이동하도록 제어 
	   String f_mem_id = (String)session.getAttribute("f_mem_id"); // MemberLoginAction.java에서 session영역에 저장된 id의 key에 해당되는 이름 : f_mem_id
		if(f_mem_id != null && !(f_mem_id.equals("admin"))){
			response.sendRedirect("./MemberLogin.me");//로그인 페이지로 이동
		}
// (★★끝)추가 : 관리자 id로 로그인 X -> 로그인 페이지로 이동하도록 제어 
   
     GoodsBean gb = (GoodsBean) request.getAttribute("goodsbean");
   
   %>   
<!-- (★★★) 수정완료 : form태그 속성으로 name="fr" onsubmit="return chForm(); " 추가 -->
   <form action="./AdminGoodsModifyProAction.ag" method="post" name="fr" onsubmit="return ckForm();">
      <input type="hidden" id="f_gs_num" name="f_gs_num" value="<%=gb.getF_gs_num()%>">    
   
     <table border="1" style="margin-left: auto; margin-right: auto;">
        <tr>
	        <td>카테고리</td>
	        <td>
	          <select name="f_gs_cate">
	           <option value="meal" 
	           <%if(gb.getF_gs_cate().equals("meal")){ %>
	           selected
	           <%} %>
	           >밀키트</option>
	           <option value="veg"
	             <%if(gb.getF_gs_cate().equals("veg")){ %>
	           selected
	           <%} %>
	           >식재료</option>
	           <option value="tool"
	             <%if(gb.getF_gs_cate().equals("tool")){ %>
	           selected
	           <%} %>
	           >주방용품</option>
	           
	          </select>
	        </td>
        </tr>
        <tr>
	        <td>상품명</td>
	        <td>
	          <input type="text" name="f_gs_name" value="<%=gb.getF_gs_name()%>">
	        </td>
        </tr>
        <tr>
	        <td>판매가</td>
	        <td>
<!-- (★★★) 수정완료 : type="text"->"number"로 변경, min="0" 추가(마이너스 값 입력 불가하도록 제어)  -->
	          <input type="number" name="f_gs_price" min="0" value="<%=gb.getF_gs_price()%>">
	        </td>
        </tr>
        
        <tr>
	        <td>수량</td>
	        <td>
<!-- (★★★) 수정완료 : type="text"->"number"로 변경, min="0" 추가(마이너스 값 입력 불가하도록 제어)  -->
	          <input type="number" name="f_gs_amount" min="0" value="<%=gb.getF_gs_amount()%>">
	        </td>
        </tr>
        <tr>
	        <td>제품정보</td>
	        <td>
<!-- (★★★) 수정완료 : <input type="text"> -> <textarea>로 변경  -->
	          <textarea rows="20" cols="30" name="f_gs_msg"><%=gb.getF_gs_msg()%></textarea>
	        </td>
        </tr>
        
 <%--        <tr>
          <td>인기상품</td>
          <td>
             <input type="radio" name="f_gs_hit" value="1" 
               <%if(gb.getF_gs_hit() == 1){ %>
                checked
               <%} %>
             >예 
             <input type="radio" name="f_gs_hit" value="0" 
                <%if(gb.getF_gs_hit() == 0){ %>
                checked
               <%} %>             
             >아니오             
          </td>
        </tr> --%>
        
        <tr>
	        <td colspan="2">
	          <input type="submit" style="width: 70px; height: 35px;" value="정보 수정">
	          <input type="reset" style="width: 70px; height: 35px;" value="초기화">
	          <input type="button" style="width: 70px; height: 35px;" value=" 창닫기" onClick='self.close()'>
	        </td>
        </tr>
     </table>  
   </form>
   
  <script src="./js/shoppingJS/admin_goods.js" ></script> 
   
   


</body>
</html>