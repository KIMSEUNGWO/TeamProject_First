window.addEventListener('load',function(){

    let date = new Date();
    var fixedYear = date.getFullYear()
        ,fixedMonth = date.getMonth()+1;
    let currYear = date.getFullYear() // 현재년도
        , currMonth = date.getMonth()+1 // 현재월
        , lastDateOfMonth = new Date(currYear, currMonth, 0).getDate(); // 현재월의 마지막날
    let currentDateYear = document.querySelector('#subPreYear');
    let currentDateMonth = document.querySelector('#subPreMonth');
    
    let todaysTag = document.querySelector('.subDays');

    currentDateYear.innerHTML = currYear;   // 이번달 달력
    currentDateMonth.innerHTML = String(currMonth).padStart(2,'0');
    let currStartDay = new Date(currYear, currMonth-1, 1).getDay(); // 현재달 시작 요일
    
    todaysTag.innerHTML = createLi(currStartDay, lastDateOfMonth , currYear, currMonth); // 왼쪽 달력

    let subNum = document.querySelectorAll('.subDay');
    let startdateYn = document.querySelector('#subStartDate');
    let enddateYn = document.querySelector('#subEndDate');    
    let mainStartDate = document.querySelector('.subStart');
    let mainEndDate = document.querySelector('.subEnd');
    

    let rendersubCalendar = () => {

        currentDateYear.innerHTML = currYear;
        currentDateMonth.innerHTML = String(currMonth).padStart(2,'0');
        lastDateOfMonth = new Date(currentDateYear.textContent,currentDateMonth.textContent, 0).getDate();
        let currStartDay = new Date(currentDateYear.textContent, currentDateMonth.textContent-1, 1).getDay(); // 현재달 시작 요일

        todaysTag.innerHTML = createLi(currStartDay,lastDateOfMonth, currYear, currMonth); // 왼쪽 달력 업데이트
    };

    let subPreBtn = document.querySelector('#subPreBtn');
    let subNextBtn = document.querySelector('#subNextBtn');

    subPreBtn.addEventListener('click', function(){
        if (currYear == fixedYear && currMonth == fixedMonth){
            return;
        }
        if (currMonth -1 < 1){
            currMonth = 12;
            currYear = currYear - 1;
        } else {
            currMonth = currMonth - 1;
        }
        rendersubCalendar();
        subNum = document.querySelectorAll('.subDay');
        startdateYn = document.querySelector('#subStartDate');
        enddateYn = document.querySelector('#subEndDate');
    
        subNum.forEach(li => {
            li.addEventListener('click', () => {
                let year = document.querySelector('#subPreYear').textContent;
                let month = document.querySelector('#subPreMonth').textContent;
                let dayy = String(li.textContent).padStart(2, '0');
                
                let value = year + '-' + month + '-' + dayy;
                if (!li.classList.contains('disabled')){
                    li.classList.add('select');
                    if (startdateYn.value != '' && enddateYn.value != ''){
                        subClassClear();
                        li.classList.add('select');
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (startdateYn.value == '') {
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (!calselect(startdateYn, value)){
                        subClassClear();
                        li.classList.add('select');
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    } else {
                        enddateYn.value = value;
                        mainEndDate.innerHTML = value;
                        if (validationCheck(startdateYn.value, enddateYn.value)){
                            createEnabledDate(startdateYn.value, enddateYn.value);
                        }
                        subClassClear();
                        startdateYn.value = '';
                        mainStartDate.innerHTML = '';
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    }
                }
            })
        });

    })

    subNextBtn.addEventListener('click', function(){
        //console.log(currYear, currMonth, fixedYear, fixedMonth);
        if (currYear == fixedYear && currMonth == fixedMonth+2){
            return;
        }

        if (currMonth + 1 > 12){
            currMonth = 1;
            currYear = currYear + 1;
        } else {
            currMonth = currMonth + 1;
        }
        rendersubCalendar();

        subNum = document.querySelectorAll('.subDay');
        startdateYn = document.querySelector('#subStartDate');
        enddateYn = document.querySelector('#subEndDate');

    
        subNum.forEach(li => {
            li.addEventListener('click', () => {
                let year = document.querySelector('#subPreYear').textContent;
                let month = document.querySelector('#subPreMonth').textContent;
                let dayy = String(li.textContent).padStart(2, '0');
                
                let value = year + '-' + month + '-' + dayy;
                if (!li.classList.contains('disabled')){
                    li.classList.add('select');
                    if (startdateYn.value != '' && enddateYn.value != ''){
                        subClassClear();
                        li.classList.add('select');
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (startdateYn.value == '') {
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (!calselect(startdateYn, value)){
                        subClassClear();
                        li.classList.add('select');
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    } else {
                        enddateYn.value = value;
                        mainEndDate.innerHTML = value;
                        if (validationCheck(startdateYn.value, enddateYn.value)){
                            createEnabledDate(startdateYn.value, enddateYn.value);
                        }
                        subClassClear();
                        startdateYn.value = '';
                        mainStartDate.innerHTML = '';
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    }
                }
                
                getmore = document.querySelectorAll('.subCalendar_cancel');
            	if (getmore){
            		getmore.forEach(svg => {
            			svg.addEventListener('click', function(){
            				let result = svg.parentNode.childNodes;
            				let resultSpan = '';
            				for (let i=0;i<result.length;i++){
            					if (result[i].tagName == "SPAN"){
            						resultSpan = result[i].textContent;
            						break;
            					}
            				}
            				let resultArr = resultSpan.split(' ~ ');

            				var rentAbleDate = {
            						rentAbleStartDate : resultArr[0],
            						rentAbleEndDate : resultArr[1]
            				};

            				$.ajax({
            					type: "GET",
            					url : window.location.pathname + "/rentdate/orders",
            					data : rentAbleDate,
            					contentType : "application/json",
            					success : function(response){
            						if (parseInt(response) == 0) {
            					        // 바로 버튼 삭제
            							svg.parentNode.remove();
            					    } else if (response > 0) {
            					    	alert(response);
            					        return;
            					    }
            					},
            					error : function(error){

            					}
            				})
            			})
            		})
            	}
            })
        });
    
    })

    subNum.forEach(li => {
        li.addEventListener('click', () => {
            let year = document.querySelector('#subPreYear').textContent;
            let month = document.querySelector('#subPreMonth').textContent;
            let dayy = String(li.textContent).padStart(2, '0');
            
            let value = year + '-' + month + '-' + dayy;
            if (!li.classList.contains('disabled')){
                li.classList.add('select');
                if (startdateYn.value != '' && enddateYn.value != ''){
                    subClassClear();
                    li.classList.add('select');
                    enddateYn.value = '';
                    mainEndDate.innerHTML = '';
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                } else if (startdateYn.value == '') {
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                } else if (!calselect(startdateYn, value)){
                    subClassClear();
                    li.classList.add('select');
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                    enddateYn.value = '';
                    mainEndDate.innerHTML = '';
                } else {
                    enddateYn.value = value;
                    mainEndDate.innerHTML = value;
                    // 시작일, 종료일이 리스트내에 포함되는날이 없다면
                    if (validationCheck(startdateYn.value, enddateYn.value)){
                        createEnabledDate(startdateYn.value, enddateYn.value);
                    }
                    subClassClear();
                    startdateYn.value = '';
                    mainStartDate.innerHTML = '';
                    enddateYn.value = '';
                    mainEndDate.innerHTML = '';
                }
            }
            
            getmore = document.querySelectorAll('.subCalendar_cancel');
        	if (getmore){
        		getmore.forEach(svg => {
        			svg.addEventListener('click', function(){
        				let result = svg.parentNode.childNodes;
        				let resultSpan = '';
        				for (let i=0;i<result.length;i++){
        					if (result[i].tagName == "SPAN"){
        						resultSpan = result[i].textContent;
        						break;
        					}
        				}
        				let resultArr = resultSpan.split(' ~ ');

        				var rentAbleDate = {
        						rentAbleStartDate : resultArr[0],
        						rentAbleEndDate : resultArr[1]
        				};

        				$.ajax({
        					type: "GET",
        					url : window.location.pathname + "/rentdate/orders",
        					data : rentAbleDate,
        					contentType : "application/json",
        					success : function(response){
        						if (parseInt(response) == 0) {
        					        // 바로 버튼 삭제
        							svg.parentNode.remove();
        					    } else if (response > 0) {
        					    	alert(response);
        					        return;
        					    }
        					},
        					error : function(error){

        					}
        				})
        			})
        		})
        	}
        })
    });

})

function createLi(subStart, subEnd, year, month) {
    let adate = new Date();
    var fixedYear = adate.getFullYear()
        ,fixedMonth = adate.getMonth()+1
        ,fixedDay = adate.getDate();

    let liTag = '';
    for (let j=1;j<=subStart;j++){
        liTag += `<li class='empty' disabled></li>`
    }
    if (fixedYear == year && fixedMonth == month){
        for (let i=1; i<fixedDay;i++){
            liTag += `<li class='subDay disabled' disabled>${i}</li>`
        }
        for (let i=fixedDay; i<=subEnd;i++){
            if (i == fixedDay){
                liTag += `<li class='subDay today'>${i}</li>`    
            } else {
                liTag += `<li class='subDay'>${i}</li>`
            }
        }
    } else {
        let StopDay = new Date(fixedYear, fixedMonth+2, fixedDay);
        //console.log(StopDay.getMonth());

        if (StopDay.getFullYear() == year && StopDay.getMonth() == month){
            for (let i=1; i<subEnd - (subEnd - StopDay.getDate());i++){
                liTag += `<li class='subDay'>${i}</li>`
            }
            for (let i=subEnd - (subEnd - StopDay.getDate()); i<=subEnd;i++){
                liTag += `<li class='subDay disabled' disabled>${i}</li>`
            }
        } else {
            for (let i=1; i<=subEnd;i++){
                liTag += `<li class='subDay'>${i}</li>`
            }
        }
    }

    return liTag;
}


function subClassClear(){
    let subNum = document.querySelectorAll('.subDay');
    subNum.forEach(li => {
        li.classList.remove('select');
    })
}

function calselect(startdateYn, enddateYn){
    let subEnd = new Date(enddateYn);
    let subStart = new Date(startdateYn.value);
    if (subEnd < subStart){
        return false;
    }
    return true;
}

function validationCheck(startDate, endDate){
    let startDateArr = document.querySelectorAll('.subStartDate');
    let endDateArr = document.querySelectorAll('.subEndDate');

    let startDateValues = Array.from(startDateArr).map(input => input.value);
    let endDateValues = Array.from(endDateArr).map(input => input.value);
    // console.log(startDateValues, endDateValues);

    if (startDateValues.length < 1 || startDateValues == null){
            return true;
        }
        
    let selectStartDate = new Date(startDate);
    let selectEndDate = new Date(endDate);

    for (let d=0;d<startDateValues.length;d++){
        let start = new Date(startDateValues[d]);
        let end = new Date(endDateValues[d]);
        if (selectStartDate >= start && selectStartDate <= end){
            return false;
        }
        if (selectEndDate >= start && selectEndDate <= end){
            return false;
        }
        if (selectStartDate <= start && selectEndDate >= end){
            return false;
        }
        if (selectEndDate <= start && selectEndDate >= end){
            return false;
        }
    }

    return true;
}

function createEnabledDate(startDate, endDate) {
    let box = document.querySelector('.enabledBox');
    let val = startDate + ' ~ ' + endDate;

    let scrip = '';

    scrip += '<aside class="enabledDate">';
    scrip += '<span>' + val + '</span>'
    scrip += '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512" class="subCalendar_cancel"><path d="M376.6 84.5c11.3-13.6 9.5-33.8-4.1-45.1s-33.8-9.5-45.1 4.1L192 206 56.6 43.5C45.3 29.9 25.1 28.1 11.5 39.4S-3.9 70.9 7.4 84.5L150.3 256 7.4 427.5c-11.3 13.6-9.5 33.8 4.1 45.1s33.8 9.5 45.1-4.1L192 306 327.4 468.5c11.3 13.6 31.5 15.4 45.1 4.1s15.4-31.5 4.1-45.1L233.7 256 376.6 84.5z"/></svg>'
    scrip += '<input type="text" name="rentAbleStartDate" class="subStartDate" value="' + startDate +  '" hidden><input type="text" name="rentAbleEndDate" class="subEndDate" value="' + endDate + '" hidden></aside>'

    box.innerHTML += scrip;
    getmore = document.querySelectorAll('.subCalendar_cancel');
	if (getmore){
		getmore.forEach(svg => {
			svg.addEventListener('click', function(){
				let result = svg.parentNode.childNodes;
				let resultSpan = '';
				for (let i=0;i<result.length;i++){
					if (result[i].tagName == "SPAN"){
						resultSpan = result[i].textContent;
						break;
					}
				}
				let resultArr = resultSpan.split(' ~ ');

				var rentAbleDate = {
						rentAbleStartDate : resultArr[0],
						rentAbleEndDate : resultArr[1]
				};

				$.ajax({
					type: "GET",
					url : window.location.pathname + "/rentdate/orders",
					data : rentAbleDate,
					contentType : "application/json",
					success : function(response){
						if (parseInt(response) == 0) {
					        // 바로 버튼 삭제
							svg.parentNode.remove();
					    } else if (response > 0) {
					    	alert(response);
					        return;
					    }
					},
					error : function(error){

					}
				})
			})
		})
	}
}
