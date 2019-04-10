package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.util.FiltroChamadoDadosGerais;

public interface ChamadoService
{
	/**
	 * 
	 * @param chamado
	 * @return
	 * @throws Exception
	 */
	public Chamado salvar(Chamado chamado) throws Exception;
	
	/**
	 * 
	 * @param chamado
	 * @return
	 * @throws Exception
	 */
	public Chamado  alterar(Chamado chamado) throws Exception;
	
	/**
	 * 
	 * @param Chamado
	 * @throws Exception
	 */
	public void remover(Chamado Chamado) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Chamado buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listar(String pesquisa) throws Exception;
	
	/**
	 * BUSCA UM CHAMADO PELO SEU TITULO
	 * @param titulo
	 * @return
	 * @throws Exception
	 */
	public Chamado buscarByTitutlo(String titulo) throws Exception;

	/**
	 * @return Lista de chamados sem um tecnico alocado 
	 * @throws Exception
	 */
	public List<Chamado> listarSemTecnicoAlocado() throws Exception;
	
	/**
	 * M�todo utilizado para listar os chamados com base em um T�cnico Passado como Parametro
	 * @param  Id do t�cnico para a consulta de chamados
	 * @return Lista de Chamados com o t�cnico informado
	 * @throws Exception
	 */
	public List<Chamado> listarByTecnico(Integer id) throws Exception;
	
	/**
	 * M�todo utilizado para listar todos os chamados solicitados pelo usu�rio
	 * @param idUsuario do usu�rio a qual se quer saber seus chamados solicitados
	 * @return Lista com todos os chamados abertos pelo usu�rio do parametro
	 * @throws Exception
	 */
	public List<Chamado> listarChamadosSolicitadoByUsuario(Integer idUsuario) throws Exception;
	
	/**
	 * M�todo utilizado para listar todos os Chamados ordenado pela atual posi��o na fila de um t�cnico
	 * @param idTecnico do T�cnico a qual se deseja listar os chamados
	 * @return Chamados ordenados pela posi��o na fila de um t�cnico
	 * @throws Exception
	 */
	public List<Chamado> listarChamadoOrdenadoPorPosicaoFilaByTecnico(Integer idTecnico) throws Exception;
	
	/**
	 * M�todo utilizado para atualizar todas as posi��es da fila de chamado, com base em uma posicao de um  
	 * novo chamado de uma determinada lista de chamados de um t�cnico
	 * @param posicao -> Posi��o nova do chamado
	 * @param idUsuarioTecnico -> A qual se deseja atualizar as posi��es de chamados
	 * @throws Exception
	 */
	public void atualizarPosicaoFilaChamados(Integer posicao,Integer idUsuarioTecnico) throws Exception;
	
	/**
	 * Lista todos os chamados Solucionados de um determinado solicitante 
	 * @param idUsuario que ser� usado para parametro do solicitante
	 * @return lista de chamados solucionados dos solicitante
	 * @throws Exception
	 */
	public List<Chamado> listarChamadoBySolicitanteAndStatusSolucionado(Integer idUsuario) throws Exception;
	
	/**
	 * VERIFICA SE O TECNICO INFORMADO PERTENCE A LISTA DE TECNICOS DO CHAMADO
	 * @param chamado CHAMADO A QUAL SE DESEJA VERIFICAR OS TECNICOS
	 * @param tecnico TECNICO A QUAL SE DESEJA VERIFICAR SE ENCONTRA-SE ALOCADO NO CHAMADO
	 * @return boolean INDICANDO A FLAG
	 */
	public boolean verificaTecnicoAlocadoChamado(Chamado chamado,Tecnico tecnico);
	
	/**
	 * LISTA TODOS OS CHAMADOS COM BASE NOS AGRUPAMENTOS DE FILTROS PASSADOS PELOS PARAMETROS DA CONSULTA
	 * @param filtroChamadoDadosGerais
	 * @return
	 * @throws Exception
	 */
	public List<Chamado> listarByFiltros(FiltroChamadoDadosGerais filtroChamadoDadosGerais) throws Exception;

}