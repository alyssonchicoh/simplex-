package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Tecnico;

public interface TecnicoService {
	
	public Tecnico salvar(Tecnico obj) throws Exception;
	public Tecnico alterar(Tecnico obj) throws Exception;
	public void remover(Tecnico obj) throws Exception;
	public Tecnico buscarPorId(Integer id) throws Exception;
	public Tecnico buscarPorIdUsuario(Integer idUsuario) throws Exception;

	public List<Tecnico> listar(String pesquisa) throws Exception;

}
