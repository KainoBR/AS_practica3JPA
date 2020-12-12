/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.LibraryFacade;
import EJB.UserFacade;
import entities.Book;
import entities.Cart;
import entities.Library;
import EJB.Log;
import EJB.StatefulBeans;
import EJB.Stats;
import entities.User;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class addBookToBookshelf extends FrontCommand {

    //addBookToBookShelf equivale a comprar los libros
    static String MSG = "#=== addBookToBookshelf";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Stats");

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/StatefulBeans");

            LibraryFacade libraryDB;
            libraryDB = (LibraryFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/LibraryFacade");

            UserFacade userDB;
            userDB = (UserFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/UserFacade");

            //@Singleton STATS
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");
            Cart carrito = (Cart) session.getAttribute("carrito");

            int userID = user.getId();

            //ya está comprobado en el addToCart 
            //pero por precausión
            if (carrito.getTotalPrice() > user.getAccount()) {
                forward("/mainPage.jsp");
            }

            double totalprice = 0.0;
            for (Book book : carrito.getCart()) {
                //ModelBiblioteca.isAlreadyContains(userID, book.getId())
               if (!libraryDB.isAlreadyContains(user, book)) {

                    stats.buyABook();

                    totalprice += book.getPrice();
                    //ModelBiblioteca.insertBookInBookShelf(user.getId(), book.getId());
                    Library newLibrary = new Library(user, book);
                    libraryDB.create(newLibrary);
                }
            }

            user.setAccount(user.getAccount() - totalprice);

            //ModelUser.changeAccount(userID, user.getAccount());
            //userDB.changeAccount(userID, user.getAccount());
            userDB.edit(user);
            //user = userDB.find(userID);

            allStatefulBean.addCart(user.getId() + 100, carrito.cartToString() + " Cart completed");
            allStatefulBean.updateCart(user.getId(), "");

            stats.completedACart();

            carrito.clearCartBook();

            session.setAttribute("carrito", carrito);
            session.setAttribute("user", user);
            session.setAttribute("biblioteca", libraryDB.getUserLibrary(userID));

            forward("/showMyBookShelf.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(addBookToBookshelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
