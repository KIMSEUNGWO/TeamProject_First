window.addEventListener('load', function(){
    let phoneCheck = document.querySelector('#check');
    phoneCheck.addEventListener('click',function(){

        document.querySelector('.regi_box_phone_check').classList.add('active');
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
					error[6].textContent = '';
					phoneCheckRes.value='2';
					
					$('#phoneCheck').click(function() {	
						const userNum = $('#phoneCheckNum').val();
						
						if(checkNum === userNum) {
							alert('인증 성공하였습니다');
							error[7].textContent = '';
							phoneCheckRes.value='1';
						}
						else {
							alert('인증번호가 일치하지 않습니다');
						}
					});
					
				}
			});
			
	});
	let userId = document.querySelector('#userId');
	let userPw = document.querySelector('#userPw');
	let userPwCheck = document.querySelector('#userPwCheck');
	let userNickname = document.querySelector('#userNickname');
	let userName = document.querySelector('#userName');
	let userPhone = document.querySelector('#userPhone');
	let phoneCheckNum = document.querySelector('#phoneCheckNum');
	let error = document.querySelectorAll('.errors');
	let button_check = document.querySelector('#check');
	let button_register = document.querySelector('#button_register');
	let idCheckRes = document.querySelector('#idCheckRes');
	let nickNameCheckRes = document.querySelector('#nickNameCheckRes');
	let phoneCheckRes = document.querySelector('#phoneCheckRes');
	button_register.disabled = true;
	button_check.disabled = true;
	
	
	userId.addEventListener('keyup',function(){
		if(userId.value==''){
			error[0].textContent='';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(!email_check(userId.value)){
			error[0].textContent='이메일 형식에 맞게 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		} else {
			error[0].textContent='';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		
	});
	
	userId.addEventListener('blur',function(){
		
			let obj={memberEmail : userId.value};
			fetchPost("/idCheck", obj, (map) => {
		    	  if(map.result=="success"){
		    		  if(email_check(userId.value)){
		    			  idCheckRes.value='0';
		    			  button_register.disabled = true;
		    			  button_check.disabled = true;
		    			  return;
		    		  } 
		    		  
		    	  } else {
		    		  error[0].textContent='이미 사용중인 아이디 입니다';
		    		  idCheckRes.value='1';
		    		  button_register.disabled = true;
		    		  button_check.disabled = true;
					  return;
		    	  }
		      })
		
	});
	
	userPw.addEventListener('keyup', function(){
		
		let pwValue = userPw.value;
		
		const regex1 = /[a-z]/;
	    const regex2 = /[A-Z]/;
	    const regex3 = /\s/;
	    const regex4 = /[\!\@\#\$\%\^\&\*\(\)\-\+\=\_]/;
		
		 if (pwValue == ''){
	            error[1].textContent = '';
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return;
	        }

	        if (pwValue.length < 8 || pwValue.length > 15){
	            error[1].textContent = '숫자, 영문 대소문자, 특수문자를 포함해 최소 8자리에서 최대 15자리까지 가능합니다.';
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return;  
	        } 
	        if (!regex1.test(pwValue)) {
	            error[1].textContent = '영문 소문자를 포함해주세요';
	            	button_register.disabled = true;
	        	button_check.disabled = true;
	            return;
	        }
	        if (!regex2.test(pwValue)) {
	            error[1].textContent = '영문 대문자를 포함해주세요';
	            return;
	        }
	        if (regex3.test(pwValue)) {
	            error[1].textContent = '공백은 사용할 수 없습니다.';
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return;
	        }
	        if (!regex4.test(pwValue)){
	            error[1].textContent = '특수문자가 없습니다.';
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return;
	        }
	        error[1].textContent = '';
	        button_register.disabled = true;
	    	button_check.disabled = true;
		
	});
	
	userPwCheck.addEventListener('keyup', function(){
			let pwValue = userPw.value;
	        let pwCheckValue = userPwCheck.value;

	        if (pwCheckValue == ''){
	            error[2].textContent = '';
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return;
	        }

	        if (pwValue == ''){
	            error[2].textContent = '비밀번호를 먼저 입력해주세요'
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return
	        }

	        if (pwValue != pwCheckValue){
	            error[2].textContent = '비밀번호가 일치하지 않습니다.'
	            button_register.disabled = true;
	        	button_check.disabled = true;
	            return;
	        } else {
	            error[2].textContent = '';
	            button_register.disabled = true;
	        	button_check.disabled = true;
				return;
	        }
	        
	});
	
	userNickname.addEventListener('keyup', function(){
		if(userNickname.value == ''){
			error[3].textContent = '';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		
		if(!nickName_check(userNickname.value)){
			error[3].textContent = '닉네임은 한글, 영어 대소문자, 숫자를 사용하여 최소 2자리, 최대 8자리까지만 가능합니다';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		} else {
			error[3].textContent = '';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
	});
	
	userNickname.addEventListener('blur', function(){
	
		let obj={memberNickname : userNickname.value};
		fetchPost("/nickNameCheck", obj, (map) => {
	    	  if(map.result=="success"){
	    		  nickNameCheckRes.value='0';
	    		  button_register.disabled = true;
	    		  button_check.disabled = true;
	  			  return;
	    	  } else {
	    		  error[3].textContent = '이미 사용중인 닉네임 입니다';
	    		  nickNameCheckRes.value='1';
	    		  button_register.disabled = true;
	    		  button_check.disabled = true;
	  			  return;
	    	  }
	      })
	});
	
	userName.addEventListener('keyup',function(){
		if(userName.value==''){
			error[4].textContent = '';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		
		if(!name_check(userName.value)){
			error[4].textContent = '이름은 한글 2글자 ~ 4글자만 입력 가능합니다';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		} else {
			error[4].textContent = '';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		
	});
	
	userPhone.addEventListener('keyup', function(){
		if(userPhone.value==''){
			error[5].textContent = '';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		
		if(!phone_check(userPhone.value)){
			error[5].textContent = '휴대폰 번호 형식으로 숫자만 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		} else {
			error[5].textContent = '';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
	});
	
	userPhone.addEventListener('blur',function(){
		
		let obj={memberPhone : userPhone.value};
		fetchPost("/phoneCheck", obj, (map) => {
	    	  if(map.result=="success"){
	    		error[5].textContent = '';
	  			button_register.disabled = true;
	  			button_check.disabled = false;
	  			return; 
	    	  } else {
	    		error[5].textContent = '이미 가입한 휴대폰 번호입니다';
	  			button_register.disabled = true;
	  			button_check.disabled = true;
	  			return;
	    	  }
	      });
		
	});
	
	button_register.addEventListener('click', function(e){
		e.preventDefault();	
		
		if(userId.value==''){
			error[0].textContent = '이메일을 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(userPw.value==''){
			error[1].textContent = '비밀번호를 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(userPwCheck.value==''){
			error[2].textContent = '비밀번호 확인을 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(userNickname.value==''){
			error[3].textContent = '닉네임을 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(userName.value==''){
			error[4].textContent = '이름을 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(userPhone.value==''){
			error[5].textContent = '핸드폰번호를 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = true;
			return;
		}
		if(phoneCheckRes.value=='5' && nickNameCheckRes.value=='0' && idCheckRes.value=='0'){
			error[6].textContent = '인증번호 발송버튼을 눌러 인증절차를 진행해주세요';
			button_register.disabled = true;
			button_check.disabled = false;
			return;
		} 
	
		if(phoneCheckRes.value=='2' && phoneCheckNum.value==''){
			error[7].textContent = '인증번호를 입력해주세요';
			button_register.disabled = true;
			button_check.disabled = false;
			return;
		}
		if(phoneCheckRes.value=='2' && phoneCheckNum.value!=''){
			error[7].textContent = '인증번호확인 버튼을 눌러주세요';
			button_register.disabled = true;
			button_check.disabled = false;
			return;
		}
		
		if(phoneCheckRes.value == '1' && nickNameCheckRes.value=='0' && idCheckRes.value=='0'){
			error[7].textContent = '';
			button_register.disabled = false;
			button_check.disabled = false;
			regist();
		}
		
	});
	
});

	function regist(){
		obj = {
				memberEmail : userId.value, 
				memberPassword : userPw.value, 
				memberNickname : userNickname.value,
				memberName : userName.value,
				memberPhone : userPhone.value
		}
		//console.log('회원가입obj : ', obj);
		
		// 회원가입 요청
		fetchPost('/members/add', obj, (map)=>{
			if(map.result == 'success'){
				location.href='/login';
				alert('회원가입이 완료되었습니다');
			} else {
				location.href='/register';
				alert('회원가입 도중 오류가 발생하였습니다, 관리자에게 문의하시기 바랍니다.');
			}
		});
	}
	
	function email_check(memberEmail){

		var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		
		return reg.test(memberEmail);
	}
	
	function password_check(memberPassword){
		var p_regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		
		return p_regExp.test(memberPassword);
	}
	
	function phone_check(memberPhone){
		
		 var regEx = /^(01[0|1|6|7|8|9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		 
		 return regEx.test(memberPhone);
		
	}
	
	function name_check(memberName){
		var n_RegExp = /^[가-힣]{2,4}$/;
		
		return n_RegExp.test(memberName);
	}
	
	function nickName_check(memberNickname){
		var nn_RegExp = /^[가-힣a-zA-Z0-9]{2,8}$/;
		
		return nn_RegExp.test(memberNickname);
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