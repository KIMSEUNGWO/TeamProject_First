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
    <link rel="stylesheet" href="../../resources/css/Project_list.css">
    <script src="../../resources/js/Project_list.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
    <main>
        <div id='contents' class='contents'>
			<div class='ctegorybox'>
                <!-- 카테고리 or 검색어 , 총 수량 -->
                <div id='categorytitle' style=' display: flex;'>
                    <p id='ctegoryT' >${searchTitle }</p>
                    <p id='numT' >(총${getCount}개 상품)</p>
                </div>
                <hr>
                <!-- 인기도순 최신등록순 -->
            </div>
			<nav>
			    <ul class='smallmenu'>
			        <li class='sortType' ><a class='actA' href='#' id="popular">인기도순</a></li>
			        <li class='sortType' ><a class='actA' href='#' id="recently">최신등록순</a></li>
			        <li class='sortType' ><a class='actA' href='#' id="rowPrice">낮은가격순</a></li>
			        <li class='sortType' ><a class='actA' href='#' id="highPrice">높은가격순</a></li>
			    </ul>
			<!--                     <ul class='smallmenu'>
			                        <li class='sortType'>
			                            <a class='' href='#'>상태별보기<span>∨</span></a>
			                            서브메뉴
			                            <ul class='submenu'>
			                                <li><a href="#">새상품</a></li>
			                                <li><a href="#">상태A</a></li>
			                                <li><a href="#">상태B</a></li>
			                            </ul>
			                        </li>
			                    </ul> -->
			</nav>
            <br>
         <div id='categoryProduct' class='categoryProduct'>
    			<div style='padding-top: 1.3em;'>
    				<div class="productList">
	               		<c:forEach items="${getList}" var="item" end='11'>
	                		<ul class='product'>
					            <a href='/products/${item.productId}'>
				                    <li class='productImage'><img class='thumbnail' src="../../../../resources/images/${item.storeImageName}" alt="${item.productName} 이미지"></li>
				                    <li class='productName'><p class='product_title'>${item.productName }</p></li>
				                    <li class='productPrice'><p class='product_price'>${item.productPrice}원/일</p></li>
					            </a>
			                </ul>
	        			</c:forEach>
				        <c:if test="${fn:length(getList) > 12}">
				           <div class="moreBox">
				               <button class="getMore">
				                   <span>더보기</span>
				                   <svg width="0.7rem" height="0.7rem" viewBox="0 0 9 6" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path d="M4.5 6L8.39711 0.75H0.602886L4.5 6Z" fill="currentColor"></path></svg>
				               </button>
				           </div>
				         </c:if>
					</div>
        	</div>
    </main>
   <%@include file="../includes/Project_footer.jsp" %>
</body>
</html></html>