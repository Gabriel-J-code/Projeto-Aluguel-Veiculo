package com.ufpb.projetoAluguelVeiculo.repositories;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Aluguel;
import com.ufpb.projetoAluguelVeiculo.entities.Loja;

import org.springframework.stereotype.Repository;

@Repository
public class AlugueisRepository {

    LojasRepository lr;

    public AlugueisRepository() {
        this.lr = new LojasRepository();
    }

    public AlugueisRepository(LojasRepository lojasRepository) {
        this.lr = lojasRepository;
    }

    public void saveData(){
        this.lr.saveData();
    }

    public Aluguel saveAluguel(Aluguel aluguel) {
        Loja loja = lr.findByCnpj(aluguel.getCnpjLoja());
        loja.addAluguel(aluguel);
        saveData();

        return aluguel;
    }

    // findAluguelByCnpj
    public ArrayList<Aluguel> findByCnpj(String cnpj) {
        return lr.findByCnpj(cnpj).getAlugueis();
    }

    // findAluguelById
    public Aluguel findById(String id, String cnpj) {
        ArrayList<Aluguel> alugueis = findByCnpj(cnpj);
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getId().equals(id)) {
                return aluguel;
            }
        }
        throw new IndexOutOfBoundsException("aluguel nao encontrado");
    }

    // deleteAluguelById
    public Boolean deleteById(String id, String cnpj) {
        Loja l = lr.findByCnpj(cnpj);
        for (Aluguel a : l.getAlugueis()) {
            if (a.getId().equals(id)) {
                l.removeAluguel(a);
                saveData();
                return true;
            }
        }
        return false;
    }

    // findAluguelByCpf
    public ArrayList<Aluguel> findByCpf(String cpf, String cnpj) {
        Loja l = lr.findByCnpj(cnpj);
        ArrayList<Aluguel> retorno = new ArrayList<Aluguel>();
        for (Aluguel alu : l.getAlugueis()) {
            if (alu.getCpfCliente().equals(cpf)) {
                retorno.add(alu);
            }
        }
        return retorno;
    }

	public void setLojaRepository(LojasRepository lojasRepository) {
        this.lr = lojasRepository;
	}	

}
