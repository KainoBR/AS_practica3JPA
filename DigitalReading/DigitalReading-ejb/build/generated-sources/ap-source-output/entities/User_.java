package entities;

import entities.Library;
import entities.Username;
import entities.Whishlist;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-19T09:48:32")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Whishlist> whishlistCollection;
    public static volatile CollectionAttribute<User, Library> libraryCollection;
    public static volatile SingularAttribute<User, Integer> admin;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Double> account;
    public static volatile SingularAttribute<User, Username> username;

}