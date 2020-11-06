package DAOs.imp;

import DAOs.AdicionalDAO;
import exceptions.DAOException;
import model.Adicionales.Adicional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdicionalDAOImp implements AdicionalDAO {

    Adicional adicional;

    public AdicionalDAOImp() {

        adicional = null;

    }

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

    @Override
    public void insert(Adicional adicional) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO adicional(descripcion, precio) VALUES(" +
                    adicional.getDescripcion() + ", "  +
                    adicional.getPrecioAdicional() + ")";
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

    @Override
    public void update(Adicional adicional) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE adicional SET descripcion = " + adicional.getDescripcion() +
                    " SET precio = " + adicional.getPrecioAdicional() +
                    " WHERE id = " + adicional.getId();
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

    @Override
    public void delete(Integer id) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "DELETE FROM adicional WHERE id = " + id;
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

    @Override
    public Adicional queryId(Integer id) throws DAOException {

        Connection conn = this.getConnection();

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
            return adicional;
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en consultar ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

}
