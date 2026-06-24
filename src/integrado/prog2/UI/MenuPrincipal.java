package UI;

import java.util.Scanner;

public class MenuPrincipal extends MenuConsola  {
    Scanner scanner = new Scanner(System.in);

    public MenuPrincipal(Scanner scanner) {
        super("SISTEMA DE GESTION DE PEDIDOS (FOOD STORE)", new String[]{
            "Categorias",
            "Productos",
            "Usuarios",
            "Pedidos"
        }, scanner);
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        switch(opcionMenu){
            case 1 -> {
                System.out.println("Selecciono Categorias.");
            }
            case 2 -> {
                System.out.println("Selecciono Productos.");
            }
            case 3 -> {
                System.out.println("Selecciono Usuarios.");
            }
            case 4 -> {
                System.out.println("Selecciono Pedidos.");
            }
        }
    }
}
