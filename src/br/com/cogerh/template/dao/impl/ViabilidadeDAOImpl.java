package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.GenericDAO;
import br.com.cogerh.template.dao.ViabilidadeDAO;
import br.com.cogerh.template.model.Viabilidade;

@Transactional
@Repository
public class ViabilidadeDAOImpl  extends GenericDAOImpl<Viabilidade, Integer> implements ViabilidadeDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Viabilidade> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("       via ");
		sb.append(" from ");
		sb.append(" 	Viabilidade via ");
		
		final TypedQuery<Viabilidade> query = entityManager.createQuery(sb.toString(), Viabilidade.class);
		
		return query.getResultList();
	}

	

}
