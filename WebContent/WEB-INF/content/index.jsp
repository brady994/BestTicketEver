<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" data-spy="scroll">
    <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
        <p id="login-alert-text"></p>
    </div>
    <div class="row row-index">
        <div class="col-md-3">
            <div class="left-sidebar">
                <h2 class="text-center">Category</h2>
                <div class="panel-group category-products">
                    <!--category-productsr-->
                    <c:forEach items="${cat}" var="entry">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">

                                    <a data-toggle="collapse" data-parent="#accordian" href="#${entry.key}" class="collapsed">
                                        ${entry.value.name}
                                        <span class="caret pull-right"></span>
                                    </a>
                                </h4>
                            </div>

                            <div id="${entry.key}" class="panel-collapse collapse">
                                <div class="panel-body">

                                    <ul>
                                        <c:forEach items="${entry.value.children}" var="son">
                                            <li>
                                                <a class="sonn" id="${son.value.name}">${son.value.name}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>

                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!— /.col-lg-6 —>
        <div class="col-md-9 padding-right">

            <div id="carousel-generic" class="carousel slide" data-ride="carousel">

                <!— Wrapper for slides —>
                <div class="carousel-inner">
                    <div class="item active">

                        <c:forEach items="${newevent}" var="top" begin="0" end="2">
                            <div class="col-sm-4">
                                <div class="">
                                    <a href="home?action=singleevent&id=${top.value.id}">
                                        <img src="${top.value.image}" class="img-responsive" alt="event"></a>

                                    </div>
                                </div>

                                <!— end thumbnail —>
                            </c:forEach>
                        </div>
                        <div class="item">
                            <c:forEach items="${newevent}" var="top1" begin="3" end="5">
                                <div class="col-sm-4">
                                    <div class="">
                                        <a href="home?action=singleevent&id=${top1.value.id}">
                                            <img src="${top1.value.image}" class="img-responsive" alt="event"></a>

                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <%-- end item --%>

                    </div>
                    <!— end carousel-inner —>

                    <!— Controls —>
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
        <div class="container conteiner-event">
            <h2 class="text-center">
                Top Event
            </h2>
            <div class="row row-event">
                <c:forEach items="${neweventall}" var="Event">
                    <div class="col col-md-3 col-sm-3">
                        <div class="flip-container">
                            <div class="card card-block">
                                <div class="front">
                                    <div class="">
                                        <img class="img-responsive" src="${Event.value.image}" alt="event"></div>
                                    </div>
                                    <!— end face front-->
                                    <div class="back">
                                        <dl class="dl-horizontal">
                                            <dt>Name:</dt>
                                            <dd>
                                                ${Event.value.name}</dd>
                                            <dt>
                                                Date:</dt>
                                            <dd>
                                                ${Event.value.date}</dd>
                                            <dt>Location:</dt>
                                            <dd>${Event.value.location}</dd>
                                        </dl>
                                        <div class=" text-center">
                                            <a href="home?action=singleevent&id=${Event.value.id}" role="button" class=" btn btn-default text-center">
                                                Tickets</a>
                                        </div>
                                    </div>
                                    <!--end face back-->
                                </div>
                                <!--end card block-->
                            </div>
                            <%-- end flip-container --%>
                        </div>
                    </c:forEach>
                    <!--end col-->

                </div>
            </div>

            <div class="push"></div>
