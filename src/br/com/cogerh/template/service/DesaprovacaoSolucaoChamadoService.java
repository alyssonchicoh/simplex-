package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.DesaprovacaoSolucaoChamado;


public interface DesaprovacaoSolucaoChamadoService {
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public DesaprovacaoSolucaoChamado salvar(DesaprovacaoSolucaoChamado obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public DesaprovacaoSolucaoChamado alterar(DesaprovacaoSolucaoChamado obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void remover(DesaprovacaoSolucaoChamado obj) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DesaprovacaoSolucaoChamado buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<DesaprovacaoSolucaoChamado> listar(String pesquisa) throws Exception;
	
	
}
