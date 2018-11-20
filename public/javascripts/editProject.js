$('body').on('click','#editProject_btn_reset',function(){
    $.get('/editProject', function(response) {
        console.log("reset");
        document.getElementById("editProject_content").innerHTML = response;
    });
});

