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

import br.com.cogerh.template.model.Usuario;

/**
 * CLASSE RESPONSAVEL POR REPRESENTAR UMA ORDEM DE SERVIÇO QUE O CHAMADO TERÁ
 * @author alyssonnascimento
 *
 */
@Entity
@Table(name="ordem_servico")
public class OrdemServico extends PersistentEntityImpl{

	@Id
	@Column(name = "ord_cod_id_pk")
	@SequenceGenerator(name = "seq_ordem_servico", sequenceName = "seq_ordem_servico", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_ordem_servico", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "ord_numero")
	private Integer numero;
	
	@Column(name = "ord_titulo")
	private String titulo;
	
	@Column(name = "ord_descricao")
	private String descricao;
	
	@Column(name = "ord_tempo_necessario")
	private Double tempoNecessario;
	
	@Column(name = "ord_data")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "usu_cod_id_fk")
	private Usuario usuarioResponsavel;
	
	@ManyToOne
	@JoinColumn(name = "cha_cod_id_fk")
	private Chamado chamado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public Double getTempoNecessario() {
		return tempoNecessario;
	}

	public void setTempoNecessario(Double tempoNecessario) {
		this.tempoNecessario = tempoNecessario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	
}
