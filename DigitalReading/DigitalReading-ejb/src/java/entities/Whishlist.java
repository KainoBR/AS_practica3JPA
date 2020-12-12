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
@Table(name = "WHISHLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Whishlist.findAll", query = "SELECT w FROM Whishlist w")
    , @NamedQuery(name = "Whishlist.findById", query = "SELECT w FROM Whishlist w WHERE w.id = :id")

    , @NamedQuery(name = "Whishlist.findByIduser", query = "SELECT w.idbook FROM Whishlist w WHERE w.iduser.id = :iduser")
    , @NamedQuery(name = "Whishlist.findByUserANDbook", query = "SELECT w FROM Whishlist w WHERE w.iduser.id = :iduser AND w.idbook.id = :idbook")})

public class Whishlist implements Serializable {

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

    public Whishlist() {
    }

    public Whishlist(User user) {
        this.iduser = user;
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
        if (!(object instanceof Whishlist)) {
            return false;
        }
        Whishlist other = (Whishlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Whishlist[ id=" + id + " ]";
    }

}
