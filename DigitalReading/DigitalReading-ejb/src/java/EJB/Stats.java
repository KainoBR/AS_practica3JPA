/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
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
public class Stats {
    //@Lock(LockType.WRITE) //default

    static String classMSG = "Stats";

    //usuarios logeados
    //.size() total de usuarios logeados
    List<String> usersLogged;

    // número de libros comprados 
    int Bookspurchased;
    // número de libros devueltos
    int BookReturned;

    int CartsCompleted;

    public Stats() {
        this.usersLogged = new ArrayList<>();

        CartsCompleted = 0;
        Bookspurchased = 0;
        BookReturned = 0;
        System.out.println(classMSG + "::constructor()");
    }

    public void removeUser(String user) {
        System.out.println(classMSG + "::removeUser( " + user + ")");
        usersLogged.remove(user);
    }

    @Lock(LockType.READ)
    public int getPurchasedBook() {
        System.out.println(classMSG + "::getPurchasedBook()");
        return Bookspurchased;
    }

    @Lock(LockType.READ)
    public int getReturnedBook() {
        System.out.println(classMSG + "::getReturnedBook()");
        return BookReturned;
    }

    @Lock(LockType.READ)
    public int getCartsCompleted() {
        System.out.println(classMSG + "::getCartsCompleted()");
        return CartsCompleted;
    }

    @Lock(LockType.READ)
    public List<String> getUsersLogged() {
        System.out.println(classMSG + "::getUsersLogged()");
        return usersLogged;
    }

    public void addUser(String user) {
        System.out.println(classMSG + "::addUser(" + user + ")");
        usersLogged.add(user);
    }

    public void buyABook() {
        System.out.println(classMSG + "::buyABook()");
        Bookspurchased++;
    }

    public void completedACart() {
        System.out.println(classMSG + "::completedACart()");
        CartsCompleted++;
    }

    public void returnABook() {
        System.out.println(classMSG + "::returnABook()");
        BookReturned++;
    }

    
    
    @PostConstruct
    public void init() {
        System.out.println("Stats::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println("Stats::kill() - @PreDestroy ");
    }
}
