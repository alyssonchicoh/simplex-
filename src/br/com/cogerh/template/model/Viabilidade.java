package br.com.cogerh.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe responsavel por representar uma Viabilidade nas mudan�as
 * @author alyssonnascimento
 * @since  04/01/2019
 * @version 1.0
 */

@Entity
@Table(name="viabilidade")
public class Viabilidade extends PersistentEntityImpl{

	@Id
	@Column(name = "via_cod_id")
	@SequenceGenerator(name = "seq_viabilidade", sequenceName = "seq_viabilidade", allocationSize = 1, initialValue = 3)
	@GeneratedValue(generator = "seq_viabilidade", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "via_titulo")
	private String titulo;
	
	@Column(name = "via_descricao")
	private String descricao;
	
	@Column(name = "via_impacto_viabilidade")
	private String impactoViabilidade;
	
	@Column(name = "via_aprovado")
	private Integer aprovado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "via_data_requerimento")
	private Date data;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "via_data_aprovacao")
	private Date dataAprovacao;
	
	@ManyToOne
	@JoinColumn(name = "cat_cod_id_fk")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "usu_solicitante_cod_id_fk")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "usu_aprovador_cod_id_fk")
	private Usuario usuarioAprovador;
	
	@ManyToOne
	@JoinColumn(name = "ger_aprovador_cod_id_fk")
	private Gerencia gerencia;
	
	@ManyToOne
	@JoinColumn(name = "cha_cod_id_fk")
	private Chamado chamado;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getAprovado() {
		return aprovado;
	}

	public void setAprovado(Integer aprovado) {
		this.aprovado = aprovado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImpactoViabilidade() {
		return impactoViabilidade;
	}

	public void setImpactoViabilidade(String impactoViabilidade) {
		this.impactoViabilidade = impactoViabilidade;
	}

	public Usuario getUsuarioAprovador() {
		return usuarioAprovador;
	}

	public void setUsuarioAprovador(Usuario usuarioAprovador) {
		this.usuarioAprovador = usuarioAprovador;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	
}
