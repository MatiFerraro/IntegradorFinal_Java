package DAOs.imp;

import DAOs.CoupeDAO;
import exceptions.DAOException;
import model.Adicionales.Adicional;
import model.Automoviles.Coupe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoupeDAOImp implements CoupeDAO {

    private Connection getConnection() throws DAOException {

        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ej_integrador3";
            String usuario = "root";
            String clave = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, clave);
        }
        catch(Exception ex) {
            throw new DAOException("Error en iniciar conexion", ex);
        }
        return conn;

    }

    private void closeConnection(Connection conn) throws DAOException {

        try {
            conn.close();
        }
        catch(Exception ex) {
            throw new DAOException("Error en cerrar conexion", ex);
        }

    }

    public void insert(Coupe coupe) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO automovil(precioBase, precioFinal) VALUES(" +
                    coupe.getPrecioBase() + ", "  +
                    coupe.getPrecioFinal() + ")";
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("Error en insert", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void update(Coupe coupe) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE automovil SET precioBase = " + coupe.getPrecioBase() +
                    " SET precioFinal = " + coupe.calcularCosto() +
                    " WHERE id = " + coupe.getId();
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("Error en update", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void delete(Integer id) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "DELETE FROM automovil WHERE id = " + id;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("Error en delete", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public Coupe queryId(Integer id) throws DAOException {

        Connection conn = this.getConnection();
        Coupe coupe = null;

        try {
            String query = "SELECT * FROM automovil WHERE id = " + id ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                coupe.setId(id);
                coupe.setPrecioBase(rs.getFloat("precioBase"));
                coupe.setPrecioFinal(rs.getFloat("precioFinal"));
            }
        }
        catch(Exception ex) {
            throw new DAOException("Error en query", ex);
        }
        finally {
            closeConnection(conn);
        }
        return coupe;

    }

    public List<String> queryAdicionales(Integer id) throws DAOException {

        Connection conn = this.getConnection();
        Adicional adicional = null;
        List<String> adicionales = new ArrayList<String>();

        try {
            String query = "SELECT descripcion, precio " +
                    "FROM adicional " +
                    "LEFT JOIN adicionales_auto ON adicional.id = adicionales_auto.idAuto" +
                    "WHERE adicionales_auto.idAuto = " + id;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                adicionales.add(rs.getString("descripcion"));
            }
        }
        catch(Exception ex) {
            throw new DAOException("Error en queryAdicionales", ex);
        }
        finally {
            closeConnection(conn);
        }
        return adicionales;

    }

}
