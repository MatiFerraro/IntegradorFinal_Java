package DAOs;

import exceptions.DAOException;
import model.Automoviles.Automovil;

import java.util.List;

public interface AutomovilDAO {

    public void insert(Automovil automovil) throws DAOException;
    public void update(Automovil automovil) throws DAOException;
    public void delete(Integer id) throws DAOException;
    public Automovil queryId(Integer id) throws DAOException;
    public List<String> queryAdicionales(Integer id) throws DAOException;

}
