package br.com.joaogosmani.jgbiblioteca.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AutorTest {
    
    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue() {
        var autor = new Autor();

        var estaVivo = autor.estaVivo();

        assertEquals(true, estaVivo);
    }

    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoPreenchidaDeveRetornarFalse() {
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        var estaVivo = autor.estaVivo();

        assertEquals(false, estaVivo);
    }

}
