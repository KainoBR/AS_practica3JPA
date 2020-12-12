/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.BookFacade;
import EJB.WhishlistFacade;
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
public class showWhishlist extends FrontCommand {

    static String MSG = "#=== showAllBooks";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            WhishlistFacade whishlistDB;
            whishlistDB = (WhishlistFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/WhishlistFacade");

            HttpSession session = (HttpSession) request.getSession();

            User usuario = (User) session.getAttribute("user");

            int userID = usuario.getId();

            List<Book> lista = whishlistDB.getUserWhishlist(userID);

            session.setAttribute("lista", lista);
            session.setAttribute("showWhishlist", true);

            forward("/mainPage.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showBooks.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
