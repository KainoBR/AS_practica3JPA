/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miki
 */
@Entity
@Table(name = "COMMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id")
    , @NamedQuery(name = "Comment.findByIduser", query = "SELECT c FROM Comment c WHERE c.iduser = :iduser")
    , @NamedQuery(name = "Comment.findByComment", query = "SELECT c FROM Comment c WHERE c.comment = :comment")
    , @NamedQuery(name = "Comment.findByUsername", query = "SELECT c FROM Comment c WHERE c.username = :username")

    , @NamedQuery(name = "Comment.findByIdbook", query = "SELECT c FROM Comment c WHERE c.idbook.id = :idbook")
    , @NamedQuery(name = "Comment.findByIdbookANDIduser", query = "SELECT c FROM Comment c WHERE c.iduser = :iduser AND c.idbook = :idbook")

    , @NamedQuery(name = "Comment.removeAllCommentsFromThisBook", query = "DELETE FROM Comment c WHERE c.idbook.id = :idbook")


})
//DELETE FROM SptTutorials s WHERE s.id = :id

public class Comment implements Serializable {

    @Override
    public String toString() {
        return comment; 
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUSER")
    private int iduser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "COMMENT")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "USERNAME")
    private String username;
    @JoinColumn(name = "IDBOOK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Book idbook;

    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Comment(Book book, int iduser, String comment, String username) {
        this.iduser = iduser;
        this.comment = comment;
        this.username = username;
        this.idbook = book;
    }
    
    public Comment(Integer id, Book book, int iduser, String comment, String username) {
        this.iduser = iduser;
        this.comment = comment;
        this.username = username;
        this.idbook = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Book getIdbook() {
        return idbook;
    }

    public void setIdbook(Book idbook) {
        this.idbook = idbook;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

 
}
