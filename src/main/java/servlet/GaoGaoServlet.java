package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gaogao.scheduler.gaogaopractice.Dog;
import com.gaogao.scheduler.gaogaopractice.DogBean;
import com.gaogao.scheduler.gaogaopractice.Owner;
import com.gaogao.scheduler.gaogaopractice.OwnerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
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
            dogBean.createDog("Denver", "04/09/1986");
            out.println("Created dog");
            ownerBean.createOwner("apodra86@gmail.com", "Sue22sue");
            out.println("Created owner");
            List<Owner> owners = ownerBean.getOwnerList();
            if(owners != null){
                out.println();
            } else {
                out.println(false);
            }
            List<Dog> dogs = dogBean.getDogList();
            if(dogs != null) out.println(true);
            
            for (Dog dog : dogs) {
                out.println("<p>");
                dogBean.addOwner(owners.get(0), dog);
                ownerBean.addDog(dog, owners.get(0));
                out.println("Dog exists with info: " );
                out.println(dog.toString());
                out.println("</p>");
                List<Owner> dogOwners = dogBean.getOwnerList(dog);
                for (Owner o : dogOwners) {
                    out.println("<p>");
                    out.println("Dog owner exists with info: ");
                    out.println(o.toString());
                    out.println("<p>");
                            
                }
                
                
            }
            
            
            for(Owner owner : owners) {
                out.println("<p>");
                out.println("Owner exists with info: ");
                out.println(owner.toString());
                out.println("</p>");
                
            }
            
            
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
