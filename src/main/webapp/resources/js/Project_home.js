// 배너
var indexBanner = 0;
const pics = ['banner1.png', 'banner2.png','banner3.png']; // 이미지 배열 만들기

// 일정 간격으로 반복적으로 콜백 함수를 실행
// setInterval(콜백함수, 대기시간(일리초), (콜백함수의 인자 나열));
var interval = setInterval(function(){
    rightBtn();
},5000);

function stop(){
    //console.log('setInterval 중지');
    clearInterval(interval); // 인터벌 중지 함수
}

function rightBtn(){
    indexBanner += 1;
        if(indexBanner == pics.length) indexBanner = 0;
        document.getElementById('banner').style.backgroundImage=`url(resources/images/${pics[indexBanner]})`;
}

function leftBtn(){
    indexBanner -= 1;
    if(indexBanner == -1) indexBanner = pics.length-1;
    document.getElementById('banner').style.backgroundImage=`url(resources/images/${pics[indexBanner]})`;
}
// 배너

window.addEventListener('load', function(){

        // 컨테이너 박스의 배경 화면
        // 배열의 0번째 인덱스값을 넣음
        // 1.컨테이너박스 선택 후 배경이미지 변경
        document.getElementById('banner').style.backgroundImage='url(resources/images/'+pics[0]+')';
    
        // 왼쪽 버튼 클릭
        left.addEventListener('click', function(){
            leftBtn();
        });
    
        // 오른쪽 버튼 클릭
        right.addEventListener('click', function(){
            rightBtn();
        });
        
    	let getmore = document.querySelector(".getMore");
    	var startRow = 0;
    	var endRow = 0;
    	
    	if (getmore){
    		getmore.addEventListener('click', function(){
    			var list_length = $(".productList .product").length;
    			
    			startRow = list_length + 1;
    			endRow = startRow + 7;
    			var moreDate = {
    				startRow : startRow,
    				endRow : endRow
    			}
    			//console.log(moreDate)
    			$.ajax({
    				type: "GET",
    				url : window.location.pathname + "main",
    				data : moreDate,
    				contentType : "application/json",
    				success : function(response){
    					getMoreList(response);
    				},
    				error : function(error){
    					
    				}
    			})
    			
    		})
    	}
    	
    	function getMoreList(list){
    		
    		var locate = document.querySelector(".productList");
    		var moreBox = document.querySelector(".moreBox");
    		var length = list.length;
    		//console.log(length)
    		for (var i=0;i<length;i++){
    			var result = list[i];
    			var content = '';

    			if (result.productId != ''){  
    				content += "<a href='/products/" + result.productId + "'>"
    				content += "<li class='productImage'><img class='thumbnail' src='../../resources/images/" + result.storeImageName + "' alt='${item.productName}'></li>"
    				content += "<li class='productName'><p class='product_title'>" + result.productName + "</p></li>"
    				content += "<li class='productPrice'><p class='product_price'>" + result.productPrice + "원/일</p></li></a>"

    				const tempUl = document.createElement("ul");
    				tempUl.classList.add("product");
    				tempUl.innerHTML = content;
    				locate.insertBefore(tempUl, moreBox);
    			}
    		}
    		moreBox = document.querySelector(".moreBox");
    		if (list.length < 4){
    			moreBox.remove();
    		}
    		
    		
    	}
})