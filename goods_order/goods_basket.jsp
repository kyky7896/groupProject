<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="com.basket.db.BasketBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라따뚜이</title>

<link rel="stylesheet" type="text/css" href="./cjkit/web/css/common.css">

</head>
<body>
<%
String id = (String) session.getAttribute("f_mem_id");
if(id==null){
	response.sendRedirect("./MemberLogin.me");
}

List basketList = (List)request.getAttribute("basketList");
List goodsList = (List)request.getAttribute("goodsList");
%>
<h2 style="text-align: center;">장바구니</h2>
  <table border="1">
    <tr>
      <td>번호</td>
      <td>사진</td>
      <td>제품명</td>
      <td>수량</td>
      <td>가격</td>
      <td>취소</td>
    </tr>
    <%
    for(int i=0; i<basketList.size(); i++){ 
      BasketBean basb = (BasketBean)basketList.get(i);
      GoodsBean gb = (GoodsBean)goodsList.get(i);
    %>
    <tr>
      <td><%=i+1 %></td>
      <td>
        <img src="./upload/<%=gb.getF_gs_img().split(",")[0] %>" width="50" height="50">
      </td>
      <td><%=gb.getF_gs_name() %></td>
      <td><%=basb.getF_bas_amount() %></td>
      <td><%=gb.getF_gs_price() %></td>
      <td>
        <a href="./BasketDelete.ba?f_bas_num=<%=basb.getF_bas_num() %>">취소</a>
      </td>
    </tr>
    <%} %>
  </table>
  
  <%
 	if (basketList.size() == 0){
 	
 	%>
 	<script type="text/javascript">
 		alert("장바구니에 상품이 없습니다!");
 		window.close(); 		
 	</script>
 	
 	<%
 		} 
	 %>
 
 <a href="#" onclick="opener.location.href='./OrderStart.or';self.close()">[구매하기]</a>

  <a href="#" onclick="opener.location.href='./GoodsList.go';self.close()">[계속쇼핑하기]</a>
  
</body>
</html>