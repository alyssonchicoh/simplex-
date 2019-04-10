package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.util.FiltroChamadoDadosGerais;

/**
 * Classe responsavel pelo gerenciamento de filtros da tela de consulta de chamado
 * @since 10/04/2019
 * @author alyssonnascimento
 */
@Controller
@Scope("view")
public class FiltroChamadoBean extends AbstractBean{
	
	
	private String tituloFiltro;
	
	private String descricaoFiltro;
	
	private Integer prioridadeFiltro;
	
	private Integer idCategoriaFiltro;
	
	private Boolean jaInformadoFiltro;
	
	private Boolean recorrenteFiltro;
	
	private Boolean trabalhosParadosFiltro;
	
	private Date dataAberturaInicioFiltro;
	private Date dataAberturaFinalFiltro;
	
	private Date dataEncerramentoInicioFiltro;
	private Date dataEncerramentoFinalFiltro;

	private Date dataUltimaAtualizacaoInicioFiltro;
	private Date dataUltimaAtualizacaoFinalFiltro;

	
	private Date dataSolucaoInicioFiltro;
	private Date dataSolucaoFinalFiltro;
	
	private FiltroChamadoDadosGerais filtroChamadoDadosGerais;
	
	private List<Chamado> chamadoList;
	
	@Autowired
	private ChamadoService chamadoService;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	private List<ExibicaoCampo> exibicaoCampos;
	private List<String> inicias = new ArrayList<String>();

	
	private final String NUMERO_CHAMADO = "Número";
	private final String TITULO_CHAMADO = "Título";
	private final String DESCRICAO_CHAMADO = "Descrição";
	private final String DATA_ABERTURA_CHAMADO = "Data Abertura";
	private final String DATA_ENCERRAMENTO_CHAMADO = "Data Encerramento";
	private final String SOLICITANTE_CHAMADO = "Solicitante";
	private final String PRIORIDADE_CHAMADO = "Prioridade";
	private final String POSICAO_FILA_CHAMADO = "Posição na Fila";
	private final String SCORE_FILA_CHAMADO = "Score da Fila";
	private final String TECNICO_INFORMADO_CHAMADO = "Tecnico Já Informado?";
	private final String RECORRENTE_CHAMADO = "Chamado Recorrente?";
	private final String CATEGORIA_CHAMADO = "Categoria";
	private final String TECNICOS_CHAMADO = "Técnicos Alocados ";
	private final String STATUS_CHAMADO = "Status do Chamado ";

	
	@PostConstruct
	public void init(){
		try {
			chamadoList = chamadoService.listarChamadosSolicitadoByUsuario(usuarioWeb.getUsuario().getId());
			initCamposIniciais();

			initCampos();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initValores();
	}

	public void initValores(){
		tituloFiltro = null;
		descricaoFiltro = null;
		prioridadeFiltro = null; 
		idCategoriaFiltro = null;
		jaInformadoFiltro = null; 
		recorrenteFiltro = null;
		trabalhosParadosFiltro = null; 
		dataAberturaInicioFiltro = null;
		dataAberturaFinalFiltro = null;
		dataEncerramentoInicioFiltro = null; 
		dataEncerramentoFinalFiltro = null;
		dataUltimaAtualizacaoInicioFiltro = null; 
		dataUltimaAtualizacaoFinalFiltro = null; 
		dataSolucaoInicioFiltro = null; 
		dataSolucaoFinalFiltro =  null;
	}
	
	private void initFiltroChamadoDadosGerais(){
		this.filtroChamadoDadosGerais = new FiltroChamadoDadosGerais(tituloFiltro, descricaoFiltro, prioridadeFiltro, 
				idCategoriaFiltro, jaInformadoFiltro, recorrenteFiltro, 
				trabalhosParadosFiltro, dataAberturaInicioFiltro, dataAberturaFinalFiltro,
				dataEncerramentoInicioFiltro, dataEncerramentoFinalFiltro, dataUltimaAtualizacaoInicioFiltro, 
				dataUltimaAtualizacaoFinalFiltro, dataSolucaoInicioFiltro, dataSolucaoFinalFiltro);
	}
	
	private void initCampos(){
		exibicaoCampos = new ArrayList<FiltroChamadoBean.ExibicaoCampo>();
		List<String> campos = getAll();
		
		for (String campo : campos) {
			ExibicaoCampo exbicao = new ExibicaoCampo();
			exbicao.setNome(campo);
			if(inicias.contains(campo)){
				exbicao.setValor(true);
			}else{
				exbicao.setValor(false);
			}
			exibicaoCampos.add(exbicao);
		}
	}
	
	private void initCamposIniciais(){
		inicias.add(this.NUMERO_CHAMADO);
		inicias.add(this.STATUS_CHAMADO);
		inicias.add(this.DATA_ABERTURA_CHAMADO);
		inicias.add(this.TITULO_CHAMADO);

	}
	
	
	public void consultar(){
		try {
			initFiltroChamadoDadosGerais();
			chamadoList = chamadoService.listarByFiltros(filtroChamadoDadosGerais);
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean verificaExibicaoCampo(String campo){
		for (ExibicaoCampo c : exibicaoCampos) {
			if(c.getNome().equals(campo)){
				return c.isValor();
			}
		}
		return false;
	}
	
	public class ExibicaoCampo{
		private String nome;
		private boolean valor;
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public boolean isValor() {
			return valor;
		}
		public void setValor(boolean valor) {
			this.valor = valor;
		}
	}
	
		public List<String> getAll(){
			List<String> all = new ArrayList<String>();
			all.add(NUMERO_CHAMADO);
			all.add(TITULO_CHAMADO);
			all.add(DESCRICAO_CHAMADO);
			all.add(DATA_ABERTURA_CHAMADO);
			all.add(DATA_ENCERRAMENTO_CHAMADO);
			all.add(SOLICITANTE_CHAMADO);
			all.add(PRIORIDADE_CHAMADO);
			all.add(POSICAO_FILA_CHAMADO);
			all.add(SCORE_FILA_CHAMADO);
			all.add(TECNICO_INFORMADO_CHAMADO);
			all.add(RECORRENTE_CHAMADO);
			all.add(CATEGORIA_CHAMADO);
			all.add(TECNICOS_CHAMADO);
			all.add(STATUS_CHAMADO);

			
			
			return all;
		}
	
		
	public void atualizaCampo(String campo){
		for (ExibicaoCampo c : exibicaoCampos) {
			if(c.getNome().equals(campo)){
				if(c.isValor()){
					c.setValor(false);
				}else{
					c.setValor(true);
				}
			}
		}
	}
	
	public boolean verificaCampoMarcado(ActionEvent event){
		
	  String valor =  (String)event.getComponent().getAttributes().get("action");
		
		for (ExibicaoCampo c : exibicaoCampos) {
			if(c.getNome().equals(valor)){
				return c.isValor();
			}
		}
		return false;
	}
	public String getTituloFiltro() {
		return tituloFiltro;
	}

	public void setTituloFiltro(String tituloFiltro) {
		this.tituloFiltro = tituloFiltro;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

	public Integer getPrioridadeFiltro() {
		return prioridadeFiltro;
	}

	public void setPrioridadeFiltro(Integer prioridadeFiltro) {
		this.prioridadeFiltro = prioridadeFiltro;
	}

	public Integer getIdCategoriaFiltro() {
		return idCategoriaFiltro;
	}

	public void setIdCategoriaFiltro(Integer idCategoriaFiltro) {
		this.idCategoriaFiltro = idCategoriaFiltro;
	}

	public Boolean getJaInformadoFiltro() {
		return jaInformadoFiltro;
	}

	public void setJaInformadoFiltro(Boolean jaInformadoFiltro) {
		this.jaInformadoFiltro = jaInformadoFiltro;
	}

	public Boolean getRecorrenteFiltro() {
		return recorrenteFiltro;
	}

	public void setRecorrenteFiltro(Boolean recorrenteFiltro) {
		this.recorrenteFiltro = recorrenteFiltro;
	}

	public Boolean getTrabalhosParadosFiltro() {
		return trabalhosParadosFiltro;
	}

	public void setTrabalhosParadosFiltro(Boolean trabalhosParadosFiltro) {
		this.trabalhosParadosFiltro = trabalhosParadosFiltro;
	}

	public Date getDataAberturaInicioFiltro() {
		return dataAberturaInicioFiltro;
	}

	public void setDataAberturaInicioFiltro(Date dataAberturaInicioFiltro) {
		this.dataAberturaInicioFiltro = dataAberturaInicioFiltro;
	}

	public Date getDataAberturaFinalFiltro() {
		return dataAberturaFinalFiltro;
	}

	public void setDataAberturaFinalFiltro(Date dataAberturaFinalFiltro) {
		this.dataAberturaFinalFiltro = dataAberturaFinalFiltro;
	}

	public Date getDataEncerramentoInicioFiltro() {
		return dataEncerramentoInicioFiltro;
	}

	public void setDataEncerramentoInicioFiltro(Date dataEncerramentoInicioFiltro) {
		this.dataEncerramentoInicioFiltro = dataEncerramentoInicioFiltro;
	}

	public Date getDataEncerramentoFinalFiltro() {
		return dataEncerramentoFinalFiltro;
	}

	public void setDataEncerramentoFinalFiltro(Date dataEncerramentoFinalFiltro) {
		this.dataEncerramentoFinalFiltro = dataEncerramentoFinalFiltro;
	}

	public Date getDataUltimaAtualizacaoInicioFiltro() {
		return dataUltimaAtualizacaoInicioFiltro;
	}

	public void setDataUltimaAtualizacaoInicioFiltro(
			Date dataUltimaAtualizacaoInicioFiltro) {
		this.dataUltimaAtualizacaoInicioFiltro = dataUltimaAtualizacaoInicioFiltro;
	}

	public Date getDataUltimaAtualizacaoFinalFiltro() {
		return dataUltimaAtualizacaoFinalFiltro;
	}

	public void setDataUltimaAtualizacaoFinalFiltro(
			Date dataUltimaAtualizacaoFinalFiltro) {
		this.dataUltimaAtualizacaoFinalFiltro = dataUltimaAtualizacaoFinalFiltro;
	}

	public Date getDataSolucaoInicioFiltro() {
		return dataSolucaoInicioFiltro;
	}

	public void setDataSolucaoInicioFiltro(Date dataSolucaoInicioFiltro) {
		this.dataSolucaoInicioFiltro = dataSolucaoInicioFiltro;
	}

	public Date getDataSolucaoFinalFiltro() {
		return dataSolucaoFinalFiltro;
	}

	public void setDataSolucaoFinalFiltro(Date dataSolucaoFinalFiltro) {
		this.dataSolucaoFinalFiltro = dataSolucaoFinalFiltro;
	}

	public FiltroChamadoDadosGerais getFiltroChamadoDadosGerais() {
		return filtroChamadoDadosGerais;
	}

	public void setFiltroChamadoDadosGerais(
			FiltroChamadoDadosGerais filtroChamadoDadosGerais) {
		this.filtroChamadoDadosGerais = filtroChamadoDadosGerais;
	}

	public List<Chamado> getChamadoList() {
		return chamadoList;
	}

	public void setChamadoList(List<Chamado> chamadoList) {
		this.chamadoList = chamadoList;
	}

	public ChamadoService getChamadoService() {
		return chamadoService;
	}

	public void setChamadoService(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}
	
	public List<ExibicaoCampo> getExibicaoCampos() {
		return exibicaoCampos;
	}

	public void setExibicaoCampos(List<ExibicaoCampo> exibicaoCampos) {
		this.exibicaoCampos = exibicaoCampos;
	}

	

		public String getNUMERO_CHAMADO() {
			return NUMERO_CHAMADO;
		}

		public String getTITULO_CHAMADO() {
			return TITULO_CHAMADO;
		}

		public String getDESCRICAO_CHAMADO() {
			return DESCRICAO_CHAMADO;
		}

		public String getDATA_ABERTURA_CHAMADO() {
			return DATA_ABERTURA_CHAMADO;
		}

		public String getDATA_ENCERRAMENTO_CHAMADO() {
			return DATA_ENCERRAMENTO_CHAMADO;
		}

		public String getSOLICITANTE_CHAMADO() {
			return SOLICITANTE_CHAMADO;
		}

		public String getPRIORIDADE_CHAMADO() {
			return PRIORIDADE_CHAMADO;
		}

		public String getPOSICAO_FILA_CHAMADO() {
			return POSICAO_FILA_CHAMADO;
		}

		public String getSCORE_FILA_CHAMADO() {
			return SCORE_FILA_CHAMADO;
		}

		public String getTECNICO_INFORMADO_CHAMADO() {
			return TECNICO_INFORMADO_CHAMADO;
		}

		public String getRECORRENTE_CHAMADO() {
			return RECORRENTE_CHAMADO;
		}

		public String getCATEGORIA_CHAMADO() {
			return CATEGORIA_CHAMADO;
		}

		public String getTECNICOS_CHAMADO() {
			return TECNICOS_CHAMADO;
		}

		public UsuarioWeb getUsuarioWeb() {
			return usuarioWeb;
		}

		public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
			this.usuarioWeb = usuarioWeb;
		}

		public List<String> getInicias() {
			return inicias;
		}

		public void setInicias(List<String> inicias) {
			this.inicias = inicias;
		}

		public String getSTATUS_CHAMADO() {
			return STATUS_CHAMADO;
		}
	
	
	

	
	
}
