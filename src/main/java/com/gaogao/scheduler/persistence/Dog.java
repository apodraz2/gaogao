/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.persistence;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adampodraza
 */
@Entity

@NamedQueries({
    @NamedQuery(name=Dog.DOG_QUERY_ALL, 
        query="select d from Dog d"),
    
})

@XmlRootElement
public class Dog implements Serializable {
    public static final String DOG_QUERY_ALL = "Dog.findAll";
    public static final String OWNERS = "Dog.OWNERS";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    
    @OneToMany(cascade = CascadeType.ALL, 
                mappedBy = "dog", 
                fetch= FetchType.LAZY
    )
    private List<Event> eventList;
    
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "dog",
            fetch= FetchType.LAZY)
    private List<ServiceProvider> providerList;
    
    //@OneToMany(optional = true)
    @ManyToMany(mappedBy="dogList")
    private List<Owner> ownerList;
    
    public Dog() {
        id = System.currentTimeMillis();
    }

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the value of birthday
     *
     * @param birthday new value of birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setOwners(List<Owner> owners) {
        this.ownerList = owners;
    }
    
    public List<Owner> getOwners() {
        
        
        return this.ownerList;
    }

    @XmlTransient
    public List<ServiceProvider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<ServiceProvider> providerList) {
        this.providerList = providerList;
    }
    
    public void setEvents(List<Event> events) {
        this.eventList = events;
    }
    
    public List<Event> getEvents() {
        return this.eventList;
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
        if (!(object instanceof Dog)) {
            return false;
        }
        Dog other = (Dog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gaogao.scheduler.gaogaopractice.Dog[ id=" + id + " ]";
    }
    
}
