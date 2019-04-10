package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Observador;


public interface ObservadorDAO extends GenericDAO<Observador, Integer>{
	
	public List<Observador> listar(String pesquisa);

	public Observador listarByUser(Integer idUsuario) throws Exception;

}
