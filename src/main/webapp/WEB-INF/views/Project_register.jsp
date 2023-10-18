<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../resources/css/Project_header.css">
    <link rel="stylesheet" href="../../resources/css/Project_footer.css">
    <script src="../../resources/js/calendar.js"></script>
    <link rel="stylesheet" href="../../resources/css/Project_register.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="../../resources/js/Project_register.js"></script>
</head>
<%@include file="../includes/Project_header.jsp"%>
<body>
    <div class='regi_area'>
        <div class='regi_center slide-in'>
            <div class='regi_logo'>
                회원가입
            </div>
            <div class='regi_box_id'>
                <h5>이메일 주소 *</h5>
                <input type="email" name="userId" id="userId" placeholder='예) email@naver.com' autocomplete='off'>
            </div>
            <div class="errors"></div>
            <div class='regi_box_pw'>
                <h5>비밀번호 *</h5>
                <input type="password" name="userPw" id="userPw" placeholder='대소문자, 숫자, 특수문자를 포함해서 8~15자 이상' maxlength='15' autocomplete='off'>
            </div>
                <div class="errors"></div>
            <div class='regi_box_pw_check'>
                <h5>비밀번호 확인 *</h5>
                <input type="password" name="userPwCheck" id="userPwCheck" maxlength='15' autocomplete='off'>
            </div>
                <div class="errors"></div>
            <div class='regi_box_nickname'>
                <h5>닉네임</h5>
                <input type="text" name="userNickname" id="userNickname" autocomplete='off'>
            </div>
                <div class="errors"></div>
            <div class='regi_box_name'>
                <h5>이름</h5>
                <input type="text" name="userName" id="userName" autocomplete='off'>
            </div>
                <div class="errors"></div>
            <div class='regi_box_phone'>
                <h5>휴대폰번호</h5>
                <div>
                    <input type="tel" name="userPhone" id="userPhone" onkeypress='phoneNum()' maxlength='13' autocomplete='off'>
                    <button class='phoneCheck' id='check'>인증번호발송</button>
                </div>
            </div>
                    <div class="errors"></div>
                    <div class="errors"></div>
            <div class='regi_box_phone_check'>
                <input type="text" name='phonCheckNum' id='phoneCheckNum' autocomplete='off'>
                <button class='phoneCheck' id='phoneCheck'>인증번호확인</button>
            </div>
            <div class="errors"></div>
            <div class="errors"></div>
            <div class='regi_button'>
                <a href="#" id="button_register">가입하기</a>
            </div>
            <input type="hidden" value='1' id="idCheckRes">
            <input type="hidden" value='1' id="nickNameCheckRes">
            <input type="hidden" value='5' id="phoneCheckRes">
        </div>

    </div>
</body>
<%@include file="../includes/Project_footer.jsp" %>
</html>