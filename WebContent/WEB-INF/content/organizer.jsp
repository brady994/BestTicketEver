<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
        <p id="login-alert-text"></p>
    </div>
    <div class="panel panel-primary panel-table">
        <div class="panel-heading">
            <div class="row">
                <div class="col col-xs-4 col-md-6">
                    <h1 class="panel-title">Event</h1>
                </div>
                <div class="col col-xs-3 col-md-6 text-right">
                    <a role="button" href="#form-event" class="btn btn-sm btn-primary btn-create collapsed" data-toggle="collapse" data-target="#form-create-event" id="create-event">
                        Create New Event
                        <i class="glyphicon glyphicon-menu-down"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="panel-body table-responsive">
            <form id="form-show-event" method="post">
                <input type="hidden" name="id" value="" id="idevent">
                    <table class="table table-striped table-bordered table-list " id="table-organizer">
                        <c:choose>
                            <c:when test="${empty eventsO}">
                                <p>
                                    <div class="alert alert-warning">
                                        We are sorry, you didn't create any events.
                                    </div>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <thead>
                                    <tr>
                                        <th>
                                            <div class="text-center">
                                                <em class="fa fa-cog"></em>
                                            </div>
                                        </th>
                                        <th>
                                            Name
                                        </th>
                                        <th>
                                            Location
                                        </th>
                                        <th>
                                            Date
                                        </th>
                                        <th>
                                            Category
                                        </th>
                                        <th>
                                            Guests
                                        </th>
                                        <th>
                                            Image
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${eventsO}" var="entry">
                                        <tr>
                                            <div class="row">

                                                <td align="center" class="col col-md-3 col-xs-12">
                                                    <a class="btn btn-default mod-event" role="button" id="" data-toggle="tooltip" data-placement="bottom" title="Modified event">
                                                        <em class="fa fa-pencil"></em>
                                                    </a>
                                                    <a class="btn btn-danger delete-event" role="button" id="" data-toggle="tooltip" data-placement="bottom" title="Delete event">
                                                        <em class="fa fa-trash-o"></em>
                                                    </a>
                                                    <a class="btn btn-danger suspend-event" role="button" id="" data-toggle="tooltip" data-placement="bottom" title="Suspend event">
                                                        <i class="fa fa-hand-paper-o"></i>
                                                    </a>
                                                    <span data-toggle="modal" data-target="#ticketsold">
                                                        <a class="btn btn-info sold" role="button" data-toggle="tooltip" data-placement="bottom" title="Tickets sold">
                                                            <i class="glyphicon glyphicon-eye-open"></i>
                                                        </a>
                                                    </span>

                                                </td>
                                                <td class="col col-md-2 col-xs-8" id="${entry.value.id}" data-target="name">
                                                    ${entry.value.name}
                                                </td>
                                                <td class="col col-md-2 col-xs-8" data-target="location">
                                                    ${entry.value.location}
                                                </td>
                                                <td class="col col-md-2 col-xs-8" data-target="date">
                                                    ${entry.value.date}
                                                </td>
                                                <td class="col col-md-2 col-xs-8" data-target="category-id">
                                                    ${entry.value.category.anchestor.name} - ${entry.value.category.name}
                                                </td>
                                                <td align="center" class="col col-md-1 col-xs-2">
                                                    <span data-toggle="modal" data-target="#modal-guest">
                                                        <a role="button" data-toggle="tooltip" data-placement="bottom" title="Show guests" class="show-guests">
                                                            <i class="fa fa-users"></i>
                                                        </a>
                                                    </span>

                                                </td>
                                                <td class="col col-md-3 col-xs-9" data-target="image">
                                                    <img class="img-responsive" src="${entry.value.image}" alt="${entry.value.name}"/>
                                                </td>
                                            </div>

                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </form>
            </div>

        </div>
        <div class="modal fade" id="modal-guest" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="">Guests</h4>
                    </div>
                    <div class="modal-body" id="remove-guest">
                        <form id="form-mod-guest" method="post">
                            <input type="hidden" name="id" value="" id="idguest">
                                <ul class="media-list" id="li-guest"></ul>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="ticketsold" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="">Sold Ticket</h4>
                        </div>
                        <div class="modal-body">
                            <form id="form-ticket" method="post" role="form">
                                <div class="table-responsive">
                                    <input type="hidden" id="eventid" name="idevent" value="">
                                        <input type="hidden" name="idcategory" id="idcategory" value="">
                                            <table class="table table-striped" id="table-ticket-organizer">
                                                <thead>
                                                    <th>
                                                        Ticket Category
                                                    </th>
                                                    <th>
                                                        Price
                                                    </th>
                                                    <th>
                                                        Tickets sold
                                                    </th>
                                                    <th>
                                                        Remaning ticket
                                                    </th>
                                                    <th>
                                                        <div class="text-center">
                                                            <em class="fa fa-cog"></em>
                                                        </div>
                                                    </th>
                                                </thead>
                                                <tbody></tbody>
                                            </table>

                                        </div>
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="panel-collapse collapse " id="form-create-event">
                        <form class="col-md-6 col-md-offset-3 form-horizontal" method="post" id="form-event">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-user"></i>
                                    </span>
                                    <input type="text" name="name" class="form-control" id="" placeholder="Name"></div>

                                </div>
                                <div class="form-group">
                                    <label for="Location">Location</label>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-map-marker"></i>
                                        </span>
                                        <input type="text" class="form-control" name="location" id="" placeholder="Location"></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="date">Date</label>
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </span>
                                            <input type="date" class="form-control" id="" name="date" placeholder="Date"></div>
                                        </div>

                                        <div class="form-group">
                                            <label for="photo">Image</label>
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-file-image-o"></i>
                                                </span>
                                                <input type="text" class="form-control" name="image" placeholder="Event's image"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="category">Category</label>
                                                <select class="form-control" id="select-category" name="idcategory"></select>

                                            </div>

                                            <div id="form-guest">

                                                <p class="text-center text-info">
                                                    Add Guest
                                                </p>
                                                <div class="form-group">
                                                    <label for="guest">Name</label>
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-user"></i>
                                                        </span>
                                                        <input type="text" name="nameguest" class="form-control" placeholder="Name Guest"></div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="photo">Photo</label>
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-camera"></i>
                                                            </span>
                                                            <input type="text" name="photo" class="form-control" placeholder="Guest's photo"></div>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="button" class="btn btn-info" id="add-guest">
                                                                Add more Guest
                                                                <i class="fa fa-user-plus"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div id="form-ticket">
                                                        <p class="text-info text-center">
                                                            Add Ticket
                                                        </p>
                                                        <div class="form-group">
                                                            <label for="quantity">Tickets's quantity</label>
                                                            <div class="input-group">
                                                                <input type="number" name="quantity" min="0"></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="ticketcategory">Tickets's category</label>
                                                                <select class="form-control select-ticketcategory" name="idcategoryt"></select>

                                                            </div>
                                                            <div class="form-group">
                                                                <label for="price">Price</label>
                                                                <div class="input-group">

                                                                    <input type="number" name="price"></div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <button type="button" class="btn btn-info" id="add-ticket">
                                                                        Add more tickets
                                                                        <i class="fa fa-plus"></i>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <button type="submit" class="btn btn-success">
                                                                    Submit
                                                                    <i class="glyphicon glyphicon-ok"></i>

                                                                </button>

                                                            </div>

                                                        </form>

                                                    </div>
                                                </div>
