import DAOs.AutomovilDAO;
import DAOs.imp.AutomovilDAOImp;
import model.Adicionales.*;
import model.Automoviles.Automovil;
import model.Automoviles.Coupe;
import model.Automoviles.Familiar;
import model.Automoviles.Sedan;

public class Main {

    public static void main(String args[]) throws Exception {

        AutomovilDAO autoSQL = new AutomovilDAOImp();
        Coupe coupeAux = new Coupe();

        Coupe miCoupe = new Coupe();
        miCoupe.agregarAdicional(new TechoCorredizo());
        miCoupe.agregarAdicional(new Llantas());

        miCoupe.setId(15);
        //miCoupe.setPrecioFinal(miCoupe.calcularCosto());

        autoSQL.insert(miCoupe);

        coupeAux = (Coupe) autoSQL.queryId(15);

        System.out.println("El id del miCoupe es: " + coupeAux.getId() +
                            " el precio base es: $" + coupeAux.getPrecioBase() +
                            " el precio final es: $" + coupeAux.getPrecioFinal());

    }

}
