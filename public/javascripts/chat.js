$(function() {
    var webSocketURL = "ws://localhost:9000/chat/ws" //("ws://localhost:9000/chat/ws") //("@routes.ChatController.ws().webSocketURL()")
    var ws = new WebSocket(webSocketURL)
    ws.onopen = function() {
        console.log("OPEN")
    }

    ws.onmessage = function(msg) {
        $("#chatList").prepend("<li class='list-group-item'>" + msg.data + "</li>")
    }

    ws.onerror = function() {
        ws = new WebSocket(webSocketURL)
    }

    $("#chatText").on("keydown", function search(e) {
        if(e.keyCode == 13) {
            if($(this).val() != "") {
                ws.send($(this).val())
                $(this).val("")
            }
        }
    });
})