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
@Table(name = "usuario_gerencia")
public class UsuarioGerencia extends PersistentEntityImpl{

	@Id
	@Column(name = "usg_cod_id")
	@SequenceGenerator(name = "seq_usuario_gerencia", sequenceName = "seq_usuario_gerencia", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_usuario_gerencia", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usu_cod_id_fk")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "ger_cod_id_fk")
	private Gerencia gerencia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	
}
