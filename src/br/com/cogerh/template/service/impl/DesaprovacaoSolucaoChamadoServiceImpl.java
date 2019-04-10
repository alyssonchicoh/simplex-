package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.DesaprovacaoSolucaoChamadoDAO;
import br.com.cogerh.template.model.DesaprovacaoSolucaoChamado;
import br.com.cogerh.template.service.DesaprovacaoSolucaoChamadoService;

@Service
public class DesaprovacaoSolucaoChamadoServiceImpl implements DesaprovacaoSolucaoChamadoService{

	
	private DesaprovacaoSolucaoChamadoDAO dao;
	
	@Autowired
	public DesaprovacaoSolucaoChamadoServiceImpl(DesaprovacaoSolucaoChamadoDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public DesaprovacaoSolucaoChamado salvar(DesaprovacaoSolucaoChamado obj)throws Exception {
		return dao.save(obj);
	}

	@Override
	public DesaprovacaoSolucaoChamado alterar(DesaprovacaoSolucaoChamado obj)throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(DesaprovacaoSolucaoChamado obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public DesaprovacaoSolucaoChamado buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<DesaprovacaoSolucaoChamado> listar(String pesquisa) throws Exception {
		return dao.listar();
	}
	
	

}
