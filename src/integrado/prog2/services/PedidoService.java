package services;

import java.util.List;
import java.util.ArrayList;

import entities.Pedido;

public class PedidoService {
    private final List<Pedido> pedidos;
    private Long ultimoId;

    public PedidoService(){
        this.pedidos = new ArrayList<>();
        this.ultimoId = 0L;
    }

    public List<Pedido> listarPedidos(){
        List<Pedido> pedidosActivos = new ArrayList<>();
        for(Pedido pedido:pedidos){
            if(!pedido.isEliminado()){
                pedidosActivos.add(pedido);
            }
        }
        return pedidosActivos;
    }

    public void crearPedidoConDetalles(){

    }

    public void editarPedido(){}

    public void eliminarPedido(){}

    public Pedido buscarPedidoPorId(Long id){
        for(Pedido pedido:pedidos){
            if(pedido.getId().equals(id) && !pedido.isEliminado()){
                return pedido;
            }
        }
        return null;
    }
}
