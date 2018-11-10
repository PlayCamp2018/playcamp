$( document ).ready(function() {
    console.log( "document loaded" );
});

$( window ).on( "load", function() {
    console.log( "window loaded" );

/*    $.get('/login', function(response) {
        //console.log(response);
        document.getElementById("login").innerHTML = response;
    });*/

    $( "#target" ).click(function() {
        $.get('/login', function(response) {
            //console.log(response);
            document.getElementById("login").innerHTML = response;
        });
    });
    $( "#other" ).click(function() {
        $.get('/lang', function(response) {
            //console.log(response);
            document.getElementById("login").innerHTML = response;
        });
    });

});



