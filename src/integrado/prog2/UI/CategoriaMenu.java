package UI;

import java.util.Scanner;
import java.util.List;
import entities.Categoria;
import services.CategoriaService;
import enums.AccionCategoria;

public class CategoriaMenu extends MenuConsola{
    private final CategoriaService categoriaService;

    public CategoriaMenu(Scanner scanner, CategoriaService categoriaService){
        super(
            "GESTION DE CATEGORIAS", 
            MenuUtil.convertirOpciones(AccionCategoria.values()),
            scanner);
            this.categoriaService = categoriaService;
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        AccionCategoria accion = AccionCategoria.values()[opcionMenu - 1];

        switch(accion){
            case LISTAR -> uiListarCategorias();
            case CREAR -> System.out.println("uiCrearCategoria()");
            case EDITAR -> System.out.println("uiEditarCategoria()");
            case ELIMINAR -> System.out.println("uiEliminarCategoria()");
        }
    }

    private void uiListarCategorias(){
        List<Categoria> activas = categoriaService.listarCategorias();
        if(activas.isEmpty()) {
            System.out.println("\nNo hay categorias activas registradas en sistema.");
            return;
        }

        System.out.println("\n=== LISTADO DE CATEGORÍAS ACTIVAS ===");
        System.out.printf("%-5s | %-20s | %-30s%n", "ID", "NOMBRE", "DESCRIPCIÓN");
        System.out.println("------------------------------------------------------------");
        for(Categoria categoria: activas){
            System.out.printf("%-5d | %-20s | %-30s%n", categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
        }
    }
}
