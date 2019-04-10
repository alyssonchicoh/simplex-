package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.model.Categoria;

public interface AcompanhamentoService {
	/**
	 * 
	 * @param Acompanhamento
	 * @return
	 * @throws Exception
	 */
	public Acompanhamento salvar(Acompanhamento acompanhamento) throws Exception;
	
	/**
	 * 
	 * @param Acompnhamento
	 * @return
	 * @throws Exception
	 */
	public Acompanhamento  alterar(Acompanhamento acompanhamento) throws Exception;
	
	/**
	 * 
	 * @param Acompanhamento
	 * @throws Exception
	 */
	public void remover(Acompanhamento acompanhamento) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Acompanhamento buscarPorId(Integer id) throws Exception;
	
	/**
	 * 
	 * @param pesquisa
	 * @return
	 * @throws Exception
	 */
	public List<Acompanhamento> listar(String pesquisa) throws Exception;
	
	/**
	 * 
	 * @param idChamado
	 * @return
	 * @throws Exception
	 */
	public List<Acompanhamento> listarByChamado(Integer idChamado) throws Exception;

}
