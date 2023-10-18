window.addEventListener('load', function(){
    let room = document.querySelector('.chatRoom');
    if (room){
	    room.addEventListener('click', function(){
	        
	        window.open("http://localhost:8080/chat", "채팅방", "width=500, height=850, location=no, status=no, scrollbars=yes,toolbars=no, resizable=no")
	    })		
	}
})