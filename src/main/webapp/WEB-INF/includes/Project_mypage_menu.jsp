<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../../../resources/css/Project_mypage_menu.css">
<body>
    <aside>     
        <div class='myName'>${myName }님</div>
        <ul class='myMenuList'>
            <li class='myMenu'><a href="/members/${memberId}">내 정보</a></li>
            <li class='myMenu'><a href="/members/${memberId}/orderlist">구매내역</a></li>
            <li class='myMenu'><a href="/members/${memberId}/items">판매내역</a></li>
            <li class='myMenu'><a href="/members/${memberId}/items/now">대여중인물품</a></li>
            <li class='myMenu'><a href="#" onclick="popup('/members/${memberId}/messages')">내 쪽지함</a></li>
            <li class='myMenu'><a href="/members/${memberId}/check">개인정보수정</a></li>
        </ul>
    </aside>
</body>
<script type="text/javascript">
function popup(url) {
	const name = "MyMessageList";
    const x = window.screen.width-600;
    const y = window.screen.height-100;
    let option = 'width = 600, height = 700, left = '+x+', top = '+y+', location=no';
	
	window.open(url, name, option);
}
</script>
</html>