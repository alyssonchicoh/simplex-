package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.GerenciaDAO;
import br.com.cogerh.template.model.Gerencia;
import br.com.cogerh.template.service.GerenciaService;

@Service
public class GerenciaServiceImpl implements GerenciaService{

	private GerenciaDAO dao;
	
	@Autowired
	public GerenciaServiceImpl(GerenciaDAO dao){
		this.dao = dao;
	}
	
	@Transactional
	public Gerencia salvar(Gerencia gerencia) throws Exception {
		return dao.save(gerencia);
	}

	@Transactional
	public Gerencia alterar(Gerencia gerencia) throws Exception {
		return dao.update(gerencia);
	}

	@Transactional
	public void remover(Gerencia gerencia) throws Exception {
		dao.delete(gerencia);
	}

	@Transactional
	public Gerencia buscarPorId(Integer id) throws Exception {
		return this.dao.buscarPorId(id);
	}

	@Transactional
	public List<Gerencia> listar(String pesquisa) throws Exception {
		return this.dao.listar(pesquisa);
	}

}
