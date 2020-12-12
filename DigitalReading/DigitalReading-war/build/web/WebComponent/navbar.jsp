<%-- 
    Document   : navbar
    Created on : 06-sep-2020, 23:08:41
    Author     : Miki
--%>

<!-- si no se incluye no funciona, aunque la incluya el header no lo hereda -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- si no se incluye no funciona : |  -->


<nav class="navbar navbar-expand-lg navbar-light" style="background-color: indianred; color: white">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <c:if test="${user != null}">

                <li class="nav-item active">
                    <a class="nav-link" style="color: white" href="mainPage.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="FrontController?command=showBookShelf">MyBookShelf</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="FrontController?command=showComments">MyComments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="FrontController?command=searchBooks">SearchBooks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="FrontController?command=loadCatalog">showCatalog</a>
                </li>
                <c:if test="${user.getAdmin()==1}">
                    <li class="nav-item" style="color: white">
                        <a class="nav-link" style="color: white" href="FrontController?command=showBooks">| Books</a>
                    </li>
                    <li class="nav-item" style="color: white">
                        <a class="nav-link" style="color: white" href="FrontController?command=showPageStats">Stats</a>
                    </li>
                    <li class="nav-item" style="color: white">
                        <a class="nav-link" style="color: white" href="FrontController?command=showSystemLog">System log</a>
                    </li>
                    <li class="nav-item" style="color: white">
                        <a class="nav-link" style="color: white" href="FrontController?command=showAllStatefulBeans">StatefulBeans</a>
                    </li>
                    <li class="nav-item" style="color: white">
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#makeAdmin">makeAdmin</button>
                    </li>
                </c:if>
            </c:if>
        </ul>
    </div>

    <div>
        <ul class="navbar-nav">

            <li class="nav-item">
                <a href="FrontController?command=showProfile" class="nav-link" style="color: white">${user.getUsername().getFirstname()} ${user.getUsername().getLastname()} </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" style="color: white" href="FrontController?command=logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<!-- Modal add Timer -->
<div class="modal fade" id="makeAdmin" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: indianred">
                <h5 class="modal-title text-center" style="color: white">make Admin</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="FrontController">
                <input type="hidden" name="command" value="makeAdmin">
                <div class="modal-body" style="background-color: GhostWhite">
                    <label>Introduce userID </label>
                    <input type="text" name="userID"/>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">submit</button>
                </div>
            </form>
        </div>  
    </div>
</div>

