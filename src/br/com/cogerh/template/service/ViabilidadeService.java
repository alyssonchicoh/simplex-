package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.model.Viabilidade;

public interface ViabilidadeService {

	public Viabilidade salvar(Viabilidade obj) throws Exception;
	public Viabilidade alterar(Viabilidade obj) throws Exception;
	public void remover(Viabilidade obj) throws Exception;
	public Viabilidade buscarPorId(Integer id) throws Exception;
	public List<Viabilidade> listar(String pesquisa) throws Exception;
}
