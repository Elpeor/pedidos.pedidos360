package com.pedidos360.pedidos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pedidos360.pedidos.Model.Pedido;
import com.pedidos360.pedidos.Service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Pedido", description = "Endpoints para gestionar pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping("/pedidos")
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todos los pedidos registrados")
    public ResponseEntity<List<Pedido>> obtenerPedidos(){
        List<Pedido> pedidos = service.getPedidos();
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/pedidos/{id}")
    @Operation(summary = "Obtener pedido por id", description = "Devuelve un pedido basado en su id")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable long id){
        Pedido pedido = service.getPedidoPorId(id);
        if (pedido ==null) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/pedidos")
    @Operation(summary = "Crear un nuevo pedido", description = "Crea un nuevo pedido con la información proporcionada")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido){
        Pedido nuevoPedido = service.savePedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/pedidos/{id}")
    @Operation(summary = "Actualizar un pedido existente", description = "Actualiza la información de un pedido existente basado en su ID")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable long id, @RequestBody Pedido pedido){
        try{
            Pedido oldPedido = service.getPedidoPorId(id);
            oldPedido.setDireccion(pedido.getDireccion());
            oldPedido.setProductos(pedido.getProductos());
            Pedido newPedido = service.savePedido(oldPedido);
            return ResponseEntity.ok(newPedido);
        }catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }    
    
    @DeleteMapping("/pedidos/{id}")
    @Operation(summary = "Eliminar un pedido", description = "Elimina la información de un pedido existente basado en su ID")
    public ResponseEntity<?> eliminarPedido(@PathVariable long id){
        try{
            service.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
