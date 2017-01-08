$(document).ready(function(e) {
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
        e.preventDefault();
        var concept = $(this).text();
        $('.search-panel span#search_concept').text(concept);
        $('#hideFilter').val(concept);
    });
});
$('.sonn').on('click', function(event) {
    var idson = $(this).attr("id");
    console.log(idson);
    $.ajax({
        url: "SearchController?action=sonsearch",
        type: "POST",
        data: "{name :" + idson + "}",
        dataType: "JSON"
    }).done(function(data) {
        if (data.result === 'SUCCESS') {
            operation_alert(data, function() {
                window.location.href = "home?action=searchCategory";
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
