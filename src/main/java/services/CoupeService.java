package services;

import DTOs.CoupeDTO;
import exceptions.ServiceException;

public interface CoupeService {

    public void fabricarCoupe(CoupeDTO coupeDTO) throws ServiceException;
    public CoupeDTO consultarCoupe(Integer idCoupe) throws ServiceException;

}
