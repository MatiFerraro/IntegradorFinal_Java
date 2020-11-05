package model.Automoviles;

import model.Adicionales.Adicional;

import java.util.List;
import java.util.ArrayList;

public abstract class Automovil {

    private Integer id;
    protected Integer idVariante;
    protected Float precioBase;
    protected Float precioFinal;
    private List<Adicional> adicionales;

    public Automovil(){
        setAdicionales(new ArrayList<Adicional>());
    }

    public void agregarAdicional(Adicional unAdicional){
        getAdicionales().add(unAdicional);
    }

    public Float calcularCosto(){
        Float acum = 0f;
        for(Adicional adicional : getAdicionales()) {
            acum += adicional.getPrecioAdicional();
        }
        acum += getPrecioBase();
        precioFinal = acum;
        return acum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(Integer idVariante) {
        this.idVariante = idVariante;
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

    public abstract String getVariante();

    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }
}
