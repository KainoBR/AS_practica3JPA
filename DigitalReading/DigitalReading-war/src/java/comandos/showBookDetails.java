/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import EJB.CommentFacade;
import entities.Book;
import EJB.Log;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showBookDetails extends FrontCommand {

    static String MSG = "#=== showBookDetails";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            CommentFacade commentDB;
            commentDB = (CommentFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/CommentFacade");

            log.addLogs(MSG);

            HttpSession session = (HttpSession) request.getSession();

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            Book book = bookDB.find(bookID);

            book.setComments(commentDB.getAllCommentsOfThisBook(bookID));

            session.setAttribute("libro", book);

            if (book != null) {
                forward("/showBookDetails.jsp");
            } else {
                forward("/mainPage.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showBookDetails.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
