package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Aviso;
import br.com.cogerh.template.model.Permissao;

public interface AvisoDAO extends GenericDAO<Aviso, Integer> {
	
	public List<Aviso> listar(String pesquisa);

}
