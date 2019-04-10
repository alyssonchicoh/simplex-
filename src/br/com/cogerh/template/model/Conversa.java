package br.com.cogerh.template.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * CLASSE RESPONSAVEL POR REPRESENTAR UMA CONVERSA DA EQUIPE
 * @author alyssonnascimento
 *
 */

@Entity
@Table(name="conversa")
public class Conversa extends PersistentEntityImpl{

	@Id
	@Column(name = "con_cod_id")
	@SequenceGenerator(name = "seq_conversa", sequenceName = "seq_conversa", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_conversa", strategy = GenerationType.SEQUENCE)	
	private Integer id;
	
	@Column(name = "con_conteudo")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "con_data_envio")
	private Date dataEnvio;
	
	@OneToMany(mappedBy="conversa", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ConversaMensagem> mensagens;
	
	@OneToMany(mappedBy="conversa", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ConversaUsuario> usuarios;
	

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

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public List<ConversaMensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<ConversaMensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public List<ConversaUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<ConversaUsuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	

}
