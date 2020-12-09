package com.ufpb.projetoAluguelVeiculo.services;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Cliente;
import com.ufpb.projetoAluguelVeiculo.repositories.ClientesRepository;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository cr;
    
    public ClientesService() {
		this.cr = new ClientesRepository();
	}

	public ClientesService(ClientesRepository clientesReposiroty) {
		this.cr = clientesReposiroty;
	}

	public Cliente adicionarCliente(Cliente cliente, String cnpj) {
        validarCliente(cliente);
        return cr.saveCliente(cliente, cnpj);
    }

    public ArrayList<Cliente> listarClientes(String cnpj) {
        return cr.findByCnpj(cnpj);
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

    public Boolean removerCliente(String cpf, String cnpj) {
        return cr.deleteByCpf(cpf, cnpj);
    }

	public Cliente buscarPorCpf(String cpf, String cnpj) {
        return cr.findByCpf(cpf, cnpj);
    }

}
