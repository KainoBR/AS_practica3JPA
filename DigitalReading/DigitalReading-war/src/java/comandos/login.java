/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import EJB.LibraryFacade;
import EJB.UserFacade;
import EJB.WhishlistFacade;
import entities.Book;
import entities.Cart;
import EJB.Log;
import EJB.StatefulBeans;
import EJB.Stats;
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
public class login extends FrontCommand {

    static String MSG = "#=== login";

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

            UserFacade userDB;
            userDB = (UserFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/UserFacade");

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Stats");

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/StatefulBeans");

            WhishlistFacade whishlistDB;
            whishlistDB = (WhishlistFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/WhishlistFacade");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //ModelUser.login(username, password);
            int userID;
            userID = userDB.login(username, password);

            if (userID > 0) {
                HttpSession session = request.getSession(true);

                //ModelUser.getUser(userID);
                User user = userDB.find(userID);
                session.setAttribute("user", user);

                List<Book> catalogo = bookDB.findAll();
               

                session.setAttribute("catalogo", catalogo);

                List<Book> biblioteca = libraryDB.getUserLibrary(userID);
                session.setAttribute("biblioteca", biblioteca);

                session.setAttribute("showWhishlist", false);

                List<Book> lista = whishlistDB.getUserWhishlist(userID);
               

                session.setAttribute("lista", lista);

                //@Statfull
                Cart carrito;
                carrito = (Cart) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Cart");
                session.setAttribute("carrito", carrito);

                //@Singleton STATS
                stats.addUser(user.toString());

                //@Singleton ALL STATEFUL BEAN
                allStatefulBean.addSession(user, password);

                forward("/mainPage.jsp");
            } else {
                forward("/index.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en " + MSG);
            System.out.println("check DB connection");
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
