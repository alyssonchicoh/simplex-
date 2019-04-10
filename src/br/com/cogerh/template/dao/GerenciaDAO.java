package br.com.cogerh.template.dao;


import java.util.List;

import br.com.cogerh.template.model.Gerencia;

public interface GerenciaDAO extends GenericDAO<Gerencia, Integer>{
	
	public List<Gerencia> listar(String pesquisa); 


}
