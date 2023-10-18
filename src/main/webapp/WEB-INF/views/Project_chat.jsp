<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../../../resources/css/Project_chat.css">
<script src="../../../../resources/js/fetch.js"></script>
<script type="text/javascript">

	var yourId;

	getMessage();
	
	window.addEventListener('load', function() {
		// 메세지 등록 버튼 이벤트
		btnMsgWrite.addEventListener('click', writeEvent);
	});
	
	// 엔터 누르면 버튼 이벤트
	function writeEventEnter(event) {
	    if (event.keyCode === 13) { 
	        event.preventDefault();
	        writeEvent();
	    }
	}
	
	function writeEvent(){
		let msgText = document.querySelector('#msgWrite').value;
		
		if(msgText.trim() !== '') {
			msgWrite();
			document.querySelector('#msgWrite').value='';
		} else {
			return false;
		}
	}
	
	// 메세지리스트 요청
	function getMessage(){
		fetchGet(`/messages/${memberId}/${productId}/${roomNo}`, getMessageList);
	}
	
	// 메세지 뷰 생성
	function getMessageList(map){
		let list = map.msgList;
		
		let msgDivStr = '';
		
		if(list.length>0) {
			// my_id는 보낸사람 
			// your_id는 받는사람
			// 로그인한 아이디랑 같은 my_id : 내가 보낸 메세지
			list.forEach(msg => {
				// 챗박스는 공통
				msgDivStr += '<div class="chatBox" style="overflow-x:hidden; overflow-y:hidden; height: 100%;">'
				
				if(msg.myId === ${memberId}) {
					const msgStatus = msg.messageReadStatus==='N'?'안읽음':'';
					// 내가 보낸 메세지
					yourId = msg.yourId;
	                msgDivStr += '<div class="myinfo">'
	                		   + '<span class="myId">'+msg.sender+'</span>'
	                		   + '</div>'
	                		   + '<div class="myChat">'
	                		   + msg.messageText
	                		   + '</div>'
	                		   + '<span class="sendDate myDate">'+msgStatus+' '+msg.messageCreateDate+'</span>';
				} else {
					const msgStatus = msg.messageReadStatus==='N'?'안읽음':'';
					// 상대방이 보낸 메세지(내가 받은 메세지)
					yourId = msg.myId;
					msgDivStr += '<div class="yourinfo">'
		             		   + '<span class="yourId">'+msg.sender+'</span>'
		             		   + '</div>'
		             		   + '<div class="yourChat">'
		             		   + msg.messageText
		             		   + '</div>'
		             		   + '<span class="sendDate">'+msgStatus+' '+msg.messageCreateDate+'</span>';
				}
				msgDivStr += '</div>';

			})	
		} else {
			yourId=${pInfo.memberId};
		}

		chatRoom.innerHTML=msgDivStr;
		window.scrollTo(0, document.querySelector('main').scrollHeight);

	}
	
	// 메세지 보내기
	function msgWrite(){
		// 1. 파라메터 수집
		let myId = document.querySelector('[name=memberId]').value;
		let productId = document.querySelector('[name=productId]').value;
		let roomNo = document.querySelector('[name=roomNo]').value;
		let messageText = document.querySelector('[name=msgWrite]').value;
		
		// 2. javascript 객체 만들기
		let message = {
			myId : myId,
			yourId : yourId,
			productId : productId,
			roomNo : roomNo,
			messageText : messageText
		};
		
		// 3. 서버에 요청(POST)
		fetchPost('/messages/insert', message, write);
	}
	
	function write(map) {
		if(map.result == 'success'){
			// 등록성공
			// 리스트 조회및 출력
			getMessage();
		} else {
			// 등록실패
			alert(map.message);
		}
	}
</script>
</head>
<body>
<wrap>

	<input type="text" value="${memberId}" style=" display:none; " name="memberId">
	<input type="text" value="${productId}" style=" display:none; " name="productId">
	<input type="text" value="${roomNo}" style=" display:none; " name="roomNo">
    <main>
    	<!-- 제품정보 시작 -->
        <div class="thead" style="position: sticky; top:0; z-index: 100;">
            <div class="Product_image">
                <img src="../../../../resources/images/${pInfo.storeImageName}" alt="">
            </div>
            <div class="Product_info">
                <div class="Product_title">${pInfo.productName}</div>
				<div class="Product_date">지역 : ${pInfo.location}</div>
                <div class="Product_subinfo">
                    <div class="Product_seller">${pInfo.sellerName}</div>
                    
                </div>
            </div>
            <a id="backBtn" href="/members/${memberId}/messages">나가기</a>
        </div>
    	<!-- 제품정보 끝 -->

    	<!-- 메세지 시작 -->
        <div class="chatRoom" id="chatRoom" style="height: 100%; min-height: 30.5rem">
            
        </div>
        <!-- 메세지 끝 -->
    </main>
            <!-- 댓글 작성 -->
        <div class="tfoot"  style="position: sticky; bottom:0; z-index: 100;">
            <div class="tfootBox"   >
                <textarea class="chatContent" name="msgWrite" id="msgWrite" cols="30" rows="10" placeholder="메세지를 입력해주세요." onkeydown="writeEventEnter(event)"></textarea>
                <button class="chatSend" id="btnMsgWrite">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                        <path d="M49.9 27.8C15.1 12.7-19.2 50.1-1.2 83.5L68.1 212.2c4.4 8.3 12.6 13.8 21.9 15c0 0 0 0 0 0l176 22c3.4 .4 6 3.3 6 6.7s-2.6 6.3-6 6.7l-176 22s0 0 0 0c-9.3 1.2-17.5 6.8-21.9 15L-1.2 428.5c-18 33.4 16.3 70.8 51.1 55.7L491.8 292.7c32.1-13.9 32.1-59.5 0-73.4L49.9 27.8z"/></svg>
                </button>
            </div>
        </div>

</body>
</html>