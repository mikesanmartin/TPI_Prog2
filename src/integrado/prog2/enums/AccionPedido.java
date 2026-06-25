package enums;

import interfaces.OpcionMenu;

public enum AccionPedido implements OpcionMenu {
    LISTAR("Listar"),
    CREAR("Crear"),
    EDITAR("Editar"),
    ELIMINAR("Eliminar");

    private final String textoPantalla;

    AccionPedido(String textoPantalla){
        this.textoPantalla = textoPantalla;
    }

    @Override
    public String getTextoPantalla(){
        return textoPantalla;
    }
}
