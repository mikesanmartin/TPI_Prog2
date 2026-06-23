package entities;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base{
    private String nombre;
    private String descripcion;
    private List<Producto> productos;
    
    public Categoria(Long id,String nombre, String descripcion){
        super(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        this.productos = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacio.");
        }
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion == null || descripcion.isBlank()){
            throw new IllegalArgumentException("Descripcion no puede ser nulo o vacio.");
        }
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    
    public void agregarProducto(Producto producto){
        if(producto == null){
            throw new IllegalArgumentException("Producto no puede ser nulo.");
        }
        this.productos.add(producto);
    }

    @Override
    public String toString() {
        return "Categoria{ nombre=" + nombre + ", descripcion=" + descripcion + ", productos=" + productos + '}';
    }
}
