<%-- 
    Document   : MostrarFormulario
    Created on : 05-may-2017, 10:02:18
    Author     : Jorge y Lidia
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="VNCHT_Main.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" language="javascript" src="libCapas.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //Utilizamos una variable en la sesión para informar de los mensajes de Error
            String mensaje = (String) session.getAttribute("Mensaje");
            if (mensaje != null) //Eliminamos el mensaje consumido
            {
                session.removeAttribute("Mensaje");
        %>
        <h1> <%=mensaje%> </h1>
        <%
            }

            System.out.println(mensaje);

            //Se obtiene el usuario actual registrado en el servicio web, del entorno de sesión
            String usuario = (String) session.getAttribute("usuario");

        %>
        <table id="formulario">
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
                                <td class="pagina3">
                                    <p class="login"> Para iniciar la sesión debes estar registrado como usuario en VNCHT </p>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                        <form method="post" onsubmit="ProcesarForm(this, './ProcesarRegistro', 'contenido'); return false;" >
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
                                    <td> email </td>
                                    <td><input type="email" name="email" /></td>
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

    </body>
</html>