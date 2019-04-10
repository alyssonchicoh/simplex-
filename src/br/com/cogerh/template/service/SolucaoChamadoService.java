package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.SolucaoChamado;


public interface SolucaoChamadoService {
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public SolucaoChamado salvar(SolucaoChamado obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public SolucaoChamado alterar(SolucaoChamado obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void remover(SolucaoChamado obj) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SolucaoChamado buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<SolucaoChamado> listar(String pesquisa) throws Exception;
	
	/**
	 * Lista as Soluções de um determinado Chamado
	 * @param idChamado
	 * @return
	 * @throws Exception
	 */
	public List<SolucaoChamado> listarByChamado(Integer idChamado) throws Exception;
	
	/**
	 * PESQUISA A SOLUÇÃO DE UM CHAMADO PELO CÓDIGO DA URL
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public SolucaoChamado pesquisarByURL(String url) throws Exception;

}
