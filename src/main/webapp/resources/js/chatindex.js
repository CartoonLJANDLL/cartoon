layui.use(['element','form','layer'], function(){
		var $ = layui.jquery,
		element = layui.element,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer;
		
document.querySelector('.chat[data-chat=person1]').classList.add('active-chat');
document.querySelector('.person[data-chat=person1]').classList.add('active');

var websocket = null;  
if ('WebSocket' in window) {  
    //Websocket的连接  
    websocket = new WebSocket("ws://localhost:8080/guomanwang/websocket/socketServer");//WebSocket对应的地址  
}  
else if ('MozWebSocket' in window) {  
    //Websocket的连接  
    websocket = new MozWebSocket("ws://localhost:8080/guomanwang/websocket/socketServer");//SockJS对应的地址  
}  
else {  
    //SockJS的连接  
    websocket = new SockJS("http://localhost:8080/guomanwang/sockjs/socketServer");    //SockJS对应的地址  
}  
websocket.onopen = onOpen;  
websocket.onmessage = onMessage;  
websocket.onerror = onError;  
websocket.onclose = onClose;  

function onOpen(openEvt) {  
    //alert(openEvt.Data);   
}  
function onMessage(evt) {  
	$(".chat").first().append('<div class="bubble you">'+evt.data+'</div>');
}  
function onError() {  
}  
function onClose() {  
}   

window.close = function () {  
    websocket.onclose();  
}
var friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.left .person'),
  name: '' },

chat = {
  container: document.querySelector('.container .right'),
  current: null,
  person: null,
  name: document.querySelector('.container .right .top .name') };


friends.all.forEach(function (f) {
  f.addEventListener('mousedown', function () {
    f.classList.contains('active') || setAciveChat(f);
  });
});

function setAciveChat(f) {
  friends.list.querySelector('.active').classList.remove('active');
  f.classList.add('active');
  chat.current = chat.container.querySelector('.active-chat');
  chat.person = f.getAttribute('data-chat');
  chat.current.classList.remove('active-chat');
  chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
  friends.name = f.querySelector('.name').innerText;
  chat.name.innerHTML = friends.name;
}
$(document).ready(function(){
$(".send").click(function(){
	var userid=10,content=$("#messages").val();
	if (websocket.readyState == websocket.OPEN) {  
        websocket.send(userid+"@"+content);//调用后台handleTextMessage方法  
        $(".chat").first().append('<div class="bubble me">'+content+'</div>'); 
    } else {  
    
    	alert("连接失败!"+websocket.readyState);  
    } 
})
})
})