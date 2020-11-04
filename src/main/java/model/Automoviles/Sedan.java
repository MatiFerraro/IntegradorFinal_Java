package model.Automoviles;

import model.Adicionales.Adicional;

public class Sedan extends Automovil {

    private String variante = "Sedan";

    public Sedan(){
        super();
        this.idVariante = 3;
        this.precioBase = 230000f;
    }

    public String getVariante() {
        return variante;
    }

}
