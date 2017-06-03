<%-- 
    Document   : tienda
    Created on : 20-abr-2017, 17:16:25
    Author     : Jorge
--%>

<%@page contentType="text/html" import="VNCHT_Main.*" import="java.util.ArrayList" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="./js/libCapas.js"></script>
        <link rel="stylesheet" type="text/CSS" href="estilo.css">
    </head>
    <body>
        <%
        String mensaje = (String)session.getAttribute("Mensaje");
        if (mensaje != null) //Eliminamos el mensaje consumido
        {
            session.removeAttribute("Mensaje");
            %>
            <h1> <%=mensaje%> </h1>
            <%
        }

        System.out.println(mensaje);%>
        <div>
            <table>
                <tr>
                    <th>id</th><th>nombre</th><th>precio</th><th>cantidad</th>
                </tr>
                <%
                    ArrayList<producto> carrito = (ArrayList) session.getAttribute("carrito");
                    producto p = null;
                    for (int i = 0; i < carrito.size(); i++) {
                        p = carrito.get(i);
                %>
                <tr>
                    <td><%=p.getId()%></td>
                    <td><%=p.getNombre()%></td>
                    <td><%=p.getPrecio()%><td>
                    <td>
                        <form method="post" onsubmit="ProcesarForm(this, 'tienda', 'contenido'); return false;">
                            <input type="hidden" name="id" value="<%=p.getId()%>">
                            <input type="text" name="cantidad" value="<%=p.getExistencias()%>">
                            <input type="submit" value="Actualizar cantidad">
                        </form>
                    </td>
                    <td>
                        <form method="post" onsubmit="ProcesarForm(this, 'tienda', 'contenido'); return false;">
                            <input type="hidden" name="id" value="<%=p.getId()%>">
                            <input type="hidden" name="cantidad" value="0">
                            <input type="submit" value="Eliminar producto">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
