package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.AvisoDAO;
import br.com.cogerh.template.model.Aviso;
import br.com.cogerh.template.service.AvisoService;

@Service
public class AvisoServiceImpl implements AvisoService{
	
	private AvisoDAO dao;
	
	@Autowired
	public AvisoServiceImpl(AvisoDAO dao) {
		this.dao = dao;
	}

	@Override
	public Aviso salvar(Aviso aviso) throws Exception {
		return dao.save(aviso);
	}

	@Override
	public Aviso alterar(Aviso aviso) throws Exception {
		return dao.update(aviso);
	}

	@Override
	public void remover(Aviso aviso) throws Exception {
		dao.delete(aviso);
	}

	@Override
	public Aviso buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Aviso> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

}
