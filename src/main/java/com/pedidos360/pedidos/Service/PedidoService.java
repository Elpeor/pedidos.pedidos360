package com.pedidos360.pedidos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedidos360.pedidos.Repository.PedidoRepository;

import com.pedidos360.pedidos.Model.Pedido;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public List<Pedido> getPedidos() {
        return repository.findAll();
    }

    public Pedido getPedidoPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido savePedido(Pedido pedido){
        return repository.save(pedido);
    }
    public void eliminarPedido(Long id) {
        repository.deleteById(id);
    }
    
}
