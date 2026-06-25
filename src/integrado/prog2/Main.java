import java.util.Scanner;

import UI.MenuPrincipal;
import services.CategoriaService;
import services.ProductoService;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService();

        MenuPrincipal menuPrincipal = new MenuPrincipal(
            scanner,
            categoriaService,
            productoService
        );

        menuPrincipal.mostrarMenu();

        System.out.println("\n=== Sistema cerrado. ===");
        scanner.close();
    }
}
