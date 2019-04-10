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
 * Classe responsavel por representar um acompanhamento de chamado
 * @author Alysson
 * @since 20/11/2018
 *
 */
@Entity
@Table(name = "acompanhamento")
public class Acompanhamento extends PersistentEntityImpl{
	
	@Id
	@Column(name = "aco_cod_id")
	@SequenceGenerator(name = "seq_acompanhamento", sequenceName = "seq_acompanhamento", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_acompanhamento", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "aco_texto")
	private String texto;
	
	@ManyToOne
	@JoinColumn(name = "cha_cod_id_fk")
	private Chamado chamado;
	
	@ManyToOne
	@JoinColumn(name = "usu_cod_id_fk")
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cha_data_abertura")
	private Date data;
	
	@Column(name = "mensagem_by_sistema")
	private boolean mensagemBySistema;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isMensagemBySistema() {
		return mensagemBySistema;
	}

	public void setMensagemBySistema(boolean mensagemBySistema) {
		this.mensagemBySistema = mensagemBySistema;
	}
	
	

}
