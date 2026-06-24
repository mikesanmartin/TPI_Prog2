package enums;

import interfaces.OpcionMenu;

public enum AccionCategoria implements OpcionMenu {
    LISTAR("Listar"),
    CREAR("Crear"),
    EDITAR("Editar"),
    ELIMINAR("Eliminar");

    private final String textoPantalla;

    AccionCategoria(String textoPantalla){
        this.textoPantalla = textoPantalla;
    }

    @Override
    public String getTextoPantalla(){
        return textoPantalla;
    }
}
