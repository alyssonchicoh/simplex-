package br.com.cogerh.template.dao;

import java.util.Date;
import java.util.List;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.util.FiltroChamadoDadosGerais;

public interface ChamadoDAO extends GenericDAO<Chamado, Integer>
{
	/**
	 * 
	 * @param pesquisa
	 * @return
	 */
	public List<Chamado> listar(String pesquisa); 
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 */
	public Chamado buscarChamadoByNome(String pesquisa);
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listarSemTecnicoAlocado() throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listarByTecnico(Integer id) throws Exception;
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listarChamadosSolicitadoByUsuario(Integer idUsuario) throws Exception;
	
	/**
	 * 
	 * @param idTecnico
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listarChamadoOrdenadoPorPosicaoFilaByTecnico(Integer idTecnico) throws Exception;

	/**
	 * Lista todos os chamados de uma determinada pessoa com base em um status especifico
	 * @param idSolicitante
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listarChamadoBySolicitanteAndStatus(Integer idSolicitante,Integer status) throws Exception;

	public List<Chamado> listarByFiltros(FiltroChamadoDadosGerais filtroChamadoDadosGerais) throws Exception;

}