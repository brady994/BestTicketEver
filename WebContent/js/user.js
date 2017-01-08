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
        $(".modal-body").append("<div class='alert alert-warning'>" + data.reason + "</div>");
    } else if (data.result === "FAIL") {
        $(".modal-body").append("<p class=text-warning>" + data.reason + "</p>");
        $(".modal-body").append('<div class="text-center"> <a href="home?action=wishlist" role="button" class=" btn btn-success">Create new Wishlist</a></div>');
    } else {
        $.each(data, function(key, value) {
            $("#w .list-group").append('<button  type="button" data-dismiss="modal" id=' + value.id + ' class="list-group-item "><i class="fa fa-star-o"></i>' + value.title + '</button>');
        });
    }
}
var btnWish;
$("#table-ticket").on("click", "button", function() {
    btnWish = $(this).attr("data-target");
    $(".list-group").empty();
    $(".modal-body").empty();

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
    var ticket_id = $(this).closest("tr").find("td:eq(0)").attr("id");
    $.ajax({
        url: "cart?action=add",
        type: "POST",
        data: "{" + "id:" + ticket_id + "}",
        dataType: "JSON",
    }).done(function(data) {
        $("body").scrollTop(0);
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
        operation_alert(data, function() {});

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});

function appendReviews(data) {
    if (data.result === "FAIL") {
        $("#reviewss").append("<p>" + data.reason + "</p>");

    } else {
        $.each(data, function(key, value) {
            $("#reviewss .list-unstyled").append('<li> <p class="title-review">' + value.title +'</p><input id="review-t" class="input-3" name="input-3" value="'+value.feedback+'" class="rating-loading" data-size="xs">' + ' <p class="customer">' + value.user.username + '</p> <p>' + value.text + '</p> </li> <hr>');
        });
    }
}

$('#show-r').on("click", function(event) {
    $.ajax({
        url: "EventController?action=showReviews",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        appendReviews(data);
        $('.input-3').rating({displayOnly: true, step: 0.5});
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});

$("#review-form").on('submit', function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    console.log(frm);
    $.ajax({
        url: "EventController?action=addReview",
        type: "POST",
        dataType: "JSON",
        data: JSON.stringify(frm)
    }).done(function(data) {
        $("body").scrollTop(0);
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
            $(target + " tbody").append('<tr id="'+key+'">' +
                '<div class="row">' +
                '<td class="col col-md-4 col-xs-8">' +
                '<div class="media">' +
                '<div class="media-left">' +
                '<a href="#" class="thumbnail"><img class="media-object img-responsive" src="' + value.event.image + '" alt="" /></a>' +
                '</div>' +
                '<div class="media-body">' +
                '<h4 class="media-heading">' + value.event.name + '</h4>' +
                '<dl>' +
                '<dt>Date</dt>' +
                '<dd>' + value.event.date + '</dd>' +
                '<dt>Location</dt>' +
                '<dd>' + value.event.location + '</dd>' +
                '</dl>' +
                '</div>' +
                '</div>' +
                '</td>' +
                '<td class="col col-md-4 col-xs-12">' +
                '<dl>' +
                '<dt>Price</dt>' +
                '<dd>' + value.price + '</dd>' +
                '<dt>Ticket Category</dt>' +
                '<dd>' + value.category.name + '</dd>' +
                '</dl>' +
                '</td>' +
                '</div>' +
                '</tr>');
        });
    }
}

function appendOrder(data, target) {
    if (data.result === "FAIL") {
        $(target + " tbody").after("<p>" + data.reason + "</p>");
    } else {
        $.each(data, function(key, value) {
            $(target + " tbody").append('<tr>' +
                '<div class="row">' +
                '<td class="col col-md-6 col-xs-12">' +
                '<div class="media">' +
                '<div class="media-left">' +
                '<a href="#" class="thumbnail"><img class="media-object img-responsive" src="' + value.ticket.event.image + '" alt="" /></a>' +
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
                '<dl>'+
                '<dt>Price</dt>' +
                '<dd>' + value.price + '</dd>' +
                '</dl>' +
                '</td>' +
                '</div>' +
                '</tr>');
        });
    }
}

$("#wishlists").on("click", "button", function() {
    var target = $(this).attr("data-target");
    var expanded = $(this).attr("aria-expanded");
    if (typeof expanded == 'undefined') {
        $.ajax({
            url: "user?action=showevent",
            type: "POST",
            data: "{" + "id:" + target.substr(1) + "}",
            dataType: "JSON",
        }).done(function(data) {
            appendWishlist(data, target);
        }).fail(function(data, status, err) {
            alert("error: " + data + " status: " + status + " err: " + err);
        });
    }

});
/*
 * Show order items
 */
$("#orders").on("click", "button", function() {
    var target = $(this).attr("data-target");
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

$(".delete-wish").on('click', function(event) {
  var idwish=$(this).attr('data-target');
  $.ajax({
      url: "user?action=deletewishlist",
      type: "POST",
      dataType: "JSON",
      data: "{id :" + idwish +"}"
  }).done(function(data) {
      operation_alert(data, function() {
          window.location.reload();
      });
  }).fail(function(data, status, err) {
      alert("error: " + data + " status: " + status + " err: " + err);
  });

});

$(".wish-cartt").on('click', function(event) {
  var idticket=$(this).closest('td').attr('id');
  $.ajax({
      url: "cart?action=add",
      type: "POST",
      dataType: "JSON",
      data: "{id :" + idticket +"}"
  }).done(function(data) {
      operation_alert(data, function() {
          window.location.reload();
      });
  }).fail(function(data, status, err) {
      alert("error: " + data + " status: " + status + " err: " + err);
  });

});
