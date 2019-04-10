package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.AcompanhamentoDAO;
import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.service.AcompanhamentoService;

@Service
public class AcompanhamentoServiceImpl implements AcompanhamentoService{
	
	private AcompanhamentoDAO dao;
	
	@Autowired
	public AcompanhamentoServiceImpl(AcompanhamentoDAO dao){
		this.dao = dao;
	}
	

	@Override
	public Acompanhamento salvar(Acompanhamento acompanhamento) throws Exception {
		return dao.save(acompanhamento);
	}

	@Override
	public Acompanhamento alterar(Acompanhamento acompanhamento) throws Exception {
		return dao.update(acompanhamento);
	}

	@Override
	public void remover(Acompanhamento acompanhamento) throws Exception {
		dao.delete(acompanhamento);
	}

	@Override
	public Acompanhamento buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Acompanhamento> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}


	@Override
	public List<Acompanhamento> listarByChamado(Integer idChamado) throws Exception {
		return dao.listarByChamado(idChamado);
	}

}
