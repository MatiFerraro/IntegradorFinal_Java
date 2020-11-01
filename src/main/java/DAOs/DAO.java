package DAOs;

import model.Adicionales.Adicional;

import java.util.List;

public interface DAO<T, I> {

    public void insert(T t) throws Exception;
    public void update(T t) throws Exception;
    public void delete(I id) throws Exception;
    public T queryId(I id) throws Exception;
    public List<String> queryAdicionales(I id) throws Exception;

}
