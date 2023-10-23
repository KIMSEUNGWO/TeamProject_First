# :pushpin: 세탁 서비스 플랫폼

## 1. 제작 기간 & 참여 인원

-   2023년 07월 26일 ~ 2023년 08월 18일
-   팀 프로젝트(6인)

</br>

## 2. 사용 기술

#### `Back-end`

-   Java 11 / Gradle
-   Oracle 11g
-   Apache Tomcat 9.0.78
-   Spring Boot 2.7.14
-   MyBatis 2.3.1
-   jQuery 3.7.1
-   Servlet JSP
-   SpringToolSuite4
-   Git, Github

#### `Front-end`

-   HTML5
-   CSS3
-   Javascript

</br>

## 3. ERD 설계

![ERD](https://github.com/KIMSEUNGWO/TeamProject_First/assets/128001994/3e9bb351-557a-481d-b5de-4be08f08e394)


## 4. 기능 및 역할

이 서비스는 자신의 캠핑 용품을 타인에게 대여해주고 대가를 받는 사용자간의 거래 플랫폼입니다.
대여금액과 대여해줄 수 있는 기간을 설정해놓으면 대여를 원하는 사용자가 신청을해 거래 확정을 받는 시스템입니다.

### 나의 역할
-   마이페이지 구현 `Controller : july/lease/controller/MyPageController.java` : [코드확인](https://github.com/KIMSEUNGWO/TeamProject_First/blob/main/src/main/java/july/lease/controller/MyPageController_ksw.java) [코드확인2](https://github.com/KIMSEUNGWO/TeamProject_First/blob/main/src/main/java/july/lease/controller/MyPageRestController.java)
-   
-   HTML, CSS 70% 제작 `HTML(JSP) : /webapp/WEB-INF/, CSS : /webapp/resouces/css/` 

[PPT 자료보기](https://github.com/KIMSEUNGWO/TeamProject_First/blob/main/TrentPPT.pptx)

</br>

## 5. 핵심 트러블 슈팅

### 5.1. 캘린더

-   가장 큰 문제는 다른부분이 아닌 캘린더에서 생겼습니다. 실제로 코드 작성의 전체비중이 30~40%를 차지할 정도로 캘린더에 문제가 많이 생겼습니다.
-   그 이유는
  1.  캘린더가 사용되는 페이지가 많다 ( 헤더, 상품상세, 상품수정 )
  2.  사용되는 페이지마다 요구사항이 다르다 ( 헤더는 달력이 2개가 붙어서 사용됨, 상품상세는 판매자가 설정한 대여가능일 표시와 이미 대여된날짜 표시, 상품수정은 판매자가 대여가능일을 표시한 날짜 출력과 다중으로 선택되는 대여가능일을 설정할 수 있어야됨, 그리고 모든 검증)
  3.  이에 따라 인터넷에 배포된 캘린더를 사용할 수 없었고, 직접 제작하게됨.
  4.  자바처럼 익숙하지 않은 자바스크립트로 작성됨
-    마치 오른손으로 젓가락질을 하다가 왼손으로 바꾼것처럼 코드구현하는데 오랜 시간이 걸렸습니다.
-    결국 요구사항에 맞는 캘린더들을 구현했습니다.
-    
<details>
<summary><b>캘린더 코드</b></summary>
<div markdown="1">

`webapp/resouces/js/calendar.js`

```javascript
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


```
</div>
</details>


<details>
<summary><b>Function 기능</b></summary>
<div markdown="1">

`aug/laundry/enums/category/CategoryPriceCalculator.java`

```java
public enum CategoryPriceCalculator {
    COMMON(value -> value),
    PASS(value -> Math.round((Long)value * 0.85));

    private Function<Long, Long> expression;

    CategoryPriceCalculator(Function expression) {
        this.expression = expression;
    }

    protected Long calculate(Long value) {
        return this.expression.apply(value);
    }

    public Float percent() {
        return this.expression.apply(100L) / 100.0f;
    }
}

```

</div>
</details>

### 5.2. 페이지 위치에 따른 footer 변화

-   페이지 위치에 따라 footer의 아이콘의 상태가 변경되는것을 구현하는데 그 목록에는 메인, 주문내역, 마이페이지가 있습니다.
-   기존에는 페이지마다 footer의 상태를 Model에 넣어반환하였으나 페이지가 계속 추가되면서 유지보수가 불가능하다는 느낌이 들었고, 이 부분을 개선하기 위해 Aspect를 도입했습니다.
-   AOP를 도입하면서 Aspect의 Class가 각 Controller에 의존하게되지만 기존에 사용했던 불필요한 코드들이 한번에 제거되었습니다.

<details>
<summary><b>Aspect 코드</b></summary>
<div markdown="1">

`aug/laundry/aspects/FooterAspect.java 의 일부`

```java
@Before("execution(* aug.laundry.controller.MainController.*(..))")
public void mainpageAspect(JoinPoint joinPoint) {
    log.info("MainPageController Aspect Before 실행 : {}", joinPoint.getSignature().getName());

    Object[] args = joinPoint.getArgs();
    for (Object arg : args) {
        if (arg instanceof Model) {
            ((Model) arg).addAttribute("footer", "main");
            return;
        }

    }
}
```
<img width="426" src="https://github.com/KIMSEUNGWO/TeamProject_Second/assets/128001994/d945ee7a-a7ac-4999-a222-bf95cb838a48">
</div>
</details>


</br>

## 6. 그 외 트러블 슈팅

<details>
<summary>AJAX 통신 시 MultipartFile 전송 실패</summary>
<div markdown="1">

-   다수의 항목에 대한 다수의 Multipart 파일과 메세지 전송 시도 - 실패
-   MultipartFile JSON 변환후 시도 - 실패
-   RepairFormData ( List<MultipartFile>, String, String ) 객체 생성, Map<String, RepairFormData>를 FormData에 넣어서 전송 - 실패
-   RepairFormData ( String, String ) 분리, Map<String, RepairFormData>와 List<MultipartFile> 를 Controller에 항목개수만큼 전송 - 성공

```java
@PostMapping(value = "/repair/order")
public @ResponseBody Map<String, Boolean> repairOrder(@SessionAttribute(name = SessionConstant.LOGIN_MEMBER, required = false) Long memberId,
                                                  @SessionAttribute(name = SessionConstant.ORDERS_CONFIRM, required = false) Long ordersDetailId,
                                                  @RequestPart("repairData") Map<String, RepairFormData> repairData,
                                                  @RequestParam(name = "files", required = false) List<MultipartFile> files) {
Map<String, Boolean> resultMap = new ConcurrentHashMap<>();
System.out.println("files = " + files);

// memberId, ordersDetailId == null 일경우 false 반환
boolean status = repairService.valid(memberId, ordersDetailId);
if (!status){
    resultMap.put("status", false);
    return resultMap;
}
Repair saveRepair = repairService.insertRepair(ordersDetailId, repairData, files);

if (saveRepair != null) {
    int saveFile = fileUploadService.saveFile(files, saveRepair.getRepairId(), FileUploadType.REPAIR);
    log.info("saveFile = {}", saveFile);
}
log.info("repairData = {}", repairData);
log.info("files = {}", files);

return resultMap;
}

```

</div>
</details>

</br>

## 7. 회고 / 느낀점

> 프로젝트를 들어가며

 첫번째 프로젝트에 이어 두번째도 팀장을 맡게 되었습니다. 주제에 대한 고민을 하다가 세탁소와 사용자를 매칭 및 배달하는 중계플랫폼을 주제로 고민하다가 사용자와 회사를 직접 매칭하는 세탁플랫폼을 주제로 선정하게되었습니다.

 두번째 프로젝트의 규모가 클것이라고 예상했지만 그 예상보다 훨신 더 큰 프로젝트였습니다. 프로젝트를 한번밖에 안해본 저에겐 1달이 안되는 기간동안 마무리할 수 있을까 란 생각이 먼저 들었던것같습니다.
 
 하지만 그저 게시판정도를 만드는 프로젝트를 하고싶지는 않았습니다. 이제껏 배운걸 활용해서 만드는것도 중요하지만 안해본것을 스스로 공부해가며 활용해보는것이 더 중요하다고 생각했기 때문입니다


> 프로젝트 과정속에서

기존 사용하던 환경이
- IDE STS -> IntelliJ 변경
- JSP -> HTML, JSTL -> Thymeleaf 변경

이렇게 변경되었습니다.

당초 계획한 7명 중 1명이 개인적인 사유로 더 이상 프로젝트에 참여하지 못하게되었습니다. 1차, 2차 프로젝트 모두 한명씩 빠지게 되어 많이 아쉬웠습니다. 빠진인원에 대한 역할도 제가 맡게 되어 아침부터 저녁 10시까지 거의 매일을 코딩했던것같습니다.

제 역활은 HTML, CSS, 세탁신청, 메인페이지였습니다. 프론트의 HTML과 CSS는 부트스트랩을 전혀 사용하지않았고, 모든 디자인을 저 혼자 담당했습니다. 왜냐하면 디자인의 일관성때문에 놓치고 싶지 않았기 때문입니다. 하지만 시간은 한정되어있고, 페이지수가 30장이 넘어가면서 일부페이지는 다른분들에게 프론트 업무를 넘겼습니다. 그리고 나머지 업무에 집중했는데, 세탁신청 페이지는 몇장되지않았지만 주문 과정이기때문에 검증할것도 많고 값을 주고받는게 정말 많았습니다.


> 프로젝트를 마무리하며

결국 제 시간까지 마무리 했습니다. 짧은 시간동안 정말 많은걸 했고, 한 동안 프로젝트에 몰입 할 수 있어서 좋은 경험이었던 것 같습니다.

하지만 마무리를 하고 코드를 볼 수록 고치고 싶은 부분이 보여 아쉬움도 많이 남았습니다. 그래서 공부를 더하고 기존 코드를 리팩토링을 하고 배포까지 해보고싶단 생각이 들었습니다. 프로젝트 기간은 끝났지만 저는 이 프로젝트를 완전히 마무리 하고싶었습니다. 그래서 지금도 공부하다가 적용해보고 싶은부분은 프로젝트에 적용시키고 있습니다.

> 느낀점

1. ENUM
   
   이번 프로젝트에서 사용되었던 기술들 중 최고를 뽑으라고 한다면 ENUM 이라고 말하고싶습니다.

   공부를 하면서 ENUM의 활용에 대한 글을 읽어보았고, 이 부분을 Category에 적용시켜보았습니다. 그 결과 DB에서 하위카테고리와 자기참조로 상위카테고리를 가져오는 형식에서 DB를 사용하지않고 데이터만으로 카테고리를 조회할 수 있게 되었으며, 코드가 직관적이어서 유지보수 측면에서도 정말 좋아졌습니다. 또한 ENUM 안에 다양한 값을 열거해 하나로 묶어둘 수 있는 부분도 인상깊었습니다.
   
   JAVA를 공부하면서 ENUM을 그저 상수를 저장하는 정도로 생각했었는데 이번 프로젝트를 하면서 생각이 완전히 바뀌었습니다.

2. MVC 패턴에 대한 고민

   Controller - Service - Repository 에 방향성을 두고 웹 개발을 했지만 프로젝트를 진행하면서 아쉬운점이 있었습니다. 팀 프로젝트를 진행하며 페이지별 Controller를 만들었는데 Service - Repository까지도 페이지별로 생성하고 거기에 맞는 코드를 작성했습니다.

   처음에는 이게 문제가 될것이라고 생각하지 않았는데 개발이 진행되면서 재사용성과 유지보수 측면에서 너무 비효율적이라는 생각이 들었습니다. 기능별 모듈화도 잘 이루어지지 않았고, 이에 따라 재사용성도 크게 줄어들면서 불필요한 코드길이만 늘어나게 되었습니다. 개발해야하는 기간이 정해져있었고, 문제를 발견한 시점에 초반이 아니었기 때문에 전체적인 리팩토링은 진행하지 못한게 너무 아쉬었습니다. 그래서 프로젝트가 끝난 지금 공통된 로직들을 혼자서 리팩토링하며 다시 공부를 하고있습니다.
   
   
4. DTO의 남발

   처음 프로젝트를 시작하며 ERD를 작성하고 그에 맞게 domain을 먼저 생성해두고 프로젝트를 진행했습니다. 그런데 의도와 다르게 domain은 전혀 사용되지않았고, form 데이터를 받고, 검증하는 DTO를 생성해 query문을 작성해 CRUD를 하며 기하급수적으로 DTO객체가 늘어나기 시작했습니다. 또 하나의 문제는 dto 객체들을 하나의 package에 담겨져있었고 어떤 테이블에 사용되는 객체인지 알기 어려웠습니다. 저는 이 문제를 해결하기 위해 프로젝트개발이 끝나고 혼자서 리팩토링을 진행중입니다. JPA를 사용해서 domain을 직접 사용했고, dto의 수를 줄여가며 일관성을 높히려고 노력중입니다. 또한 사용되어야하는 DTO는 service, repository 패키지에 dto객체를 넘겨 사용 위치를 명확하게 하려고하고있습니다.

   dto 를 많이 사용하면 문제가되는지는 사실 정확하게 모르겠습니다. 하지만 제가 생각했을 때 유지보수 측면에서 좋지 못한것같습니다. 테이블에 튜플이 증가하거나 삭제가 되어야한다고 했을 때, domain 객체에 검증 dto를 상황별로 만들었다고 가정하면 모든 dto를 모두 수정해야하는 문제가 있고, 수정하면서 생기는 예기치 못한 문제들이 추가로 발생할 수 있어서 저는 dto를 최대한 사용하지 않는 방향으로 개발하는것이 좋다고 생각합니다.

5. git commit

   결론부터 말하자면 commit과 push는 기능별로 해야한다고 느꼈습니다. 프로젝트가 끝나고 git을 확인해봤는데 메세지를 남기긴했지만 너무 포괄적이고 어떤부분을 어떻게 수정했는지 전혀 표시되어있지 않아 나중에 문제가 생길 수 도 있다고 생각했습니다. 저도 마찬가지로 수정해야할 부분이 생기면 이것저것 전체적으로 수정하고 모든 파일을 commit해서 한번에 올린적이 아주 많았습니다. 협업을 해야하는 프로젝트 특성상 다른사람이 봤을 때 직관적으로 변경된 부분을 찾을 수 있어야 하기에 git에서도 메세지 기능을 넣어준것같은데 이 부분이 전혀 활용되지 않았다는게 조금 아쉬웠습니다.
