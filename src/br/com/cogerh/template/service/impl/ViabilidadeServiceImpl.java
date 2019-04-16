package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.ViabilidadeDAO;
import br.com.cogerh.template.model.Viabilidade;
import br.com.cogerh.template.service.ViabilidadeService;

@Service
public class ViabilidadeServiceImpl implements ViabilidadeService{

	
	private ViabilidadeDAO dao;
	
	@Autowired
	public  ViabilidadeServiceImpl(ViabilidadeDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Viabilidade salvar(Viabilidade obj) throws Exception {
		return dao.save(obj);
	}

	@Override
	public Viabilidade alterar(Viabilidade obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(Viabilidade obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public Viabilidade buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Viabilidade> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

}
