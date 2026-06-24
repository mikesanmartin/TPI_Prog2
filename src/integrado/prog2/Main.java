import java.util.Scanner;

import UI.MenuPrincipal;
import services.CategoriaService;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        CategoriaService categoriaService = new CategoriaService();

        MenuPrincipal menuPrincipal = new MenuPrincipal(scanner,categoriaService);

        menuPrincipal.mostrarMenu();

        System.out.println("\n=== Sistema cerrado. ===");
        scanner.close();
    }
}
