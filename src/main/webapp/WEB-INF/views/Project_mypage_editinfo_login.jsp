<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/Project_mypage_editinfo_login.css">
</head>
<%@include file="../includes/Project_header.jsp"%>
<body>
    <main>
        <div class='wrapper'>
            <%@include file="../includes/Project_mypage_menu.jsp" %>
            
            <div class='myPageMain'>
                <form action="" method="post">
                <div class="editInfo_login">
                    <h2>개인정보 수정</h2>
                    <div class='editInfo_box_pw'>
                        <h5>비밀번호</h5>
                        <input type="password" name="userPw" id="userPw" autocomplete='off'>
                    </div>
                   	<c:if test="${not empty errors}"><div class="errors">비밀번호가 일치하지 않습니다.</div></c:if>
                    <div class='editInfo_button'>
                        <input type="submit" value="확인">
                    </div>
                </div>
                </form>
            </div>
        </div>
        
    </main>
</body>
<%@include file="../includes/Project_footer.jsp" %>
</html>