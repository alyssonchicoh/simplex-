package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.service.ChamadoService;

/**
 * CLASSE RESPONSAVEL POR GERENCIAR AS INFORMAÇÕES QUE ESTARAM PRESENTE NA TELA PRINCIPAIS DOS USUÁRIOS
 * @author alyssonnascimento
 * @since 23/01/2019
 */

@Controller
@Scope("view")
public class DashboardBean extends AbstractBean{
	
	@Autowired
	private ChamadoService chamadoService;
	
	private List<Chamado> ultimosChamadosSolicitados;
	
	private List<Chamado> chamadosPendentes;
	
	private boolean chamadoPendente = false;
	
	@Autowired
	private UsuarioWeb usuarioWeb;

	@PostConstruct
	public void init(){
		chamadosPendentes = new ArrayList<Chamado>();
		consultarUltimosChamadosSolicitados();
		verificarChamadosPendentes();
	}
	
	private void consultarUltimosChamadosSolicitados(){
		try {
			ultimosChamadosSolicitados = chamadoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void verificarChamadosPendentes(){
		try {
			chamadosPendentes = chamadoService.listarChamadoBySolicitanteAndStatusSolucionado(usuarioWeb.getUsuario().getId());
				if(chamadosPendentes.size() > 0){
					chamadoPendente = true;
				}else{
				chamadoPendente = false;
				}
			} catch (Exception e) {
				chamadoPendente = false;
				e.printStackTrace();
			}
	}
	

	public ChamadoService getChamadoService() {
		return chamadoService;
	}

	public void setChamadoService(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}

	public List<Chamado> getUltimosChamadosSolicitados() {
		return ultimosChamadosSolicitados;
	}

	public void setUltimosChamadosSolicitados(
			List<Chamado> ultimosChamadosSolicitados) {
		this.ultimosChamadosSolicitados = ultimosChamadosSolicitados;
	}

	public boolean isChamadoPendente() {
		return chamadoPendente;
	}

	public void setChamadoPendente(boolean chamadoPendente) {
		this.chamadoPendente = chamadoPendente;
	}

	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}

	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}

	public List<Chamado> getChamadosPendentes() {
		return chamadosPendentes;
	}

	public void setChamadosPendentes(List<Chamado> chamadosPendentes) {
		this.chamadosPendentes = chamadosPendentes;
	}
	
	
	
}
