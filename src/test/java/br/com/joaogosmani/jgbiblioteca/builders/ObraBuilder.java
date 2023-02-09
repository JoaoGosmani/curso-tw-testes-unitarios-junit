package br.com.joaogosmani.jgbiblioteca.builders;

import br.com.joaogosmani.jgbiblioteca.enums.Tipo;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class ObraBuilder {
    
    private Obra obra;

    public static ObraBuilder builder() {
        var builder = new ObraBuilder();

        var autor =  AutorBuilder.builder().build();
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);
        builder.obra = obra;

        return builder;
    }

    public Obra build() {
        return obra;
    }

}
