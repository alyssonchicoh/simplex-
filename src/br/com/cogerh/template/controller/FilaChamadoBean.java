package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.TecnicoService;

/**
 * Classe responsável por gerenciar a Tela de Fila de chamados do menu de observador
 * @author alyssonnascimento
 * @since 04/04/2019
 */
@Controller
@Scope("view")
public class FilaChamadoBean extends AbstractBean{
	
	private List<TecnicoChamadoVO> tecnicos;
	private List<Chamado> chamadoList;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ChamadoService chamadoService;
	
	private String pesquisaTecnico;
	
	
	@PostConstruct
	public void init(){
		tecnicos = new ArrayList<TecnicoChamadoVO>();
		consultarTecnicos();
	}
	
	public void consultarTecnicos(){
		try {
			List<Tecnico> tecnicos = tecnicoService.listar(pesquisaTecnico);
			for (Tecnico tecnico : tecnicos) {
				List<Chamado> chamados = consultarChamadoByTecnico(tecnico.getId());
				TecnicoChamadoVO vo = new TecnicoChamadoVO();
				vo.setTecnico(tecnico);
				vo.setChamados(chamados);
				this.tecnicos.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Chamado> consultarChamadoByTecnico(Integer idTecnico){
		try {
			return chamadoList = chamadoService.listarByTecnico(idTecnico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public TecnicoService getTecnicoService() {
		return tecnicoService;
	}

	public void setTecnicoService(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

	public String getPesquisaTecnico() {
		return pesquisaTecnico;
	}

	public void setPesquisaTecnico(String pesquisaTecnico) {
		this.pesquisaTecnico = pesquisaTecnico;
	}

	

	public List<TecnicoChamadoVO> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<TecnicoChamadoVO> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public ChamadoService getChamadoService() {
		return chamadoService;
	}

	public void setChamadoService(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}

	public List<Chamado> getChamadoList() {
		return chamadoList;
	}

	public void setChamadoList(List<Chamado> chamadoList) {
		this.chamadoList = chamadoList;
	}

	
	
	public class TecnicoChamadoVO{
		private Tecnico tecnico;
		private List<Chamado> chamados;
		public Tecnico getTecnico() {
			return tecnico;
		}
		public void setTecnico(Tecnico tecnico) {
			this.tecnico = tecnico;
		}
		public List<Chamado> getChamados() {
			return chamados;
		}
		public void setChamados(List<Chamado> chamados) {
			this.chamados = chamados;
		}
		
		
	}
	
	
}
