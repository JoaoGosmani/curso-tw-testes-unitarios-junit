package br.com.joaogosmani.jgbiblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.joaogosmani.jgbiblioteca.builders.ClienteBuilder;
import br.com.joaogosmani.jgbiblioteca.builders.EmprestimoBuilder;
import br.com.joaogosmani.jgbiblioteca.builders.ObraBuilder;
import br.com.joaogosmani.jgbiblioteca.dao.EmprestimoDAO;
import br.com.joaogosmani.jgbiblioteca.enums.Reputacao;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

    @Mock
    private EmprestimoDAO emprestimoDAO;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        var cliente = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(cliente, emprestimo.getCliente());
        assertEquals(List.of(obra), emprestimo.getObras());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoRuimDeveRetornarUmEmprestimoComDevolucaoParaUmDia() {
        var cliente = ClienteBuilder.builder().reputacao(Reputacao.RUIM).build();
        var obra = ObraBuilder.builder().build();

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoRegularDeveRetornarUmEmprestimoComDevolucaoParaTresDia() {
        var cliente = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoBoaDeveRetornarUmEmprestimoComDevolucaoParaCincoDia() {
        var cliente = ClienteBuilder.builder().reputacao(Reputacao.BOA).build();
        var obra = ObraBuilder.builder().build();

        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraNuloDeveLancarUmaExcecaoDoTipoillegalArgumentException() {
        var cliente = ClienteBuilder.builder().build();
        var mensagemEsperada = "Obras não pode ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoillegalArgumentException() {
        var cliente = ClienteBuilder.builder().build();
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obras não pode ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoillegalArgumentException() {
        var obra = ObraBuilder.builder().build();
        var mensagemEsperada = "Cliente não pode ser nulo";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNotificarAtrasoForChamadoDeveRetornarONumeroDeNotificacoes() {
        var emprestimos = List.of(
            EmprestimoBuilder.builder().build(),
            EmprestimoBuilder.builder().dataDevolucao(LocalDate.now().minusDays(1)).build()
        );

        when(emprestimoDAO.buscarTodos()).thenReturn(emprestimos);

        emprestimoService.notificarAtrasos();

        verify(notificacaoService).notificar(emprestimos.get(1));
    }

}
