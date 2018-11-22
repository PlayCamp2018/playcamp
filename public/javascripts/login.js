$('body').on('click','#login_btn_register',function(){
    $.get('/registration', function(response) {
        console.log("registration");
        document.getElementById("login_content").innerHTML = response;
    });
});