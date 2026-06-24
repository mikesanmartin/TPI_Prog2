import java.util.Scanner;

import UI.MenuPrincipal;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        MenuPrincipal menuPrincipal = new MenuPrincipal(scanner);

        menuPrincipal.mostrarMenu();

        System.out.println("\n=== Sistema cerrado. ===");
        scanner.close();
    }
}
