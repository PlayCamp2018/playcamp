$('body').on('click','#index_btn_login',function(){
    $.get('/login', function(response) {
        console.log("index login ok");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#index_btn_registration',function(){
    $.get('/registration', function(response) {
        console.log("index registration ok");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#index_btn_home',function(){
    $.ajax({
        url: '/homepage',
        type: "GET",
        success: function(response) {
            console.log("index homepage ok");
            document.getElementById("index_content").innerHTML = response;
        },
        error: function(response) {
            console.log("index homepage error");
            document.getElementById("index_content").innerHTML = response;
        }
    });
});

$('body').on('click','#index_btn_profile',function(){
    $.ajax({
        url: '/profilePage',
        type: "GET",
        success: function(response) {
            console.log("index profile ok");
            document.getElementById("index_content").innerHTML = response;
        },
        error: function(response) {
            console.log("index profile error");
            document.getElementById("index_content").innerHTML = response;
        }
    });
});

$('body').on('click','#index_btn_lang',function(){
    $.get('/test/lang', function(response) {
        console.log("index lang ok");
        document.getElementById("index_content").innerHTML = response;
    });
});