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
	 * Método utilizado para listar os chamados com base em um Técnico Passado como Parametro
	 * @param  Id do técnico para a consulta de chamados
	 * @return Lista de Chamados com o técnico informado
	 * @throws Exception
	 */
	public List<Chamado> listarByTecnico(Integer id) throws Exception;
	
	/**
	 * Método utilizado para listar todos os chamados solicitados pelo usuário
	 * @param idUsuario do usuário a qual se quer saber seus chamados solicitados
	 * @return Lista com todos os chamados abertos pelo usuário do parametro
	 * @throws Exception
	 */
	public List<Chamado> listarChamadosSolicitadoByUsuario(Integer idUsuario) throws Exception;
	
	/**
	 * Método utilizado para listar todos os Chamados ordenado pela atual posição na fila de um técnico
	 * @param idTecnico do Técnico a qual se deseja listar os chamados
	 * @return Chamados ordenados pela posição na fila de um técnico
	 * @throws Exception
	 */
	public List<Chamado> listarChamadoOrdenadoPorPosicaoFilaByTecnico(Integer idTecnico) throws Exception;
	
	/**
	 * Método utilizado para atualizar todas as posições da fila de chamado, com base em uma posicao de um  
	 * novo chamado de uma determinada lista de chamados de um técnico
	 * @param posicao -> Posição nova do chamado
	 * @param idUsuarioTecnico -> A qual se deseja atualizar as posições de chamados
	 * @throws Exception
	 */
	public void atualizarPosicaoFilaChamados(Integer posicao,Integer idUsuarioTecnico) throws Exception;
	
	/**
	 * Lista todos os chamados Solucionados de um determinado solicitante 
	 * @param idUsuario que será usado para parametro do solicitante
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