package br.com.joaogosmani.jgbiblioteca.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutorTest {
    
    @Test
    public void testaMetodoEstaVivo() {
        var autor = new Autor();

        var estaVivo = autor.estaVivo();

        Assertions.assertEquals(true, estaVivo);
    }

}
