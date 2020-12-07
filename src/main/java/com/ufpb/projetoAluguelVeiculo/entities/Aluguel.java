package com.ufpb.projetoAluguelVeiculo.entities;

import com.ufpb.projetoAluguelVeiculo.utils.Static;
import org.joda.time.DateTime;

public class Aluguel {
    private String id;
    private String cnpjLoja;
    private Veiculo veiculo;
    private String cpfCliente;
    private String horarioInicio;
    private String horarioTermino;
    private Double valor;

    public Aluguel(String cnpjLoja, Veiculo veiculo, String cpfCliente, String horarioInicio, String horarioTermino) {
        this.id = Static.encode(cpfCliente + horarioInicio);
        this.cnpjLoja = cnpjLoja;
        this.veiculo = veiculo;
        this.cpfCliente = cpfCliente;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        setValor();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCnpjLoja() {
        return cnpjLoja;
    }

    public void setCnpjLoja(String loja) {
        this.cnpjLoja = loja;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(String horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public Double getvalor() {
        return valor;
    }

    public void setValor() {
        DateTime d1 = Static.stringParseData(horarioInicio);
        DateTime d2 = Static.stringParseData(horarioTermino);
        int minutosEntreDatas = Static.minutosEntreDatas(d1, d2);
        int horasParaPagar = Static.numHorasParaPagamento(minutosEntreDatas);
        Double valorHoraVeiculo = this.veiculo.getModeloVeiculo().getValorHora();
        this.valor = horasParaPagar * valorHoraVeiculo;
    }
}