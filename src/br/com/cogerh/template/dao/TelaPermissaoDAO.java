package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.TelaPermissao;

public interface TelaPermissaoDAO extends GenericDAO<TelaPermissao, Integer>
{
	public List<TelaPermissao> listar(String pesquisa); 
	
}