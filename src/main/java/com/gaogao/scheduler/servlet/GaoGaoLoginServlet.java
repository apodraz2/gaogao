/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.servlet;

import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.persistence.Owner;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adampodraza
 */
public class GaoGaoLoginServlet extends HttpServlet {
    
    @EJB
    OwnerBean ownerBean;
    
    RequestDispatcher dispatcher;

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
            throws ServletException, IOException {
        Owner o;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        for(int i = 0; i < ownerBean.getOwnerList().size(); i++) {
            if(ownerBean.getOwnerList().get(i).getEmail().equals(email) && ownerBean.getOwnerList().get(i).getPassword().equals(password)) {
                
                o = ownerBean.getOwnerList().get(i);
                request.setAttribute("owner", o);
                dispatcher = getServletContext().getRequestDispatcher("/dashboard.jsp");
                dispatcher.forward(request, response);
            }
        }
        
        //dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
        //dispatcher.forward(request, response);
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
