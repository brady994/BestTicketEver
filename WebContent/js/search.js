/***/

$("#search").on('submit', function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    $.ajax({
        url: "SearchController",
        type: "POST",
        dataType: "JSON",
        data: JSON.stringify(frm)
    }).done(function(data) {
        if (data.result === "SUCCESS") {
            operation_alert(data, function() {
                window.location.href = "home?action=search";
            });
        } else {
            operation_alert(data, function() {
                window.location.reload();
            });
        }
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});
