package br.com.joaogosmani.jgbiblioteca.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AutorTest {
    
    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue() {
        var autor = new Autor();

        var estaVivo = autor.estaVivo();

        assertTrue(estaVivo);
    }

    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoPreenchidaDeveRetornarFalse() {
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        var estaVivo = autor.estaVivo();

        assertFalse(estaVivo);
    }

}
