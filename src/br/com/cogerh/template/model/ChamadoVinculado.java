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
 * CLASSE RESPONSAVEL POR REPRESENTAR UM CHAMADO VINCULADO A OUTRO CHAMADO
 * @author alyssonnascimento
 *
 */

@Entity
@Table(name = "chamado_vinculado")
public class ChamadoVinculado extends PersistentEntityImpl {

	@Id
	@Column(name = "chv_cod_id")
	@SequenceGenerator(name = "seq_chamado_vinculado", sequenceName = "seq_chamado_vinculado", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_chamado_vinculado", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cha_origem_cod_id_fk")
	private Chamado chamadoOrigem;
	
	@ManyToOne
	@JoinColumn(name = "cha_destino_cod_id_fk")
	private Chamado chamadoDestino;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Chamado getChamadoOrigem() {
		return chamadoOrigem;
	}

	public void setChamadoOrigem(Chamado chamadoOrigem) {
		this.chamadoOrigem = chamadoOrigem;
	}

	public Chamado getChamadoDestino() {
		return chamadoDestino;
	}

	public void setChamadoDestino(Chamado chamadoDestino) {
		this.chamadoDestino = chamadoDestino;
	}
	
	
}
