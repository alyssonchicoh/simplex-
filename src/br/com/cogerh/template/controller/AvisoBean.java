package br.com.cogerh.template.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Aviso;
import br.com.cogerh.template.service.AvisoService;


@Controller
@Scope("view")
public class AvisoBean extends AbstractBean{

	@Autowired
	private AvisoService avisoService;
	
	private List<Aviso> avisoList;
	
	private Aviso aviso;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@PostConstruct
	public void init(){
		aviso = new Aviso();
		consultar();
	}
	
	public String showFormNovo(){
		return "novo.xhtml?faces-redirect=true";
	}
	
	public void salvar(){
		try {
			aviso.setUsuarioAutor(usuarioWeb.getUsuario());
			aviso.setDataPublicacao(new Date());
			aviso.setAtivo(true);
			aviso = avisoService.salvar(aviso);
			super.adicionaMensagemDeSucesso("Aviso salvo com sucesso");
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void consultar(){
		try {
			avisoList = avisoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AvisoService getAvisoService() {
		return avisoService;
	}

	public void setAvisoService(AvisoService avisoService) {
		this.avisoService = avisoService;
	}

	public List<Aviso> getAvisoList() {
		return avisoList;
	}

	public void setAvisoList(List<Aviso> avisoList) {
		this.avisoList = avisoList;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	
	
	
}
