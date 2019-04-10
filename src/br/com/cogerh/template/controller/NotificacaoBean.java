package br.com.cogerh.template.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.service.NotificacaoService;

@Controller
@Scope("view")
public class NotificacaoBean extends AbstractBean{

	private List<Notificacao> notificacoes;
	
	@Autowired
	private NotificacaoService notificacaoService;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	
	@PostConstruct
	public void init(){
		consultarNotificacoes();
	}
	
	/**
	 * MÉTODO UTILIZADO PARA CONSULTAR AS NOTIFICAÇÕES DE UM DETERMINADO USUÁRIO
	 */
	public void consultarNotificacoes(){
		try {
			notificacoes = notificacaoService.listarByUsuario(usuarioWeb.getUsuario().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}

	public NotificacaoService getNotificacaoService() {
		return notificacaoService;
	}

	public void setNotificacaoService(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}

	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}

	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}
	
	
	
}
