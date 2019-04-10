package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Gerencia;

public interface GerenciaService {

	
	public Gerencia  salvar(Gerencia gerencia) throws Exception;
	
	/**
	 * 
	 * @param Gerencia
	 * @return
	 * @throws Exception
	 */
	public Gerencia  alterar(Gerencia gerencia) throws Exception;
	
	/**
	 * 
	 * @param Gerencia
	 * @throws Exception
	 */
	public void remover(Gerencia gerencia) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Gerencia buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<Gerencia> listar(String pesquisa) throws Exception;
	
	

}
