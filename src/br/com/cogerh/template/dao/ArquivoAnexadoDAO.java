package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.ArquivoAnexado;

public interface ArquivoAnexadoDAO extends GenericDAO<ArquivoAnexado, Integer>{
	public List<ArquivoAnexado> listar(String pesquisa); 
	
	public List<ArquivoAnexado> buscarArquivoByChamado(Integer idChamado);

}
