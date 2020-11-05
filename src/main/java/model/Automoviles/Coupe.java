package model.Automoviles;

import model.Adicionales.Adicional;

public class Coupe extends Automovil {

    private String variante = "Coupe";

    public Coupe(){
        super();
        this.idVariante = 1;
        this.precioBase = 270000f;
    }

    public String getVariante() {
        return variante;
    }

}
