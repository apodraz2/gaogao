/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.gaogaopractice;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author adampodraza
 */
@Entity
public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String email;
    
    private String password;
    
    /**@OneToMany(cascade = CascadeType.ALL, 
                mappedBy = "ownerId", 
                fetch= FetchType.LAZY
    )**/
    
    @ManyToMany
    @JoinTable(name = "OWNER_DOG")
    private List<Dog> dogList;
    
    Owner() {}
    
    Owner(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public List<Dog> getDogList() {
        return this.dogList;
    }
    
    public void setDogList(List<Dog> dogs) {
        this.dogList = dogs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gaogao.scheduler.gaogaopractice.User[ id=" + email + " ]";
    }
    
}
