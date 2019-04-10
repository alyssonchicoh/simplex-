package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.TecnicoDAO;
import br.com.cogerh.template.dao.TelaPermissaoDAO;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.model.TelaPermissao;
import br.com.cogerh.template.service.TecnicoService;
import br.com.cogerh.template.service.TelaPermissaoService;

@Service
public class TelaTecnicoServiceImpl implements TelaPermissaoService{

	private TelaPermissaoDAO dao;
	
	@Autowired
	public TelaTecnicoServiceImpl(TelaPermissaoDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public TelaPermissao salvarTelaPermissao(TelaPermissao tela)
			throws Exception {
		return dao.save(tela);
	}

	@Override
	public TelaPermissao alterarTelaPermissao(TelaPermissao tela)
			throws Exception {
		return dao.update(tela);
	}

	@Override
	public void removerTelaPermissao(TelaPermissao tela) throws Exception {
		dao.delete(tela);
	}

	@Override
	public TelaPermissao buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<TelaPermissao> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}
	
	

}
