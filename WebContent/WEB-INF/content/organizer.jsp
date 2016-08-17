
    <div class="container">
<<<<<<< HEAD
      <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
          <p id="login-alert-text"></p>
      </div>
        <div class="panel panel-primary panel-table">
            <div class="panel-heading">
                <div class="row">
                    <div class="col col-xs-6">
                        <h1 class="panel-title">Event</h1>
                    </div>
                    <div class="col col-xs-6 text-right">
                        <button type="button" class="btn btn-sm btn-primary btn-create collapsed" data-toggle="collapse" data-target="#form-create-event">
                            Create New Event <i class="glyphicon glyphicon-menu-down"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive">
                <table class="table table-striped table-bordered table-list " id="table-organizer">

                    <thead>
                        <tr>
                            <th>
                                <em class="fa fa-cog"></em>
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
                                Price
                            </th>
                            <th>
                                Image
                            </th>
                        </tr>
                    </thead>
=======
        <div class="panel panel-primary panel-table">
            <div class="panel-heading">
                <div class="row">
                    <div class="col col-xs-6">
                        <h1 class="panel-title">Event</h1>
                    </div>
                    <div class="col col-xs-6 text-right">
                        <button type="button" class="btn btn-sm btn-primary btn-create collapsed" data-toggle="collapse" data-target="#form-create-event">
                            Create New Event <i class="glyphicon glyphicon-menu-down"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive">
                <table class="table table-striped table-bordered table-list " id="table-organizer">
                
                    <thead>
                        <tr>
                            <th>
                                <em class="fa fa-cog"></em>
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
                                Price
                            </th>
                            <th>
                                Image
                            </th>
                        </tr>
                    </thead>           
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
                    <tbody>
                    <c:forEach items="${events }" value="ev">
                        <tr>
                            <td align="center">
                                <a class="btn btn-default mod-event" role="button" id=""><em class="fa fa-pencil"></em></a>
                                <a class="btn btn-danger delete-event" role="button" id=""><em class="fa fa-trash-o"></em></a>
                            </td>
                            <td>
                                ${ev.name }
                            </td>
                            <td>
                                 ${ev.location }
                            </td>
                            <td>
                                ${ev.date }
                            </td>
                            <td>
                                 ${ev.category }
                            </td>
                            <td>
                                 ${ev.ticket.price }
                            </td>
                            <td>
                                 ${ev.image }
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>

        <div class="panel-collapse collapse " id="form-create-event">
            <form class="col-md-6 col-md-offset-3 form-horizontal" method="post">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="" placeholder="Name">
                </div>
                <div class="form-group">
                    <label for="Location">Location</label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
                        <input type="text" class="form-control" id="" placeholder="Location">
                    </div>
                </div>
                <div class="form-group">
                    <label for="date">Date</label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input type="date" class="form-control" id="" placeholder="Date">
                    </div>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-euro"></i></span>
                        <input type="text" class="form-control" id="" placeholder="Price">
                    </div>
                </div>
                <div class="form-group">
                    <label for="category">Category</label>
                    <select class="form-control">
                        <option>Bu</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>

                </div>
                <div class="form-group">
                    <label for=""></label>
                    <select class="form-control">
                        <option>Bu</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>

                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">
                    Submit <i class="glyphicon glyphicon-ok"></i>

                  </button>

                </div>


            </form>

        </div>
    </div>
