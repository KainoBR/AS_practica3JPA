/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Book;
import entities.Comment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Miki
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> {

    @PersistenceContext(unitName = "DigitalReading-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }

    //ModelComment.getCommentFromThisUserOfThisBook(user.getId(), book.getId())
    public List<Comment> getCommentFromThisUserOfThisBook(int userID, Book bookID) {
        return em.createNamedQuery("Comment.findByIdbookANDIduser")
                .setParameter("iduser", userID)
                .setParameter("idbook", bookID)
                .getResultList();

    }

    public List<Comment> getAllCommentsOfThisUser(int userID) {
        return em.createNamedQuery("Comment.findByIduser")
                .setParameter("iduser", userID)
                .getResultList();
    }

    public List<Comment> getAllCommentsOfThisBook(int bookID) {
        return em.createNamedQuery("Comment.findByIdbook")
                .setParameter("idbook", bookID)
                .getResultList();
    }

    public void removeAllCommentsFromThisBook(int bookID) {
        em.createNamedQuery("Comment.removeAllCommentsFromThisBook")
                .setParameter("idbook", bookID)
                .executeUpdate();
    }

}
