package entities;

import entities.Book;
import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-19T09:48:32")
@StaticMetamodel(Whishlist.class)
public class Whishlist_ { 

    public static volatile SingularAttribute<Whishlist, User> iduser;
    public static volatile SingularAttribute<Whishlist, Book> idbook;
    public static volatile SingularAttribute<Whishlist, Integer> id;

}