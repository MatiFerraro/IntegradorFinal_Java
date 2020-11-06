package services.imp;

import DAOs.AdicionalDAO;
import DAOs.imp.AdicionalDAOImp;
import DTOs.AdicionalDTO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.Adicionales.Adicional;
import services.AdicionalService;

public class AdicionalServiceImp implements AdicionalService {

    private AdicionalDAO adicionalDAO = null;

    @Override
    public void agregarAdicional(AdicionalDTO adicionalDTO) throws ServiceException {

        Adicional adicional = converterDTO_Model(adicionalDTO);

        try {
            adicionalDAO.insert(adicional);
        }
        catch (DAOException ex) {
            throw new ServiceException("Service Error: Error en insert " + ex.getCause());
        }

    }

    @Override
    public void eliminarAdicional(Integer idAdicional) throws ServiceException {

        try {
            adicionalDAO.delete(idAdicional);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en delete " + ex.getCause());
        }

    }

    @Override
    public void modificarAdicional(AdicionalDTO adicionalDTO) throws ServiceException {

        Adicional adicional = converterDTO_Model(adicionalDTO);

        try {
            adicionalDAO.update(adicional);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en update " + ex.getCause());
        }

    }

    @Override
    public AdicionalDTO consultarAdicional(Integer idAdicional) throws ServiceException {

        AdicionalDTO adicionalDTO = null;

        try {
            adicionalDTO = converterModel_DTO(adicionalDAO.queryId(idAdicional));
            return adicionalDTO;
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en consultar " + ex.getCause());
        }

    }


    public Adicional converterDTO_Model(AdicionalDTO adicionalDTO) {

        Adicional adicional = null;
        adicional.setDescripcion(adicionalDTO.getDescripcion());
        adicional.setPrecioAdicional(adicionalDTO.getPrecioAdicional());
        return adicional;

    }

    public AdicionalDTO converterModel_DTO(Adicional adicional) {

        AdicionalDTO adicionalDTO = new AdicionalDTO();
        adicionalDTO.setDescripcion(adicional.getDescripcion());
        adicionalDTO.setPrecioAdicional(adicional.getPrecioAdicional());
        return adicionalDTO;

    }

    @Override
    public void updateService(AdicionalDTO adicionalDTO) throws ServiceException {

        try {
            adicionalDAO.update(converterDTO_Model(adicionalDTO));
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al actualizar " + ex.getCause());
        }

    }

    @Override
    public void deleteService(Integer idAdicional) throws ServiceException {

        try {
            adicionalDAO.delete(idAdicional);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al eliminar " + ex.getCause());
        }

    }

}
