<%-- 
    Document   : detail
    Created on : Sep 18, 2020, 4:55:45 PM
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
        <title>Detail Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            .container{
                margin-top: 5%;
            }
            .card-title{
                margin-bottom: 0;
            }
            .card-text{
                margin: 0;
            }
            hr{
                border-top: 1px solid rgba(0,0,0,1);
                opacity: 0.3;
                margin: 0;
            }
            button{
                margin: 0;
                border: 0;
                background: #fff;
            }
            .col-4{
                padding: 0;
            }
            .badge{
                font-size: 110%;
            }
            .content{
                margin-bottom: 1%;
            }
            .comment{
                margin-top: 3%;

                padding: 1% 3%;
                background-color: whitesmoke;
                position: relative;
            }

            .detail{ 
                height: 83vh;
                position: relative;
                overflow-y: scroll;
            }
            .comment p{
                margin-bottom: 0;
            }
            .image-trash{
                position: absolute;
                right: 4%;
            }
            .navbar{
                overflow: hidden;
                position: fixed;
                top: 0;
                width: 100%;
                z-index: 100;
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
                <div class="col-7">
                    <div class="card">
                        <c:if test="${not empty sessionScope.DETAIL_ARTICLE.image}">  
                            <img src="Image/${sessionScope.DETAIL_ARTICLE.image}" class="card-img-top">
                        </c:if>

                    </div>
                </div>
                <div class="col-5">
                    <div class="card detail">                     
                        <div class="card-body">
                            <c:if test="${sessionScope.DETAIL_ARTICLE.email == sessionScope.ACCOUNT.email}">
                                <a data-toggle="modal" data-target="#popup${sessionScope.DETAIL_ARTICLE.id}" class="image-trash"><img src="Image/trash-3x.png" width="25px"></a>
                                </c:if>
                            <h5 class="card-title">${sessionScope.DETAIL_ARTICLE.accountName}</h5>
                            <small>${sessionScope.DETAIL_ARTICLE.date}</small>
                            <div class="content">
                                <p>${sessionScope.DETAIL_ARTICLE.title}</p>
                                <c:forEach var="string" items="${sessionScope.DETAIL_ARTICLE.description}">
                                    <p class="card-text">${string}</p>
                                </c:forEach>
                            </div>
                            <c:if test="${sessionScope.DETAIL_ARTICLE.email == sessionScope.ACCOUNT.email}">
                                <a data-toggle="modal" data-target="#popup${sessionScope.DETAIL_ARTICLE.id}" class="image-trash"><img src="Image/trash-3x.png" width="27px"></a>
                                </c:if>
                            <hr>
                            <form action="MainController">
                                <div class="row">
                                    <div class="col-4">
                                        <button name="btnAction" value="Emotion-Like">
                                            <c:choose>
                                                <c:when test="${sessionScope.EMOTION.icon == 'Like'}">    
                                                    <img src="Image/like-color.jpg" width="33%"><span class="badge badge-light">${sessionScope.LIKE}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="Image/like.jpg" width="33%"><span class="badge badge-light">${sessionScope.LIKE}</span>
                                                </c:otherwise>
                                            </c:choose>

                                        </button>
                                    </div>
                                    <div class="col-4">
                                        <button name="btnAction" value="Emotion-Dislike">
                                            <c:choose>
                                                <c:when test="${sessionScope.EMOTION.icon == 'Dislike'}">    
                                                    <img src="Image/unlike-color.jpg" width="33%"><span class="badge badge-light">${sessionScope.DISLIKE}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="Image/unlike.jpg" width="33%"><span class="badge badge-light">${sessionScope.DISLIKE}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                    </div>
                                    <div class="col-4">

                                        <button type="submit" name="btnAction" value="Comment">
                                            <img src="Image/comment.png" width="33%" height="150%">
                                        </button>


                                    </div>
                                </div>
                            </form>
                            <hr>
                            <div class="comments">
                                <c:forEach var="comment" items="${sessionScope.COMMENTS}">
                                    <div class="card card-body comment">                      
                                        <b class="card-title">${comment.accountName}</b>
                                        <small>${comment.date}</small>
                                        <div>
                                            <p>${comment.detail}</p>
                                        </div>

                                        <c:if test="${comment.email == sessionScope.ACCOUNT.email}">
                                            <a data-toggle="modal" data-target="#popupCmt${comment.commentID}" class="image-trash"><img src="Image/trash-3x.png" width="17px"></a>
                                            </c:if> 
                                    </div>
                                    <div class="modal fade" id="popupCmt${comment.commentID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Delete comment?</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure that you want to delete this comment?
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="MainController">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        <button type="submit" class="btn btn-primary"  name="btnAction" value="Delete Comment">Delete</button>
                                                        <input type="hidden" name="commentID" value="${comment.commentID}"/>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>
                                <c:if test="${param.btnAction == 'Comment'}">
                                    <div class="card card-body comment">                      
                                        <div>
                                            <form action="MainController">
                                                <input class="form-control" type="text" name="txtComment" placeholder="Add new commnet here"/> 
                                                <button name="btnAction" value="Submit Comment"></button>
                                            </form>

                                        </div>
                                    </div>
                                </c:if>

                            </div>

                            <!--Modal-->
                            <div class="modal fade" id="popup${sessionScope.DETAIL_ARTICLE.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Delete post?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure that you want to delete this post?
                                        </div>
                                        <div class="modal-footer">
                                            <form action="MainController">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary"  name="btnAction" value="Delete Post">Delete</button>
                                                <input type="hidden" name="articleID" value="${sessionScope.DETAIL_ARTICLE.id}"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
