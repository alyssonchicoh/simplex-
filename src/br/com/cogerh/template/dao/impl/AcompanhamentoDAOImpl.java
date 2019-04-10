package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.AcompanhamentoDAO;
import br.com.cogerh.template.model.Acompanhamento;


@Transactional
@Repository
public class AcompanhamentoDAOImpl extends GenericDAOImpl<Acompanhamento, Integer> implements AcompanhamentoDAO{

	@Override
	public List<Acompanhamento> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();

		

		sb.append(" select ");
		sb.append("     aco ");
		sb.append(" from ");
		sb.append(" 	Acompanhamento aco ");
	

		final TypedQuery<Acompanhamento> query = entityManager.createQuery(sb.toString(), Acompanhamento.class);

		

		return query.getResultList();
	}

	@Override
	public List<Acompanhamento> listarByChamado(Integer idChamado) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		cond.append("WHERE aco.chamado.id = "+idChamado);
		

		sb.append(" select ");
		sb.append("     aco ");
		sb.append(" from ");
		sb.append(" 	Acompanhamento aco ");
		sb.append(cond);
	

		final TypedQuery<Acompanhamento> query = entityManager.createQuery(sb.toString(), Acompanhamento.class);

		

		return query.getResultList();	}

}
