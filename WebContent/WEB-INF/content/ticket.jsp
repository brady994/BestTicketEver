<<<<<<< HEAD
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
                    <a role="buttonclick" class="collapsed" href="#reviews" data-toggle="collapse" data-target="#reviewss" id="show-r">See reviews</a>
                </figcaption>
            </div>

            <div class="media-body media-description col-md-8 col-xs-8">
                <div class="jumbotron">
                    <h1 class="media-heading">${event.name }</h1>
                    <dl class="dl-horizontal">
                        <dt>
                            Date:</dt>
                        <dd>
                            ${event.date}</dd>
                        <dt>Location:</dt>
                        <dd>${event.location}</dd>
                        <dt>Guest</dt>
                        <c:forEach items="${event.guests}" var="entry">
                          <dd>${entry.value.name}</dd>
                        </c:forEach>
                        <dt>Description</dt>
                        <dd>${event.description}</dd>
                    </dl>

                </div>
            </div>
        </div>
    </div>
    <h1>
        Tickets</h1>
    <div id="table-ticket">
        <table class="table table-striped table-responsive">
            <thead>
                <tr>
                    <th>
                        Category
                    </th>
                    <th>
                        Seat
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
                                ${ fn:length(entry.value)}</em>
                        </td>
                        <td>
                            ${entry.value[0].price}
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-cart">
                                <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true" id="#c${count.index}"></span>
                                Add to cart
                            </button>

                        </td>
                        <td>
                            <button type="button" class="btn btn-default wish hidden-xs" data-toggle="modal" data-target="#w">
                                <span class="glyphicon glyphicon-star-empty pull-right" aria-hidden="true"></span>
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
                <input type="text" class="form-control" name="title" id="title" placeholder="Title"></div>
                <div class="form-group">
                    <label for="comment">Comment</label>
                    <textarea name="text" class="form-control" rows="8" placeholder="Write here.."></textarea>
                </div>
                <input id="input-1" name="feedback" class="rating rating-loading" data-min="0" data-max="5" data-step="1">

                    <button id="sub-review" type="submit" class="btn btn-default">
                        Submit
                    </button>
                </form>
            </div>
        </div>

        <div class="push"></div>
=======
<div class="container">
    <div class="media">
        <div class="media-left">
            <img class="media-object" src="Img/summer.jpg" alt="evento" />
            <figcaption> <a href="#reviews">See reviews</a></figcaption>
        </div>
        <c:forEach items="${Event}" var =" event">
        <div class="media-body media-description">
            <h1>Event Title</h1>
            <dl class="dl-horizontal">
                <dt> Date:</dt>
                <dd> ${event.date }</dd>
                <dt>Location:</dt>
                <dd>${event.location }</dd>
            </dl>

        </div><%!  %></c:forEach>
    </div>
    <h1> Tickets</h1>
    <div class="table-ticket">
        <table class="table table-striped table-responsive">
            <thead>
                <tr>
                    <th>
                        Category
                    </th>
                    <th>
                        Seat
                    </th>
                    <th>
                        Price
                    </th>
                    <th>
                    </th>
      				 <th>
                       Wishlist
                     </th>                 
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        Platea
                    </td>
                    <td>
                        <em> 10/100</em>
                    </td>
                    <td>
                        20$
                    </td>
                    <td>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true"></span> Add to cart
                        </button>
                        
                    </td>
                        <td>
                            <form class="" method="post">
                                <select class="form-control">
                                    <option id="">1</option>
                                    <option id="">2</option>
                                    <option id="">3</option>
                                    <option id="">4</option>
                                    <option id="">5</option>
                                </select>
                            </form>

                        </td>
                </tr>
                <tr>
                    <td>
                        Tribuna
                    </td>
                    <td>
                        <em>19/20</em>
                    </td>
                    <td>
                        10$
                    </td>
                    <td>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true"></span> Add to cart
                        </button>
                        
                    </td>
                        <td>
                            <form class="" method="post">
                                <select class="form-control">
                                    <option id="">1</option>
                                    <option id="">2</option>
                                    <option id="">3</option>
                                    <option id="">4</option>
                                    <option id="">5</option>
                                </select>
                            </form>

                        </td>
                </tr>
                <tr>
                    <td>
                        Posto in piedi
                    </td>
                    <td>
                        <em>20/20</em>
                    </td>
                    <td>
                        50$
                    </td>
                    <td>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart pull-right hidden-xs" aria-hidden="true"></span> Add to cart
                        </button>
                       
                    </td>
                       <td>
                            <form class="" method="post">
                                <select class="form-control">
                                    <option id="">1</option>
                                    <option id="">2</option>
                                    <option id="">3</option>
                                    <option id="">4</option>
                                    <option id="">5</option>
                                </select>
                            </form>

                        </td>
                </tr>

            </tbody>

        </table>
    </div>

    <h2>Reviews</h2>
    <div class="panel panel-default" id="reviews">
        <div class="panel-heading">
            <div class="row">
                <div class="col col-xs-6">
                    <h1 class="panel-title">Reviews Customer </h1>
                </div>

                <div class="col col-xs-6 text-right">
                    <a class="collapsed" href="#form-review" data-toggle="collapse" data-target="#form-review"> Write review<i class="glyphicon glyphicon-pencil pull-right"></i></a>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <ul class="list-unstyled">
                <li>
                    <p class="title-review">
                        Title
                    </p>
                    <p class="customer">
                        Customer
                    </p>
                    <p>
                        Text
                    </p>
                </li>
                <hr>
                <li>
                    <p class="title-review">
                        Title
                    </p>
                    <p class="customer">
                        Customer
                    </p>
                    <p>
                        Text
                    </p>
                </li>
                <hr>
            </ul>
        </div>
    </div>
    <div class="collapse panel-collapse" id="form-review">
        <form role="form" method="post">
            <div class="form-group">
                <label for="Title">Title</label>
                <input type="text" class="form-control " id="title" placeholder="Title">
            </div>
            <div class="form-group">
                <label for="comment">Comment</label>
                <textarea name="text-review" class="form-control" rows="8" placeholder="Write here.."></textarea>
            </div>
            <button type="submit" class="btn btn-default">
                Submit
            </button>
        </form>
    </div>
</div>




<div class="push">

</div>
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
