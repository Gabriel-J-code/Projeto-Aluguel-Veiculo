package com.ufpb.projetoAluguelVeiculo.services;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Cliente;
import com.ufpb.projetoAluguelVeiculo.repositories.LojasRepository;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

import org.springframework.stereotype.Service;

/**
 * ClienteService
 */
@Service
public class ClientesService {
    private LojasRepository lr;

    public ClientesService() {
        this.lr = new LojasRepository();
    }

    public Cliente adicionarCliente(Cliente cliente, String cnpj) {
        clienteExiste(cliente.getCpf(), cnpj);
        validarCliente(cliente);
        return lr.saveCliente(cliente, cnpj);
    }

    public ArrayList<Cliente> listarClientes(String cnpj) {
        return lr.findClientsByCnpj(cnpj);
    }

    public Cliente buscarClientePorCpf(String cpf, String cnpj) {
        return lr.findClienteByCpf(cpf, cnpj);
    }

    public void validarCliente(Cliente cliente) {
        
        if (Static.isEmpty(cliente.getNome()))
            throw new RuntimeException("Cliente sem nome.");
        if (Static.isEmpty(cliente.getCpf()))
            throw new RuntimeException("Cliente " + cliente.getNome() + " sem cpf.");
        if (Static.isEmpty(cliente.getEmail()))
            throw new RuntimeException("Cliente " + cliente.getNome() + " sem email.");
        if (Static.isEmpty(cliente.getTelefone()))
            throw new RuntimeException("Cliente " + cliente.getNome() + " sem telefone.");
    }

    public boolean removerCliente(String cpf, String cnpj) {
        return lr.deleteClienteByCpf(cpf, cnpj);
    }

    public boolean clienteExiste(String cpf, String cnpj) {
        try {
            Cliente cliente = lr.findClienteByCpf(cpf, cnpj);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}