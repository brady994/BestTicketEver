/**
 * JS that goes with the account servlet
 */


(function($) {
    $.fn.serializeFormJSON = function() {
        var disabled = this.find(':input:disabled').removeAttr('disabled');
        var o = {};
        var a = this.serializeArray();
        disabled.attr('disabled', 'disabled');
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

function operation_alert(result, callback) {
    $("#login-auto-close-alert").focus();
    var str = result.result;
    console.log(str);
    if ((str.search("SUCCESS")) != -1) {
        $("#login-alert-text").html("<strong>" + result.message + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-success fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-success fade in");
            callback();
        });
    } else {
        $("#login-alert-text").html("<strong>" + result.reason + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-danger fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-danger fade in");
            callback();
        });
    }
}

$("#signup-form").submit(function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    $.ajax({
        url: "account?action=signup",
        type: "POST",
        dataType: "JSON",
        data: JSON.stringify(frm)
    }).done(function(data) {
        operation_alert(data, function() {
            window.location.href = "home";
        });

    }).fail(function(data, status, err) {
        alert("error: " + data + " status: " + status + " err: " + err);
    });
});

$("#login-form").submit(function(e) {
    e.preventDefault();
    var frm = $(this).serializeFormJSON();
    console.log(frm);
    $.ajax({
        url: "account?action=signin",
        type: "POST",
        dataType: "JSON",
        data: JSON.stringify(frm)
    }).done(function(data) {
        var str = data.result;
        if (str.search("ORGANIZER") != -1) {
            operation_alert(data, function() {
                window.location.href = "home?action=organizer";
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
	$(document).on('ready', function(){
	    $('.input-3').rating({displayOnly: true, step: 0.5});
			$('#input-1').rating({
        step: 1,
        starCaptions: {1: 'Very Poor', 2: 'Poor', 3: 'Ok', 4: 'Good', 5: 'Very Good'},
        starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
    });
	});
