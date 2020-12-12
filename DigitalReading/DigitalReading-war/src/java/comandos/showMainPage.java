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
//restore the main page
public class showMainPage extends FrontCommand {

    static String MSG = "#=== showMainPage";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            HttpSession session = request.getSession();

            List<Book> catalogo = bookDB.findAll();

            session.setAttribute("catalogo", catalogo);
            session.setAttribute("showWhishlist", false);

            forward("/mainPage.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showBooks.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

    
}
