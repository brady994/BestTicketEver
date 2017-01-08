<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
    <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
        <p id="login-alert-text"></p>
    </div>
    <form id="form-cart" method="GET">

        <div class="panel panel-default panel-table">
            <div class="panel-heading">
                <div class="row">
                    <div class="col col-xs-6">
                        <h1 class="panel-title panel-title-cart">
                            Ticket in your cart
                        </h1>
                    </div>
                    <div class="col col-xs-6 text-right">
                        <button type="button" id="clear" class=" btn btn-warning">
                            <span class="glyphicon glyphicon-trash"></span>
                            Remove All
                        </button>
                    </div>

                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-cart" id="cart-table">
                        <c:choose>
                            <c:when test="${empty cart.tickets}">

                                <div class="alert alert-warning">
                                    <p>
                                        We are sorry, you don't have any tickets in your cart.
                                    </p>
                                </div>

                            </c:when>
                            <c:otherwise>

                                <thead>
                                    <tr>
                                        <th>
                                            Event
                                        </th>
                                        <th></th>
                                        <th>
                                            Quantity
                                        </th>
                                        <th>
                                            Price
                                        </th>
                                        <th>
                                            Total
                                        </th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${cart.tickets}" var="entry">
                                        <tr>
                                            <div class="row">
                                                <td class="col col-md-6 col-xs-8" id="${entry.key}">
                                                    <div class="media">
                                                        <div class="media-left col col-md-6 col-xs-8">
                                                            <img class="media-object img-responsive" src="${entry.value.event.image}" alt="${entry.value.event.name}"/>
                                                        </div>
                                                        <div class="media-body col col-md-6 col-xs-4">
                                                            <a href="#">
                                                                <h4 class="media-heading">${entry.value.event.name}
                                                                </h4>
                                                            </a>
                                                            <dl>
                                                                <dt>
                                                                    Date</dt>
                                                                <dd>${entry.value.event.date}</dd>
                                                                <dt>Location</dt>
                                                                <dd>${entry.value.event.location}</dd>

                                                            </dl>
                                                        </div>

                                                    </div>
                                                </td>
                                                <td class=" col col-md-2 col-xs-6">
                                                    <dl>
                                                        <dt>Ticket Category</dt>
                                                        <dd>${entry.value.category.name}</dd>

                                                    </dl>
                                                </td>
                                                <td class="col col-md-2 col-xs-4">
                                                    <select name="quantity" class="form-control input-sm">
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                        <option value="4">4</option>
                                                        <option value="5">5</option>
                                                    </select>
                                                </td>

                                                <td class="col col-md-1 col-xs-4">
                                                    ${entry.value.price}
                                                </td>

                                                <td class="col col-md-1 col-xs-4">
                                                    <strong>${entry.value.price}</strong>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-warning rm-item">
                                                        <span class="glyphicon glyphicon-remove pull-left"></span>
                                                        Remove
                                                    </button>
                                                </td>

                                            </div>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>

                                            <a href="home?action=index" role="button" class=" btn btn-info">
                                                <i class="fa fa-angle-left"></i>
                                                Continue
                                            </a>

                                        </td>
                                        <td>
                                            <strong>
                                                <span id="total">${cart.total}</span>$</strong>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-success">
                                                Checkout
                                                <i class="fa fa-angle-right" aria-hidden="true"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="push"></div>
