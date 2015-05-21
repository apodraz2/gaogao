/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.beans;

import com.gaogao.scheduler.messaging.OwnerRequest;
import java.io.StringReader;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author adampodraza
 */
@MessageDriven(mappedName = "jms/GaoGaoQ",
activationConfig = {
    @ActivationConfigProperty(
     propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class GaoGaoMDB implements MessageListener{
    
    //@Resource(mappedName = "jms/ConnectionFactory")    
    //private QueueConnectionFactory queueConnectionFactory;

    @Override
    public void onMessage(Message message) {
        //To change body of generated methods, choose Tools | Templates.
        
        OwnerRequest or = null;
        
        try {
            or = convert(((TextMessage)message).getText());
            System.out.println(or.toString());
            
        } catch (JMSException x) {
            
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private OwnerRequest convert (String xml) {
        OwnerRequest or = null;
        
        try {
            JAXBContext context = JAXBContext.newInstance(OwnerRequest.class);
            Unmarshaller m = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                or = (OwnerRequest) m.unmarshal(reader);
                
            }
        }catch (JAXBException ex) {
            
        }
        return or;
    }
}
