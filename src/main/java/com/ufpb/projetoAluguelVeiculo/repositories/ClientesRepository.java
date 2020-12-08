package com.ufpb.projetoAluguelVeiculo.repositories;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Cliente;
import com.ufpb.projetoAluguelVeiculo.entities.Loja;

import org.springframework.stereotype.Repository;

@Repository
public class ClientesRepository {

    private LojasRepository lr;

    public ClientesRepository(){
        this.lr = new LojasRepository();
    }

    public ClientesRepository(LojasRepository lojasRepository) {
        this.lr = lojasRepository;
    }

    public void saveData(){
        this.lr.saveData();
    }

    public Cliente saveCliente(Cliente cliente, String cnpj) {
        for (Loja loja : this.lr.findAll()) {
            if (loja.getCnpj().equals(cnpj)) {
                loja.addCliente(cliente);
                saveData();
                return cliente;
            }
        }
        throw new RuntimeException("Loja não cadastrada.");
    }

    public ArrayList<Cliente> findByCnpj(String cnpj) {
        for (Loja loja : lr.findAll()){
            if (loja.getCnpj().equals(cnpj)) {
                return loja.getClientes();
            }
        }
        throw new RuntimeException("Loja não cadastrada.");
    }

    public Cliente findByCpf(String cpf, String cnpj) {
        ArrayList<Cliente> clientes = findByCnpj(cnpj);
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public boolean deleteByCpf(String cpf, String cnpj) {
        Loja l = lr.findByCnpj(cnpj);
        for (Cliente c : l.getClientes()) {
            if (c.getCpf().equals(cpf)) {
                l.removeCliente(c);
                saveData();
                return true;
            }
        }
        return false;
    }

	public void setLojaRepository(LojasRepository lojasRepository) {
        this.lr = lojasRepository;
	}

}
