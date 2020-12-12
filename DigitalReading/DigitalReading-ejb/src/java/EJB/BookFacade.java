/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class BookFacade {

    @PersistenceContext(unitName = "DigitalReading-ejbPU")
    private EntityManager em;

    public BookFacade() {
    }

    public void create(Book entity) {
        em.persist(entity);
        /*em.createQuery("INSERT INTO Book "
                + "(title, author, genre, sellingdate, price) "
                + "VALUES (:entity.title , :entity.author, :entity.genre, :entity.sellingdate, entity.price);")
                .setParameter("entity", entity)
                .executeUpdate();
         */
    }

    public void edit(Book entity) {
        em.merge(entity);
        return;
        /*em.createQuery("UPDATE Book b SET b.title = title, b.author = author, b.genre = genre, b.sellingdate = sellingdate, b.price = price WHERE b.id = :id")
                .setParameter("title", entity.getTitle())
                .setParameter("author", entity.getAuthor())
                .setParameter("genre", entity.getGenre())
                .setParameter("sellingdate", entity.getSellingdate())
                .setParameter("price", entity.getPrice())
                .setParameter("id", entity.getId())
                .executeUpdate();*/
    }

    public void remove(Book entity) {
        em.createQuery("DELETE FROM Book b WHERE b.id = :id")
                .setParameter("id", entity.getId())
                .executeUpdate();
    }

    public Book find(Object id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Book.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Book> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Book.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    //Select + LIKE with CRITERIA API
    public List<Book> searchBooksByTitle(String title) {
        //construimos el CriteriaBuilder cb
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //vamos a crear una sentencia
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        //Query Roots >> from table
        Root<Book> root = cq.from(Book.class);

        cq.where(cb.like(root.get("title"), title));

        CriteriaQuery<Book> select = ((CriteriaQuery<Book>) cq).select(root);
        TypedQuery<Book> tq = em.createQuery(select);
        return tq.getResultList();
    }

    public List<Book> searchBooksByAuthor(String filter) {
        return em.createQuery("SELECT b FROM Book b WHERE b.author LIKE :filter")
                .setParameter("filter", filter)
                .getResultList();
    }

    public List<Book> searchBooksByGenre(String filter) {
        return em.createQuery("SELECT b FROM Book b WHERE b.genre LIKE :filter")
                .setParameter("filter", filter)
                .getResultList();
    }

    public List<Book> searchBooksByGenreOrdened(String filter) {
        return em.createQuery("SELECT b FROM Book b WHERE b.genre LIKE :filter ORDER BY b.sellingdate")
                .setParameter("filter", filter)
                .getResultList();
    }

    public List<Book> searchBooksByAuthorOrdened(String filter) {
        return em.createQuery("SELECT b FROM Book b WHERE b.author LIKE :filter ORDER BY b.sellingdate")
                .setParameter("filter", filter)
                .getResultList();
    }

    //Select + LIKE with CRITERIA API
    public List<Book> searchBooksByTitleOrdened(String title) {
        //construimos el CriteriaBuilder cb
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //vamos a crear una sentencia
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        //Query Roots >> from table
        Root<Book> root = cq.from(Book.class);

        cq.where(cb.like(root.get("title"), title));

        CriteriaQuery<Book> select;

        //desc == descendente, asc == ascendente    
        select = ((CriteriaQuery<Book>) cq).select(root).orderBy(cb.desc(root.get("sellingdate")));
        TypedQuery<Book> tq = em.createQuery(select);

        return tq.getResultList();
    }

}
