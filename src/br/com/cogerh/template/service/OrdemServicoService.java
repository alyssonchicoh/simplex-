package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.OrdemServico;

public interface OrdemServicoService {

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public OrdemServico salvar(OrdemServico obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public OrdemServico alterar(OrdemServico obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void remover(OrdemServico obj) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OrdemServico buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<OrdemServico> listar(String pesquisa) throws Exception;
	
	/**
	 * LISTA TODAS AS ORDENS DE SERVIÇOS DE UM CHAMADO, COM BASE EM SEU ID
	 * @param idChamado
	 * @return
	 * @throws Exception
	 */
	public List<OrdemServico> listarByChamadoOrigem(Integer idChamado) throws Exception;
}
