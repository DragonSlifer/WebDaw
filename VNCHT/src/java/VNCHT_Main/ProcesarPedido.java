/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge
 */
@WebServlet(name = "ProcesarPedido", urlPatterns = {"/ProcesarPedido"})
public class ProcesarPedido extends HttpServlet {

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
        HttpSession sesion = request.getSession();

        ArrayList<producto> carrito = (ArrayList<producto>) sesion.getAttribute("carrito");
        String nombre = request.getParameter("input_nombre");
        String direccion = request.getParameter("input_direcc");
        String dni = request.getParameter("input_dni");
        String tarjeta = request.getParameter("input_tarjeta");
        
        boolean valido = true;

        if (carrito == null) {
            sesion.setAttribute("Mensaje", "El carrito de la compra esta vacio");
            response.sendRedirect("tienda.jsp");
        } else {
            if (!dni.matches("[0-9]{8}[a-zA-Z]")) {                             ///< Comprobamos que el DNI introducido es válido
                valido = false;
            }

            if (!valido) {
                sesion.setAttribute("Mensaje", "DNI no valido");
                response.sendRedirect("tienda.jsp");
            } else {
                Pedido pedido = new Pedido();

                pedido.setNombre(nombre);
                pedido.setDireccion(direccion);
                pedido.setDni(dni);
                pedido.setTarjeta(tarjeta);
                sesion.setAttribute("pedido", pedido);
                
                response.sendRedirect("Resguardo.jsp");
            }
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
