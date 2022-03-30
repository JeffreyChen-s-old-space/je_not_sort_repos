var webSocket;
var chat;

function SetSocket(){
    if("WebSocket" in window){
        chat = document.getElementById("chat")
        webSocket = new WebSocket("ws://localhost:5050/websocket/websocket");
        webSocket.onopen = function (){

        }
        webSocket.onmessage = function (Message_Event){
            console.log(Message_Event.data);
            chat.insertAdjacentText("beforeend",Message_Event.data);
            chat.insertAdjacentHTML("beforeend","<br>")
        }
        webSocket.onclose = function (){
            console.log("WebSocket Closed");
        }
        webSocket.onerror = function (){
        }
        console.log("支援WebSocket");
    }else {
        alert("不支援WebSocket");
    }
}

function sendMessage(){
    Message = document.Message_Form.messageInput.value
    webSocket.send(Message);
}