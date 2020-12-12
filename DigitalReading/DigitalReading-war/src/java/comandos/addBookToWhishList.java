/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import EJB.LibraryFacade;
import EJB.WhishlistFacade;
import entities.Book;
import EJB.Log;
import entities.User;
import entities.Whishlist;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class addBookToWhishList extends FrontCommand {

    static String MSG = "#=== addBookToWhishList";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            LibraryFacade libraryDB;
            libraryDB = (LibraryFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/LibraryFacade");

            WhishlistFacade whishlistDB;
            whishlistDB = (WhishlistFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/WhishlistFacade");

            log.addLogs(MSG);

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            //ModelBook.getBook(bookID);
            Book bookToAdd = bookDB.find(bookID);

            List<Book> lista = whishlistDB.getUserWhishlist(user.getId());
            boolean cont=false;
            if (lista.size() > 0) {

                for (Book book : lista) {
                    if (book.getId().equals(bookToAdd.getId())) {
                        cont = true;
                    }
                }
            }
            System.out.println(cont);
            //si no está ya en la lista de deseos en la biblioteca
            if ( !cont & !libraryDB.isAlreadyContains(user, bookToAdd)) {

                Whishlist wishlistEntity = new Whishlist(user);
                wishlistEntity.setIdbook(bookToAdd);

                whishlistDB.create(wishlistEntity);

                lista = whishlistDB.getUserWhishlist(user.getId());
                session.setAttribute("lista", lista);
            }
            forward("/mainPage.jsp");
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(addBookToCart.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
