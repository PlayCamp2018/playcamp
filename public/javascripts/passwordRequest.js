$('body').on('click','#passwordRequest_btn_ok',function(){
    $.get('/passwordRequest', function(response) {
        console.log("registration");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#passwordRequest_btn_cancel',function(){
    $.get('/login', function(response) {
        console.log("login");
        document.getElementById("index_content").innerHTML = response;
    });
});