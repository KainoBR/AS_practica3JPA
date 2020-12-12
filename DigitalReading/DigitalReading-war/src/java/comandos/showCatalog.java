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
public class showCatalog extends FrontCommand {

    static String MSG = "#=== showCatalog";

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

            int value;
            if (session.getAttribute("value") == null) {
                value = 0;

            } else {
                value = (int) session.getAttribute("value");
            }

            String accion = request.getParameter("accion");
            if (null == accion) { //prevencion
                value = Integer.parseInt(request.getParameter("value"));
            } else {
                switch (accion) {
                    case "next":
                        value++;
                        break;
                    case "prev":
                        value--;
                        break;
                    default:
                        value = Integer.parseInt(request.getParameter("value"));
                        break;
                }
            }
            int[] rango = {value, value};

            List<Book> revista = bookDB.findRange(rango);

            session.setAttribute("revista", revista);

            session.setAttribute("value", value);

            forward("/showCatalog.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showCatalog.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
