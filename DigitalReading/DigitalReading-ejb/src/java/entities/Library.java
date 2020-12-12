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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miki
 */
@Entity
@Table(name = "LIBRARIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Library.findAll", query = "SELECT l FROM Library l")
    , @NamedQuery(name = "Library.findById", query = "SELECT l FROM Library l WHERE l.id = :id")

    , @NamedQuery(name = "Library.findByIduser", query = "SELECT l FROM Library l WHERE l.iduser.id = :id")
    , @NamedQuery(name = "Library.findThisBookInThisUserLibrary", query = "SELECT l FROM Library l WHERE l.idbook.id = :book AND l.iduser.id = :user")
    , @NamedQuery(name = "Library.getIdLibraryByUserANDBook", query = "SELECT l.id FROM Library l WHERE l.idbook.id = :book AND l.iduser.id = :user")
        
    , @NamedQuery(name = "Library.removeAllBooksFromThisLibrary", query = "DELETE FROM Library l WHERE l.idbook.id = :idbook")
        
})
public class Library implements Serializable {

    @Override
    public String toString() {
        return "Library{" + "idbook=" + idbook + ", iduser=" + iduser + '}';
    }

    public Library(User iduser, Book idbook) {
        this.idbook = idbook;
        this.iduser = iduser;
    }
    
     

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "IDBOOK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Book idbook;
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User iduser;

    public Library() {
    }

    public Library(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getIdbook() {
        return idbook;
    }

    public void setIdbook(Book idbook) {
        this.idbook = idbook;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
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
        if (!(object instanceof Library)) {
            return false;
        }
        Library other = (Library) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
