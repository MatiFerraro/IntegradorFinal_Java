package DAOs.imp;

import DAOs.AdicionalesAutoDAO;
import exceptions.DAOException;
import model.Adicionales.Adicional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AdicionalesAutoDAOImp implements AdicionalesAutoDAO {

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
            throw new DAOException("Error en iniciar conexion ", ex);
        }
        return conn;

    }

    private void closeConnection(Connection conn) throws DAOException {

        try {
            conn.close();
        }
        catch(Exception ex) {
            throw new DAOException("Error en cerrar conexion ", ex);
        }

    }

    public void insert(Integer idAutomovil, Integer idAdicional) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO adicionales_auto(idAuto, idAdicional) VALUES(" +
                    idAutomovil + ", " + idAdicional + ")";
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en insert ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void update(Integer idAutomovil, Integer idAdicional) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE adicionales_auto SET idAdicional = " + idAdicional +
                    " WHERE idAuto = " + idAutomovil;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en update ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void delete(Integer idAutomovil, Integer idAdicional) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "DELETE FROM adicional_auto WHERE idAuto = " + idAutomovil +
                            " AND idAdicional = " + idAdicional;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en delete ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public void deleteAll(Integer idAutomovil) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "DELETE FROM adicional_auto WHERE idAuto = " + idAutomovil;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en delete ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    public List<Adicional> queryAdicionalesAuto(Integer idAutomovil) throws DAOException {

        Connection conn = this.getConnection();
        Adicional adicional = null;
        List<Adicional> adicionales = null;

        try {
            String query = "SELECT descripcion, precio " +
                    "FROM adicional " +
                    "LEFT JOIN adicionales_auto ON adicional.id = adicionales_auto.idAuto" +
                    "WHERE adicionales_auto.idAuto = " + idAutomovil;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            while(rs.next()){
                adicional.setId(rs.getInt("id"));
                adicional.setDescripcion(rs.getString("descripcion"));
                adicional.setPrecioAdicional(rs.getFloat("precio"));
                adicionales.add(adicional);
            }
            return adicionales;
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en consultar adicionales ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

}
