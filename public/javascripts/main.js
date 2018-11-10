$( document ).ready(function() {
    console.log( "document loaded" );
});
$( window ).on( "load", function() {
    console.log( "window loaded" );
/*    $.get('/login', function(response) {
        //console.log(response);
        document.getElementById("login").innerHTML = response;
    });*/
    $( "#btn_login" ).click(function() {
        $.get('/login', function(response) {
            //console.log(response);
            document.getElementById("content").innerHTML = response;
        });
    });
    $( "#btn_lang" ).click(function() {
        $.get('/lang', function(response) {
            //console.log(response);
            document.getElementById("content").innerHTML = response;
        });
    });
    $( "#btn_users" ).click(function() {
        $.get('/users', function(response) {
            //console.log(response);
            var jsonPretty = JSON.stringify(response, null, '\t');
            document.getElementById("content").innerHTML = jsonPretty;
        });
    });
    $( "#btn_get_user" ).click(function() {
        $.get('/getUser', function(response) {
            //console.log(response);
            document.getElementById("content").innerHTML = response;
        });
    });
});
