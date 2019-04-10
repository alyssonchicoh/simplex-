package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Aviso;

public interface AvisoService {
	/**
	 * 
	 * @param Aviso
	 * @return
	 * @throws Exception
	 */
	public Aviso  salvar(Aviso aviso) throws Exception;
	
	/**
	 * 
	 * @param Aviso
	 * @return
	 * @throws Exception
	 */
	public Aviso  alterar(Aviso aviso) throws Exception;
	
	/**
	 * 
	 * @param Aviso
	 * @throws Exception
	 */
	public void remover(Aviso aviso) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Aviso buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<Aviso> listar(String pesquisa) throws Exception;
	
	
}
