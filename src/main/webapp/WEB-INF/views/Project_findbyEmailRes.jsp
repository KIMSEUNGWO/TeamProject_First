<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../resources/css/Project_findbyEmailRes.css">
</head>
<%@include file="../includes/Project_header.jsp" %>
<body>
    <div class='findResult_area'>
        <div class='findResult_center slide-in'>
            <div class='findResult_logo'>
                이메일 찾기
            </div>
            <div class="findResult_Content">
                <div class="findResult_title">요청하신 이메일 찾기 결과입니다.</div>
                <hr>
                <c:if test="${empty socialLogin }">
                <div class="findResult">
                    ${memberEmail}
                </div>
                </c:if>
                <c:if test="${socialLogin eq 'Naver' }">
                <div class="findResult">
                    	네이버 로그인 계정입니다
                </div>
                </c:if>
                <c:if test="${socialLogin eq 'Kakao' }">
                <div class="findResult">
                    	카카오 로그인 계정입니다
                </div>
                </c:if>
                <hr>
                <div class="findResult_Btn">
                    <a href="/login">로그인하러가기</a>
                </div>
            </div>
        </div>

    </div>
</body>
<%@include file="../includes/Project_footer.jsp" %>
</html>