package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.SolucaoChamadoDAO;
import br.com.cogerh.template.model.ArquivoAnexado;
import br.com.cogerh.template.model.SolucaoChamado;

@Transactional
@Repository
public class SolucaoChamadoDAOImpl extends GenericDAOImpl<SolucaoChamado, Integer> implements SolucaoChamadoDAO{

	@Override
	public List<SolucaoChamado> listar() throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("     sol ");
		sb.append(" from ");
		sb.append(" 	SolucaoChamado sol ");
		sb.append(  cond );
		
		final TypedQuery<SolucaoChamado> query = entityManager.createQuery(sb.toString(), SolucaoChamado.class);
		return query.getResultList();	
	}

	@Override
	public List<SolucaoChamado> listarByChamado(Integer idChamado) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		cond.append("WHERE SOL.chamado.id = "+idChamado);
		
		sb.append(" select ");
		sb.append("     sol ");
		sb.append(" from ");
		sb.append(" 	SolucaoChamado sol ");
		sb.append(  cond );
		
		final TypedQuery<SolucaoChamado> query = entityManager.createQuery(sb.toString(), SolucaoChamado.class);
		return query.getResultList();		
	}

	@Override
	public SolucaoChamado pesquisarByURL(String url) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		cond.append(" where sol.hashUrl = '"+url +"'");
		sb.append(" select ");
		sb.append("     sol ");
		sb.append(" from ");
		sb.append(" 	SolucaoChamado sol ");
		sb.append(  cond );
		
		final TypedQuery<SolucaoChamado> query = entityManager.createQuery(sb.toString(), SolucaoChamado.class);
	return query.getSingleResult();
	}

}
