$(function() {
    $('[data-toggle="tooltip"]').tooltip();
    $('.input-3').rating({displayOnly: true, step: 0.5});
    $('#input-1').rating({
      step: 1,
      starCaptions: {1: 'Very Poor', 2: 'Poor', 3: 'Ok', 4: 'Good', 5: 'Very Good'},
      starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
  });

});

$('#add-guest').on('click', function(event) {
    $('<div class="form-group"> <label for="guest">Name</label> <div class="input-group"> <span class="input-group-addon"><i class="fa fa-user"></i></span><input type="text" name="nameguest" class="form-control" placeholder="Name Guest"></div></div><div class="form-group"><label for="photo">Photo</label><div class="input-group"><span class="input-group-addon"><i class="fa fa-camera"></i></span><input type="text" name="photo" class="form-control" placeholder="Guests photo"></div></div>').insertBefore($(this).parent());

});

function appendCategory(data, target) {
    $.each(data, function(key, value) {
        $.each(value.children, function(key1, value1) {
            $(target).append('<option value="' + key1 + '">' + value1.name + '</option>');
        });

    });
}

function appendTicketCategory(data) {
    $.each(data, function(key, value) {
        $('.select-ticketcategory').append('<option value="' + key + '">' + value.name + '</option>');

    });
}
$('#add-ticket').on('click', function(event) {
    $.ajax({
        url: "EventController?action=showTicketCategory",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        $('<div class="form-group"><label for="quantity">Ticketss quantity</label><div class="input-group"><input type="number" name="quantity"></div></div>  <div class="form-group"><label for="ticketcategory">Ticketss category</label><select class="form-control select-ticketcategory" name="idcategoryt"></select></div><div class="form-group"><label for="price">Price</label>  <div class="input-group">  <input type="number" name="price"></div>  </div>').insertBefore($('#add-ticket').parent());
        appendTicketCategory(data);
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});

$('#create-event').on('click', function(event) {
    $.ajax({
        url: "EventController?action=showTicketCategory",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        appendTicketCategory(data);
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
    $.ajax({
        url: "EventController?action=showCategory",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        var target = '#select-category';
        appendCategory(data, target);
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});

$('#form-event').on('submit', function(event) {
    event.preventDefault();
    var frm = $(this).serializeFormJSON();
    $.ajax({
        url: "EventController?action=CREATE",
        type: "POST",
        data: JSON.stringify(frm),
        dataType: "JSON",
    }).done(function(data) {
        $("body").scrollTop(0);
        operation_alert(data, function() {
            window.location.reload();
        });
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});


$('.delete-event').on("click", function() {
  var idevent=$(this).closest('tr').find("td:eq(1)").attr("id");
  $.ajax({
      url: "EventController?action=DELETE",
      type: "POST",
      data: "{id:" +idevent+"}",
      dataType: "JSON",
  }).done(function(data) {
      $("body").scrollTop(0);
      operation_alert(data, function() {
          window.location.reload();
      });
  }).fail(function(data, status, err) {
      alert("error: " + data + " status: " + status + " err: " + err);
  });


});

$('.suspend-event').on("click", function(event) {
  var idevent=$(this).closest('tr').find("td:eq(1)").attr("id");
  $.ajax({
      url: "EventController?action=SUSPEND",
      type: "POST",
      data: "{id:" +idevent+"}",
      dataType: "JSON",
  }).done(function(data) {
      $("body").scrollTop(0);
      operation_alert(data, function() {
          window.location.reload();
      });
  }).fail(function(data, status, err) {
      alert("error: " + data + " status: " + status + " err: " + err);
  });

});


$(document).on('click', '.mod-event', function(e) {
    $(this).closest("tr").each(function(index) {
        var idevent = $(this).find("td:eq(1)").attr("id");
        $('#idevent').val(idevent);
        $(this).find("td:eq(0)").replaceWith('<td align="center" class="col col-md-1 col-xs-2"><button type="submit" class="btn btn-success sub-event"> Submit </button></td>');
        $("td", this).each(function(i) {
            if (i !== 0 && i !== 3 && i !== 4 && i !== 5 && i !== 6) {
                $value = $(this).text();
                $label = $(this).data('target');
                $(this).replaceWith('<td class="col col-md-2 col-xs-4"><input name="' + $label + '" type="text" class="form-control" id="" value=' + $value + '></td>');

            } else if (i === 3) {
                $value = $.trim($(this).text());
                $label = $(this).data('target');
                $(this).replaceWith('<td class="col col-md-2 col-xs-4"><input type="date" class="form-control" id="" value="' + $value + '" name=' + $label + '></td>');
            } else if (i === 4) {
                $(this).replaceWith('<td class="col col-md-2 col-xs-4"><select class="form-control" id="select-category-modified" name="idcategory"></select></td>');
            } else if (i === 6) {
                var value = $(this).find('img').attr("src");
                $label = $(this).data('target');
                $(this).replaceWith('<td class="col col-md-3 col-xs-6"><input name="' + $label + '" type="text" class="form-control" id="" value=' + value + '></td>');
            }
        });


    });
    $.ajax({
        url: "EventController?action=showCategory",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        var target = '#select-category-modified';
        appendCategory(data, target);
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});
$('#form-show-event').on('submit', function(event) {
    event.preventDefault();
    var frm = $(this).serializeFormJSON();

    $.ajax({
        url: "EventController?action=UPDATE",
        type: "POST",
        data: JSON.stringify(frm),
        dataType: "JSON",
    }).done(function(data) {
        $("body").scrollTop(0);
        operation_alert(data, function() {
            window.location.reload();
        });
    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });


});

function appendTicket(data) {
    $.each(data, function(key, value) {
      if (value.remainTicket !== 0){
        $('#table-ticket-organizer tbody').append('<tr id="'+value.category.idcategoryt+'"> <td>' + key + '</td> <td>' + value.price + '</td> <td> ' +value.soldTicket+ '</td> <td> ' + value.remainTicket + '</td> <td> <a class="btn btn-default mod-ticket" role="button" id="" data-toggle="tooltip" data-placement="bottom" title="Modified price"><em class="fa fa-pencil"></em></a></td> </tr>');
      }else{
        $('#table-ticket-organizer tbody').append('<tr class="success" id="'+value.category.idcategoryt+'"> <td>' + key + '</td> <td>' + value.price + '</td> <td> '+value.soldTicket+'</td> <td>SOLD OUT! </td> <td> <a class="btn btn-default mod-ticket" role="button" id="" data-toggle="tooltip" data-placement="bottom" title="Modified price"><em class="fa fa-pencil"></em></a></td> </tr>');
      }

    });


}


$('.sold').on('click', function(event) {
    var idevent = $(this).closest('tr').find("td:eq(1)").attr("id");
    $('#eventid').val(idevent);
      $('#table-ticket-organizer tbody').empty();
    $.ajax({
        url: "EventController?action=showTicket",
        type: "POST",
        data: "{" + "id:" + idevent + "}",
        dataType: "JSON",
    }).done(function(data) {
        appendTicket(data);
        jQuery("#ticketsold").modal('show');

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});
$('#ticketsold').on('shown.bs.modal', function () {
  $('.mod-ticket').focus();
  $('#form-ticket').focus();
});

$(document).on('click','.mod-ticket', function(event) {
  var idcategoryt = $(this).closest('tr').attr("id");
  $('#idcategory').val(idcategoryt);
  var price=$.trim($(this).closest('tr').find("td:eq(1)").text());
  $(this).closest('tr').find("td:eq(1)").replaceWith('<td><input type="text" name="price" value="'+price+'"></td>');
  $(this).closest('tr').find("td:last").replaceWith('<td><button type="submit"class="btn btn-success">Submit</button></td>');
});

$('#form-ticket').on('submit',function(event) {
  event.preventDefault();
  var frm = $(this).serializeFormJSON();
  $.ajax({
      url: "EventController?action=updatePrice",
      type: "POST",
      data: JSON.stringify(frm),
      dataType: "JSON",
  }).done(function(data) {
	  $('#ticketsold').modal('hide');
    $("body").scrollTop(0);
      operation_alert(data,function() {
        window.location.reload();
      });
  }).fail(function(data, status, err) {
      alert("error: " + data + " status: " + status + " err: " + err);
  });

});

function appendGuest(data) {
    $('#li-guest').empty();
    $.each(data, function(key, value) {
        $('#li-guest').append('<li class="media" id="' + key + '"><div class="media-left col col-md-2 col-xs-4"><img class="media-object img-responsive" src="' + value.photo + '" alt="' + value.nameguest + '"></div><div class="media-body col col-md-4 col-xs-8">  <h4 class="media-heading">' + value.nameguest + '</h4></div> <div class="text-right"><a data-dismiss="modal" role="button" class="btn btn-danger remove-guest"><em class="fa fa-trash-o"></em></a> <a class="btn btn-default mod-guest" role="button" id=""><em class="fa fa-pencil"></em></a></div></li>');

    });

}

$('.show-guests').on('click', function(event) {
    var idevent = $(this).closest('tr').find("td:eq(1)").attr("id");
    $.ajax({
        url: "EventController?action=showGuest",
        type: "POST",
        data: "{" + "id:" + idevent + "}",
        dataType: "JSON",
    }).done(function(data) {
        appendGuest(data);
      $("#modal-guest").modal('show');

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });

});

$('#remove-guest').on('click', '.remove-guest', function(event) {
    var idguest = $(this).closest('li').attr("id");
    $.ajax({
        url: "EventController?action=deleteguest",
        type: "POST",
        data: "{" + "id:" + idguest + "}",
        dataType: "JSON",
    }).done(function(data) {
        $("body").scrollTop(0);
        operation_alert(data, function() {
            window.location.reload();
        });

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });


});

$('#remove-guest').on('click', '.mod-guest', function(event) {

    var idguest = $(this).closest('li').attr("id");
    $('#idguest').val(idguest);
    var img = $(this).closest('li').find('img').attr("src");
    var name = $.trim($(this).closest('li').find('.media-body').text());
    $(this).closest('li').replaceWith('<div class="table-responsive"> <table class="table"> <tbody> <tr><td><input type="text" name="photo" value="' + img + '"></td><td> <input  type="text" name="nameguest" value="' + name + '"></td><td><div class="text-right"> <button type="submit" class="btn btn-success" >Submit</button></td></tr></tbody></table></div>');
});

$('#form-mod-guest').submit(function(event) {
    event.preventDefault();
    var frm = $(this).serializeFormJSON();
    $.ajax({
        url: "EventController?action=editguest",
        type: "POST",
        data: JSON.stringify(frm),
        dataType: "JSON",
    }).done(function(data) {
      $('#modal-guest').modal('hide');
        $("body").scrollTop(0);
        operation_alert(data, function() {
            window.location.reload();
        });

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});
