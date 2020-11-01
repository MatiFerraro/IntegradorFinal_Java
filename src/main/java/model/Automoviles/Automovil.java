package model.Automoviles;

import model.Adicionales.Adicional;

import java.util.List;
import java.util.ArrayList;

public abstract class Automovil {

    protected Integer id;
    protected Float precioBase;
    protected Float precioFinal;

    protected List<Adicional> adicionales;

    protected Automovil(){
        adicionales = new ArrayList<Adicional>();
    }

    public void agregarAdicional(Adicional unAdicional){
        adicionales.add(unAdicional);
    }

    public Float calcularCosto(){
        Float acum = 0f;
        for(Adicional adicional : adicionales) {
            acum += adicional.getPrecioAdicional();
        }
        acum += getPrecioBase();
        precioFinal = acum;
        return acum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Float precioBase) {
        this.precioBase = precioBase;
    }

    public Float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Float precioFinal) {
        this.precioFinal = precioFinal;
    }
}
