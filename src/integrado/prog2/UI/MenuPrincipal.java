package UI;

import java.util.Scanner;
import enums.AccionMenuPrincipal;

import services.CategoriaService;
import services.ProductoService;
import services.UsuarioService;
import services.PedidoService;

public class MenuPrincipal extends MenuConsola  {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public MenuPrincipal(Scanner scanner, CategoriaService categoriaService, ProductoService productoService, UsuarioService usuarioService, PedidoService pedidoService) {
        super(
            "SISTEMA DE GESTION DE PEDIDOS (FOOD STORE)", 
            MenuUtil.convertirOpciones(AccionMenuPrincipal.values()),
            scanner);
            this.categoriaService = categoriaService;
            this.productoService = productoService;
            this.usuarioService = usuarioService;
            this.pedidoService = pedidoService;
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        AccionMenuPrincipal accion = AccionMenuPrincipal.values()[opcionMenu - 1];

        switch(accion){
            case CATEGORIAS -> new CategoriaMenu(scanner, categoriaService).mostrarMenu();
            case PRODUCTOS -> new ProductoMenu(scanner, productoService,categoriaService).mostrarMenu();
            case USUARIOS -> new UsuarioMenu(scanner,usuarioService).mostrarMenu();
            case PEDIDOS -> {
                System.out.println("Selecciono Pedidos.");
            }
        }
    }
}
