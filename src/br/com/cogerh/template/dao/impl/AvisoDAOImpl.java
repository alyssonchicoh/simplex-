package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.AvisoDAO;
import br.com.cogerh.template.dao.CategoriaDAO;
import br.com.cogerh.template.model.Aviso;
import br.com.cogerh.template.model.Categoria;

@Transactional
@Repository
public class AvisoDAOImpl extends GenericDAOImpl<Aviso, Integer> implements AvisoDAO{

	@Override
	public List<Aviso> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("     avs ");
		sb.append(" from ");
		sb.append(" 	Aviso avs ");
		sb.append(  cond );
		
		final TypedQuery<Aviso> query = entityManager.createQuery(sb.toString(), Aviso.class);

		return query.getResultList();
				
	}

}
