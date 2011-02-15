$(document).ready(function() {
    $('#commentform').submit(function() {
        var postid = $('#postid').val();
        var commentbody = $('#commentbody').val();

        //alert("comment: " +commentbody);

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