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
import br.com.cogerh.template.service.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
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
	
	public void initFormDetalhamento(Integer idViabilidade){
		try {
			this.viabilidade = viabilidadeService.buscarPorId(idViabilidade);
			consultarChamado(viabilidade.getChamado().getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void showFormNovo(Integer idChamado){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	          externalContext.redirect(externalContext.getRequestContextPath() + "/pages/viabilidade/novo.xhtml?chamadoId="+idChamado);
	    } catch (IOException e) {
	          e.printStackTrace();
	    }
	}
	
	public String showFormDetalhamento(Integer idViabilidade){
		return "detalhamento.xhtml?faces-redirect=true&idViabilidade = "+idViabilidade;
	}
	
	public void salvar(){
		try {
			viabilidade.setUsuario(usuarioWeb.getUsuario());
			viabilidade.setCategoria(chamadoSelecionado.getCategoria());
			viabilidade.setChamado(chamadoSelecionado);
			viabilidade.setAprovado(1);
			viabilidade.setData(new Date());
			viabilidadeService.salvar(viabilidade);
			viabilidade = new Viabilidade();
			atualizarChamado(StatusChamado.AGUARDANDO_VIABILIDADE);
			super.adicionaMensagemDeSucesso("Viabilidade Cadastrada com sucesso!");
			notificarUsuariosGerencia(viabilidade.getGerencia());
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void salvarAprovacaoViabilidade(boolean param){
		
		try {
			viabilidade.setUsuarioAprovador(usuarioWeb.getUsuario());
			viabilidade.setDataAprovacao(new Date());
			viabilidade.setAprovado(param ? 2 : 3);
			atualizarChamado(StatusChamado.EM_ATENDIMENTO);
			viabilidadeService.alterar(viabilidade);
			super.adicionaMensagemDeSucesso("Viabilidade Analisada! Os técnicos do chamado foram notificados.");
			emailService.enviarEmailViabilidadeRespondida(chamadoSelecionado);
			

		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private void atualizarChamado(Integer status){
		this.chamadoSelecionado.setStatus(status);
		try {
			chamadoService.alterar(chamadoSelecionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Mï¿½TODO RESPONSAVEL POR REALIZAR A NOTIFICAï¿½ï¿½O DE TODOS OS USUï¿½RIOS QUE SERï¿½O OS RESPONSAVEIS POR APROVAR OU Nï¿½O A VIABILIDADE CADASTRADA
	 * @param gerencia
	 */
	private void notificarUsuariosGerencia(Gerencia gerencia){
		
		for (UsuarioGerencia usu : gerencia.getResponsaveis()) {
			Notificacao notificacao = new Notificacao();
			notificacao.setTitulo("Solicitaï¿½ao de Viabilidade");
			notificacao.setMensagem("Exite uma solicitaï¿½ï¿½o de viabilidade pendente para sua gerï¿½ncia");
			notificacao.setDataNofificacao(new Date());
			notificacao.setUsuarioDestinatario(usu.getUsuario());
			try {
				notificacaoService.salvar(notificacao);
				System.out.println("Notificaï¿½ï¿½o Enviada");
				super.adicionaMensagemDeSucesso("Notificaï¿½ï¿½o enviada para usuï¿½rios responsï¿½veis");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				super.adicionaMensagemDeErro(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Mï¿½TODO UTILIZADO PARA CONSULTAR UM CHAMADO COM BASE EM SEU ID
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
