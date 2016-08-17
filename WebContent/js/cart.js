<<<<<<< HEAD
// subTotal();
$("#clear").on('click', function(e) {
    $.ajax({
        url: "cart?action=clear",
        type: "POST",
        dataType: "JSON",
    }).done(function(data) {
        console.log(data);
        operation_alert(data, function() {
            window.location.reload();
        });

    });
});

$(".rm-item").on('click', function(e) {
    var idticket = $(this).closest("tr").find("td:eq(0)").attr("id");
    console.log(idticket);
    $.ajax({
        url: "cart?action=remove",
        type: "POST",
        data: "{" + "id:" + idticket + "}",
        dataType: "JSON",
    }).done(function(data) {
        operation_alert(data, function() {
            window.location.reload();
        });


    });
});

$('#cart-table select').change(function() {
    $value = +$(this).closest('select').find(':selected').text();
    $tmp = +$(this).closest("tr").find("td:eq(2)").text();
    $prova = $value * $tmp;
    $(this).closest("tr").find("td:eq(3)").html('<strong>' + $prova + '</strong>');
    subTotal();


});

$("#form-cart").submit(function(e) {

    e.preventDefault();
    var total = $("#total").text();
    var ok = selectValue(total);
    console.log(ok);

    $.ajax({
        url: "cart?action=buy",
        type: "POST",
        dataType: "JSON",
        data:ok,
    }).done(function(data) {
        operation_alert(data, function() {
            window.location.reload();
        });


    });

});

function selectValue(total)
{
  var rowCount = $('#cart-table >tbody >tr').length;
  var data="{quantity:[";
  $("#cart-table tbody tr:not(:last-child)").each(function(i) {
    var value = $(this).find(':selected').text();

    if (i===rowCount-2)
    {
      data+=value+"],";
    }else{
      data+=value+',';
    }
  });
  data+="total"+':'+total+"}";
  return data;
}


function subTotal(total) {
    $total = 0;
    $("#cart-table tr").find("td:eq(3)").each(function(i) {

        if (i < $("#cart-table tbody").children().length - 1) {
            $tmp = +$(this).text();
            $total = $tmp + $total;

        }

    });
    $("#cart-table tbody tr:last-child").find("td:eq(3)").html('Total:<span id="total">' + $total +'</span>'+ '$');

}



// $(".table-cart .btn").on("click", function() {
//
//     if ($("tbody").children().length !== 2) {
//         $(this).closest("tr").remove();
//         subTotal();
//
//     } else {
//         $(".table-cart tbody").empty();
//     }
//
// });
//
// $('tr select').change(function() {
//     console.log("ciao");
//     $value = +$(this).closest('select').find(':selected').text();
//     $tmp = +$(this).closest("tr").find("td:eq(2)").text();
//     $prova = $value * $tmp;
//     console.log($prova);
//     $(this).closest("tr").find("td:eq(3)").html('<strong>' + $prova + '</strong>');
//     subTotal();
//
//
// });
//
//
//
//
//
// function subTotal(total) {
//     $total = 0;
//     $("tr").find("td:eq(3)").each(function(i) {
//
//         if (i < $("tbody").children().length - 1) {
//             $tmp = +$(this).text();
//             $total = $tmp + $total;
//
//         }
//
//     });
//     $("tbody tr:last-child").find("td:eq(3)").html('Total:' + $total);
//
// }
=======
$(document).ready(function() {
    subTotal();

    $(".btn-cart").on("click", function() {
        $(".table-cart tbody").empty();

    });

    $(".table-cart .btn").on("click", function() {

        if ($("tbody").children().length !== 2) {
            $(this).closest("tr").remove();
            subTotal();

        } else {
            $(".table-cart tbody").empty();
        }

    });

    $('tr select').change(function() {
        console.log("ciao");
        $value = +$(this).closest('select').find(':selected').text();
        $tmp = +$(this).closest("tr").find("td:eq(2)").text();
        $prova = $value * $tmp;
        console.log($prova);
        $(this).closest("tr").find("td:eq(3)").html('<strong>' + $prova + '</strong>');
        subTotal();


    });



});

function subTotal(total) {
    $total = 0;
    $("tr").find("td:eq(3)").each(function(i) {

        if (i < $("tbody").children().length - 1) {
            $tmp = +$(this).text();
            $total = $tmp + $total;

        }

    });
    $("tbody tr:last-child").find("td:eq(3)").html('Total:' + $total);

}
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
