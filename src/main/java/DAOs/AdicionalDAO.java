package DAOs;

import exceptions.DAOException;
import model.Adicionales.Adicional;

public interface AdicionalDAO {

    public void insert(Adicional adicional) throws DAOException;
    public void delete(Integer id) throws DAOException;
    public void update(Adicional adicional) throws DAOException;
    public Adicional queryId(Integer id) throws DAOException;

}
