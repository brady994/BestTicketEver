$(document).ready(function() {
    $(document).on('click', '#edit', function(e) {
        e.preventDefault();
        $("#footer-profile").append('<button type="Submit" class="btn btn-success" id="sub-profile">Submit</button>');
        $(".table-user-information tbody tr").each(function(index) {
            $value = $(this).find("td:eq(1)").text();
            $label = $(this).closest("label").attr("for");
            console.log($label);
            if (index !== 2) {
                $(this).find("td:eq(1)").replaceWith('<td><input type="text" class="form-control" name=' + $label + ' value=' + $value + '></td>');

            }

        });
        $(this).remove();


    });
    $(document).on('click', '#sub-profile', function(e) {
        e.preventDefault();
        console.log("ok");
        $(".table-user-information tbody tr").each(function(index) {
            $value = $(this).find("td:eq(1)").find('input').val();
            if (index !== 2) {
                $(this).find("td:eq(1)").replaceWith('<td>' + $value + '</td>');

            }

        });
        $("#edit-user").append('<a href="" data-original-title="Edit this user" id="edit" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning text-right"><i class="glyphicon glyphicon-edit"></i></a>');
        $(this).remove();


    });



});
