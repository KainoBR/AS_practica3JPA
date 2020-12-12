/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Book;
import entities.User;
import entities.Whishlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Miki
 */
@Stateless
public class WhishlistFacade extends AbstractFacade<Whishlist> {

    @PersistenceContext(unitName = "DigitalReading-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WhishlistFacade() {
        super(Whishlist.class);
    }

    public List<Book> getUserWhishlist(int userID) {
        return em.createNamedQuery("Whishlist.findByIduser")
                .setParameter("iduser", userID)
                .getResultList();
    }

    public Whishlist getWhishlistByUserANDBook(int userID, int bookID) {
        return (Whishlist) em.createNamedQuery("Whishlist.findByUserANDbook")
                .setParameter("iduser", userID)
                .setParameter("idbook", bookID)
                .getSingleResult();
    }

    public void removeThisBookFromAllWhishlist(int bookID) {
        em.createQuery("DELETE FROM Whishlist l WHERE l.idbook.id = :idbook")
                .setParameter("idbook", bookID)
                .executeUpdate();

    }

    

}
