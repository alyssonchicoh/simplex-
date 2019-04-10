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

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe responsável por representar um arquivo anexado ao chamado
 * @author alyssonnascimento
 * @since 15/01/2019
 *
 */

@Entity
@Table(name = "arquivo_anexado")
public class ArquivoAnexado extends PersistentEntityImpl {
	
	@Id
	@Column(name = "arq_cod_id")
	@SequenceGenerator(name = "seq_arquixo_anexado", sequenceName = "seq_arquixo_anexado", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_arquixo_anexado", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "arq_nome")
	private String nome;
	
	@Column(name = "arq_caminho")
	private String caminho;
	
	@Column(name = "arq_formato")
	private String formato;
	
	@Column(name = "arq_tamanho")
	private Double tamanho;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:MM:ss")
	@Column(name = "arq_data")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "cha_cod_id_fk")
	private Chamado chamado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	
}
