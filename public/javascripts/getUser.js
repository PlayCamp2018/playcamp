$( window ).on( "load", function() {
    $( "#btn_get_user_id" ).click(function() {
        var id = $( "#user_id" ).val()
        $.get('/user/get/0', function(response) {
            console.log(response);
            var jsonPretty = JSON.stringify(response, null, '\t');
            document.getElementById("getUser_Content").innerHTML = jsonPretty;
        });
    });
});