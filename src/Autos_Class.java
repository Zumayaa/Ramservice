public class Autos_Class {
    String nombre_auto;
    String marca;
    String categoria;
    String kilometraje;
    String costo;
    String img_dir;

    public Autos_Class() {
    }

    public Autos_Class(String nombre_auto, String marca, String categoria, String kilometraje, String costo, String img_dir) {
        this.nombre_auto = nombre_auto;
        this.marca = marca;
        this.categoria = categoria;
        this.kilometraje = kilometraje;
        this.costo = costo;
        this.img_dir = img_dir;
    }

    public String getNombre_auto() {
        return nombre_auto;
    }

    public void setNombre_auto(String nombre_auto) {
        this.nombre_auto = nombre_auto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getImg_dir() {
        return img_dir;
    }

    public void setImg_dir(String img_dir) {
        this.img_dir = img_dir;
    }
}
