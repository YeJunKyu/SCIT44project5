$(document).ready(function() {
        $.ajax({
    url: "/lms/sidebar", // sidebar로 연결함
    success: function(data) {
        $("#sidebar-placeholder").html(data);
    }
});

});