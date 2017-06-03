<%-- 
    Document   : index
    Created on : 24-mar-2017, 13:13:45
    Author     : Lidia Montero Egidos y Jorge Martinez Hernandez
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="VNCHT_Main.accesoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Valve no Cuenta Hasta 3</title>
        <link rel="stylesheet" type="text/CSS" href="estilo.css">
        <script type="text/javascript" src="libCapas.js">
        </script>
    </head>
    <body onload="cargaInicial()">
        <div id="menu" class="menu">
        </div>
        <div id="contenido" class="contenido">
        </div>
        <%
            accesoBD baseDatos = accesoBD.getInstance();
            baseDatos.registrarUsuarioBD("UsuarioDePrueba", "prueba");
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("true", true);
        %>
    </body>
</html>
