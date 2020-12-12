/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.LibraryFacade;
import EJB.Log;
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
public class showBookShelf extends FrontCommand {

    static String MSG = "#=== showBookShlef";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");

            LibraryFacade libraryDB;
            libraryDB = (LibraryFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/LibraryFacade");

            log.addLogs(MSG);

            HttpSession session = (HttpSession) request.getSession();

            User usuario = (User) session.getAttribute("user");

            int userID = usuario.getId();

            session.setAttribute("biblioteca", libraryDB.getUserLibrary(userID));

            forward("/showMyBookShelf.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showBookShelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
