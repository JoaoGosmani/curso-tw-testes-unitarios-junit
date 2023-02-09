package br.com.joaogosmani.jgbiblioteca.services;

import java.time.LocalDate;
import java.util.List;

import br.com.joaogosmani.jgbiblioteca.models.Cliente;
import br.com.joaogosmani.jgbiblioteca.models.Emprestimo;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class EmprestimoService {

    private static final int DIAS_PARA_DEVOLUCAO = 3;
    
    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var dataDevolucao = dataEmprestimo.plusDays(DIAS_PARA_DEVOLUCAO);

        emprestimo.setCliente(cliente);
        emprestimo.setObras(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimo;
    }

}
