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
            case CREAR -> uiCrearCategoria();
            case EDITAR -> uiEditarCategoria();
            case ELIMINAR -> uiEliminarCategoria();
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

    private void uiCrearCategoria(){
        System.out.println("\n=== CREAR NUEVA CATEGORÍA ===");

        System.out.print("Ingrese el nombre de la nueva categoria: ");
        String nombre = scanner.nextLine().trim();

        if(nombre.isEmpty()) {
            System.out.println("El campo nombre no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese la descripcion de la nueva categoria: ");
        String descripcion = scanner.nextLine().trim();

        if(descripcion.isEmpty()){
            System.out.println("El campo descripcion no puede estar vacio.");
            return;
        }

        boolean categoriaCreada = categoriaService.crearCategoria(nombre, descripcion);

        if(categoriaCreada){
            System.out.println("Categoria creada exitosamente.");
        } else {
            System.out.println("Error: Ya existe categoria activa con ese nombre.");
        }
    }

    private void uiEditarCategoria(){
        System.out.println("\n=== EDITAR CATEGORÍA ===");

        System.out.print("Desea ver el listado existente de categorias antes de continuar? (S/N)");
        String respuesta = scanner.nextLine().trim();
        if (respuesta.equalsIgnoreCase("S")){
            this.uiListarCategorias();
        } 

        System.out.print("\nIngrese el ID de la categoría a editar: ");
        Long id = leerIdValido();
        if(id == null) return;

        System.out.print("Ingrese el nuevo nombre para la categoria: ");
        String nuevoNombre = scanner.nextLine().trim();
        if(nuevoNombre.isEmpty()){
            System.out.println("El nuevo nombre no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese la nueva descripcion: ");
        String nuevaDescripcion = scanner.nextLine().trim();
        if(nuevaDescripcion.isEmpty()){
            System.out.println("La nueva descripcion no puede estar vacia.");
            return;
        }

        boolean categoriaEditada= categoriaService.editarCategoria(id, nuevoNombre, nuevaDescripcion);

        if(categoriaEditada){
            System.out.println("Categoria actualizada exitosamente.");
        } else {
            System.out.println("Error: El ID ingresado no existe o corresponde a una categoria eliminada.");
        }
    }

    private void uiEliminarCategoria(){
        System.out.println("\n=== ELIMINAR CATEGORÍA ===");
        
        System.out.print("Desea ver el listado existente de categorias antes de continuar? (S/N)");
        String respuesta = scanner.nextLine().trim();
        if (respuesta.equalsIgnoreCase("S")){
            this.uiListarCategorias();
        } 
        
        System.out.println("Ingrese el ID de la categoria a eliminar: ");
        Long id = leerIdValido();
        if(id == null) return;

        System.out.print("ESta seguro de que desea eliminar la categoria?.");
        String confirmar = scanner.nextLine().trim();

        if(!confirmar.equalsIgnoreCase("S")){
            System.out.println("Operacion cancelada.");
            return;
        }

        boolean categoriaEliminada = categoriaService.eliminarCategoria(id);

        if(categoriaEliminada){
            System.out.println("Categoria eliminada existosamente.");
        } else {
            System.out.println("Error: El ID ingresado no existe o ya fue eliminado.");
        }
    }
}
