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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miki
 */
@Entity
@Table(name = "LIKES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Love.findAll", query = "SELECT l FROM Love l")
    , @NamedQuery(name = "Love.findById", query = "SELECT l FROM Love l WHERE l.id = :id")
    , @NamedQuery(name = "Love.findByIduser", query = "SELECT l FROM Love l WHERE l.iduser = :iduser")})
public class Love implements Serializable {

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
    @JoinColumn(name = "IDBOOK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Book idbook;

    public Love() {
    }

    public Love(Integer id) {
        this.id = id;
    }

    public Love(Integer id, int iduser) {
        this.id = id;
        this.iduser = iduser;
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
        if (!(object instanceof Love)) {
            return false;
        }
        Love other = (Love) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Love[ id=" + id + " ]";
    }
    
}
