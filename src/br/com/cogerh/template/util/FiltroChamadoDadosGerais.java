package br.com.cogerh.template.util;

import java.util.Date;

/**
 * Classe responsavel por agrupar os filtros gerias de um Chamado
 * @author alysson
 *
 */
public class FiltroChamadoDadosGerais {

	private String titulo;
	private String descricao;
	private Integer  prioridade;
	private Integer idCategoria;
	private Boolean jaInformado;
	private Boolean recorrente;
	private Boolean trabalhosParados;
	private Date dataAberturaInicio;
	private Date dataAberturaFinal;
	private Date dataEncerramentoInicio;
	private Date dataEncerramentoFinal;
	private Date dataUltimaAtualizacaoInicio;
	private Date dataUltimaAtualizacaoFinal;
	private Date dataSolucaoInicio;
	private Date dataSolucaoFinal;

	
	
	
	public FiltroChamadoDadosGerais(String titulo, String descricao, Integer prioridade, Integer idCategoria,
									Boolean jaInformado, Boolean recorrente, Boolean trabalhosParados, 
									Date dataAberturaInicio,Date dataAberturaFinal, Date dataEncerramentoInicio,
									Date dataEncerramentoFinal,Date dataUltimaAtualizacaoInicio, Date dataUltimaAtualizacaoFinal, Date dataSolucaoInicio,Date dataSolucaoFinal) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.idCategoria = idCategoria;
		this.jaInformado = jaInformado;
		this.recorrente = recorrente;
		this.trabalhosParados = trabalhosParados;
		this.dataAberturaInicio = dataAberturaInicio;
		this.dataAberturaFinal = dataAberturaFinal;
		this.dataEncerramentoInicio = dataEncerramentoInicio;
		this.dataEncerramentoFinal = dataEncerramentoFinal;
		this.dataUltimaAtualizacaoInicio = dataUltimaAtualizacaoInicio;
		this.dataUltimaAtualizacaoFinal = dataUltimaAtualizacaoFinal;
		this.dataSolucaoInicio = dataSolucaoInicio;
		this.dataSolucaoFinal = dataSolucaoFinal;
	}
	
	
	public boolean isExistTitulo() {
		return this.titulo != null ? true : false;
	}
	
	public boolean isExistDescricao() {
		return this.descricao != null ? true : false;
	}
	
	public boolean isExistPrioridade() {
		return this.prioridade != null ? true : false;
	}
	
	public boolean isExistCategoria() {
		return this.idCategoria != null ? true : false;
	}
	
	public boolean isExistInformado() {
		return this.jaInformado != null ? true : false;
	}
	
	public boolean isExistRecorrente() {
		return this.recorrente != null ? true : false;
	}
	
	public boolean isExistTrabalhosParados() {
		return this.trabalhosParados != null ? true : false;
	}
	
	public boolean isExistDataAberturaInicio() {
		return this.dataAberturaInicio != null ? true : false;
	}
	
	public boolean isExistDataAberturaFinal() {
		return this.dataAberturaFinal != null ? true : false;
	}
	
	public boolean isExistDataEncerramentoInicio() {
		return this.dataEncerramentoInicio != null ? true : false;
	}
	
	public boolean isExistDataEncerramentoFinal() {
		return this.dataEncerramentoFinal != null ? true : false;
	}
	
	public boolean isExistDataUltimaAtualizacaoInicio() {
		return this.dataUltimaAtualizacaoInicio != null ? true : false;
	}
	
	public boolean isExistDataUltimaAtualizacaoFinal() {
		return this.dataUltimaAtualizacaoFinal != null ? true : false;
	}
	
	public boolean isExistDataSolucaoInicio() {
		return this.dataSolucaoInicio != null ? true : false;
	}
	
	public boolean isExistDatataSolucaoFinal() {
		return this.dataSolucaoFinal != null ? true : false;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public Integer getPrioridade() {
		return prioridade;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	
	public Boolean getJaInformado() {
		return jaInformado;
	}


	public Boolean getRecorrente() {
		return recorrente;
	}


	public Boolean getTrabalhosParados() {
		return trabalhosParados;
	}


	public Date getDataAberturaInicio() {
		return dataAberturaInicio;
	}
	public Date getDataAberturaFinal() {
		return dataAberturaFinal;
	}
	public Date getDataEncerramentoInicio() {
		return dataEncerramentoInicio;
	}
	public Date getDataEncerramentoFinal() {
		return dataEncerramentoFinal;
	}	

	public Date getDataUltimaAtualizacaoInicio() {
		return dataUltimaAtualizacaoInicio;
	}
	public Date getDataUltimaAtualizacaoFinal() {
		return dataUltimaAtualizacaoFinal;
	}


	public Date getDataSolucaoInicio() {
		return dataSolucaoInicio;
	}


	public Date getDataSolucaoFinal() {
		return dataSolucaoFinal;
	}
	
	
	
}
