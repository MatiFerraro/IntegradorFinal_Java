package services;

import DTOs.AdicionalDTO;
import exceptions.ServiceException;

public interface AdicionalService {

    public void agregarAdicional(AdicionalDTO adicionalDTO) throws ServiceException;
    public AdicionalDTO consultarAdicional(Integer idAdicional) throws ServiceException;
    public void modificarAdicional(AdicionalDTO automovilDTO) throws ServiceException;
    public void eliminarAdicional(Integer idAdicional) throws ServiceException;
    public void updateService(AdicionalDTO adicionalDTO) throws ServiceException;
    public void deleteService(Integer idAdicional) throws ServiceException;

}
