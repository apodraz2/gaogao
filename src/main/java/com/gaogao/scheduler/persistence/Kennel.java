/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adampodraza
 */
@Entity
@XmlRootElement
public class Kennel extends ServiceProvider {
    
    private String checkList;

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }
    
    
}
