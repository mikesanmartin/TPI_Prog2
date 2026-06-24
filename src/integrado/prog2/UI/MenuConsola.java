package UI;

import interfaces.Menu;
import java.util.Scanner;

public abstract class MenuConsola implements Menu{
    protected final Scanner scanner;
    private final String titulo;
    private final String[] opciones;

    public MenuConsola(String titulo, String[] opciones, Scanner scanner){
        this.titulo = titulo;
        this.opciones = opciones;
        this.scanner = scanner;
    }

    @Override
    public void mostrarMenu(){
        int opcion;
        do{
            System.out.println("\n=== " + titulo + " ===");
            for(int i = 0; i < opciones.length; i++){
                System.out.println((i + 1) + ". " + opciones[i]);
            }
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcionValida();

            if(opcion != 0){
                ejecutarOpcion(opcion);
            }
        } while (opcion != 0);

    }

    private int leerOpcionValida(){
        while(true){
            try{
                int opcionUsuario = Integer.parseInt(scanner.nextLine());

                if (opcionUsuario >= 0 && opcionUsuario <= opciones.length){
                    return opcionUsuario;
                }
                System.out.println("Opcion fuera de rango. Por favor, reintente.");
            } catch (NumberFormatException e){
                System.out.println("Entrada invalida. Por favor, ingrese un numero.");
            }
        }
    }

    protected abstract void ejecutarOpcion(int opcionMenu);

    protected Long leerIdValido(){
        try{
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("ID Invalido. Intente nuevamente.");
            return null;
        }
    }
}
