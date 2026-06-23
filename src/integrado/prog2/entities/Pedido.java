package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import enums.Estado;
import enums.FormaPago;

import interfaces.Calculable;

public class Pedido extends Base implements Calculable{
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private List<DetallePedido> detalles;
    private Usuario usuario;
    
    public Pedido(Long id, boolean eliminado, LocalDateTime createdAt, LocalDate fecha, Estado estado, FormaPago formaPago, Usuario usuario){
        super(id,eliminado,createdAt);
        setFecha(fecha);
        setEstado(estado);
        setFormaPago(formaPago);
        setUsuario(usuario);
        this.detalles  = new ArrayList<>();
    }

    public void setFecha(LocalDate fecha) {
        if(fecha == null){
            throw new IllegalArgumentException("Fecha no puede ser nulo.");
        }
        this.fecha = fecha;
    }

    public void setEstado(Estado estado) {
        if(estado == null){
            throw new IllegalArgumentException("Estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    public void setTotal(double total) {
        if(total <= 0){
            throw new IllegalArgumentException("Total no puede ser negativo.");
        }
        this.total = total;
    }

    public void setFormaPago(FormaPago formaPago) {
        if(formaPago == null){
            throw new IllegalArgumentException("Forma de Pago no puede ser nulo.");
        }
        this.formaPago = formaPago;
    }

    public void setUsuario(Usuario usuario) {
        if(usuario == null){
            throw new IllegalArgumentException("Usuario no puede ser nulo.");
        }
        this.usuario = usuario;
        if(!usuario.getPedidos().contains(this)){
            usuario.agregarPedido(this);
        }
    }
    
    public void addDetallePedido(int cantidad, Producto producto){
        if(cantidad < 0){
            throw new IllegalArgumentException("Cantidad debe ser mayor a cero.");
        }
        if(producto == null){
            throw new IllegalArgumentException("Producto no puede ser nulo.");
        };
        
        double subtotalDetalle = producto.getPrecio() * cantidad;
        Long nuevoIdDetalle = (long) (this.detalles.size() + 1);
        boolean eliminadoInicialBase = false;
        LocalDateTime fechaCreacion = LocalDateTime.now();
        
        DetallePedido nuevoDetalle = new DetallePedido(nuevoIdDetalle,eliminadoInicialBase,fechaCreacion,cantidad,subtotalDetalle,producto);
        
        this.detalles.add(nuevoDetalle);
        calcularTotal();
    }
    
    public DetallePedido findDetallePedidoByProducto(Producto producto){
        if(producto == null){
            throw new IllegalArgumentException("Producto no puede ser nulo.");
        }
        for(DetallePedido dp:detalles){
            if(dp.getProducto() == producto){
                return dp;
            }
        }
        return null;
    }
    
    public void deleteDetallePedidoByProducto(Producto producto){
        if(producto == null){
            throw new IllegalArgumentException("Producto no puede ser nulo.");
        }
        detalles.removeIf(dp -> dp.getProducto().equals(producto));
        calcularTotal();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    @Override
    public void calcularTotal(){
        double totalAcumulado = 0;
        for(DetallePedido dp:detalles){
            totalAcumulado += dp.getSubtotal();
        }
        setTotal(totalAcumulado);
    }

    @Override
    public String toString() {
        return String.format(
                "Pedido [ID: %s] | Fecha: %s | Estado: %s | Pago: %s | Total: $%.2f",
                super.getId(),
                this.fecha,
                this.estado,
                this.formaPago,
                this.total
        );
    }
}
