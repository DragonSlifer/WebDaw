<%@page import="java.util.ArrayList"%>
<%@page import="VNCHT_Main.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class="contenido">
            <tr>
                <td>
                    <table>
                        <tr>
                            <td class="tituloRegistro">
                                <p class="titulo">RESGUARDO DEL PEDIDO</p>
                            </td>
                        </tr>
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>
                                <p class="titulo">Datos del pedido:</p>
                            </td>
                        </tr>
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <%
                        ArrayList<producto> productos = (ArrayList<producto>)session.getAttribute("carrito");
                        Pedido pedido = (Pedido)session.getAttribute("pedido");
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
                            <td class="espacioGra">
                                <p class="tituloTienda"><%=productos.get(i).getNombre()%></p>
                            </td>
                            <td class="espacioPeq">
                                <p><%=productos.get(i).getExistencias()%></p>
                            </td>
                            <td class="espacioPeq">
                                <p class="precio"><%=productos.get(i).getPrecio()%> EUR</p>
                            </td>
                        </tr>
                    </table>
                    <% 
                        } 
                    %>
                    <table>
                        <tr>
                            <td>
                                <p class="titulo">Información del pedido</p>
                            </td>
                        </tr>
                        <tr> 
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>
                                <p class="tituloTienda">Nombre: <%=pedido.getNombre()%></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="tituloTienda">Direccion: <%=pedido.getDireccion()%></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="tituloTienda">DNI <%=pedido.getDni()%></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="tituloTienda">Número de Tarjeta <%=pedido.getTarjeta()%></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input value="Confirmar" onclick="Cargar('tramitacion','contenido')" class="boton"/>
                            </td>
                            <td>
                                <input value="Cancelar" onclick="Cargar('tienda.jsp','contenido')" class="boton"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
