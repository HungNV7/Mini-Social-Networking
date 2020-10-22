<%-- 
    Document   : notify
    Created on : Sep 29, 2020, 7:18:51 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <title>Notification Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            .container{
                margin-top: 3%;
            }
            p{
                margin-bottom: 0;
            }
            a{
                text-decoration: none;
                color: #000;
            }
            a:hover{
                text-decoration: none;
                color: #000;
            }
            .seen{
                background-color: gainsboro;
            }
            .media{
                margin-bottom: 1%;

            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="MainController?btnAction=Search&txtSearch=">Entropi</a>
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="MainController?btnAction=Search&txtSearch=">Home <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <a href="MainController?btnAction=Notify" class="btn btn-outline-success my-2 my-sm-0" type="submit" style="margin-right: 10px;">Notification</a>
                <a href="MainController?btnAction=Logout" class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</a>
            </div>
        </nav>

        <div class="container">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                    <c:forEach var="notification" items="${sessionScope.NOTIFICATIONS}">
                        <c:choose>
                            <c:when test="${notification.type == 'Comment'}">
                                <a href="MainController?btnAction=Detail Notify&id=${notification.articleID}&notifyID=${notification.id}">
                                    <c:choose>
                                        <c:when test="${notification.isRead}">
                                            <div class="media">
                                                <c:if test="${not empty notification.image}">
                                                    <img src="Image/${notification.image}" class="mr-3" alt="..." style="height: 10vh;">
                                                </c:if>

                                                <div class="media-body">
                                                    <p><b>${notification.name}</b> commented on your post!</p>
                                                    <small>${notification.date}</small>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="media seen">
                                                <c:if test="${not empty notification.image}">
                                                    <img src="Image/${notification.image}" class="mr-3" alt="..." style="height: 10vh;">
                                                </c:if>
                                                <div class="media-body">
                                                    <p><b>${notification.name}</b> commented on your post!</p>
                                                    <small>${notification.date}</small>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="MainController?btnAction=Detail Notify&id=${notification.articleID}&notifyID=${notification.id}">
                                    <c:choose>
                                        <c:when test="${notification.isRead}">
                                            <div class="media">
                                                <c:if test="${not empty notification.image}">
                                                    <img src="Image/${notification.image}" class="mr-3" alt="..." style="height: 10vh;">
                                                </c:if>
                                                <div class="media-body">
                                                    <p><b>${notification.name}</b> reacted to your post!</p>
                                                    <small>${notification.date}</small>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="media seen">
                                                <c:if test="${not empty notification.image}">
                                                    <img src="Image/${notification.image}" class="mr-3" alt="..." style="height: 10vh;">
                                                </c:if>
                                                <div class="media-body">
                                                    <p><b>${notification.name}</b> reacted to your post!</p>
                                                    <small>${notification.date}</small>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </div>
                <div class="col-2"></div>
            </div>

        </div>


    </body>
</html>
