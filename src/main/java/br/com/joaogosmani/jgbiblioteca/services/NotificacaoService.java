package br.com.joaogosmani.jgbiblioteca.services;

import br.com.joaogosmani.jgbiblioteca.models.Emprestimo;

public interface NotificacaoService {
    
    void notificar(Emprestimo emprestimo);

}
