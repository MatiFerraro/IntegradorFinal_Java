package services.imp;

import DAOs.AutomovilDAO;
import DAOs.imp.AutomovilDAOImp;
import DTOs.AdicionalDTO;
import DTOs.AutomovilDTO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.Adicionales.Adicional;
import model.Automoviles.Automovil;
import services.AutomovilService;

import java.util.ArrayList;
import java.util.List;

public class AutomovilServiceImp implements AutomovilService {

    AutomovilDAO automovilDAO;
    AutomovilDTO automovilDTO;
    List<AutomovilDTO> automovilesDTO;

    List<Adicional> adicionales;
    List<AdicionalDTO> adicionalesDTO;
    AdicionalServiceImp adicionalService;

    public AutomovilServiceImp() {

        automovilDAO = new AutomovilDAOImp();
        automovilDTO = null;
        automovilesDTO = new ArrayList<>();

        adicionales = new ArrayList<>();
        adicionalesDTO = new ArrayList<>();
        adicionalService = null;

    }

    @Override
    public void fabricarAuto(AutomovilDTO automovilDTO) throws ServiceException {

        Automovil automovil = converterDTO_Model(automovilDTO);

        try {
            automovilDAO.insert(automovil);
        }
        catch (DAOException ex) {
            throw new ServiceException("Service Error: Error en insert " + ex.getCause());
        }

    }

    @Override
    public void eliminarAuto(Integer idAutomovil) throws ServiceException {

        try {
            automovilDAO.delete(idAutomovil);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en delete " + ex.getCause());
        }

    }

    @Override
    public void modificarAuto(AutomovilDTO automovilDTO) throws ServiceException {

        Automovil automovil = converterDTO_Model(automovilDTO);

        try {
            automovilDAO.update(automovil);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en update " + ex.getCause());
        }

    }

    @Override
    public AutomovilDTO consultarAuto(Integer idAutomovil) throws ServiceException {

        AutomovilDTO automovilDTO = null;
        try {
            automovilDTO = converterModel_DTO(automovilDAO.queryId(idAutomovil));
            return automovilDTO;
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en consultar " + ex.getCause());
        }
    }

    @Override
    public Float consultarAuto_Precio(Integer idAutomovil) throws ServiceException {

        Float precioFinal;

        try {
            precioFinal = automovilDAO.queryIdPrecio(idAutomovil);
            return precioFinal;
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en consultar precio " + ex.getCause());
        }
    }

    @Override
    public List<AutomovilDTO> consultarAutos() throws ServiceException {

        try {
            List<Automovil> automoviles = automovilDAO.query();
            for(Automovil automovil : automoviles) {
                automovilDTO = converterModel_DTO(automovil);
                automovilesDTO.add(automovilDTO);
            }
            return automovilesDTO;
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error en consultar lista " + ex.getCause());
        }

    }


    public Automovil converterDTO_Model(AutomovilDTO automovilDTO) {

        Automovil automovil = null;
        automovil.setIdVariante(automovilDTO.getIdVariante());
        automovil.setPrecioBase(automovilDTO.getPrecioBase());
        automovil.setPrecioFinal(automovilDTO.getPrecioFinal());
        adicionalesDTO = automovilDTO.getAdicionales();
        for(AdicionalDTO adicionalDTO : adicionalesDTO){
            automovil.agregarAdicional(adicionalService.converterDTO_Model(adicionalDTO));
        }
        return automovil;

    }

    public AutomovilDTO converterModel_DTO(Automovil automovil) {

        AutomovilDTO automovilDTO = null;
        automovilDTO.setIdVariante(automovil.getIdVariante());
        automovilDTO.setPrecioBase(automovil.getPrecioBase());
        automovilDTO.setPrecioFinal(automovil.getPrecioFinal());
        adicionales = automovil.getAdicionales();
        for(Adicional adicional : adicionales){
            adicionalesDTO.add(adicionalService.converterModel_DTO(adicional));
        }
        automovilDTO.setAdicionales(adicionalesDTO);
        return automovilDTO;

    }

    @Override
    public void updateService(AutomovilDTO automovilDTO) throws ServiceException {

        try {
            automovilDAO.update(converterDTO_Model(automovilDTO));
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al actualizar " + ex.getCause());
        }

    }

    @Override
    public void deleteService(Integer idAutomovil) throws ServiceException {

        try {
            automovilDAO.delete(idAutomovil);
        }
        catch (Exception ex) {
            throw new ServiceException("Service Error: Error al eliminar " + ex.getCause());
        }

    }

}
