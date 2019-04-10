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
 * Essa classe é responsável por representar uma notificação que será exibida no sistema
 * @author Alysson Nascimento
 * @since 29/12/2018
 */
@Entity
@Table(name = "notificacao")
public class Notificacao extends PersistentEntityImpl {

	/**
	 * ID DA NOTIFICAÇÃO
	 */
	@Id
	@Column(name = "not_cod_id")
	@SequenceGenerator(name = "seq_notificacao", sequenceName = "seq_notificacao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_notificacao", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	/**
	 * TITULO DA MENSAGEM QUE SERÁ EXIBIDA NA NOTIFICAÇÃO
	 */
	@Column(name = "not_titulo")
	private String titulo;
	
	/**
	 * MENSAGEM QUE SERÁ EXIBIDA NA NOTIFICAÇÃO
	 */
	@Column(name = "not_mensagem")
	private String mensagem;
	
	/**
	 * LINK PARA REDIRECIONAR O USUÁRIO
	 */
	@Column(name = "not_link")
	private String link;
	
	/**
	 * USUÁRIO QUE RECEBERÁ A NOTIFICAÇÃO
	 */
	@ManyToOne
	@JoinColumn(name = "usu_id_fk_destinatario")
	private Usuario usuarioDestinatario;
	
	/**
	 * DATA QUE A NOTIFICAÇÃO FOI GERADA
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:MM:ss")
	@Column(name = "not_data")
	private Date dataNofificacao;
	
	/**
	 * CONDIÇÃO PARA VERIFICAR SE A NOTIFICAÇÃO JÁ FOI LIDA PELO USUÁRIO
	 */
	@Column(name = "not_lida")
	private boolean isLida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Usuario getUsuarioDestinatario() {
		return usuarioDestinatario;
	}

	public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
		this.usuarioDestinatario = usuarioDestinatario;
	}

	public Date getDataNofificacao() {
		return dataNofificacao;
	}

	public void setDataNofificacao(Date dataNofificacao) {
		this.dataNofificacao = dataNofificacao;
	}

	public boolean isLida() {
		return isLida;
	}

	public void setLida(boolean isLida) {
		this.isLida = isLida;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
}
