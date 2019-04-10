package br.com.cogerh.template.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.TecnicoDAO;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.service.TecnicoService;

@Service
public class TecnicoServiceImpl implements TecnicoService{
	
	private TecnicoDAO dao;
	
	@Autowired
	public TecnicoServiceImpl(TecnicoDAO dao){
		this.dao = dao;
	}

	@Transactional
	public Tecnico salvar(Tecnico obj) throws Exception {
		return this.dao.save(obj);
	}

	@Transactional
	public Tecnico alterar(Tecnico obj) throws Exception {
		return this.dao.update(obj);
	}

	@Transactional
	public void remover(Tecnico obj) throws Exception {
		dao.delete(obj);
	}

	@Transactional
	public Tecnico buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Transactional
	public List<Tecnico> listar(String pesquisa) throws Exception {
		return dao.listar(null);
	}

	@Override
	public Tecnico buscarPorIdUsuario(Integer idUsuario) throws Exception {
		try{
			return dao.buscarPorIdUsuario(idUsuario);
		}catch(NoResultException e){
			return null;
		}
	}

}
