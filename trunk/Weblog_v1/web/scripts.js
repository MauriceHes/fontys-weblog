$(document).ready(function() {
    $('#commentform').submit(function() {
        var postid = $('#postid').val();
        var commentbody = $('#commentbody').val();


	$.ajax({
            type: 		"post",
            url: 		"addcomment",
            data: 		"postid=" + postid + "&commentbody=" + commentbody,
            success:
                function(data) {
                    $('.post').append(data);
		}
	});
	return false;
    });
});