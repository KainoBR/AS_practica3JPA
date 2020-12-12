/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Book;
import entities.Library;
import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Miki
 */
@Stateless
public class LibraryFacade extends AbstractFacade<Library> {

    @PersistenceContext(unitName = "DigitalReading-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibraryFacade() {
        super(Library.class);
    }

    public List getUserLibrary(int userID) {
        return em.createNamedQuery("Library.findByIduser")
                .setParameter("id", userID)
                .getResultList();
    }

    public boolean isAlreadyContains(User user, Book book) {
        try {
            em.createNamedQuery("Library.findThisBookInThisUserLibrary")
                    .setParameter("book", book.getId())
                    .setParameter("user", user.getId())
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Integer getLibraryIDbyUserANDBook(User user, Book book) {
        return (Integer) em.createNamedQuery("Library.getIdLibraryByUserANDBook")
                .setParameter("book", book.getId())
                .setParameter("user", user.getId())
                .getSingleResult();
    }

    public void removeThisBookFromAllLibrary(int bookID) {
        em.createNamedQuery("Library.removeAllBooksFromThisLibrary")
                .setParameter("idbook", bookID)
                .executeUpdate();

    }

}
