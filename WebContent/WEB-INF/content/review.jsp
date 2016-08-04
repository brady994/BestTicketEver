<div class="container">
    <div class="row">
        <c:forEach items="${reviews}" var="eventReview">
        <div class="span8 col-xs-6">
        
            <h4><strong>${eventReview.title}</strong></h4>
            <p>
                <a href="#">Event Name</a>
            </p>
        </div>
        </c:forEach>
    </div>
     <c:forEach items="Review" var=" review">
    <div class="row">
        <div class="col-xs-2 col-md-2 review">
            <div class="panel status">
                <input id="ratings-hidden" name="rating" type="hidden">

                <div class="stars starrr" data-rating="0">
                    <span class="glyphicon glyphicon-star-empty"></span>
                    <span class="glyphicon glyphicon-star-empty"></span>
                    <span class="glyphicon glyphicon-star-empty"></span>
                    <span class="glyphicon glyphicon-star-empty"></span>
                    <span class="glyphicon glyphicon-star-empty"></span>
                </div>
            </div>

        </div>
        
        <div class="col-xs-6 col-md-8">
            <p>
                ${review.text}
            </p>
        </div>
        
    </div>
   
    <div class="row">
        <div class="col-xs-6 col-md-8">
            <p>
                <i class="fa fa-calendar"></i> ${review.event.date }
            </p>

        </div>
    </div>
     </c:forEach>
    <hr>
</div>
