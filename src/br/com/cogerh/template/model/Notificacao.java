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
 * Essa classe � respons�vel por representar uma notifica��o que ser� exibida no sistema
 * @author Alysson Nascimento
 * @since 29/12/2018
 */
@Entity
@Table(name = "notificacao")
public class Notificacao extends PersistentEntityImpl {

	/**
	 * ID DA NOTIFICA��O
	 */
	@Id
	@Column(name = "not_cod_id")
	@SequenceGenerator(name = "seq_notificacao", sequenceName = "seq_notificacao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_notificacao", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	/**
	 * TITULO DA MENSAGEM QUE SER� EXIBIDA NA NOTIFICA��O
	 */
	@Column(name = "not_titulo")
	private String titulo;
	
	/**
	 * MENSAGEM QUE SER� EXIBIDA NA NOTIFICA��O
	 */
	@Column(name = "not_mensagem")
	private String mensagem;
	
	/**
	 * LINK PARA REDIRECIONAR O USU�RIO
	 */
	@Column(name = "not_link")
	private String link;
	
	/**
	 * USU�RIO QUE RECEBER� A NOTIFICA��O
	 */
	@ManyToOne
	@JoinColumn(name = "usu_id_fk_destinatario")
	private Usuario usuarioDestinatario;
	
	/**
	 * DATA QUE A NOTIFICA��O FOI GERADA
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:MM:ss")
	@Column(name = "not_data")
	private Date dataNofificacao;
	
	/**
	 * CONDI��O PARA VERIFICAR SE A NOTIFICA��O J� FOI LIDA PELO USU�RIO
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
