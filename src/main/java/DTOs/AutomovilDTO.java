package DTOs;

import java.util.List;

public class AutomovilDTO {

    private String variante;
    private Integer idVariante;
    private Float precioBase;
    private Float precioFinal;
    private List<AdicionalDTO> adicionales;


    public String getVariante() {
        return variante;
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

    public List<AdicionalDTO> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<AdicionalDTO> adicionales) {
        this.adicionales = adicionales;
    }
}
