function kindChange(e){
	var sleep = ["텐트","매트","쉘터","침낭","기타"];
	var utensil = ["그릴","화로","토치","코펠","기타"];
	var convenienceGoods = ["테이블","의자","파워뱅크","행어","워터저그","기타"];
	var etc = ["기타"];
	var target = document.querySelector(".category_second");
	
	if(e.value == "1") var d = sleep;
	else if(e.value == "2") var d = utensil;
	else if(e.value == "3") var d = convenienceGoods;
	else if(e.value == "4") var d = etc;
	
	target.options.length = 0;
	
	for(let x = 0; x < d.length; x++){
		var opt = document.createElement("option");
		if (e.value == "4") { // e.value가 4인 경우
			opt.value = "400";
		} else if (x == d.length - 1) { // d의 배열이 마지막 인덱스인 경우
			opt.value = (parseInt(e.value) * 100 + 99).toString();
		} else {
			opt.value = (parseInt(e.value) * 100 + x + 1).toString();
		}

		//console.log(opt.value);
		opt.innerHTML = d[x];
		target.appendChild(opt);
	}
}

function kindChange2(e){
	var seoul = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중량구"];
	var incheon = ["중구","동구","미추홀구","연수구","남동구","부평구","계양구","서구","강화군","옹진군"];
	var gyeonggi = ["수원시","성남시","용인시","안양시","안산시","과천시","광명시","광주시","군포시","부천시","시흥시","김포시","안성시","오산시","의왕시","이천시","평택시","하남시","화성시","여주시","양평군","고양시","구리시","남양주시","동두천시","양주시","의정부시","파주시","포천시"];
	var target = document.querySelector(".region_second");
	
	if(e.value == "서울특별시") var d = seoul;
	else if(e.value == "인천광역시") var d = incheon;
	else if(e.value == "경기도") var d = gyeonggi;
	
	target.options.length = 0;
	
	for(x in d){
		var opt = document.createElement("option");
		opt.value = d[x];
		opt.innerHTML = d[x];

		//console.log(opt.value);
		opt.innerHTML = d[x];
		target.appendChild(opt);
	}
}

function previewImage(f){
    var file = f.files;

    for (let i = 0;i<file.length;i++){
        //console.log(i);
        var reader = new FileReader();
        reader.onload = function(rst){
           // console.log('count')
            document.querySelector('.view_image').innerHTML += '<img src ="' + rst.target.result + '" width = "400px", height = "300px">';
        }
    }

    reader.readAsDataURL(...file);

}

window.addEventListener('load',function(){
    
    let preview = document.querySelector('.view_image_click');

    preview.addEventListener('click', function(){
        document.querySelector('.real-upload').click();
    })

    let input = document.querySelector('.real-upload');
    let previousFiles = [];

    input.addEventListener('change', (event) => {
        let fileInput = document.querySelector('.preview');
        let removeChild = document.querySelectorAll('.view_image');
        let leng = fileInput.childElementCount;
        const selectedFiles = event.target.files;
        //console.log(selectedFiles.length);

        if (event.target.files.length > 0) {
            // 파일이 선택되었을 때 이전 파일 정보를 복사하여 저장
            previousFiles = copyFiles(event.target.files);
        }
        if (selectedFiles.length == 0){
            //console.log("??")
            // 이전 파일 정보를 파일 입력 요소에 설정하여 파일 등록 취소 시 유지
            if (previousFiles.length > 0) {
                const newFileList = new DataTransfer();
                previousFiles.forEach((file) => {
                  newFileList.items.add(file);
                });
                input.files = newFileList.files;
              }
            return;
        }
        // 사진등록할때마다 모든 사진들을 제거
        if (leng > 1){
            for (let k=0;k<removeChild.length;k++){
                fileInput.removeChild(removeChild[k]);
            }
        }

        // 선택된 파일들을 순회하며 이미지를 표시합니다.
        for (const file of selectedFiles) {
            const reader = new FileReader();
            let view = document.querySelector('.view_image');

            // 파일을 읽기 위해 FileReader를 사용합니다.
            reader.onload = function (e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                img.alt = file.name;
                // 이미지의 크기 확인
                img.onload = function () {
                    if (img.width > 320) img.width = 320;
                    if (img.height > 320) img.height = 320;
                  };
                const newParagraph = document.createElement('div');
                newParagraph.classList.add('view_image');
                newParagraph.insertBefore(img, newParagraph.firstChild);

                fileInput.appendChild(newParagraph);
                // fileInput.appendChild(newParagraph);
            };
            // 이미지를 Base64 데이터 URL로 읽어옵니다.
            reader.readAsDataURL(file);
        }
    });

    
})

// 파일 선택 취소 시 파일들을 유지하기 위해 타입 변환
function copyFiles(files) {
    const copiedFiles = [];
    for (const file of files) {
      const blob = new Blob([file], { type: file.type });
      const copiedFile = new File([blob], file.name, { type: file.type });
      copiedFiles.push(copiedFile);
    }
    return copiedFiles;
  }