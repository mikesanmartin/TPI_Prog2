import services.CategoriaService;

public class Main {
    public static void main(String[] args){
        CategoriaService categoriaService = new CategoriaService();

        System.out.println("Look: " + categoriaService.getCategoriasActivas());
    }
}
