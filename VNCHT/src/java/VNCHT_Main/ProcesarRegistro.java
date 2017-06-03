/*
 * Desarrollo de Aplicaciones Web, 3 GII
 * Laboratorio
 * Lidia Montero Egidos y Jorge Martinez Hernandez
 */
package VNCHT_Main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lidia
 */
@WebServlet(name = "ProcesarRegistro", urlPatterns = {"/ProcesarRegistro"})
public class ProcesarRegistro extends HttpServlet {

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
        //Hay que recuperar más cosas
        String nick = request.getParameter("usuario");
        String password = request.getParameter("clave");
        /*String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String fecha_nac = request.getParameter("fecha_nac");
        String direccion = request.getParameter("direccion");*/
        String tipo = request.getParameter("tipo");
        HttpSession sesion = request.getSession(true); //Se accede al entorno de la sesión
        accesoBD baseDatos = accesoBD.getInstance(); //Instancia de la clase accesoBD para la conexión con
                                                     //la BD
        if ((nick != null) && (password!=null)) //Se comprueba si el usuario y la clave están en la BD
            {
                if (baseDatos.registrarUsuarioBD(nick, password))
                {
                    sesion.setAttribute("usuario", nick); //Registramos al usuario en el entorno de la sesión
                }
                else //El usuario/clave se encuentra en la BD
                {
                    sesion.setAttribute("Mensaje", "Ya existe este usuario en la BD"); //Registramos el error en el entorno de la sesión
                }
            }
            else
            {
                sesion.setAttribute("Mensaje", "Falta introducir el usuario o la clave"); //Registramos el error en el entorno de la sesión
            }
        response.sendRedirect("Registrarse.jsp");
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
        /*accesoBD bd = new accesoBD();
        int id;
        ResultSet ids = bd.loginUsuario(request);
        
        try {
            id=ids.getInt("id_user");
            Cookie c = new Cookie("userID",Integer.toString(id));
            c.setMaxAge(60*60*8);                                           ///< Dura 8 horas
            response.addCookie(c);                                          ///< Cuando pasemos de Servlet, esta cookie se guardará
        } catch (SQLException ex) {
            Logger.getLogger(ProcesarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
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
