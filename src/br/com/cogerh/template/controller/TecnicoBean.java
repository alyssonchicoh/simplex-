package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.TecnicoService;
import br.com.cogerh.template.service.UsuarioService;

@Controller
@Scope("view")
public class TecnicoBean extends AbstractBean{
	
	@Autowired
	private TecnicoService tecnicoService;
	private List<Tecnico> tecnicoList;
	private Tecnico tecnico;

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
		tecnico = new Tecnico();
		tecnicoList = new ArrayList<Tecnico>();
		usuarioList = new ArrayList<Usuario>();
	}
	
	public void listar(){
		try {
			tecnicoList = tecnicoService.listar(null);
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
			tecnicoService.salvar(tecnico);
			super.adicionaMensagemDeSucesso("Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void definirUsuario(Usuario usuario){
		System.out.println(usuario.getLogin());
		tecnico.setUsuario(usuario);
	}
	
	public String showFormNovo(){
		return "novo.xhtml?faces-redirect=true";
	}
	public String showFormListar(){
		return "lista.xhtml?faces-redirect=true";

	}
	
	
	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public TecnicoService getTecnicoService() {
		return tecnicoService;
	}

	public void setTecnicoService(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

	public List<Tecnico> getTecnicoList() {
		return tecnicoList;
	}

	public void setTecnicoList(List<Tecnico> tecnicoList) {
		this.tecnicoList = tecnicoList;
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


