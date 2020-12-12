/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import entities.Book;
import EJB.Log;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class searchBooks extends FrontCommand {

    static String MSG = "#=== searchBook";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");
            log.addLogs(MSG);

            HttpSession session = request.getSession();

            String filter = request.getParameter("filter");
            String option = request.getParameter("option");
            if (filter == null || filter.isEmpty() || option == null) {
                bookDB.findAll();
                forward("/searchBooks.jsp");
            } else {
                String order = request.getParameter("order");
                filter = "%" + filter + "%";
                List<Book> bookFound = null;
                switch (option) {
                    case "title":
                        if ("date".equals(order)) {
                            bookFound = bookDB.searchBooksByTitleOrdened(filter);
                        } else {
                            bookFound = bookDB.searchBooksByTitle(filter);
                        }
                        break;
                    case "author":
                        if ("date".equals(order)) {
                            bookFound = bookDB.searchBooksByAuthorOrdened(filter);
                        } else {
                            bookFound = bookDB.searchBooksByAuthor(filter);
                        }
                        break;
                    case "genre":
                        if ("date".equals(order)) {
                            bookFound = bookDB.searchBooksByGenreOrdened(filter);
                        } else {
                            bookFound = bookDB.searchBooksByGenre(filter);
                        }
                        break;
                }
                session.setAttribute("bookFound", bookFound);

                forward("/searchBooks.jsp");
            }
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(searchBooks.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
