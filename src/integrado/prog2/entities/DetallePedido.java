package entities;

public class DetallePedido extends Base{
    private int cantidad;
    private double subtotal;
    private Producto producto;
    
    public DetallePedido(Long id, int cantidad, double subtotal, Producto producto){
        super(id);
        setCantidad(cantidad);
        setSubtotal(subtotal);
        setProducto(producto);
    }

    public void setCantidad(int cantidad) {
        if(cantidad <= 0){
            throw new IllegalArgumentException("Cantidad no puede ser menor a cero.");
        }
        this.cantidad = cantidad;
        
        if(this.producto != null){
            double subtotalActualizado = calcularSubtotal();
            setSubtotal(subtotalActualizado);
        }
    }

    public void setSubtotal(double subtotal) {
        if(subtotal <= 0){
            throw new IllegalArgumentException("Subtotal no puede ser negativo o cero.");
        }
        this.subtotal = subtotal;
    }

    public void setProducto(Producto producto) {
        if(producto == null){
            throw new IllegalArgumentException("Producto no puede ser nulo.");
        }
        this.producto = producto;
    }
    
    public double calcularSubtotal(){
        double subtotal = getProducto().getPrecio() * getCantidad();
        return subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "DetallePedido{ base=[ id=" + super.getId() + ", eliminado=" + super.isEliminado() + ",  created at=" + super.getCreatedAt() + "], cantidad=" + cantidad + ", subtotal=" + subtotal + ", producto=" + producto + '}';
    }
}
