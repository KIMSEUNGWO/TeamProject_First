window.addEventListener('load', function(){
    		button_login.addEventListener('click', function(e){
    			e.preventDefault();
    			
    				let obj={
        					memberEmail : document.querySelector('#userId').value,
        					memberPassword : document.querySelector('#userPw').value,
        					redirectURL : document.querySelector('#redirectURL').value
        			};
					
    				//console.log(obj);
        			fetchPost('/login', obj, loginCheck);
    		})
    	
    		
    		function loginCheck(map){
    			let error = document.querySelectorAll('.errors');
    			// 로그인 성공 -> list로 이동
    			if(map.result == "success"){
    				location.href= map.url;
    			} else{
    				// 실패 -> 메세지 출력
    				error[0].textContent='이메일과 비밀번호를 확인해주세요';
    			}
    		}
    	});
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