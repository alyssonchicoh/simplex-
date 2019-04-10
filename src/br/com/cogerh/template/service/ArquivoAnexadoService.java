package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.ArquivoAnexado;

public interface ArquivoAnexadoService {
	
	public ArquivoAnexado  salvar(ArquivoAnexado obj) throws Exception;
	
	
	public ArquivoAnexado  alterar(ArquivoAnexado obj) throws Exception;
	
	
	public void remover(ArquivoAnexado obj) throws Exception;
	
	public ArquivoAnexado buscarPorId(Integer id) throws Exception;
	
	
	public List<ArquivoAnexado> listar(String pesquisa) throws Exception;
	
	
	public List<ArquivoAnexado>  buscarByChamado(Integer idChamado) throws Exception;

}
