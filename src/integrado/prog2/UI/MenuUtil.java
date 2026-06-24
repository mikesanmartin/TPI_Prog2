package UI;

import interfaces.OpcionMenu;

public class MenuUtil {
    private MenuUtil(){
        throw new IllegalStateException("Clase utilitaria - no debe ser instanciada.");
    }

    public static String[] convertirOpciones(OpcionMenu[] valores){
        String[] textos = new String[valores.length];
        for(int i = 0; i < valores.length; i++){
            textos[i] = valores[i].getTextoPantalla();
        }
        return textos;
    }

}
