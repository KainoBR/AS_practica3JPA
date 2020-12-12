/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import EJB.LibraryFacade;
import entities.Book;
import entities.Library;
import EJB.Log;
import EJB.Stats;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class removeBookFromBookshelf extends FrontCommand {

    static String MSG = "#=== removeBookFromBookshelf";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            LibraryFacade libraryDB;
            libraryDB = (LibraryFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/LibraryFacade");
            
            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Stats");

            HttpSession session = request.getSession();

            int bookID = Integer.parseInt(request.getParameter("bookID"));
            User user = (User) session.getAttribute("user");

            int userID = user.getId();

            //ModelBiblioteca.deleteBookFromBookShelf(userID, bookID);
            Book book = bookDB.find(bookID);
            
            Integer libraryID = libraryDB.getLibraryIDbyUserANDBook(user, book);
            
            libraryDB.remove(libraryDB.find(libraryID));

            user.setAccount(user.getAccount() + bookDB.find(bookID).getPrice());

            stats.returnABook();

            //ModelBiblioteca.getBookShelf(userID)
            session.setAttribute("biblioteca", libraryDB.getUserLibrary(userID));
            session.setAttribute("user", user);

            forward("/showMyBookShelf.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(removeBookFromBookshelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
