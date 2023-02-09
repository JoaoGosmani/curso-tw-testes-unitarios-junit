package br.com.joaogosmani.jgbiblioteca.services;

import java.time.LocalDate;
import java.util.List;

import br.com.joaogosmani.jgbiblioteca.enums.Reputacao;
import br.com.joaogosmani.jgbiblioteca.models.Cliente;
import br.com.joaogosmani.jgbiblioteca.models.Emprestimo;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class EmprestimoService {
    
    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var diasParaDevolucao = 3;
        if (cliente.getReputacao() == Reputacao.RUIM) {
            diasParaDevolucao = 1;
        } else if (cliente.getReputacao() == Reputacao.REGULAR) {
            diasParaDevolucao = 3;
        } else {
            diasParaDevolucao = 5;
        }
        var dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        emprestimo.setCliente(cliente);
        emprestimo.setObras(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimo;
    }

}
