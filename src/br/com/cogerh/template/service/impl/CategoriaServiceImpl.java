package br.com.cogerh.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.CategoriaDAO;
import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	private CategoriaDAO dao;
	
	@Autowired
	public CategoriaServiceImpl(CategoriaDAO dao){
		this.dao = dao;
	}

	@Override
	public Categoria salvar(Categoria categoria) throws Exception {
		return dao.save(categoria);
	}

	@Override
	public Categoria alterar(Categoria categoria) throws Exception {
		return dao.update(categoria);
	}

	@Override
	public void remover(Categoria categoria) throws Exception {
		dao.delete(categoria);
		
	}

	@Override
	public Categoria buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
		
	}

	@Override
	public List<Categoria> listar(String pesquisa) throws Exception {
		
		return dao.listar(pesquisa);
	}

	@Override
	public Categoria buscarByNome(String pesquisa) throws Exception {
		
		return dao.buscarCategoriaByNome(pesquisa);
	}

}
