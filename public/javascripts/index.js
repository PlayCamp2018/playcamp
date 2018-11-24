$('body').on('click','#index_login',function(){
    $.get('/login', function(response) {
        console.log("login");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#index_registration',function(){
    $.get('/registration', function(response) {
        console.log("registration");
        document.getElementById("index_content").innerHTML = response;
    });
});

$('body').on('click','#index_lang',function(){
    $.get('/test/lang', function(response) {
        console.log("lang");
        document.getElementById("index_content").innerHTML = response;
    });
});

//$('body').on('click', '#editProject_btn_ok', function() {
//
//    // var $button = $( this );
//
//    // $button.width( $button.width() ).text('...');
//
//    // set ajax data
//    var data = {
//        'editProject_name' : $('#editProject_name').val(),
//        'editProject_description' : $('#editProject_description').val()
//    };
//
//    $.ajax ({
//         url: '/editProject', // url
//         type: "POST", // send method
//         data: JSON.stringify(data), // json send data
//         dataType: "html", // return type
//         contentType: "application/json; charset=utf-8", // send type
//         success: function(response) { // success callback
//            console.log('ok');
//            document.getElementById("editProject_content").innerHTML = response;
//         },
//         error: function(response) { // error callback
//            console.log('error')
//         }
//    });
//});