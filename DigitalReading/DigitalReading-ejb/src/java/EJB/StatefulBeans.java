/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Startup;

/**
 *
 * @author Miki
 */
@Startup
@Singleton
@LocalBean
public class StatefulBeans {

    static String classMSG = "StatefulBeans";

    List<String> users;
    HashMap<Integer, String> carritos;

    public StatefulBeans() {
        System.out.println(classMSG + "::constructor()");
        users = new ArrayList<>();
        carritos = new HashMap<>();
    }

    @Lock(LockType.READ) //permit multiple access
    public List<String> getAllUsers() {
        System.out.println(classMSG + "::getAllUsers()");
        return users;
    }

    public void addSession(User user, String carrito) {
        System.out.println(classMSG + "::addSession(" + user.toString() + "," + carrito + ")");
        users.add(user.toString());
        carritos.put(user.getId(), carrito);
    }

    public void removeSession(User user) {
        System.out.println(classMSG + "::removeSession(+" + user.toString() + ")");
        users.remove(user.toString());
        carritos.remove(user.getId());
    }

    @Lock(LockType.READ) //permit multiple access
    public HashMap<Integer, String> getAllCarts() {
        System.out.println(classMSG + "::getAllCarts()");
        return carritos;
    }

    public void addCart(int userID, String carrito) {
        System.out.println(classMSG + "::addCart(" + userID + "," + carrito + ")");
        carritos.put(userID, carrito);
    }

    public void updateCart(int userID, String carrito) {
        System.out.println(classMSG + "::updateCart(" + userID + "," + carrito + ")");
        carritos.replace(userID, carrito);
    }

    public void updateUser(String oldUser, String newUser) {
        System.out.println(classMSG + "::updateUser(" + oldUser + "," + newUser + ")");
        users.remove(oldUser);
        users.add(newUser);
    }

    @PostConstruct
    public void init() {
        System.out.println("StatefulBeans::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println("StatefulBeans::kill() - @PreDestroy ");
    }

}
