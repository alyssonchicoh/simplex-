package br.com.cogerh.template.service;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.cogerh.template.model.Observador;

public interface ObservadorService {

	public Observador salvar(Observador obj) throws Exception;
	
	public Observador alterar(Observador obj) throws Exception;
	
	public void remover(Observador obj) throws Exception;
	
	public Observador buscarPorId(Integer id) throws Exception;
	
	public List<Observador> listar(String pesquisa) throws Exception;
	
	public Observador listarByUser(Integer idUsuario) throws Exception;
}
