<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container" data-spy="scroll">

    <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
        <p id="login-alert-text"></p>
    </div>
    <div class="row">
        <div class="media">
            <div class="media-left col-md-5 col-xs-5">
                <img class="media-object img-responsive" src="${event.image}" alt="${event.name}"/>
                <figcaption>
                    <a role="buttonclick" class="collapsed col-xs-4 col-md-4" href="#reviews" data-toggle="collapse" data-target="#reviewss" id="show-r">See reviews</a>
                    <input class="input-3" name="input-3" value="${event.avgFeedback}" class="rating-loading" data-size="sm"></figcaption>
                </div>
                <div class="media-body media-description">
                    <div class="jumbotron" id="jub">
                        <div class="row">

                            <h1 class="media-heading">${event.name }</h1>
                            <div class="col col-md-6 col-xs-7">

                                <dl class="dl-horizontal" id="dl-event">
                                    <dt>
                                        Date:</dt>
                                    <dd>
                                        ${event.date}</dd>
                                        <dt>Status:</dt>
                                        <c:choose>
                                          <c:when test="${not event.suspended}">
                                            <dd><p class="text-success">
                                              AVAILABLE
                                            </p></dd>
                                          </c:when>
                                          <c:otherwise>
                                            <dd><p class="text-danger">
                                              SUSPENDED
                                            </p></dd>
                                          </c:otherwise>
                                        </c:choose>
                                    <ul class="media-list">

                                        <dt>Guest:</dt>

                                        <c:forEach items="${event.guests}" var="entry">

                                            <li class="media">
                                                <div class="media-left col-md-6 col-xs-5">

                                                    <img class="media-object img-responsive img-cart" src="${entry.value.image}" alt="${value.value.name}"/>

                                                </div>
                                                <div class="media-body">
                                                    <h4 class="media-heading">
                                                        ${entry.value.name}
                                                    </h4>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </dl>
                            </div>
                            <div class="col col-md-6 col-xs-12">
                                <dl>
                                    <dt>Location:
                                        <span>
                                            <a role="button" id="seemap">
                                                See on map</a>
                                        </span>
                                    </dt>
                                    <dd id="location">${event.location}</dd>

                                </dl>
                                <div id="map" class="col col-xs-12">
                                  <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <h1>
            Tickets</h1>
        <div id="table-ticket">
            <table class="table table-striped table-responsive" id="ticket-table">
                <thead>
                    <tr>
                        <th>
                            Category
                        </th>
                        <th>
                            Remaning Tickets
                        </th>
                        <th>
                            Price
                        </th>
                        <th></th>
                        <th>
                            Wishlist
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${event.tickets_category}" var="entry" varStatus="count">
                        <tr>
                            <td id="${entry.value[0].id}">
                                ${entry.key}
                            </td>
                            <td>
                                <em>
                                    ${entry.value[0].remainTicket}</em>
                            </td>
                            <td>
                                ${entry.value[0].price}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${entry.value[0].remainTicket == 0}">
                                        <button type="button" class="btn btn-default btn-cart disabled">
                                            <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true" id="#c${count.index}"></span>
                                            Add to cart
                                        </button>
                                    </c:when>
                                    <c:when test="${event.suspended eq true}">
                                        <button type="button" class="btn btn-default btn-cart disabled">
                                            <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true" id="#c${count.index}"></span>
                                            Add to cart
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-default btn-cart">
                                            <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true" id="#c${count.index}"></span>
                                            Add to cart
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default wish " data-toggle="modal" data-target="#w">
                                    <span class="glyphicon glyphicon-star-empty pull-right hidden-xs" aria-hidden="true"></span>
                                    Add to wishlist
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </div>
        <div class="modal fade" id="w" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id=""></h4>
                    </div>
                    <div class="modal-body" id="modal-wish">
                        <ul class="list-group"></ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <h2>Reviews</h2>
        <div class="panel panel-default" id="reviews">
            <div class="panel-heading">
                <div class="row">
                    <div class="col col-xs-6">
                        <h1 class="panel-title">Reviews Customer
                        </h1>
                    </div>

                    <div class="col col-xs-6 text-right">
                        <a class="collapsed" href="#form-review" data-toggle="collapse" data-target="#form-review">
                            Write review<i class="glyphicon glyphicon-pencil pull-right"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="panel-body panel-collapse collapse" id="reviewss">
                <ul class="list-unstyled"></ul>
            </div>
        </div>
        <div class="collapse panel-collapse" id="form-review">
            <form id="review-form" role="form" method="post">
                <div class="form-group">
                    <label for="Title">Title</label>
                    <input type="text" class="form-control gatta" name="title" id="title" placeholder="Title"></div>
                    <div class="form-group">
                        <label for="comment">Comment</label>
                        <textarea name="text" class="form-control gatta" rows="8" placeholder="Write here.."></textarea>
                    </div>
                    <input id="input-1" name="feedback" class="rating rating-loading" data-min="0" data-max="5" data-step="1">

                        <button id="sub-review" type="submit" class="btn btn-success">
                            Submit
                        </button>
                    </form>
                </div>
            </div>

            <div class="push"></div>
