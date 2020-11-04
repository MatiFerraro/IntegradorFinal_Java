package model.Automoviles;

import model.Adicionales.Adicional;

public class Familiar extends Automovil {

    private String variante = "Familiar";

    public Familiar(){
        super();
        this.idVariante = 2;
        this.precioBase = 245000f;
    }

    public String getVariante() {
        return variante;
    }

}
