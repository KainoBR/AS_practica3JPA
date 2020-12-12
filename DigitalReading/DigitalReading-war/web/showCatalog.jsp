
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- si no se incluye no funciona, aunque la incluya el header no lo hereda -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- si no se incluye no funciona : |  -->

<!--- Detalles de un libro seleccionado -->

<jsp:include page="./WebComponent/header.jsp"/>
<jsp:include page="./WebComponent/navbar.jsp"/>

<div class="container py-3">
    <div class="row d-flex justify-content-center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item">
                    <c:choose>

                        <c:when test="${value>0}">
                            <a class="page-link" href="FrontController?command=showCatalog&accion=prev" aria-label="Previous"/>
                        </c:when>
                        <c:otherwise>
                            <a class="page-link " href="#" aria-label="Previous"/>
                        </c:otherwise>
                    </c:choose>
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                    </a>
                </li>

                <c:forEach var = "i" begin = "0" end = "${catalogo.size()-1}">

                    <c:url var="showCatalog" value="FrontController">

                        <c:param name="command" value="showCatalog"></c:param>
                        <c:param name="value" value="${i}"></c:param>

                    </c:url>
                    <c:choose>
                        <c:when test="${i == value}">
                            <li class="page-item active">
                                <a class="page-link" href="${showCatalog}">${i+1}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${showCatalog}">${i+1}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                <li class="page-item">
                    <c:choose>

                        <c:when test="${value<catalogo.size()-1}">
                            <a class="page-link" href="FrontController?command=showCatalog&accion=next" aria-label="Next"/>
                        </c:when>
                        <c:otherwise>
                            <a class="page-link " href="#" aria-label="Next"/>
                        </c:otherwise>
                    </c:choose>
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <div class="row d-flex justify-content-center">
        <c:forEach var="libro" items="${revista}">

            <div class="col-md-3 bg-dark text-center">
                <div class="card mb-2 shadow-sm ">

                    <div class="card-header">${libro.title} </div>

                    <div class="card-body">
                        <img class="card-img-top img-responsive" src="${libro.getCoverpage()}" alt="Card image cap" width="10" height="250">

                        <div>
                            <label>${libro.getAuthor()}</label>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <small class="text-muted">${libro.getLikes()} &nbsp 
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                </svg>
                            </small>
                        </div>
                    </div>

                </div>
            </div>

        </c:forEach>
    </div>
</div>

<jsp:include page="./WebComponent/modals.jsp"/>
<jsp:include page="./WebComponent/footer.jsp"/>