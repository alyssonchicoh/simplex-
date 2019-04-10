package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.ArquivoAnexadoDAO;
import br.com.cogerh.template.model.ArquivoAnexado;
import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.util.HasValue;

@Transactional
@Repository
public class ArquivoAnexadoDAOImpl extends GenericDAOImpl<ArquivoAnexado, Integer> implements ArquivoAnexadoDAO{

	@Override
	public List<ArquivoAnexado> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("     arq ");
		sb.append(" from ");
		sb.append(" 	ArquivoAnexado arq ");
		sb.append(  cond );
		
		final TypedQuery<ArquivoAnexado> query = entityManager.createQuery(sb.toString(), ArquivoAnexado.class);
		return query.getResultList();	
		
	}

	@Override
	public List<ArquivoAnexado> buscarArquivoByChamado(Integer idChamado) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		cond.append(" where arq.chamado.id = "+idChamado);
		
		sb.append(" select ");
		sb.append("     arq ");
		sb.append(" from ");
		sb.append(" 	ArquivoAnexado arq ");
		sb.append(  cond );
		
		final TypedQuery<ArquivoAnexado> query = entityManager.createQuery(sb.toString(), ArquivoAnexado.class);
		return query.getResultList();		}

}
