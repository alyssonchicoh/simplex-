package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.OrdemServico;

public interface OrdemServicoDAO extends GenericDAO<OrdemServico, Integer>{
	
	public List<OrdemServico> listar(String pesquisa);
}
