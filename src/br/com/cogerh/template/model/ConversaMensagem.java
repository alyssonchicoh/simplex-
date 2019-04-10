package br.com.cogerh.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="conversa_mensagem")
public class ConversaMensagem extends PersistentEntityImpl{

	@Id
	@Column(name = "cnm_cod_id")
	@SequenceGenerator(name = "seq_conversa_mensagem", sequenceName = "seq_conversa_mensagem", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_conversa_mensagem", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "con_cod_id_fk")
	private Conversa conversa;
	
	@ManyToOne
	@JoinColumn(name = "men_cod_id_fk")
	private Mensagem mensagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
