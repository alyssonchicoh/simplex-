package br.com.cogerh.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tela_permissao")
public class TelaPermissao extends PersistentEntityImpl{

	@Id
	@Column(name = "tpe_cod_id")
	@SequenceGenerator(name = "seq_tela_permissao", sequenceName = "seq_tela_permissao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_tela_permissao", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "tpe_nome")
	private String nome;
	
	@Column(name = "tpe_descricao")
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
