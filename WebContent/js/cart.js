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
