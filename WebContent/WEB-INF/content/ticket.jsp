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
