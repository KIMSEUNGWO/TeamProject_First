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
                }
            }
            
        })
    });

    // 캘린더 표시에 관한 액션 시작
    const cancel = document.querySelector('.subCalendar_cancel');
    cancel.addEventListener('click', function(){
        document.querySelector('.subCalendar').classList.add('active');
    })

    // 캘린더 표시에 관한 액션 끝

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