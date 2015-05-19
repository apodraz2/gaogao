package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gaogao.scheduler.gaogaopractice.Dog;
import com.gaogao.scheduler.gaogaopractice.DogBean;
import com.gaogao.scheduler.gaogaopractice.Event;
import com.gaogao.scheduler.gaogaopractice.Owner;
import com.gaogao.scheduler.gaogaopractice.OwnerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adampodraza
 */
public class GaoGaoServlet extends HttpServlet {

    @EJB
    private DogBean dogBean;
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GaoGaoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GaoGaoServlet at " + request.getContextPath() + "</h1>");
            
            //Create a new owner
            Owner o = ownerBean.createOwner("wususa@gmail.com", "Sue22sue");
            
            //Add a new dog to the new owner
            ownerBean.addNewDog(o, "Denver", "04/09/1986");
            
            //Add a new event to the dog
            ownerBean.addEvent(o, "Walk Denver", "11/05/2015", "Denver");
            
            List <Event> events = ownerBean.getDogList(o).get(0).getEvents();
            
            System.out.println("I need to " + events.get(0).getDescription() + " on " + events.get(0).getDate());
            
            out.println("</body>");
            out.println("</html>");
        }
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
