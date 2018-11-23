$('body').on('click','#login_btn_login',function(){
    $.get('/homepage', function(response) {
        console.log("login");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#login_btn_register',function(){
    $.get('/registration', function(response) {
        console.log("registration");
        document.getElementById("login_content").innerHTML = response;
    });
});