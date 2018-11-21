
$( window ).on( "load", function() {
    console.log( "window loaded" );

    $( "#btn-error" ).click(function() {
        $.get('/testdialog?dialog=ERROR', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });
    $( "#btn-info" ).click(function() {
        $.get('/testdialog?dialog=INFO', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

    $( "#btn-success" ).click(function() {
        $.get('/testdialog?dialog=SUCCESS', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

    $( "#btn-warn" ).click(function() {
        $.get('/testdialog?dialog=WARN', function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

    $( "#btn-register" ).click(function() {
        var bla = $('#txt-register').val();
        var req = '/testmail/register?mail=' + bla;
        $.get(req, function(response) {
            //console.log(response);
            document.getElementById("modalDialogContent").innerHTML = response;
            $('#dialogModal').modal('show')
        });
    });

});
