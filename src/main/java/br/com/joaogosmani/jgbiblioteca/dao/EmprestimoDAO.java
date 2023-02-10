package br.com.joaogosmani.jgbiblioteca.dao;

import java.util.List;

import br.com.joaogosmani.jgbiblioteca.models.Emprestimo;

public interface EmprestimoDAO {
    
    List<Emprestimo> buscarTodos();

}
