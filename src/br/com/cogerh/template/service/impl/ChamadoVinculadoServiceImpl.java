package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cogerh.template.dao.ChamadoVinculadoDAO;
import br.com.cogerh.template.model.ChamadoVinculado;
import br.com.cogerh.template.service.ChamadoVinculadoService;

public class ChamadoVinculadoServiceImpl implements ChamadoVinculadoService{
	
	private ChamadoVinculadoDAO dao;
	
	@Autowired
	public ChamadoVinculadoServiceImpl(ChamadoVinculadoDAO dao) {
		this.dao = dao;
	}

	@Override
	public ChamadoVinculado salvar(ChamadoVinculado obj) throws Exception {
		return dao.save(obj);
	}

	@Override
	public ChamadoVinculado alterar(ChamadoVinculado obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(ChamadoVinculado obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public ChamadoVinculado buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<ChamadoVinculado> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

	@Override
	public List<ChamadoVinculado> listarByChamadoOrigem(Integer idChamado) throws Exception {
		return dao.listarByChamadoOrigem(idChamado);
	}

}
