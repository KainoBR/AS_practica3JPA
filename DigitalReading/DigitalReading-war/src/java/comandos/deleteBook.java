/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import EJB.CommentFacade;
import EJB.LibraryFacade;
import EJB.Log;
import EJB.WhishlistFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class deleteBook extends FrontCommand {

    static String MSG = "#=== deleteBook";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            LibraryFacade libraryDB;
            libraryDB = (LibraryFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/LibraryFacade");

            CommentFacade commentDB;
            commentDB = (CommentFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/CommentFacade");
            
            WhishlistFacade whishlistDB;
            whishlistDB = (WhishlistFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/WhishlistFacade");

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            HttpSession session = request.getSession();

            /*
            ModelBook.delete(bookID);
            ModelComment.deleteAllCommentFromThisBook(bookID);
            ModelBiblioteca.deleteBookFromAllBookShelf(bookID);
             */
            commentDB.removeAllCommentsFromThisBook(bookID);
            libraryDB.removeThisBookFromAllLibrary(bookID);
            whishlistDB.removeThisBookFromAllWhishlist(bookID);
            bookDB.remove(bookDB.find(bookID));
            //libraryDB.removeThisBookFromAllUsersLibrarys(bookID);

            session.setAttribute("catalogo", bookDB.findAll());

            forward("/showBooks.jsp");
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(deleteBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
