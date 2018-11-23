$('body').on('click','#homepage_btn_ok',function(){
    $.get('/homepage', function(response) {
        console.log("homepage_ok");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#homepage_btn_cancel',function(){
    $.get('/login', function(response) {
        console.log("homepage_X");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#homepage_btn_editProject',function(){
    $.get('/editProject', function(response) {
        console.log("homepage_editProject");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#homepage_btn_newProject',function(){
    $.get('/newProject', function(response) {
        console.log("homepage_editProject");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#homepage_btn_project',function(){
    $.get('/project', function(response) {
        console.log("homepage_project");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#homepage_btn_user',function(){
    $.get('/profilePage', function(response) {
        console.log("homepage_editProject");
        document.getElementById("index_content").innerHTML = response;
    });
});