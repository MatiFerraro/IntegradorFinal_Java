package services.imp;

import DAOs.CoupeDAO;
import DAOs.imp.CoupeDAOImp;
import DTOs.CoupeDTO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.Automoviles.Coupe;
import services.AutomovilService;
import services.CoupeService;

public class CoupeServiceImp2 implements AutomovilService<Coupe> {

    private CoupeDAO coupeDAO = new CoupeDAOImp();

    public void fabricarAuto(CoupeDTO coupeDTO) throws ServiceException {
        Coupe coupe = new Coupe();
        try {
            coupeDAO.insert(coupe);
        }
        catch (DAOException ex) {
            throw new ServiceException("Error al insertar Coupe" + ex.getCause());
        }
    }

    public CoupeDTO consultarCoupe(Integer idCoupe) throws ServiceException {
        CoupeDTO coupeDTO = null;
        try {
            coupeDTO = converterModel_DTO(coupeDAO.queryId(idCoupe));
            return coupeDTO;
        }
        catch (Exception ex) {
            throw new ServiceException("Error al consutlar Coupe" + ex.getCause());
        }
    }

    private Coupe converterDTO_Model(CoupeDTO coupeDTO){
        Coupe coupe = new Coupe();
        coupe.setPrecioBase(coupeDTO.getPrecioBase());
        coupe.setPrecioFinal(coupeDTO.getPrecioFinal());
        return coupe;
    }

    private CoupeDTO converterModel_DTO(Coupe coupe){
        CoupeDTO coupeDTO = new CoupeDTO();
        coupeDTO.setPrecioBase(coupe.getPrecioBase());
        coupeDTO.setPrecioFinal(coupe.getPrecioFinal());
        return coupeDTO;

    }

}
