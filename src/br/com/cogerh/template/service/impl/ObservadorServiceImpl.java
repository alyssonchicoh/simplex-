package br.com.cogerh.template.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.ObservadorDAO;
import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Observador;
import br.com.cogerh.template.service.ObservadorService;

@Service
public class ObservadorServiceImpl implements ObservadorService {
	
	private ObservadorDAO dao;
	
	@Autowired
	public ObservadorServiceImpl(ObservadorDAO dao){
		this.dao = dao;
	}

	@Override
	public Observador salvar(Observador obj) throws Exception {
		return dao.save(obj);
	}

	@Override
	public Observador alterar(Observador obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(Observador obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public Observador buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Observador> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

	@Override
	public Observador listarByUser(Integer idUsuario)  {
		try {
			return dao.listarByUser(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
