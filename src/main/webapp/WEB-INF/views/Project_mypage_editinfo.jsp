<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보수정</title>
    <link rel="stylesheet" href="../../resources/css/Project_mypage_editinfo.css">
    <script src="../../resources/js/Project_mypage_editinfo.js"></script>
</head>
<%@include file="../includes/Project_header.jsp"%>
    <main>
        <div class='wrapper'>
		<%@include file="../includes/Project_mypage_menu.jsp" %>
            <div class='myPageMain'>
                <h1>나의 정보</h1>
                <form action="./editinfo" method="post">
	                <div class="editForm">
	                    <h2>필수 회원 정보</h2>
	                    <hr>
	                    <div class="editBox">
	                        <div class="userNameForm">
	                            <div>이름</div>
	                            <div class="userName">${member.memberName}</div>
	                        </div>
	                        <div class="userEmailForm">
	                            <div>이메일</div>
	                            <div class="userEmail">${member.memberEmail }</div>
	                        </div>
	                        <div class="userPwForm">
	                            <div>비밀번호</div>
	                            <input type="password" name="userPw" id="userPw" minlength="8" maxlength="15">
	                            <div class="errors"></div>
	                        </div>
	                        <div class="userPwCheckForm">
	                            <div>비밀번호 확인</div>
	                            <input type="password" name="userPwCheck" id="userPwCheck" minlength="8" maxlength="15">
	                            <div class="errors"></div>
	                        </div>
	                        <div class="userPhoneForm">
	                            <div>휴대전화</div>
	                            <div class="phone">${member.memberPhone}</div>
	                        </div>
	                    </div>
	                </div>
	                <div class="errorBox"><span class="errors message">${message}</span></div>
	                <div class="editButtonBox">
	                    <input type="submit" class="editButton" value="수정하기"></input>
	                </div>
                </form>
            </div>
        </div>
        
    </main>
    <%@include file="../includes/Project_footer.jsp" %>
</html>