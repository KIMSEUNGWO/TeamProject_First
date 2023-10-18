window.onload = function(){
    let date = new Date();
    var fixedYear = date.getFullYear()
        ,fixedMonth = date.getMonth()+1;
    let currYear = date.getFullYear() // 현재년도
        , currMonth = date.getMonth()+1 // 현재월
        , lastDateOfMonth = new Date(currYear, currMonth, 0).getDate(); // 현재월의 마지막날
    let currentDateYear = document.querySelector('#preYear');
    let currentDateMonth = document.querySelector('#preMonth');
    let nextDateYear = document.querySelector('#nextYear');
    let nextDateMonth = document.querySelector('#nextMonth');
    
    let todaysTag = document.querySelector('.toCal .days');
    let fromdaysTag = document.querySelector('.fromCal .days');

    currentDateYear.innerHTML = currYear;   // 이번달 달력
    currentDateMonth.innerHTML = String(currMonth).padStart(2,'0');
    let currStartDay = new Date(currYear, currMonth-1, 1).getDay(); // 현재달 시작 요일

    let nextDate = new Date(currYear, currMonth+1, 0); // 다음달 달력
    nextDateYear.innerHTML = nextDate.getFullYear();
    nextDateMonth.innerHTML =  String(nextDate.getMonth()+1).padStart(2,'0');
    let nextLastDateOfMonth = nextDate.getDate();
    let nextStartDay = new Date(currYear, currMonth, 1).getDay(); // 다음달 시작 요일
    
    todaysTag.innerHTML = createPreLi(currStartDay, lastDateOfMonth , currYear, currMonth); // 왼쪽 달력
    fromdaysTag.innerHTML = createNextLi(nextStartDay,nextLastDateOfMonth, nextDate.getFullYear(), nextDate.getMonth()+1); // 오른쪽 달력

    let num = document.querySelectorAll('.day');
    let prenum = document.querySelectorAll('.preday');
    let nextnum = document.querySelectorAll('.nextday');
    let startdateYn = document.querySelector('#startDate');
    let enddateYn = document.querySelector('#endDate');
    let startIndex = document.querySelector('#startDateIndex');
    let endIndex = document.querySelector('#endDateIndex');
    
    let mainStartDate = document.querySelector('.start');
    let mainEndDate = document.querySelector('.end');
    

    let renderCalendarPrev = () => {
        nextDateYear.innerHTML = currentDateYear.textContent;
        nextDateMonth.innerHTML = currentDateMonth.textContent;
        let nextStartDay = new Date(currYear, currMonth, 1); // 다음달 시작 요일
        lastDateOfMonth = new Date(nextDateYear.textContent,nextDateMonth.textContent, 0).getDate();

        // fromdaysTag.innerHTML = createNextLi(nextStartDay.getDay(),lastDateOfMonth, nextStartDay.getFullYear(), nextStartDay.getMonth()+1); // 오른쪽 달력 업데이트
        const clonedChildren = [];
        for (const child of todaysTag.children) {
            clonedChildren.push(child.cloneNode(true));
        }
        while (fromdaysTag.firstChild) {
            fromdaysTag.removeChild(fromdaysTag.firstChild);
          }
        for (const clonedChild of clonedChildren) {
            fromdaysTag.appendChild(clonedChild);
        }

        currentDateYear.innerHTML = currYear;
        currentDateMonth.innerHTML = String(currMonth).padStart(2,'0');
        lastDateOfMonth = new Date(currentDateYear.textContent,currentDateMonth.textContent, 0).getDate();
        let currStartDay = new Date(currYear, currMonth-1, 1).getDay(); // 현재달 시작 요일

        todaysTag.innerHTML = createPreLi(currStartDay,lastDateOfMonth, currYear, currMonth); // 왼쪽 달력 업데이트
    };

    let renderCalendarNext = () => {
        currentDateYear.innerHTML = nextDateYear.textContent;
        currentDateMonth.innerHTML = nextDateMonth.textContent;
        let currStartDay = new Date(currYear, currMonth-1, 1); // 현재달 시작 요일
        lastDateOfMonth = new Date(currentDateYear.textContent,currentDateMonth.textContent, 0).getDate();
        todaysTag.innerHTML = createPreLi(currStartDay.getDay(),lastDateOfMonth, currStartDay.getFullYear(), currStartDay.getMonth()+1); // 왼쪽 달력 업데이트

        const clonedChildren = [];
        for (const child of fromdaysTag.children) {
            clonedChildren.push(child.cloneNode(true));
        }
        while (todaysTag.firstChild) {
            todaysTag.removeChild(todaysTag.firstChild);
          }
        for (const clonedChild of clonedChildren) {
            todaysTag.appendChild(clonedChild);
        }

        let newDay = new Date(currYear, currMonth + 1, 0);
        nextDateYear.innerHTML = newDay.getFullYear();
        nextDateMonth.innerHTML = String(newDay.getMonth()+1).padStart(2,'0');
        lastDateOfMonth = newDay.getDate();
        let nextStartDay = new Date(currYear, currMonth, 1); // 다음달 시작 요일
        lastDateOfMonth = new Date(nextDateYear.textContent,nextDateMonth.textContent, 0).getDate();
        fromdaysTag.innerHTML = createNextLi(nextStartDay.getDay(),lastDateOfMonth,nextStartDay.getFullYear(),nextStartDay.getMonth()+1); // 오른쪽 달력 업데이트
    };

    let preBtn = document.querySelector('#preBtn');
    let nextBtn = document.querySelector('#nextBtn');

    preBtn.addEventListener('click', function(){
        if (currYear == fixedYear && currMonth == fixedMonth){
            return;
        }
        if (currMonth -1 < 1){
            currMonth = 12;
            currYear = currYear - 1;
        } else {
            currMonth = currMonth - 1;
        }
        renderCalendarPrev();
        //console.log(currYear, currMonth)
        num = document.querySelectorAll('.day');
        prenum = document.querySelectorAll('.preday');
        nextnum = document.querySelectorAll('.nextday');
        startdateYn = document.querySelector('#startDate');
        enddateYn = document.querySelector('#endDate');
        startIndex = document.querySelector('#startDateIndex');
        endIndex = document.querySelector('#endDateIndex');

        num.forEach((li, index) => {
            li.addEventListener('click', function(){
                if (!li.classList.contains('disabled')){
                    if (startIndex.value != '' && endIndex.value != ''){
                        endIndex.value = ''
                        startIndex.value = index;
                    } else if (startIndex.value == ''){
                        startIndex.value = index;
                    } else if (startIndex.value > index){
                        startIndex.value = index;
                    } else {
                        endIndex.value = index;
                    }
                }
            })
        })
    
        prenum.forEach(li => {
            li.addEventListener('click', () => {
                let year = document.querySelector('#preYear').textContent;
                let month = document.querySelector('#preMonth').textContent;
                let dayy = String(li.textContent).padStart(2, '0');
                
                let value = year + '-' + month + '-' + dayy;
                if (!li.classList.contains('disabled')){
                    li.classList.add('select');
                    if (startdateYn.value != '' && enddateYn.value != ''){
                        classClear();
                        li.classList.add('select');
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (startdateYn.value == '') {
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (!calselect(startdateYn, value)){
                        classClear();
                        li.classList.add('select');
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    } else {
                        enddateYn.value = value;
                        mainEndDate.innerHTML = value;
                    }
                    if (enddateYn.value != '' && startdateYn.value != ''){
                        drawingLine();
                    }
                }
                
            })
        });
    
        nextnum.forEach(li => {
            li.addEventListener('click', () => {
                let year = document.querySelector('#nextYear').textContent;
                let month = document.querySelector('#nextMonth').textContent;
                let dayy = String(li.textContent).padStart(2, '0');
                let value = year + '-' + month + '-' + dayy;
                if (!li.classList.contains('disabled')){
                    li.classList.add('select');

                    if (startdateYn.value != '' && enddateYn.value != ''){
                        classClear();
                        li.classList.add('select');
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (startdateYn.value == '') {
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (!calselect(startdateYn, value)){
                        classClear();
                        li.classList.add('select');
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    } else {
                        enddateYn.value = value;
                        mainEndDate.innerHTML = value;
                    }
                    if (enddateYn.value != '' && startdateYn.value != ''){
                        drawingLine();
                    }
                }
                
            })
        })



    })

    nextBtn.addEventListener('click', function(){
        if (currYear == fixedYear && currMonth == fixedMonth+2){
            return;
        }
        if (currMonth + 1 > 12){
            currMonth = 1;
            currYear = currYear + 1;
        } else {
            currMonth = currMonth + 1;
        }
        renderCalendarNext();

        num = document.querySelectorAll('.day');
        prenum = document.querySelectorAll('.preday');
        nextnum = document.querySelectorAll('.nextday');
        startdateYn = document.querySelector('#startDate');
        enddateYn = document.querySelector('#endDate');
        startIndex = document.querySelector('#startDateIndex');
        endIndex = document.querySelector('#endDateIndex');

        num.forEach((li, index) => {
            li.addEventListener('click', function(){
                if (!li.classList.contains('disabled')){
                    if (startIndex.value != '' && endIndex.value != ''){
                        endIndex.value = ''
                        startIndex.value = index;
                    } else if (startIndex.value == ''){
                        startIndex.value = index;
                    } else if (startIndex.value > index){
                        startIndex.value = index;
                    } else {
                        endIndex.value = index;
                    }
                }
            })
        })
    
        prenum.forEach(li => {
            li.addEventListener('click', () => {
                let year = document.querySelector('#preYear').textContent;
                let month = document.querySelector('#preMonth').textContent;
                let dayy = String(li.textContent).padStart(2, '0');
                
                let value = year + '-' + month + '-' + dayy;
                if (!li.classList.contains('disabled')){
                    li.classList.add('select');
                    if (startdateYn.value != '' && enddateYn.value != ''){
                        classClear();
                        li.classList.add('select');
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (startdateYn.value == '') {
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (!calselect(startdateYn, value)){
                        classClear();
                        li.classList.add('select');
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    } else {
                        enddateYn.value = value;
                        mainEndDate.innerHTML = value;
                    }
                    if (enddateYn.value != '' && startdateYn.value != ''){
                        drawingLine();
                    }
                }
                
            })
        });
    
        nextnum.forEach(li => {
            li.addEventListener('click', () => {
                let year = document.querySelector('#nextYear').textContent;
                let month = document.querySelector('#nextMonth').textContent;
                let dayy = String(li.textContent).padStart(2, '0');
                let value = year + '-' + month + '-' + dayy;
                if (!li.classList.contains('disabled')){
                    li.classList.add('select');
                    if (startdateYn.value != '' && enddateYn.value != ''){
                        classClear();
                        li.classList.add('select');
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (startdateYn.value == '') {
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                    } else if (!calselect(startdateYn, value)){
                        classClear();
                        li.classList.add('select');
                        startdateYn.value = value;
                        mainStartDate.innerHTML = value;
                        enddateYn.value = '';
                        mainEndDate.innerHTML = '';
                    } else {
                        enddateYn.value = value;
                        mainEndDate.innerHTML = value;
                    }
                    if (enddateYn.value != '' && startdateYn.value != ''){
                        drawingLine();
                    }
                }
                
            })
        })
    })
    num.forEach((li, index) => {
        li.addEventListener('click', function(){
            if (!li.classList.contains('disabled')){
                if (startIndex.value != '' && endIndex.value != ''){
                    endIndex.value = ''
                    startIndex.value = index;
                } else if (startIndex.value == ''){
                    startIndex.value = index;
                } else if (startIndex.value > index){
                    startIndex.value = index;
                } else {
                    endIndex.value = index;
                }
            }
        })
    })

    prenum.forEach(li => {
        li.addEventListener('click', () => {
            let year = document.querySelector('#preYear').textContent;
            let month = document.querySelector('#preMonth').textContent;
            let dayy = String(li.textContent).padStart(2, '0');
            
            let value = year + '-' + month + '-' + dayy;
            if (!li.classList.contains('disabled')){
                li.classList.add('select');
                if (startdateYn.value != '' && enddateYn.value != ''){
                    classClear();
                    li.classList.add('select');
                    enddateYn.value = '';
                    mainEndDate.innerHTML = '';
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                } else if (startdateYn.value == '') {
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                } else if (!calselect(startdateYn, value)){
                    classClear();
                    li.classList.add('select');
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                    enddateYn.value = '';
                    mainEndDate.innerHTML = '';
                } else {
                    enddateYn.value = value;
                    mainEndDate.innerHTML = value;
                }
                if (enddateYn.value != '' && startdateYn.value != ''){
                    drawingLine();
                }
            }
            
        })
    });

    nextnum.forEach(li => {
        li.addEventListener('click', () => {
            let year = document.querySelector('#nextYear').textContent;
            let month = document.querySelector('#nextMonth').textContent;
            let dayy = String(li.textContent).padStart(2, '0');
            let value = year + '-' + month + '-' + dayy;
            if (!li.classList.contains('disabled')){
                li.classList.add('select');
                if (startdateYn.value != '' && enddateYn.value != ''){
                    classClear();
                    li.classList.add('select');
                    enddateYn.value = '';
                    mainEndDate.innerHTML = '';
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                } else if (startdateYn.value == '') {
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                } else if (!calselect(startdateYn, value)){
                    classClear();
                    li.classList.add('select');
                    startdateYn.value = value;
                    mainStartDate.innerHTML = value;
                    enddateYn.value = '';
                    mainEndDate.innerHTML = value;
                } else {
                    enddateYn.value = value;
                    mainEndDate.innerHTML = value;
                }
                if (enddateYn.value != '' && startdateYn.value != ''){
                    drawingLine();
                }
            }
            
        })
    })

    // 캘린더 표시에 관한 액션 시작
    const searchbar = document.querySelector('#search');
    const cancel = document.querySelector('.calendar_cancel');
    searchbar.addEventListener('click', function(event) {
        document.querySelector('.calendar').classList.remove('active');
    });
    cancel.addEventListener('click', function(){
        document.querySelector('.calendar').classList.add('active');
    })
    // 캘린더 표시에 관한 액션 끝

}

function createPreLi(start, end, year, month) {
    let adate = new Date();
    var fixedYear = adate.getFullYear()
        ,fixedMonth = adate.getMonth()+1
        ,fixedDay = adate.getDate();

    let liTag = '';
    for (let j=1;j<=start;j++){
        liTag += `<li class='empty' disabled></li>`
    }
    if (fixedYear == year && fixedMonth == month){
        for (let i=1; i<fixedDay;i++){
            liTag += `<li class='day preday disabled' disabled>${i}</li>`
        }
        for (let i=fixedDay; i<=end;i++){
            if (i == fixedDay){
                liTag += `<li class='day preday today'>${i}</li>`    
            } else {
                liTag += `<li class='day preday'>${i}</li>`
            }
        }
    } else {
        for (let i=1; i<=end;i++){
            liTag += `<li class='day preday'>${i}</li>`
        }
    }

    return liTag;
}

function createNextLi(start, end, year, month) {
    let adate = new Date();
    var fixedYear = adate.getFullYear()
        ,fixedMonth = adate.getMonth()+1
        ,fixedDay = adate.getDate();

    let liTag = '';
    for (let j=1;j<=start;j++){
        liTag += `<li class='empty' disabled></li>`
    }
    if (fixedYear == year && fixedMonth == month){
        for (let i=1; i<fixedDay;i++){
            liTag += `<li class='day nextday disabled' disabled>${i}</li>`
        }
        for (let i=fixedDay; i<=end;i++){
            if (i == fixedDay){
                liTag += `<li class='day nextday today'>${i}</li>`    
            } else {
                liTag += `<li class='day nextday'>${i}</li>`
            }
        }
    } else {
        let StopDay = new Date(fixedYear, fixedMonth+2, fixedDay);

        if (StopDay.getFullYear() == year && StopDay.getMonth()+1 == month){
            for (let i=1; i<end - (end - StopDay.getDate());i++){
                liTag += `<li class='day nextday'>${i}</li>`
            }
            for (let i=end - (end - StopDay.getDate()); i<=end;i++){
                liTag += `<li class='day nextday disabled' disabled>${i}</li>`
            }
        } else {
            for (let i=1; i<=end;i++){
                liTag += `<li class='day nextday'>${i}</li>`
            }
        }
    }

    return liTag;
}

function classClear(){
    let num = document.querySelectorAll('.day');
    num.forEach(li => {
        li.classList.remove('select');
    })
}

function calselect(startdateYn, enddateYn){
    let end = new Date(enddateYn);
    let start = new Date(startdateYn.value);
    if (end < start){
        return false;
    }
    return true;
}

function drawingLine(){
    startIndex = parseInt(document.querySelector('#startDateIndex').value);
    endIndex = parseInt(document.querySelector('#endDateIndex').value);
    index = document.querySelectorAll('.day');
    for (let k=startIndex;k<=endIndex;k++){
        index[k].classList.add('select');
    }
    return;
}