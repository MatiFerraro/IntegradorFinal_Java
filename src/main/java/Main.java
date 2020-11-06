import DTOs.AutomovilDTO;
import model.Adicionales.*;
import model.Automoviles.Automovil;
import model.Automoviles.Coupe;
import services.AutomovilService;
import services.imp.AutomovilServiceImp;

public class Main {

    public static void main(String args[]) throws Exception {

        /*CoupeDAO coupeDAO = new CoupeDAOImp();
        Coupe coupeAux = new Coupe();

        Coupe miCoupe = new Coupe();
        miCoupe.agregarAdicional(new TechoCorredizo());
        miCoupe.agregarAdicional(new Llantas());

        miCoupe.setId(15);
        //miCoupe.setPrecioFinal(miCoupe.calcularCosto());

        coupeDAO.insert(miCoupe);

        coupeAux = coupeDAO.queryId(15);

        System.out.println("El id del miCoupe es: " + coupeAux.getId() +
                            " el precio base es: $" + coupeAux.getPrecioBase() +
                            " el precio final es: $" + coupeAux.getPrecioFinal());
         */

        AutomovilDTO automovilDTO = null;

        AutomovilService automovilService = new AutomovilServiceImp();
        automovilDTO = automovilService.consultarAuto(1);

        System.out.println("ID Variante: " + automovilDTO.getIdVariante());
        System.out.println("Precio Base: " + automovilDTO.getPrecioBase());
        System.out.println("Precio Final: " + automovilDTO.getPrecioFinal());

    }

}
