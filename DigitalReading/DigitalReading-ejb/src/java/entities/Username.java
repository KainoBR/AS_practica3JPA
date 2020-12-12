/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Miki
 */
@Embeddable
/*@AttributeOverrides({
	  @AttributeOverride( name = "firstName", column = @Column(name = "contact_first_name")),
	  @AttributeOverride( name = "lastName", column = @Column(name = "contact_last_name"))})*/
public class Username implements Serializable {

    public Username() {
    }

    @Override
    public String toString() {
        return "firstname " + firstname + ", lastname=" + lastname + '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Username(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "LASTNAME")
    private String lastname;

}
