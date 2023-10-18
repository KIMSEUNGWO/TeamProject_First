window.addEventListener('load',function(){
    let open = document.querySelectorAll('.rent');

    let modal = document.getElementById('modal-box');

	// 판매중인 물품리스트 AJAX 사용
	let getmore = document.querySelector(".getMore");
	var startRow = 0;
	var endRow = 0;
	getmore.addEventListener('click', function(){
		var list_length = $(".listMain").length;
		
		startRow = list_length + 1;
		endRow = startRow + 5;
		var moreDate = {
			startDate : startRow,
			endDate : endRow
		}
		
		$.ajax({
			type: "GET",
			url : window.location.pathname + "/sell",
			data : moreDate,
			contentType : "application/json",
			success : function(response){
				getMoreList(response);
			},
			error : function(error){
			}
		})
		
	})
	

	open.forEach(button => {
		button.addEventListener('click', function(){
			var productIds = {productId : button.classList.item(1)};
			$.ajax({
				type: "GET",
				url : window.location.pathname + "/modal",
				data : productIds,
				contentType : "application/json",
				success : function(response){
					let closeArr = button.parentNode.childNodes;
					let closeArr2 = '';
					let close = '';
					modalStatus(button.parentNode, response);
					for (var j=0;j<closeArr.length;j++){
						if (closeArr[j].id == 'modal-box'){
							closeArr2 = closeArr[j].childNodes[0].childNodes;
							for (var k=0;k<closeArr2.length;k++){
								if (closeArr2[k].id == 'close'){
									close = closeArr2[k];
									break;
								}
							}
						}
					}
					close.addEventListener('click', function(){
						close.parentNode.parentNode.remove();
					})
					open = document.querySelectorAll('.rent');
				},
				error : function(error){
				}
			})
		})
	})
	

	
	
	
	

})


function modalStatus(product, result){
	//console.log(product)
	var modalContent = '';
	       modalContent += "<div id = 'modal-box' class = 'modalactive'>"
       	   modalContent += "<div id = 'modal-contents'>"
       	   modalContent += " <button id = 'close'>&times;</button>"
       	   modalContent += "<h2 id = 'title'>현재 대여중인 상품</h2>"
       	   modalContent += "<div id = 'profile'>"
       	   modalContent += "<img src='../../resources/images/" + result.productImage + "' alt=''>"
   		   modalContent += "<div id = 'desc'>"
   		   modalContent += "<table class='product-info'>"
   		   modalContent += "<tr><th>상품번호</th><td class='product-no'>" + result.productId + "</td></tr>"
   		   modalContent += "<tr><th>상품명</th><td class = 'product-name'>" + result.productTitle + "</td></tr>"
   		   modalContent += "<tr><th>대여기간</th><td class = 'product-date1'>" + result.rentDate+ "</td></tr>"
   		   modalContent += "<tr><th>대여자</th><td class = 'product-person'>" + result.rentMemberName + "</td></tr>"
   		   modalContent += "</table></div></div></div></div>"	
	
	product.innerHTML += modalContent;
	
	open = document.querySelectorAll('.rent');
	
		open.forEach(button => {
		button.addEventListener('click', function(){
			var productIds = {productId : button.classList.item(1)};
			$.ajax({
				type: "GET",
				url : window.location.pathname + "/modal",
				data : productIds,
				contentType : "application/json",
				success : function(response){
					let closeArr = button.parentNode.childNodes;
					let closeArr2 = '';
					let close = '';
					modalStatus(button.parentNode, response);
					for (var j=0;j<closeArr.length;j++){
						if (closeArr[j].id == 'modal-box'){
							closeArr2 = closeArr[j].childNodes[0].childNodes;
							for (var k=0;k<closeArr2.length;k++){
								if (closeArr2[k].id == 'close'){
									close = closeArr2[k];
									break;
								}
							}
						}
					}
					close.addEventListener('click', function(){
						close.parentNode.parentNode.remove();
					})
					open = document.querySelectorAll('.rent');
				},
				error : function(error){
				}
			})
		})
	})
	
}

function getMoreList(list){
	
	var locate = document.querySelector(".myLeaseStatus");
	var moreBox = document.querySelector(".moreBox");
	var length = list.length;
	
	for (var i=0;i<length;i++){
		var result = list[i];
		var content = '';
		if (result.productId != ''){

           content += "<li class='productImage'><a href='../../products/" + result.itemId + "'><img src='../../resources/images/" + result.itemImage + "' alt=''></a></li>"
           content += "<li class='productTitle'><a href='../../products/" + result.itemId + "'>" + result.itemTitle + "</a></li>"
           content += "<li class='productPrice'><a href='../../products/" + result.itemId + "'>" + result.itemPrice + "원/일</a></li>"
           content += "<li class='productTotalCount'><a href='../../products/" + result.itemId + "'>" + result.itemTotalCount + "건</a></li>"
           content += "<li class='productTotalBenefit'><a href='../../products/" + result.itemId + "'>" + result.itemTotalPrice + "원</a></li>"
           content += "<li class='productStatus'>"
           			if (result.itemStatus == 1){
           content += "<button type='button' class='wait'>판매중</button>"
           				
           			} else {
       	   content += "<button type='button' class='rent " + result.itemId + "'>대여중</button>"
       	   content += "</li>"
       	   
		
           			}

			
			const tempUl = document.createElement("ul");
			tempUl.classList.add("listMain");
			tempUl.innerHTML = content;
			locate.insertBefore(tempUl, moreBox);
			moreBox = document.querySelector(".moreBox");
			if (list.length < 5){
				moreBox.remove();
			}
		}
	}
	
	open = document.querySelectorAll('.rent');
	
	open.forEach(button => {
		button.addEventListener('click', function(){
			var productIds = {productId : button.classList.item(1)};
			$.ajax({
				type: "GET",
				url : window.location.pathname + "/modal",
				data : productIds,
				contentType : "application/json",
				success : function(response){
					let closeArr = button.parentNode.childNodes;
					let closeArr2 = '';
					let close = '';
					modalStatus(button.parentNode, response);
					for (var j=0;j<closeArr.length;j++){
						if (closeArr[j].id == 'modal-box'){
							closeArr2 = closeArr[j].childNodes[0].childNodes;
							for (var k=0;k<closeArr2.length;k++){
								if (closeArr2[k].id == 'close'){
									close = closeArr2[k];
									break;
								}
							}
						}
					}
					close.addEventListener('click', function(){
						close.parentNode.parentNode.remove();
					})
					open = document.querySelectorAll('.rent');
				},
				error : function(error){
				}
			})
		})
	})
	
}