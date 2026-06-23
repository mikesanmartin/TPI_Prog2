package services;

import java.util.List;
import java.util.ArrayList;

import entities.Categoria;

public class CategoriaService {
    private final List<Categoria> categorias;
    private int ultimoId;

    public CategoriaService() {
        this.categorias = new ArrayList<>();
        this.ultimoId = 0;
    }

    public List<Categoria> getCategoriasActivas(){
        List<Categoria> categoriasActivas = new ArrayList<>();
        for (Categoria categoria: categorias){
            if(!categoria.isEliminado()){
                categoriasActivas.add(categoria);
            }
        }
        return categoriasActivas;
    }
}
