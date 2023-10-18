<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.productName } : 상세페이지</title>
    <link rel="stylesheet" href="../../../../resources/css/Project_product_details.css">
    <link rel="stylesheet" href="../../../../resources/css/Project_product_details_calendar.css">
    <script src="../../../../resources/js/Project_product_details.js"></script>
    <script src='../../../../resources/js/Project_product_details_calendar.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
      <main>
		
      <input type="text" name="memberId" value="${memberId}" class="hidden">
      <input type="text" name="productId" value="${productId}" class="hidden">
          <div class="product">
          <c:if test="${product.productEndStatus == 'Y'.charAt(0)}"> <div class="soldOut"><span>판매중지된 상품입니다.</span></div> </c:if>
            <div class='product_section_one'>  
                <div id="container" >          
                    <button class="arrow" id="left">&lang;</button>  
                    <button class="arrow" id="right">&rang;</button>		            	      
                </div>          
            </div>
      
            <form action="/products/${productId}/orders" method="post">
                <div class='product_section_two'>
                    <div class='product_info'>
                        <div class="product_title">${product.productName }</div>
                        <div class="product_price"><fmt:formatNumber value="${product.productPrice}" pattern="###,###" />원/일</div>
                    </div>
                    <hr>
                    <div class="product_details_info">
                        <div class="product_rent_count">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path d="M48 0C21.5 0 0 21.5 0 48V368c0 26.5 21.5 48 48 48H64c0 53 43 96 96 96s96-43 96-96H384c0 53 43 96 96 96s96-43 96-96h32c17.7 0 32-14.3 32-32s-14.3-32-32-32V288 256 237.3c0-17-6.7-33.3-18.7-45.3L512 114.7c-12-12-28.3-18.7-45.3-18.7H416V48c0-26.5-21.5-48-48-48H48zM416 160h50.7L544 237.3V256H416V160zM112 416a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm368-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/></svg>
                            <span>${confirmStatusCount}</span>
                        </div>
                        <div class="product_view_count">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"/></svg>
                            <span>${product.productVisitCount}</span>
                        </div>
                        <div class="product_upload_date">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 0a256 256 0 1 1 0 512A256 256 0 1 1 256 0zM232 120V256c0 8 4 15.5 10.7 20l96 64c11 7.4 25.9 4.4 33.3-6.7s4.4-25.9-6.7-33.3L280 243.2V120c0-13.3-10.7-24-24-24s-24 10.7-24 24z"/></svg>
                            <span>${product.productCreateDate}</span>
                        </div>
                    </div>
                    
                    <div class="product_status">
                        <div class="product_location_form">
                            <div class="product_label">거래지역</div>
                            <div class="product_info_location">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"><path d="M215.7 499.2C267 435 384 279.4 384 192C384 86 298 0 192 0S0 86 0 192c0 87.4 117 243 168.3 307.2c12.3 15.3 35.1 15.3 47.4 0zM192 128a64 64 0 1 1 0 128 64 64 0 1 1 0-128z"/></svg>
                                <span>${product.location}</span>
                            </div>
                        </div>
                        <div class="product_label_form">
                            <div class="product_label">카테고리</div>
                            <ul class='product_tag_list'>
                                <li class='tagbox'><a href="/list?startDate=&endDate=&sort=&category=${product.categoryName}&search=">#${product.categoryName}</a></li>
                                <li class='tagbox'><a href="/list?startDate=&endDate=&sort=&category=${product.categoryName2}&search=">#${product.categoryName2}</a></li>  
                            </ul>
                        </div>
                        <div class="product_rent_date_form">
                            <div class="product_label">대여가능일</div>
                            <div class="product_info_rent_date">
                                <input type="text" name="orderRentStartDate" id="subStartDate" class='hidden'>
                                <input type="text" name="orderRentEndDate" id="subEndDate" class='hidden'>
                                <input type="text" name="rentDate" id="possibleDate" value='${rentDate}' hidden>
                                <input type="text" name="" id='impossibleDate' value='${orderRentDate}' hidden>
                                <div class='subCalendar'>
                                    <div class='subCal toCal'>
                                        <div class='subCalTop'>
                                            <span id='subPreBtn'>
                                                <svg transform='rotate(180)' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512">
                                                    <path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"/></svg>
                                            </span>
                                            <div>
                                                <span id='subPreYear'></span>
                                                <span>.</span>
                                                <span id='subPreMonth'></span>
                
                                            </div>
                                            <span id='subNextBtn'>
                                                <svg width='0.8rem' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"/></svg>
                                            </span>
                                        </div>
                                        <ul class='subWeekTitle'>
                                            <li class="subCol">일</li>
                                            <li class="subCol">월</li>
                                            <li class="subCol">화</li>
                                            <li class="subCol">수</li>
                                            <li class="subCol">목</li>
                                            <li class="subCol">금</li>
                                            <li class="subCol">토</li>
                                        </ul>
                                        <ul class='subDays'>
                
                                        </ul>
                                    </div>
                                    <div id='subSearchForm'>
                                        <div>
                                            <span>시작일 : </span>
                                            <span class="subStart">0000-00-00</span>
                                        </div>
                                        <div>
                                            <span>종료일 : </span>
                                            <span class="subEnd">0000-00-00</span>
                                        </div>
                                    </div>
                                </div>             

                            </div>
                        </div>
                    </div>

                    <div class="product_buttons">
                    	<c:choose>
                    		<c:when test="${isMyItem}">
                    			<c:choose>
	                    			<c:when test="${product.productEndStatus != 'Y'.charAt(0)}">
		                    		   <button type="button" onclick="location.href='/products/${productId}/edit'" class="product_edit">
				                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg>
				                            <span>수정하기</span>
				                        </button>
				                        <button type="button" class="product_delete">
				                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>    
				                            <span>판매중지</span>
				                        </button>                    			
	                    			</c:when>
                    				<c:otherwise>
                    					<button type="button" class="product_resell">
				                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M48.5 224H40c-13.3 0-24-10.7-24-24V72c0-9.7 5.8-18.5 14.8-22.2s19.3-1.7 26.2 5.2L98.6 96.6c87.6-86.5 228.7-86.2 315.8 1c87.5 87.5 87.5 229.3 0 316.8s-229.3 87.5-316.8 0c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0c62.5 62.5 163.8 62.5 226.3 0s62.5-163.8 0-226.3c-62.2-62.2-162.7-62.5-225.3-1L185 183c6.9 6.9 8.9 17.2 5.2 26.2s-12.5 14.8-22.2 14.8H48.5z"/></svg>
				                            <span>재판매하기</span>
				                        </button>
                    				</c:otherwise>
                    			</c:choose>
                    		</c:when>
                    		<c:otherwise>

                    		    <button class="chat" id="chat" name="chat">
		                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M160 368c26.5 0 48 21.5 48 48v16l72.5-54.4c8.3-6.2 18.4-9.6 28.8-9.6H448c8.8 0 16-7.2 16-16V64c0-8.8-7.2-16-16-16H64c-8.8 0-16 7.2-16 16V352c0 8.8 7.2 16 16 16h96zm48 124l-.2 .2-5.1 3.8-17.1 12.8c-4.8 3.6-11.3 4.2-16.8 1.5s-8.8-8.2-8.8-14.3V474.7v-6.4V468v-4V416H112 64c-35.3 0-64-28.7-64-64V64C0 28.7 28.7 0 64 0H448c35.3 0 64 28.7 64 64V352c0 35.3-28.7 64-64 64H309.3L208 492z"/></svg>
		                            <span>쪽지</span>
		                        </button>
		                        
		                        <button type="button" id = "product-sellReserve">
		                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path d="M48 0C21.5 0 0 21.5 0 48V368c0 26.5 21.5 48 48 48H64c0 53 43 96 96 96s96-43 96-96H384c0 53 43 96 96 96s96-43 96-96h32c17.7 0 32-14.3 32-32s-14.3-32-32-32V288 256 237.3c0-17-6.7-33.3-18.7-45.3L512 114.7c-12-12-28.3-18.7-45.3-18.7H416V48c0-26.5-21.5-48-48-48H48zM416 160h50.7L544 237.3V256H416V160zM112 416a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm368-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/></svg>
		                            <span>대여하기</span>
		                        </button>
		                        <div id = "modal-box">
		                    		<div id = "modal-contents">
		                    		<button id = "close" type="button" onclick="location.href='/products/${productId}'">&times;</button>
		                    			<h2 id = "top-title">예약 확인</h2>
		                    			<div id = "profile">
		                    			
		                    			<div class = "image-content">
		                    				<img src = "../../../../resources/images/${product.images[0].storeImageName}" alt = ""><br>
		                    			</div>
		                    			
		                    				<div id = "desc">
		                    					<h2>상품명</h2><br>
		                    					<p class = "text-date" id="text-date" name="text-date"></p>
		                    					<p class = "text-num" id = "total-date" name = "total-date">대여일 : </p>
		                    					<p class = "text-price">${product.productPrice}</p>
		                    					
		                    					<p class = "text-totalPrice" id = "totalmodalprice"></p>
		                    					<input type = "text" name="orderPrice" style="display:none;" id = "inputTotalmodalprice">
		                    					
		                    					<div class = "bottom">
		                    					<input type="submit" id = "rent_button" value="대여 신청하기">
		                    					</div>
		                    				</div>
		                    			</div>
		                    		</div>
		                    	</div>	
                    		</c:otherwise>
                    	</c:choose> 
                    </div>
                </div>
            </form>
        </div>
        
        <div class='product_detail'>
            <hr>
            <div class='detail_section'>
                <div class="detail_section_detail">
                    <h4 class='product_detail_title'>상품상세</h4>
                    <span class='product_detail_content'>
                        ${product.productContent }
                    </span>                 
                </div>         
                <div class='detail_section_three'>
                    <h4 class='product_detail_title'>판매자 상점정보</h4>
                    <div class='user_info'>
                        <svg xmlns='http://www.w3.org/2000/svg' viewBox="0 0 640 512"><path d="M36.8 192H603.2c20.3 0 36.8-16.5 36.8-36.8c0-7.3-2.2-14.4-6.2-20.4L558.2 21.4C549.3 8 534.4 0 518.3 0H121.7c-16 0-31 8-39.9 21.4L6.2 134.7c-4 6.1-6.2 13.2-6.2 20.4C0 175.5 16.5 192 36.8 192zM64 224V384v80c0 26.5 21.5 48 48 48H336c26.5 0 48-21.5 48-48V384 224H320V384H128V224H64zm448 0V480c0 17.7 14.3 32 32 32s32-14.3 32-32V224H512z"/></svg>
                        <div class='user_info_detail'>
                            <div class='user_nickName'>${nickname }</div>
                            <div class='uesr_info_detail_list'>
                                <div class='user_info_label'>상품</div>
                                <div class='user_info_value'>${productsCount}</div>
                            </div>
                        </div>
                    </div>
                    <ul class='summary_products'>
                    	<c:forEach var="item" items="${productList}" end="5">
	                        <li class='summary_product'>
	                            <a href='./${item.productId }'>
	                                <img src='../../../../resources/images/${item.storeImageName}'>
	                                <div>
	                                    <span class='summary_product_price'><fmt:formatNumber value="${item.productPrice}" pattern="###,###" />원</span>
	                                </div>
	                            </a>
	                        </li>              		
                    	</c:forEach>
                    </ul>
                    
                    <c:if test="${productsCount - 6 - 1 >= 0}">           
	                    <div class='moreBox'>
	                        <a href="../list?startDate=&endDate=&sort=&category=&search=${nickname }">
	                            <span><span>${productsCount - 6 - 1 }개</span> 상품 더보기</span>
	                            <svg width='0.8rem' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"/></svg>
	                        </a>
	                    </div>
	                </c:if>
                    <hr>
                </div>            
            </div>
        </div>
    </main>
    <script type = "text/javascript">
   	 	const mId = document.querySelector("[name=memberId]").value;
        window.addEventListener('load', function(){
        	
        	chat.addEventListener('click', function(e){
        		e.preventDefault();

        	    const name = "MyMessageList";
        	    const x = window.screen.width-600;
        	    const y = window.screen.height-100;
        	    const pId = document.querySelector("[name=productId]").value;
        	    
        	    if(mId === '') {
        	    	alert("로그인이 필요합니다.");
        	    } else {
	        	    const url = '/members/'+mId+'/messages/'+pId+'/0';
	        	    let option = 'width = 600, height = 712, left = '+x+', top = '+y+', location=no';
	        	    window.open(url, name, option);
        	    }
        	    	
        	});
        	
            let open = document.getElementById('product-sellReserve');
            let close = document.getElementById('close');
            let modal = document.getElementById('modal-box');
    
            
            
            open.addEventListener('click', function(){
            	
                let startDate = document.querySelector('#subStartDate').value;
                let endDate = document.querySelector('#subEndDate').value;
                if (startDate == '' || endDate == ''){
                	alert("대여일을 설정해주세요.");
                	return;
                }
            	if(mId === '') {
        	    	alert("로그인이 필요합니다.");
        	    	return;
        	    }
	                let modalprice = document.querySelector('.text-price').textContent;
	                
	                //console.log("startDate", startDate);
	                //console.log("endDate", endDate);
	                //console.log("modalprice", modalprice);
	                
	                document.querySelector('#text-date').innerText = '대여 기간 : '+startDate+' ~ '+endDate;
	                
	                var arr1 = startDate.split('-');
	                var arr2 = endDate.split('-');
	                
	                var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
	                var dat2 = new Date(arr2[0], arr2[1], arr2[2]);
	                
	                var diff = dat2 - dat1;
	                var currDay = 24 * 60 * 60 * 1000; // 시 * 분 * 초 * 밀리세컨
	                var currMonth = currDay * 30; // 월
	                var currYear = currMonth * 12; // 년
	                
	                let priceNumber = parseInt(modalprice.replace(/\D/g, ''));
	
	             // 숫자를 ###,### 형식으로 변환
	             let formattedModalPrice = priceNumber.toLocaleString('en-US');
	
	             
	                
	                document.querySelector('#total-date').innerText = '총 일수 : '+ parseInt(diff/currDay) + '박' + parseInt((diff/currDay+1)) + '일';
	                document.querySelector('.text-price').textContent = '가격 : ' + formattedModalPrice + '원';
	                let total2 = modalprice * parseInt(diff/currDay+1);
	                let formattedTotal2 = total2.toLocaleString('en-US');
	                document.querySelector('#totalmodalprice').innerText = '최종가격 : '+ formattedTotal2 + '원';
	                document.querySelector('#inputTotalmodalprice').value = parseInt(modalprice*parseInt(diff/currDay+1));
	                
	                modal.classList.add('active');
            });
    
            close.addEventListener('click', function(){
                modal.classList.remove('active');
            });
        })
        
        // document.getElementById("product-sellReserve").onclick = function(){
        //     alert("대여 예약 되었습니다.");
        // };
        
        
        </script>
</body>
    <%@include file="../includes/Project_footer.jsp" %>
</html>