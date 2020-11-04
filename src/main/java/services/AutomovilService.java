package services;
import DTOs.AdicionalDTO;
import DTOs.AutomovilDTO;
import exceptions.ServiceException;

public interface AutomovilService {

    public void fabricarAuto(AutomovilDTO automovilDTO) throws ServiceException;
    public AutomovilDTO consultarAuto(Integer idAutomovil) throws ServiceException;
    public void modificarAuto(AutomovilDTO automovilDTO) throws ServiceException;
    public void eliminarAuto(Integer idAutomovil) throws ServiceException;
    public void updateService(AutomovilDTO automovilDTO) throws ServiceException;
    public void deleteService(Integer idAutomovil) throws ServiceException;

}
