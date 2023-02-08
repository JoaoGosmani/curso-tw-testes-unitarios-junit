package br.com.joaogosmani.jgbiblioteca.models;

import java.time.LocalDate;

public class Autor extends Pessoa {
    
    private LocalDate dataFalecimento;

    public Autor() {}

    public Autor(String nome, LocalDate dataNascimento, LocalDate dataFalecimento) {
        super(nome, dataNascimento);
        this.dataFalecimento = dataFalecimento;
    }

    public Autor(Long id, String nome, LocalDate dataNascimento, LocalDate dataFalecimento) {
        super(id, nome, dataNascimento);
        this.dataFalecimento = dataFalecimento;
    }

    public LocalDate getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(LocalDate dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public boolean estaVivo() {
        return dataFalecimento == null;
    }

}
