<%-- 
    Document   : index
    Created on : 05-sep-2020, 19:42:18
    Author     : Miki
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<jsp:include page="./WebComponent/header.jsp"/>
<jsp:include page="./WebComponent/navbar.jsp"/>
<!-- si no se incluye no funciona, aunque la incluya el header no lo hereda -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- si no se incluye no funciona : |  -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="px-3">
    <div class="py-3 text-center">
        <p class="lead">DigitalReading!</p>
    </div>
    <div class="row">
        <div class="col-md-4 order-md-2 ">
            <jsp:include page="./WebComponent/cart.jsp"/>
        </div>
        <div class="col-md-8 order-md-1">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="FrontController?command=showMainPage">Catalog</a></li>
                    <li class="page-item"><a class="page-link" href="FrontController?command=showWhishlist">Whishlist</a></li>
                </ul>
            </nav>
            <c:choose>
                <c:when test="${showWhishlist}">
                    <jsp:include page="./WebComponent/whishlist.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="./WebComponent/catalogo.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<jsp:include page="./WebComponent/modals.jsp"/>
<jsp:include page="./WebComponent/footer.jsp"/>
