package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Acompanhamento;

public interface AcompanhamentoDAO extends GenericDAO<Acompanhamento, Integer>{
	
	public List<Acompanhamento> listar(String pesquisa); 

	public List<Acompanhamento> listarByChamado(Integer idChamado);

}
