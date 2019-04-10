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

/**
 * Classe responsável por representar um Técnico
 * @author alysson
 * @sice 14/11/2018 
 *
 */
@Entity
@Table(name="tecnico")
public class Tecnico extends PersistentEntityImpl{

	

	@Id
	@Column(name = "tec_cod_id")
	@SequenceGenerator(name = "seq_tecnico", sequenceName = "seq_tecnico", allocationSize = 1, initialValue = 3)
	@GeneratedValue(generator = "seq_tecnico", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "tec_nota_geral")
	private Double notaGeral;
	
	@ManyToOne
	@JoinColumn(name = "usu_cod_id_fk")
	private Usuario usuario;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getNotaGeral() {
		return notaGeral;
	}

	public void setNotaGeral(Double notaGeral) {
		this.notaGeral = notaGeral;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((notaGeral == null) ? 0 : notaGeral.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		Tecnico tec = (Tecnico) obj;
		if(this.id.equals(tec.getId())){
			return true;
		}else{
			return false;
		}
	}

	
	
	
}
