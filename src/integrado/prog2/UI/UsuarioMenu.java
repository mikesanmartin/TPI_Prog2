package UI;

import java.util.Scanner;
import java.util.List;
import entities.Usuario;
import services.UsuarioService;
import enums.AccionCategoria;
import enums.AccionUsuario;

public class UsuarioMenu extends MenuConsola{
    private final UsuarioService usuarioService;

    public UsuarioMenu(Scanner scanner, UsuarioService usuarioService){
        super(
            "GESTION DE USUARIOS",
            MenuUtil.convertirOpciones(AccionUsuario.values()),
            scanner
        );
            this.usuarioService = usuarioService;
    }

    @Override
    protected void ejecutarOpcion(int opcionMenu){
        AccionCategoria accion = AccionCategoria.values()[opcionMenu - 1];

        switch(accion){
            case LISTAR -> uiListarUsuarios();
            case CREAR -> uiCrearUsuario();
            case EDITAR -> uiEditarUsuario();
            case ELIMINAR -> uiEliminarUsuario();
        }
    }

    public void uiListarUsuarios(){
        List<Usuario> usuariosActivos = usuarioService.listarUsuarios();
        if(usuariosActivos.isEmpty()){
            System.out.println("\nNo hay usuarios activos registrados en sistema.");
            return;
        }

        System.out.println("\n=== LISTADO DE CATEGORÍAS ACTIVAS ===");
        System.out.printf("%-5s | %-20s | %-20s | %-30s | %-15s%n", "ID", "NOMBRE", "APELLIDO", "MAIL", "ROL");
        for(Usuario usuario:usuariosActivos){
            System.out.printf("%-5d | %-20s | %-20s | %-30s | %-15s%n",
            usuario.getId(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getMail(),
            usuario.getRol()
            );
        }
        System.out.println("=====================================================================================================");
    }

    public void uiCrearUsuario(){}

    public void uiEditarUsuario(){}

    public void uiEliminarUsuario(){}
}
