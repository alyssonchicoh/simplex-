package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.TecnicoDAO;
import br.com.cogerh.template.model.Tecnico;

@Transactional
@Repository
public class TecnicoDAOImpl extends GenericDAOImpl<Tecnico, Integer> implements TecnicoDAO{

	@Override
	public List<Tecnico> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();

		
		sb.append(" select ");
		sb.append("		tec ");
		sb.append(" from ");
		sb.append(" 	Tecnico tec ");
		sb.append(  cond );

		final TypedQuery<Tecnico> query = entityManager.createQuery(sb.toString(), Tecnico.class);

		return query.getResultList();
	}

	@Override
	public Tecnico buscarPorIdUsuario(Integer idUsuario) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		cond.append(" where tec.usuario.id = "+idUsuario);
		
		sb.append(" select ");
		sb.append("		tec ");
		sb.append(" from ");
		sb.append(" 	Tecnico tec ");
		sb.append(  cond );

		final TypedQuery<Tecnico> query = entityManager.createQuery(sb.toString(), Tecnico.class);

		return query.getSingleResult();
		
		}

}
