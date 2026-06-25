package enums;

import interfaces.OpcionMenu;

public enum AccionUsuario implements OpcionMenu {
    LISTAR("Listar"),
    CREAR("Crear"),
    EDITAR("Editar"),
    ELIMINAR("Eliminar");

    private final String textoPantalla;

    AccionUsuario(String textoPantalla){
        this.textoPantalla = textoPantalla;
    }

    @Override
    public String getTextoPantalla(){
        return textoPantalla;
    }
}
