/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge
 */
public class tramitacion extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession sesion = request.getSession();
        ArrayList<producto> carrito = (ArrayList<producto>)sesion.getAttribute("carrito");
        accesoBD baseDatos = accesoBD.getInstance();
        
        Pedido pedido = (Pedido)sesion.getAttribute("pedido");
        String usuario = (String)sesion.getAttribute("usuario");
        String nombre = pedido.getNombre();
        String direccion = pedido.getDireccion();
        String tarjeta = pedido.getTarjeta();
        
        if(baseDatos.actualizarAlmacen(carrito))
        {
            if(baseDatos.anyadirPedido(nombre, tarjeta, direccion, carrito, usuario))
            {  
                carrito.removeAll(carrito);
                sesion.setAttribute("carrito", carrito);
                sesion.setAttribute("Mensaje", "La compra se ha llevado a cabo correctamente");
                
            }
            else
            {
                sesion.setAttribute("Mensaje", "Error en la tramitacion del pedido");  
            } 
        }
        else
        {
            sesion.setAttribute("Mensaje", "Error en la tramitacion del pedido");
        }
        
        response.sendRedirect("tienda.jsp");
        
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
