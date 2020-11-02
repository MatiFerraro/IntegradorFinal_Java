package services;

import DTOs.CoupeDTO;
import DTOs.FamiliarDTO;
import DTOs.SedanDTO;
import exceptions.ServiceException;

public interface AutomovilService <T>{

    public void fabricarCoupe(T autoDTO) throws ServiceException;
    public T consultarCoupe(Integer idAuto) throws ServiceException;

}
