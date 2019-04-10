package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.NotificacaoDAO;
import br.com.cogerh.template.dao.PermissaoDAO;
import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.util.HasValue;

@Transactional
@Repository
public class NotificacaoDAOImpl extends GenericDAOImpl<Notificacao, Integer> implements NotificacaoDAO{

	@Override
	public List<Notificacao> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		sb.append(" select ");
		sb.append("		noti ");
		sb.append(" from ");
		sb.append(" 	Notificacao noti ");
		sb.append(  cond );
		

		final TypedQuery<Notificacao> query = entityManager.createQuery(sb.toString(), Notificacao.class);

		return query.getResultList();
		
	}

	@Override
	public List<Notificacao> listarByUsuario(Integer idUsuario) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		cond.append(" where noti.usuarioDestinatario.id = "+idUsuario);
		
		sb.append(" select ");
		sb.append("		noti ");
		sb.append(" from ");
		sb.append(" 	Notificacao noti ");
		sb.append(  cond );
		

		final TypedQuery<Notificacao> query = entityManager.createQuery(sb.toString(), Notificacao.class);

		return query.getResultList();
	}

}
