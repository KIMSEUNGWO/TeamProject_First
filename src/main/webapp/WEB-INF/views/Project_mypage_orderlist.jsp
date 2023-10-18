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
    <link rel="stylesheet" href="../../resources/css/Project_mypage_orderlist.css">
    <script src="../../resources/js/Project_chat_connect.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
    <main>
        <div class='wrapper'>
		<%@include file="../includes/Project_mypage_menu.jsp" %>
            <div class='myPageMain'>
                <div class='myPageTitle'><a href="#">나의 구매 내역 ></a></div>
                <div class='myLeaseStatus'>
                    <ul class='leaseTitle'>
                        <li>상품번호</li>
                        <li>상품사진</li>
                        <li>상품명</li>
                        <li>대여시작일</li>
                        <li>대여종료일</li>
                        <li>대여일수</li>
                        <li>판매자</li>
                        <li>현재상태</li>
                        <li>비고</li>
                    </ul>
                    
                    <c:set var="check" value="${orderItems}"/>
                    <c:choose>
                    	<c:when test="${empty check}">
                    		<ul class="listMain empty"><span>구매내역이 없습니다.</span></ul>
                    	</c:when>
                    	<c:otherwise>
	            			<c:forEach var="item" items="${check}" end="4">
	            				<c:set var="productId" value="${item.productId }"/>
			                    <ul class='listMain'>
			                        <li class='productIdx'><a href="../../products/${productId }">${productId}</a></li>
			                        <li class='productImage'><a href="../../products/${productId }"><img src="../../resources/images/${item.productImage }" alt=""></a></li>
			                        <li class='productTitle'><a href="../../products/${productId }">${item.productName }</a></li>
			                        <li class='productLeaseStartDate'><a href="../../products/${productId }">${item.startDate }</a></li>
			                        <li class='productLeaseEndDate'><a href="../../products/${productId }">${item.endDate }</a></li>
			                        <li class='productLeaseDay'><a href="../../products/${productId }">${item.rentDate }일</a></li>
			                        <li class='productLeaseSeller'><a href="../../products/${productId }">${item.sellerName }</a></li>
			                        <li class='productLeaseStatus'><a href="../../products/${productId }">${item.productStatus }</a></li>
			                        <li class="productLeaseChat">
			                        	<c:if test="${item.productStatus eq '대여확정'}">
			                        		<a href="#" class="chatConnect" onclick="popup('/members/${memberId}/messages/${item.productId }/0')">연락하기</a>
			                        	</c:if>
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
<script type="text/javascript">
	let getmore = document.querySelector(".getMore");
	var startRow = 0;
	var endRow = 0;
	getmore.addEventListener('click', function(){
		var list_length = $(".listMain").length;
		
		startRow = list_length + 1;
		endRow = startRow + 5;
		var moreDate = {
			startDate : startRow,
			endDate : endRow
		}
		
		$.ajax({
			type: "GET",
			url : window.location.pathname + "/order",
			data : moreDate,
			contentType : "application/json",
			success : function(response){
				getMoreList(response);
			},
			error : function(error){
			}
		})
		
		
		
		
	})
	
	function popup(url) {
		const name = "MyMessageList";
	    const x = window.screen.width-600;
	    const y = window.screen.height-100;
	    let option = 'width = 600, height = 712, left = '+x+', top = '+y+', location=no';
		
		window.open(url, name, option);
	}
	
	function getMoreList(list){
		
		var locate = document.querySelector(".myLeaseStatus");
		var moreBox = document.querySelector(".moreBox");
		var length = list.length;
		//console.log(length)
		for (var i=0;i<length;i++){
			var result = list[i];
			var content = '';
			if (result.productId != ''){
			
	           content += "<li class='productIdx'><a href='../../products/" + result.productId + "'>" + result.productId + "</a></li>"
	           content += "<li class='productImage'><a href='../../products/" + result.productId + "'><img src='../../resources/images/" + result.productImage + "' alt=''></a></li>"
	           content += "<li class='productTitle'><a href='../../products/" + result.productId + "'>" + result.productName + "</a></li>"
	           content += "<li class='productLeaseStartDate'><a href='../../products/" + result.productId + "'>" + result.startDate + "</a></li>"
	           content += "<li class='productLeaseEndDate'><a href='../../products/" + result.productId + "'>" + result.endDate + "</a></li>"
	           content += "<li class='productLeaseDay'><a href='../../products/" + result.productId + "'>" + result.rentDate + "일</a></li>"
	           content += "<li class='productLeaseSeller'><a href='../../products/" + result.productId + "'>" + result.sellerName + "</a></li>"
	           content += "<li class='productLeaseStatus'><a href='../../products/" + result.productId + "'>" + result.productStatus + "</a></li>"
	           content += "<li class='productLeaseChat'>"
	           if (result.productStatus == '대여확정'){
	             content += "<a href='#' class='chatConnect'>연락하기</a>"
	           }
	           content += "</li>"
				const tempUl = document.createElement("ul");
				tempUl.classList.add("listMain");
				tempUl.innerHTML = content;
				locate.insertBefore(tempUl, moreBox);
			}
			
		}
		moreBox = document.querySelector(".moreBox");
		if (list.length < 5){
			moreBox.remove();
		}
		
	}


</script>
<%@include file="../includes/Project_footer.jsp" %>
</html>