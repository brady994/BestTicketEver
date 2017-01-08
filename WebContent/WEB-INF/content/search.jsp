<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container conteiner-event" data-spy="scroll">
    <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
        <p id="login-alert-text"></p>
    </div>
        <div class="row row-event">

            <c:forEach items="${event}" var="event">
                <div class=" col col-md-3 col-sm-3">
                    <div class="flip-container">
                        <div class="card card-block">
                            <div class="front">
                                <div class="thumbnail">
                                    <img class="img-responsive" src="${event.value.image}" alt="event"></div>
                                </div>
                                <!-- end face front-->
                                <div class="back">
                                    <dl class="dl-horizontal">
                                        <dt>Name:</dt>
                                        <dd>${event.value.name}</dd>
                                        <dt>
                                            Date:</dt>
                                        <dd>
                                            ${event.value.date}</dd>
                                        <dt>Location:</dt>
                                        <dd>${event.value.location}</dd>
                                    </dl>
                                    <div class=" text-center">
                                        <a href="home?action=singleevent&id=${event.value.id}" role="button" class=" btn btn-info text-center">
                                            Tickets</a>
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
    <div class="push"></div>
