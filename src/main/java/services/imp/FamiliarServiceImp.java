package services.imp;

import DAOs.FamiliarDAO;
import DAOs.imp.FamiliarDAOImp;
import DTOs.FamiliarDTO;
import exceptions.DAOException;
import exceptions.ServiceException;
import model.Automoviles.Familiar;
import services.FamiliarService;

public class FamiliarServiceImp implements FamiliarService {

    private FamiliarDAO familiarDAO = new FamiliarDAOImp();

    public void fabricarFamiliar(FamiliarDTO familiarDTO) throws ServiceException {
        Familiar familiar = new Familiar();
        try {
            familiarDAO.insert(familiar);
        }
        catch (DAOException ex) {
            throw new ServiceException("Error al insertar Familiar" + ex.getCause());
        }
    }

    public FamiliarDTO consultarFamiliar(Integer idFamiliar) throws ServiceException {
        FamiliarDTO familiarDTO = null;
        try {
            familiarDTO = converterModel_DTO(familiarDAO.queryId(idFamiliar));
            return familiarDTO;
        }
        catch (Exception ex) {
            throw new ServiceException("Error al consutlar Familiar" + ex.getCause());
        }
    }

    private Familiar converterDTO_Model(FamiliarDTO familiarDTO){
        Familiar familiar = new Familiar();
        familiar.setPrecioBase(familiarDTO.getPrecioBase());
        familiar.setPrecioFinal(familiarDTO.getPrecioFinal());
        return familiar;
    }

    private FamiliarDTO converterModel_DTO(Familiar familiar){
        FamiliarDTO familiarDTO = new FamiliarDTO();
        familiarDTO.setPrecioBase(familiar.getPrecioBase());
        familiarDTO.setPrecioFinal(familiar.getPrecioFinal());
        return familiarDTO;

    }

}
