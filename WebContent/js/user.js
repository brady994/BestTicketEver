/**
 *
 */
$(document).on('click', '#edit', function(e) {
    e.preventDefault();
    $("#footer-profile").append('<button type="submit" class="btn btn-success" id="sub-profile">Submit</button>');
    $(".table-user-information tbody tr").each(function(index) {
        $value = $(this).find("td:eq(1)").text();
        $label = $(this).find("label").attr("for");
        console.log($label);
        if (index !== 2) {
            $(this).find("td:eq(1)").replaceWith('<td><input type="text" class="form-control" name=' + $label + ' value=' + $value + '></td>');

        }

    });
    $(this).remove();


});

$("#update-user-form").on("click", "#sub-profile", function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    console.log(frm);
    $.ajax({
        url: "user?action=update",
        type: "POST",
        dataType: "JSON",
        data: JSON.stringify(frm)
    }).done(function(data) {


        $(".table-user-information tbody tr").each(function(index) {
            $value = $(this).find("td:eq(1)").find('input').val();
            if (index !== 2) {
                $(this).find("td:eq(1)").replaceWith('<td>' + $value + '</td>');

            }

        });
        $("#edit-user").append('<a href="" data-original-title="Edit this user" id="edit" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning text-right"><i class="glyphicon glyphicon-edit"></i></a>');
        $(this).remove();
        operation_alert(data);

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});

function appendWishlist(data, target) {
    if (data.result === "FAIL") {
        $(target + " tbody").after("<p>" + data.reason + "</p>");
    } else {
        $.each(data, function(key, value) {
            $(target + " tbody").after('<tr>' +
                '<div class="row">' +
                '<td class="col-md-10">' +
                '<div class="media">' +
                '<div class="media-left">' +
                '<a href="#" class="thumbnail"><img class="media-object" src="' + value.image + '" alt="" /></a>' +
                '</div>' +
                '<div class="media-body">' +
                '<h4 class="media-heading">' + value.name + '</h4>' +
                '<dl>' +
                '<dt>Date</dt>' +
                '<dd>' + value.date + '</dd>' +
                '<dt>Location</dt>' +
                '<dd>' + value.location + '</dd>' +
                '</dl>' +
                '</div>' +
                '</div>' +
                '</td>' +
                '</div>' +
                '</tr>');
        });
    }
}

function appendOrder(data, target) {
    console.log(data);
    if (data.result === "FAIL") {
        $(target + " tbody").after("<p>" + data.reason + "</p>");
    } else {
        $.each(data, function(key, value) {
            console.log(value);
            $(target + " tbody").append('<tr>' +
                '<div class="row">' +
                '<td class="col col-md-4 col-xs-12">' +
                '<div class="media">' +
                '<div class="media-left">' +
                '<a href="#" class="thumbnail"><img class="media-object" src="' + value.ticket.event.image + '" alt="" /></a>' +
                '</div>' +
                '<div class="media-body">' +
                '<h4 class="media-heading">' + value.ticket.event.name + '</h4>' +
                '<dl>' +
                '<dt>Date</dt>' +
                '<dd>' + value.ticket.event.date + '</dd>' +
                '<dt>Location</dt>' +
                '<dd>' + value.ticket.event.location + '</dd>' +
                '</dl>' +
                '</div>' +
                '</div>' +
                '</td>' +
                '<td class=" col col-md-4 col-xs-12">' +
                '<dl>' +
                '<dt>Ticket</dt>' +
                '<dd>' + value.ticket.id + '</dd>' +
                '<dt>Category</dt>' +
                '<dd>' + value.ticket.category.name + '</dd>' +
                '</dl>' +
                '</td>' +
                '<td class="col col-md-4 col-xs-12">' +
                '<dl>' +
                '<dt>Seller</dt>' +
                '<dd>' + value.seller.username + '</dd>' +
                '<dt>Price</dt>' +
                '<dd>' + value.price + '</dd>' +
                '</dl>' +
                '</td>' +
                '</div>' +
                '</tr>');
        });
    }
}
/*
 * offset and limit for wishlist
 */
var offset = 0;
var limit = 5;

/*
 *  Show wishlists events
 */
$("#wishlists").on("click", "button", function() {
    var target = $(this).attr("data-target");
    var expanded = $(this).attr("aria-expanded");
    if (typeof expanded == 'undefined') {
        $.ajax({
            url: "user?action=showevent",
            type: "POST",
            data: "{" + "id:" + target.substr(1, 1) + "}",
            dataType: "JSON",
        }).done(function(data) {
            appendWishlist(data, target);
        }).fail(function(data, status, err) {
            alert("error: " + data + " status: " + status + " err: " + err);
        });
    }
    /*&offset="+offset+"&limit="+limit*/
});
/*
 *  Show order items
 */
$("#orders").on("click", "button", function() {
    var target = $(this).attr("data-target");
    var expanded = $(this).attr("aria-expanded");
    if (typeof expanded == 'undefined') {
        $.ajax({
            url: "user?action=showitems",
            type: "POST",
            data: "{" + "id:" + target.substr(1, 1) + "}",
            dataType: "JSON",
        }).done(function(data) {
            appendOrder(data, target);
        }).fail(function(data, status, err) {
            alert("error: " + data + " status: " + status + " err: " + err);
        });
    }
    /*&offset="+offset+"&limit="+limit*/
});
$("#addwishlist").on('click', function() {
    $("#addwishlist-body").hide().removeClass("hidden").slideDown("slow", function() {});
    window.location.hash = "#addwishlist-form";
    $("#addwishlist-form #title").focus();
});
$("#addwishlist-form").submit(function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    $.ajax({
        url: "user?action=addwishlist",
        type: "POST",
        dataType: "JSON",
        data: JSON.stringify(frm)
    }).done(function(data) {
        operation_alert(data, function() {
            window.location.reload();
        });
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});
