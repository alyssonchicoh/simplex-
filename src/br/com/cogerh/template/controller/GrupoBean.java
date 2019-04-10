package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.GrupoPermissao;
import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.model.GrupoPermissao;
import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.service.GrupoService;
import br.com.cogerh.template.service.PermissaoService;
import br.com.cogerh.template.service.UsuarioService;

@Controller
@Scope("view")
public class GrupoBean extends AbstractBean
{
	private Grupo grupo = new Grupo();
	private String pesquisa; 

	@Autowired
	private GrupoService grupoService;
	
	private List<Grupo> filteredGrupos;

	@Autowired
	private PermissaoService permissaoService;

	private List<Grupo> grupoList = new ArrayList<Grupo>();

	private DualListModel<Permissao> permissaoPickList;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@Autowired
	private UsuarioService usuarioService;

	private List<Permissao> listaPermissaoAreaUsuario;
	
	private List<Permissao> listaPermissaoAreaObservadores;
	
	private List<Permissao> listaPermissaoAreaTecnicos;
	
	private List<Permissao> listaPermissaoAreaAdministradores;

	private List<Permissao> listaPermissaoSelecionadas;
	
	
	
	@PostConstruct
	public void init()
	{
		try
		{
			grupo = new Grupo();
			
			this.grupoList = grupoService.listarGrupos(null);
			this.listaPermissaoAreaUsuario = permissaoService.listarPermissoesByAreaUsuario();
			this.listaPermissaoAreaObservadores = permissaoService.listarPermissoesByAreaObservadores();
			this.listaPermissaoAreaTecnicos = permissaoService.listarPermissoesByAreaTecnicos();
			this.listaPermissaoAreaAdministradores = permissaoService.listarPermissoesByAreaAdministradores();
			this.listaPermissaoSelecionadas = new ArrayList<Permissao>();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

    public void initForm(Integer grupoId) {
    
    	
    	
    }
    
    public void selecionarPermissao(Permissao permissao){
    	System.out.println("Chamou");
    }

	public void salvar(){
		
		try{
			obterPermissoesSelecionadas();
			List<GrupoPermissao> grupoPermissaoList = new ArrayList<GrupoPermissao>();
			for (Permissao permissao : listaPermissaoSelecionadas) {
				GrupoPermissao gp = new GrupoPermissao();
				gp.setPermissao(permissao);
				gp.setGrupo(grupo);
				grupoPermissaoList.add(gp);
			}
			grupo.setGrupoPermissaoList(grupoPermissaoList);
			grupoService.salvarGrupo(this.grupo);

			this.grupo = new Grupo();

			this.adicionaMensagemDeSucesso("Grupo adicionado com sucesso");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			this.adicionaMensagemDeErro("Ocorreu um erro ao tentar salvar o registro");
		}
	}
	
	public void obterPermissoesSelecionadas(){
		
		for (Permissao permissao : listaPermissaoAreaUsuario) {
			if(permissao.isCheck()){
				listaPermissaoSelecionadas.add(permissao);
			}
		}
		
		for (Permissao permissao : listaPermissaoAreaObservadores) {
			if(permissao.isCheck()){
				listaPermissaoSelecionadas.add(permissao);
			}
		}
		
		for (Permissao permissao : listaPermissaoAreaTecnicos) {
			if(permissao.isCheck()){
				listaPermissaoSelecionadas.add(permissao);
			}
		}
		
		for (Permissao permissao : listaPermissaoAreaAdministradores) {
			if(permissao.isCheck()){
				listaPermissaoSelecionadas.add(permissao);
			}
		}
		
	}
	
	public void listar()
	{
		try
		{
			this.grupoList = grupoService.listarGrupos(this.pesquisa);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void remover(Grupo grupo)
	{
		try
		{
			grupoService.removerGrupo(grupo);

			this.grupoList = grupoService.listarGrupos(null);

			this.adicionaMensagemDeSucesso("Grupo removido com sucesso");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			this.adicionaMensagemDeErro("Ocorreu um erro ao tentar remover o registro");
		}
	}

	public void alterar()
	{
		try
		{
			for (Permissao permissao : permissaoPickList.getTarget())
			{
				boolean canAdd = true;

				if (null == this.grupo.getGrupoPermissaoList())
					this.grupo.setGrupoPermissaoList(new ArrayList<GrupoPermissao>());

				GrupoPermissao grupoPermissao = new GrupoPermissao();

				grupoPermissao.setPermissao(permissao);
				grupoPermissao.setGrupo(this.grupo);

				// Verifica se a permissao em questao ja foi adicionada a lista de permissoes do grupo
				for (GrupoPermissao gruPer : this.grupo.getGrupoPermissaoList())
				{
					if (gruPer.getPermissao().equals(grupoPermissao.getPermissao()))
					{
						canAdd = false;

						break;
					}
				}

				if (canAdd)
					this.grupo.getGrupoPermissaoList().add(grupoPermissao);
			}
			
			for (Permissao permissao : permissaoPickList.getSource())
			{
				boolean canRemove = false;

				GrupoPermissao grupoPermissao = new GrupoPermissao();

				/** Verifica se a permissao em questao foi adicionada ao pickList source
				 *  e precisa ser removida da lista de grupoPermissaos da grupo
				 **/
				for (GrupoPermissao bacGer : this.grupo.getGrupoPermissaoList())
				{
					if (bacGer.getPermissao().equals(permissao))
					{
						canRemove = true;

						grupoPermissao = bacGer;
						
						break;
					}
				}

				if (canRemove)
					this.grupo.getGrupoPermissaoList().remove(grupoPermissao);
			}

			this.grupo = grupoService.alterarGrupo(this.grupo);
			
			usuarioWeb.setUsuario(this.usuarioService.alterarUsuario(usuarioWeb.getUsuario()));

			this.adicionaMensagemDeSucesso("Grupo atualizado com sucesso");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			this.adicionaMensagemDeErro("Ocorreu um erro ao tentar atualizar o registro");
		}
	}

	
	
	
	public String showFormNovo() 
	{
        return "novo.xhtml?faces-redirect=true";
    }

	public String showFormListar() 
	{
        return "lista.xhtml?faces-redirect=true";
    }

	public String showFormEditar(Integer grupoId) 
	{
		return "novo.xhtml?faces-redirect=true&grupoId=" + grupoId;
	}

	public Grupo getGrupo() {return grupo;}
	public void setGrupo(Grupo grupo) {this.grupo = grupo;}

	public List<Grupo> getGrupoList() {return grupoList;}
	public void setGrupoList(List<Grupo> grupoList) {this.grupoList = grupoList;}

	public String getPesquisa() {return pesquisa;}
	public void setPesquisa(String pesquisa) {this.pesquisa = pesquisa;}

	public DualListModel<Permissao> getPermissaoPickList() {return permissaoPickList;}
	public void setPermissaoPickList(DualListModel<Permissao> permissaoPickList) {this.permissaoPickList = permissaoPickList;}

	public List<Grupo> getFilteredGrupos() {
		return filteredGrupos;
	}

	public void setFilteredGrupos(List<Grupo> filteredGrupos) {
		this.filteredGrupos = filteredGrupos;
	}

	public List<Permissao> getListaPermissaoAreaUsuario() {
		return listaPermissaoAreaUsuario;
	}

	public void setListaPermissaoAreaUsuario(
			List<Permissao> listaPermissaoAreaUsuario) {
		this.listaPermissaoAreaUsuario = listaPermissaoAreaUsuario;
	}

	public List<Permissao> getListaPermissaoAreaObservadores() {
		return listaPermissaoAreaObservadores;
	}

	public void setListaPermissaoAreaObservadores(
			List<Permissao> listaPermissaoAreaObservadores) {
		this.listaPermissaoAreaObservadores = listaPermissaoAreaObservadores;
	}

	public List<Permissao> getListaPermissaoAreaTecnicos() {
		return listaPermissaoAreaTecnicos;
	}

	public void setListaPermissaoAreaTecnicos(
			List<Permissao> listaPermissaoAreaTecnicos) {
		this.listaPermissaoAreaTecnicos = listaPermissaoAreaTecnicos;
	}

	public List<Permissao> getListaPermissaoAreaAdministradores() {
		return listaPermissaoAreaAdministradores;
	}

	public void setListaPermissaoAreaAdministradores(
			List<Permissao> listaPermissaoAreaAdministradores) {
		this.listaPermissaoAreaAdministradores = listaPermissaoAreaAdministradores;
	}

	public List<Permissao> getListaPermissaoSelecionadas() {
		return listaPermissaoSelecionadas;
	}

	public void setListaPermissaoSelecionadas(
			List<Permissao> listaPermissaoSelecionadas) {
		this.listaPermissaoSelecionadas = listaPermissaoSelecionadas;
	}


	
}