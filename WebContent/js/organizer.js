$(document).ready(function() {
    $(document).on("click",'.delete-event', function() {
        $(this).closest("tr").remove();

    });
    $(document).on('click','.mod-event', function(e) {
      e.preventDefault();
        $(this).closest("tr").each(function(index) {
            $(this).find("td:eq(0)").replaceWith('<td><button type="submit" class="btn btn-success sub-event"> Submit </button></td>');
            $("td", this).each(function(i) {
                if (i !== 0) {
                    $value = $(this).text();
                    $(this).replaceWith('<td><input type="text" class="form-control" id="" value=' + $value + '></td>');

                }
            });


        });

    });
    $(document).on('click','.sub-event', function() {
        $(this).closest("tr").each(function(index) {

            $("td", this).each(function(i) {
                if (i !== 0) {
                    $value = $(this).find("input").val();
                    $(this).replaceWith('<td>' + $value + '</td>');

                }
            });


        });

        $(this).closest("tr").find("td:eq(0)").replaceWith('<td align="center"> <a class="btn btn-default mod-event" role="button"><em class="fa fa-pencil"></em></a> <a class="btn btn-danger delete-event" role="button"><em class="fa fa-trash-o"></em></a></td>');


    });



});
