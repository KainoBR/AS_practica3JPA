package entities;

import entities.Comment;
import entities.Library;
import entities.Love;
import entities.Whishlist;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-19T09:48:32")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile ListAttribute<Book, Comment> comments;
    public static volatile ListAttribute<Book, Library> library;
    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile ListAttribute<Book, Love> loves;
    public static volatile SingularAttribute<Book, String> genre;
    public static volatile ListAttribute<Book, Whishlist> whishlistCollection;
    public static volatile SingularAttribute<Book, String> coverpage;
    public static volatile SingularAttribute<Book, Integer> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Date> sellingdate;
    public static volatile SingularAttribute<Book, Integer> likes;

}