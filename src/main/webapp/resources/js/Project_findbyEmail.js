window.addEventListener('load', function(){
    let phoneCheck = document.querySelector('#check');
    phoneCheck.addEventListener('click',function(){

        document.querySelector('.findEmail_box_phone_check').classList.add('active');
    })
})


function phoneNum(){
    let num = document.querySelector('#userPhone');
    //console.log(num.value.length);
    if (num.value.length == 3 || num.value.length == 8){
        num.value += "-";
    }
}

window.addEventListener('load', function(){
	$('#check').click(function() {
		
		const to = $('#userPhone').val();
		
		$.ajax ({
			url: '/check/sendSMS',
			type: 'GET',
			data: {
				"to" : to
			},
			success: function(data) {
				const checkNum = data;
				alert('인증번호가 발송되었습니다');
				error[2].textContent = '';
				phoneCheckRes.value='2';
				
				$('#phoneCheck').click(function() {	
					const userNum = $('#phoneCheckNum').val();
					
					if(checkNum === userNum) {
						alert('인증 성공하였습니다.');
						error[3].textContent = '';
						phoneCheckRes.value='1';
					}
					else {
						alert('인증 실패하였습니다. 다시 입력해주세요.');
					}
				});
			}
		});
		
	});
	
	let userName = document.querySelector('#userName');
	let userPhone = document.querySelector('#userPhone');
	let phoneCheckNum = document.querySelector('#phoneCheckNum');
	let error = document.querySelectorAll('.errors');
	let button_check = document.querySelector('#check');
	let button_findbyEmail = document.querySelector('#button_findbyEmail');
	let phoneCheckRes = document.querySelector('#phoneCheckRes');
	button_findbyEmail.disabled = true;
	button_check.disabled = true;
	
	userName.addEventListener('keyup',function(){
		if(userName.value==''){
			error[0].textContent = '';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
		
		if(!name_check(userName.value)){
			error[0].textContent = '이름은 한글 2글자 ~ 4글자만 입력 가능합니다';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		} else {
			error[0].textContent = '';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
		
	});
	
	userPhone.addEventListener('keyup', function(){
		if(userPhone.value==''){
			error[1].textContent = '';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
		
		if(!phone_check(userPhone.value)){
			error[1].textContent = '휴대폰 번호 형식으로 숫자만 입력해주세요';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		} else {
			error[1].textContent = '';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
	});
	
	userPhone.addEventListener('blur', function(){
		findbyEmailCheck();
		});
	
	button_findbyEmail.addEventListener('click', function(e){
		e.preventDefault();
		
		if(userName.value==''){
			error[0].textContent = '이름을 입력해주세요';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(userPhone.value==''){
			error[1].textContent = '핸드폰번호를 입력해주세요';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
		
		if(phoneCheckRes.value=='0'){
			error[2].textContent = '인증번호 발송버튼을 눌러 인증절차를 진행해주세요';
			button_findbyEmail.disabled = true;
			button_check.disabled = false;
			return;
		} 
	
		if(phoneCheckRes.value=='2' && phoneCheckNum.value==''){
			error[3].textContent = '인증번호를 입력해주세요';
			button_findbyEmail.disabled = true;
			button_check.disabled = false;
			return;
		}
		if(phoneCheckRes.value=='2' && phoneCheckNum.value!=''){
			error[3].textContent = '인증번호확인 버튼을 눌러주세요';
			button_findbyEmail.disabled = true;
			button_check.disabled = false;
			return;
		}
		if(phoneCheckRes.value == '1'){
			error[3].textContent = '';
			button_findbyEmail.disabled = false;
			button_check.disabled = false;
		
			
		let obj={
				memberName : document.querySelector('#userName').value,
				memberPhone : document.querySelector('#userPhone').value	
		};
		
		fetchPost('/findbyEmailAction', obj, phoneCheck);
		}
	})
	
});

function phoneCheck(map){

	let error = document.querySelectorAll('.errors');
	
	if(map.result == "success"){
		location.href= map.url;
	} 
	
};

function findbyEmailCheck(){
	
	let error = document.querySelectorAll('.errors');
	let phoneCheckRes = document.querySelector('#phoneCheckRes');
	let userName = document.querySelector('#userName');
	let userPhone = document.querySelector('#userPhone');
	let button_check = document.querySelector('#check');
	let button_findbyEmail = document.querySelector('#button_findbyEmail');
	
	obj = {
			memberName : userName.value,
			memberPhone : userPhone.value
	}
	
	fetchPost('/findbyEmailCheck', obj, (map)=>{
		if(map.result == 'success'){
			phoneCheckRes.value='0';
			error[1].textContent = '';
			button_findbyEmail.disabled = true;
			button_check.disabled = false;
			return;
		} else {
			error[1].textContent = '일치하는 정보가 없습니다';
			button_findbyEmail.disabled = true;
			button_check.disabled = true;
			return;
		}
	})
};


	
	
	function name_check(memberName){
		var n_RegExp = /^[가-힣]{2,4}$/;
		
		return n_RegExp.test(memberName);
	};
	
	function phone_check(memberPhone){
		
		 var regEx = /^(01[0|1|6|7|8|9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		 
		 return regEx.test(memberPhone);
		
	};

function fetchPost(url, obj, callback){
	try{
		// url 요청
		fetch(url, {method : 'post', headers : {'Content-Type' : 'application/json'}, body : JSON.stringify(obj)})
			// 요청결과 json 문자열을 javascript 객체로 반환
			.then(response => response.json())
			// 콜백함수 실행
			.then(map => callback(map));
	} catch(e) {
		console.log('fetchPost', e)
	}
};	