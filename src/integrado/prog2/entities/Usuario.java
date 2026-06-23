package entities;

import java.util.List;
import java.util.ArrayList;
import enums.Rol;

public class Usuario extends Base{
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;
    private List<Pedido> pedidos;
    
    public Usuario(Long id, String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol){
        super(id);
        setNombre(nombre);
        setApellido(apellido);
        setMail(mail);
        setCelular(celular);
        setContrasenia(contrasenia);
        setRol(rol);
        this.pedidos = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacio.");
        }
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if(apellido == null || apellido.isBlank()){
            throw new IllegalArgumentException("Apellido no puede ser nulo o vacio.");
        }
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        if(mail == null || mail.isBlank()){
            throw new IllegalArgumentException("Mail no puede ser nulo o vacio.");
        }
        this.mail = mail;
    }

    public void setCelular(String celular) {
        if(celular == null || celular.isBlank()){
            throw new IllegalArgumentException("Celular no puede ser nulo o vacio.");
        }
        this.celular = celular;
    }

    public void setContrasenia(String contrasenia) {
        if(contrasenia == null || contrasenia.isBlank()){
            throw new IllegalArgumentException("Contraseña no puede ser nulo o vacio.");
        }
        this.contrasenia = contrasenia;
    }

    public void setRol(Rol rol) {
        if(rol == null){
            throw new IllegalArgumentException("Rol no puede ser nulo.");
        }
        this.rol = rol;
    }
    
    public void agregarPedido(Pedido pedido){
        if(pedido == null){
            throw new IllegalArgumentException("Pedido no puede ser nulo.");
        }
        if(!this.pedidos.contains(pedido)){
            this.pedidos.add(pedido);
        }
        
        if(pedido.getUsuario() != this){
            pedido.setUsuario(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getCelular() {
        return celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void generarReporte() {
        StringBuilder sb = new StringBuilder();
        double totalAcumuladoUsuario = 0.0;
        String separadorGrande = "================================================================\n";
        String separadorChico = "---------------------------------\n";
        
        sb.append(separadorGrande);
        sb.append(String.format("USUARIO: %s %s | Mail: %s | Rol: %s%n", 
                this.nombre,
                this.apellido,
                this.mail,
                this.rol));
        sb.append(separadorGrande);
        
        for (Pedido pedido:pedidos){
            sb.append(String.format("Pedido #%d | Fecha: %s | Estado: %s | FormaPago: %s%n",
                    pedido.getId(),
                    pedido.getFecha(),
                    pedido.getEstado(),
                    pedido.getFormaPago()));
            sb.append(separadorChico);
            
            for(DetallePedido dp:pedido.getDetalles()){
                sb.append(String.format("- DetallePedido #%d: %s x %d => Subtotal: $%.2f%n", 
                        dp.getId(),
                        dp.getProducto().getNombre(),
                        dp.getCantidad(),
                        dp.getSubtotal()));
            }
            sb.append(String.format("TOTAL DEL PEDIDO: $%.2f%n",
                    pedido.getTotal()));
            sb.append(separadorChico);
            totalAcumuladoUsuario += pedido.getTotal();
        }
        sb.append(String.format("TOTAL ACUMULADO del usuario: $%.2f%n",
                totalAcumuladoUsuario));
        sb.append(separadorGrande);
        
        System.out.println(sb);
        
    }

    @Override
    public String toString() {
        return "Usuario{ nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", celular=" + celular + ", contrasenia=" + contrasenia + ", rol=" + rol + ", pedidos=" + pedidos + '}';
    }
}
