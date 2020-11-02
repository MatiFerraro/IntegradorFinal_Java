package DAOs.imp;

import model.Adicionales.Adicional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdicionalDAOImp {

    private Connection getConnection() throws Exception {

        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ej_integrador3";
            String usuario = "root";
            String clave = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, clave);
        }
        catch(Exception ex) {
            throw new Exception("Error en iniciar conexion", ex);
        }
        return conn;

    }

    private void closeConnection(Connection conn) throws Exception {

        try {
            conn.close();
        }
        catch(Exception ex) {
            throw new Exception("Error en cerrar conexion", ex);
        }

    }

    public void insert(Adicional adicional) throws Exception {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO adicional(descripcion, precio) VALUES(" +
                    adicional.getDescripcion() + ", "  +
                    adicional.getPrecioAdicional() + ")";
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new Exception("Error en insert", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void update(Adicional adicional) throws Exception {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE adicional SET descripcion = " + adicional.getDescripcion() +
                    " SET precio = " + adicional.getPrecioAdicional() +
                    " WHERE id = " + adicional.getId();
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new Exception("Error en update", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void delete(Integer id) throws Exception {

        Connection conn = this.getConnection();

        try {
            String query = "DELETE FROM adicional WHERE id = " + id;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new Exception("Error en delete", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public Adicional queryId(Integer id) throws Exception {

        Connection conn = this.getConnection();
        Adicional adicional = null;

        try {
            String query = "SELECT * FROM adicional WHERE id = " + id ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                adicional.setId(id);
                adicional.setDescripcion(rs.getString("descripcion"));
                adicional.setPrecioAdicional(rs.getFloat("precio"));
            }
        }
        catch(Exception ex) {
            throw new Exception("Error en query", ex);
        }
        finally {
            closeConnection(conn);
        }
        return adicional;

    }

}
