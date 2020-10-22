<%-- 
    Document   : create
    Created on : Sep 20, 2020, 1:20:12 AM
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
        <title>Create Article Page</title>
        <style>
            body{
                background-color: whitesmoke;
            }
            h2{
                text-align: center;
            }
            .container-fluid{
                margin-top: 5%;
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
        <div class="container-fluid main row">
            <div class="col-3"></div>
            <div class="col-6">
                <h2>CREATE ARTICLE</h2>
                <form action="MainController" method="POST">
                    <div class="form-group">
                        <label>Title</label>
                        <input type="text" class="form-control" placeholder="Enter title" name="txtTitle" style="border: 1px solid;"/> 
                    </div>    
                    <div class="form-group">
                        <label>Description</label>
                        <textarea type="text" class="form-control" placeholder="Enter description"  name="txtDescription" style="border: 1px solid;" rows="10"></textarea> 
                    </div>  
                    <label>Link Image</label>
                    <div class="input-group mb-3">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="inputGroupFile02" name="txtImage">
                            <label class="custom-file-label" for="inputGroupFile02" aria-describedby="inputGroupFileAddon02" style="border: 1px solid;">Choose file</label> 
                        </div> 
                    </div>
                    <c:if test="${not empty sessionScope.ERROR_CREATE_ARTICLE}">
                        <font color="red"><small>${sessionScope.ERROR_CREATE_ARTICLE}</small></font> 
                        </c:if>
                    <br>
                    <button type="submit" class="btn btn-primary btn-block" name="btnAction" value="Submit Create Article">Submit</button>
                    </br>
                </form>
            </div>
            <div class="col-3"></div>

            <c:remove var="ERROR_CREATE_ARTICLE" scope="session"></c:remove>
        </div>    
    </body>
    <script>
// Add the following code if you want the name of the file appear on select
        $(".custom-file-input").on("change", function () {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        });
    </script>
</html>
