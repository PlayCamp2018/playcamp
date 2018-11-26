//$('body').on('click','#login_btn_login',function(){
//    $.get('/homepage', function(response) {
//        console.log("login");
//        document.getElementById("index_content").innerHTML = response;
//    });
//});

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

$('body').on('click', '#login_btn_login', function() {
    var data = {
        'login_email' : $('#login_in_email').val(),
        'login_password' : $('#login_in_password').val(),
    };

    $.ajax ({
        url: '/login', // url
        type: "POST", // send method
        data: JSON.stringify(data), // json send data
        dataType: "html", // return type
        contentType: "application/json; charset=utf-8", // send type
        success: function(response) { // success callback
            console.log('ok');
            document.getElementById("index_content").innerHTML = response;
        },
        error: function(response) { // error callback
            console.log('error')
            document.getElementById("index_content").innerHTML = response;
        }
    });
});