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
@Table(name="tecnico_chamado")
public class TecnicoChamado extends PersistentEntityImpl{

	@Id
	@Column(name = "tch_cod_id")
	@SequenceGenerator(name = "seq_tecnico_chamado", sequenceName = "seq_tecnico_chamado", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_tecnico_chamado", strategy = GenerationType.SEQUENCE)
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cha_cod_id_fk")
	private Chamado chamado;
	
	@ManyToOne
	@JoinColumn(name = "tec_cod_id_fk")
	private Tecnico tecnico;

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
