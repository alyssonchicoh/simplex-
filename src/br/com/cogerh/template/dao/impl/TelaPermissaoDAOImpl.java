package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.PermissaoDAO;
import br.com.cogerh.template.dao.TelaPermissaoDAO;
import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.model.TelaPermissao;
import br.com.cogerh.template.util.HasValue;

@Transactional
@Repository
public class TelaPermissaoDAOImpl extends GenericDAOImpl<TelaPermissao, Integer> implements TelaPermissaoDAO
{
	public List<TelaPermissao> listar(String pesquisa) 
	{
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();

	

		sb.append(" select ");
		sb.append("		tp ");
		sb.append(" from ");
		sb.append(" 	TelaPermissao tp ");
		sb.append(  cond );
	
		final TypedQuery<TelaPermissao> query = entityManager.createQuery(sb.toString(), TelaPermissao.class);

		return query.getResultList();
	}

	
}