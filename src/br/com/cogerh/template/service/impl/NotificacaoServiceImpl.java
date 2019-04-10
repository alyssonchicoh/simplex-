package br.com.cogerh.template.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.NotificacaoDAO;
import br.com.cogerh.template.dao.TecnicoDAO;
import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.NotificacaoService;

@Service
public class NotificacaoServiceImpl implements NotificacaoService{
	
	private NotificacaoDAO dao;
	
	@Autowired
	public NotificacaoServiceImpl(NotificacaoDAO dao){
		this.dao = dao;
	}


	@Override
	public Notificacao salvar(Notificacao obj) throws Exception {
		return dao.save(obj);
	}

	@Override
	public Notificacao alterar(Notificacao obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(Notificacao obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public Notificacao buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Notificacao> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}
	
	/**
	 * Método utilizado para adicionar uma Notificação de novo chamado inserido
	 * @param usuario
	 * @throws Exception 
	 */
	@Override
	public void adicionarNotificacaoNovoChamado(Usuario usuario) throws Exception{
		Notificacao notificacao = new Notificacao();
		notificacao.setTitulo("Novo Chamado");
		notificacao.setMensagem("Chamado aguardando sua atenção!");
		notificacao.setLida(false);
		notificacao.setDataNofificacao(new Date());
		notificacao.setUsuarioDestinatario(usuario);
		
		dao.save(notificacao);
	}


	@Override
	public List<Notificacao> listarByUsuario(Integer idUsuario)throws Exception {
		return dao.listarByUsuario(idUsuario);
	}

}
