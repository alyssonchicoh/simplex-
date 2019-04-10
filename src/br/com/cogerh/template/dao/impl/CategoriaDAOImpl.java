package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.CategoriaDAO;
import br.com.cogerh.template.dao.ChamadoDAO;
import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.util.HasValue;

@Transactional
@Repository
public class CategoriaDAOImpl extends GenericDAOImpl<Categoria, Integer> implements CategoriaDAO{
		
		public List<Categoria> listar(String pesquisa) {
			final StringBuilder sb = new StringBuilder();
			final StringBuilder cond = new StringBuilder();
			
			sb.append(" select ");
			sb.append("     cat ");
			sb.append(" from ");
			sb.append(" 	Categoria cat ");
			sb.append(  cond );
			sb.append(" order by ");
			sb.append(" 	cat.nome "); 

			final TypedQuery<Categoria> query = entityManager.createQuery(sb.toString(), Categoria.class);

			return query.getResultList();
		}
		
		
		@Override
		public Categoria buscarCategoriaByNome(String pesquisa) {
			final StringBuilder sb = new StringBuilder();
			final StringBuilder cond = new StringBuilder();


			if (HasValue.execute(pesquisa))
				cond.append(" and (upper(titutlo) like :titulo) ");

			if (cond.length() > 4)
				cond.replace(0, 5, " where ");

			sb.append(" select ");
			sb.append("     cha ");
			sb.append(" from ");
			sb.append(" 	Chamado cha ");
			sb.append(  cond );
			sb.append(" order by ");
			sb.append(" 	cha.numero "); 

			final TypedQuery<Categoria> query = entityManager.createQuery(sb.toString(), Categoria.class);

			if (HasValue.execute(pesquisa))
			{
				query.setParameter("titulo", "%" + pesquisa.toUpperCase() + "%");
			}
			


			return query.getSingleResult();
		}
		

}
