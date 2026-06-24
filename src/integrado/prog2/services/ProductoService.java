package services;

import java.util.List;
import java.util.ArrayList;

import entities.Producto;
import entities.Categoria;

public class ProductoService {
    private final List<Producto> productos;
    private Long ultimoId;

    public ProductoService(){
        this.productos = new ArrayList<>();
        this.ultimoId = 0L;
    }

    public List<Producto> listarProductos(){
        List<Producto> productosActivos = new ArrayList<>();
        for(Producto producto: productos){
            if(!producto.isEliminado()){
                productosActivos.add(producto);
            }
        }
        return productosActivos;
    }

    public boolean crearProducto(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible,Categoria categoria){
        for(Producto producto: productos){
            if(!producto.isEliminado() && producto.getNombre().equalsIgnoreCase(nombre)){
                return false;
            }
        }

        ultimoId++;
        Producto nuevoProducto = new Producto(ultimoId,nombre,precio,descripcion,stock,imagen,disponible,categoria);
        productos.add(nuevoProducto);
        return true;
    }

    public boolean editarProducto(Long id, double precio, int stock, Categoria categoria){
        for(Producto producto: productos){
            if(producto.getId().equals(id) && !producto.isEliminado()){
                producto.setPrecio(precio);
                producto.setStock(stock);
                producto.setCategoria(categoria);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProducto(Long id){
        for(Producto producto: productos){
            if(producto.getId().equals(id) && !producto.isEliminado()){
                producto.setEliminado(true);
                return true;
            }
        }
        return false;
    }
}
