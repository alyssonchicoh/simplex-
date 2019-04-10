package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;

public interface CategoriaService
{
	/**
	 * 
	 * @param Categoria
	 * @return
	 * @throws Exception
	 */
	public Categoria  salvar(Categoria categoria) throws Exception;
	
	/**
	 * 
	 * @param Categoria
	 * @return
	 * @throws Exception
	 */
	public Categoria  alterar(Categoria categoria) throws Exception;
	
	/**
	 * 
	 * @param Categoria
	 * @throws Exception
	 */
	public void remover(Categoria categoria) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Categoria buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<Categoria> listar(String pesquisa) throws Exception;
	
	/**
	 * 
	 * @param titulo
	 * @return
	 * @throws Exception
	 */
	public Categoria buscarByNome(String pesquisa) throws Exception;

}