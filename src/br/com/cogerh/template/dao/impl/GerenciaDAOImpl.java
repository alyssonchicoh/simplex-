package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;






import br.com.cogerh.template.dao.GerenciaDAO;
import br.com.cogerh.template.dao.GrupoDAO;
import br.com.cogerh.template.model.Gerencia;
import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.util.HasValue;

@Transactional
@Repository
public class GerenciaDAOImpl extends GenericDAOImpl<Gerencia, Integer> implements GerenciaDAO{

	@Override
	public List<Gerencia> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();

		sb.append(" select ");
		sb.append("     ger ");
		sb.append(" from ");
		sb.append(" 	Gerencia ger ");
		
		final TypedQuery<Gerencia> query = entityManager.createQuery(sb.toString(), Gerencia.class);

		
		return query.getResultList();
	}

}
