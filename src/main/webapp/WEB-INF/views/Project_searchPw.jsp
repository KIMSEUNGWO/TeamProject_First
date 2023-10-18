<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="../../resources/css/Project_header.css">
    <link rel="stylesheet" href="../../resources/css/Project_footer.css">
    <script src="../../resources/js/calendar.js"></script>
    <link rel="stylesheet" href="../../resources/css/Project_searchPw.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="../../resources/js/Project_searchPw.js"></script>
</head>
<%@include file="../includes/Project_header.jsp"%>
<body>
    <div class='findEmail_area'>
        <div class='findEmail_center slide-in'>
            <div class='findEmail_logo'>
                비밀번호 찾기
            </div>
            <div class='findEmail_box_name'>
                <h5>이메일</h5>
                <input type="email" name="userId" id="userId" autocomplete='off'>
            </div>
            <div class="errors"></div>
            <div class='findEmail_box_name'>
                <h5>이름</h5>
                <input type="text" name="userName" id="userName" autocomplete='off'>
            </div>
            <div class="errors"></div>
            <div class='findEmail_box_phone'>
                <h5>휴대폰번호</h5>
                <div>
                    <input type="tel" name="userPhone" id="userPhone" onkeypress='phoneNum()' maxlength='13' autocomplete='off'>
                    <button class='phoneCheck' id='check'>인증번호발송</button>
                </div>
            </div>
            <div class="errors"></div>
            <div class="errors"></div>
            <div class='findEmail_box_phone_check'>
                <input type="text" name='phonCheckNum' id='phoneCheckNum' autocomplete='off'>
                <button class='phoneCheck' id='phoneCheck'>인증번호확인</button>
            </div>
            <div class="errors"></div>
            <div class="errors"></div>
            <div class='findEmail_button'>
                <a href="#" id="button_searchPw">비밀번호 찾기</a>
            </div>
            	<input type="hidden" value='1' id="idCheckRes">
                <input type="hidden" value='5' id="phoneCheckRes">
            
        </div>

    </div>
</body>
<%@include file="../includes/Project_footer.jsp" %>  	
</html>