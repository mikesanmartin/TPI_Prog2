import java.util.Scanner;

import UI.MenuPrincipal;
import services.CategoriaService;
import services.ProductoService;
import services.UsuarioService;
import services.PedidoService;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService();
        UsuarioService usuarioService = new UsuarioService();
        PedidoService pedidoService = new PedidoService();

        MenuPrincipal menuPrincipal = new MenuPrincipal(
            scanner,
            categoriaService,
            productoService,
            usuarioService,
            pedidoService
        );

        menuPrincipal.mostrarMenu();

        System.out.println("\n=== Sistema cerrado. ===");
        scanner.close();
    }
}
