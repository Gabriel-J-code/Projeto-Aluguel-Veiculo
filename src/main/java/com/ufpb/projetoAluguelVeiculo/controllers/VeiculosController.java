package com.ufpb.projetoAluguelVeiculo.controllers;

import java.util.ArrayList;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;
import com.ufpb.projetoAluguelVeiculo.services.VeiculosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VeiculosController {
    private VeiculosService vs;
    
    public VeiculosController(){
        this.vs = new VeiculosService();
    }

    @Autowired
    public VeiculosController(VeiculosService veiculosService) {
        this.vs = veiculosService;
	}

	@GetMapping("/veiculos")
    public ResponseEntity<ArrayList<Veiculo>> veiculosDisponiveis() {
        return new ResponseEntity<>(vs.listarVeiculos(), HttpStatus.OK);
    }

    @GetMapping("/veiculo/{id}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<Veiculo>(vs.buscarVeiculoPorCodigo(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<Veiculo>(HttpStatus.NOT_FOUND);
        }
    }

}