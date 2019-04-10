package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Tecnico;

public interface TecnicoDAO extends GenericDAO<Tecnico, Integer>{
	
	public List<Tecnico> listar(String pesquisa); 
	
	public Tecnico buscarPorIdUsuario(Integer idUsuario) throws Exception;



}
