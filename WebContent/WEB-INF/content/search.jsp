<div class="container conteiner-event">
<h2 class="text-center"> Search for : ${Event.name} </h2>
<div class="row row-event">
  <c:forEach items="${Event}" var="event">
    <div class=" col-md-3">
        <div class="flip-container">
            <div class="card card-block">
                <div class="front">
                    <div class="thumbnail">
                        <img class="img-responsive" src="${event.image}" alt="event">
                    </div>
                </div>
                <!-- end face front-->
                <div class="back">
                  <dl class="dl-horizontal">
                      <dt> Date:</dt>
                      <dd> ${event.date}</dd>
                      <dt>Location:</dt>
                      <dd>${event.location}</dd>
                  </dl>
                    <div class=" text-center">
                        <a href="home?action=singleevent" role="button" class=" btn btn-default text-center"> Tickets</a>
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
