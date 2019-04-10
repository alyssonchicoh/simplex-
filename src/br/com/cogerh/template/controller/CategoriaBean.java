package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.service.CategoriaService;

@Controller
@Scope("view")
public class CategoriaBean extends AbstractBean{

	private Categoria categoria;
	
	private List<Categoria> categoriaList;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@PostConstruct
	public void init(){
		this.inicializaValores();
		this.listar();
	}
	
	
	public String showFormNovo(){
        return "novo.xhtml?faces-redirect=true";
    }
	
	/**
	 * Método utilizado para inicializar os valores das variaveis
	 */
	public void inicializaValores(){
		categoria = new Categoria();
		categoriaList = new ArrayList<Categoria>();

	}
	
	public void salvar(){
		try{
			Categoria cat = categoriaService.salvar(categoria);	
			if(cat!= null){
				categoria = new Categoria();
				adicionaMensagemDeSucesso("Categoria Salva com sucesso!");
			}else{
				adicionaMensagemDeErro("Erro ao Salvar");
			}
		}catch (Exception e){
			adicionaMensagemDeErro("Erro ao Salvar "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void listar(){
		try {
			categoriaList = categoriaService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}


	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}
	
	
	
	
	
	

}
