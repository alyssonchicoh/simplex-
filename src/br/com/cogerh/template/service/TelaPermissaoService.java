package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.TelaPermissao;

public interface TelaPermissaoService
{
	public TelaPermissao salvarTelaPermissao(TelaPermissao grupo) throws Exception;
	public TelaPermissao alterarTelaPermissao(TelaPermissao grupo) throws Exception;
	public void removerTelaPermissao(TelaPermissao grupo) throws Exception;
	public TelaPermissao buscarPorId(Integer id) throws Exception;
	public List<TelaPermissao> listar(String pesquisa) throws Exception;
	


}