package UI;

import java.util.Scanner;
import java.util.List;
import entities.Pedido;
import services.PedidoService;
import enums.AccionPedido;

public class PedidoMenu extends MenuConsola {
    private final PedidoService pedidoService;

    public PedidoMenu(Scanner scanner, PedidoService pedidoService){
        super(
            "GESTION DE CATEGORIAS",
            MenuUtil.convertirOpciones(AccionPedido.values()),
            scanner
        );
        this.pedidoService = pedidoService;
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        AccionPedido accion = AccionPedido.values()[opcionMenu - 1];

        switch(accion){
            case LISTAR -> System.out.println("Dentro del menu pedidos se seleccion la opcion LISTAR.");
            case CREAR -> System.out.println("Dentro del menu pedidos se seleccion la opcion CREAR.");
            case EDITAR -> System.out.println("Dentro del menu pedidos se seleccion la opcion EDITAR.");
            case ELIMINAR -> System.out.println("Dentro del menu pedidos se seleccion la opcion ELIMINAR.");
        }
    }
}
