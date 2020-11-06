package DAOs.imp;

import DAOs.AdicionalesAutoDAO;
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

    Automovil automovil;
    Adicional adicional;
    List<Automovil> automoviles;
    List<Adicional> adicionales;
    AdicionalesAutoDAO adicionalesAutoDAO;

    public AutomovilDAOImp() {

        automovil = null;
        adicional = null;
        automoviles = new ArrayList<>();
        adicionales = new ArrayList<>();
        adicionalesAutoDAO = new AdicionalesAutoDAOImp();

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
    public void insert(Automovil automovil) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "INSERT INTO automovil(precioBase, precioFinal) VALUES(" +
                    automovil.getPrecioBase() + ", "  +
                    automovil.getPrecioFinal() + ")";
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            Integer idAuto = automovil.getId();
            adicionales = automovil.getAdicionales();

            for(Adicional adicional : adicionales) {
                Integer idAdicional = adicional.getId();
                adicionalesAutoDAO.insert(idAuto, idAdicional);
            }
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en insert ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public void delete(Integer id) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "DELETE FROM automovil WHERE id = " + id;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            adicionalesAutoDAO.deleteAll(id);
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en delete ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public void update(Automovil automovil) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "UPDATE automovil SET precioBase = " + automovil.getPrecioBase() +
                    " SET precioFinal = " + automovil.calcularCosto() +
                    " WHERE id = " + automovil.getId();
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);

            Integer idAutomovil = automovil.getId();
            adicionales = automovil.getAdicionales();
            for (Adicional adicional : adicionales) {
                Integer idAdicional = adicional.getId();
                adicionalesAutoDAO.update(idAutomovil, idAdicional);
            }
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en update ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public Automovil queryId(Integer id) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "SELECT * FROM automovil WHERE id = " + id ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                automovil.setId(id);
                automovil.setPrecioBase(rs.getFloat("precioBase"));
                automovil.setPrecioFinal(rs.getFloat("precioFinal"));
                adicionales = adicionalesAutoDAO.queryAdicionalesAuto(id);
                for(Adicional adicional : adicionales) {
                    automovil.agregarAdicional(adicional);
                }
            }
            return automovil;
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en consultar ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public Float queryIdPrecio(Integer id) throws DAOException {

        Connection conn = this.getConnection();
        Float precioFinal = 0f;

        try {
            String query = "SELECT precioFinal FROM automovil WHERE id = " + id ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            if(rs.next()){
                precioFinal = rs.getFloat("precioFinal");
            }
            return precioFinal;
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en consultar precio ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public List<Automovil> query() throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "SELECT * FROM automovil" ;
            Statement sentencia = conn.createStatement();
            sentencia.execute(query);
            ResultSet rs = sentencia.getResultSet();
            while(rs.next()){
                Integer idAutomovil = rs.getInt("id");
                automovil.setId(idAutomovil);
                automovil.setIdVariante(rs.getInt("idVariante"));
                automovil.setPrecioBase(rs.getFloat("precioBase"));
                automovil.setPrecioFinal(rs.getFloat("precioFinal"));
                automoviles.add(automovil);

                adicionales = adicionalesAutoDAO.queryAdicionalesAuto(idAutomovil);
                for(Adicional adicional : adicionales) {
                    automovil.agregarAdicional(adicional);
                }
            }
            return automoviles;
        }
        catch(Exception ex) {
            throw new DAOException("DAO Error: Error en consultar lista ", ex);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public List<Adicional> queryAdicionales(Integer id) throws DAOException {

        Connection conn = this.getConnection();

        try {
            String query = "SELECT descripcion, precio " +
                    "FROM adicional " +
                    "LEFT JOIN adicionales_auto ON adicional.id = adicionales_auto.idAuto" +
                    "WHERE adicionales_auto.idAuto = " + id;
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
