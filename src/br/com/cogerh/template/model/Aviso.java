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
 * CLASSE RESPONSAVEL POR REPRESENTAR UM AVISO QUE SERÁ MANTIDO NO SISTEMA
 * @author alyssonnascimento
 *
 */
@Entity
@Table(name = "aviso")
public class Aviso extends PersistentEntityImpl{

	@Id
	@Column(name = "avs_cod_id")
	@SequenceGenerator(name = "seq_aviso", sequenceName = "seq_aviso", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_aviso", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "avs_titulo")
	private String titulo;
	
	@Column(name = "avs_conteudo", length = 10000)
	private String conteudo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "avs_data_publicacao")
	private Date dataPublicacao;
	
	@Column(name = "avs_is_ativo")
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "usu_id_fk_autor")
	private Usuario usuarioAutor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuarioAutor() {
		return usuarioAutor;
	}

	public void setUsuarioAutor(Usuario usuarioAutor) {
		this.usuarioAutor = usuarioAutor;
	}
	
	
}
