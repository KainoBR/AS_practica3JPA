/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Miki
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "DigitalReading-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public int login(String username, String password) throws javax.persistence.NoResultException {

        try {
            return (int) em.createNamedQuery("User.loginDB")
                    .setParameter("firstname", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return 0;
        }

    }

    public boolean isExists(String name, String lastname) throws javax.persistence.NoResultException {
        return em.createNamedQuery("User.isExistDB")
                .setParameter("firstname", name)
                .setParameter("lastname", lastname)
                .getFirstResult() != 0;
    }

    //public void changeAccount(int userID, double account) {
    //"SELECT u FROM User u WHERE u.firstname =: firstname AND u.lastname = :lastname")
/*        em.createQuery("UPDATE User u SET u.account= :account WHERE u.id= :id")
                .setParameter("id", userID)
                .setParameter("account", account);
        
     */
    //}
    public void updateAdmin(int userid) {
        em.createQuery("UPDATE User u SET u.admin = 1 WHERE u.id = :id")
                .setParameter("id", userid)
                .executeUpdate();
    }
}
