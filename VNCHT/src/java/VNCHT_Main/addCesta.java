/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Martínez
 * @author Lidia Montero Egidos
 */
public class addCesta extends HttpServlet {

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
        accesoBD baseDatos = accesoBD.getInstance(); //Se obtiene acceso a la BD
        
        //Se extrae la información del nuevo producto del formulario
        int referencia = Integer.parseInt(request.getParameter("referencia"));
        String nombre = request.getParameter("nombre");
        float precio = Float.parseFloat(request.getParameter("precio"));
        String imagen = request.getParameter("imagen");
        //int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int cantidad = 1;
        int indice = 0;
        
        HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesión
        
        //Comprobamos si ya existe un carrito de la compra
        ArrayList<producto> carrito = (ArrayList)sesion.getAttribute("carrito");
        
        if (carrito == null) //Si no existe lo creamos
        {
            carrito = new ArrayList();
            sesion.setAttribute("true",false);
        }
        
        boolean encontrado = false;
        
        for(int i = 0; i < carrito.size(); i++)
        {
            producto producto = carrito.get(i);
            if(producto.getId() == referencia)
            {
                encontrado = true;
                indice = i;
            }
        }
        
        if(encontrado)
        {
            if(baseDatos.disponible(referencia, cantidad))
            {
                carrito.get(indice).setExistencias(carrito.get(indice).getExistencias() + cantidad);
            }
            else
            {
                sesion.setAttribute("Mensaje", "No hay suficientes unidades del producto disponibles");
            }
        }
        else
        {
            if(baseDatos.disponible(referencia, cantidad))
            {
                
                producto aux = new producto();
                aux.setId(referencia);
                aux.setNombre(nombre);
                //aux.setImagen(imagen);
                aux.setPrecio(precio);
                aux.setExistencias(cantidad); 
                
                carrito.add(aux);
            }
            else
            {
                sesion.setAttribute("Mensaje", "No hay suficientes unidades del producto disponibles");
            }
            
            // Por último se añade el carrito de la compra al entorno de la sesión
            sesion.setAttribute("carrito", carrito);
            
            // Y se redirige la petición al jsp “added.jsp” que nos informará si el producto se ha añadido correctamente.
            /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/added.jsp");
            dispatcher.forward(request,response);*/
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
