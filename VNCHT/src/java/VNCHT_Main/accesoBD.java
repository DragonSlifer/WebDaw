/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VNCHT_Main;

/**
 *
 * @author Jorge
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class accesoBD {

    Connection conexionBD;
    private static accesoBD instanciaUnica;

    public accesoBD() {
        conexionBD = null;
    }

    public static accesoBD getInstance() 
    {
        if (instanciaUnica==null) 
        {
            instanciaUnica=new accesoBD();
        }

        return instanciaUnica;
    }
    
    public boolean disponible (int id, int cantidad)
    {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
       {
           abrirConexionBD();
       }
       ResultSet resultados;
       try
       {
           String con;
           Statement s = conexionBD.createStatement();
           //Obtenemos la cantidad de unidades del producto
           con = "SELECT existencias FROM `productos` WHERE id_producto='" + id + "'";
           resultados = s.executeQuery(con);
           resultados.next();
           
           if(resultados.getInt("existencias") >= cantidad) //se comprueba si hay productos en stock
           {
               return true;
           }
           else
           {
               return false;
           }
       }
       catch(Exception e)
       {
           //Error en la conexión con la BD
           System.out.println("No se ha completado la peticion...");
           return false;
       }
    }
    
    public void abrirConexionBD() {
        if (conexionBD == null) { // tiendaonline es el nombre de la base de datos que hemos creado con anterioridad.
            String nombreConexionBD = "jdbc:mysql://localhost/tiendaonline";
            try { // root y sin clave es el usuario por defecto que crea WAMPP.
                Class.forName("com.mysql.jdbc.Driver");
                conexionBD = DriverManager.getConnection(nombreConexionBD, "root", "");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("No se ha podido conectar a la BB.DD...");
            }
        }
    }

    public boolean comprobarAcceso() {
        abrirConexionBD();
        return conexionBD != null;
    }

    public int existenciasProductoBD(int id) {
        abrirConexionBD();
        ResultSet resultados = null;
        int existencias = 0;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT existencias FROM `productos` WHERE id_producto=" + id;
            resultados = s.executeQuery(con);
            if (resultados.next()) {
                existencias = resultados.getInt("existencias");
            }
        } catch (SQLException e) {
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        return existencias;
    }

    /**
     * Esta consulta obtiene todos los productos de la base de datos.
     * @return 
     */
    public ResultSet obtenerProductosBD() {
        abrirConexionBD();
        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM `productos`";
            resultados = s.executeQuery(con);
        } catch (SQLException e) {
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        return resultados;
    }
    
    /**
     * Esta consulta obtiene los datos del usuario segun el id del usuario. NO
     * recupera la contraseña bajo ningun concepto.
     * @param id
     * @return 
     */
    public ResultSet obtenerDatosUsuario(int id){
        abrirConexionBD();
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT nombre, apellidos, email FROM `user` WHERE id_user = " + id ;
            resultados = s.executeQuery(con);
        } catch (SQLException e) {
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        
        return resultados;
    }
    
    /**
     * Esta consulta obtiene el id del pedido, además de otros datos, según el
     * id del cliente el cual tenga la misma fecha que el sistema y todavía no
     * haya sido procesado.
     * @param id_cliente
     * @return 
     */
    public ResultSet obtenerPedidoIdCliente(int id_cliente){
        abrirConexionBD();
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
            con = "SELECT id_pedido,fecha_entrega, precio_pedido"
                + "FROM `pedidos`"
                + "WHERE id_cliente = " + id_cliente
                    + "AND TO_CHAR(fecha_pedido, 'DD-MON-YYYY') = " + date
                    + "AND pedido = 1";
            resultados = s.executeQuery(con);
        } catch (SQLException e) {
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        
        return resultados;
    }
    
    /**
     * Esta consulta devuelve los datos de la tabla PedidosProductos según el id
     * del pedido.
     * @param id_pedido
     * @return 
     */
    public ResultSet obtenerPedidosProductos(int id_pedido){
        abrirConexionBD();
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT id_producto, cantidad, precio_total FROM `pedidosproductos` WHERE id_pedido = " + id_pedido + "";
            resultados = s.executeQuery(con);
        } catch (SQLException e) {
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        
        return resultados;
    }
    
    public ResultSet obtenerProductosPedidos(int id_producto){
        abrirConexionBD();
        ResultSet resultados = null;
        
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT nombre, precio_unidad, tipo FROM `productos` WHERE id_producto = " + id_producto + "";
            resultados = s.executeQuery(con);
        } catch (SQLException e) {
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        
        return resultados;
    }
    
    public ResultSet loginUsuario(HttpServletRequest request){
        abrirConexionBD();
        ResultSet resultados = null;
        
        try{
            String consulta;
            Statement s = conexionBD.createStatement();
            consulta = "SELECT id_user FROM user WHERE nick = \"" + request.getParameter("uname") + "\" AND password = \"" + request.getParameter("password")+ "\"";
            resultados = s.executeQuery(consulta);
            
        } catch (SQLException e){
            System.out.println("Error ejecutando la consulta a la BB.DD....");
        }
        
        return resultados;
    }
    
    public boolean comprobarUsuarioBD(String usuario, String clave)
    {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        ResultSet resultados = null;
        try
        {
            String con;
            Statement s = conexionBD.createStatement();
            //Consulta, buscamos una correspondencia usuario/clave
            con = "SELECT * FROM user WHERE nick LIKE '" + usuario + "' and password LIKE '" + clave + "'";
            System.out.println(con);
            resultados = s.executeQuery(con);

            if ( resultados.first() ) //El usuario/clave se encuentra en la BD
            {
                s.close();
                return true;
            }
            else //El usuario/clave no se encuentra en la BD
            {
                s.close();
                return false;
            }
        }
        catch(Exception e)
        {
            //Error en la conexión con la BD
            System.out.println("No se ha completado la peticion..." + e);
            return false;
        }
    }
    
    public boolean comprobarAdminBD(String usuario, String clave)
    {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        ResultSet resultados = null;
        try
        {
            String con;
            Statement s = conexionBD.createStatement();
            //Consulta, buscamos una correspondencia usuario/clave
            con = "SELECT * FROM user WHERE nick LIKE '" + usuario + "' and password LIKE '" + clave + "'";
            System.out.println(con);
            resultados = s.executeQuery(con);

            if ( (resultados.first()) && ("administrador".equals(resultados.getString("tipo")))) //El usuario/clave se encuentra en la BD
            {
                s.close();
                return true;
            }
            else //El usuario/clave no se encuentra en la BD
            {
                s.close();
                return false;
            }
        }
        catch(Exception e)
        {
            //Error en la conexión con la BD
            System.out.println("No se ha completado la peticion..." + e);
            return false;
        }
    }
    
    public boolean registrarUsuarioBD(String nick, String password)
    {
       if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
       {
           abrirConexionBD();
       }
       
       try
       {
            String con;
            //Insertamos el nuevo usuario
            con = "INSERT INTO 'user' (nick, password, tipo, estado) VALUES ('" + nick + "', '" + password + "', 'usuario', 'activo');";

            conexionBD.createStatement().executeUpdate(con);

            return true;
       }
       catch(Exception e)
       {
           //Error en la conexión con la BD
           System.out.println("No se ha completado la peticion...");
           return false;
       }
    }
    
    public ArrayList<ResultSet> obtenerPedidos(String usuario)
    {
        ResultSet resultados1, resultados2, resultados3;
        ArrayList<ResultSet> pedidos = new ArrayList<>();
        
        if(conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        
        try
        {
            String con;
            
            Statement s1 = conexionBD.createStatement();
            //Statement s2 = conexionBD.createStatement();
            
            //Obtenemos el id de usuario
            //con = "SELECT id_user FROM user WHERE nick LIKE '" + usuario + "'";
            //resultados3 = s1.executeQuery(con);
            //resultados3.next();
            //obtenemos todos los pedidos del usuario que no esten enviados
            con = "SELECT * FROM pedidos WHERE (nick LIKE '" + usuario + "') AND (estado = 'pendiente')";
            resultados1 = s1.executeQuery(con);
            pedidos.add(resultados1);
            
            while(resultados1.next())
            {
                //obtenemos todos los productos de cada pedido
                con = "SELECT PROD.*,PP.cantidad,PP.precio_total FROM pedidos_productos PP JOIN productos PROD ON PP.id_producto=PROD.id_producto WHERE id_pedido = " + resultados1.getInt("id_pedido");
                resultados2 = conexionBD.createStatement().executeQuery(con);
                pedidos.add(resultados2);
            }
            resultados1.beforeFirst(); 
            System.out.println("Obtenidos pedidos");
        }
        catch(Exception e)
        {
            //Error en la conexión con la BD
            System.out.println("No se ha completado la peticion..." + e);
            System.out.println("No se han podido motrar los pedidos");
            return null;
        }
        
        return pedidos;
    }
    
    public boolean modificarUsuarioBD(String nick, String password)
    {
       if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
       {
           abrirConexionBD();
       }
       
       try
       {
            String con;
            //Actualizamos la nueva contraseña
            con = "UPDATE user SET password='" + password + "' WHERE nick LIKE '" + nick + "';";

            conexionBD.createStatement().executeUpdate(con);

            return true;
       }
       catch(Exception e)
       {
           //Error en la conexión con la BD
           System.out.println("No se ha completado la peticion..." + e);
           System.out.println("No se ha podido actualizar la contraseña");
           return false;
       }
    }
    
    public boolean actualizarAlmacen(ArrayList<producto> productos)
    {
        if(conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        
        try
        {
            ResultSet resultados;
            String con;
            
            for (int i = 0; i < productos.size(); i++)
            {
                //obtenemos la cantidad de productos que hay
                con = "SELECT existencias FROM productos WHERE id_producto = " + productos.get(i).getId() + ";";
                resultados = conexionBD.createStatement().executeQuery(con);
                resultados.next();
                int cantidad = resultados.getInt("existencias") - productos.get(i).getExistencias();
                //Actualizamos la cantidad de productos
                con = "UPDATE productos SET existencias=" + cantidad + " WHERE id_producto = " + productos.get(i).getId() + ";";
                conexionBD.createStatement().executeUpdate(con);
            }
            
            return true;
        }
        catch(Exception e)
        {
            //Error en la conexión con la BD
            System.out.println("No se ha completado la peticion..."+ e.toString());
            return false;
        }
    }
    
    public boolean anyadirPedido(String nombre, String tarjeta, String direccion, ArrayList<producto> productos, String usuario)
    {
        if(conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        
        try
        {
            ResultSet resultados;
            String con, estado = "pendiente";
            
            con = "SELECT id_pedido FROM pedidos WHERE nick LIKE '" + usuario + "';";
            resultados = conexionBD.createStatement().executeQuery(con);
            resultados.next();
            int idPedido = resultados.getInt(1) + 1;
            //introducimos el nuevo pedido

            con = "INSERT INTO pedidos (id_pedido,nick,estado) VALUES ('" + idPedido  + "','" + usuario +  "','" + estado + "')";
            conexionBD.createStatement().executeUpdate(con);
            //introducimos todos los productos del pedido
            System.out.println("Control 0");
            con = "INSERT INTO pedidos_productos (id_pedido,id_producto, cantidad, precio_total) VALUES ";
            int precio = 0;
            for (int i = 0 ; i < productos.size(); i++) 
            {
                System.out.println("Control " + i+1);
                precio += productos.get(i).getExistencias();
                con += "('" + idPedido + "','" + productos.get(i).getId() + "','" + productos.get(i).getExistencias() + "','" + productos.get(i).getExistencias()*productos.get(i).getPrecio() + "')";
                
                if(i != productos.size() - 1)
                {
                    con += ",";
                }
            }
            con += ";";
            
            conexionBD.createStatement().executeUpdate(con);
            
            con = "UPDATE pedidos SET precio_pedido = " + precio + " WHERE id_pedido = " + idPedido + ";";
            conexionBD.createStatement().executeUpdate(con);
            return true;
        }
        catch(Exception e)
        {
            //Error en la conexión con la BD
            System.out.println("No se ha completado la peticion..."+ e.toString());
            return false;
        }
    }
}
