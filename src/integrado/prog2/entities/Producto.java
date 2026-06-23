package entities;

import java.time.LocalDateTime;

public class Producto extends Base {
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;
    
    public Producto(Long id, boolean eliminado, LocalDateTime createdAt, String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria){
        super(id,eliminado,createdAt);
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setStock(stock);
        setImagen(imagen);
        setDisponible(disponible);
        setCategoria(categoria);
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacio.");
        }
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if(precio < 0){
            throw new IllegalArgumentException("Precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion == null || descripcion.isBlank()){
            throw new IllegalArgumentException("Descripcion no puede ser nulo o vacio.");
        }
        this.descripcion = descripcion;
    }

    public void setStock(int stock) {
        if(stock < 0){
            throw new IllegalArgumentException("Stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public void setImagen(String imagen) {
        if(imagen == null || imagen.isBlank()){
            throw new IllegalArgumentException("Imagen no puede ser nulo o vacio.");
        }
        this.imagen = imagen;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setCategoria(Categoria categoria) {
        if(categoria == null){
            throw new IllegalArgumentException("Categoria no puede ser nulo.");
        }
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Producto{ nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + ", stock=" + stock + ", imagen=" + imagen + ", disponible=" + disponible + ", categoria=" + categoria + '}';
    }
}
