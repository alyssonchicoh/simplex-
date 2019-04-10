package br.com.cogerh.template.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.ChamadoDAO;
import br.com.cogerh.template.dao.GrupoDAO;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.util.FiltroChamadoDadosGerais;
import br.com.cogerh.template.util.HasValue;

@Transactional
@Repository
public class ChamadoDAOImpl extends GenericDAOImpl<Chamado, Integer> implements ChamadoDAO{
	
	public List<Chamado> listar(String pesquisa) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();

		

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(  cond );
		sb.append(" order by ");
		sb.append(" 	cha.numero "); 

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();
	}
	
	
	@Override
	public Chamado buscarChamadoByNome(String pesquisa) {
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

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		if (HasValue.execute(pesquisa))
		{
			query.setParameter("titulo", "%" + pesquisa.toUpperCase() + "%");
		}
		


		return query.getSingleResult();
	}


	@Override
	public List<Chamado> listarSemTecnicoAlocado() throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		cond.append("where tc.tecnico is null");

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(" 	left join cha.tecnicosChamados tc ");
		sb.append(cond.toString());
	
		

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();	}


	@Override
	public List<Chamado> listarByTecnico(Integer id) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		cond.append("where tc.tecnico.usuario.id ="+id);

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(" 	inner join cha.tecnicosChamados tc ");
		sb.append(cond.toString());
	
		

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();	
	}


	@Override
	public List<Chamado> listarChamadosSolicitadoByUsuario(Integer idUsuario) throws Exception {
		
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		cond.append("where so.id ="+idUsuario);

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(" 	inner join cha.solicitante so ");
		sb.append(cond.toString());
	
		

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();		
	}


	@Override
	public List<Chamado> listarChamadoOrdenadoPorPosicaoFilaByTecnico(Integer idTecnico) throws Exception {
		
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		cond.append("where tec.id = "+idTecnico);

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(" 	inner join cha.tecnicosChamados tc ");
		sb.append(" 	inner join tc.tecnico tec ");

		sb.append(cond.toString());
		sb.append(" order by ");
		sb.append(" 	cha.posicaoFila "); 
		
		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();	
	}


	@Override
	public List<Chamado> listarChamadoBySolicitanteAndStatus(Integer idSolicitante, Integer status) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		cond.append("where so.id ="+idSolicitante + "and cha.status = " + status);

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(" 	inner join cha.solicitante so ");
		sb.append(cond.toString());
	
		

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();	}


	@Override
	public List<Chamado> listarByFiltros(FiltroChamadoDadosGerais filtroChamadoDadosGerais) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder cond = new StringBuilder();
		
		
		if(filtroChamadoDadosGerais != null) {
			cond.append(" WHERE 1=1");
		}
		
		if(filtroChamadoDadosGerais != null) {
			//ADICIONANDO O FILTRO DE TITULO
			cond.append(filtroChamadoDadosGerais.isExistTitulo() ? " AND UPPER(cha.titulo) like UPPER('%" + filtroChamadoDadosGerais.getTitulo() +"%')": "");
			//ADICIONANDO O FILTRO DE DESCRICAO
			cond.append(filtroChamadoDadosGerais.isExistDescricao() ? " AND UPPER(cha.descricao) like UPPER('%" + filtroChamadoDadosGerais.getDescricao() +"'%)": "");
			//ADICIONANDO O FILTRO DE PRIORIDADE
			cond.append(filtroChamadoDadosGerais.isExistPrioridade() ? " AND cha.prioridade = "+ filtroChamadoDadosGerais.getPrioridade() : "");
			//ADICIONANDO O FILTRO DE CATEGORIA
			cond.append(filtroChamadoDadosGerais.isExistCategoria() ?" AND cha.categoria.id = "+ filtroChamadoDadosGerais.getIdCategoria(): "");
			//ADICIONANDO O FLTRO DE JÁ INFORMADO
			cond.append(filtroChamadoDadosGerais.isExistInformado() ? " AND cha.isTecnicoInformado = "+filtroChamadoDadosGerais.getJaInformado(): "");
			//ADICIONANDO O FILTRO DE RECORRENTE
			cond.append(filtroChamadoDadosGerais.isExistRecorrente() ? " AND cha.isRecorrente =  "+filtroChamadoDadosGerais.getRecorrente() : "");
			//ADICIONANDO O FILTRO DE TRABALHOS PARADOS
			cond.append(filtroChamadoDadosGerais.isExistTrabalhosParados() ? " AND cha.isTrabalhosParados =  "+filtroChamadoDadosGerais.getTrabalhosParados() : "");
			//ADICIONANDO O FILTRO DE DATA ABERTURA INICIO
			cond.append(filtroChamadoDadosGerais.isExistDataAberturaInicio() ? filtroChamadoDadosGerais.getDataAberturaInicio() : "");
			//ADICIONANDO O FILTRO DE DATA ABERTURA FINAL
			cond.append(filtroChamadoDadosGerais.isExistDataAberturaFinal() ? filtroChamadoDadosGerais.getDataAberturaFinal() : "");
			//ADICIONANDO O FILTRO DE DATA ENCERRAMENTO INICIO
			cond.append(filtroChamadoDadosGerais.isExistDataEncerramentoInicio() ? filtroChamadoDadosGerais.getDataEncerramentoInicio() : "");
			//ADICIONANDO O FILTRO DE DATA ENCERRAMENTO FINAL
			cond.append(filtroChamadoDadosGerais.isExistDataEncerramentoFinal() ? filtroChamadoDadosGerais.getDataEncerramentoFinal() : "");
			//ADICIONANDO O FILTRO DE DATA ULTIMA ATUALIZACAO INICIO
			cond.append(filtroChamadoDadosGerais.isExistDataUltimaAtualizacaoInicio() ? filtroChamadoDadosGerais.getDataUltimaAtualizacaoInicio() : "");
			//ADICIONANDO O FILTRO DE DATA ULTIMA ATUALIZACAO FINAL
			cond.append(filtroChamadoDadosGerais.isExistDataUltimaAtualizacaoFinal() ? filtroChamadoDadosGerais.getDataUltimaAtualizacaoFinal() : "");
			//ADICIONANDO O FILTRO DE DATA SOLUCAO INICIO
			cond.append(filtroChamadoDadosGerais.isExistDataSolucaoInicio() ? filtroChamadoDadosGerais.getDataSolucaoInicio() : "");
			//ADICIONANDO O FILTRO DE DATA SOLUCAO FINAL
			cond.append(filtroChamadoDadosGerais.isExistDatataSolucaoFinal() ? filtroChamadoDadosGerais.getDataSolucaoFinal() : "");
		}
		
		

		sb.append(" select ");
		sb.append("     cha ");
		sb.append(" from ");
		sb.append(" 	Chamado cha ");
		sb.append(cond.toString());
	
		

		final TypedQuery<Chamado> query = entityManager.createQuery(sb.toString(), Chamado.class);

		return query.getResultList();		}
	
}