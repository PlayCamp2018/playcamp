$('body').on('click', '#newProject_btn_addUser', function() {

    var that = this

    $.ajax ({
         url: '/getAllUsers', // url
         type: "POST", // send method
         success: function(response) { // success callback
            $('#newProject_btn_dropDownMenu_userList').empty()
            $.each(response, function(index, user) {
                //console.log(user)
                //TODO: duplicated ids
                $('#newProject_btn_dropDownMenu_userList').append(
                    '<button type="button" id="newProject_btn_addUser" ' +
                                          'class="dropdown-item" ' +
                                          'data-id="'+user._id+'" ' +
                                          'data-firstname="'+user.firstname+'" ' +
                                          'data-lastname="'+user.lastname+'" ' +
                                          'data-email="'+user.email+'">'+user.firstname +' '+ user.lastname +': '+ user.email+'</button>');
            })
         },
         error: function(response) { // error callback
            console.log('error new User')
         }
    });
});

$('body').on('click', '#newProject_btn_addUser', function(event) {
    //TODO: id not working, id field value: data-id="[object Object]"
    var id        = $(this).data("id")
    var firstname = $(this).data("firstname")
    var lastname  = $(this).data("lastname")
    var email     = $(this).data("email")

    var ownerEmail = $('#newProject_in_owner').data('email')

    if (typeof email !== "undefined" && email !== ownerEmail) {
        var alreadyInList = false

        $('#newProject_userList li').each(function(idx, li){
            if ($(li).data("email") === email) {
                alreadyInList = true
            }
        })

        if (alreadyInList !== true) {
            $('#newProject_userList').append(
                                        '<li class="list-group-item" data-id="'+id +
                                                                  '" data-firstname="'+firstname +
                                                                  '" data-lastname="'+lastname +
                                                                  '" data-email="'+email+'">' +
                                                                  firstname +' '+ lastname +': '+ email +
                                    '<button type="button" id="newProject_btn_remove_user" class="btn btn-danger btn-sm pull-right"><i class="fa fa-trash"></i></button></li>');
        }

    }
});

$('body').on('click', '#newProject_btn_remove_user', function() {
    $(this).parent().remove()
});

$('body').on('click', '#newProject_btn_save', function() {
    var projectName = $('#newProject_in_name').val()
    var projectDescription = $('#newProject_in_description').val()

    if (projectName === "" || projectDescription === "") {
        console.log('newProject_btn_save EMPTY name or description')
        return
    }

    var ownerEmail = $('#newProject_in_owner').data('email')
    var userList = []

    $('#newProject_userList li').each(function(idx, li){
        userList.push($(li).data("email"))
    })

    data

    var data = {
        'projectName' : projectName,
        'projectDescription' : projectDescription,
        'ownerEmail' : ownerEmail,
        'userList' : userList
    };

    console.log(JSON.stringify(data))

    $.ajax ({
        url: '/saveProject', // url
        type: "POST", // send method
        data: JSON.stringify(data), // json send data
        dataType: "html", // return type
        contentType: "application/json; charset=utf-8", // send type
        success: function(response) { // success callback
            console.log('ok');
        },
        error: function(response) { // error callback
            console.log('error')
        }
    });

});