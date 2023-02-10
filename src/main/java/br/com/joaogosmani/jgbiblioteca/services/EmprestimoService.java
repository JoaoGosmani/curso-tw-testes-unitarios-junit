package br.com.joaogosmani.jgbiblioteca.services;

import java.time.LocalDate;
import java.util.List;

import br.com.joaogosmani.jgbiblioteca.dao.EmprestimoDAO;
import br.com.joaogosmani.jgbiblioteca.models.Cliente;
import br.com.joaogosmani.jgbiblioteca.models.Emprestimo;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class EmprestimoService {

    private EmprestimoDAO emprestimoDAO;

    public EmprestimoService(EmprestimoDAO emprestimoDAO) {
        this.emprestimoDAO = emprestimoDAO;
    }
    
    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        if (obras == null || obras.isEmpty()) {
            throw new IllegalArgumentException("Obras não pode ser nulo e nem vazio");
        }

        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();
        var dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        emprestimo.setCliente(cliente);
        emprestimo.setObras(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimo;
    }

    public int notificarAtrasos() {
        var notificacoes = 0;
        var hoje = LocalDate.now();

        var emprestimos = emprestimoDAO.buscarTodos();

        for (Emprestimo emprestimo : emprestimos) {
            var estaAtrasado = emprestimo.getDataDevolucao().isBefore(hoje);
            if (estaAtrasado) {
                notificacoes++;
            }
        }

        return notificacoes;
    }

}
