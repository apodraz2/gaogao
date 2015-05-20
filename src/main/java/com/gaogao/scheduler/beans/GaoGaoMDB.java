/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author adampodraza
 */
@MessageDriven(mappedName = "jms/GaoGaoQ",
activationConfig = {
    @ActivationConfigProperty(
     propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class GaoGaoMDB implements MessageListener{

    @Override
    public void onMessage(Message message) {
        System.out.println(message); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
