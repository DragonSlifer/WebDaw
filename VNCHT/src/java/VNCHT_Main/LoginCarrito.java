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
public class LoginCarrito extends HttpServlet {

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
        String usuario = (String)request.getParameter("usuario"); //Se obtiene el nombre de usuario
        String clave = (String)request.getParameter("clave"); //Se obtiene la clave de usuario del formulario
        String tipo = (String)request.getParameter("tipo");
        HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesión
        
        accesoBD baseDatos = accesoBD.getInstance(); //Instancia de la clase accesoBD para la conexión con
                                                     //la BD
        if(tipo.equals("Registrarse"))
        {
            if ((usuario != null) && (clave!=null)) //Se comprueba si el usuario y la clave están en la BD
            {
                /*if (baseDatos.registrarUsuarioBD(usuario, clave))
                {
                    
                    sesion.setAttribute("usuario", usuario); //Registramos al usuario en el entorno de la sesión
                    sesion.setAttribute("carrito",new ArrayList<>());
                    sesion.setAttribute("clave", clave);
                }
                else
                {
                    sesion.setAttribute("Mensaje", "Ya existe este usuario en la BD"); //Registramos el error en el entorno de la sesión
                }*/
            }
            else
            {
                sesion.setAttribute("Mensaje", "Falta introducir el usuario o la clave"); //Registramos el error en el entorno de la sesión
            }
        }
        else
        {
            if ((usuario != null) && (clave!=null)) //Se comprueba si el usuario y la clave están en la BD
            {
                
                if (baseDatos.comprobarUsuarioBD(usuario, clave))
                {
                    sesion.setAttribute("usuario", usuario); //Registramos al usuario en el entorno de la sesión
                    sesion.setAttribute("carrito",new ArrayList<producto>());
                    sesion.setAttribute("clave", clave);
                }
                else //El usuario/clave no se encuentra en la BD
                {
                    sesion.setAttribute("Mensaje", "Usuario y/o clave incorrectos"); //Registramos el error en el entorno de la sesión
                }
            }
            else
            {
                sesion.setAttribute("Mensaje", "Falta introducir el usuario o la clave"); //Registramos el error en el entorno de la sesión
            } 
        }
        
        response.sendRedirect("carrito.jsp"); //Redirigimos la petición a la página de la tienda virtual
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
