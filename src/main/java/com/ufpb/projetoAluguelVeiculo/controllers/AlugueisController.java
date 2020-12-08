package com.ufpb.projetoAluguelVeiculo.controllers;

import java.util.ArrayList;
import com.ufpb.projetoAluguelVeiculo.DTO.CnpjDTO;
import com.ufpb.projetoAluguelVeiculo.DTO.CpfCnpjDTO;
import com.ufpb.projetoAluguelVeiculo.DTO.IdCnpjDTO;
import com.ufpb.projetoAluguelVeiculo.DTO.PagamentoDTO;
import com.ufpb.projetoAluguelVeiculo.utils.Static;
import com.ufpb.projetoAluguelVeiculo.DTO.AluguelDTO;
import com.ufpb.projetoAluguelVeiculo.entities.Aluguel;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;
import com.ufpb.projetoAluguelVeiculo.services.AlugueisService;
import com.ufpb.projetoAluguelVeiculo.services.VeiculosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * AlugueisController
 */
@RestController
@RequestMapping("/api/loja")
public class AlugueisController {
    private AlugueisService alugueis_service;

    private VeiculosService veiculos_service;    

    public AlugueisController() {
        this.alugueis_service = new AlugueisService();
        this.veiculos_service = new VeiculosService();
    }
    @Autowired
    public AlugueisController(AlugueisService alugueisService, VeiculosService veiculosService) {
        this.alugueis_service = alugueisService;
        this.veiculos_service = veiculosService;
	}

	@GetMapping("/alugueis")
    public ResponseEntity<ArrayList<Aluguel>> listarAlugueis(@RequestBody CnpjDTO cnpj) {
        try {
            return new ResponseEntity<>(alugueis_service.listaAlugueisDaLoja(cnpj.getCnpj()), HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/aluguel")
    public ResponseEntity<Aluguel> pegarAluguel(@RequestBody IdCnpjDTO idCnpj) {
        try {
            return new ResponseEntity<Aluguel>(alugueis_service.getAluguel(idCnpj.getId(), idCnpj.getCnpj()),
                    HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/cliente/alugueis")
    public ResponseEntity<ArrayList<Aluguel>> buscarAluguelPeloCliente(@RequestBody CpfCnpjDTO cpfCnpj) {
        try {
            return new ResponseEntity<>(alugueis_service.listarAlugueisPorCpf(cpfCnpj.getCpf(), cpfCnpj.getCnpj()),
                    HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PostMapping("/aluguel")
    public ResponseEntity<Aluguel> criarAluguel(@RequestBody AluguelDTO aluguel) {
        try {
            Veiculo veiculo = veiculos_service.buscarVeiculoPorCodigo(aluguel.getCodigoVeiculo());
            Aluguel a = new Aluguel(aluguel.getCnpj(), veiculo, aluguel.getCpf(), aluguel.getHorarioInicio(),
                    aluguel.getHorarioTermino());

            return new ResponseEntity<Aluguel>(alugueis_service.addAluguel(a), HttpStatus.OK);

        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("/aluguel/pagar/{id}")
    public ResponseEntity<String> deletarAluguel(@PathVariable("id") String id, @RequestBody PagamentoDTO pagamento) {
        try {
            Double pagamentoRetorno = alugueis_service.pagarAluguel(id, pagamento.getCnpj(), pagamento.getValor());
            String troco = pagamentoRetorno == 0 ? "Pago" : "Troco: R$ " + Static.fixed2f(pagamentoRetorno);
            return new ResponseEntity<>(troco, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}
