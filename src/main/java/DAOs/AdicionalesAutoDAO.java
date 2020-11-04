package DAOs;

import exceptions.DAOException;
import model.Adicionales.Adicional;

import java.util.List;

public interface AdicionalesAutoDAO {

    public void insert(Integer idAutomovil, Integer idAdicional) throws DAOException;
    public void update(Integer idAutomovil, Integer idAdicional) throws DAOException;
    public void delete(Integer idAutomovil, Integer idAdicional) throws DAOException;
    public List<Adicional> queryAdicionalesAuto(Integer idAutomovil) throws DAOException;

}
