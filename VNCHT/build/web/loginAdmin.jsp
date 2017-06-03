<%-- 
    Document   : loginAdmin
    Created on : 12-may-2017, 12:20:37
    Author     : Lidia
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="VNCHT_Main.accesoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        //Utilizamos una variable en la sesión para informar de los mensajes de Error
        String mensaje = (String)session.getAttribute("Mensaje");
        if (mensaje != null) //Eliminamos el mensaje consumido
        {
            session.removeAttribute("Mensaje");
            %>
            <h1> <%=mensaje%> </h1>
            <%
        }

        System.out.println(mensaje);
        
        //Se obtiene el usuario actual registrado en el servicio web, del entorno de sesión
        String usuario = (String)session.getAttribute("administrador");

        System.out.println("Administrador: " + usuario);

        if (usuario == null) //No hay usuario registrado
        {
            System.out.println("Esto '" + usuario + "' debe de ser null.");
            //Mostramos el formulario para la introducción del usuario y la clave
            %>
                <table id="formulariologin">
                    <tr>
                        <td>
                            <div>
                                <table>
                                    <tr>
                                        <td class="tituloRegistro">
                                            <span class="titulo"> INICIAR SESION </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                                <table>
                                    <tr>
                                        <td>
                                            <p class="login"> Para iniciar la sesión debes ser administrador en VNCHT </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                                <form method="post" onsubmit="ProcesarForm(this,'./ProcesarLoginAdmin','contenido'); return false;">
                                <table>
                                    <tr>
                                        <td> Nick </td>
                                        <td><input type="text" name="usuario" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td> Contraseña </td>
                                        <td><input type="password" name="clave" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="submit" value="Enviar" class="boton"/></td>
                                    </tr>
                                </table>
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            <%
        }
        else
        {
            System.out.println("El administrador " + usuario + " se ha logueado.");
          %><a href="#" onclick="Cargar('menuAdmin.html','contenido')">Administrador</a><%
        }%>
    </body>
</html>