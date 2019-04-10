package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.ObservadorDAO;
import br.com.cogerh.template.dao.OrdemServicoDAO;
import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.model.Observador;
import br.com.cogerh.template.model.OrdemServico;

@Transactional
@Repository
public class OrdemServicoDAOImpl extends GenericDAOImpl<OrdemServico, Integer> implements OrdemServicoDAO{
	
	@Override
	public List<OrdemServico> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("       os ");
		sb.append(" from ");
		sb.append(" 	 OrdemServico os ");
	

		final TypedQuery<OrdemServico> query = entityManager.createQuery(sb.toString(), OrdemServico.class);

		

		return query.getResultList();
	
	}
}
