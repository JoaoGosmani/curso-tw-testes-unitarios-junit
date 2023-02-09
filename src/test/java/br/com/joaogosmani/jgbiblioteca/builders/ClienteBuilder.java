package br.com.joaogosmani.jgbiblioteca.builders;

import java.time.LocalDate;

import br.com.joaogosmani.jgbiblioteca.enums.Reputacao;
import br.com.joaogosmani.jgbiblioteca.models.Cliente;

public class ClienteBuilder {
    
    private Cliente cliente;

    public static ClienteBuilder builder() {
        var builder = new ClienteBuilder();

        var cliente =  new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        builder.cliente = cliente;

        return builder;
    }

    public ClienteBuilder reputacao(Reputacao reputacao) {
        cliente.setReputacao(reputacao);

        return this;
    }

    public Cliente build() {
        return cliente;
    }

}
