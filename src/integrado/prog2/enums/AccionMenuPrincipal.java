package enums;

import interfaces.OpcionMenu;

public enum AccionMenuPrincipal implements OpcionMenu {
    CATEGORIAS("Categorias"),
    PRODUCTOS("Productos"),
    USUARIOS("Usuarios"),
    PEDIDOS("Pedidos");

    private final String textoPantalla;

    AccionMenuPrincipal(String textoPantalla){
        this.textoPantalla = textoPantalla;
    }

    @Override
    public String getTextoPantalla(){
        return textoPantalla;
    }
}
