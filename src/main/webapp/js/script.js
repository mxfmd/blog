$(document).ready(function () {

    $('#newCommentForm').submit(function (e) {
        e.preventDefault();

        $.ajax({
            url: '/post/newcomment',
            type: 'POST',
            data: $('#newCommentForm').serialize(),
            success: function (comment) {

                if (!comment.message) {

                    $("<div class='col-md-8 col-md-offset-1' id='comments'><div class='panel panel-default'><div class='panel-body'><p>" + comment.body + "</p></div><div class='panel-footer'><p>Author: <i>" + comment.author + "</i></p><p>Email: <i>"
                        + comment.email + "</i></p></div></div></div>").appendTo('#comments').hide().fadeIn(500);

                    $('#newCommentForm')[0].reset();

                } else {
                    alert(comment.message);
                }

            },

            error: function () {
                alert('Failure occurs while adding comment');
            }
        });

    });
});
