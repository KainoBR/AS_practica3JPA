/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.CommentFacade;
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
public class showProfile extends FrontCommand {

    static String MSG = "#=== showProfile";

    @Override
    public void process() {

        System.out.println(MSG);

        try {
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            LibraryFacade libraryDB;
            libraryDB = (LibraryFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/LibraryFacade");

            CommentFacade commentDB;
            commentDB = (CommentFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/CommentFacade");

            HttpSession session = (HttpSession) request.getSession();

            User user = (User) session.getAttribute("user");
            int id = user.getId();

            int numComments = commentDB.getAllCommentsOfThisUser(id).size();

            int likes = 0;
            //ModelBiblioteca.getBookShelf(id)

            int numBooks = libraryDB.getUserLibrary(id).size();

            session.setAttribute("numComments", numComments);
            session.setAttribute("numBooks", numBooks);
            session.setAttribute("numLikes", likes);

            forward("/showMyProfile.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(showProfile.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
