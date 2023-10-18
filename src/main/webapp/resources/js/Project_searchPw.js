
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
				error[3].textContent = '';
				phoneCheckRes.value='2';
				
				$('#phoneCheck').click(function() {	
					const userNum = $('#phoneCheckNum').val();
					
					if(checkNum === userNum) {
						alert('인증 성공하였습니다.');
						error[5].textContent = '';
						phoneCheckRes.value='1';
					}
					else {
						alert('인증 실패하였습니다. 다시 입력해주세요.');
					}
				});
				
			}
		});
		
	});
	
	let userId = document.querySelector('#userId');
	let userName = document.querySelector('#userName');
	let userPhone = document.querySelector('#userPhone');
	let phoneCheckNum = document.querySelector('#phoneCheckNum');
	let error = document.querySelectorAll('.errors');
	let button_check = document.querySelector('#check');
	let idCheckRes = document.querySelector('#idCheckRes');
	let button_searchPw = document.querySelector('#button_searchPw');
	let phoneCheckRes = document.querySelector('#phoneCheckRes');
	button_searchPw.disabled = true;
	button_check.disabled = true;
	
	userId.addEventListener('keyup',function(){
		if(userId.value==''){
			error[0].textContent='';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		if(!email_check(userId.value)){
			error[0].textContent='이메일 형식에 맞게 입력해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		} else {
			error[0].textContent='';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		
	});
	
	userId.addEventListener('blur',function(){
		
			let obj={memberEmail : userId.value};
			fetchPost("/idCheck", obj, (map) => {
		    	  if(map.result=="success"){
		    		  if(userId.value!='')
		    			  error[0].textContent='가입하지않은 이메일 입니다';
		    		  	  button_searchPw.disabled = true;
		      			  button_check.disabled = true;
		    			  return;
		    		   
		    	  } else {
		    		  if(email_check(userId.value)){
		    			  idCheckRes.value='0';
		    			  button_searchPw.disabled = true;
		    	    	  button_check.disabled = true;
						  return;
		    		  }
		    		  
		    	  }
		      })
		
	});
	
	userName.addEventListener('keyup',function(){
		if(userName.value==''){
			error[1].textContent = '';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		
		if(!name_check(userName.value)){
			error[1].textContent = '이름은 한글 2글자 ~ 4글자만 입력 가능합니다';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		} else {
			error[1].textContent = '';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		
	});
	
	userPhone.addEventListener('keyup', function(){
		if(userPhone.value==''){
			error[2].textContent = '';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		
		if(!phone_check(userPhone.value)){
			error[2].textContent = '휴대폰 번호 형식으로 숫자만 입력해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		} else {
			error[2].textContent = '';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
	});
	
	userPhone.addEventListener('blur',function(){
		
		searchPwCheck();
		
	});
	
	
	button_searchPw.addEventListener('click', function(e){
		
		e.preventDefault();
		
		if(userId.value==''){
			error[0].textContent = '이메일을 입력해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		if(userName.value==''){
			error[1].textContent = '이름을 입력해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		if(userPhone.value==''){
			error[2].textContent = '핸드폰번호를 입력해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = true;
			return;
		}
		if(phoneCheckRes.value=='0' && idCheckRes.value=='0'){
			error[2].textContent = '인증번호 발송버튼을 눌러 인증절차를 진행해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = false;
			return;
		} 
	
		if(phoneCheckRes.value=='2' && phoneCheckNum.value==''){
			error[4].textContent = '인증번호를 입력해주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = false;
			return;
		}
		if(phoneCheckRes.value=='2' && phoneCheckNum.value!=''){
			error[4].textContent = '인증번호확인 버튼을 눌러주세요';
			button_searchPw.disabled = true;
    		button_check.disabled = false;
			return;
		}
		
		if(phoneCheckRes.value == '1' && idCheckRes.value=='0'){
			error[4].textContent = '';
			button_searchPw.disabled = false;
    		button_check.disabled = false;
		}
		
		const to = $('#userPhone').val();
		
		$.ajax ({
			url: '/check/sendPassword',
			type: 'GET',
			data: {
				"to" : to
			},
			success: function(data) {
				const checkNum = data;
				alert('임시비밀번호가 휴대전화 번호로 전송되었습니다.');
				obj = {
						memberEmail : userId.value,
						memberName : userName.value,
	    				memberPhone : document.querySelector('#userPhone').value,
	    				memberPassword : data, 
	    		}
	    		
	    	
	    		fetchPost('/searchPwAction', obj, (map)=>{
	    			if(map.result == 'success'){
						location.href='/login';
	    			} else {
	    				error[5].textContent = '일치하는 정보가 없습니다';
	    			}
	    		});
			}
			
		})
	});
	
	
});
function searchPwCheck(){

let userId = document.querySelector('#userId');
let error = document.querySelectorAll('.errors');
let phoneCheckRes = document.querySelector('#phoneCheckRes');
let userName = document.querySelector('#userName');
let userPhone = document.querySelector('#userPhone');
let button_check = document.querySelector('#check');
let button_searchPw = document.querySelector('#button_searchPw');

obj = {
	memberEmail : userId.value,
	memberName : userName.value,
	memberPhone : userPhone.value
}

fetchPost('/searchPwCheck', obj, (map)=>{
if(map.result == 'success'){
	phoneCheckRes.value='0';
	error[2].textContent = '';
	button_searchPw.disabled = true;
	button_check.disabled = false;
} else {
	error[2].textContent = '일치하는 정보가 없습니다';
	button_searchPw.disabled = true;
	button_check.disabled = true;
}
});
}

	
function email_check(memberEmail){

	var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		
	return reg.test(memberEmail);
}

function phone_check(memberPhone){
	
	 var regEx = /^(01[0|1|6|7|8|9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	 
	 return regEx.test(memberPhone);
	
	}

function name_check(memberName){
	var n_RegExp = /^[가-힣]{2,4}$/;
	
	return n_RegExp.test(memberName);
}

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
}