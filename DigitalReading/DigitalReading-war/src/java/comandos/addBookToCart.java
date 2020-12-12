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
import entities.Cart;
import EJB.Log;
import EJB.StatefulBeans;
import entities.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class addBookToCart extends FrontCommand {

    static String MSG = "#=== addBookToCart";

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

            log.addLogs(MSG);

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            Cart carrito = (Cart) session.getAttribute("carrito");

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            //ModelBiblioteca.getBookShelf(user.getId()
            List<Book> bookShelf = libraryDB.getUserLibrary(user.getId());

            //ModelBook.getBook(bookID);
            Book bookToBuy = bookDB.find(bookID);

            if (!carrito.contains(bookToBuy) & !libraryDB.isAlreadyContains(user, bookToBuy)) {

                carrito.addBookToCart(bookToBuy);

                StatefulBeans allStatefulBean;
                allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/StatefulBeans");

                allStatefulBean.updateCart(user.getId(), carrito.cartToString());

                session.setAttribute("carrito", carrito);
            }
            forward("/mainPage.jsp");
        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(addBookToCart.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

    /*private boolean aux(List<Book> bookShelf, Book o) {
        for (Book book : bookShelf) {
            if (book.getId() == o.getId()) {
                return true;
            }
        }
        return false;
    }*/

}
