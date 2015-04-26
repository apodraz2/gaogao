/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.gaogaopractice;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author adampodraza
 */
@Entity

@NamedQueries({
    @NamedQuery(name=Dog.DOG_QUERY_ALL, 
        query="select d from Dog d")
})

public class Dog implements Serializable {
    public static final String DOG_QUERY_ALL = "Dog.findAll";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    
    @OneToMany(cascade = CascadeType.ALL, 
                mappedBy = "dogId", 
                fetch= FetchType.LAZY
    )
    private List<Event> eventList;
    
    //@OneToMany(optional = true)
    @ManyToMany(mappedBy="dogList")
    private List<Owner> ownerList;
    
    Dog() {
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
