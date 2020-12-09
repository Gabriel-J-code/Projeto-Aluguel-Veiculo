package com.ufpb.projetoAluguelVeiculo.controllers;

import java.util.ArrayList;
import com.ufpb.projetoAluguelVeiculo.DTO.CnpjDTO;
import com.ufpb.projetoAluguelVeiculo.entities.Loja;
import com.ufpb.projetoAluguelVeiculo.services.LojasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/api")
public class LojasController {
    private LojasService ls;   

    public LojasController(){
        this.ls = new LojasService();
    }
    @Autowired
    public LojasController(LojasService lojasService) {
        this.ls = lojasService;
	}

	@PostMapping("/loja")
    public ResponseEntity<Loja> adicionarLoja(@RequestBody Loja loja) {
        try {
            return new ResponseEntity<>(ls.adicionarLoja(loja), HttpStatus.CREATED);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/lojas")
    public ResponseEntity<ArrayList<Loja>> listarLojas() {
        return new ResponseEntity<>(ls.todasAsLojas(), HttpStatus.OK);
    }

    @DeleteMapping("/loja")
    public ResponseEntity<Boolean> deletarLoja(@RequestBody CnpjDTO cnpj) {
        Boolean deletar = ls.deleteLoja(cnpj.getCnpj());
        if (deletar == true)
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
