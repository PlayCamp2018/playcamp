$('body').on('click','#login_btn_login',function(){
    $.get('/homepage', function(response) {
        console.log("login");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#login_btn_register',function(){
    $.get('/registrationIndex', function(response) {
        console.log("registration");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#login_btn_password_request',function(){
    $.get('/passwordRequest', function(response) {
        console.log("password request");
        document.getElementById("index_content").innerHTML = response;
    });
});