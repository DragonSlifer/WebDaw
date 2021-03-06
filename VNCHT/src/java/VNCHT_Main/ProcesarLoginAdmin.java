/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge
 */
public class ProcesarLoginAdmin extends HttpServlet {

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
        String nick = request.getParameter("usuario");
        String password = request.getParameter("clave");
        HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesión
        accesoBD baseDatos = accesoBD.getInstance(); //Instancia de la clase accesoBD para la conexión con
        //la BD

        if ((nick != null) && (password != null)) //Se comprueba si el usuario y la clave están en la BD
        {
            if (baseDatos.comprobarAdminBD(nick, password)) {

                sesion.setAttribute("administrador", nick); //Registramos al usuario en el entorno de la sesión
                System.out.println("Si sale bien, aparece: " + (String) sesion.getAttribute("administrador"));
                
            } else //El usuario/clave no se encuentra en la BD
            {
                sesion.setAttribute("Mensaje", "La contraseña o el usuario son incorrectos. ¿Estás seguro que"
                        + "eres el administrador?"); //Registramos el error en el entorno de la sesión
            }
        } else {
            sesion.setAttribute("Mensaje", "Falta introducir el usuario o la clave"); //Registramos el error en el entorno de la sesión
        }
        response.sendRedirect("loginAdmin.jsp");
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
