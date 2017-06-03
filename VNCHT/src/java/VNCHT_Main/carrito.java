/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; // Para el entorno de sesión 
import java.util.ArrayList;            // Para las listas 
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Lidia Montero y Jorge Martínez
 */
public class carrito extends HttpServlet {

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
        
        producto p = new producto(); 
        p.setId(Integer.parseInt(request.getParameter("id"))); 
        p.setNombre(request.getParameter("nombre")); 
        p.setPrecio(Float.parseFloat(request.getParameter("precio"))); 
        HttpSession sesion = request.getSession(true);                              // Accedemos al entorno de sesión         
        ArrayList <producto> carrito = (ArrayList)sesion.getAttribute("carrito");   // Carrito 
        if (carrito == null) 
        {
            carrito = new ArrayList<>(); 
            sesion.setAttribute("carrito", carrito); 
        }
        int i=0; 
        while (i<carrito.size() && carrito.get(i).getId()!=p.getId()) 
        {
            i++; 
        }
        if (i<carrito.size()) 
        {
            accesoBD bd = new accesoBD(); 
            int existencias = bd.existenciasProductoBD(p.getId()); // Existencias del producto 
            int actual = carrito.get(i).getExistencias(); 
            if (actual<existencias) 
            {
                carrito.get(i).setExistencias(actual+1);                
            }
            p.setExistencias(carrito.get(i).getExistencias()); 
        }
        else 
        {
            p.setExistencias(1); 
            carrito.add(p); 
        }
        request.setAttribute("existencias",p.getExistencias()); 
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrito.jsp"); 
        dispatcher.forward(request,response); 
    }

    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        processRequest(request, response); 
    }
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        processRequest(request, response); 
    }
    @Override 
    public String getServletInfo() { 
        return "Short description"; 
    }
}
