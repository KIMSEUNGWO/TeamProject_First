window.addEventListener('load',function(){
    let getmore = document.querySelector(".getMore");
	var startRow = 0;
	var endRow = 0;
	
	// 정렬순서 변경
	let popular = document.querySelector('#popular');
	let recently = document.querySelector('#recently');
	let rowPrice = document.querySelector('#rowPrice');
	let highPrice = document.querySelector('#highPrice');
	
    popular.addEventListener('click', function() { getMapping('popular'); });
    recently.addEventListener('click', function() { getMapping('recently'); });
    rowPrice.addEventListener('click', function() { getMapping('rowPrice'); });
    highPrice.addEventListener('click', function() { getMapping('highPrice'); });
    
    var btns = document.querySelectorAll(".actA");
    let sortNow = document.querySelector('#sort');
    btns.forEach(a => {
		if (a.id == sortNow.value){
			a.classList.add('active');
		}
	})
	
	if (getmore){
		getmore.addEventListener('click', function(){
			var list_length = $(".productList .product").length;
			startRow = list_length + 1;
			endRow = startRow + 7;
	        var search = document.querySelector('#search').value;
	        var startDate = document.querySelector('#startDate').value;
	        var endDate = document.querySelector('#endDate').value;
	        var sort = document.querySelector('#sort').value;
			var moreDate = {
                startDate: startDate, // 수정: 대여 시작 날짜
				startRow : startRow,
                endDate: endDate, // 수정: 대여 종료 날짜
				endRow : endRow,
                sort: sort, // 수정: 카테고리 ID
				search: search // 수정: 검색어 값
			};
	        
			$.ajax({
				type: "GET",
				url : window.location.pathname + "/items",
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
		for (var i=0;i<length;i++){
			var result = list[i];
			var content = '';

			if (result.productId != ''){  
				content += "<a href='/products/" + result.productId + "'>"
				content += "<li class='productImage'><img class='thumbnail' src='../../../../resources/images/" + result.storeImageName + "' alt=''></li>"
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
	function getMapping(str){
		let sort = document.querySelector('#sort');
		if (str == 'popular') sort.value = 'popular'
		else if (str == 'recently') sort.value = 'recently'
		else if (str == 'rowPrice') sort.value = 'rowPrice'
		else if (str == 'highPrice') sort.value = 'highPrice'
		
		let headerForm = document.querySelector('#headerForm');
		headerForm.submit();
		
	}

})

// 액티브
function change_btn(e) {
    var btns = document.querySelectorAll(".actA");
    let sortNow = document.querySelector('#sort');
    btns.forEach(function(btn, i) {
        if(e.currentTarget == btn) {
            
        }
        else {
            btn.classList.remove("active");
        }
    });
    //console.log( e.currentTarget );
}


function change_btnPage(e) {
    var btns = document.querySelectorAll(".btnPage");
    btns.forEach(function(btn, i) {
        if(e.currentTarget == btn) {
            btn.classList.add("active");
        }
        else {
            btn.classList.remove("active");
        }
    });
    //console.log( e.currentTarget );
    
}
