package services;

import java.util.List;
import java.util.ArrayList;

import entities.Categoria;

public class CategoriaService {
    private final List<Categoria> categorias;
    private Long ultimoId;

    public CategoriaService() {
        this.categorias = new ArrayList<>();
        this.ultimoId = 0L;
    }

    public List<Categoria> listarCategorias(){
        List<Categoria> categoriasActivas = new ArrayList<>();
        for (Categoria categoria: categorias){
            if(!categoria.isEliminado()){
                categoriasActivas.add(categoria);
            }
        }
        return categoriasActivas;
    }

    public boolean crearCategoria(String nombre, String descripcion){
        for(Categoria categoria : categorias){
            if(!categoria.isEliminado() && categoria.getNombre().equalsIgnoreCase(nombre)){
                return false;
            }
        }

        ultimoId++;
        Categoria nuevaCategoria = new Categoria(ultimoId,nombre,descripcion);
        categorias.add(nuevaCategoria);
        return true;
    }

    public boolean editarCategoria(Long id, String nuevoNombre, String nuevaDescripcion){
        for(Categoria categoria: categorias){
            if(categoria.getId().equals(id) && !categoria.isEliminado()){
                categoria.setNombre(nuevoNombre);
                categoria.setDescripcion(nuevaDescripcion);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCategoria(Long id){
        for(Categoria categoria: categorias){
            if(categoria.getId().equals(id) && !categoria.isEliminado()){
                categoria.setEliminado(true);
                return true;
            }
        }
        return false;
    }
}
