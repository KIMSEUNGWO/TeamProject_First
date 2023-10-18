<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="../../resources/css/Project_mypage_main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="../../resources/js/Project_mypage_main.js"></script>
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
    <main>
        <div class='wrapper'>
		<%@include file="../includes/Project_mypage_menu.jsp" %>
            <div class='myPageMain'>

                <div class="totalStatus">
                    <div class="totalBox">
                        <a href="#">
                            <div>
                                <span>대여건수</span>
                                <span>${compressInfo[0]}건</span>
                            </div>
                            <div class="orderBox"></div>
                        </a>
                    </div>
                    <div class="totalBox">
                        <a href="#">
                            <div>
                                <span>판매건수</span>
                                <span>${compressInfo[1]}건</span>
                            </div>
                            <div class="sellBox"></div>
                        </a>
                    </div>
                    <div class="totalBox">
                        <a href="#">
                            <div>
                                <span>내 물건</span>
                                <span>${compressInfo[2]}건</span>
                            </div>
                            <div class="myitemBox"></div>
                        </a>
                    </div>
                    <div class="totalBox">
                        <a href="#">
                            <div>
                                <span>대여대기</span>
                                <span>${compressInfo[3]}건</span>
                            </div>
                            <div class="orderlistBox"></div>
                        </a>
                    </div>
                </div>
                <div class='myPageTitle'><a href="#">현재 대여 요청 ></a></div>
                <div class='myLeaseStatus'>
                    <ul class='leaseTitle'>
                        <li>상품번호</li>
                        <li>상품사진</li>
                        <li>상품명</li>
                        <li>대여시작일</li>
                        <li>대여종료일</li>
                        <li>대여일수</li>
                        <li>신청인</li>
                        <li>비고</li>
                    </ul>
                    <c:set var="check" value="${confirmInfo}"/>
                    <c:choose>
                    	<c:when test="${empty check}">
                    		<ul class="listMain empty"><span>대여요청이 없습니다.</span></ul>
                    	</c:when>
                    	<c:otherwise>
		                    	<c:forEach var="item" items="${check}" end="4">
		                    			<c:set var="productId" value="${item.productId}"/>
				                    	 <ul class='listMain'>
					                        <li class='productIdx'><a href="../../products/${productId }">${productId}</a></li>
					                        <li class='productImage'><a href="../../products/${productId}"><img src="../../resources/images/${item.productImage}" alt=""></a></li>
					                        <li class='productTitle'><a href="../../products/${productId }">${item.productName}</a></li>
					                        <li class='productLeaseStartDate'><a href="../../products/${productId }">${item.startDate}</a></li>
					                        <li class='productLeaseEndDate'><a href="../../products/${productId }">${item.endDate}</a></li>
					                        <li class='productLeaseDay'><a href="../../products/${productId }">${item.countDate}일</a></li>
					                        <li class='productLeaseStatus'><a href="../../products/${productId }">${item.memberName }</a></li>
					                        <li class='productLeaseConfirm'>
					                        	<c:choose>
					                        		<c:when test="${item.orderConfirmStatus == 2}">
							                            <button class='confirmBtn ${item.orderId }'>확정</button>
							                            <button class='cancelBtn ${item.orderId }'>거절</button>					                        		
					                        		</c:when>
					                        		<c:otherwise>
					                        			<button class='chatBtn ${item.orderId }' onclick="popup('/members/${memberId}/messages/${item.productId }/0')">쪽지</button>
					                        			<button class='sendBtn ${item.orderId }'>승인</button>
					                        		</c:otherwise>
					                        	</c:choose>
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