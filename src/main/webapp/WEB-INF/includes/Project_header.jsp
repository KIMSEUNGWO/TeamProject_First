<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../../resources/css/Project_header.css">
<script src="../../../resources/js/calendar.js"></script>
<script src="../../../resources/js/fetch.js"></script>
<script type="text/javascript">

	window.addEventListener('load', function() {

		fetchGet('/category', categoryList);

	})
	
	function categoryList(map){

		const list = map.list;
		
		// 카테고리를 대분류 중분류로 저장할 맵 
		const categoryS = new Map();
		
		list.forEach(cate=>{
			if(cate.categoryId2 === 0) {
				// 대분류
					categoryS.set(cate.categoryId, {categoryName: cate.categoryName, categoryId : cate.categoryId, subcategory : []});
			} else {
				// cate.categoryId가 0이 아니면 중분류를 의미
				const cateParentId = categoryS.get(cate.categoryId2);
				if(cateParentId != null) {
					// 부모카테고리가 있으면 subcategory에 배열 추가
					cateParentId.subcategory.push({categoryName: cate.categoryName, categoryId : cate.categoryId});
				}
			}
		})
		
		let category = "<nav>"
			 		 + "<ul class = 'categoryMenu'>";
		
		// 맵에서 카테고리 대분류 찍고 중분류있으면 서브메뉴 만들기
		categoryS.forEach((pCate, categoryId)=>{
			category += "<li><a href = '/list?startDate=&endDate=&sort=&category="+pCate.categoryName+"&search=' id = 'category-size'>"+pCate.categoryName+"<span>∨</span></a>";
			
			if(pCate.subcategory.length>0) {
				category += "<ul class = 'submenu'>";
				pCate.subcategory.forEach(subCate => {
					category += "<li><a href = '/list?startDate=&endDate=&sort=&category="+subCate.categoryName+"&search=' id = 'subcategory-size'>"+subCate.categoryName+"</a></li>";				
				})
				category +="</ul>";
			}
			
			category += "</li>";
		})
		 
		category += "</ul>"
	 	 	 	 + "</nav>";

		categorytop.innerHTML=category;	
		
		if(map.notReadM!=="notlogin") {
			message.innerText+="("+map.notReadM+")";		
		}
	}
	
	 function popup(url) {
	    	const name = "MyMessageList";
	        const x = window.screen.width-600;
	        const y = window.screen.height-100;
	        let option = 'width = 600, height = 712, left = '+x+', top = '+y+', location=no';
	    	
	    	window.open(url, name, option);
	 }
	
</script>
</head>
<body>
    <header>

        <div class='header_top'>
            <ul class='header_top_menu'>
            
           		<li class='header_page'><a href="#" id='message' onclick="popup('/members/${memberId}/messages')">쪽지</a></li>
                <li class='header_page'><a href="/members/${memberId}">마이페이지</a></li>
            	<c:if test="${not empty memberId}" var="login">
	                <li class='header_page'><a href="/logout">로그아웃</a></li>
            	</c:if>
            	<c:if test="${not login}">
                	<li class='header_page'><a href="/login">로그인/회원가입</a></li>
                </c:if>
                
            </ul>
        </div>
        <div class='head'>
            <div class='logo'><a href="/"><img src="../../../../resources/images/rogo.png" alt="" class= "img" style="width: 100%;"></a></div>
            
            
			<input type="text" name="startDateIndex" id="startDateIndex" class='hidden' value='0'>
			<input type="text" name="endDateIndex" id="endDateIndex" class='hidden' value='0'>
            <!-- 검색 폼 시작 -->
            <form method="get" name='searchFormS' action="/list" id="headerForm">
	            <div class='search'>
	                <input type="text" name="startDate" id="startDate" class='hidden' value="${startDate}">
	                <input type="text" name="endDate" id="endDate" class='hidden' value="${endDate }">
	                <input type="text" name="sort" id="sort" class='hidden' value="${sort }">
	               	<input type="text" name="category" id="category" class="hidden" value="${category }">
	                <input type="search" name="search" id="search" placeholder='검색어' autocomplete='off' value="${search}">
	                <svg width='1.2rem' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
	                    <path d="M368 208A160 160 0 1 0 48 208a160 160 0 1 0 320 0zM337.1 371.1C301.7 399.2 256.8 416 208 416C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208c0 48.8-16.8 93.7-44.9 129.1L505 471c9.4 9.4 9.4 24.6 0 33.9s-24.6 9.4-33.9 0L337.1 371.1z"/></svg></a> 
	                <div class='calendar active'>
	                    <div class='cal_header'>
	                        <h3>대여일</h3>
	                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" class='calendar_cancel'>
	                            <path d="M376.6 84.5c11.3-13.6 9.5-33.8-4.1-45.1s-33.8-9.5-45.1 4.1L192 206 56.6 43.5C45.3 29.9 25.1 28.1 11.5 39.4S-3.9 70.9 7.4 84.5L150.3 256 7.4 427.5c-11.3 13.6-9.5 33.8 4.1 45.1s33.8 9.5 45.1-4.1L192 306 327.4 468.5c11.3 13.6 31.5 15.4 45.1 4.1s15.4-31.5 4.1-45.1L233.7 256 376.6 84.5z"/></svg>
	                        
	                    </div>
	                    <div class='cal toCal'>
	                        <div class='calTop'>
	                            <span id='preBtn'>
	                                <svg transform='rotate(180)' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512">
	                                    <path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"/></svg>
	                            </span>
	                            <div>
	                                <span id='preYear'></span>
	                                <span>.</span>
	                                <span id='preMonth'></span>
	
	                            </div>
	                        </div>
	                        <ul class='weekTitle'>
	                            <li class="col">일</li>
	                            <li class="col">월</li>
	                            <li class="col">화</li>
	                            <li class="col">수</li>
	                            <li class="col">목</li>
	                            <li class="col">금</li>
	                            <li class="col">토</li>
	                        </ul>
	                        <ul class='days'>
	
	                        </ul>
	                    </div>
	                    <div class='cal fromCal'>                        
	                        <div class='calTop'>
	                            <div>
	                                <span id='nextYear'></span>
	                                <span>.</span>
	                                <span id='nextMonth'></span>
	                            </div>
	                            <span id='nextBtn'>
	                                <svg width='0.8rem' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path d="M278.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-160 160c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L210.7 256 73.4 118.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l160 160z"/></svg>
	                            </span>
	                        </div>
	                        <ul class='weekTitle'>
	                            <li class="col">일</li>
	                            <li class="col">월</li>
	                            <li class="col">화</li>
	                            <li class="col">수</li>
	                            <li class="col">목</li>
	                            <li class="col">금</li>
	                            <li class="col">토</li>
	                        </ul>
	                        <ul class='days'>
	
	                        </ul>
	                    </div>
	                    <div id='searchForm'>
	                        <div>
	                            <span>시작일 : </span>
	                            <span class="start"></span>
	                        </div>
	                        <div>
	                            <span>종료일 : </span>
	                            <span class="end"></span>
	                        </div>
	                        <button>검색하기</button>
	                    </div>
	                </div>
	            </div>
            </form>
            <!-- 검색 폼 끝 -->
            
            <ul class='menulist'>
            	<c:choose>
            		<c:when test="${not empty memberId}">
            			<li class='menu'><a href="/members/${memberId}/items">내상점</a></li>
            		</c:when>
            		<c:otherwise>
            			<li class='menu'><a href="/login">내상점</a></li>
            		</c:otherwise>
            	</c:choose>
                
                <li class='menu'><a href="/products/add">상품등록</a></li>

            
          

            </ul>
        </div>
        
        <div class = "categorytop" id="categorytop">

        </div>
    </header>
