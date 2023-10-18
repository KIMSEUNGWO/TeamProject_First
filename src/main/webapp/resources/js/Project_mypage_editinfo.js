window.addEventListener('load', function(){
  
    let pw = document.querySelector('#userPw');
    let pwCheck = document.querySelector('#userPwCheck');
    let error = document.querySelectorAll('.errors');
    let button = document.querySelector('.editButton');
    button.disabled = true;
    const regex1 = /[a-z]/;
    const regex2 = /[A-Z]/;
    const regex3 = /\s/;
    const regex4 = /[\!\@\#\$\%\^\&\*\(\)\-\+\=\_]/;
    

    // error.textContent = '대문자, 특수문자를 포함한 8~15자리만 가능합니다.'
    pw.addEventListener('keyup', function(){
		pw = document.querySelector('#userPw');
        let pwValue = pw.value;

        if (pwValue == ''){
            error[0].textContent = '';
            button.disabled = true;
            return;
        }

        if (pwValue.length < 8 || pwValue.length > 15){
            error[0].textContent = '숫자, 대문자, 특수문자를 포함해 8 ~ 15자만 가능합니다.'
            button.disabled = true;
            return;  
        } 
        if (!regex1.test(pwValue)) {
            error[0].textContent = '영문 소문자를 포함해주세요'
            button.disabled = true;
            return;
        }
        if (!regex2.test(pwValue)) {
            error[0].textContent = '영문 대문자를 포함해주세요'
            button.disabled = true;
            return;
        }
        if (regex3.test(pwValue)) {
            error[0].textContent = '공백은 사용할 수 없습니다.'
            button.disabled = true;
            pw.value = '';
            return;
        }
        if (!regex4.test(pwValue)){
            error[0].textContent = '특수문자가 없습니다.'
            button.disabled = true;
            return;
        }
        error[0].innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM369 209L241 337c-9.4 9.4-24.6 9.4-33.9 0l-64-64c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l47 47L335 175c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9z"/></svg>'
    })

    pwCheck.addEventListener('keyup', function(){
        pw = document.querySelector('#userPw').value;
        let pwValue = pwCheck.value;

        if (pwValue == ''){
            error[1].textContent = '';
            button.disabled = true;
            return;
        }

        if (pw == ''){
            error[1].textContent = '비밀번호를 먼저 입력해주세요'
            button.disabled = true;
            pwCheck.value = '';
            return
        }

        if (pw != pwValue){
            error[1].textContent = '비밀번호가 일치하지 않습니다.'
            button.disabled = true;
            return;
        } else {
			button.disabled = false;
            error[1].innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM369 209L241 337c-9.4 9.4-24.6 9.4-33.9 0l-64-64c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l47 47L335 175c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9z"/></svg>'
        }

        

        
    })
})
