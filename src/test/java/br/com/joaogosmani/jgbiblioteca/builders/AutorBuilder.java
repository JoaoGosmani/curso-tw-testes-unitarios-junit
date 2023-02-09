package br.com.joaogosmani.jgbiblioteca.builders;

import java.time.LocalDate;

import br.com.joaogosmani.jgbiblioteca.models.Autor;

public class AutorBuilder {
    
    private Autor autor;

    public static AutorBuilder builder() {
        var builder = new AutorBuilder();

        var autor =  new Autor(1L, "Autor teste", LocalDate.now(), null);
        builder.autor = autor;

        return builder;
    }

    public Autor build() {
        return autor;
    }

}
