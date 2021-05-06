<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="com.order.db.OrderBean"%>
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
     //request.setAttribute("orderDetailList", ordao.orderDetail(trade_num));
   
     List orderDetailList 
          = (List) request.getAttribute("orderDetailList");
     
     int total=0; // 총합 금액
        
   %>
   <h2 style="text-align: center;"> 주문내역 상세보기 </h2>
   <br>

   <table border="1">
      <tr>
        <td>주문번호</td>
        <td>주문일자</td>
        <td>상품명</td>
        <td>주문 수량</td>
        <td>주문 금액</td>
      </tr>
      
      <%for(int i=0;i<orderDetailList.size();i++){
    	    OrderBean ob =(OrderBean) orderDetailList.get(i);
    	    total += ob.getF_or_sum();
    	  %>
	      <tr>	        	      
	        <td><%=ob.getF_or_trade_num() %></td>
	        <td><%=ob.getF_or_date() %></td>
	        <td><%=ob.getF_or_gs_name() %></td>
	        <td><%=ob.getF_or_bas_amount() %></td>
	        <td><%=ob.getF_or_sum() %></td>
	      </tr>
      <%} %>
   </table>
	<br>
	
      <%for(int i=0;i<orderDetailList.size();i++){
    	    OrderBean ob =(OrderBean) orderDetailList.get(i);
    	  %>
   
   <h3> 받는 사람 : <%=ob.getF_or_rec_name() %></h3><br>
   <h3> 주문 받으실 주소 : <%=ob.getF_or_rec_postcode() %>, 
   					<%=ob.getF_or_rec_address() %>
   					<%=ob.getF_or_rec_detailAddress() %>
   					<%=ob.getF_or_rec_extraAddress() %></h3><br>

   <h3> 배송 메세지 : <%=ob.getF_or_memo() %></h3><br>
   					
   		<%
   		break;
      } %>			
   					
   <h3> 총 주문 금액 : <%=total %>원</h3>
   
   <hr><hr>
   
<a href="#" onclick="javascript:window.close()">[이전 페이지]</a>

  <a href="#" onclick="opener.location.href='./GoodsList.go';self.close()">[쇼핑몰로 돌아가기]</a>	 	
   
   
</body>
</html>