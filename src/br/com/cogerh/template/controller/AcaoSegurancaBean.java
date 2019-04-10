package br.com.cogerh.template.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Observador;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.ObservadorService;
import br.com.cogerh.template.service.TecnicoService;

/**
 * Classe responsavel por controlar A seguraça das ações do sistema
 * @author alyssonnascimento
 * @since 15/03/2019
 */

@Controller
@Scope("view")
public class AcaoSegurancaBean extends AbstractBean {
	
	private Chamado chamado;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ObservadorService observadorService;
	
	@Autowired
	private ChamadoService chamadoService;
	
	private final String ACAO_DETALHAMENTO_FINALIZAR_CHAMADO = "ACAO_FINALIZAR_CHAMADO";
	
	private final String ACAO_DETALHAMENTO_TRABALHAR_CHAMADO = "ACAO_TRABALHAR_CHAMADO";
	
	private final String ACAO_DETALHAMENTO_ALOCAR_TECNICO = "ACAO_ALOCAR_TECNICO";
	
	private final String ACAO_DETALHAMENTO_SOLICITACAO_VIABILIDADE = "ACAO_SOLICITACAO_VIABILIDADE";
	
	private final String ACAO_DETALHAMENTO_EDITAR_CHAMADO = "ACAO_EDITAR_CHAMADO";
	
	private final String VIEW_BOX_ACOES_TECNICAS = "VIEW_BOX_ACOES_TECNICAS";
	
	public void init(Integer idChamado){
		try {
			chamado = chamadoService.buscarPorId(idChamado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isPermissao(String acao){
		boolean permissao = false;
		
			switch (acao) {
				case ACAO_DETALHAMENTO_FINALIZAR_CHAMADO:
					permissao = regraAcaoDetalhamentoFinalizarChamado();
					break;
				case ACAO_DETALHAMENTO_TRABALHAR_CHAMADO:
					permissao = regraAcaoDetalhamentoTrabalharChamado();
					break;
					
				case ACAO_DETALHAMENTO_ALOCAR_TECNICO:
					permissao = regraAcaoDetalhamentoAlocarTecnicos();
					break;
				
				case ACAO_DETALHAMENTO_SOLICITACAO_VIABILIDADE:
					permissao = regraAcaoDetalhamentoSolicitacaoViabilidade();
					break;
					
				case ACAO_DETALHAMENTO_EDITAR_CHAMADO:
					permissao = regraAcaoDetalhamentoEditarChamado();
					break;
				case VIEW_BOX_ACOES_TECNICAS:
					permissao = regraViewBoxAcoesTecnicas();
					break;
			}
		
		return permissao;
	}

	private boolean regraAcaoDetalhamentoFinalizarChamado(){
		try{
			//BUSCANDO O USUÁRIO QUE ENCONTRA-SE LOGADI
			Tecnico tecnico = tecnicoService.buscarPorIdUsuario(usuarioWeb.getUsuario().getId());
			
				//VERIFICANDO SE O USUARIO É UM TECNICO
				if(tecnico == null){
					return false;
				}else{
					if(chamado.isTrabalhando()){
						return true;
					}else{
						return false;
					}
					
				}
		}catch(Exception e){
			if(! (e instanceof NoResultException)){
				e.printStackTrace();
			}
			return false;
		}
	}
	
	private boolean regraAcaoDetalhamentoTrabalharChamado() {
		try {
			//BUSCANDO O USUÁRIO QUE ENCONTRA-SE LOGADI
			Tecnico tecnico = tecnicoService.buscarPorIdUsuario(usuarioWeb.getUsuario().getId());
			
				//VERIFICANDO SE O USUARIO É UM TECNICO
				if(tecnico == null){
					return false;
				}else{
					//VERIFICANDO SE O USUÁRIO FAZ PARTE DA LISTA DE TECNICOS DO CHAMADO
					if(chamadoService.verificaTecnicoAlocadoChamado(chamado, tecnico)){
						//VERIFICA SE O CHAMADO JÁ ENCONTRA-SE TRABALHANDO, CASO SIM
						//O BOTÃO NÃO DEVE SER RENDERIZADO
						if(chamado.isTrabalhando()){
							return false;
						}else{
							return true;
						}
						
					}else{
						return false;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return false;
	}
	
	private boolean regraAcaoDetalhamentoAlocarTecnicos(){
		try{
			Observador observador = observadorService.listarByUser(usuarioWeb.getUsuario().getId());
			if(observador != null){
				return true;
			}else{
				return false;
			}
		
		}catch(Exception e){
			if(! (e instanceof NoResultException)){
				e.printStackTrace();
			}
			return false;
		}
	}

	private boolean regraAcaoDetalhamentoSolicitacaoViabilidade() {
		try {
			// BUSCANDO O USUÁRIO QUE ENCONTRA-SE LOGADI
			Tecnico tecnico = tecnicoService.buscarPorIdUsuario(usuarioWeb.getUsuario().getId());

			// VERIFICANDO SE O USUARIO É UM TECNICO
			if (tecnico == null) {
				return false;
			} else {
				// VERIFICANDO SE O USUÁRIO FAZ PARTE DA LISTA DE TECNICOS DO
				// CHAMADO
				if (chamadoService.verificaTecnicoAlocadoChamado(chamado,tecnico)) {
					
					if(chamado.isTrabalhando()){
						return false;
					}else{
						return true;
					}				}
			}
		} catch (Exception e) {

		}

		return false;
	}
	
	private boolean regraAcaoDetalhamentoEditarChamado(){
		try{
			Observador observador = observadorService.listarByUser(usuarioWeb.getUsuario().getId());
			if(observador != null){
				return true;
			}else{
				return false;
			}
		
		}catch(Exception e){
			if(! (e instanceof NoResultException)){
				e.printStackTrace();
			}
			return false;
		}
	}
	
	private boolean regraViewBoxAcoesTecnicas(){
		try{
			// BUSCANDO O USUÁRIO QUE ENCONTRA-SE LOGADI
			Tecnico tecnico = tecnicoService.buscarPorIdUsuario(usuarioWeb.getUsuario().getId());
			Observador observador = observadorService.listarByUser(usuarioWeb.getUsuario().getId());

			// VERIFICANDO SE O USUARIO É UM TECNICO
				if (tecnico == null && observador == null) {
					return false;
				}else{
					if(observador != null){
						return true;
					}
					if (chamadoService.verificaTecnicoAlocadoChamado(chamado,tecnico)) {
						return true;
					}
					else{
						return false;
					}
				}
		}catch(Exception e){
			if(! (e instanceof NoResultException)){
				e.printStackTrace();
			}
			return false;
		}
		
	}
	
	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}

	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}

	public TecnicoService getTecnicoService() {
		return tecnicoService;
	}

	public void setTecnicoService(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

	public String getACAO_DETALHAMENTO_FINALIZAR_CHAMADO() {
		return ACAO_DETALHAMENTO_FINALIZAR_CHAMADO;
	}
	
	public String getACAO_DETALHAMENTO_TRABALHAR_CHAMADO() {
		return ACAO_DETALHAMENTO_TRABALHAR_CHAMADO;
	}
	
	public String getACAO_DETALHAMENTO_ALOCAR_TECNICO() {
		return ACAO_DETALHAMENTO_ALOCAR_TECNICO;
	}


	public String getACAO_DETALHAMENTO_SOLICITACAO_VIABILIDADE() {
		return ACAO_DETALHAMENTO_SOLICITACAO_VIABILIDADE;
	}


	public String getACAO_DETALHAMENTO_EDITAR_CHAMADO() {
		return ACAO_DETALHAMENTO_EDITAR_CHAMADO;
	}


	public String getVIEW_BOX_ACOES_TECNICAS() {
		return VIEW_BOX_ACOES_TECNICAS;
	}

	
	
}
