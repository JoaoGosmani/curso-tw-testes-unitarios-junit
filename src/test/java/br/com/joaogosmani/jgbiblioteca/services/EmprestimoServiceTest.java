package br.com.joaogosmani.jgbiblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.joaogosmani.jgbiblioteca.enums.Reputacao;
import br.com.joaogosmani.jgbiblioteca.enums.Tipo;
import br.com.joaogosmani.jgbiblioteca.models.Autor;
import br.com.joaogosmani.jgbiblioteca.models.Cliente;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class EmprestimoServiceTest {

    private EmprestimoService emprestimoService;

    @BeforeEach
    private void setUp() {
        emprestimoService = new EmprestimoService();
    }
    
    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(cliente, emprestimo.getCliente());
        assertEquals(List.of(obra), emprestimo.getObras());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoRuimDeveRetornarUmEmprestimoComDevolucaoParaUmDia() {
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.RUIM);
        var autor = new Autor(1L, "Autor teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoRegularDeveRetornarUmEmprestimoComDevolucaoParaTresDia() {
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoBoaDeveRetornarUmEmprestimoComDevolucaoParaCincoDia() {
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.BOA);
        var autor = new Autor(1L, "Autor teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraNuloDeveLancarUmaExcecaoDoTipoillegalArgumentException() {
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var mensagemEsperada = "Obras não pode ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoillegalArgumentException() {
        var cliente = new Cliente(1L, "Cliente teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obras não pode ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoillegalArgumentException() {
        var autor = new Autor(1L, "Autor teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra teste", 100, Tipo.LIVRO, autor);
        var mensagemEsperada = "Cliente não pode ser nulo";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

}
