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
    
    let nodes = Array.from(createLi(currStartDay, lastDateOfMonth , currYear, currMonth)); // 왼쪽 달력
    for (var cnt of nodes){
        todaysTag.appendChild(cnt);
    }

    let subNum = document.querySelectorAll('.subDay');
    let startdateYn = document.querySelector('#subStartDate');
    let enddateYn = document.querySelector('#subEndDate');    
    let mainStartDate = document.querySelector('.subStart');
    let mainEndDate = document.querySelector('.subEnd');

    // 대여가능날과 대여불가능한날 정보
    let possibleDate = document.querySelector('#possibleDate');
    let impossibleDate = document.querySelector('#impossibleDate');

    let rendersubCalendar = () => {
        currentDateYear.innerHTML = currYear;
        currentDateMonth.innerHTML = String(currMonth).padStart(2,'0');
        lastDateOfMonth = new Date(currentDateYear.textContent,currentDateMonth.textContent, 0).getDate();
        let currStartDay = new Date(currentDateYear.textContent, currentDateMonth.textContent-1, 1).getDay(); // 현재달 시작 요일
        let nodes = Array.from(createLi(currStartDay, lastDateOfMonth , currYear, currMonth)); // 왼쪽 달력
        todaysTag.innerHTML = '';
        for (var cnt of nodes){
            todaysTag.appendChild(cnt);
        }
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
                        if (!checkDate(document.querySelector('.subDays').childNodes,startdateYn.value, enddateYn.value)){
                            enddateYn.value = '';
                            mainEndDate.innerHTML = '';
                        }
                    }
                }
                
            })
        });

    })

    subNextBtn.addEventListener('click', function(){
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
                        if (!checkDate(document.querySelector('.subDays').childNodes,startdateYn.value, enddateYn.value)){
                            enddateYn.value = '';
                            mainEndDate.innerHTML = '';
                        }
                    }
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
                    if (!checkDate(document.querySelector('.subDays').childNodes,startdateYn.value, enddateYn.value)){
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    }
                }
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
            liTag += `<li class='subDay disabled'>${i}</li>`
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

        if (StopDay.getFullYear() == year && StopDay.getMonth() == month){
            for (let i=1; i<subEnd - (subEnd - StopDay.getDate());i++){
                liTag += `<li class='subDay'>${i}</li>`
            }
            for (let i=subEnd - (subEnd - StopDay.getDate()); i<=subEnd;i++){
                liTag += `<li class='subDay disabled'>${i}</li>`
            }
        } else {
            for (let i=1; i<=subEnd;i++){
                liTag += `<li class='subDay'>${i}</li>`
            }
        }
    }
    
    return isPossible(year, month, liTag);
}

function changeArr(dateValue){

    let arr = String(dateValue).split(","); // 한 묶음으로 split
    let doubleArr = []
    for (var m=0; m<arr.length;m++){
        doubleArr.push(arr[m].split('|')); // 시작일, 종료일로 split
    }
    return doubleArr;
}

function isPossible(year, month, tag) {
    let tempUl = document.createElement('ul');
    tempUl.innerHTML = tag;
    let tempUlList = tempUl.childNodes;
    let pDate = changeArr(possibleDate.value);
    let impDate = changeArr(impossibleDate.value);
    // 가능한 날
    tempUlList.forEach((li, index) => {
        if (!li.classList.contains('empty') && !li.classList.contains('today') && !li.classList.contains('disabled')){
            let possible = new Date(year, month, li.textContent); // 기준일
            if (possibleDate.value != '' && possibleDate.value != null){
	            for (var m = 0;m<pDate.length;m++){
	                let splitStartDate = pDate[m][0].split('-');
	                let splitEnddate = pDate[m][1].split('-');
	                let possibleStartDate = new Date(splitStartDate[0], splitStartDate[1], splitStartDate[2]); // 가능한날짜 시작일
	                let possibleEndDate = new Date(splitEnddate[0], splitEnddate[1], splitEnddate[2]); // 가능한날짜 종료일
	
	                if (possibleStartDate <= possible && possibleEndDate >= possible){ // 기준일이 시작일보다 크고 종료일보다 작을때
	                    li.classList.add('possible');
	                }
	            }
	        }
	        if (impossibleDate.value != '' && impossibleDate.value != null){
	            for (var n = 0;n<impDate.length;n++){
	                let splitStartDate = impDate[n][0].split('-');
	                let splitEnddate = impDate[n][1].split('-');
	                let impossibleStartDate = new Date(splitStartDate[0], splitStartDate[1], splitStartDate[2]); // 불가능한날짜 시작일
	                let impossibleEndDate = new Date(splitEnddate[0], splitEnddate[1], splitEnddate[2]); // 불가능한날짜 종료일
	
	                if (impossibleStartDate <= possible && impossibleEndDate >= possible){ // 기준일이 시작일보다 크고 종료일보다 작을때
	                    li.classList.add('impossible');
	                    li.classList.remove('possible');
	                    li.classList.add('disabled');
	                }
	            }
	       }
        }
    })

    tempUlList.forEach(li => {
        if (!li.classList.contains('possible')){
            li.classList.add('disabled');
        } 
    });
    return tempUlList;
}

function checkDate(subDays, startDate, endDate) {
    let startDateValue = Number(startDate.split('-')[2]);
    let endDateValue = Number(endDate.split('-')[2]);
    for (const li of subDays) {
        if (!li.classList.contains('empty')) {
            if (startDateValue <= Number(li.textContent) && endDateValue >= Number(li.textContent)) {
                if (li.classList.contains('impossible') || !li.classList.contains('possible')) {
                    return false;
                }
            }
        }
    }
    return true;
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