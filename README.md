# :pushpin: 캠핑용품 대여 플랫폼

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

-   메인페이지(리스트) 구현 `Controller : july/lease/controller/ListController.java` : [코드확인](https://github.com/KIMSEUNGWO/TeamProject_First/blob/main/src/main/java/july/lease/controller/ListController_ksw.java)
-   마이페이지 구현 `Controller : july/lease/controller/MyPageController.java` : [코드확인](https://github.com/KIMSEUNGWO/TeamProject_First/blob/main/src/main/java/july/lease/controller/MyPageController_ksw.java) [코드확인2](https://github.com/KIMSEUNGWO/TeamProject_First/blob/main/src/main/java/july/lease/controller/MyPageRestController.java)
-   HTML, CSS 70% 제작 `HTML(JSP) : /webapp/WEB-INF/, CSS : /webapp/resouces/css/` 

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

### 5.2. Interceptor의 사용과 PathVariable

-   인터셉터를 적용시켜보고자 Mypage에 Interceptor를 설정해보았습니다.

<details>
<summary><b>MyPageInterceptor</b></summary>
<div markdown="1">

`july/lease/interceptor/MyPageInterceptor.java`

```java
@Slf4j
public class MyPageInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String requestURI = request.getRequestURI();
		String requestURI2 = requestURI.replaceAll("/members/", "");
		requestURI2 = requestURI2.substring(0, requestURI2.indexOf("/") == -1 ? requestURI2.length() : requestURI2.indexOf("/"));
		String memberId = requestURI2; 
		log.info("마이페이지 인증체크 인터셉터 실행 {}, memberId = {}", requestURI2, memberId);

		HttpSession session = request.getSession();
		log.info("sessionValue = {}", session.getAttribute("memberId"));
		String sessionValue = String.valueOf(session.getAttribute("memberId"));
		if (session == null || sessionValue == null || !memberId.equals(sessionValue)) {
			log.info("미인증 사용자 요청");
			log.info("접속사용자 = {}, 요청된 memberId = {}", sessionValue, memberId);
			response.sendRedirect("/login?redirectURL=" + requestURI);
			return false;
		}
		log.info("인증된 사용자 접속 승인");  
		return true;
	}

}
```

`july/lease/config/InterceptorWebConfig.java`

```java
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// /members/ 이후 주소에 대한 인터셉터 (세션ID와 요청memberID 비교)
		registry.addInterceptor(new MyPageInterceptor())
				.order(1)
				.addPathPatterns("/members/**") // /members/ 하위 전 myPageInterceptor 적용
				.excludePathPatterns("/resources/js/**", "/resources/css/**", "/*.ico", "/error", "/members/add"); // 예외 URI
				// members/add = 회원가입
	}
```


</div>
</details>

-   하지만 여기에는 문제가 있었습니다. PathVariable을 사용해 회원번호를 조회하게되면서 로그인을 하지않는 회원이 마이페이지로 접속할경우 경로가 이상해지는걸 확인했습니다.
-   정상적인 경로는 members/(회원번호) 가 들어와야하지만 members// 이런식으로 경로가 설정되는데 이러한 경로는 인터셉터에서 회원아이디를 조회할때 문제가 생겼습니다.
-   그래서 저는 인터셉터에서 아래와같이 수정하여 문제를 해결했습니다.

```java
String requestURI = request.getRequestURI();
String requestURI2 = requestURI.replaceAll("/members/", "");
requestURI2 = requestURI2.substring(0, requestURI2.indexOf("/") == -1 ? requestURI2.length() : requestURI2.indexOf("/"));
String memberId = requestURI2;
  ```

</br>

</br>

## 7. 회고 / 느낀점

> 프로젝트를 들어가며

 첫 프로젝트에서 팀장을 맡게되었습니다. 어떤 이유에선지는 모르겠지만 추천이되어서 하게되어서 부담감을 가지고 시작하게 된것같습니다. 프로젝트에 대해서 전무한데 주제부터 시작해서 모든것을 이끌어가려고 하다보니 쉽지 않았습니다.


> 프로젝트 과정속에서

  제 역할은 마이페이지 제작이었습니다. 하지만 어쩌다보니 HTML과 CSS, 캘린더까지 맡게되어 업무가 생각보다 많았던것같습니다.

  그런데 메인페이지를 담당하던 사람이 학원을 그만두면서 그 역할을 제가 이어받게 되어 메인페이지안에 제품리스트를 제가 만들게 되었습니다. 

  Git, Github를 처음 사용해보았습니다.

> 프로젝트를 마무리하며

  프로젝트를 마무리해보니 캘린더 하나의 제작기간이 길었던 만큼 기억에 남는건 캘린더밖에 없었던것같습니다. 그래도 나머지 부분에서는 크게 어려움을 느끼지 않고 잘 넘어갔던것 같습니다. 

> 느낀점

1. JSP의 불편함
   
   이번 프로젝트에서 가장 불편했던건 JSTL과 JSP였던것같습니다. JSTL의 사용은 간단하지만 코드가 지저분해지고 JSP파일을 직접 실행하지 못해 VSCode를 사용해 수정하곤 해야헸습니다.

2. 코드작성보단 Query문 작성이 대부분

   제가 담당했던 마이페이지는 거의 모든 페이지가 query문으로 도배가 되어있습니다. 마이페이지 특성상 판매자와 구매자에 대한 총계, 부분합계 등 통계를 내야하는부분이 많았습니다.
   이후 JPA에 대해서 공부했고, 리팩토링을 하면서 JPA로 변경하려고 합니다.

<details>
<summary><b>MyPageMapper.xml</b></summary>
<div markdown="1">
    
   ```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="july.lease.dao.mypage.MyPageMapper">

	<select id="findByName" resultType="String">
		SELECT MEMBER_NAME
		FROM MEMBER
		WHERE member_id = #{memberId}
	</select>
	
	<select id="findByOrderCount" resultType="Integer"> <!--내가 대여한 물건이 정상적으로 반납된 건수 -->
		SELECT COUNT(ORDER_CONFIRM_STATUS)
		FROM ORDERS
		WHERE MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 5 <!-- 4 : 대여가 완료된 주문건 -->
	</select>
	
	<select id="findBySellCount" resultType="Integer"> <!-- 판매건수 -->
		SELECT COUNT(*)
		FROM ORDERS
		WHERE ORDER_CONFIRM_STATUS = 5 AND PRODUCT_ID IN (
									                        SELECT PRODUCT_ID
									                        FROM PRODUCT
									                        WHERE MEMBER_ID = #{memberId}
									                        )
	</select>
	
	<select id="findByMyitemsCount" resultType="Integer"> <!--내 물건(현재 대여가능한 물건의 개수) -->
		SELECT COUNT(*)
		FROM PRODUCT
		WHERE MEMBER_ID = #{memberId} AND PRODUCT_END_STATUS = 'N'
	</select>
	
	<select id="findByWaitItems" resultType="Integer"> <!-- 대여확정을 기다리는 건수 -->
		SELECT COUNT(ORDER_CONFIRM_STATUS)
		FROM ORDERS
		WHERE MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 2 <!-- 2 : 대기중인 건수 -->
	</select>
	
	<select id="confirmCheck" resultType="july.lease.dto.MyPageMainInfoDto"> <!--현재 대여요청 -->
			SELECT ORDERS.PRODUCT_ID as productId, P_IMAGE.STORE_IMAGE_NAME as productImage, PRODUCT_NAME as productName, TO_CHAR(ORDER_RENT_START_DATE, 'YYYY-MM-DD') as startDate, TO_CHAR(ORDER_RENT_END_DATE,'YYYY-MM-DD') as endDate, ORDER_RENT_END_DATE - ORDER_RENT_START_DATE + 1 as countDate, TO_CHAR(ORDER_PRICE, 'FM999,999,999') AS price, MEMBER.MEMBER_NICKNAME AS memberName, ORDERS.ORDER_CONFIRM_STATUS AS orderConfirmStatus, ORDERS.ORDER_ID AS orderId, ORDERS.MEMBER_ID AS memberId
		    FROM ORDERS
		    JOIN MEMBER ON (MEMBER.MEMBER_ID = ORDERS.MEMBER_ID)
		    JOIN PRODUCT ON (ORDERS.PRODUCT_ID =  PRODUCT.PRODUCT_ID)
		    LEFT JOIN (
		        SELECT PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
		        FROM PRODUCT_IMAGE
		    ) P_IMAGE ON (PRODUCT.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
		    WHERE PRODUCT.MEMBER_ID = ${memberId} AND ORDER_CONFIRM_STATUS IN (2, 3)
		    ORDER BY ORDER_CREATE_DATE DESC
	</select>
	
		<select id="confirmCheckAJAX" resultType="july.lease.dto.MyPageMainInfoDto"> <!--현재 대여요청 -->
		SELECT A.productId productId, A.productImage as productImage, A.productName as productName, A.startDate as startDate, A.endDate endDate, A.countDate as countDate, A.price as price, A.memberName as memberName,  A.orderConfirmStatus AS orderConfirmStatus, A.ORDER_ID as orderId, A.memberId as memberId
		FROM (
		    SELECT ORDERS.PRODUCT_ID as productId, P_IMAGE.STORE_IMAGE_NAME as productImage, PRODUCT_NAME as productName, TO_CHAR(ORDER_RENT_START_DATE, 'YYYY-MM-DD') as startDate, TO_CHAR(ORDER_RENT_END_DATE,'YYYY-MM-DD') as endDate, ORDER_RENT_END_DATE - ORDER_RENT_START_DATE + 1 as countDate, TO_CHAR(ORDER_PRICE, 'FM999,999,999') AS price, MEMBER.MEMBER_NICKNAME AS memberName,  ORDERS.ORDER_CONFIRM_STATUS AS orderConfirmStatus, ORDERS.ORDER_ID AS ORDER_ID, ORDERS.MEMBER_ID AS memberId, ROW_NUMBER() OVER (ORDER BY ORDER_CREATE_DATE DESC) AS RNUM
		    FROM ORDERS
		    JOIN MEMBER ON (MEMBER.MEMBER_ID = ORDERS.MEMBER_ID)
		    JOIN PRODUCT ON (ORDERS.PRODUCT_ID =  PRODUCT.PRODUCT_ID)
		    LEFT JOIN (
		        SELECT PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
		        FROM PRODUCT_IMAGE
		    ) P_IMAGE ON (PRODUCT.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
		    WHERE PRODUCT.MEMBER_ID = ${memberId} AND ORDER_CONFIRM_STATUS IN (2, 3)
		    ORDER BY ORDER_CREATE_DATE DESC
		) A
		WHERE RNUM BETWEEN #{startRow} AND #{endRow}
	</select>
	
	<select id="findBySellingItem" resultType="Integer"> <!-- 현재 판매중인 물건의 갯수 -->
		SELECT COUNT(PRODUCT_ID) 
		FROM PRODUCT
		WHERE MEMBER_ID = #{memberId} AND PRODUCT_END_STATUS = 'N'
	</select>
	
	<select id="todayReturnItem" resultType="Integer"> <!-- 오늘 반납예정인 물건의 갯수 -->
		SELECT COUNT(ORDER_ID)
		FROM ORDERS
		JOIN PRODUCT ON (ORDERS.PRODUCT_ID = PRODUCT.PRODUCT_ID)
		WHERE PRODUCT.MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 4 AND TO_CHAR(SYSDATE, 'YYYYMMDD') = TO_CHAR(ORDER_RENT_END_DATE, 'YYYYMMDD')
	</select>
	
	<select id="totalBenefit" resultType="String"> <!-- 총 수익금 -->
		SELECT TO_CHAR(COALESCE(SUM(ORDER_PRICE),0), 'FM999,999,999')
		FROM ORDERS
		JOIN PRODUCT ON (PRODUCT.PRODUCT_ID = ORDERS.PRODUCT_ID)
		WHERE PRODUCT.MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 5
	</select>

	<select id="itemSellList" resultType="july.lease.dto.MyPageSellItemsDto"> <!-- 4: 거래가 끝난 물건 단위의 총 대여수, 총 수익금 조회 -->
		SELECT PRODUCT.PRODUCT_ID AS itemId, STORE_IMAGE_NAME as itemImage, PRODUCT_NAME as itemTitle, TO_CHAR(PRODUCT_PRICE, 'FM999,999,999') AS itemPrice, PRODUCT_STATUS as itemStatus, COALESCE(A.CNT, 0) as itemTotalCount, COALESCE(TO_CHAR(A.TOTAL, 'FM999,999,999'), '0') as itemTotalPrice
		FROM PRODUCT
		LEFT JOIN (
				        SELECT PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
				        FROM PRODUCT_IMAGE
				    ) P_IMAGE ON (PRODUCT.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
		LEFT JOIN (
		            SELECT PRODUCT.PRODUCT_ID, COUNT(PRODUCT.PRODUCT_ID) AS CNT, SUM(ORDER_PRICE) AS TOTAL
		            FROM ORDERS
		            JOIN PRODUCT ON (ORDERS.PRODUCT_ID = PRODUCT.PRODUCT_ID)
		            WHERE PRODUCT.MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 5
		            GROUP BY PRODUCT.PRODUCT_ID
		            ) A ON (A.PRODUCT_ID = PRODUCT.PRODUCT_ID)
		WHERE PRODUCT.MEMBER_ID = #{memberId} AND PRODUCT_END_STATUS = 'N'
		ORDER BY PRODUCT.PRODUCT_ID
	</select>
	
	<select id="itemSellListAJAX" resultType="july.lease.dto.MyPageSellItemsDto"> <!-- 4: 거래가 끝난 물건 단위의 총 대여수, 총 수익금 조회 -->
		SELECT A.itemId as itemId, A.itemImage as itemImage, A.itemName as itemTitle, A.PRODUCT_PRICE as itemPrice, A.itemStatus as itemStatus, A.itemTotalCount as itemTotalCount, A.itemTotalPrice as itemTotalPrice
		FROM (
				SELECT PRODUCT.PRODUCT_ID AS itemId, STORE_IMAGE_NAME as itemImage, PRODUCT_NAME as itemName, TO_CHAR(PRODUCT_PRICE, 'FM999,999,999') AS PRODUCT_PRICE, PRODUCT_STATUS as itemStatus, COALESCE(A.CNT, 0) as itemTotalCount, COALESCE(TO_CHAR(A.TOTAL, 'FM999,999,999'), '0') as itemTotalPrice, ROW_NUMBER() OVER (ORDER BY PRODUCT.PRODUCT_ID) AS RNUM
				FROM PRODUCT
				LEFT JOIN (
						        SELECT PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
						        FROM PRODUCT_IMAGE
						    ) P_IMAGE ON (PRODUCT.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
				LEFT JOIN (
				            SELECT PRODUCT.PRODUCT_ID, COUNT(PRODUCT.PRODUCT_ID) AS CNT, SUM(ORDER_PRICE) AS TOTAL
				            FROM ORDERS
				            JOIN PRODUCT ON (ORDERS.PRODUCT_ID = PRODUCT.PRODUCT_ID)
				            WHERE PRODUCT.MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 5
				            GROUP BY PRODUCT.PRODUCT_ID
				            ) A ON (A.PRODUCT_ID = PRODUCT.PRODUCT_ID)
				WHERE PRODUCT.MEMBER_ID = #{memberId} AND PRODUCT_END_STATUS = 'N'
				ORDER BY PRODUCT.PRODUCT_ID
		    ) A
		WHERE RNUM BETWEEN #{startRow} AND #{endRow}
	</select>
	
	

	<select id="endItems" resultType="july.lease.domain.Product"> <!-- 판매중지가 된 물건리스트 조회 -->
		SELECT PRODUCT_ID, MEMBER_ID, CATEGORY_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_CONTENT, LOCATION, PRODUCT_CREATE_DATE, PRODUCT_END_STATUS
		FROM PRODUCT
		WHERE MEMBER_ID = #{memberId} AND PRODUCT_END_STATUS = 'Y'
	</select>
	
	<!-- 구매페이지 -->
	
	<select id="orderItems" resultType="july.lease.dto.MyPageOrderItemsDto"> <!-- 판매중지가 된 물건리스트 조회 -->
		SELECT ORDERS.PRODUCT_ID AS productId, STORE_IMAGE_NAME as productImage, PRODUCT_NAME as productName, TO_CHAR(ORDER_RENT_START_DATE, 'YYYY-MM-DD') as startDate, TO_CHAR(ORDER_RENT_END_DATE, 'YYYY-MM-DD') as endDate, ORDER_RENT_END_DATE - ORDER_RENT_START_DATE + 1 AS rentDate, MEMBER_NICKNAME as sellerName, DECODE(ORDER_CONFIRM_STATUS, '1', '대여거절','2','대기중','3','대여확정', '4','대여중', '거래완료') as productStatus
		FROM ORDERS
		LEFT JOIN (
				        SELECT PRODUCT_IMAGE.PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_IMAGE.PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
				        FROM PRODUCT_IMAGE
				    ) P_IMAGE ON (ORDERS.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
		JOIN PRODUCT ON (ORDERS.PRODUCT_ID = PRODUCT.PRODUCT_ID)
		JOIN MEMBER ON (MEMBER.MEMBER_ID = PRODUCT.MEMBER_ID)
		WHERE ORDERS.MEMBER_ID = #{memberId}
		ORDER BY ORDER_ID DESC
	</select>
	
	<select id="orderItemsAJAX" resultType="july.lease.dto.MyPageOrderItemsDto"> <!-- 판매중지가 된 물건리스트 조회 -->
		SELECT productId, productImage, productName, startDate, endDate, rentDate, sellerName, productStatus
		FROM  (
		    SELECT ORDERS.PRODUCT_ID AS productId, STORE_IMAGE_NAME as productImage, PRODUCT_NAME as productName, TO_CHAR(ORDER_RENT_START_DATE, 'YYYY-MM-DD') as startDate, TO_CHAR(ORDER_RENT_END_DATE, 'YYYY-MM-DD') as endDate, ORDER_RENT_END_DATE - ORDER_RENT_START_DATE + 1 AS rentDate, MEMBER_NICKNAME as sellerName, DECODE(ORDER_CONFIRM_STATUS, '1','대여거절','2','대기중','3','대여확정','4','대여중', '거래완료') as productStatus, ROW_NUMBER() OVER (ORDER BY ORDER_ID DESC) AS RNUM
		    FROM ORDERS
		    LEFT JOIN (
					        SELECT PRODUCT_IMAGE.PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_IMAGE.PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
					        FROM PRODUCT_IMAGE
					    ) P_IMAGE ON (ORDERS.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
		    JOIN PRODUCT ON (ORDERS.PRODUCT_ID = PRODUCT.PRODUCT_ID)
		    JOIN MEMBER ON (MEMBER.MEMBER_ID = PRODUCT.MEMBER_ID)
		    WHERE ORDERS.MEMBER_ID = #{memberId}
		    ORDER BY ORDER_ID DESC
		    ) A
		WHERE A.RNUM BETWEEN #{startRow} AND #{endRow}
	</select>
	
	<select id="validConfirm1" resultType="Long"> <!-- 확정 취소버튼 클릭시 유효성검사 첫번째 / 해당 멤버가 해당 물품을 소유하고있는지 확인 -->
		SELECT *
		FROM PRODUCT
		WHERE MEMBER_ID = #{memberId} AND PRODUCT_ID = #{productId}
	</select>
	
	<select id="validConfirm2" resultType="Long"> <!-- 확정 취소버튼 클릭시 유효성검사 두번째 / 해당 주문번호가 해당 물품을 가르키고있는지 확인 -->
		SELECT *
		FROM ORDERS
		WHERE ORDER_ID = #{orderId} AND PRODUCT_ID = #{productId}
	</select>
	
	<update id="confirmUpdate"> <!-- 확정 취소버튼 클릭시 유효성검사 두번째 / 해당 주문번호가 해당 물품을 가르키고있는지 확인 -->
		UPDATE ORDERS
		SET ORDER_CONFIRM_STATUS = #{confirmId}
		WHERE ORDER_ID = #{orderId} AND PRODUCT_ID = #{productId}
	</update>
	
	<select id="sellItemsModal" resultType="july.lease.dto.MyPageSellitemsModalDto"> <!-- 대여중일때 모달창에 띄울 정보 조회 -->
		SELECT PRODUCT.PRODUCT_ID AS productId, STORE_IMAGE_NAME AS productImage, PRODUCT_NAME AS productTitle, TO_CHAR(ORDER_RENT_START_DATE, 'YYYY-MM-DD') || ' ~ ' || TO_CHAR(ORDER_RENT_END_DATE, 'YYYY-MM-DD') AS rentDate, MEMBER_NAME AS rentMemberName
		FROM ORDERS
		JOIN MEMBER ON (ORDERS.MEMBER_ID = MEMBER.MEMBER_ID)
		JOIN PRODUCT ON (PRODUCT.PRODUCT_ID = ORDERS.PRODUCT_ID)
		LEFT JOIN (
		        SELECT PRODUCT_ID, STORE_IMAGE_NAME, ROW_NUMBER() OVER (PARTITION BY PRODUCT_ID ORDER BY PRODUCT_IMAGE_ID) AS ROW_NUM
		        FROM PRODUCT_IMAGE
		    ) P_IMAGE ON (PRODUCT.PRODUCT_ID = P_IMAGE.PRODUCT_ID AND P_IMAGE.ROW_NUM = 1)
		WHERE PRODUCT.PRODUCT_ID = #{productId} AND PRODUCT.MEMBER_ID = #{memberId} AND ORDER_CONFIRM_STATUS = 4 AND PRODUCT_STATUS = 2
	</select>
	
	<update id="updatePw"> <!-- 개인정보수정(비밀번호변경) -->
		UPDATE MEMBER
		SET MEMBER_PASSWORD = #{pw}
		WHERE MEMBER_ID = #{memberId}
	</update>
	
	<update id="productRent"> <!-- 물품 대여중으로 변경 -->
		UPDATE	PRODUCT
		SET PRODUCT_STATUS = 2
		WHERE PRODUCT_ID = #{productId}
	</update>
	
	<update id="productReturn"> <!-- 물품 반납으로 변경 -->
		UPDATE	PRODUCT
		SET PRODUCT_STATUS = 1
		WHERE PRODUCT_ID = #{productId}
	</update>
	
	<select id="rentList" resultType="july.lease.dto.MyPageRentItemsDto"> <!-- 대여중일때 모달창에 띄울 정보 조회 -->
		select a.order_id, b.product_id, b.product_name, to_char(a.order_rent_start_date, 'yyyy-mm-dd') as product_start_date,
		       to_char(a.order_rent_end_date, 'yyyy-mm-dd') as product_end_date,
		         (a.order_rent_end_date - a.order_rent_start_date) + 1 as product_date_count, '대여중' as product_status, p_image.store_image_name as product_image
		from orders a
		left join product b
		    on a.product_id = b.product_id
		left join member c
		    on b.member_id = c.member_id
		left join (
		              select product_id, store_image_name, row_number() over (partition by product_id order by product_image_id) as row_num
		              from product_image
		          ) p_image on (b.product_id = p_image.product_id and p_image.row_num = 1)
		where c.member_id = #{memberId} and a.order_confirm_status = 4 and b.product_status = 2
	</select>
</mapper>
   ```
</div>
</details>
   
   
   
4. 첫 프로젝트

  이 프로젝트에 참여한 사람들은 모두 프로젝트에 대한 경험이 없던 사람들입니다. 프로젝트 시작부터 끝까지 유연하게 넘어가는 부분이 없었습니다. 많이 부족했고 깔끔하지 못한 프로젝트라고 할수있습니다. 누구나그렇듯 처음부터 잘할 수 없고 만족할만한 성과를 내기 어렵습니다. 저는 이 프로젝트를 더 업그레이드 시켜서 더 좋은 프로젝트로 완성시키고싶습니다. 

