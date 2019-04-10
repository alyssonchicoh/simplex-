package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;

public interface CategoriaDAO extends GenericDAO<Categoria, Integer>{
	
	public List<Categoria> listar(String pesquisa); 
	
	public Categoria buscarCategoriaByNome(String pesquisa);

}
