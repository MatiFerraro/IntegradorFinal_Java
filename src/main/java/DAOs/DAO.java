package DAOs;

import exceptions.DAOException;
import model.Adicionales.Adicional;

import java.util.List;

public interface DAO<T, I> {

    public void insert(T t) throws DAOException;
    public void update(T t) throws DAOException;
    public void delete(I id) throws DAOException;
    public T queryId(I id) throws DAOException;
    public List<String> queryAdicionales(I id) throws DAOException;

}
