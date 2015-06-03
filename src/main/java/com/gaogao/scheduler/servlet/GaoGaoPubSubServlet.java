/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.servlet;

import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.messaging.OwnerRequest;
import com.gaogao.scheduler.persistence.Owner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author adampodraza
 */
public class GaoGaoPubSubServlet extends HttpServlet {

    @Resource(mappedName = "jms/GaoGaoTopic")
    private Topic topic;
    @Resource(mappedName = "jms/se554-pubsub")    
    private QueueConnectionFactory topicConnectionFactory;
    
    RequestDispatcher dispatcher;
    
    @EJB
    private OwnerBean ownerBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JAXBException, JMSException {
        
        String email = request.getParameter("email");
            
            String password = request.getParameter("password");
            String op = "CREATE_OWNER";
            
            OwnerRequest.OwnerOperation operation = null;
            
            for(OwnerRequest.OwnerOperation o : OwnerRequest.OwnerOperation.values()) {
                if(op.equals(o.toString())) {
                    operation = o;
                }
            }
            
            
            OwnerRequest or = new OwnerRequest(email, password, operation);
            
            StringWriter writer = new StringWriter();


            Marshaller m = JAXBContext.newInstance(OwnerRequest.class).createMarshaller();
            m.marshal(or, writer);
            String answer = "Don't know";
            
            try (JMSContext context = topicConnectionFactory.createContext()) {
                TemporaryQueue replyQueue = context.createTemporaryQueue();
                String msg = writer.toString();
                
                TextMessage requestMessage
                        = context.createTextMessage(msg);
                
                requestMessage.setJMSReplyTo(replyQueue);
                
                System.out.println("Request: " + msg);
                context.createProducer().send(
                        topic, requestMessage);

                JMSConsumer consumer = context.createConsumer(replyQueue);
                answer = consumer.receiveBody(String.class);
            }
            
            for(Owner o: ownerBean.getOwnerList()) {
                if(o.getEmail().equals(email)) {
                    request.setAttribute("owner", o);
                    break;
                }
            }
                
            dispatcher = getServletContext().getRequestDispatcher("/GaoGaoLoginServlet");
            dispatcher.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(GaoGaoPubSubServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(GaoGaoPubSubServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(GaoGaoPubSubServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(GaoGaoPubSubServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
