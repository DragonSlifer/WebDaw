/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

import java.io.IOException;
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
public class AgregarElemento extends HttpServlet {

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
        HttpSession sesion = request.getSession(true);
        ArrayList<producto> carrito = (ArrayList<producto>) sesion.getAttribute("carrito");
        accesoBD baseDatos = accesoBD.getInstance(); //Se obtiene acceso a la BD

        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int referencia = Integer.parseInt(request.getParameter("referencia"));
        float precio;
        boolean encontrado = false;
        int i = 0;

        while (!encontrado && i < carrito.size()) {
            if (carrito.get(i).getId() == referencia) {
                if (baseDatos.disponible(referencia, carrito.get(i).getExistencias() + cantidad)) {
                    precio = carrito.get(i).getPrecio() / carrito.get(i).getExistencias();

                    carrito.get(i).setExistencias(carrito.get(i).getExistencias() + cantidad);
                } else {
                    sesion.setAttribute("Mensaje", "No hay suficientes unidades del producto");
                }
                encontrado = true;
            }
            i++;
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
