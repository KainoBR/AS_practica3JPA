<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="javax.naming.InitialContext"%>
<jsp:include page="./WebComponent/header.jsp"/>
<jsp:include page="./WebComponent/navbar.jsp"/>


<div container>
    <div class="col-md-4 py-3">
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                Users session active: 
                <span class="badge badge-primary badge-pill">${stats.getUsersLogged().size()}</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
                Books purchased: 
                <span class="badge badge-primary badge-pill">${stats.getPurchasedBook()}</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
                Books returned: 
                <span class="badge badge-primary badge-pill">${stats.getReturnedBook()}</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">
                Carts completed: 
                <span class="badge badge-primary badge-pill">${stats.getCartsCompleted()}</span>
            </li>
        </ul>
    </div>
    <div class="py-3 px-3">
        <table class="table-hover">
            <tr>
                <th>Sessions active  </th>
            </tr>
            <c:forEach var="usuario" items="${stats.getUsersLogged()}">

                <tr> 
                    <td>${usuario}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="./WebComponent/footer.jsp"/>