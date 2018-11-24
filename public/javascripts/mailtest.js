
$( window ).on( "load", function() {
    console.log( "window loaded" );

    $( "#btn-error" ).click(function() {
        $.get('/mail/dialog?dialog=ERROR', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });
    $( "#btn-info" ).click(function() {
        $.get('/mail/dialog?dialog=INFO', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

    $( "#btn-success" ).click(function() {
        $.get('/mail/dialog?dialog=SUCCESS', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

    $( "#btn-warn" ).click(function() {
        $.get('/mail/dialog?dialog=WARN', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

    $( "#btn-register" ).click(function() {
        var bla = $('#txt-mailaddress').val();
        var req = '/mail/register?mail=' + bla;
        $.get(req, function(response) {
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });
    $( "#btn-passwortreset" ).click(function() {
        var bla = $('#txt-mailaddress').val();
        var req = '/mail/passwortreset?mail=' + bla;
        $.get(req, function(response) {
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

});
