package br.com.cogerh.template.service;

import java.util.List;

import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.Usuario;

public interface NotificacaoService {
	
	public Notificacao salvar(Notificacao obj) throws Exception;
	public Notificacao alterar(Notificacao obj) throws Exception;
	public void remover(Notificacao obj) throws Exception;
	public Notificacao buscarPorId(Integer id) throws Exception;
	public List<Notificacao> listar(String pesquisa) throws Exception;
	public void adicionarNotificacaoNovoChamado(Usuario usuario) throws Exception;
	public List<Notificacao> listarByUsuario(Integer idUsuario) throws Exception;

}
