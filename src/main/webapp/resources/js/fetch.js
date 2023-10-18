// JSON포맷 GET방식 전달 함수
function fetchGet(url, callback){
	try{
		fetch(url)
			.then(response => response.json())
			.then(map => callback(map));			
	}catch(e){
		console.log('fetchGet',e);
	}
}

//JSON포맷 POST방식 전달 함수
function fetchPost(url, obj, callback){
	try{
		fetch(url , { method : 'post'
					, headers : {'Content-Type' : 'application/json'}
					, body : JSON.stringify(obj)
				})
			.then(response => response.json())
			.then(map => callback(map));			
	}catch(e){
		console.log('fetchPost', e);
	}
}