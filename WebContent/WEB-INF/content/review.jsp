<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <div tabindex="-1" id="login-auto-close-alert" class="alert hidden">
        <p id="login-alert-text"></p>
    </div>
    <c:choose>
        <c:when test="${empty reviews}">
            <div class="alert alert-warning">
                <p>
                    We are sorry, you don't have any reviews.
                </p>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${reviews}" var="entry">
                <div class="row">
                    <div class="col-xs-6">

                        <h4>
                            <strong>${entry.value.title}</strong>
                        </h4>
                        <p>
                            <a href="home?action=singleevent&id=${entry.value.event.id}">${entry.value.event.name}</a>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-8 col-md-3 review">
                        <div class="panel status">
                            <input class="input-3" name="input-3" value="${entry.value.feedback}" class="rating-loading" data-size="sm"></div>

                        </div>

                        <div class="col-xs-10 col-md-8">
                            <p>
                                ${entry.value.text}
                            </p>
                        </div>

                    </div>
                    <hr></c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
