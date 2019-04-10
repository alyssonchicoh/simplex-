package br.com.cogerh.template.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author alysson (alysson.chicoh@gmail.com)
 *
 */


@Entity
@Table(name = "chamado")
public class Chamado extends PersistentEntityImpl {

	@Id
	@Column(name = "cha_cod_id")
	@SequenceGenerator(name = "seq_chamado", sequenceName = "seq_chamado", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_chamado", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "cha_numero")
	private String numero;

	@Column(name = "cha_titulo")
	private String titulo;

	@Column(name = "cha_descricao")
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cha_data_abertura")
	private Date dataAbertura;

	@Temporal(TemporalType.DATE)
	@Column(name = "cha_data_encerramento")
	private Date dataEncerramento;

	@ManyToOne
	@JoinColumn(name = "usu_id_fk_solicitante")
	private Usuario solicitante;

	@Column(name = "cha_prioridade")
	private Integer prioridade;
	
	@Column(name = "cha_posicao_fila")
	private Integer posicaoFila;
	

	@Column(name = "cha_score_fila")
	private Integer scoreFila;

	@Column(name = "cha_is_tecnico_informado")
	private boolean isTecnicoInformado;

	@Column(name = "cha_is_retroativo")
	private boolean isRetroativo;

	@Column(name = "cha_is_trabalhos_parados")
	private boolean isTrabalhosParados;
	
	@Column(name = "cha_is_recorrente")
	private boolean isRecorrente;
	
	@Column(name = "cha_is_trabalhando")
	private boolean isTrabalhando;

	@ManyToOne
	@JoinColumn(name = "cat_cod_id_fk")
	private Categoria categoria;
	
	@OneToMany(mappedBy="chamado", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TecnicoChamado> tecnicosChamados;
	
	@Column(name = "cha_status")
	private Integer status;
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}
	public Integer getPosicaoFila() {
		return posicaoFila;
	}

	public void setPosicaoFila(Integer posicaoFila) {
		this.posicaoFila = posicaoFila;
	}

	public Integer getScoreFila() {
		return scoreFila;
	}

	public void setScoreFila(Integer scoreFila) {
		this.scoreFila = scoreFila;
	}

	public boolean isTecnicoInformado() {
		return isTecnicoInformado;
	}

	public void setTecnicoInformado(boolean isTecnicoInformado) {
		this.isTecnicoInformado = isTecnicoInformado;
	}

	public boolean isRetroativo() {
		return isRetroativo;
	}

	public void setRetroativo(boolean isRetroativo) {
		this.isRetroativo = isRetroativo;
	}

	public boolean isTrabalhosParados() {
		return isTrabalhosParados;
	}

	public void setTrabalhosParados(boolean isTrabalhosParados) {
		this.isTrabalhosParados = isTrabalhosParados;
	}

	public boolean isRecorrente() {
		return isRecorrente;
	}

	public void setRecorrente(boolean isRecorrente) {
		this.isRecorrente = isRecorrente;
	}

	public List<TecnicoChamado> getTecnicosChamados() {
		return tecnicosChamados;
	}

	public void setTecnicosChamados(List<TecnicoChamado> tecnicosChamados) {
		this.tecnicosChamados = tecnicosChamados;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isTrabalhando() {
		return isTrabalhando;
	}

	public void setTrabalhando(boolean isTrabalhando) {
		this.isTrabalhando = isTrabalhando;
	}

	

	
	
}
