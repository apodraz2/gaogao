package com.gaogao.scheduler.messaging;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adampodraza
 */
@XmlRootElement(name="OwnerRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerRequest implements Serializable{
    
    public static enum OwnerOperation { CREATE_OWNER, REMOVE_OWNER};
    
    @XmlElement(name="email")
    private String email;
    
    @XmlElement(name="password")
    private String password;
    
    @XmlElement(name="operation")
    private OwnerOperation operation;
    
    public OwnerRequest() {}
    
    public OwnerRequest(String email, String password, OwnerOperation operation) {
        this.email = email;
        this.password = password;
        this.operation = operation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OwnerOperation getOperation() {
        return operation;
    }

    public void setOperation(OwnerOperation operation) {
        this.operation = operation;
    }
    
    
    
}
