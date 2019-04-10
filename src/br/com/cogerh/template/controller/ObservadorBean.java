package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Observador;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.ObservadorService;
import br.com.cogerh.template.service.UsuarioService;

@Controller
@Scope("view")
public class ObservadorBean extends AbstractBean{
	
	@Autowired
	private ObservadorService ObservadorService;
	private List<Observador> ObservadorList;
	private Observador Observador;

	@Autowired
	private UsuarioService usuarioService;
	private List<Usuario> usuarioList;
	
	
	@PostConstruct
	public void init(){
		inicializarValores();
		listar();
		listarUsuarios();
	}
	
	public void inicializarValores(){
		Observador = new Observador();
		ObservadorList = new ArrayList<Observador>();
		usuarioList = new ArrayList<Usuario>();
	}
	
	public void listar(){
		try {
			ObservadorList = ObservadorService.listar(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarUsuarios(){
		try {
			usuarioList = usuarioService.listarUsuarios(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void salvar(){
		try {
			ObservadorService.salvar(Observador);
			super.adicionaMensagemDeSucesso("Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void definirUsuario(Usuario usuario){
		System.out.println(usuario.getLogin());
		Observador.setUsuario(usuario);
	}
	
	public String showFormNovo(){
		return "novo.xhtml?faces-redirect=true";
	}
	public String showFormListar(){
		return "lista.xhtml?faces-redirect=true";

	}
	
	
	public Observador getObservador() {
		return Observador;
	}

	public void setObservador(Observador Observador) {
		this.Observador = Observador;
	}

	public ObservadorService getObservadorService() {
		return ObservadorService;
	}

	public void setObservadorService(ObservadorService ObservadorService) {
		this.ObservadorService = ObservadorService;
	}

	public List<Observador> getObservadorList() {
		return ObservadorList;
	}

	public void setObservadorList(List<Observador> ObservadorList) {
		this.ObservadorList = ObservadorList;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
	
	
	
}


