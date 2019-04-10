package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.DesaprovacaoSolucaoChamado;
import br.com.cogerh.template.model.SolucaoChamado;

public interface DesaprovacaoSolucaoChamadoDAO extends GenericDAO<DesaprovacaoSolucaoChamado, Integer>{
	
	public List<DesaprovacaoSolucaoChamado> listar() throws Exception;
}
