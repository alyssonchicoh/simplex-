package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.ArquivoAnexadoDAO;
import br.com.cogerh.template.model.ArquivoAnexado;
import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.service.ArquivoAnexadoService;
import br.com.cogerh.template.service.CategoriaService;

@Service
public class ArquivoAnexadoServiceImpl implements ArquivoAnexadoService{
	
	private ArquivoAnexadoDAO dao;
	
	@Autowired
	public ArquivoAnexadoServiceImpl(ArquivoAnexadoDAO dao) {
		this.dao = dao;
	}

	@Override
	public ArquivoAnexado salvar(ArquivoAnexado obj) throws Exception {
		return dao.save(obj);
	}

	@Override
	public ArquivoAnexado alterar(ArquivoAnexado obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(ArquivoAnexado obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public ArquivoAnexado buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<ArquivoAnexado> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

	@Override
	public List<ArquivoAnexado>  buscarByChamado(Integer idChamado) throws Exception {
		return dao.buscarArquivoByChamado(idChamado);
	}

}
