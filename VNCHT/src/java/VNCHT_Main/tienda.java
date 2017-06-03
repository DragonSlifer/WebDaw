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
import java.util.ArrayList; //* Para las listas

public class tienda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        HttpSession sesion = request.getSession(true); // Accedemos al entorno de sesión
        ArrayList<producto> carrito = (ArrayList) sesion.getAttribute("carrito"); // Carrito
        if (cantidad >= 0) {
            int i = 0;
            while (i < carrito.size() && carrito.get(i).getId() != id) {
                i++;
            }
            if (i < carrito.size()) {
                if (cantidad == 0) {
                    carrito.remove(i);
                } else {
                    accesoBD bd = new accesoBD();
                    int existencias = bd.existenciasProductoBD(id); // Existencias del producto
                    if (cantidad <= existencias) {
                        carrito.get(i).setExistencias(cantidad);
                    }
                }
            }
        }
        if (carrito.size() > 0) {
            response.sendRedirect("tienda.jsp");
        } else {
            response.sendRedirect("productos.jsp");
        }
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
