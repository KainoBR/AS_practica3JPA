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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class insertBook extends FrontCommand {

    static String MSG = "#=== insertBook";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            BookFacade bookDB;
            bookDB = (BookFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/BookFacade");

            List<Book> catalogo = bookDB.findAll();

            Book newBook = insertBookForm();
            System.out.println("\n\n -----------------------------------------------------");
            System.out.println(newBook);
            System.out.println("\n\n -----------------------------------------------------");
            
            for (Book book : catalogo) {
                if (newBook.equals(book)) {
                    forward("/showCatalog.jsp");
                }
            }
            
            bookDB.create(newBook);

            HttpSession session = request.getSession();
            session.setAttribute("catalogo", bookDB.findAll());

            forward("/showBooks.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(insertBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

    /**
     * lee el formulario y crea un nuevo Book
     *
     * @return
     */
    private Book insertBookForm() {
        // --- leer el formulario de insertBook --- //
        String title = request.getParameter("title");

        String author = request.getParameter("author");

        String genre = request.getParameter("genre");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sellingDate = null;
        try {
            sellingDate = dateFormat.parse(request.getParameter("sellingDate"));
        } catch (Exception e) {
            System.out.print("error con el formato de fecha");
            forward("/commandErrorPage.jsp");
        }

        double price = Double.parseDouble(request.getParameter("price"));

        // --- Creamos un nuevo objeto Book --- //
        return new Book(title, author, genre, sellingDate, price, 0, " ");
    }

}
