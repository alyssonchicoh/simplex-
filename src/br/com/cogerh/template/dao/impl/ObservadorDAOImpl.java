package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.NotificacaoDAO;
import br.com.cogerh.template.dao.ObservadorDAO;
import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Observador;

@Transactional
@Repository
public class ObservadorDAOImpl extends GenericDAOImpl<Observador, Integer> implements ObservadorDAO{

	@Override
	public List<Observador> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("		obs ");
		sb.append(" from ");
		sb.append(" 	Observador obs ");
		sb.append(  cond );
		

		final TypedQuery<Observador> query = entityManager.createQuery(sb.toString(), Observador.class);

		return query.getResultList();
		
	}

	@Override
	public Observador listarByUser(Integer idUsuario) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
			cond.append(" where obs.usuario.id = "+idUsuario);
			
		sb.append(" select ");
		sb.append("		obs ");
		sb.append(" from ");
		sb.append(" 	Observador obs ");
		sb.append(  cond );
		

		final TypedQuery<Observador> query = entityManager.createQuery(sb.toString(), Observador.class);

		return query.getSingleResult();
	}

}
