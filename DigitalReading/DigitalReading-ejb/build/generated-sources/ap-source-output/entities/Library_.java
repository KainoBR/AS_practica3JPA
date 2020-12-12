package entities;

import entities.Book;
import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-19T09:48:32")
@StaticMetamodel(Library.class)
public class Library_ { 

    public static volatile SingularAttribute<Library, User> iduser;
    public static volatile SingularAttribute<Library, Book> idbook;
    public static volatile SingularAttribute<Library, Integer> id;

}