package br.com.cogerh.template.controller;

import java.text.SimpleDateFormat;
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

import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.service.CategoriaService;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.util.FiltroChamadoDadosGerais;

/**
 * Classe responsavel pelo gerenciamento de filtros da tela de consulta de
 * chamado
 * 
 * @since 10/04/2019
 * @author alyssonnascimento
 */
@Controller
@Scope("view")
public class FiltroChamadoBean extends AbstractBean {

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
	
	private List<Categoria> categoriaList;
	
	private List<String> prioridadeList;

	private List<String> camposAll;
	
	private List<String> camposSelecionados;
	
	private Categoria categoriaSelecionada;
	
	@Autowired
	private ChamadoService chamadoService;

	@Autowired
	private UsuarioWeb usuarioWeb;
	
	@Autowired
	private CategoriaService categoriaService;
	
	private String prioridadeSelecionada;

	private String queryCategoriaFiltro;
	
	private String labelDataAbertura;
	
	//LABEL DAS COLUNAS DO DATATABLE
	private final String NUMERO_CHAMADO = "Número";
	
	private final String TITULO_CHAMADO = "Título";
	
	private final String DESCRICAO_CHAMADO = "Descriçãoo";
	
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
	public void init() {
		try {
			initCamposAll();
			initCamposSelecionados();
			initListPrioridade();
			initListCategoria();
			chamadoList = chamadoService.listarChamadosSolicitadoByUsuario(usuarioWeb.getUsuario().getId());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initValores();
	}

	private void initCamposAll() {
		camposAll = new ArrayList<String>();

		this.camposAll.add(NUMERO_CHAMADO);
		this.camposAll.add(TITULO_CHAMADO);
		this.camposAll.add(DESCRICAO_CHAMADO);
		this.camposAll.add(DATA_ABERTURA_CHAMADO);
		this.camposAll.add(DATA_ENCERRAMENTO_CHAMADO);
		this.camposAll.add(SOLICITANTE_CHAMADO);
		this.camposAll.add(PRIORIDADE_CHAMADO);
		this.camposAll.add(POSICAO_FILA_CHAMADO);
		this.camposAll.add(SCORE_FILA_CHAMADO);
		this.camposAll.add(TECNICO_INFORMADO_CHAMADO);
		this.camposAll.add(RECORRENTE_CHAMADO);
		this.camposAll.add(CATEGORIA_CHAMADO);
		this.camposAll.add(TECNICOS_CHAMADO);
		this.camposAll.add(STATUS_CHAMADO);
	}

	private void initCamposSelecionados() {
		camposSelecionados = new ArrayList<String>();
		this.camposSelecionados.add(NUMERO_CHAMADO);
		this.camposSelecionados.add(STATUS_CHAMADO);
		this.camposSelecionados.add(DATA_ABERTURA_CHAMADO);
		this.camposSelecionados.add(TITULO_CHAMADO);
	}

	public void initValores() {
		tituloFiltro = null;
		descricaoFiltro = null;
		prioridadeFiltro = null;
		idCategoriaFiltro = null;
		jaInformadoFiltro = null;
		recorrenteFiltro = null;
		trabalhosParadosFiltro = null;
		
		dataEncerramentoInicioFiltro = null;
		dataEncerramentoFinalFiltro = null;
		dataUltimaAtualizacaoInicioFiltro = null;
		dataUltimaAtualizacaoFinalFiltro = null;
		dataSolucaoInicioFiltro = null;
		dataSolucaoFinalFiltro = null;
	}
	
	public void initListPrioridade(){
		prioridadeList = new ArrayList<String>();
		prioridadeList.add("Baixa");
		prioridadeList.add("Média");
		prioridadeList.add("Alta");
	}
	
	public void initListCategoria(){
		try {
			this.categoriaList = categoriaService.listar(queryCategoriaFiltro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initFiltroChamadoDadosGerais() {
		this.filtroChamadoDadosGerais = new FiltroChamadoDadosGerais(tituloFiltro, descricaoFiltro, prioridadeFiltro,
				idCategoriaFiltro, jaInformadoFiltro, recorrenteFiltro, trabalhosParadosFiltro,
				dataAberturaInicioFiltro, dataAberturaFinalFiltro, dataEncerramentoInicioFiltro,
				dataEncerramentoFinalFiltro, dataUltimaAtualizacaoInicioFiltro, dataUltimaAtualizacaoFinalFiltro,
				dataSolucaoInicioFiltro, dataSolucaoFinalFiltro);
	}

	public void consultar() {
		try {
			initFiltroChamadoDadosGerais();
			chamadoList = chamadoService.listarByFiltros(filtroChamadoDadosGerais);
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean verificaExistenciaExibicaoCampo(String campo) {
		if(camposSelecionados.contains(campo)) {
			return true;
		}else {
			return false;
		}
	}

	
	public void adicionaFiltroPrioridade(String prioridade){
		if(prioridade.equals("Baixa")){
			this.prioridadeFiltro = 1;
		}
		
		if(prioridade.equals("Média")){
			this.prioridadeFiltro = 2;

		}
		
		if(prioridade.equals("Alta")){
			this.prioridadeFiltro = 3;

		}
		
		prioridadeSelecionada = prioridade;
	}
	
	public void adicionaFiltroDataAbertura(){
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		if(this.dataAberturaInicioFiltro != null){
			this.labelDataAbertura +=  " "  + dataFormatada.format(dataAberturaInicioFiltro);
		}
		
		if(this.dataAberturaInicioFiltro != null && this.dataAberturaFinalFiltro != null){
			this.labelDataAbertura = this.labelDataAbertura + " Até ";
		}
		
		if(this.dataAberturaFinalFiltro != null){
			this.labelDataAbertura +=  " "  + dataFormatada.format(dataAberturaFinalFiltro);
		}
	}
	
	
	public void adicionarFiltroCategoria(Categoria categoria){
		this.idCategoriaFiltro = categoria.getId();
		this.categoriaSelecionada = categoria;
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
		System.out.println("Chamou!");
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

	public void setDataUltimaAtualizacaoInicioFiltro(Date dataUltimaAtualizacaoInicioFiltro) {
		this.dataUltimaAtualizacaoInicioFiltro = dataUltimaAtualizacaoInicioFiltro;
	}

	public Date getDataUltimaAtualizacaoFinalFiltro() {
		return dataUltimaAtualizacaoFinalFiltro;
	}

	public void setDataUltimaAtualizacaoFinalFiltro(Date dataUltimaAtualizacaoFinalFiltro) {
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

	public void setFiltroChamadoDadosGerais(FiltroChamadoDadosGerais filtroChamadoDadosGerais) {
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

	

	public String getSTATUS_CHAMADO() {
		return STATUS_CHAMADO;
	}

	public List<String> getCamposAll() {
		return camposAll;
	}

	public void setCamposAll(List<String> camposAll) {
		this.camposAll = camposAll;
	}

	public List<String> getCamposSelecionados() {
		return camposSelecionados;
	}

	public void setCamposSelecionados(List<String> camposSelecionados) {
		this.camposSelecionados = camposSelecionados;
	}

	public List<String> getPrioridadeList() {
		return prioridadeList;
	}

	public void setPrioridadeList(List<String> prioridadeList) {
		this.prioridadeList = prioridadeList;
	}

	public String getPrioridadeSelecionada() {
		return prioridadeSelecionada;
	}

	public void setPrioridadeSelecionada(String prioridadeSelecionada) {
		this.prioridadeSelecionada = prioridadeSelecionada;
	}

	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public String getQueryCategoriaFiltro() {
		return queryCategoriaFiltro;
	}

	public void setQueryCategoriaFiltro(String queryCategoriaFiltro) {
		this.queryCategoriaFiltro = queryCategoriaFiltro;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public String getLabelDataAbertura() {
		return labelDataAbertura;
	}

	public void setLabelDataAbertura(String labelDataAbertura) {
		this.labelDataAbertura = labelDataAbertura;
	}
	
	

}

