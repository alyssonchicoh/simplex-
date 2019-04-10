package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.model.Viabilidade;

public interface ViabilidadeDAO  extends GenericDAO<Viabilidade, Integer>{
	public List<Viabilidade> listar(String pesquisa); 
	
}


