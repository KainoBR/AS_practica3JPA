/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Miki
 */
@Stateful
public class Cart {

    static String classMSG = "Cart";

    List<Book> cartBook;

    public Cart() {
        System.out.println(classMSG + "::constructor()");
        cartBook = new ArrayList<>();
    }

    public void clearCartBook() {
        System.out.println(classMSG + "::clearCartBook()");
        cartBook.clear();
    }

    public void addBookToCart(Book book) {
        System.out.println(classMSG + "::addBookToCart(" + book + ")");
        cartBook.add(book);
    }

    public boolean isEmpty() {
        System.out.println(classMSG + "::isEmpty()");
        return cartBook.isEmpty();
    }

    public List<Book> getCart() {
        System.out.println(classMSG + "::getCart() >> List<Book>");
        return cartBook;
    }

    public void removeBookFromCart(Book bookToRemove) {
        System.out.println(classMSG + "::removeBookFromCart(" + bookToRemove + ")");
        int i = 0;
        for (Book book : cartBook) {
            if (book.getId() == bookToRemove.getId()) {
                cartBook.remove(i);
                return;
            }
            i++;
        }
    }

    public boolean contains(Book o) {
        //System.out.println(classMSG + "::contains(" + o + ")");
        for (Book book : cartBook) {
            if (book.getId() == o.getId()) {
                return true;
            }
        }
        return false;
    }

    public double getTotalPrice() {
       // System.out.println(classMSG + "::getTotalPrice()");
        double result = 0.0;
        for (Book book : cartBook) {
            result += book.getPrice();
        }
        return result;
    }
    
    

    public String cartToString() {
        System.out.println(classMSG + "::toString()");
        String res = " ";
        for (Book book : cartBook) {
            res += book + "\n";
        }
        return res.trim();
    }

    @Remove
    public void remove() {
        System.out.println(classMSG + "::remove() - @Remove ");
    }

    @PostConstruct
    public void init() {
        System.out.println(classMSG + "::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println(classMSG + "::kill() - @PreDestroy ");
    }

    @PostActivate
    public void postActivate() {
        System.out.println(classMSG + "::postActivate() - @PostActivate ");
    }

    @PrePassivate
    public void prePassivate() {
        System.out.println(classMSG + "::prePassivate() - @PrePassivate ");
    }

}
