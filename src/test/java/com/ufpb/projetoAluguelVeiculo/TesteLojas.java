package com.ufpb.projetoAluguelVeiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.ufpb.projetoAluguelVeiculo.entities.Aluguel;
import com.ufpb.projetoAluguelVeiculo.entities.Cliente;
import com.ufpb.projetoAluguelVeiculo.entities.Loja;
import com.ufpb.projetoAluguelVeiculo.entities.ModeloVeiculo;
import com.ufpb.projetoAluguelVeiculo.entities.Veiculo;
import com.ufpb.projetoAluguelVeiculo.services.AlugueisService;
import com.ufpb.projetoAluguelVeiculo.services.ClientesService;
import com.ufpb.projetoAluguelVeiculo.services.LojasService;
import com.ufpb.projetoAluguelVeiculo.utils.ServicesInitForTest;
import com.ufpb.projetoAluguelVeiculo.utils.Static;

import org.junit.jupiter.api.Test;

public class TesteLojas {
    
    private ServicesInitForTest sInitForTest;
    private LojasService ls;
    private ClientesService cs;
    private AlugueisService as;

    public TesteLojas() {
        this.sInitForTest = new ServicesInitForTest();        
        this.ls = sInitForTest.getLojasService();
        this.cs = sInitForTest.getClientesService();
        this.as = sInitForTest.getAlugueisService();
    }

    //

    private static ArrayList<Loja> arrToArrayList(Loja... k) {
        ArrayList<Loja> arrayList = new ArrayList<>();
        for (Loja item : k)
            arrayList.add(item);
        return arrayList;
    }

    private static ArrayList<Cliente> arrToArrayList(Cliente... k) {
        ArrayList<Cliente> arrayList = new ArrayList<>();
        for (Cliente item : k)
            arrayList.add(item);
        return arrayList;
    }

    //

    private void validarLoja(String mensagemEsperada, Loja loja) {
        try {
            ls.adicionarLoja(loja);
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    private void validarAluguel(String mensagemEsperada, Aluguel aluguel) {
        try {
            as.addAluguel(aluguel);
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    private void validarDeletarAluguel(String mensagemEsperada, Aluguel aluguel) {
        try {
            as.deletarAluguel("", aluguel.getCnpjLoja());
        } catch (RuntimeException e) {
            assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    private void validarCliente(String mensagemEsperada, Cliente cliente) {
        try {
            ls.adicionarLoja(lojaCompleta1);
        } catch(RuntimeException e1) {
            
            try {
                cs.adicionarCliente(cliente, CNPJ_LOJA1);
            } catch (RuntimeException e2) {
                assertEquals(mensagemEsperada, e2.getMessage());
            }
        }

    }

    //

    private String NOME_CLIENTE = "Cliente1";
    private String CPF_CLIENTE = "000.000.000-00";
    private String EMAIL_CLIENTE = "teste@teste.com";
    private String TELEFONE_CLIENTE = "(00)0000-0000";

    private Cliente clienteCompleto = new Cliente(NOME_CLIENTE, CPF_CLIENTE, EMAIL_CLIENTE, TELEFONE_CLIENTE);

    private String NOME_CLIENTE2 = "Cliente2";
    private String CPF_CLIENTE2 = "111.111.111-11";
    private String EMAIL_CLIENTE2 = "c2@teste.com";
    private String TELEFONE_CLIENTE2 = "(11)1111-1111";

    private Cliente clienteCompleto2 = new Cliente(NOME_CLIENTE2, CPF_CLIENTE2, EMAIL_CLIENTE2, TELEFONE_CLIENTE2);

    private String CODIGO_VEICULO = "0001";
    private String TIPO_VEICULO = "Bicicleta";
    private String MODELO_VEICULO = "Modelo1";
    private Double VALOR_HORA = 1.5;

    private ModeloVeiculo modeloCompleto = new ModeloVeiculo(TIPO_VEICULO, MODELO_VEICULO, VALOR_HORA);

    private Veiculo veiculoCompleto = new Veiculo(CODIGO_VEICULO, modeloCompleto);

    private String NOME_LOJA1 = "Loja1";
    private String CNPJ_LOJA1 = "cnpj1";
    private String IE_LOJA1 = "ie1";
    private String CEP_LOJA1 = "99999-999";
    private String NUMERO_LOJA1 = "s/n";

    private String NOME_LOJA2 = "Loja2";
    private String CNPJ_LOJA2 = "cnpj2";
    private String IE_LOJA2 = "ie2";
    private String CEP_LOJA2 = "99999-000";
    private String NUMERO_LOJA2 = "102A";

    private Loja lojaCompleta1 = new Loja(NOME_LOJA1, CNPJ_LOJA1, IE_LOJA1, CEP_LOJA1, NUMERO_LOJA1);

    private Loja lojaCompleta2 = new Loja(NOME_LOJA2, CNPJ_LOJA2, IE_LOJA2, CEP_LOJA2, NUMERO_LOJA2);

    private String HORARIO_INICIO = "12/12/1222 12:12";
    private String HORARIO_TERMINO = "12/12/1222 14:32";

    private Aluguel aluguelCompleto = new Aluguel(CNPJ_LOJA1, veiculoCompleto, CPF_CLIENTE, HORARIO_INICIO,
            HORARIO_TERMINO);

    @Test
    public void criarLoja() {
        sInitForTest.cleanRepository();

        ls.adicionarLoja(lojaCompleta1);
        ls.adicionarLoja(lojaCompleta2);
        
        int totalExperado = 2;
        assertEquals(totalExperado, ls.todasAsLojas().size());

    }

    private String TEXTO_ESPERADO_LOJA_NOME_INVALIDO = "loja sem nome.";
    private String TEXTO_ESPERADO_LOJA_CNPJ_INVALIDO = "loja Loja1 sem cnpj.";
    private String TEXTO_ESPERADO_LOJA_IE_INVALIDO = "loja Loja1 sem ie.";
    private String TEXTO_ESPERADO_LOJA_CEP_INVALIDO = "loja Loja1 sem cep.";
    private String TEXTO_ESPERADO_LOJA_NUMERO_INVALIDO = "loja Loja1 sem numero.";

    @Test
    public void validarNomeLojaInvalido() {
        // zerar_database(), ou reiniciar pra um estado// >>criar esse metodo<<
        Loja lojaNomeVazio = lojaCompleta1;
        lojaNomeVazio.setNome("");
        validarLoja(TEXTO_ESPERADO_LOJA_NOME_INVALIDO, lojaNomeVazio);

        Loja lojaNomeNulo = lojaCompleta1;
        lojaNomeNulo.setNome(null);
        validarLoja(TEXTO_ESPERADO_LOJA_NOME_INVALIDO, lojaNomeNulo);
    }

    @Test
    public void validarCnpjLojaInvalido() {
        // zerar_database(), ou reiniciar pra um estado// >>criar esse metodo<<
        Loja lojaCnpjVazio = lojaCompleta1;
        lojaCnpjVazio.setCnpj("");
        validarLoja(TEXTO_ESPERADO_LOJA_CNPJ_INVALIDO, lojaCnpjVazio);

        Loja lojaCnpjNulo = lojaCompleta1;
        lojaCnpjNulo.setCnpj(null);
        validarLoja(TEXTO_ESPERADO_LOJA_CNPJ_INVALIDO, lojaCnpjNulo);
    }

    @Test
    public void validarIeLojaInvalido() {
        // zerar_database(), ou reiniciar pra um estado// >>criar esse metodo<<
        Loja lojaIeVazio = lojaCompleta1;
        lojaIeVazio.setIe("");
        validarLoja(TEXTO_ESPERADO_LOJA_IE_INVALIDO, lojaIeVazio);

        Loja lojaIeNulo = lojaCompleta1;
        lojaIeNulo.setIe(null);
        validarLoja(TEXTO_ESPERADO_LOJA_IE_INVALIDO, lojaIeNulo);
    }

    @Test
    public void validarCepLojaInvalido() {
        // zerar_database(), ou reiniciar pra um estado// >>criar esse metodo<<
        Loja lojaCepVazio = lojaCompleta1;
        lojaCepVazio.setCep("");
        validarLoja(TEXTO_ESPERADO_LOJA_CEP_INVALIDO, lojaCepVazio);

        Loja lojaCepNulo = lojaCompleta1;
        lojaCepNulo.setCep(null);
        validarLoja(TEXTO_ESPERADO_LOJA_CEP_INVALIDO, lojaCepNulo);
    }

    @Test
    public void validarNumeroLojaInvalido() {
        // zerar_database(), ou reiniciar pra um estado// >>criar esse metodo<<
        Loja lojaNumeroVazio = lojaCompleta1;
        lojaNumeroVazio.setNumero("");
        validarLoja(TEXTO_ESPERADO_LOJA_NUMERO_INVALIDO, lojaNumeroVazio);

        Loja lojaNumeroNulo = lojaCompleta1;
        lojaNumeroNulo.setNumero(null);
        validarLoja(TEXTO_ESPERADO_LOJA_NUMERO_INVALIDO, lojaNumeroNulo);
    }

    // get loja
    @Test
    public void verificarGetLoja() {
        // zerar_database(), ou reiniciar pra um estado// >>criar esse metodo<<        
        sInitForTest.cleanRepository();
        ls.deleteLoja(CNPJ_LOJA1);
        Gson gson = new Gson();
        Loja lojaGetLoja = ls.adicionarLoja(lojaCompleta1);
        String loja1Gson = gson.toJson(lojaGetLoja);
        String esperado = gson.toJson(ls.getLoja(lojaGetLoja.getCnpj()));
        assertEquals(esperado, loja1Gson);
        ls.deleteLoja(lojaGetLoja.getCnpj());
    }

    // todasAsLojas
    private ArrayList<Loja> LOJAS_TESTE = arrToArrayList(lojaCompleta1, lojaCompleta2);

    @Test
    public void validarGetLojas() {
        sInitForTest.cleanRepository();
        ls.adicionarLoja(lojaCompleta1);
        ls.adicionarLoja(lojaCompleta2);
        ArrayList<Loja> lojas = ls.todasAsLojas();

        Gson gson = new Gson();
        String testeLojaJson = gson.toJson(LOJAS_TESTE);
        String lojasJson = gson.toJson(lojas);

        assertEquals(testeLojaJson, lojasJson);

    }
    
    // ClientesService

    private String TEXTO_ESPERADO_CLIENTE_NOME_INVALIDO = "Cliente sem nome.";
    private String TEXTO_ESPERADO_CLIENTE_CPF_INVALIDO = "Cliente Cliente1 sem cpf.";
    private String TEXTO_ESPERADO_CLIENTE_EMAIL_INVALIDO = "Cliente Cliente1 sem email.";
    private String TEXTO_ESPERADO_CLIENTE_TELEFONE_INVALIDO = "Cliente Cliente1 sem telefone.";

    @Test
    public void validarNomeClienteInvalido() {
        Cliente clienteNomeVazio = clienteCompleto;
        clienteNomeVazio.setNome("");
        validarCliente(TEXTO_ESPERADO_CLIENTE_NOME_INVALIDO, clienteNomeVazio);

        Cliente clienteNomeNulo = clienteCompleto;
        clienteNomeNulo.setNome(null);
        validarCliente(TEXTO_ESPERADO_CLIENTE_NOME_INVALIDO, clienteNomeNulo);
    }

    @Test
    public void validarCpfClienteInvalido() {
        Cliente clienteCpfVazio = clienteCompleto;
        clienteCpfVazio.setCpf("");
        validarCliente(TEXTO_ESPERADO_CLIENTE_CPF_INVALIDO, clienteCpfVazio);

        Cliente clienteCpfNulo = clienteCompleto;
        clienteCpfNulo.setCpf(null);
        validarCliente(TEXTO_ESPERADO_CLIENTE_CPF_INVALIDO, clienteCpfNulo);
    }

    @Test
    public void validarEmailClienteInvalido() {
        Cliente clienteEmailVazio = clienteCompleto;
        clienteEmailVazio.setEmail("");
        validarCliente(TEXTO_ESPERADO_CLIENTE_EMAIL_INVALIDO, clienteEmailVazio);

        Cliente clienteEmailNulo = clienteCompleto;
        clienteEmailNulo.setEmail(null);
        validarCliente(TEXTO_ESPERADO_CLIENTE_EMAIL_INVALIDO, clienteEmailNulo);
    }

    @Test
    public void validarTelefoneClienteInvalido() {
        Cliente clienteTelefoneVazio = clienteCompleto;
        clienteTelefoneVazio.setTelefone("");
        validarCliente(TEXTO_ESPERADO_CLIENTE_TELEFONE_INVALIDO, clienteTelefoneVazio);

        Cliente clienteTelefoneNulo = clienteCompleto;
        clienteTelefoneNulo.setTelefone(null);
        validarCliente(TEXTO_ESPERADO_CLIENTE_TELEFONE_INVALIDO, clienteTelefoneNulo);
    }

    // addClienteLoja
    @Test
    public void testarAdicionarCliente() {
        sInitForTest.cleanRepository();
        ls.adicionarLoja(lojaCompleta1);
        String cpfTeste = CPF_CLIENTE;
        cs.adicionarCliente(clienteCompleto, CNPJ_LOJA1);
        assertEquals(cs.buscarPorCpf(
            CPF_CLIENTE, 
            CNPJ_LOJA1
        ).getCpf(), cpfTeste);
    }

    // listarClientesPorCnpj
    @Test
    public void testarListagemDeClientesPorCnpj() {
        sInitForTest.cleanRepository();
        ls.adicionarLoja(lojaCompleta1);

        Cliente c1 = cs.adicionarCliente(clienteCompleto, CNPJ_LOJA1);
        Cliente c2 = cs.adicionarCliente(clienteCompleto2, CNPJ_LOJA1);
        ArrayList<Cliente> clientesAux = arrToArrayList(c1, c2);
        
        Gson gson = new Gson();
        String listaAux = gson.toJson(clientesAux);
        String listaRecebida = gson.toJson(cs.listarClientes(lojaCompleta1.getCnpj()));
        assertEquals(listaAux, listaRecebida);
    }

    // deletarCliente
    @Test
    public void testarDeletarCliente() {
        sInitForTest.cleanRepository();
        ls.adicionarLoja(lojaCompleta1);
        String cpfTeste = CPF_CLIENTE;
        cs.adicionarCliente(clienteCompleto, CNPJ_LOJA1);
        Boolean deletado = cs.removerCliente(cpfTeste, CNPJ_LOJA1);
        assertEquals(true, deletado);
    }


    // AlugueisService

    private String TEXTO_ESPERADO_ALUGUEL_CNPJLOJA_INVALIDO = "Aluguel sem cnpj.";
    private String TEXTO_ESPERADO_ALUGUEL_CPF_INVALIDO = "Aluguel sem cpf.";
    private String TEXTO_ESPERADO_ALUGUEL_HORAINICIO_INVALIDO = "Aluguel sem hora de inicio.";
    private String TEXTO_ESPERADO_ALUGUEL_HORATERMINO_INVALIDO = "Aluguel sem hora de fim.";
    private String TEXTO_ESPERADO_ALUGUEL_ID_INVALIDO = "Aluguel sem id";

    @Test
    public void validarAluguelCnpjLojaInvalido() {
        Aluguel aluguelCnpjVazio = aluguelCompleto;
        aluguelCnpjVazio.setCnpjLoja("");
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_CNPJLOJA_INVALIDO, aluguelCnpjVazio);

        Aluguel aluguelCnpjNulo = aluguelCompleto;
        aluguelCnpjNulo.setCnpjLoja(null);
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_CNPJLOJA_INVALIDO, aluguelCnpjNulo);
    }

    @Test
    public void validarAluguelCpfInvalido() {
        Aluguel aluguelCpfVazio = aluguelCompleto;
        aluguelCpfVazio.setCpfCliente("");
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_CPF_INVALIDO, aluguelCpfVazio);

        Aluguel aluguelCpfNulo = aluguelCompleto;
        aluguelCpfNulo.setCpfCliente(null);
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_CPF_INVALIDO, aluguelCpfNulo);
    }

    @Test
    public void validarAluguelHoraInicioInvalido() {
        Aluguel aluguelHoraInicioVazio = aluguelCompleto;
        aluguelHoraInicioVazio.setHorarioInicio("");
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_HORAINICIO_INVALIDO, aluguelHoraInicioVazio);

        Aluguel aluguelHoraInicioNulo = aluguelCompleto;
        aluguelHoraInicioNulo.setHorarioInicio(null);
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_HORAINICIO_INVALIDO, aluguelHoraInicioNulo);
    }

    @Test
    public void validarAluguelHoraTerminoInvalido() {
        Aluguel aluguelHoraTerminoVazio = aluguelCompleto;
        aluguelHoraTerminoVazio.setHorarioTermino("");
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_HORATERMINO_INVALIDO, aluguelHoraTerminoVazio);

        Aluguel aluguelHoraTerminoNulo = aluguelCompleto;
        aluguelHoraTerminoNulo.setHorarioTermino(null);
        validarAluguel(TEXTO_ESPERADO_ALUGUEL_HORATERMINO_INVALIDO, aluguelHoraTerminoNulo);
    }

    @Test
    public void validarAluguelDeletarByIdInvalido() {
        sInitForTest.cleanRepository();
        ls.adicionarLoja(lojaCompleta1);
        as.addAluguel(aluguelCompleto);
        validarDeletarAluguel(TEXTO_ESPERADO_ALUGUEL_ID_INVALIDO, aluguelCompleto);
        Static.cleanData();
    }

    @Test
    public void clean(){
        Static.cleanData();
    }
    
}

