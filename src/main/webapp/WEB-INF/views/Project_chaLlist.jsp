<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='../../resources/css/Project_chatList.css'>
<script src="../../../../resources/js/fetch.js"></script>
<script>

	window.addEventListener('load', function() {
		
		btnDelete.addEventListener('click', function(){
			// 삭제할 메세지 선택 번호
			const deleteChecks= document.querySelectorAll("#deleteCheck");
			const deleteCheckArr = [];
			
			deleteChecks.forEach(checkbox => {
				if(checkbox.checked){
					deleteCheckArr.push(checkbox.value);
				}
			});
			
			try{				
				fetchPost('/messages/delete', deleteCheckArr, deleteMessage);
			}catch(e){
				console.log('fetchPost', e);
			}
			
		});
		
		// 버튼 전체선택
		checkAll.addEventListener('click', function(){
			const checkboxes = document.querySelectorAll('input[type="checkbox"]');
			
			checkboxes.forEach((checkbox) => {
				checkbox.checked = checkAll.checked;
			})
		});
		
	})
	
	function deleteMessage(map){
		if(map.message === "success") {
			window.location.reload();
		} else {
			alert("삭제중 오류가 발생했습니다. 다시 시도해주세요.");
			window.location.reload();
		}
	}

</script>
</head>
<body>
<div class="container">
        <header>
            <h1 id="headerN" >쪽지함</h1>
            <div id="checkA" "><input type="checkbox" value="전체선택" id = "checkAll"> 
            	<p id="headerP">전체선택</p>
             	<input type="button" value="삭제" id = "btnDelete">
             </div>
           
        </header>
            
        <main>
	        <!-- 쪽지 리스트 -->
	        <!-- memberId랑 myId가 같으면 내가 보낸거 -->
	        <!-- memberId랑 yourId가 같으면 내가 받은거니까 yourId=myId가 돼야함 -->
	        <c:forEach items="${allChatList}" var="list">
	        
	        <div style="display: flex; flex-direction: row;">
	        	<input  type="checkbox" id="deleteCheck" value="${list.roomNo}">
	        	
	        	<div style="width: 95%;" class = "message-box" onclick = "location.href='/members/${memberId}/messages/${list.productId }/${list.roomNo}';">
	                <div style="" class = "message-img">
	                    <img src="../../../../resources/images/${list.storeImageName}" alt="" class= "img"> 
	                </div>
	                <div id="pInfoBox">
	                	<div class = "product_name info">${list.productName} <span id="cnt">${list.noReadMcnt != 0 ? 'N' : ''}</span></div>
	                    <p class = "info" id= "mName">${list.myId eq memberId ? list.reciver : list.sender}</p>
	                    <p class = "info" id= "mText">${list.messageText}</p>
	                    <p class = "info" id= "mDate">${list.messageCreateDate}</p>
	                </div>
	            </div>
	        </div>
	        </c:forEach>

        </main>
    </div>
</body>
</html>