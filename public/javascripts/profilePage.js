$('body').on('click','#profilePage_btn_save',function(){
    var data = {
        'firstname' : $('#profilePage_in_firstname').val(),
        'lastname' : $('#profilePage_in_lastname').val(),
        'email' : $('#profilePage_in_email').val(),
        'password' : $('#profilePage_in_password').val(),
        'password2' : $('#profilePage_in_password2').val()
    };

    if (data['password'] !== data['password2']) {
        console.log("profilePage pw are not the same")
    }
    else if(data['password'] === "" || data['password2'] === "") {
        console.log("profilePage pw are empty")
    } else {
        $.ajax ({
            url: '/profilePage', // url
            type: "POST", // send method
            data: JSON.stringify(data), // json send data
            dataType: "html", // return type
            contentType: "application/json; charset=utf-8", // send type
            success: function(response) { // success callback
                console.log('profilePage save ok');
                document.getElementById("index_content").innerHTML = response;
            },
            error: function(response) { // error callback
                console.log('profilePage save error')
            }
        });
    }
});

$('body').on('click','#profilePage_btn_reset',function(){
    $.ajax({
        url: '/profilePage',
        type: "GET",
        success: function(response) {
            console.log("profilePage reset ok");
            document.getElementById("index_content").innerHTML = response;
        },
        error: function(response) {
            console.log("profilePage reset error");
            document.getElementById("index_content").innerHTML = response;
        }
    });
});