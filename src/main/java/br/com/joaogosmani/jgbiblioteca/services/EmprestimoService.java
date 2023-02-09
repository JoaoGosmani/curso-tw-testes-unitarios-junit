package br.com.joaogosmani.jgbiblioteca.services;

import java.time.LocalDate;
import java.util.List;

import br.com.joaogosmani.jgbiblioteca.models.Cliente;
import br.com.joaogosmani.jgbiblioteca.models.Emprestimo;
import br.com.joaogosmani.jgbiblioteca.models.Obra;

public class EmprestimoService {
    
    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
        var emprestimo = new Emprestimo();

        emprestimo.setCliente(cliente);
        emprestimo.setObras(obras);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(3));

        return emprestimo;
    }

}
