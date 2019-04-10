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
 * Classe responsável por representar uma rejeicao da solução do chamado
 * @since 22/02/2019
 * @author alyssonnascimento
 *
 */
@Entity
@Table(name="desaprovacao_solucao_chamado")
public class DesaprovacaoSolucaoChamado extends PersistentEntityImpl{

	@Id
	@Column(name = "dsc_cod_id")
	@SequenceGenerator(name = "seq_desaprovacao", sequenceName = "seq_desaprovacao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_desaprovacao", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usu_responsavel_cod_id_fk")
	private Usuario usuario;
	
	@Column(name = "dsc_motivo")
	private String motivo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dsc_dt_data")
	private Date data;

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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
