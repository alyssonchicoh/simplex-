package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Tecnico;

public interface NotificacaoDAO extends GenericDAO<Notificacao, Integer>{
	
	public List<Notificacao> listar(String pesquisa); 
	
	public List<Notificacao> listarByUsuario(Integer idUsuario); 

}
