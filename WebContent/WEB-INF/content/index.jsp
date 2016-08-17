<div class="container" data-spy="scroll">
<div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
		<p id="login-alert-text"></p></div>
    <div class="row row-index">
        <div class="col-md-3">
            <div class="left-sidebar">
                <h2 class="text-center">Category</h2>
                <div class="panel-group category-products">
                    <!--category-productsr-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordian" href="#sport" class="collapsed">
                  Sport
                  <span class="caret pull-right"></span>
                </a>
              </h4>
                        </div>
                        <div id="sport" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul>
                                    <li><a href="#">Tennis</a></li>
                                    <li><a href="#">Calcio</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordian" href="#teatro" class="collapsed">
                    <span class="caret pull-right"></span>
                  Teatro
                </a>
              </h4>
                        </div>
                        <div id="teatro" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul>
                                    <li><a href="#">Opera</a></li>
                                    <li><a href="#">Balletto</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordian" href="#cinema" class="collapsed">
                    <span class="caret pull-right"></span>
                  Cinema
                </a>
              </h4>
                        </div>
                        <div id="cinema" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul>
                                    <li><a href="#">Azione</a></li>
                                    <li><a href="#">Drammatico</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <!-- /.col-lg-6 -->
        <div class="col-md-9 padding-right">

            <div id="carousel-generic" class="carousel slide" data-ride="carousel">

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">

                      <c:forEach items="${event}" var="topEvent" begin="0" end="2">
                          <div class="col-sm-4">
                              <div class="thumbnail">
                                  <a href="home?action=singleevent"> <img src="${topEvent.image}" alt="event"> </a>

                              </div>
                          </div>

                          <!-- end thumbnail -->
                      </c:forEach>
                      </div>
                      <div class="item">
                        <c:forEach items="${event}" var="topEvent" begin="3" end="5">
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <a href="home?action=singleevent"> <img src="${topEvent.image}" alt="event"> </a>

                            </div>
                        </div>
                        </c:forEach>
                      </div>
                    </div>

                    <!--end item -->

                </div>
                <!-- end carousel-inner -->

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-generic" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-generic" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
            <!--end carousel-generic-->
        </div>

    </div>
    <!-- /.row -->
    <div class="container conteiner-event">

    <h2 class="text-center"> Top Event </h2>
    <div class="row row-event">
      <c:forEach items="${event}" var="topEvent">
        <div class=" col-md-3">
            <div class="flip-container">
                <div class="card card-block">
                    <div class="front">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${topEvent.image}" alt="event">
                        </div>
                    </div>
                    <!-- end face front-->
                    <div class="back">
                      <dl class="dl-horizontal">
                        <dt>Name:</dt>
                        <dd> "${topEvent.name}"</dd>
                      <dt> Date:</dt>
                      <dd> "${topEvent.date}"</dd>
                      <dt>Location:</dt>
                      <dd>"${topEvent.location}"</dd>
                      </dl>
                        <div class=" text-center">
                            <a href="home?action=singleevent&id=5" role="button" class=" btn btn-default text-center"> Tickets</a>
                        </div>
                    </div>
                    <!--end face back-->
                </div>
                <!--end card block-->
            </div>
            <!--end flip-container -->
        </div>
      </c:forEach>
        <!--end col-->

    </div>
</div>
</div>
<div class="push">
</div>
<footer>
    <p>
        <em>Copyright &copy by Eliana Palermiti</em>
    </p>
</footer>