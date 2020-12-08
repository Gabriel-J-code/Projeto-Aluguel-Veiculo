package com.ufpb.projetoAluguelVeiculo.services;

import java.util.ArrayList;

import com.ufpb.projetoAluguelVeiculo.entities.Loja;
import com.ufpb.projetoAluguelVeiculo.repositories.LojasRepository;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

import org.springframework.stereotype.Service;

@Service
public class LojasService {

    private LojasRepository lr;

    public LojasService(){
        this.lr = new LojasRepository();
    }

	public LojasService(LojasRepository lojasRepository) {
        this.lr = lojasRepository;
    }
    public Loja adicionarLoja(Loja loja) {
        validarLoja(loja);
        Loja lojaSalva = lr.saveLoja(loja);
        return lojaSalva;     
    }

    private void validarLoja(Loja loja) {
           
        if (Static.isEmpty(loja.getNome())) 
            throw new RuntimeException("loja sem nome.");
        if (Static.isEmpty(loja.getCnpj())) 
            throw new RuntimeException("loja " + loja.getNome() + " sem cnpj.");
        if (Static.isEmpty(loja.getIe())) 
            throw new RuntimeException("loja " + loja.getNome() + " sem ie.");
        if (Static.isEmpty(loja.getCep())) 
            throw new RuntimeException("loja " + loja.getNome() + " sem cep.");
        if (Static.isEmpty(loja.getNumero())) 
            throw new RuntimeException("loja " + loja.getNome() + " sem numero.");
    }

    public Loja getLoja(String cnpj){
        return lr.findByCnpj(cnpj);
    }

    public ArrayList<Loja> todasAsLojas() {
        return lr.findAll();
    }

    public boolean deleteLoja(String cnpj){
        return lr.deleteByCNPJ(cnpj);
    }
    
}