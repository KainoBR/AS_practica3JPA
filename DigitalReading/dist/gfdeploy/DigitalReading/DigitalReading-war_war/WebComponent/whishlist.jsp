
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- si no se incluye no funciona, aunque la incluya el header no lo hereda -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- si no se incluye no funciona : |  -->


<div class=" py-3 px-3">
    <div class="row">
        <c:forEach var="libro" items="${lista}">

            <c:url var="addBookToCart" value="FrontController">

                <c:param name="command" value="addBookToCart"></c:param>
                <c:param name="bookID" value="${libro.id}"></c:param>

            </c:url>

            <c:url var="showBook" value="FrontController">

                <c:param name="command" value="showBookDetails"></c:param>
                <c:param name="bookID" value="${libro.id}"></c:param>

            </c:url>

            <c:url var="removeBook" value="FrontController">

                <c:param name="command" value="removeBookFromWhishlist"></c:param>
                <c:param name="bookID" value="${libro.id}"></c:param>

            </c:url>


            <div class="col-md-4 bg-dark text-center py-3">
                <div class="card">
                    <div class="card-header py-3" style="height: 90px">${libro.title} </div>
                    <div class="card-body py-3">
                        <img class="card-img-top img-responsive" src="${libro.getCoverpage()}" alt="Card image cap" width="50" height="250">

                        <div class="d-flex justify-content-between align-items-center py-3 px-2">
                            <div class="btn-group">
                                <c:choose>

                                    <c:when test = "${carrito.contains(libro)}">
                                        <button type="button" data-toggle="modal" data-target="#cartContainTheBook" class="btn btn-warning">buy</button>
                                    </c:when>

                                    <c:when test = "${false}">
                                        <button type="button" data-toggle="modal" data-target="#bookIsAlreadyInTheBookshelf" class="btn btn-warning">buy</button>
                                    </c:when>

                                    <c:otherwise>
                                        <a href="${addBookToCart}" class="btn btn-warning">buy</a>
                                    </c:otherwise>
                                </c:choose>
                                <a href="${showBook}" class="btn btn-info">details</a>
                                <a href="${removeBook}" class="btn btn-warning">remove</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </c:forEach>
    </div>
</div>

