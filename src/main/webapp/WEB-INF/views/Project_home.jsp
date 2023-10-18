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
    <link rel="stylesheet" href="../../resources/css/Project_home.css">
    <script src="../../resources/js/Project_home.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
<main>
	<div id='contents' class='contents'>

		<div id='banner'>
		    <div class="arrow" id="left">&lang;</div>
		    <div class="arrow" id="right">&rang;</div>
		</div>
		<div id='latestProduct' class='latestProduct'>
			<div> <p class='categoryT' >인기상품</p> </div>
              <!-- 한달동안 대여가 많이 된 인기 상품 리스트 -->
			<div style='padding-top: 1.3em;'>
				<c:forEach items="${popularList}" var="item" begin="0" end="3">
					<ul class='product' >
	                   <a href='/products/${item.productId}'>
							<li class='productImage'><img class='thumbnail' src="../../../../resources/images/${item.storeImageName}" alt="${item.productName} 이미지"></li>
		                    <li class='productName'><p class='product_title'>${item.productName }</p></li>
		                    <li class='productPrice'><p class='product_price'>${item.productPrice}원/일</p></li>
	                   </a>
					</ul>
				</c:forEach>    
			</div>
		</div>

        <div id='categoryProduct' class='categoryProduct'>
		    <div> <p class='categoryT'>전체상품</p> </div>
	    	<!-- 전체 상품 리스트 -->    
	   		<div style='padding-top: 1.3em;'>
	    		<div class="productList">
			        <c:forEach items="${items }" var="item" end="11">
		                <ul class='product'>
				            <a href='/products/${item.productId}'>
			                    <li class='productImage'><img class='thumbnail' src="../../../../resources/images/${item.storeImageName}" alt="${item.productName} 이미지"></li>
			                    <li class='productName'><p class='product_title'>${item.productName }</p></li>
			                    <li class='productPrice'><p class='product_price'>${item.productPrice}원/일</p></li>
				            </a>
		                </ul>
			        </c:forEach>
			        <c:if test="${fn:length(items) > 11}">
			           <div class="moreBox">
			               <button class="getMore">
			                   <span>더보기</span>
			                   <svg width="0.7rem" height="0.7rem" viewBox="0 0 9 6" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M4.5 6L8.39711 0.75H0.602886L4.5 6Z" fill="currentColor"></path></svg>
			               </button>
			           </div>
			        </c:if>
		    	</div>
			</div>
		</div>
	</div>
</main>
</body>
<%@include file="../includes/Project_footer.jsp" %>
</html>