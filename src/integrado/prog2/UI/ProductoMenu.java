 package UI;

import java.util.Scanner;
import java.util.List;
import entities.Categoria;
import entities.Producto;
import services.CategoriaService;
import services.ProductoService;
import enums.AccionProducto;

public class ProductoMenu extends MenuConsola{
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoMenu(Scanner scanner, ProductoService productoService, CategoriaService categoriaService){
        super(
            "GESTION DE PRODUCTOS",
            MenuUtil.convertirOpciones(AccionProducto.values()),
            scanner);
            this.productoService = productoService;
            this.categoriaService = categoriaService;
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        AccionProducto accion = AccionProducto.values()[opcionMenu - 1];

        switch(accion){
            case LISTAR -> uiListarProductos();
            case CREAR -> uiCrearProducto();
            case EDITAR -> uiEditarProducto();
            case ELIMINAR -> uiEliminarProducto();
        }
    }

    private void uiListarProductos(){
        List<Producto> activos = productoService.listarProductos();
        if(activos.isEmpty()) {
            System.out.println("\nNo hay productos activas registradas en sistema.");
            return;
        }

        System.out.println("\n=== LISTADO DE PRODUCTOS ACTIVOS ===");
        System.out.printf("%-5s | %-25s | %-12s | %-8s | %-20s%n", "ID", "NOMBRE", "PRECIO", "STOCK", "CATEGORÍA (ID)");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for(Producto producto: activos){
            String infoCategoria = "Sin categoria";
            if(producto.getCategoria() != null){
                infoCategoria = producto.getCategoria().getNombre() + " (ID: " + producto.getCategoria().getId() + ")";
            }

            System.out.printf("%-5d | %-25s | $%-11.2f | %-8d | %-20s%n",
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                infoCategoria
            );
        }
    }

    private void uiCrearProducto(){
        System.out.println("\n=== CREAR NUEVO PRODUCTO ===");

        System.out.print("Ingrese el nombre del nuevo producto: ");
        String nombre = scanner.nextLine().trim();

        if(nombre.isEmpty()){
            System.out.println("El campo nombre no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese el precio del nuevo producto: ");
        double precio;
        try {
            precio = Double.parseDouble(scanner.nextLine().trim());
            if (precio <= 0) {
                System.out.println("El campo precio debe ser un numero mayor a cero.");
                return;
            }
        } catch (NumberFormatException e){
            System.out.println("Error: Debe ingresar un valor numerico valido para este campo.");
            return;
        }

        System.out.println("Ingrese una breve descripcion del nuevo producto: ");
        String descripcion = scanner.nextLine().trim();

        if(descripcion.isEmpty()){
            System.out.println("El campo descripcion no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese el stock inicial del nuevo producto: ");
        int stock;
        try {
            stock = Integer.parseInt(scanner.nextLine().trim());
            if (stock < 0) {
                System.out.println("El campo stock no peude ser menor a cero.");
                return;
            }
        } catch (NumberFormatException e){
            System.out.println("Error: Debe ingresar un numero entero valido para este campo.");
            return;
        }

        List<Categoria> categoriasActivas = categoriaService.listarCategorias();

        if(categoriasActivas.isEmpty()){
            System.out.println("No se puede avanzar con la creacion del producto porque no hay categorias disponibles.");
            System.out.println("Por favor, cree una categoria primero.");
            return;
        }

        System.out.println("\n--- Seleccione una Categoría Obligatoria ---");
        for (Categoria categoria: categoriasActivas){
            System.out.printf("[%d] %s%n", categoria.getId(), categoria.getNombre());
        }
        System.out.print("Ingrese el ID correspondiente a la categoria del nuevo producto: ");

        Long idCategoriaElegida = leerIdValido();
        if(idCategoriaElegida == null) return;

        Categoria categoriaAsociada = null;
        for (Categoria categoria: categoriasActivas) {
            if(categoria.getId().equals(idCategoriaElegida)){
                categoriaAsociada = categoria;
                break;
            }
        }

        if(categoriaAsociada == null) {
            System.out.println("La categoria seleccionada no existe o ha sido eliminada.");
            return;
        }

        String imagen = "default.png";
        boolean disponible = true;

        boolean productoCreado = productoService.crearProducto(nombre, precio, descripcion, stock, imagen, disponible, categoriaAsociada);

        if(productoCreado){
            System.out.println("\nProducto creado exitosamente.");
        } else {
            System.out.println("Error: Ya existe un producto activo con ese nombre.");
        }
    }

    public void uiEditarProducto(){
        System.out.println("\n=== EDITAR PRODUCTO ===");

        System.out.println("Ingrese el ID del producto que desea editar: ");
        Long idProducto = leerIdValido();
        if(idProducto == null) return;

        Producto productoAEditar = productoService.buscarProductoPorId(idProducto);

        if(productoAEditar == null || productoAEditar.isEliminado()){
            System.out.println("Error: El producto con ID " + idProducto + " no existe o fue eliminado.");
            return;
        }

        System.out.println("\nModificando producto: " + productoAEditar.getNombre());
        System.out.println("(Presione [ENTER] para mantener el valor actual)");

        double nuevoPrecio = productoAEditar.getPrecio();
        System.out.print("Precio actual [$" + productoAEditar.getPrecio() + "]");
        String entradaPrecio = scanner.nextLine().trim();

        if(!entradaPrecio.isEmpty()){
            try{
                double precioAux = Double.parseDouble(entradaPrecio);
                if(precioAux < 0) {
                    System.out.println("El precio no puede ser negativo. Edicion cancelada.");
                    return;
                }
                nuevoPrecio = precioAux;
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato de precio invalido. Edicion cancelada.");
                return;
            }
        }

        int nuevoStock = productoAEditar.getStock();
        System.out.println("Stock actual [" + productoAEditar.getStock() + "]: ");
        String entradaStock = scanner.nextLine().trim();
        if(!entradaStock.isEmpty()){
            try{
                int stockAux = Integer.parseInt(entradaStock);
                if(stockAux < 0) {
                    System.out.println("El nuevo stock no puede ser negativo. Edicion cancelada.");
                    return;
                }
                nuevoStock = stockAux;
            } catch (NumberFormatException e){
                System.out.println("Error: Formato de stock invalido. Edicion cancelada.");
                return;
            }
        }

        Categoria nuevaCategoria = productoAEditar.getCategoria();
        System.out.println("Categoría actual: " + (productoAEditar.getCategoria() != null ? productoAEditar.getCategoria().getNombre() : "Ninguna"));
        System.out.print("¿Desea cambiar la categoría? (S/N): ");
        String cambiarCat = scanner.nextLine().trim();

        if (cambiarCat.equalsIgnoreCase("S")) {
            List<Categoria> categoriasActivas = categoriaService.listarCategorias();
            if(categoriasActivas.isEmpty()){
                System.out.println("No hay categorias disponibles.");
            } else {
                System.out.println("\n--- Categorías Disponibles ---");
                for(Categoria categoria:categoriasActivas){
                    System.out.printf("[%d] %s%n", categoria.getId(), categoria.getNombre());
                }
                System.out.print("Seleccione el ID de la nueva categoria: ");
                Long idCategoria = leerIdValido();

                if(idCategoria != null){
                    Categoria categoriaEncontrada = categoriaService.buscarCategoriaPorId(idCategoria);
                    if(categoriaEncontrada != null){
                        nuevaCategoria = categoriaEncontrada;
                    } else {
                        System.out.println("Categoria no encontrada o eliminada. Se mantiene categoria original.");
                    }
                }
            }
        }

        boolean productoActualizado = productoService.editarProducto(idProducto, nuevoPrecio, nuevoStock, nuevaCategoria);

        if (productoActualizado){
            System.out.println("\nProducto actualizado exitosamente.");
        } else {
            System.out.println("\nError al actualizar el producto.");
        }
    }

    public void uiEliminarProducto(){
        System.out.println("\n=== ELIMINAR PRODUCTO ===");

        System.out.println("Ingrese el ID del producto a eliminar: ");
        Long idProducto = leerIdValido();
        if(idProducto == null) return;

        System.out.println("Esta seguro de que desea eliminar el producto?. (S/N)");
        String confirmar = scanner.nextLine().trim();

        if (!confirmar.equalsIgnoreCase("S")){
            System.out.println("Operacion cancelada.");
            return;
        }

        boolean productoEliminado = productoService.eliminarProducto(idProducto);

        if(productoEliminado){
            System.out.println("Producto eliminado existosamente.");
        } else {
            System.out.println("Error: El ID ingresado no existe o ya fue eliminado.");
        }
    }
}
