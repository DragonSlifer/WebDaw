<%@page contentType="text/html" import="VNCHT_Main.*" import="java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="./js/libCapas.js"></script>
        <link rel="stylesheet" type="text/CSS" href="estilo.css">
        <script type="text/javascript">
            function alerta(var cadena) {
                alert(cadena);
            }
        </script> 
    </head>
    <body>
        <%
            accesoBD con = new accesoBD();
            ResultSet productos = con.obtenerProductosBD();
        %>
        <div>
            <table>
                <%
                    if (productos != null) {
                        while (productos.next()) {
                            int id = productos.getInt("id_producto");
                            String nombre = productos.getString("nombre");
                            float precio = productos.getFloat("precio");
                            int existencias = productos.getInt("existencias");
                %>
                <tr>
                    <td><%=id%></td>
                    <td><%=nombre%></td>
                    <td><%=productos.getString("descripcion")%></td>
                    <td><%=precio%></td>
                    <td>
                        <%
                            if (existencias > 0) {
                        %>
                        <form method="post" onsubmit="ProcesarForm(this, './addCesta', 'contenido'); return false;">
                            <input type="hidden" name="referencia" value="<%=id%>">
                            <input type="hidden" name="nombre" value="<%=nombre%>">
                            <input type="hidden" name="precio" value="<%=precio%>">
                            <input type="submit" value="Añadir al carrito">
                        </form>
                            
                        <%
                            
                        } else {
                        %>
                        &nbsp;
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <h1> NO HAY PRODUCTOS EN LA BASE DE DATOS O EL RESULTADO DE LA CONSULTA ES NULO </h1>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>