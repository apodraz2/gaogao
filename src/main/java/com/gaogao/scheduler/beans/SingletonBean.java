/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author adampodraza
 */
@Singleton
@Startup
public class SingletonBean {
    
    int numDogs;
    
    @PostConstruct
    private void startup() {
        System.out.println("Startup bean initiated");
    }
    
    public void add(int i) {
        numDogs += i;
    }
    
    public int getNumDogs() {
        return this.numDogs;
    }
    
}
