package br.com.cogerh.template.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.OrdemServico;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.CategoriaService;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.OrdemServicoService;
import br.com.cogerh.template.service.UsuarioService;

/**
 * 
 * @author alyssonnascimento
 *
 */

@Controller
@Scope("view")
public class OrdemServicoBean  extends AbstractBean{

	private OrdemServico os;
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	private List<OrdemServico> osList;

	private List<Usuario> usuarioList;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private List<Chamado> chamadoList;
	
	@Autowired
	private ChamadoService chamadoService;
	
	
	@PostConstruct
	private void init(){
		os = new OrdemServico();
		consultarUsuarios();
		consultarChamados();
		consultarOrdemServico();
	}
	
	public void consultarOS(){
		try {
			osList = ordemServicoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			ordemServicoService.salvar(os);
			super.adicionaMensagemDeSucesso("Ordem de Servico salva com Sucesso!");
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void consultarOrdemServico() {
		try {
			osList = ordemServicoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void consultarUsuarios(){
		try {
			usuarioList = usuarioService.listarUsuarios(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void consultarChamados(){
		try {
			chamadoList = chamadoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atribuirChamado(Chamado chamado){
		this.os.setChamado(chamado);
	}
	
	public void atribuirUsuario(Usuario usuario){
		this.os.setUsuarioResponsavel(usuario);
	}

	public String showFormNovo(){
		return "novo.xhtml?faces-redirect=true";
	}
	
	public OrdemServico getOs() {
		return os;
	}

	public void setOs(OrdemServico os) {
		this.os = os;
	}

	public OrdemServicoService getOrdemServicoService() {
		return ordemServicoService;
	}

	public void setOrdemServicoService(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}

	public List<OrdemServico> getOsList() {
		return osList;
	}

	public void setOsList(List<OrdemServico> osList) {
		this.osList = osList;
	}

	

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<Chamado> getChamadoList() {
		return chamadoList;
	}

	public void setChamadoList(List<Chamado> chamadoList) {
		this.chamadoList = chamadoList;
	}
	
	
}
