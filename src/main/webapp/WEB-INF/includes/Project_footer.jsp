<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../../resources/css/Project_footer.css">
</head>
<body>
<footer>
    <div class="footerMain">
        <div class="foot_bottom">
            <div class="team_intro">
                <h3>대여해조</h3>
                <span>김승우</span>
                <span>강소희</span>
                <span>김동현</span>
                <span>오승찬</span>
                <span>이동현</span>
                <span>홍수빈</span>
            </div>
            <div class="tec_serv">
                <h3>기술</h3>
                <span>문자인증 API</span>
                <span>소셜 로그인 API</span>
                <span>Interceptor</span>
            </div>
            <div>
                <h3>개발환경</h3>
				<span>Spring Boot</span>
				<span>Java</span>
				<span>HTML</span>
				<span>CSS</span>
				<span>JavaScript</span>
				<span>GitHub</span>
            </div>
            <div>
                <h3>페이지</h3>
                <a href="/">홈</a>
                <a href="/login">로그인</a>
                <a href="/members/add">회원가입</a>
                <c:if test="${not empty memberId}">
                <a href="/members/${memberId }">마이페이지</a>
                </c:if>
                <c:if test="${empty memberId}">
                <a href="/login">마이페이지</a>
                </c:if>
            </div>
        </div>
        <div class="foot_bottom_bottom">
            <a href="#">이용약관</a>
            <a href="#">개인정보처리방침</a>
            <a href="#">운영정책</a>
            <a href="#">청소년보호정책</a>
            <a href="#">브랜드보호정책</a>
            <a href="#">권리침해신고안내</a>
            <a href="#">공지사항</a>
            <a href="#">사이버윤리실</a>
            <a href="#">Contact Us</a>
        </div>
        <div></div>
        
    </div>
</footer>
</body>
</html>