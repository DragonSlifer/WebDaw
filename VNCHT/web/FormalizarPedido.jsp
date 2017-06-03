<%-- 
    Document   : FormalizarPedido
    Created on : 24-abr-2017, 11:40:24
    Author     : Jorge
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" import="VNCHT_Main.*" import="java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/CSS" href="estilo.css">
        <title>Formalizar Pedido</title>
    </head>
    <body>
        <%
            if (!(boolean) session.getAttribute("true")) {

        %>
        <p>Por favor, rellene el siguiente formulario para que podamos tramitar el pedido. TODOS los campos son obligatorios.</p>
        <form id="formualario" onsubmit="ProcesarForm(this, 'ProcesarPedido', 'contenido');return false">
            <label>Escriba su nombre: </label>
            <input type="text" name="input_nombre">
            <label>Escriba su DNI: </label>
            <input type="text" name="input_dni">
            <label>Escriba su dirección: </label>
            <input type="text" name="input_direcc">
            <label>Escriba su código postal: </label>
            <input type="text" name="input_codpos">
            <label>Escoja su método de pago: </label>
            <select name="input_mepago">
                <option value="0" selected="selected">1-Tarjeta de Crédito</option>
                <option value="1">2-Con la tarjeta de compra del Corte Inglés</option>
                <option value="2">3-Riñón</option>
                <option value="3">4-Cabra</option>
                <option value="4">5-Palanca</option>
            </select>
            <label>Escriba su número de Targeta: </label>
            <input type="text" name="input_tarjeta">
            <p>Solo las opciones 1 y 2 son válidas. Si escojes las opciones 3 a 5, golpe de remo.</p>
            <input type="submit" name="Submit" value="Formalizar Pedido" />
            <%} else {%>
                    <h1> NO PUEES COMPAR SI NO PIDES </h1>
                <%}
            %>
        </form>
    </body>
</html>
