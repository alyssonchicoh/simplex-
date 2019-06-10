package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.GrupoDAO;
import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.service.GrupoService;

@Service
public class GrupoServiceImpl implements GrupoService
{
	private GrupoDAO dao;

	@Autowired
	public GrupoServiceImpl(GrupoDAO dao)
	{
		this.dao = dao;
	}

	@Transactional
	public Grupo salvarGrupo(Grupo grupo) throws Exception
	{
		return this.dao.save(grupo);
	}

	@Transactional
	public Grupo alterarGrupo(Grupo grupo) throws Exception
	{
		return this.dao.update(grupo);
	}

	@Transactional
	public void removerGrupo(Grupo grupo) throws Exception
	{
		this.dao.delete(grupo);
	}

	@Transactional
	public List<Grupo> listarGrupos(String pesquisa) throws Exception
	{
		List<Grupo> grupoList = dao.listar(pesquisa);

		return grupoList;
	}

	@Transactional
	public Grupo buscarPorId(Integer id) throws Exception
	{
		Grupo grupo = dao.buscarPorId(id);

		return grupo;
	}

	@Transactional
	public Grupo buscarGrupoByNome(String pesquisa) throws Exception {

		return dao.buscarGrupoByNome(pesquisa);
	}

	@Override
	public void initAllGrupo() throws Exception {
		Grupo usuarios = new Grupo("USUARIOS");
		Permissao PERMISSAO_INCLUIR_CHAMADO = new Permissao("INCLUIR_CHAMADO","PERMISSÃO RESPONSÁVEL POR INCLUIR CHAMADOS NO SISTEMA");
		Permissao PERMISSAO_LISTAR_CHAMADO = new Permissao("LISTAR_CHAMADO","PERMISSÃO RESPONSÁVEL POR LISTAR OS CHAMADOS NO SISTEMA");
		Permissao PERMISSAO_ATRIBUIR_TECNICO_CHAMADO = new Permissao("ATRIBUIR_TECNICO_CHAMADO","PERMISSÃO RESPONSÁVEL POR INCLUIR CHAMADOS NO SISTEMA");
		
		
	

	}

	public GrupoDAO getDao() {
		return dao;
	}

	public void setDao(GrupoDAO dao) {
		this.dao = dao;
	}
}