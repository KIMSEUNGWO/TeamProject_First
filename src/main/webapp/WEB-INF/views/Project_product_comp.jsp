<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../../resources/css/Project_product_comp.css">
</head>
<%@include file="../includes/Project_header.jsp" %>
<body> 
     
     <main>
     	<div class = "comp-box">
     		<div class = "box-content">
     			<p>예약이 완료 되었습니다.</p>
     			
     			<div id = "home-button-box">
     			<button type = "button" id = "home" onclick="location.href='/'">홈</button>
     			<button type = "button" id = "reserve-list" onclick="location.href='../../../../members/${memberId}/orderlist'">예약목록</button>
     			</div>
     		</div>
     	</div>
     
     </main>  
        
    
</body>
    <%@include file="../includes/Project_footer.jsp" %>
</html>