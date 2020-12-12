/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.CommentFacade;
import entities.Book;
import EJB.Log;
import entities.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showComments extends FrontCommand {

    static String MSG = "#=== showComments";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            CommentFacade commentDB;
            commentDB = (CommentFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/CommentFacade");

            HttpSession session = (HttpSession) request.getSession();

            User usuario = (User) session.getAttribute("user");

            int userID = usuario.getId();

            List<Book> catalogo = (List<Book>) session.getAttribute("catalogo");

            // la idea es obtener Todos los comentarios de Todos los libros realizados por el usuario
            for (Book book : catalogo) {
                book.setComments(commentDB.getCommentFromThisUserOfThisBook(userID, book));
            }

            session.setAttribute("catalogo", catalogo);

            forward("/showMyComments.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showComments.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
