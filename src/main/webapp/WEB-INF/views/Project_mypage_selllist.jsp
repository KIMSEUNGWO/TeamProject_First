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
    <link rel="stylesheet" href="../../resources/css/Project_mypage_selllist.css">
    <link rel="stylesheet" href="../../resources/css/Project_mypage_selllist_modal.css">
    <script src="../../resources/js/Project_mypage_selllist_modal.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
    <main>
        <div class='wrapper'>
		<%@include file="../includes/Project_mypage_menu.jsp" %>
            <div class='myPageMain'>
                <div class="totalStatus">
                    <div class="totalBox">
                        <div>
                            <span>판매중</span>
                            <span>${sellItemInfo[0]}건</span>
                        </div>
                        <div class="orderBox"></div>
                    </div>
                    <div class="totalBox">
                        <div>
                            <span>오늘반납예정</span>
                            <span>${sellItemInfo[1]}건</span>
                        </div>
                        <div class="sellBox"></div>
                    </div>
                    <div class="totalBox">
                            <div>
                                <span>총 수익금</span>
                                <span>${sellItemInfo[2]}원</span>
                            </div>
                            <div class="myitemBox"></div>
                    </div>
                </div>
                <div class='myPageTitle'><a href="#">판매중 ></a></div>
                <div class='myLeaseStatus'>
                        <ul class='leaseTitle'>
                            <li>상품사진</li>
                            <li>상품명</li>
                            <li>가격</li>
                            <li>총 대여수</li>
                            <li>총 대여금액</li>
                            <li>현재상태</li>
                        </ul>
                        
                <c:set var="SellList" value="${itemSellList}"/>
                  	<c:choose>
                        <c:when test="${empty SellList}">
                        	<ul class="listMain empty" aria-colspan="5"><span>판매중인 물건이 없습니다.</span></ul>
                        </c:when>
                        <c:otherwise>
                     		<c:forEach var="item" items="${SellList}" end="4">
		                        <ul class='listMain'>
		                            <li class='productImage'><a href="../../products/${item.itemId }"><img src="../../resources/images/${item.itemImage }" alt=""></a></li>
		                            <li class='productTitle'><a href="../../products/${item.itemId }">${item.itemTitle}</a></li>
		                            <li class="productPrice"><a href="../../products/${item.itemId }">${item.itemPrice }원/일</a></li>
		                            <li class='productTotalCount'><a href="../../products/${item.itemId }">${item.itemTotalCount }건</a></li>
		                            <li class='productTotalBenefit'><a href="../../products/${item.itemId }">${item.itemTotalPrice }원</a></li>
		                            <c:choose>
		                            	<c:when test="${item.itemStatus eq 1}">
		                            		<li class='productStatus'><button type="button" class="wait">판매중</button></li>
		                            	</c:when>
		                            	<c:otherwise>
				                            <li class='productStatus'>
			                                <button type="button" class="rent ${item.itemId }">대여중</button>
			                            	</li>
		                            	</c:otherwise>
		                            </c:choose>
		                        </ul>
                        </c:forEach>
                        <c:if test="${fn:length(SellList) > 5 }">
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

                <div class='itemList'>
                    <div class='myPageTitle'><a href="#">판매완료 상품 ></a></div>
                    <div class='items'>
                    	
                    	<c:set var="check" value="${endItems}"/>
                    	
                    	<c:choose>
                    		<c:when test="${empty check}">
                    		     <div class='item empty'><span>상품이 없습니다.</span></div>
                    		</c:when>
                    		<c:otherwise>
                    		     <c:forEach var="endItem" items="${check}">
			                        <div class='item'>
			                            <a href="/products/${endItem.productId }">
			                                <div class='itemImage'>
			                                    <img src="../../resources/images/${endItem.images[0].storeImageName}" alt="">
			                                </div>
			                                <button id = "image-text">판매완료</button>
			                                <div class='itemTitle'>${endItem.productName }</div>
			                                <div class='itemPrice'>
			                                    <div class='price'>${endItem.productPrice}원</div>
			                                    <div class='price_day'>/일</div>
			                                </div>
			                            </a>
			                        </div>
		                        </c:forEach>
                    		
                    		</c:otherwise>
                    	
                    	</c:choose>
                    
 
                        
                        
                    </div>
                </div>

            </div>
        </div>
        
    </main>
</body>
<%@include file="../includes/Project_footer.jsp" %>

</html>