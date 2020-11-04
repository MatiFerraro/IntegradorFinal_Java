package DAOs.imp;

import DAOs.AutomovilDAO;
import exceptions.DAOException;
import model.Adicionales.Adicional;
import model.Automoviles.Automovil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutomovilDAOImp implements AutomovilDAO {

    private Connection getConnection() throws DAOException {

        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/fabricaciondeautos";
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

    public void insert(Automovil automovil) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO automovil(precioBase, precioFinal) VALUES(" +
                    automovil.getPrecioBase() + ", "  +
                    automovil.getPrecioFinal() + ")";
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            List<Adicional> adicionales = automovil.getAdicionales();
            for(Adicional adicional : adicionales) {

            }
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en insert", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void update(Automovil automovil) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE automovil SET precioBase = " + automovil.getPrecioBase() +
                    " SET precioFinal = " + automovil.calcularCosto() +
                    " WHERE id = " + automovil.getId();
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en update", ex);
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
            throw new DAOException("DAO Error: Error en delete", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public Automovil queryId(Integer id) throws DAOException {

        Connection conn = this.getConnection();
        Automovil automovil = null;

        try {
            String query = "SELECT * FROM automovil WHERE id = " + id ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                automovil.setId(id);
                automovil.setPrecioBase(rs.getFloat("precioBase"));
                automovil.setPrecioFinal(rs.getFloat("precioFinal"));
            }
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en query", ex);
        }
        finally {
            closeConnection(conn);
        }
        return automovil;

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
            throw new DAOException("DAO Error: Error en queryAdicionales", ex);
        }
        finally {
            closeConnection(conn);
        }
        return adicionales;

    }

}
