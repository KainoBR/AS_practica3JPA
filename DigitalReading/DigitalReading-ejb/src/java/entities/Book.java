/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miki
 */
@Entity
@Table(name = "BOOKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
    , @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id")
    , @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
    , @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author")
    , @NamedQuery(name = "Book.findByGenre", query = "SELECT b FROM Book b WHERE b.genre = :genre")
    , @NamedQuery(name = "Book.findBySellingdate", query = "SELECT b FROM Book b WHERE b.sellingdate = :sellingdate")
    , @NamedQuery(name = "Book.findByPrice", query = "SELECT b FROM Book b WHERE b.price = :price")
    , @NamedQuery(name = "Book.findByLikes", query = "SELECT b FROM Book b WHERE b.likes = :likes")
    , @NamedQuery(name = "Book.findByCoverpage", query = "SELECT b FROM Book b WHERE b.coverpage = :coverpage")
})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "AUTHOR")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "SELLINGDATE")
    @Temporal(TemporalType.DATE)
    private Date sellingdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "LIKES")
    private Integer likes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "COVERPAGE")
    private String coverpage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbook")
    private List<Library> library;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbook")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbook")
    private List<Love> loves;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbook")
    private List<Whishlist> whishlistCollection;

    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(String title, String author, String genre, Date sellingDate, double price, int likes, String coverpage) {

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.sellingdate = sellingDate;
        this.price = price;
        this.likes = likes;
        this.coverpage = coverpage;
    }

    public Book(Integer id, String title, String author, String genre, Date sellingDate, double price, int likes, String coverpage) {
        this.id = id;

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.sellingdate = sellingDate;
        this.price = price;
        this.likes = likes;
        this.coverpage = coverpage;
    }

    @Override
    public String toString() {
        return title + "\nAuthor: " + author + "\nPrice: " + price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getSellingdate() {
        return sellingdate;
    }

    public void setSellingdate(Date sellingdate) {
        this.sellingdate = sellingdate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getCoverpage() {
        return coverpage;
    }

    public void setCoverpage(String coverpage) {
        this.coverpage = coverpage;
    }

    @XmlTransient
    public Collection<Library> getLibraryCollection() {
        return library;
    }

    public void setLibrary(List<Library> libraryCollection) {
        this.library = libraryCollection;
    }

    @XmlTransient
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> commentCollection) {
        this.comments = commentCollection;
    }

    @XmlTransient
    public List<Love> getLoves() {
        return loves;
    }

    public void setLoves(List<Love> loveCollection) {
        this.loves = loveCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @XmlTransient
    public List<Whishlist> getWhishlist() {
        return whishlistCollection;
    }

    public void setWhishlist(List<Whishlist> whishlistCollection) {
        this.whishlistCollection = whishlistCollection;
    }

}
