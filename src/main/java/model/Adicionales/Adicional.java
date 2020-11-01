package model.Adicionales;

public abstract class Adicional {

    protected Integer id;
    private String descripcion;
    protected Float precioAdicional;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(Float precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

}
