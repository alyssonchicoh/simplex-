package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.SolucaoChamado;

public interface SolucaoChamadoDAO extends GenericDAO<SolucaoChamado, Integer>{
	
	public List<SolucaoChamado> listar() throws Exception;
	
	public List<SolucaoChamado> listarByChamado(Integer idChamado) throws Exception;
	
	public SolucaoChamado pesquisarByURL(String url) throws Exception;


}
