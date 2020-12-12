/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.CommentFacade;
import entities.Book;
import EJB.Log;
import entities.User;
import entities.Comment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class insertComment extends FrontCommand {

    static String MSG = "#=== insertComment";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            CommentFacade commentDB;
            commentDB = (CommentFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/CommentFacade");

            HttpSession session = request.getSession();

            String comment = request.getParameter("comment");
            User user = (User) session.getAttribute("user");

            Book book = (Book) session.getAttribute("libro");

            
            Comment newComment = new Comment(book, user.getId(), comment, user.getUsername().getFirstname());
            
            //ModelComment.insertComment(newComment);
            commentDB.create(newComment);

            //ModelComment.getCommentsFromThisBook(book.getId()
            book.setComments(commentDB.getAllCommentsOfThisBook(book.getId()));

            session.setAttribute("libro", book);
            forward("/showBookDetails.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(insertBook.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
