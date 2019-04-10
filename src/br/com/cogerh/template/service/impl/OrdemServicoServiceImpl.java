package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.OrdemServicoDAO;
import br.com.cogerh.template.model.OrdemServico;
import br.com.cogerh.template.service.ObservadorService;
import br.com.cogerh.template.service.OrdemServicoService;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

	private OrdemServicoDAO dao;
	
	@Autowired
	public OrdemServicoServiceImpl(OrdemServicoDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public OrdemServico salvar(OrdemServico obj) throws Exception {
		return dao.save(obj);
	}

	@Override
	public OrdemServico alterar(OrdemServico obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(OrdemServico obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public OrdemServico buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<OrdemServico> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

	@Override
	public List<OrdemServico> listarByChamadoOrigem(Integer idChamado) throws Exception {
		return null;
	}

}
