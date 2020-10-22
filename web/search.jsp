<%-- 
    Document   : home
    Created on : Sep 16, 2020, 8:23:25 PM
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
        <title>Search Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            .container{
                margin-top: 5%;
            }
            .form-control{
                border: 1px solid;
            }
            .posts{
                margin-top: 4%;
            }
            h2{
                text-align: center;
                margin-bottom: 2%;
            }
            .pagination{
                margin-top: 1%;
            }
            .card{
                margin-bottom: 3%;
                background: #fff;
                box-shadow: 0px 0px 2px 0px #000;
            }
            .text{
                overflow: auto;
                word-break: break-all;
                font-family: sans-serif;              
            }
            .card-title{
                margin-bottom: 0;
            }
            .card-text{
                margin: 0
            }
            a{
                text-decoration: none;
                color: #000;
            }
            a:hover{
                text-decoration: none;
                color: #000;
            }
            .content{
                margin: 2% 0;
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
                <div class="col-2"></div>
                <div class="col-7">
                    <h2>Welcome ${sessionScope.ACCOUNT.name}</h2>
                </div>
                <div class="col-1"></div>
                <div class="col-2"></div>
            </div>
            <form action="MainController">
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-7">
                        <input type="text" class="form-control" name="txtSearch" value="${param.txtSearch}">
                    </div>
                    <div class="col-1">
                        <input class="btn btn-secondary" type="submit" value="Search" name="btnAction">
                    </div>
                    <div class="col-2">
                        <input class="btn btn-secondary" type="submit" name="btnAction" value="Create Article"/>
                    </div>
                </div>
            </form>

            <form action="MainController">
                <div class="row">

                    <div class="col-2"></div>
                    <div class="col-7">                      

                        <div class="posts">

                            <c:if test="${sessionScope.LIST_ARTICLE != NULL}">  

                                <c:forEach var="article" items="${sessionScope.LIST_ARTICLE}">
                                    <a href="MainController?btnAction=Detail&id=${article.id}" >
                                        <div class="card card-body">  

                                            <h5 class="card-title">${article.accountName}</h5>
                                            <small>${article.date}</small>
                                            <div class="content">
                                                <p>${article.title}</p>
                                                <c:forEach var="string" items="${article.description}">
                                                    <p class="card-text">${string}</p>
                                                </c:forEach>
                                            </div>

                                            <c:if test="${not empty article.image}">  
                                                <img src="Image/${article.image}" class="card-img-top" alt="...">
                                            </c:if>

                                            <c:if test="${article.email == sessionScope.ACCOUNT.email}">
                                                <a data-toggle="modal" data-target="#popup${article.id}" class="image-trash"><img src="Image/trash-3x.png" width="27px"></a>
                                                </c:if>

                                        </div>

                                        <!--Modal-->
                                        <div class="modal fade" id="popup${article.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                                            <input type="hidden" name="articleID" value="${article.id}"/>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>

                                </c:forEach>
                                <c:if test="${sessionScope.LIST_ARTICLE.size() != 0}">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <li class="page-item">
                                                <a class="page-link" href="MainController?btnAction=Search&txtSearch=${param.txtSearch}&page=${requestScope.page}&other=prev" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <c:if test="${sessionScope.COUNT != null}">
                                                <c:forEach begin="1" end="${sessionScope.COUNT}" varStatus="count">
                                                    <li class="page-item"><a class="page-link" href="MainController?btnAction=Search&txtSearch=${param.txtSearch}&page=${count.index}">${count.index}</a></li>
                                                    </c:forEach>
                                                </c:if>

                                            <a class="page-link" href="MainController?btnAction=Search&txtSearch=${param.txtSearch}&page=${requestScope.page}&other=next" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>

                            </c:if> 

                        </div>
                    </div>


                    <div class="col-1">
                    </div>
                    <div class="col-1">
                    </div>
                    <div class="col-1"></div>
                </div>
            </form>




        </div>



    </body>
</html>

<!-- form long form, enter luc search de kiem tra-->