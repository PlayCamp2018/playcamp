$( document ).ready(function() {
    console.log( "document loaded" );
});

$( window ).on( "load", function() {
    console.log( "window loaded" );


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

    $( "#btn-routen" ).click(function() {
        $.ajax(jsRoutes.controllers.DBTestController.listUser())
            .done(function( data ) {
                if ( console && console.log ) {
                    console.log( "Sample of data:", data.slice( 0, 100 ) );
                    document.getElementById("login").innerHTML = data;
                    console.log("done");
                }
            })
            .fail(
                console.log("fail")
            );
    });

});



