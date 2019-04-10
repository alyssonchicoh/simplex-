package br.com.cogerh.template.model;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="gerencia")
public class Gerencia extends PersistentEntityImpl{
	
	@Id
	@Column(name = "ger_cod_id")
	@SequenceGenerator(name = "seq_gerencia", sequenceName = "seq_gerencia", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_gerencia", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "ger_nome")
	private String nome;
	
	@OneToMany(mappedBy="gerencia", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UsuarioGerencia> responsaveis;

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

	public List<UsuarioGerencia> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<UsuarioGerencia> responsaveis) {
		this.responsaveis = responsaveis;
	}

	
	

}
