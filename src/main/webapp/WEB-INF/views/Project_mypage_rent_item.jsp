<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resources/css/Project_mypage_rent_item.css">
    <script src="../../../resources/js/Project_mypage_rent_item.js"></script>
    <script src="../../../resources/js/Project_chat_connect.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
    <main>
        <div class='wrapper'>
		<%@include file="../includes/Project_mypage_menu.jsp" %>
            <div class='myPageMain'>
                <div class='myPageTitle'><a href="#">대여중인 물건 ></a></div>
                <div class='myLeaseStatus'>
                    <ul class='leaseTitle'>
                        <li>상품번호</li>
                        <li>상품사진</li>
                        <li>상품명</li>
                        <li>대여시작일</li>
                        <li>대여종료일</li>
                        <li>대여일수</li>
                        <li>현재상태</li>
                        <li>비고</li>
                    </ul>
                    
                    <c:set var="check" value="${rentList}"/>
                    <c:choose>
                    	<c:when test="${empty check}">
                    		<ul class="listMain empty"><span>대여중인 나의 물건이 없습니다.</span></ul>
                    	</c:when>
                    	<c:otherwise>
	            			<c:forEach var="item" items="${check}" end="4">
	            				<c:set var="productId" value="${item.productId }"/>
			                    <ul class='listMain'>
			                        <li class='productIdx'><a href="../../products/${productId }">${productId}</a></li>
			                        <li class='productImage'><a href="../../products/${productId }"><img src="../../../../resources/images/${item.productImage }" alt=""></a></li>
			                        <li class='productTitle'><a href="../../products/${productId }">${item.productName }</a></li>
			                        <li class='productLeaseStartDate'><a href="../../products/${productId }">${item.productStartDate }</a></li>
			                        <li class='productLeaseEndDate'><a href="../../products/${productId }">${item.productEndDate }</a></li>
			                        <li class='productLeaseDay'><a href="../../products/${productId }">${item.productDateCount }일</a></li>
			                        <li class='productLeaseStatus'><a href="../../products/${productId }">${item.productStatus }</a></li>
			                        <li class="productLeaseChat">
			                        	<a href="#" class="chatConnect" onclick="popup('/members/${memberId}/messages/${productId }/0')">채팅</a>
			                        	<button class='completeBtn ${item.orderId }'>반납</button>
			                        </li>
			                    </ul>
	            			</c:forEach>
	            			<c:if test="${fn:length(check) > 5}">
			                    <div class="moreBox">
			                        <button class="getMore">
			                            <span>더보기</span>
			                            <svg width="0.7rem" height="0.7rem" viewBox="0 0 9 6" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M4.5 6L8.39711 0.75H0.602886L4.5 6Z" fill="currentColor"></path></svg>
			                        </button>
			                    </div>
			                </c:if>
                    	</c:otherwise>
                    
                    </c:choose>
                </div>
            </div>
        </div>
        
    </main>
</body>
<%@include file="../includes/Project_footer.jsp" %>
</html>