
$('body').on('click', '#registration_btn_save', function() {

    // var $button = $( this );

    // $button.width( $button.width() ).text('...');

    // set ajax data
    var data = {
        'user_firstname' : $('#first_name').val(),
        'user_lastname' : $('#last_name').val(),
        'user_email' : $('#email').val(),
        'user_password' : $('#password').val(),
        'user_password2' : $('#password2').val()

    };

    $.ajax ({
        url: '/registration_save', // url
        type: "GET", // send method
        data: JSON.stringify(data), // json send data
        dataType: "html", // return type
        contentType: "application/json; charset=utf-8", // send type
        success: function(response) { // success callback
            console.log('ok');
            document.getElementById("registration_content").innerHTML = response;
        },
        error: function(response) { // error callback
            console.log('error')
        }
    });
});
