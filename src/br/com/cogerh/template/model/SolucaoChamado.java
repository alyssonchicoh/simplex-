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
 * CLASSE RESPONSÁVEL POR REPRESENTAR A SOLUÇÃO DO CHAMADO
 * @author alyssonnascimento
 * @since 22/01/2019
 * 
 */
@Entity
@Table(name="solucao_chamado")
public class SolucaoChamado extends PersistentEntityImpl{
	
	@Id
	@Column(name = "sol_cod_id")
	@SequenceGenerator(name = "sol_gerencia", sequenceName = "sol_gerencia", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "sol_gerencia", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "sol_descricao_solucao_usuario")
	private String solucaoUsuario;

	@Column(name = "sol_descricao_solucao_tecnica")
	private String solucaoTeccnica;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:MM:ss")
	@Column(name = "sol_data")
	private Date dataSolucao;
	
	@ManyToOne
	@JoinColumn(name = "cha_cod_id_fk")
	private Chamado chamado;
	
	@ManyToOne
	@JoinColumn(name = "usu_tec_cod_id_fk")
	private Usuario usuario;
	
	@Column(name = "sol_nota")
	private Integer notaSolucao;
	
	@Column(name = "sol_confirmacao")
	private boolean confirmacao;
	
	@ManyToOne
	@JoinColumn(name = "rsc_cod_id_fk")
	private DesaprovacaoSolucaoChamado rejeicaoSolucaoChamado;
	
	@Column(name = "sol_hash_url")
	private String hashUrl;
	
	public String getSolucaoUsuario() {
		return solucaoUsuario;
	}

	public void setSolucaoUsuario(String solucaoUsuario) {
		this.solucaoUsuario = solucaoUsuario;
	}

	public String getSolucaoTeccnica() {
		return solucaoTeccnica;
	}

	public void setSolucaoTeccnica(String solucaoTeccnica) {
		this.solucaoTeccnica = solucaoTeccnica;
	}

	public Date getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(Date dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getNotaSolucao() {
		return notaSolucao;
	}

	public void setNotaSolucao(Integer notaSolucao) {
		this.notaSolucao = notaSolucao;
	}

	public boolean isConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(boolean confirmacao) {
		this.confirmacao = confirmacao;
	}

	public DesaprovacaoSolucaoChamado getRejeicaoSolucaoChamado() {
		return rejeicaoSolucaoChamado;
	}

	public void setRejeicaoSolucaoChamado(
			DesaprovacaoSolucaoChamado rejeicaoSolucaoChamado) {
		this.rejeicaoSolucaoChamado = rejeicaoSolucaoChamado;
	}

	public String getHashUrl() {
		return hashUrl;
	}

	public void setHashUrl(String hashUrl) {
		this.hashUrl = hashUrl;
	}

	
	
	

}
