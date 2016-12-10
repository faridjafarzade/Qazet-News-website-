/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Farid
 */
@Entity
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue
    private int id;
    @Column(unique=true)
    private String name;
   
    private String password;
    @Column(unique=true)
    private String email;
   
    private boolean editor;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the editor
     */
    public boolean isEditor() {
        return editor;
    }

    /**
     * @param editor the editor to set
     */
    public void setEditor(boolean editor) {
        this.editor = editor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Admin other = (Admin) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojoes.Link[ id=" + id + " ]";
    }
    
    
}
