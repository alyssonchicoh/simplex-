package br.com.cogerh.template.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Gerencia;
import br.com.cogerh.template.model.Notificacao;
import br.com.cogerh.template.model.UsuarioGerencia;
import br.com.cogerh.template.model.Viabilidade;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.GerenciaService;
import br.com.cogerh.template.service.NotificacaoService;
import br.com.cogerh.template.service.ViabilidadeService;
import br.com.cogerh.template.util.StatusChamado;

@Controller
@Scope("view")
public class ViabilidadeBean extends AbstractBean{


	@Autowired
	private ViabilidadeService viabilidadeService;
	
	@Autowired
	private ChamadoService chamadoService;
	
	@Autowired
	private GerenciaService gerenciaService;
	
	@Autowired
	private NotificacaoService notificacaoService;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	private List<Gerencia> gerenciaList;
	
	private Chamado chamadoSelecionado;
	
	private String pesquisaGerencia = null;
	
	private Viabilidade viabilidade;
	
	private List<Viabilidade> viabilidadeList;
	
	private Gerencia gerenciaSelecionada; 
	
	
	
	
	@PostConstruct
	public void init(){
		viabilidade = new Viabilidade();
		listar();
	}

	
	public void initFormNovo(Integer chamadoId){
		consultarChamado(chamadoId);
		consultarGerencias();

	}
	private void showFormNovo(Integer idChamado){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	          externalContext.redirect(externalContext.getRequestContextPath() + "/pages/viabilidade/novo.xhtml?chamadoId="+idChamado);
	    } catch (IOException e) {
	          e.printStackTrace();
	    }
	}
	
	public void salvar(){
		try {
			viabilidade.setUsuario(usuarioWeb.getUsuario());
			viabilidade.setCategoria(chamadoSelecionado.getCategoria());
			viabilidadeService.salvar(viabilidade);
			atualizarChamado();
			super.adicionaMensagemDeSucesso("Viabilidade Cadastrada com sucesso!");
			notificarUsuariosGerencia(viabilidade.getGerencia());
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private void atualizarChamado(){
		this.chamadoSelecionado.setStatus(StatusChamado.AGUARDANDO_VIABILIDADE);
		try {
			chamadoService.alterar(chamadoSelecionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * MÉTODO RESPONSAVEL POR REALIZAR A NOTIFICAÇÃO DE TODOS OS USUÁRIOS QUE SERÃO OS RESPONSAVEIS POR APROVAR OU NÃO A VIABILIDADE CADASTRADA
	 * @param gerencia
	 */
	private void notificarUsuariosGerencia(Gerencia gerencia){
		
		for (UsuarioGerencia usu : gerencia.getResponsaveis()) {
			Notificacao notificacao = new Notificacao();
			notificacao.setTitulo("Solicitaçao de Viabilidade");
			notificacao.setMensagem("Exite uma solicitação de viabilidade pendente para sua gerência");
			notificacao.setDataNofificacao(new Date());
			notificacao.setUsuarioDestinatario(usu.getUsuario());
			try {
				notificacaoService.salvar(notificacao);
				System.out.println("Notificação Enviada");
				super.adicionaMensagemDeSucesso("Notificação enviada para usuários responsáveis");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				super.adicionaMensagemDeErro(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * MÉTODO UTILIZADO PARA CONSULTAR UM CHAMADO COM BASE EM SEU ID
	 * @param idChamado
	 */
	private void consultarChamado(Integer idChamado){
		try {
			chamadoSelecionado = chamadoService.buscarPorId(idChamado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void consultarGerencias(){
		try {
			gerenciaList = gerenciaService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listar(){
		try {
			viabilidadeList = viabilidadeService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selecionarGerencia(Gerencia gerencia){
		viabilidade.setGerencia(gerencia);
	}


	public ViabilidadeService getViabilidadeService() {
		return viabilidadeService;
	}


	public void setViabilidadeService(ViabilidadeService viabilidadeService) {
		this.viabilidadeService = viabilidadeService;
	}


	public ChamadoService getChamadoService() {
		return chamadoService;
	}


	public void setChamadoService(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}


	public GerenciaService getGerenciaService() {
		return gerenciaService;
	}


	public void setGerenciaService(GerenciaService gerenciaService) {
		this.gerenciaService = gerenciaService;
	}


	public List<Gerencia> getGerenciaList() {
		return gerenciaList;
	}


	public void setGerenciaList(List<Gerencia> gerenciaList) {
		this.gerenciaList = gerenciaList;
	}


	public Chamado getChamadoSelecionado() {
		return chamadoSelecionado;
	}


	public void setChamadoSelecionado(Chamado chamadoSelecionado) {
		this.chamadoSelecionado = chamadoSelecionado;
	}


	public String getPesquisaGerencia() {
		return pesquisaGerencia;
	}


	public void setPesquisaGerencia(String pesquisaGerencia) {
		this.pesquisaGerencia = pesquisaGerencia;
	}


	public Viabilidade getViabilidade() {
		return viabilidade;
	}


	public void setViabilidade(Viabilidade viabilidade) {
		this.viabilidade = viabilidade;
	}


	public List<Viabilidade> getViabilidadeList() {
		return viabilidadeList;
	}


	public void setViabilidadeList(List<Viabilidade> viabilidadeList) {
		this.viabilidadeList = viabilidadeList;
	}


	public Gerencia getGerenciaSelecionada() {
		return gerenciaSelecionada;
	}


	public void setGerenciaSelecionada(Gerencia gerenciaSelecionada) {
		this.gerenciaSelecionada = gerenciaSelecionada;
	}



	
	
}
