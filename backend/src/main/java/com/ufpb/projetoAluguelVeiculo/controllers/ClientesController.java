package com.ufpb.projetoAluguelVeiculo.controllers;

import java.util.ArrayList;
import com.ufpb.projetoAluguelVeiculo.DTO.ClienteDTO;
import com.ufpb.projetoAluguelVeiculo.DTO.CnpjDTO;
import com.ufpb.projetoAluguelVeiculo.entities.Cliente;
import com.ufpb.projetoAluguelVeiculo.services.ClientesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * ClienteController
 */
@RestController
@RequestMapping("/api/loja/clientes")
public class ClientesController {

    private ClientesService clientes_service;

    public ClientesController() {
        this.clientes_service = new ClientesService();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody ClienteDTO cliente) {
        try {
            Cliente clienteMap = new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(),
                    cliente.getTelefone());

            return new ResponseEntity<>(clientes_service.adicionarCliente(clienteMap, cliente.getCnpjLoja()),
                    HttpStatus.CREATED);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<ArrayList<Cliente>> listarClientes(@RequestBody CnpjDTO cnpj) {
        return new ResponseEntity<>(clientes_service.listarClientes(cnpj.getCnpj()), HttpStatus.OK);
    }
}
