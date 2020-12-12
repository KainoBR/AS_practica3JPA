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
import javax.ejb.EJB;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class getBook extends FrontCommand {

    static String MSG = "#=== getBook";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            int bookID = Integer.parseInt(request.getParameter("bookID"));

            Book uploadBook = bookDB.find(bookID);

            if (uploadBook != null) {
                //atributo especial para actualizar! (muestra los campos en el formulario
                request.setAttribute("bookToUpload", uploadBook);
                forward("/updateBook.jsp");
            } else {
                forward("/showAllBooks.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en " + MSG);
            Logger.getLogger(getBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
