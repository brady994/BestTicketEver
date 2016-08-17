<<<<<<< HEAD
/**
 *
 */
$(document).on('click', '#edit', function(e) {
    e.preventDefault();
    $("#footer-profile").append('<button type="submit" class="btn btn-success" id="sub-profile">Submit</button>');
    $(".table-user-information tbody tr").each(function(index) {
        $value = $(this).find("td:eq(1)").text();
        $label = $(this).find("label").attr("for");
        $(this).find("td:eq(1)").replaceWith('<td><input type="text" class="form-control" id=' + $label + ' name=' + $label + ' value=' + $value + '></td>');

    });
    $(this).remove();

});

function showwishlist(data) {
    if (data.result === "NOUSER") {
        $(".modal-body").append("<p class=text-danger>" + data.reason + "</p>");
    }else if (data.result === "FAIL") {
      $(".modal-body").append("<p class=text-warning>" + data.reason + "</p>");
      $(".modal-body").append('<div class="text-center"> <a href="home?action=wishlist" role="button" class=" btn btn-success">Create new Wishlist</a></div>');
    } else {
        $.each(data, function(key, value) {
            $("#w .list-group").append('<button  type="button" data-dismiss="modal" id=' + value.id + ' class="list-group-item ">' + value.title + '</li>');
        });
    }
}
var btnWish;
$("#table-ticket").on("click", "button", function() {
    btnWish = $(this).attr("data-target");
    $(".list-group").empty();

});

var id_ticket;

$(".wish").on("click", function() {
    id_ticket = $(this).closest("tr").find("td:eq(0)").attr("id");
    $.ajax({
        url: "user?action=showwishlist",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        showwishlist(data);
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});

$(".btn-cart").on("click", function() {
  var ticket_id=$(this).closest("tr").find("td:eq(0)").attr("id");
  $.ajax({
      url: "cart?action=add",
      type: "POST",
      data: "{" + "id:" + ticket_id + "}",
      dataType: "JSON",
  }).done(function(data) {
    console.log(data);
    operation_alert(data, function() {
        window.location.reload();
    });

  }).fail(function(data, status, err) {
      alert("error: " + data + " status: " + status + " err: " + err);
  });
});

$("#modal-wish").on("click", "button", function() {
    var id_wish = $(this).attr("id");
    $.ajax({
        url: "user?action=addticket",
        type: "POST",
        data: "{" + "idticket:" + id_ticket + ',' + "idwish:" + id_wish + "}",
        dataType: "JSON",
    }).done(function(data) {
        operation_alert(data, function() {
        });

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});

function appendReviews(data) {
    if (data.result === "FAIL") {
        $("#reviewss").append("<p>" + data.reason + "</p>");

    } else {
        $.each(data, function(key, value) {
            $("#reviewss .list-unstyled").append('<li> <p class="title-review">' + value.title + '</p> <p class="customer">' + value.user.username + '</p> <p>' + value.text + '</p> </li> <hr>');
        });
    }
}

$('#show-r').on("click", function(event) {
    $.ajax({
        url: "EventController?action=showReviews",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        console.log(data + "gatta");
        appendReviews(data);
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});

$("#review-form").on('submit', function(e) {
  console.log("ok");
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    console.log(frm);
    $.ajax({
        url: "EventController?action=addReview",
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

$("#update-user-form").on('submit', function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    $.ajax({
        url: "user?action=update",
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
                '<a href="#" class="thumbnail"><img class="media-object" src="' + value.event.image + '" alt="" /></a>' +
                '</div>' +
                '<div class="media-body">' +
                '<h4 class="media-heading">' + value.event.name + '</h4>' +
                '<dl>' +
                '<dt>Date</dt>' +
                '<dd>' + value.event.date + '</dd>' +
                '<dt>Location</dt>' +
                '<dd>' + value.event.location + '</dd>' +
                '<dt>Price</dt>' +
                '<dd>' + value.price + '</dd>' +
                '<dt>Ticket Category</dt>' +
                '<dd>' + value.category.name + '</dd>' +
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
 * Show wishlists events
 */
$("#wishlists").on("click", "button", function() {
    var target = $(this).attr("data-target");
    console.log(target.substr(1));
    var expanded = $(this).attr("aria-expanded");
    if (typeof expanded == 'undefined') {
        $.ajax({
            url: "user?action=showevent",
            type: "POST",
            data: "{" + "id:" + target.substr(1) + "}",
            dataType: "JSON",
        }).done(function(data) {
            console.log(data + "done");
            appendWishlist(data, target);
        }).fail(function(data, status, err) {
            alert("error: " + data + " status: " + status + " err: " + err);
        });
    }
    /* &offset="+offset+"&limit="+limit */
});
/*
 * Show order items
 */
$("#orders").on("click", "button", function() {
    var target = $(this).attr("data-target");
    console.log(target.substr(1));
    var expanded = $(this).attr("aria-expanded");
    if (typeof expanded == 'undefined') {
        $.ajax({
            url: "user?action=showitems",
            type: "POST",
            data: "{" + "id:" + target.substr(1) + "}",
            dataType: "JSON",
        }).done(function(data) {
            appendOrder(data, target);
        }).fail(function(data, status, err) {
            alert("error: " + data + " status: " + status + " err: " + err);
        });
    }
    /* &offset="+offset+"&limit="+limit */
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
      console.log(data);
        // alert("error: " + data + " status: " + status + " err: " + err);
    });
});
=======
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
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
