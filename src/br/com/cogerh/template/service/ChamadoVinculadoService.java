package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.ChamadoVinculado;

public interface ChamadoVinculadoService {
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public ChamadoVinculado salvar(ChamadoVinculado obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public ChamadoVinculado alterar(ChamadoVinculado obj) throws Exception;
	
	/**
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void remover(ChamadoVinculado obj) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ChamadoVinculado buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<ChamadoVinculado> listar(String pesquisa) throws Exception;
	
	/**
	 * 
	 * @param idChamado
	 * @return
	 * @throws Exception
	 */
	public List<ChamadoVinculado> listarByChamadoOrigem(Integer idChamado) throws Exception;



}
