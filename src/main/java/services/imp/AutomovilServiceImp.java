package services.imp;

import DAOs.AutomovilDAO;
import DAOs.imp.AutomovilDAOImp;
import DTOs.AutomovilDTO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.Automoviles.Automovil;
import services.AutomovilService;

public class AutomovilServiceImp implements AutomovilService {

    private AutomovilDAO automovilDAO = new AutomovilDAOImp();

    public void fabricarAuto(AutomovilDTO automovilDTO) throws ServiceException {
        Automovil automovil = converterDTO_Model(automovilDTO);
        try {
            automovilDAO.insert(automovil);
        }
        catch (DAOException ex) {
            throw new ServiceException("Service Error: Error al fabricar Automovil" + ex.getCause());
        }
    }

    public AutomovilDTO consultarAuto(Integer idAutomovil) throws ServiceException {
        AutomovilDTO automovilDTO = null;
        try {
            automovilDTO = converterModel_DTO(automovilDAO.queryId(idAutomovil));
            return automovilDTO;
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al consultar Automovil" + ex.getCause());
        }
    }

    public void modificarAuto(AutomovilDTO automovilDTO) throws ServiceException {
        Automovil automovil = converterDTO_Model(automovilDTO);
        try {
            automovilDAO.update(automovil);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al modificar Automovil" + ex.getCause());
        }
    }

    public void eliminarAuto(Integer idAutomovil) throws ServiceException {
        try {
            automovilDAO.delete(idAutomovil);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al eliminar Automovil" + ex.getCause());
        }
    }

    public Automovil converterDTO_Model(AutomovilDTO automovilDTO) {
        Automovil automovil = new Automovil();
        automovil.setIdVariante(automovilDTO.getIdVariante());
        automovil.setPrecioBase(automovilDTO.getPrecioBase());
        automovil.setPrecioFinal(automovilDTO.getPrecioFinal());
        return automovil;
    }

    private AutomovilDTO converterModel_DTO(Automovil automovil) {
        AutomovilDTO automovilDTO = new AutomovilDTO();
        automovilDTO.setPrecioBase(automovil.getPrecioBase());
        automovilDTO.setPrecioFinal(automovil.getPrecioFinal());
        return automovilDTO;

    }

    public void updateService(AutomovilDTO automovilDTO) throws ServiceException {

        try {
            automovilDAO.update(converterDTO_Model(automovilDTO));
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al actualizar Automovil" + ex.getCause());
        }

    }

    public void deleteService(Integer idAutomovil) throws ServiceException {

        try {
            automovilDAO.delete(idAutomovil);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al eliminar Automovil" + ex.getCause());
        }

    }

}
