$(document).ready(function() {
    $('#form').submit(function() {
        var postid = $('#postid').val();
        var commentbody = $('#commentbody').val();

	$.ajax({
            type: 		"post",
            url: 		"comment.jsp",
            data: 		"postid=" + postid + "&commentbody=" + commentbody,
            success:
                function(data) {
                    $('.post').append(data);
                    //$('#result').hide();
                    //$("#result").html("<h3>" + data + "</h3>").fadeIn("slow");
		}
	});
	return false;
    });
});