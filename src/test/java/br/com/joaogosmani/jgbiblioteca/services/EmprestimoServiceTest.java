package br.com.joaogosmani.jgbiblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.joaogosmani.jgbiblioteca.enums.Reputacao;
import br.com.joaogosmani.jgbiblioteca.enums.Tipo;
import br.com.joaogosmani.jgbiblioteca.models.Autor;
import br.com.joaogosmani.jgbiblioteca.models.Cliente;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class EmprestimoServiceTest {
    
    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(emprestimo.getCliente(), cliente);
        assertEquals(emprestimo.getObras(), List.of(obra));
        assertEquals(emprestimo.getDataEmprestimo(), LocalDate.now());
        assertEquals(emprestimo.getDataDevolucao(), LocalDate.now().plusDays(3));
    }

}
