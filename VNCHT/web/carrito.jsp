<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import="VNCHT_Main.*" contentType="text/html" pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" language="javascript" src="js/libCapas.js"></script>
        <title>Cesta</title>
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
        String usuarioActual = (String)session.getAttribute("usuario");
        boolean click = false;

        if (usuarioActual == null) //No hay usuario registrado
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
                                            <p class="login"> Para acceder a la lista de la compra debes estar registrado como usuario en VNCHT </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                                <table>
                                    <tr>
                                        <td><h1>No estás logueado, ¿buscas el login? Dale a <a href="#" onclick="Cargar('MostrarLogin.jsp','contenido')">Login</a></h1></td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            <%
        }
        else
        {
            
            //Si existe un usuario, se mostrará la información del carrito de la compra
            %>
                <table>
                    <tr>
                        <td>
                            <div>
                                <img class="cabecera" id="cabeceraTienda" src="Imagenes/Logo3.png" alt="Logo3">
                            </div>
                        </td>
                    </tr>
                </table>
                <table class="contenido">
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <p class="titulo">TU CESTA</p>
                                    </td>
                                </tr>
                            </table>
                            <%
                               boolean youshallnotpass;
                                if((boolean)session.getAttribute("true")){
                                    youshallnotpass = true;
                                } else {
                                    youshallnotpass = false;
                                }
                                ArrayList<producto> productos = new ArrayList<>();
                                if(!youshallnotpass)
                                     productos = (ArrayList<producto>)session.getAttribute("carrito");
                                DecimalFormat decimales = new DecimalFormat("0.00");
                                float total = 0;
                                if(!productos.isEmpty())
                                {
                                    for(int i = 0; i < productos.size(); i++)
                                    {
                            %>
                            <table>
                                <tr>
                                    <td>
                                        <p class="apartado">Producto</p>
                                    </td>
                                    <td>
                                        <p class="apartado">Titulo</p>
                                    </td>
                                    <td>
                                        <p class="apartado">Cantidad</p>
                                    </td>
                                    <td>
                                        <p class="apartado">Precio</p>
                                    </td>
                                </tr>
                                <tr>
                                    <!--<td class="espacioPeq">
                                        <img class="tienda" src="" alt=""
                                    <% //productos.get(i).getImagen()
                                       //productos.get(i).getNombre()
                                    %>>
                                    </td>
                                    <td class="espacioGra">-->
                                        <p class="tituloTienda"><%=productos.get(i).getNombre()%></p>
                                    </td>
                                    <td class="espacioPeq">
                                        <p><%=productos.get(i).getExistencias()%></p>
                                    </td>
                                    <td class="espacioPeq">
                                        <p class="precio"><%=decimales.format(productos.get(i).getPrecio() * productos.get(i).getExistencias())%> EUR</p>
                                        <%total += productos.get(i).getPrecio() * productos.get(i).getExistencias();%>
                                    </td>
                                    <td>
                                        <form method="post" onsubmit="ProcesarForm(this,'anyadirElemento','contenido');return false">
                                            <input type="submit" value="Añadir" class="boton">
                                            <input type="number" name="cantidad" value="1" min="1"/>
                                            <input value="<%= productos.get(i).getId()%>" type="hidden" name="referencia"/>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" onsubmit="ProcesarForm(this,'eliminarElemento','contenido');return false">
                                            <input type="submit" value="Eliminar" class="boton">
                                            <input type="number" name="cantidad" value="1" min="1"/>
                                            <input value="<%= productos.get(i).getId()%>" type="hidden" name="referencia"/>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <hr />
                            <% 
                                    }
                                }
                                else
                                {
                                    
                            %>
                            <table>
                                <tr>
                                    <td>
                                        <p>No hay productos en tu cesta</p>
                                    </td>
                                </tr>
                            </table>
                            <%
                                }
                            %>
                            <table>
                                <tr>
                                    <td>
                                        <p class="precio">PRECIO TOTAL = <%=decimales.format(total)%> EUR</p>
                                    </td>
                                </tr>
                            </table>
                            <table>
                                <tr>
                                    <td>
                                        <input value="Seguir Comprando" onclick="Cargar('productos.jsp', 'contenido');" class="boton"/>
                                    </td>
                                    <td>
                                        <input value="Comprar" onclick="Cargar('FormalizarPedido.jsp', 'contenido');" class="boton"/>
                                    </td>
                                    <td>
                                        <input value="Vaciar Cesta" onclick="Cargar('vaciarCesta', 'contenido');" class="boton"/>
                                    </td>
                            </table>
                        </td>
                    </tr>
                </table>
            <%}
        %>
    </body>
</html>