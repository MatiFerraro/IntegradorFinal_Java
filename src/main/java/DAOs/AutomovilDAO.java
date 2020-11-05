package DAOs;

import exceptions.DAOException;
import model.Adicionales.Adicional;
import model.Automoviles.Automovil;

import java.util.List;

public interface AutomovilDAO {

    public void insert(Automovil automovil) throws DAOException;
    public void delete(Integer id) throws DAOException;
    public void update(Automovil automovil) throws DAOException;
    public Automovil queryId(Integer id) throws DAOException;
    public Float queryIdPrecio(Integer id) throws DAOException;
    public List<Automovil> query() throws DAOException;
    public List<Adicional> queryAdicionales(Integer id) throws DAOException;

}
