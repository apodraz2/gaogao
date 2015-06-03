/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adampodraza
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String email;
    
    private String password;
    
    @ManyToMany
    @JoinTable(name = "OWNER_DOG")
    private List<Dog> dogList;
    
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "owner",
            fetch= FetchType.LAZY)
    private List<ServiceProvider> providerList;
    
    public Owner() {}
    
    public Owner(String email, String password) {
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
    
    @XmlTransient
    public List<Dog> getDogList() {
        return this.dogList;
    }
    
    public void setDogList(List<Dog> dogs) {
        this.dogList = dogs;
    }
    
    @XmlTransient
    public List<ServiceProvider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<ServiceProvider> providerList) {
        this.providerList = providerList;
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
