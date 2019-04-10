package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Gerencia;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.model.UsuarioGerencia;
import br.com.cogerh.template.service.GerenciaService;
import br.com.cogerh.template.service.UsuarioService;

@Controller
@Scope("view")
public class GerenciaBean extends AbstractBean{
	
	//VARIAVIES PARA GERENCIA
	
	@Autowired
	private GerenciaService gerenciaService;
	
	private Gerencia gerencia;
	
	private List<Gerencia> gerenciaList;
	
	//VARIAVEIS DE USUÁROS
	
	@Autowired
	private UsuarioService usuarioService;
	
	private List<Usuario> usuarioList;
	
	private List<Usuario> usuarioSelecionadosList;
	
	private String pesquisaUsuario;
	
	private String nomesUsuariosSelecionados;
	
	
	@PostConstruct
	public void init(){
		initListGerencia();
		listarUsuarios();
		initAllList();
		initObjetos();
	}
	
	/**
	 * Método utilizado para inicializar Todas as Listas presentes na classe
	 */
	public void initAllList(){
		usuarioSelecionadosList = new ArrayList<Usuario>();
	}
	
	public void initObjetos(){
		gerencia = new Gerencia();
	}
	/**
	 * INICIA A LISTA COM AS GERENCIAS CADASTRADAS NO BANCO
	 */
	public void initListGerencia(){
		gerenciaList = new ArrayList<>();
		try {
			gerenciaList = gerenciaService.listar(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * REDIRECIONA O USUÁRIO PARA O FORMULARIO DE CADASTRO
	 * @return URL DO FORM PARA CADASTRO
	 */
	public String showFormNovo(){
        return "novo.xhtml?faces-redirect=true";

	}
	
	public void salvar(){
		try {
			gerenciaService.salvar(gerencia);
			super.adicionaMensagemDeSucesso("Gerência Salva com Sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			super.adicionaMensagemDeSucesso(e.getMessage());

			e.printStackTrace();
		}
	}
	
	/**
	 * INICIA A LISTA COM OS USUÁRIOS CADASTRADOS NO SISTEMA
	 */
	public void listarUsuarios(){
		usuarioList = new ArrayList<Usuario>();
	
		try {
			usuarioList = usuarioService.listarUsuarios(pesquisaUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void atribuirUsuario(Usuario usuario){
		usuarioSelecionadosList.add(usuario);
		System.out.println("CHAMANDO");
		nomesUsuariosSelecionados = "";
		for (Usuario u : usuarioSelecionadosList) {
			nomesUsuariosSelecionados = nomesUsuariosSelecionados + u.getNome() + " ";
			
		}
		
	}
	
	/**
	 * VERIRFICAR SE UM DETERMINADO USUARIO EXISTA NA LSITA DE USUARIOS SELECIOANADAS NA TELA DE CADASTRO DE GERENCIA
	 * @param idUsuario
	 */
	public boolean verificarExistenciaUsuarioByList(Integer idUsuario){
		for (Usuario usuario : usuarioSelecionadosList) {
			if(usuario.getId().equals(idUsuario)){
				return true;
			}
		}
		return false;
	}
	
	public void adicionarUsuario(Usuario usuario){
		usuarioSelecionadosList.add(usuario);
	}
	
	public void removerUsuario(Usuario usuario){
		usuarioSelecionadosList.remove(usuario);
	}
	
	public void salvarUsuarios(){
		List<UsuarioGerencia> ugList = new ArrayList<UsuarioGerencia>();
		for (Usuario usuario : usuarioSelecionadosList) {
			UsuarioGerencia ug = new UsuarioGerencia();
			ug.setGerencia(gerencia);
			ug.setUsuario(usuario);
			ugList.add(ug);
		}
		gerencia.setResponsaveis(ugList);
	}
	
	public GerenciaService getGerenciaService() {
		return gerenciaService;
	}

	public void setGerenciaService(GerenciaService gerenciaService) {
		this.gerenciaService = gerenciaService;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public List<Gerencia> getGerenciaList() {
		return gerenciaList;
	}

	public void setGerenciaList(List<Gerencia> gerenciaList) {
		this.gerenciaList = gerenciaList;
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

	public String getPesquisaUsuario() {
		return pesquisaUsuario;
	}

	public void setPesquisaUsuario(String pesquisaUsuario) {
		this.pesquisaUsuario = pesquisaUsuario;
	}

	public List<Usuario> getUsuarioSelecionadosList() {
		return usuarioSelecionadosList;
	}

	public void setUsuarioSelecionadosList(List<Usuario> usuarioSelecionadosList) {
		this.usuarioSelecionadosList = usuarioSelecionadosList;
	}

	public String getNomesUsuariosSelecionados() {
		return nomesUsuariosSelecionados;
	}

	public void setNomesUsuariosSelecionados(String nomesUsuariosSelecionados) {
		this.nomesUsuariosSelecionados = nomesUsuariosSelecionados;
	}

	
	
	

}
