package services;
import DTOs.AdicionalDTO;
import DTOs.AutomovilDTO;
import exceptions.ServiceException;

import java.util.List;

public interface AutomovilService {

    public void fabricarAuto(AutomovilDTO automovilDTO) throws ServiceException;
    public void eliminarAuto(Integer idAutomovil) throws ServiceException;
    public void modificarAuto(AutomovilDTO automovilDTO) throws ServiceException;
    public AutomovilDTO consultarAuto(Integer idAutomovil) throws ServiceException;
    public Float consultarAuto_Precio(Integer idAutomovil) throws ServiceException;
    public List<AutomovilDTO> consultarAutos() throws ServiceException;

    public void updateService(AutomovilDTO automovilDTO) throws ServiceException;
    public void deleteService(Integer idAutomovil) throws ServiceException;

}
