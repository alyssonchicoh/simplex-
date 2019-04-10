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
 * REPRESENTA UMA MENSAGEM QUE SERÁ USADA NA CONVERSA DENTRO DO CHAT DA EQUIPE
 * @author 	alyssonnascimento
 * @since 	31/01/2019
 */

@Entity
@Table(name="mensagem")
public class Mensagem extends PersistentEntityImpl{

	@Id
	@Column(name = "men_cod_id")
	@SequenceGenerator(name = "seq_mensagem", sequenceName = "seq_mensagem", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_mensagem", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "men_conteudo")
	private String conteudo;
	
	@ManyToOne
	@JoinColumn(name = "usu_cod_id_fk_remetente")
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "men_data_inclusao")
	private Date data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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
	
	
	
	
}
