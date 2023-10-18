window.addEventListener('load', function(){
	
	let getmore = document.querySelector(".getMore");
	let confirm = document.querySelectorAll('.completeBtn');
	var startRow = 0;
	var endRow = 0;
	
	if (getmore){
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
			url : window.location.pathname + "/order",
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

	
	function popup(url) {
		const name = "MyMessageList";
	    const x = window.screen.width-600;
	    const y = window.screen.height-100;
	    let option = 'width = 600, height = 712, left = '+x+', top = '+y+', location=no';
		
		window.open(url, name, option);
	}
	confirm.forEach(button => {
		button.addEventListener('click', function(){
				let types = {
					type : button.classList.item(0),
					orderId : button.classList.item(1),
					productId : button.parentNode.parentNode.childNodes[1].firstChild.textContent
				};
				//console.log(types);
				$.ajax({
					type: "POST",
					url : "../confirm",
					data : types,
					statusCode : {
						200 : function(response){
							location.reload();
						},
						404 : function(error){
							console.log('실패');
						}
					}
				})
		})
	})
	function applyEventListeners() {
        confirm = document.querySelectorAll('.completeBtn');
        confirm.forEach(button => {
            button.addEventListener('click', function () {
				
                let types = {
                    type: button.classList.item(0),
                    orderId: button.classList.item(1),								
                    productId: button.parentNode.parentNode.childNodes[0].firstChild.textContent // 더보기로 추가된 리스트의 항목은 childNodes[0]이 productId를 가르키고있
                };
               																				// 처음 5개가 보여지는 리스트에서는 childNodes[1]이 productId임 수정시 주의할 것!
                $.ajax({
                    type: "POST",
                    url: window.location.pathname + "/confirm",
                    data: types,
					statusCode : {
						200 : function(response){
							location.reload();
						}
					},
                    error: function (error) {
						console.log('실패');
                    }
                })
            });
        });
    }
	
})

	function getMoreList(list){
		
		var locate = document.querySelector(".myLeaseStatus");
		var moreBox = document.querySelector(".moreBox");
		var length = list.length;
		//console.log(length)
		for (var i=0;i<length;i++){
			var result = list[i];
			var content = '';
			if (result.productId != ''){
			
	           content += "<li class='productIdx'><a href='../../products/" + result.productId + "'>" + result.productId + "</a></li>"
	           content += "<li class='productImage'><a href='../../products/" + result.productId + "'><img src='../../resources/images/" + result.productImage + "' alt=''></a></li>"
	           content += "<li class='productTitle'><a href='../../products/" + result.productId + "'>" + result.productName + "</a></li>"
	           content += "<li class='productLeaseStartDate'><a href='../../products/" + result.productId + "'>" + result.startDate + "</a></li>"
	           content += "<li class='productLeaseEndDate'><a href='../../products/" + result.productId + "'>" + result.endDate + "</a></li>"
	           content += "<li class='productLeaseDay'><a href='../../products/" + result.productId + "'>" + result.rentDate + "일</a></li>"
	           content += "<li class='productLeaseStatus'><a href='../../products/" + result.productId + "'>" + result.productStatus + "</a></li>"
	           content += "<li class='productLeaseChat'>"
	           if (result.productStatus == '대여확정'){
	             content += "<a href='#' class='chatConnect'>연락하기</a>"
	           }
	           content += "</li>"
				const tempUl = document.createElement("ul");
				tempUl.classList.add("listMain");
				tempUl.innerHTML = content;
				locate.insertBefore(tempUl, moreBox);
			}
			
		}
		moreBox = document.querySelector(".moreBox");
		if (list.length < 5){
			moreBox.remove();
		}
		// 새로운 .confirmBtn과 .cancelBtn을 최신화
        confirm = document.querySelectorAll('.completeBtn');
        applyEventListeners();
	}