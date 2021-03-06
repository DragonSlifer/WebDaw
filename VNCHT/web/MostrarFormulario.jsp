<%-- 
    Document   : MostrarFormulario
    Created on : 05-may-2017, 10:02:18
    Author     : Jorge y Lidia
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="VNCHT_Main.accesoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" language="javascript" src="js/libCapas.js"></script>
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
        String usuario = (String)session.getAttribute("usuario");

        if (usuario == null) //No hay usuario registrado
        {
            //Mostramos el formulario para la introducción del usuario y la clave
            %>
                <table id="formulario">
                    <tr>
                        <td>
                            <div>
                                <table>
                                    <tr>
                                        <td>
                                            <div>
                                                <img class="cabecera" id="cabeceraLogin" src="Imagenes/Logo-Usuario.jpg" alt="Logo-Usuario">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
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
                                <form method="get" onsubmit="ProcesarForm(this,'./ProcesarFormulario','contenido')" >
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
                                        <td> Nombre </td>
                                        <td><input type="text" name="nombre" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> Apellidos </td>
                                        <td><input type="text" name="apellidos" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> email </td>
                                        <td><input type="email" name="email" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> Direccion </td>
                                        <td><input type="email" name="direccion" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> Fecha Nacimiento </td>
                                        <td><input type="date" name="fecha_nac" /></td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="radio" name="tipo" value="Login" checked="checked"/> Login</td>
                                        <td><input type="radio" name="tipo" value="Registrarse"/> Registro</td>
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
            String clave = (String)session.getAttribute("clave");
            //Si existe un usuario, se mostrará la información del carrito de la compra
            %>
            <table id="formulario">
                <tr>
                    <td>
                        <div>
                            <table>
                                <tr>
                                    <td>
                                        <div>
                                            <img class="cabecera" src="Imagenes/Logo-Registro.jpg" alt="Logo-Registro">
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td class="tituloRegistro">
                                        <span class="titulo">Usuario <%=usuario%></span>
                                    </td>
                                </tr>
                                <tr> 
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td>
                                        <form method="post" onsubmit="ProcesarForm(this,'cerrarSesion','contenido');return false" >
                                            <input type="submit" value="Cerrar Sesion" class="boton"/>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td>
                                        <p class="titulo">Datos personales:</p>
                                    </td>
                                </tr>
                            </table>
                            <form method="post" onsubmit="ProcesarForm(this,'modificarDatos','contenido');return false" >
                                <table>
                                    <tr>
                                        <td>
                                            <label>Usuario: </label>
                                            <input type="text" name="usuario" value="<%=usuario%>"/>
                                        </td>
                                        <td>
                                            <label>Clave: </label>
                                            <input type="text" name="clave" value="<%=clave%>"/>
                                        </td>
                                        <td>
                                            <input type="submit" value="Modificar" class="boton"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                            <table>
                                <tr>
                                    <td class="tituloRegistro">
                                        <p class="titulo">Pedidos realizados:</p>
                                    </td>
                                </tr>
                            </table>
                            <%
                                accesoBD baseDatos = accesoBD.getInstance();
                                ArrayList<ResultSet> pedidos = baseDatos.obtenerPedidos(usuario);
                                int i = 1;
                                while (pedidos.get(0).next())
                                {
                            %>
                            
                            <table>
                                <tr>
                                    <td>
                                        <p class="titulo">ID de Pedido: <%=pedidos.get(0).getInt("id_pedido")%></p>
                                    </td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td>
                                        <p class="titulo">ID de Producto</p>
                                    </td>
                                    <td>
                                        <p class="titulo">Producto</p>
                                    </td>
                                    <td>
                                        <p class="titulo">Unidades</p>
                                    </td>
                                </tr>
                            
                            <%
                                    
                                        while(pedidos.get(i).next())
                                        {
                            %>
                            
                                <tr>
                                    <td>
                                        <p><%=pedidos.get(i).getInt("id_producto")%></p>
                                    </td>
                                    <td>
                                        <p><%=pedidos.get(i).getString("nombre")%></p>
                                    </td>
                                    <td>
                                        <p><%=pedidos.get(i).getInt("existencias")%></p>
                                    </td>
                                </tr>
                            
                            <%
                                        }
                                    
                            %>
                                <tr>
                                    <td>
                                        <form method="post" onsubmit="ProcesarForm(this,'cancelarPedido','contenido');return false" >
                                        <input type="submit" value="Cancelar Pedido" class="boton"/>
                                        <input type="hidden" value="<%=pedidos.get(0).getInt("id_pedido")%>" name="idPedido"/>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <%
                                i++;
                                } 
                            %>
                        </div>
                    </td>
                </tr>
            </table>
            <%}
        %>
    </body>
</html>