package enums;

import interfaces.OpcionMenu;

public enum AccionProducto implements OpcionMenu {
    LISTAR("Listar"),
    CREAR("Crear"),
    EDITAR("Editar"),
    ELIMINAR("Eliminar");

    private final String textoPantalla;

    AccionProducto(String textoPantalla){
        this.textoPantalla = textoPantalla;
    }

    @Override
    public String getTextoPantalla(){
        return textoPantalla;
    }
}
