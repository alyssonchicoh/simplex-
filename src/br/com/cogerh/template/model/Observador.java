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
@Table(name="observador")
public class Observador extends PersistentEntityImpl{
	
	@Id
	@Column(name = "obs_cod_id")
	@SequenceGenerator(name = "seq_observador", sequenceName = "seq_observador", allocationSize = 1, initialValue = 3)
	@GeneratedValue(generator = "seq_observador", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usu_cod_id_fk")
	private Usuario usuario;

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
	

}
