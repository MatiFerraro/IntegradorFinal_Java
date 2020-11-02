package DAOs.imp;

import DAOs.FamiliarDAO;
import exceptions.DAOException;
import model.Adicionales.Adicional;
import model.Automoviles.Familiar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FamiliarDAOImp implements FamiliarDAO {

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

    public void insert(Familiar familiar) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO automovil(precioBase, precioFinal) VALUES(" +
                    familiar.getPrecioBase() + ", "  +
                    familiar.getPrecioFinal() + ")";
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

    public void update(Familiar familiar) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE automovil SET precioBase = " + familiar.getPrecioBase() +
                    " SET precioFinal = " + familiar.calcularCosto() +
                    " WHERE id = " + familiar.getId();
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

    public Familiar queryId(Integer id) throws DAOException {

        Connection conn = this.getConnection();
        Familiar familiar = null;

        try {
            String query = "SELECT * FROM automovil WHERE id = " + id ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                familiar.setId(id);
                familiar.setPrecioBase(rs.getFloat("precioBase"));
                familiar.setPrecioFinal(rs.getFloat("precioFinal"));
            }
        }
        catch(Exception ex) {
            throw new DAOException("Error en query", ex);
        }
        finally {
            closeConnection(conn);
        }
        return familiar;

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
