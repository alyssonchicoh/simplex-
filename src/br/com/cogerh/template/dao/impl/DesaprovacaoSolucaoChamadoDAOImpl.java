package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.DesaprovacaoSolucaoChamadoDAO;
import br.com.cogerh.template.dao.SolucaoChamadoDAO;
import br.com.cogerh.template.model.ArquivoAnexado;
import br.com.cogerh.template.model.DesaprovacaoSolucaoChamado;
import br.com.cogerh.template.model.SolucaoChamado;

@Transactional
@Repository
public class DesaprovacaoSolucaoChamadoDAOImpl extends GenericDAOImpl<DesaprovacaoSolucaoChamado, Integer> implements DesaprovacaoSolucaoChamadoDAO{

	@Override
	public List<DesaprovacaoSolucaoChamado> listar() throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("     des ");
		sb.append(" from ");
		sb.append(" 	DesaprovacaoSolucaoChamado des ");
		sb.append(  cond );
		
		final TypedQuery<DesaprovacaoSolucaoChamado> query = entityManager.createQuery(sb.toString(), DesaprovacaoSolucaoChamado.class);
		return query.getResultList();	
	}

	

}
