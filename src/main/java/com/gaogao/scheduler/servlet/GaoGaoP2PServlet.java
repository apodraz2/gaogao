package com.gaogao.scheduler.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.messaging.OwnerRequest;
import com.gaogao.scheduler.messaging.OwnerRequest.OwnerOperation;
import com.gaogao.scheduler.persistence.Owner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.QueueConnectionFactory;

import javax.annotation.Resource;
import javax.ejb.EJB;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
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
public class GaoGaoP2PServlet extends HttpServlet {

    @Resource(mappedName = "jms/GaoGaoQ")
    private Queue queue;
    @Resource(mappedName = "jms/se554-p2p")    
    private QueueConnectionFactory queueConnectionFactory;
    
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
     * @throws JAXBException
     * @throws JMSException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JAXBException, JMSException {
        
        
            
            String email = request.getParameter("email");
            
            String password = request.getParameter("password");
            String op = "CREATE_OWNER";
            
            OwnerOperation operation = null;
            
            for(OwnerOperation o : OwnerOperation.values()) {
                if(op.equals(o.toString())) {
                    operation = o;
                }
            }
            
            
            OwnerRequest or = new OwnerRequest(email, password, operation);
            
            StringWriter writer = new StringWriter();


            Marshaller m = JAXBContext.newInstance(OwnerRequest.class).createMarshaller();
            m.marshal(or, writer);
            String answer = "Don't know";
            
            try (JMSContext context = queueConnectionFactory.createContext()) {
                TemporaryQueue replyQueue = context.createTemporaryQueue();
                String msg = writer.toString();
                
                TextMessage requestMessage
                        = context.createTextMessage(msg);
                
                requestMessage.setJMSReplyTo(replyQueue);
                
                System.out.println("Request: " + msg);
                context.createProducer().send(
                        queue, requestMessage);

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
            Logger.getLogger(GaoGaoP2PServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(GaoGaoP2PServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GaoGaoP2PServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(GaoGaoP2PServlet.class.getName()).log(Level.SEVERE, null, ex);
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
