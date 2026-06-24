package UI;

import java.util.Scanner;
import enums.AccionMenuPrincipal;

import services.CategoriaService;

public class MenuPrincipal extends MenuConsola  {

    private final CategoriaService categoriaService;

    public MenuPrincipal(Scanner scanner, CategoriaService categoriaService) {
        super(
            "SISTEMA DE GESTION DE PEDIDOS (FOOD STORE)", 
            MenuUtil.convertirOpciones(AccionMenuPrincipal.values()),
            scanner);
            this.categoriaService = categoriaService;
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        AccionMenuPrincipal accion = AccionMenuPrincipal.values()[opcionMenu - 1];

        switch(accion){
            case CATEGORIAS -> new CategoriaMenu(scanner, categoriaService).mostrarMenu();
            case PRODUCTOS -> {
                System.out.println("Selecciono Productos.");
            }
            case USUARIOS -> {
                System.out.println("Selecciono Usuarios.");
            }
            case PEDIDOS -> {
                System.out.println("Selecciono Pedidos.");
            }
        }
    }
}
