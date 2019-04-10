package br.com.cogerh.template.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.SolucaoChamado;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.EmailService;
import br.com.cogerh.template.service.SolucaoChamadoService;
import br.com.cogerh.template.util.StatusChamado;

/**
 * 
 * @author alyssonnascimento
 * @since  22/01/2019
 */
@Controller
@Scope("view")
public class SolucaoChamadoBean extends AbstractBean{
	
	@Autowired
	private ChamadoService chamadoService;
	
	private Chamado chamado;
	
	@Autowired
	private SolucaoChamadoService solucaoChamadoService;
	
	private SolucaoChamado solucaoChamado;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@Autowired
	private EmailService email;
	
	private boolean exibeHistoricoSolucaoChamado = false;
	
	private boolean exibeSolucaoChamado = false;


	public EmailService getEmail() {
		return email;
	}

	public void setEmail(EmailService email) {
		this.email = email;
	}

	public boolean isExibeSolucaoChamado() {
		return exibeSolucaoChamado;
	}

	public void setExibeSolucaoChamado(boolean exibeSolucaoChamado) {
		this.exibeSolucaoChamado = exibeSolucaoChamado;
	}
	
	@PostConstruct
	private void init() {
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
		System.out.println(path);
	}
	
	public void initFormAprovacaoSolucao(String hashURL){
		System.out.println(hashURL);
		try {
			solucaoChamado = solucaoChamadoService.pesquisarByURL(hashURL);
		} catch (Exception e) {
			if(e instanceof NoResultException){
				super.adicionaMensagemDeErro("NÃO ENCONTRAMOS UM CHAMADO PARA SUA SOLICITAÇÃO!");
			}
			e.printStackTrace();
		}
	}

	public void initFormEncerramento(Integer chamadoId){
		chamado = consultarChamadoByID(chamadoId);
		solucaoChamado = new SolucaoChamado();
		verificaSolucaoChamado();
	}
	
	/**
	 * MÉTODO UTILIZADO PARA VERIFICAR SE O CHAMADO TEM SOLUÇÃO
	 */
	private void verificaSolucaoChamado(){
		try {
			List<SolucaoChamado> list = solucaoChamadoService.listarByChamado(chamado.getId());
			
			if(list != null){
				if(list.size() >0){
					exibeSolucaoChamado = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Chamado consultarChamadoByID(Integer id) {
		Chamado chamado = null;
		try {
			chamado = chamadoService.buscarPorId(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return chamado;
	}
	
	public String salvar(){
		solucaoChamado.setDataSolucao(new Date());
		solucaoChamado.setUsuario(usuarioWeb.getUsuario());
		solucaoChamado.setChamado(chamado);
		
		try {
			solucaoChamadoService.salvar(solucaoChamado);
			System.out.println("SALVA");
			atualizarStatusChamado();
			enviarEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detalhamento.xhtml?faces-redirect=true&chamadoId="+chamado.getId() + "&newChamado=false&newChamadoFechado=true";
	}

	public void atualizarStatusChamado(){
		try {
			chamado.setStatus(StatusChamado.SOLUCIONADO);
			chamadoService.alterar(chamado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void aprovar(){
		solucaoChamado.setConfirmacao(true);
		try {
			solucaoChamadoService.alterar(solucaoChamado);
			super.adicionaMensagemDeSucesso("Chamado Aprovado com Sucesso!");
			System.out.println("APROVADO!!!");
		} catch (Exception e) {
			super.adicionaMensagemDeErro("Erro ao Aprovar Chamado!");

			e.printStackTrace();
		}
	}
	
	public void exibirHistorico(){
		System.out.println("CHAMOU");
		exibeHistoricoSolucaoChamado = true;
	}
	
	public void enviarEmail(){
		email.enviarEmailChamadoEncerrado(solucaoChamado);

	}

	public ChamadoService getChamadoService() {
		return chamadoService;
	}


	public void setChamadoService(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}


	public Chamado getChamado() {
		return chamado;
	}


	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}


	public SolucaoChamadoService getSolucaoChamadoService() {
		return solucaoChamadoService;
	}


	public void setSolucaoChamadoService(SolucaoChamadoService solucaoChamadoService) {
		this.solucaoChamadoService = solucaoChamadoService;
	}


	public SolucaoChamado getSolucaoChamado() {
		return solucaoChamado;
	}


	public void setSolucaoChamado(SolucaoChamado solucaoChamado) {
		this.solucaoChamado = solucaoChamado;
	}


	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}


	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}


	public boolean isExibeHistoricoSolucaoChamado() {
		return exibeHistoricoSolucaoChamado;
	}


	public void setExibeHistoricoSolucaoChamado(boolean exibeHistoricoSolucaoChamado) {
		this.exibeHistoricoSolucaoChamado = exibeHistoricoSolucaoChamado;
	}
	
	
}
